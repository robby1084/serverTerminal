package org.iti.framework.hebut.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.interfaces.IEntity;

@Entity
@Table(name="CityDetail")
public class CityDetail implements IEntity{

	/**
	 * 城市详细信息
	 */
	private static final long serialVersionUID = -3525126421938832567L;

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
	
	/**
	 * 行政区划代码
	 */
	@Column
	private String divisionCode;
	
	/**
	 * 是否为经济特区
	 */
	@Column
	private Boolean isSpecial;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="city_fk")
	private City city;
	
	@Override
	public String getGlobeId() {
		return GlobeKeyBuilder.keyBuilder(getClass(), id);
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

	@Override
	public Long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public Boolean getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
