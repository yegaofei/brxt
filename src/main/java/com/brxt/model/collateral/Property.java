package com.brxt.model.collateral;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

@Entity
@Table(name = "collateral_property")
public class Property extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 976633855792536310L;
	private Long id;
	private Collateral collateral;
	private String yearsLimit;
	private String landUsageType;
	private Integer landUsageYears;
	private BigDecimal price;
	
	private String createUser; // 创建人
	private Date createTime; // 创建时间
	private String updateUser; // 最后更新人
	private Date updateTime; // 最后更新时间
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYearsLimit() {
		return yearsLimit;
	}
	public void setYearsLimit(String yearsLimit) {
		this.yearsLimit = yearsLimit;
	}
	public String getLandUsageType() {
		return landUsageType;
	}
	public void setLandUsageType(String landUsageType) {
		this.landUsageType = landUsageType;
	}
	public Integer getLandUsageYears() {
		return landUsageYears;
	}
	public void setLandUsageYears(Integer landUsageYears) {
		this.landUsageYears = landUsageYears;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="collateral_id")
	public Collateral getCollateral() {
		return collateral;
	}
	public void setCollateral(Collateral collateral) {
		this.collateral = collateral;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.yearsLimit).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Property)) {
			return false;
		}

		final Property land = (Property) o;

		return !(collateral != null ? !collateral.equals(land.collateral)
				: land.collateral != null)
				&& !(yearsLimit != null ? !yearsLimit.equals(land.yearsLimit)
						: land.yearsLimit != null)
				&& !(landUsageType != null ? !landUsageType.equals(land.landUsageType) : land.landUsageType != null);
	}

	@Override
	public int hashCode() {
		int result;
		result = (collateral != null ? collateral.hashCode() : 0);
		result = 29 * result + (yearsLimit != null ? yearsLimit.hashCode() : 0);
		result = 29 * result
				+ (landUsageType != null ? landUsageType.hashCode() : 0);
		return result;
	}

}
