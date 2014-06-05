package com.brxt.model;

import java.util.Date;

import org.appfuse.model.BaseObject;
import org.appfuse.model.User;

import com.brxt.model.enums.StatementType;

public abstract class Statement extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3626006551144835062L;
	
	protected Long projectId;
	protected Long counterpartyId;	
	protected Long id; 
	protected String reportName;
	protected StatementType statementType;
	protected String reportYear;
	protected String reportMonth;
	protected User createUser; //创建人
	protected Date createTime; //创建时间
	protected User updateUser; //最后更新人
	protected Date updateTime; //最后更新时间	
	
	protected Long getProjectId() {
		return projectId;
	}
	protected void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	protected Long getCounterpartyId() {
		return counterpartyId;
	}
	protected void setCounterpartyId(Long counterpartyId) {
		this.counterpartyId = counterpartyId;
	}
	protected Long getId() {
		return id;
	}
	protected void setId(Long id) {
		this.id = id;
	}
	protected String getReportName() {
		return reportName;
	}
	protected void setReportName(String reportName) {
		this.reportName = reportName;
	}
	protected StatementType getStatementType() {
		return statementType;
	}
	protected void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}
	protected String getReportYear() {
		return reportYear;
	}
	protected void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	protected String getReportMonth() {
		return reportMonth;
	}
	protected void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}	
	protected User getCreateUser() {
		return createUser;
	}
	protected void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	protected Date getCreateTime() {
		return createTime;
	}
	protected void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	protected User getUpdateUser() {
		return updateUser;
	}
	protected void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	protected Date getUpdateTime() {
		return updateTime;
	}
	protected void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((reportName == null) ? 0 : reportName.hashCode());
		result = prime * result
				+ ((reportYear == null) ? 0 : reportYear.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statement other = (Statement) obj;
		if (reportName == null) {
			if (other.reportName != null)
				return false;
		} else if (!reportName.equals(other.reportName))
			return false;
		if (reportYear == null) {
			if (other.reportYear != null)
				return false;
		} else if (!reportYear.equals(other.reportYear))
			return false;
		return true;
	}	
	
}
