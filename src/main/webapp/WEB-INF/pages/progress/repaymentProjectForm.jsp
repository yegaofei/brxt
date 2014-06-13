<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="repaymentProject.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='repaymentProject.title'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="repaymentProject" method="post" action="/progress/repaymentProjectForm" 
    		id="repaymentProjectForm" cssClass="well" onsubmit="return validateRepaymentProject(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	
    <spring:bind path="repaymentProject.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.name"/>
        <form:input path="name" id="name" maxlength="20" cssClass="form-control"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>

    <spring:bind path="repaymentProject.projectEndTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.projectEndTime"/>
        <form:input path="projectEndTime" id="projectEndTime" maxlength="60" cssClass="form-control"/>
        <form:errors path="projectEndTime" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.totalSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.totalSize"/>
        <form:input path="totalSize" id="totalSize" maxlength="60" cssClass="form-control"/>
        <form:errors path="totalSize" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.totalSaleSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.totalSaleSize"/>
        <form:input path="totalSaleSize" id="totalSaleSize" maxlength="60" cssClass="form-control"/>
        <form:errors path="totalSaleSize" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.permitSaleSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.permitSaleSize"/>
        <form:input path="permitSaleSize" id="permitSaleSize" maxlength="60" cssClass="form-control"/>
        <form:errors path="permitSaleSize" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.openDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.openDate"/>
        <form:input path="openDate" id="openDate" maxlength="60" cssClass="form-control"/>
        <form:errors path="openDate" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.preSaleSize">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.preSaleSize"/>
        <form:input path="preSaleSize" id="preSaleSize" maxlength="60" cssClass="form-control"/>
        <form:errors path="preSaleSize" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.salePercentRate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.salePercentRate"/>
        <form:input path="salePercentRate" id="salePercentRate" maxlength="60" cssClass="form-control"/>
        <form:errors path="salePercentRate" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.preAvgPrice">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.preAvgPrice"/>
        <form:input path="preAvgPrice" id="preAvgPrice" maxlength="60" cssClass="form-control"/>
        <form:errors path="preAvgPrice" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.currAvgPrice">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.currAvgPrice"/>
        <form:input path="currAvgPrice" id="currAvgPrice" maxlength="60" cssClass="form-control"/>
        <form:errors path="currAvgPrice" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repaymentProject.comments">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repaymentProject.comments"/>
        <form:input path="comments" id="comments" maxlength="60" cssClass="form-control"/>
        <form:errors path="comments" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty repaymentProject.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty repaymentProject.updateUser}">
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

<v:javascript formName="repaymentProject" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#projectEndTime').datepicker({
				language: 'zh-CN'				
			});
  });
  
  $(function() {
    $('#openDate').datepicker({
				language: 'zh-CN'				
			});
  });
  
</script>
