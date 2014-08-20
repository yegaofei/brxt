package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.service.CreditInformationManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/creditInforForm*")
public class CreditInforFormController extends BaseFormController {

	private CreditInformationManager creditInformationManager;
	private ProjectInfoManager projectInfoManager;

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setCreditInformationManager(
			@Qualifier("creditInformationManager") CreditInformationManager creditInformationManager) {
		this.creditInformationManager = creditInformationManager;
	}

	@ModelAttribute("counterparties")
	public List<Counterparty> getCounterparties(final HttpServletRequest request) {
		Long projectInfoId = getProjectInfoId(request);
		if(projectInfoId != null)
		{
			ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
			Set<Counterparty> counterparties = projectInfo.getCounterparties();
			
			List<Counterparty> cpList = null;
			if (counterparties != null && counterparties.size() > 0) {
			    Iterator<Counterparty> itCp = counterparties.iterator();
                while(itCp.hasNext())
                {
                    Counterparty cp = itCp.next();
                    if(cp.getCounterpartyType().equals(CounterpartyType.INSTITUTION.getTitle()))
                    {
                        itCp.remove();
                    }
                }
				cpList = new ArrayList<Counterparty>(counterparties);
			}
			
			Set<Counterparty> guarantors = projectInfo.getGuarantors();
			if (guarantors != null && guarantors.size() > 0)
			{
			    Iterator<Counterparty> itGuarantor = guarantors.iterator();
                while(itGuarantor.hasNext())
                {
                    Counterparty cp = itGuarantor.next();
                    if(cp.getCounterpartyType().equals(CounterpartyType.INSTITUTION.getTitle()))
                    {
                        itGuarantor.remove();
                    }
                }
				if(cpList != null)
				{
					cpList.addAll(guarantors);
				}
				else
				{
					cpList = new ArrayList<Counterparty>(guarantors);
				}
			}
			return cpList;
		}
		else
		{
			return null;
		}
	}

	@ModelAttribute("creditInformation")
	public CreditInformation getCreditInformation(
			final HttpServletRequest request) {
		String creditInformationId = request.getParameter("id");
		if (!StringUtils.isBlank(creditInformationId)) {
			// Edit
			CreditInformation creditInformation = creditInformationManager
					.get(Long.valueOf(creditInformationId));
			return creditInformation;
		}
		// Add
		return new CreditInformation();
	}
	
	@ModelAttribute("projectInfoId")
	protected Long getProjectInfoId(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} 
		else
		{
			return Long.valueOf(projectInfoId);			
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("creditInformation") final CreditInformation creditInformation,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			@ModelAttribute("counterpartyId") final Long counterpartyId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		String returnView = "redirect:/creditInformation";
		final Locale locale = request.getLocale();
		
		if (!StringUtils.isBlank(method)) {
			if (validator != null) { // validator is null during testing
				validator.validate(creditInformation, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}
			
			switch (method) {
			case "SaveCreditInfor":
				User currentUser = getCurrentUser();
				Long creditInformationId = creditInformation.getId();
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				creditInformation.setProjectInfo(projectInfo);
				//Lookup the underlying counterparty
				creditInformation.setCounterparty(lookupCounterparty(projectInfo, counterpartyId));
				
				if (creditInformationId == null) {
					// Save new subject capacity
					creditInformation.setCreateTime(new Date());
					creditInformation.setUpdateTime(new Date());
					creditInformation.setCreateUser(currentUser);
					creditInformation.setUpdateUser(currentUser);
				} else {
					// Update Existed
					User createUser = getUserManager().getUserByUsername(projectInfo.getCreateUser());
					creditInformation.setCreateUser(createUser);
					creditInformation.setUpdateTime(new Date());
					creditInformation.setUpdateUser(currentUser);
				}
				creditInformationManager.save(creditInformation);
				if(!projectInfo.getProjectInfoStatus().getCreditInformation())
				{
					projectInfo.getProjectInfoStatus().setCreditInformation(true);
					projectInfoManager.save(projectInfo);
				}
				
				saveMessage(request,
						getText("creditInformation.save.successful", locale));
				break;
			case "Cancel":
				break;
			default:
			}
		}else {
			saveError(request, getText("errors.token", locale));
			returnView = "";
		}
		
		return returnView;
	}
	
	private Counterparty lookupCounterparty(final ProjectInfo projectInfo, final Long counterpartyId)
	{
		Set<Counterparty> cp = projectInfo.getCounterparties();
		if(cp != null)
		{
			Iterator<Counterparty> cpIt = cp.iterator();
			while(cpIt.hasNext())
			{
				Counterparty counterparty = cpIt.next();
				if(counterparty.getId().equals(counterpartyId))
				{
					return counterparty;
				}
			}
		}
		
		Set<Counterparty> ga = projectInfo.getGuarantors();
		if(ga != null)
		{
			Iterator<Counterparty> cpIt = ga.iterator();
			while(cpIt.hasNext())
			{
				Counterparty counterparty = cpIt.next();
				if(counterparty.getId().equals(counterpartyId))
				{
					return counterparty;
				}
			}
		}
		
		return null;
	}
}
