package com.brxt.model.collateral;

import java.util.Date;

import org.appfuse.model.BaseObject;
import org.appfuse.model.User;

public class Collateral extends BaseObject{
	
	protected String certificateNo;
	protected String certificate;
	protected String bookedFundUsage;
	protected String contractFundUsage;
	protected String privilegesOrder;
	protected String archived;  //是否移交档案管理部门
	protected String executor; //本次执业机构
	protected Date evaluatedTime;
	protected String evaluatedMethod1;
	protected String value1;
	protected String evaluatedMethod2;
	protected String value2;
	protected String owner;
	protected String location;
	protected String district;
	protected String evaluatedValue;
	protected String rate;	//抵押率
	
	protected CollateralType collateralType;
	protected User createUser; // 创建人
	protected Date createTime; // 创建时间
	protected User updateUser; // 最后更新人
	protected Date updateTime; // 最后更新时间
	protected Integer version;
	
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
	public CollateralType getCollateralType() {
		return collateralType;
	}
	public void setCollateralType(CollateralType collateralType) {
		this.collateralType = collateralType;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
