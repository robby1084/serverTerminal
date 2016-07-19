package org.iti.framework.hebut.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.iti.common.util.GlobeKeyBuilder;
import org.iti.entity.interfaces.IEntity;

@Entity
@Table(name="City")
public class City implements IEntity{

	/**
	 * 城市
	 */
	private static final long serialVersionUID = -4186900983470870213L;
	
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
	private String cityName;
	
	@OneToOne(mappedBy="city",cascade=CascadeType.ALL)
	private CityDetail cityDetail;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="province_fk")
	private Province province;
	
	@Override
	public String getGlobeId() {
		return GlobeKeyBuilder.keyBuilder(getClass(), id);
	}

	@Override
	public Long getTimeStamp() {
		return timeStamp;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public CityDetail getCityDetail() {
		return cityDetail;
	}

	public void setCityDetail(CityDetail cityDetail) {
		this.cityDetail = cityDetail;
	}

}
