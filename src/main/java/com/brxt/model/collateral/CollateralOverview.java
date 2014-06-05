package com.brxt.model.collateral;

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
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.appfuse.model.User;

import com.brxt.model.ProjectInfo;


@Entity
@Table(name = "collateral_overview")
public class CollateralOverview extends BaseObject {

	private Long id;
	private ProjectInfo projectInfo; // 项目id
	private String description;
	private List<Collateral> collaterals;
	
	private User createUser; // 创建人
	private Date createTime; // 创建时间
	private User updateUser; // 最后更新人
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
	@JoinColumn(name="projectInfo_id") 
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Collateral> getCollaterals() {
		return collaterals;
	}
	public void setCollaterals(List<Collateral> collaterals) {
		this.collaterals = collaterals;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createUser", nullable = true)
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updateUser", nullable = true)
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
		.append(this.description).toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CollateralOverview)) {
			return false;
		}

		final CollateralOverview collateralOverview = (CollateralOverview) o;

		return !(description != null ? !description
				.equals(collateralOverview.description)
				: collateralOverview.description != null)
				&& !(projectInfo != null ? !projectInfo
						.equals(collateralOverview.projectInfo)
						: collateralOverview.projectInfo != null);
	}
	
	@Override
	public int hashCode() {
		int result;
        result = (description != null ? description.hashCode() : 0);
        result = 29 * result + (projectInfo != null ? projectInfo.hashCode() : 0);
        return result;
	}
}
