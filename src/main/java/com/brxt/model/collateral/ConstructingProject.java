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
@Table(name = "constructing_project")
public class ConstructingProject extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6614585262038115264L;
	private Long id;
	private Collateral collateral;
	private String type; //房产类型
	private Double projectSize;
	private Integer floor;
	private String rightsType;
	private String yearsLimit;
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
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="collateral_id")
	public Collateral getCollateral() {
		return collateral;
	}
	public void setCollateral(Collateral collateral) {
		this.collateral = collateral;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getProjectSize() {
		return projectSize;
	}
	public void setProjectSize(Double projectSize) {
		this.projectSize = projectSize;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getRightsType() {
		return rightsType;
	}
	public void setRightsType(String rightsType) {
		this.rightsType = rightsType;
	}
	public String getYearsLimit() {
		return yearsLimit;
	}
	public void setYearsLimit(String yearsLimit) {
		this.yearsLimit = yearsLimit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
		.append(this.type).toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ConstructingProject)) {
			return false;
		}

		final ConstructingProject constructingProject = (ConstructingProject) o;

		return !(collateral != null ? !collateral
				.equals(constructingProject.collateral)
				: constructingProject.collateral != null)
				&& !(type != null ? !type
						.equals(constructingProject.type)
						: constructingProject.type != null)
				&& !(projectSize != null ? !projectSize
						.equals(constructingProject.projectSize)
						: constructingProject.projectSize != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (collateral != null ? collateral.hashCode() : 0);
        result = 29 * result + (type != null ? type.hashCode() : 0);
        result = 29 * result + (projectSize != null ? projectSize.hashCode() : 0);
        return result;
	}

}
