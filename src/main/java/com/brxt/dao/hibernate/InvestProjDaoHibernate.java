package com.brxt.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;

@Repository("investmentProjectDao")
public class InvestProjDaoHibernate extends GenericDaoHibernate<InvestmentProject, Long> implements InvestmentProjectDao {

	public InvestProjDaoHibernate() {
		super(InvestmentProject.class);
	}

	@Override
	public List<InvestmentProject> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		List<InvestmentProject> list = findByNamedQuery("searchIPByProjectInfoId", queryParams);
		if (list != null) {
			for (InvestmentProject investmentProject : list) {
				CapitalInvestmentType ct = CapitalInvestmentType.valueOf(investmentProject.getInvestmentProjectType().toUpperCase());
				investmentProject.setType(ct);
			}
		}
		return list;
	}

	@Override
	public List<InvestmentProject> findUniqueProjects(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		List<InvestmentProject> list = findByNamedQuery("searchIPByProjectInfoId", queryParams);
		List<InvestmentProject> destList = null;
		if (list != null) {
			destList = new ArrayList<InvestmentProject>();
			for (InvestmentProject investmentProject : list) {				
				boolean duplicate = false;
				for(InvestmentProject ip : destList)
				{
					if(ip.getName().equals(investmentProject.getName()) && ip.getInvestmentProjectType().equals(investmentProject.getInvestmentProjectType()))
					{
						duplicate = true;
						break;
					}
				}
				
				if(duplicate)
				{
					continue;
				}
				
				CapitalInvestmentType ct = CapitalInvestmentType.valueOf(investmentProject.getInvestmentProjectType().toUpperCase());
				investmentProject.setType(ct);
				destList.add(investmentProject);
			}
		}
		return destList;
	}

}
