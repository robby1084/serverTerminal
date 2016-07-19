package org.iti.framework.hebut.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.interfaces.IEntity;

@Entity
@Table(name="Province")
public class Province implements IEntity{

	/**
	 * 省份实体
	 */
	private static final long serialVersionUID = -4458922555096436943L;

	@Id
	@GeneratedValue
	private Long id; 
	/**
	 * 时间戳
	 */
	@Column
	private Long timeStamp;
	/**
	 * 实体状态
	 */
	@Column
	private Integer state;
	/**
     * 历史id
     */
	@Column
	private Long historyId;
	
	@Column
	private String provinceName;
	
	@Column
	private String provinceCode;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="province")
	private Set<City> cities;
	
	@Override
	public String getGlobeId() {
		return GlobeKeyBuilder.keyBuilder(getClass(), id);
	}

	@Override
	public Long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
