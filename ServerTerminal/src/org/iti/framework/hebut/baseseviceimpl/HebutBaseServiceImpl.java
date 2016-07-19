package org.iti.framework.hebut.baseseviceimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.iti.common.cache.CommonCache;
import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.annotations.CachingEntity;
import org.iti.entity.db.nosql.abstracts.AbstractNoSqlTreeEntity;
import org.iti.entity.db.nosql.interfaces.INoSqlEntity;
import org.iti.entity.interfaces.IEntity;
import org.iti.entity.interfaces.IEntity.EntityState;
import org.iti.entity.model.nosql.interfaces.INoSqlTree;
import org.iti.framework.hebut.basesevice.HebutBaseService;
import org.iti.framework.hebut.util.Page;
import org.iti.framework.hebut.util.PaginationHelper;
import org.iti.rdbms.base.impl.BaseServiceImpl;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.couchbase.client.CouchbaseClient;
@Service("HebutBaseServiceImpl")
public class HebutBaseServiceImpl extends BaseServiceImpl implements HebutBaseService  {
	//private final static Log log = LogFactory.getLog(HebutBaseServiceImpl.class);
	{
		//log.debug("initialization BaseServiceImpl");
	}
	@Resource(name = "mongoTemplate")
	private MongoOperations mongoOperations;

	@Override
	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	@Override
	public CouchbaseClient getCacheClient() {
		return CommonCache.getCacheClient();
	}

	@Override
	public INoSqlEntity saveToNoSql(INoSqlEntity entity, String profile) {
		if (entity.getId() != null)
			throw new RuntimeException("ʵ�����idֵ����Ҫ���ø��·���������ȥ��idֵ");
		entity.setId(ObjectId.get());
		entity.setState(EntityState.NORMAL);
		return saveToNoSqlCore(entity, profile);
	}

	private INoSqlEntity saveToNoSqlCore(INoSqlEntity entity, String profile) {
		entity.setTimeStamp(new Date().getTime());
		this.mongoOperations.save(entity, profile);
		if (entity.getClass().isAnnotationPresent(CachingEntity.class)) {
			String bucketName = entity.getClass()
					.getAnnotation(CachingEntity.class).cacheName();
			Integer expiry = entity.getClass()
					.getAnnotation(CachingEntity.class).expiry().getValue();
			CommonCache.getCacheClient(bucketName).set(entity.getGlobeId(),
					expiry, entity);
		}
		return entity;
	}

	@Override
	public void deleteFromNoSql(INoSqlEntity entity, String profile) {
		if (entity.getId() == null)
			throw new RuntimeException("��ʵ���idΪnull");
		entity.setState(EntityState.DELETED);
		saveToNoSqlCore(entity, profile);
	}

	@Override
	public INoSqlEntity updateToNoSql(INoSqlEntity entity, String profile) {
		if (entity.getId() == null)
			throw new RuntimeException("��ʵ���idΪnull");
		INoSqlEntity history = loadEntityFromNoSqlById(entity.getClass(),
				profile, entity.getId(), null);
		if (history == null)
			return null;
		history.setId(ObjectId.get());
		history.setState(EntityState.DISABLE);
		entity.setHistoryId(history.getId());
		mongoOperations.save(history, profile);
		saveToNoSqlCore(entity, profile);
		return entity;
	}

	private INoSqlEntity loadEntityFromNoSqlById(Class<?> clazz,
			String profile, ObjectId id, EntityState state) {
		if (id == null)
			throw new RuntimeException("���idΪnull");
		INoSqlEntity entity = null;
		String bucketName = "";
		Integer expiry = -1;
		Boolean isAnnotationedCachingEntity = Boolean.FALSE;
		if (clazz.isAnnotationPresent(CachingEntity.class)) {
			bucketName = clazz.getAnnotation(CachingEntity.class).cacheName();
			expiry = clazz.getAnnotation(CachingEntity.class).expiry()
					.getValue();
			isAnnotationedCachingEntity = Boolean.TRUE;
			entity = (INoSqlEntity) CommonCache.getCacheClient(bucketName).get(
					GlobeKeyBuilder.keyBuilder(clazz, id));
		}
		if (entity == null) {
			Criteria criteria = Criteria.where(INoSqlEntity.COMMON_NAME_ID).is(
					id);
			criteria = state == null ? criteria : (criteria
					.and(INoSqlEntity.COMMON_NAME_ENTITY_STATE)
					.is(state.name()));
			entity = (INoSqlEntity) mongoOperations.findOne(
					new Query(criteria), clazz, profile);
			if (entity != null && isAnnotationedCachingEntity) {
				CommonCache.getCacheClient(bucketName).set(
						GlobeKeyBuilder.keyBuilder(clazz, id), expiry, entity);
			}
		}
		return entity;

	}

	@Override
	public INoSqlEntity loadEntityFromNoSqlById(Class<?> clazz, String profile,
			ObjectId id) {
		return loadEntityFromNoSqlById(clazz, profile, id, EntityState.NORMAL);
	}

	@Override
	public void deleteTreeEntityNode(AbstractNoSqlTreeEntity entityNode,
			String profile) {
		AbstractNoSqlTreeEntity parentEntity = (AbstractNoSqlTreeEntity) (entityNode
				.getParent() == null ? null : loadEntityFromNoSqlById(
				entityNode.getClass(), profile, entityNode.getParent()));
		if (parentEntity != null) {
			parentEntity.getChildren().remove(entityNode.getId());
		}
		this.updateToNoSql(parentEntity, profile);
	}

	@Override
	public List<INoSqlEntity> getEntityChildren(INoSqlEntity entity,
			String profile) {
		if (entity instanceof INoSqlTree) {
			List<? extends INoSqlEntity> children = this.getMongoOperations()
					.find(new Query(Criteria.where(IEntity.COMMON_NAME_ID)
							.in(((INoSqlTree) entity).getChildren())
							.and(IEntity.COMMON_NAME_ENTITY_STATE)
							.is(EntityState.NORMAL.name())), entity.getClass(),
							profile);
			Map<ObjectId, INoSqlEntity> childMap = new HashMap<ObjectId, INoSqlEntity>();
			for (INoSqlEntity child : children) {
				childMap.put(child.getId(), child);
			}
			List<INoSqlEntity> sorted = new ArrayList<INoSqlEntity>();
			for (ObjectId id : ((INoSqlTree) entity).getChildren()) {
				if (childMap.get(id) == null)
					continue;
				sorted.add(childMap.get(id));
			}
			return sorted;
		} else {
			//log.error("�����ʵ������ṹ");
			return null;
		}
	}

	@Override
	public <T extends INoSqlEntity> List<T> loadAllEntityWithoutCache(
			Class<T> clazz, String profile) {
		return this.mongoOperations.findAll(clazz, profile);
	}
	protected <T> Page<T> loadPage(int pageNo,int pageSize,String sqlCountRows,String sqlFetchRows,final Class<T> clazz){
		PaginationHelper<T> helper= new PaginationHelper<T> ();
		Page<T> page=helper.fetchPage(this.jdbcTemplate, sqlCountRows, sqlFetchRows, pageNo, pageSize, new ParameterizedRowMapper<T>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public T mapRow(ResultSet arg0, int arg1) throws SQLException {
				return (T) loadEntityFromRDBMSById(clazz, arg0.getLong("id"));
			}
		});
		return page;
	}
	protected boolean checkString(String object){
		if(object!=null&&!object.trim().equals("")){
			return true;
		}
	return false;
	}

}
