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
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.brxt.model.ProjectInfo;


@Entity
@Table(name = "collateral_overview")
public class CollateralOverview extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5687597785866292844L;
	private Long id;
	private ProjectInfo projectInfo; // 项目id
	private String description;
	private String evaluatedValue; //抵押物评估价值
	private String rate;	//抵押率
	private List<Collateral> collaterals = new ArrayList<Collateral>();
	
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "collateralOverview", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT) 
	public List<Collateral> getCollaterals() {
		return collaterals;
	}
	public void setCollaterals(List<Collateral> collaterals) {
		this.collaterals = collaterals;
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
