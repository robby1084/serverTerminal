package org.iti.framework.hebut.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.types.ObjectId;
import org.iti.application.util.ReadDataUtil;
import org.iti.common.util.PropertiesLoader;
import org.iti.entity.interfaces.IEntity.EntityState;
import org.iti.security.entity.AuthDepartment;
import org.iti.security.entity.AuthDepartment.DeptTypeEnum;
import org.iti.security.rmi.client.AuthCenterClient;

public class DepartmentUtil {
	public static Integer DEEP = 1;

	public static AuthDepartment loadParentDepartmentId(ObjectId deptId,
			Integer deep) {
		if (deep == null) {
			return null;
		}
		AuthDepartment dpt = ReadDataUtil.loadCachingEntityGeneralType(
				AuthDepartment.class, deptId);
		if (dpt != null) {
			if (deep.intValue() == dpt.getDeep().intValue()) {
				return dpt;
			} else if (deep.intValue() < dpt.getDeep().intValue()) {
				dpt = loadParentDepartmentId(dpt.getParent(), deep);
			} else {
				return dpt;
			}
		}
		return dpt;
	}

	/**
	 * ���Ժ����λ
	 * 
	 * @return
	 */
	public static List<AuthDepartment> loadAuthDepartment() {
		List<AuthDepartment> listEnd = new ArrayList<AuthDepartment>();
		try {
			List<AuthDepartment> list = getAuthCenterClient().getClient()
					.getTopDepartments();
			if (list != null && list.size() != 0) {
				for (AuthDepartment au : list) {
					if (au.getChildren() != null && au.getChildren().size() > 0) {
						for (ObjectId id : au.getChildren()) {
							AuthDepartment e = ReadDataUtil
									.loadCachingEntityGeneralType(
											AuthDepartment.class, id);
							if (e != null&&e.getState().equals(EntityState.NORMAL)) {
								listEnd.add(e);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(listEnd, new Comparator<AuthDepartment>(){

			@Override
			public int compare(AuthDepartment o1, AuthDepartment o2) {
				long l1=o1.getType().getValue();
				long l2=o2.getType().getValue();
				String code1= o1.getCode();
				String code2= o2.getCode();
				return l1==l2?(code1==code2?0:(code1.compareTo(code2))):(l1>l2?1:-1);
			}
			
		});


		return listEnd;
	}

	public static List<AuthDepartment> loadDepartmentByType(DeptTypeEnum type) {
		List<AuthDepartment> listEnd = new ArrayList<AuthDepartment>();
		List<AuthDepartment> list = loadAuthDepartment();
		if (type == null) {
			return list;
		}
		for (AuthDepartment e : list) {
			if (e.getType() != null && e.getType().equals(type)) {
				listEnd.add(e);
			}
		}
		return listEnd;
	}
    public static AuthDepartment loadSchoolLeaderOffice(){
    	AuthDepartment end=null;
    	String code= PropertiesLoader.getPropertiesValue("SchoolLeaderCode", "System");
		try {
			List<AuthDepartment> list = getAuthCenterClient().getClient()
					.getTopDepartments();
			if (list != null && list.size() != 0) {
				aux:for (AuthDepartment au : list) {
					if (au.getChildren() != null && au.getChildren().size() > 0) {
						for (ObjectId id : au.getChildren()) {
							AuthDepartment e = ReadDataUtil
									.loadCachingEntityGeneralType(
											AuthDepartment.class, id);
							if (e.getCode().equals(code)) {
								end=e;
								break aux;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return end;
    }
	private static AuthCenterClient getAuthCenterClient() {
		return (AuthCenterClient) org.iti.application.context.bean.factory.BeanFactory
				.getBean("authCenterClient");
	}
}
