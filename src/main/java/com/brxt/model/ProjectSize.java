package com.brxt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "findProjectSizeByProjectInfoId",
	query = "select * from project_size s where s.projectInfo_id = :projectInfo_id",
        resultClass = ProjectSize.class
	)
})
@Entity
@Table(name = "project_size")
public class ProjectSize {

	private Date startTime;
	private Date endTime;
	private BigDecimal projectSize;
	private Long id;
	private ProjectInfo projectInfo;

	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="size", length=30)
	public BigDecimal getProjectSize() {
		return projectSize;
	}

	public void setProjectSize(BigDecimal pojectSize) {
		this.projectSize = pojectSize;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

}
