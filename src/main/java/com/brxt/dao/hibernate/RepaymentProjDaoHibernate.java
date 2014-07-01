package com.brxt.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.RepaymentProjectDao;
import com.brxt.model.projectprogress.RepaymentProject;

@Repository("repaymentProjectDao")
public class RepaymentProjDaoHibernate extends
GenericDaoHibernate<RepaymentProject, Long> implements RepaymentProjectDao{

	public RepaymentProjDaoHibernate()
	{
		super(RepaymentProject.class);
	}
	
	@Override
	public List<RepaymentProject> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchRPByProjectInfoId", queryParams);
	}
	
	@Override
	public List<RepaymentProject> findUniqueProjects(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		List<RepaymentProject> list = findByNamedQuery("searchRPByProjectInfoId", queryParams);
		List<RepaymentProject> destList = null;
		if (list != null) {
			destList = new ArrayList<RepaymentProject>();
			for (RepaymentProject investmentProject : list) {				
				boolean duplicate = false;
				for(RepaymentProject ip : destList)
				{
					if(ip.getName().equals(investmentProject.getName()))
					{
						duplicate = true;
						break;
					}
				}
				
				if(duplicate)
				{
					continue;
				}
				
				destList.add(investmentProject);
			}
		}
		return destList;
	}

}