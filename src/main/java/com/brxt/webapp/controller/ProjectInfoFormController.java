package com.brxt.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.model.ProjectInfo;
import com.brxt.service.ProjectInfoManager;
 

@Controller
@RequestMapping("/projectInfoForm*")
public class ProjectInfoFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	 
    @Autowired
    public void setProjectInfoManager(@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
        this.projectInfoManager = projectInfoManager;
    }
 
    public ProjectInfoFormController() {
        setCancelView("redirect:projectInfo");
        setSuccessView("redirect:projectInfo");
    }
 
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ProjectInfo showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
 
        if (!StringUtils.isBlank(id)) {
            return projectInfoManager.get(new Long(id));
        }
 
        return new ProjectInfo();
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(projectInfo, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "projectInfoForm";
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (projectInfo.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	projectInfoManager.remove(projectInfo.getId());
            saveMessage(request, getText("projectInfo.deleted", locale));
        } else {
        	projectInfoManager.save(projectInfo);
            String key = (isNew) ? "projectInfo.added" : "projectInfo.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:projectInfoForm?id=" + projectInfo.getId();
            }
        }
 
        return success;
    }
}
