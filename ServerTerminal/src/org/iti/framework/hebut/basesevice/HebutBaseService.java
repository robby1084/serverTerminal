package org.iti.framework.hebut.basesevice;

import java.util.List;

import org.bson.types.ObjectId;
import org.iti.entity.db.nosql.abstracts.AbstractNoSqlTreeEntity;
import org.iti.entity.db.nosql.interfaces.INoSqlEntity;
import org.iti.entity.model.nosql.interfaces.INoSqlTree;
import org.iti.rdbms.base.BaseService;
import org.springframework.data.mongodb.core.MongoOperations;

import com.couchbase.client.CouchbaseClient;



public interface HebutBaseService extends BaseService{

	/**
	 * ��ȡMongoOperations
	 * 
	 * @return
	 */
	public MongoOperations getMongoOperations();

	/**
	 * ��ȡCouchbaseClient
	 * 
	 * @return
	 */
	public CouchbaseClient getCacheClient();

	/**
	 * ������ݵ�NoSql(MongoDB)
	 * 
	 * @param entity
	 *            ��ʵ�壬��ʵ����δ���浽��ݿ��У�Ҫ��idΪnull
	 * @param profile
	 */
	public INoSqlEntity saveToNoSql(INoSqlEntity entity, String profile);

	/**
	 * ��NoSql(MongoDB)��ɾ�����
	 * 
	 * @param entity
	 * @param profile
	 */
	public void deleteFromNoSql(INoSqlEntity entity, String profile);

	/**
	 * �������
	 * 
	 * @param entity
	 *            ��ʵ��
	 * @param profile
	 *            ʵ�������ļ�(����)
	 * @return
	 */
	public INoSqlEntity updateToNoSql(INoSqlEntity entity, String profile);

	/**
	 * ���id��ȡ���
	 * 
	 * @param clazz
	 *            ʵ������
	 * @param profile
	 *            ʵ�������ļ�(����)
	 * @param id
	 *            ���id
	 * @return
	 */
	public INoSqlEntity loadEntityFromNoSqlById(Class<?> clazz, String profile,
			ObjectId id);

	/**
	 * ɾ�����ڵ�ʵ��
	 * 
	 * @param entityNode
	 *            ��ڵ�ʵ��
	 * @param profile
	 *            ʵ�������ļ�(����)
	 */
	public void deleteTreeEntityNode(AbstractNoSqlTreeEntity entityNode,
			String profile);
	/**
	 * �ǻ��淽��
	 * ��ȡ��ʵ����ӽڵ�
	 * 
	 * @param entity
	 *            ��ʵ�壬��ʵ�����̳�{@link INoSqlTree}�ӿ�
	 * @param profile
	 *            ʵ���ж����PROFILE��
	 * @return ����{@link INoSqlEntity}ʵ���б?��
	 */
	public List<INoSqlEntity> getEntityChildren(INoSqlEntity entity,
			String profile);
	/**
	 * �ǻ��淽��
	 * ��ȡ���Collection(��)����Ч���
	 * @param clazz
	 * ָ����
	 * @param profile
	 * ʵ�������ļ�(����)
	 * @return
	 * �������е���Ч���
	 */
	public <T extends INoSqlEntity> List<T> loadAllEntityWithoutCache(
			Class<T> clazz, String profile);


}
