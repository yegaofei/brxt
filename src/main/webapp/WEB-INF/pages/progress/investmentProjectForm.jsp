<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectProgress.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='projectProgress.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectProgress" method="post" action="projectProgressForm" id="projectProgressForm" cssClass="well">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	
    <spring:bind path="projectProgress.projectProgressTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectProgress.projectProgressTime"/>
        <form:input path="projectProgressTime" id="queryTime" maxlength="20" cssClass="form-control"/>
        <form:errors path="projectProgressTime" cssClass="help-block"/>
    </div>

    <spring:bind path="projectProgress.amount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectProgress.amount"/>
        <form:input path="amount" id="amount" maxlength="60" cssClass="form-control"/>
        <form:errors path="amount" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="projectProgress.type">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectProgress.type"/>
        <label class="checkbox-inline">
        	<form:radiobutton path="type" value="principle" /><fmt:message key='principle'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="type" value="interest" /><fmt:message key='interest'/>
		</label>
    </div>
    
    <spring:bind path="projectProgress.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectProgress.comment"/>
        <form:input path="comment" id="comment" maxlength="60" cssClass="form-control"/>
        <form:errors path="comment" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty projectProgress.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty projectProgress.updateUser}">
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

<v:javascript formName="projectProgress" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
