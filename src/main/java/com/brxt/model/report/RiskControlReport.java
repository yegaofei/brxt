package com.brxt.model.report;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;

import com.brxt.model.ProjectInfo;

@Entity
@Table(name = "risk_control_report")
public class RiskControlReport extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6533912247249058510L;
	private Long id;
	private ProjectInfo projectInfo;
	private ReportStatus reportStatus;
	private Date timeRangeStart;  
	private Date timeRangeEnd;  
	
	
	
	
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	private Integer version;
	
	
	
	
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
