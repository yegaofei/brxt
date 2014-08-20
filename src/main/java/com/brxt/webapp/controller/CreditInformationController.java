package com.brxt.webapp.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.service.CreditInformationManager;


@Controller
@RequestMapping("/creditInformation*")
public class CreditInformationController extends BaseFormController{
	
	private CreditInformationManager creditInformationManager;

	@Autowired
	public void setCreditInformationManager(
			@Qualifier("creditInformationManager") CreditInformationManager creditInformationManager) {
		this.creditInformationManager = creditInformationManager;
	}
	
	
	public CreditInformationController() {
		setCancelView("redirect:/projectInfo");
        setSuccessView("redirect:/creditInformation");
	}
	
	@ModelAttribute("creditInformationList")
	public List<CreditInformation> getCreditInformationList(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		List<CreditInformation> ciList = null;
		if(!StringUtils.isBlank(projectInfoId))
		{
			ProjectInfo project = null;
			Set<Counterparty> cp = null;
			Set<Counterparty> ga = null;
			
			ciList = creditInformationManager.findByProjId(Long.valueOf(projectInfoId));
			
			if(ciList != null && ciList.size() > 0) {
			    CreditInformation ci = ciList.get(0); 
			    project = ci.getProjectInfo();
                cp = project.getCounterparties();
                ga = project.getGuarantors();
			} else {
			    return ciList;
			}
			
			Iterator<CreditInformation> itCp = ciList.iterator();
			while(itCp.hasNext()) {
			    CreditInformation ci = itCp.next();
			    if (cp != null && !cp.isEmpty() && cp.contains(ci.getCounterparty()))
                {
                    ci.setTradeRelationship(TradingRelationship.COUNTERPARTY);
                } 
			    else if (ga != null && !ga.isEmpty() && ga.contains(ci.getCounterparty()))
                {
                    ci.setTradeRelationship(TradingRelationship.GUARANTOR);
                }  
			    else 
			    {
			        itCp.remove();
			    }
			}
		}
		return ciList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request) throws Exception {
		return new ModelAndView();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String method = request.getParameter("method");
		String creditInformationId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if( StringUtils.isBlank(method) || StringUtils.isBlank(creditInformationId))
		{
			saveError(request, getText("errors.creditInformation.required", locale));
		}
		else
		{
			switch (method) {
			case "Edit":
				mav.setViewName("redirect:/creditInforForm?id=" + creditInformationId);
				break;
			case "Delete":				
				creditInformationManager.remove(Long.valueOf(creditInformationId));
				saveMessage(request, getText("creditInformation.delete.successful", locale));
				mav.setViewName(getSuccessView());
				break;
				default:
			}
		}
		return mav;
	}
	
}
