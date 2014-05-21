package com.brxt.webapp.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
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
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = getCurrentUser(auth, getUserManager());
        
        if (request.getParameter("delete") != null) {
        	projectInfoManager.remove(projectInfo.getId());
            saveMessage(request, getText("projectInfo.deleted", locale));
        } else {
        	if(isNew)
        	{
        		projectInfo.setCreateUser(currentUser);
        		projectInfo.setCreateTime(new Date());
        	}
        	else
        	{
        		ProjectInfo pi = projectInfoManager.get(projectInfo.getId());
        		User createUser = pi.getCreateUser();
        		Date createTime = pi.getCreateTime();
        		
        		projectInfo.setCreateUser(createUser);
        		projectInfo.setCreateTime(createTime);
        		projectInfo.setUpdateUser(currentUser);
        		projectInfo.setUpdateTime(new Date());
        	}
        	projectInfoManager.save(projectInfo);
            String key = (isNew) ? "projectInfo.added" : "projectInfo.updated";
            saveMessage(request, getText(key, locale));
 
//            if (!isNew) {
//                success = "redirect:projectInfoForm?id=" + projectInfo.getId();
//            }
        }
 
        return success;
    }
    
    
    private User getCurrentUser(Authentication auth, UserManager userManager) {
        User currentUser;
        if (auth.getPrincipal() instanceof LdapUserDetails) {
            LdapUserDetails ldapDetails = (LdapUserDetails) auth.getPrincipal();
            String username = ldapDetails.getUsername();
            currentUser = userManager.getUserByUsername(username);
        } else if (auth.getPrincipal() instanceof UserDetails) {
            currentUser = (User) auth.getPrincipal();
        } else if (auth.getDetails() instanceof UserDetails) {
            currentUser = (User) auth.getDetails();
        } else {
            throw new AccessDeniedException("User not properly authenticated.");
        }
        return currentUser;
    }
}
