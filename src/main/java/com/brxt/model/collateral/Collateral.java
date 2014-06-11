package com.brxt.model.collateral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "collateral_detail")
public class Collateral extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1325559738604056944L;
	protected Long id;
	protected CollateralOverview collateralOverview;
	protected String certificateNo; //权证号
	protected String certificate; //权证内容
	protected String bookedFundUsage; //权证登记的资金运用方式
	protected String contractFundUsage; //运用合同签署的资金运用方式
	protected String privilegesOrder; //抵押权顺位
	protected String archived;  //是否移交档案管理部门
	protected String executor; //本次执业机构
	protected Date evaluatedTime; //评估时间
	protected String evaluatedMethod1;
	protected String value1;
	protected String evaluatedMethod2;
	protected String value2;
	protected String owner;
	protected String location; //所处位置
	protected String district; //行政区划级别
	protected String evaluatedValue; //抵押物评估价值
	protected String rate;	//抵押率
	
	protected CollateralType collateralType; //抵押物类型	
	private List<Land> landList = new ArrayList<Land>();
	private List<Property> propertyList = new ArrayList<Property>();
	private List<ConstructingProject> constructingProjectList = new ArrayList<ConstructingProject>();
	
	protected String createUser; // 创建人
	protected Date createTime; // 创建时间
	protected String updateUser; // 最后更新人
	protected Date updateTime; // 最后更新时间
	protected Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="collateralOverview_id")
	public CollateralOverview getCollateralOverview() {
		return collateralOverview;
	}
	public void setCollateralOverview(CollateralOverview collateralOverview) {
		this.collateralOverview = collateralOverview;
	}
	
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getBookedFundUsage() {
		return bookedFundUsage;
	}
	public void setBookedFundUsage(String bookedFundUsage) {
		this.bookedFundUsage = bookedFundUsage;
	}
	public String getContractFundUsage() {
		return contractFundUsage;
	}
	public void setContractFundUsage(String contractFundUsage) {
		this.contractFundUsage = contractFundUsage;
	}
	public String getPrivilegesOrder() {
		return privilegesOrder;
	}
	public void setPrivilegesOrder(String privilegesOrder) {
		this.privilegesOrder = privilegesOrder;
	}
	public String getArchived() {
		return archived;
	}
	public void setArchived(String archived) {
		this.archived = archived;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public Date getEvaluatedTime() {
		return evaluatedTime;
	}
	public void setEvaluatedTime(Date evaluatedTime) {
		this.evaluatedTime = evaluatedTime;
	}
	public String getEvaluatedMethod1() {
		return evaluatedMethod1;
	}
	public void setEvaluatedMethod1(String evaluatedMethod1) {
		this.evaluatedMethod1 = evaluatedMethod1;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "collateral", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT) 
	public List<Property> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "collateral", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT) 
	public List<ConstructingProject> getConstructingProjectList() {
		return constructingProjectList;
	}
	public void setConstructingProjectList(
			List<ConstructingProject> constructingProjectList) {
		this.constructingProjectList = constructingProjectList;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getEvaluatedMethod2() {
		return evaluatedMethod2;
	}
	public void setEvaluatedMethod2(String evaluatedMethod2) {
		this.evaluatedMethod2 = evaluatedMethod2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getEvaluatedValue() {
		return evaluatedValue;
	}
	public void setEvaluatedValue(String evaluatedValue) {
		this.evaluatedValue = evaluatedValue;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Transient
	public CollateralType getCollateralType() {
		return collateralType;
	}
	public void setCollateralType(CollateralType collateralType) {
		this.collateralType = collateralType;
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "collateral", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT) 
	public List<Land> getLandList() {
		return landList;
	}
	public void setLandList(List<Land> landList) {
		this.landList = landList;
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
				.append(this.certificateNo).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Collateral)) {
			return false;
		}

		final Collateral collateral = (Collateral) o;

		return !(certificateNo != null ? !certificateNo
				.equals(collateral.certificateNo)
				: collateral.certificateNo != null)
				&& !(evaluatedTime != null ? !evaluatedTime
						.equals(collateral.evaluatedTime)
						: collateral.evaluatedTime != null)
				&& !(collateralOverview != null ? !collateralOverview
						.equals(collateral.collateralOverview)
						: collateral.collateralOverview != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (collateralOverview != null ? collateralOverview.hashCode() : 0);
        result = 29 * result + (certificateNo != null ? certificateNo.hashCode() : 0);
        result = 29 * result + (evaluatedTime != null ? evaluatedTime.hashCode() : 0);
        return result;
	}

}
