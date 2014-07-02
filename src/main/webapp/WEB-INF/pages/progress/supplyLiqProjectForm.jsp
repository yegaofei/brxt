<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="supplyLiquidProject.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='supplyLiquidProject.title'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="supplyLiquidProject" method="post" action="/progress/supplyLiqProjectForm" 
    	id="supplyLiqProjectForm" cssClass="well"  onsubmit="return validateSupplyLiquidProject(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="investmentStatus.id"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	<input type="hidden" name="investmentStatusId" value="${param.investmentStatusId}"/>
	
    <div class="form-group">
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.name"/>
        <form:input path="investmentStatus.projectName" id="projectName" maxlength="20" cssClass="form-control" readonly="true"/>
    </div>

    <spring:bind path="supplyLiquidProject.projectEndTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.projectEndTime"/>
        <form:input path="projectEndTime" id="projectEndTime" maxlength="60" cssClass="form-control"/>
        <form:errors path="projectEndTime" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.industryVista">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.industryVista"/>
        <form:input path="industryVista" id="industryVista" maxlength="60" cssClass="form-control"/>
        <form:errors path="industryVista" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.firmSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.firmSize"/>
        <form:input path="firmSize" id="firmSize" maxlength="60" cssClass="form-control"/>
        <form:errors path="firmSize" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.description"/>
        <form:input path="description" id="description" maxlength="60" cssClass="form-control"/>
        <form:errors path="description" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.saleSituation">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.saleSituation"/>
        <form:input path="saleSituation" id="saleSituation" maxlength="60" cssClass="form-control"/>
        <form:errors path="saleSituation" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.bigChanges">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.bigChanges"/>
        <label class="checkbox-inline">
        	<form:radiobutton path="bigChanges" value="true" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="bigChanges" value="false" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <spring:bind path="supplyLiquidProject.investmentProgress">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.investmentProgress"/>
        <form:input path="investmentProgress" id="investmentProgress" maxlength="60" cssClass="form-control"/>
        <form:errors path="investmentProgress" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.comments">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.comments"/>
        <form:input path="comments" id="comments" maxlength="60" cssClass="form-control"/>
        <form:errors path="comments" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="supplyLiquidProject.evaluation">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="supplyLiquidProject.evaluation"/>
        <form:input path="evaluation" id="evaluation" maxlength="60" cssClass="form-control"/>
        <form:errors path="evaluation" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty supplyLiquidProject.createUser}">
        <form:hidden path="createUser" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty supplyLiquidProject.updateUser}">
        <form:hidden path="updateUser" />     
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

<v:javascript formName="supplyLiquidProject" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#projectEndTime').datepicker({
				language: 'zh-CN'				
			});
  });
</script>
