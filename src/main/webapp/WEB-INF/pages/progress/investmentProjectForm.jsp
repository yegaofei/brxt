<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="investmentProject.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='investmentProject.title'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="investmentProject" method="post" action="/progress/investmentProjectForm" id="investmentProjectForm" cssClass="well"  onsubmit="return validateInvestmentProject(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="investmentProjectType"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	
	<div class="form-group">
        <appfuse:label styleClass="control-label" key="investmentProject.type"/> : 
        <fmt:message key="${investmentProject.investmentProjectType}" />
    </div>
	
    <spring:bind path="investmentProject.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.name"/>
        <form:input path="name" id="name" maxlength="20" cssClass="form-control"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>

    <spring:bind path="investmentProject.projectEndTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.projectEndTime"/>
        <form:input path="projectEndTime" id="projectEndTime" maxlength="60" cssClass="form-control"/>
        <form:errors path="projectEndTime" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.startTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.startTime"/>
        <form:input path="startTime" id="startTime" maxlength="60" cssClass="form-control"/>
        <form:errors path="startTime" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.endTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.endTime"/>
        <form:input path="endTime" id="endTime" maxlength="60" cssClass="form-control"/>
        <form:errors path="endTime" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.investmentAmount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.investmentAmount"/>
        <form:input path="investmentAmount" id="investmentAmount" maxlength="60" cssClass="form-control"/>
        <form:errors path="investmentAmount" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.totalAmout">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.totalAmout"/>
        <form:input path="totalAmout" id="totalAmout" maxlength="60" cssClass="form-control"/>
        <form:errors path="totalAmout" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.description"/>
        <form:input path="description" id="description" maxlength="60" cssClass="form-control"/>
        <form:errors path="description" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="investmentProject.delayed">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.delayed"/>
        <label class="checkbox-inline">
        	<form:radiobutton path="delayed" value="true" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="delayed" value="false" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <spring:bind path="investmentProject.comments">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="investmentProject.comments"/>
        <form:input path="comments" id="comments" maxlength="60" cssClass="form-control"/>
        <form:errors path="comments" cssClass="help-inline"/>
    </div>
    
    <c:if test="${empty investmentProject.id}">
    <div class="form-group">
    	<form:checkbox path="sameAsRepayment" id="sameAsRepayment"/>
        <fmt:message key="investmentProject.same.repayment"/>
    </div>
    </c:if>
    
    <c:if test="${not empty investmentProject.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty investmentProject.updateUser}">
        <form:hidden path="updateUser.username" />     
        <form:hidden path="updateTime" />     
    </c:if>
    
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveprojectProgress" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>       
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

<v:javascript formName="investmentProject" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#projectEndTime').datepicker({
				language: 'zh-CN'				
			});
  });
  
  $(function() {
    $('#startTime').datepicker({
				language: 'zh-CN'				
			});
  });
  
  $(function() {
    $('#endTime').datepicker({
				language: 'zh-CN'				
			});
  });
</script>

