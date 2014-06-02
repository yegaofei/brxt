<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="subjectCapacity.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='subjectCapacity.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="subjectCapacity" method="post" action="subjectCapacityForm" id="subjectCapacityForm" cssClass="well">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	 
	<div class="row">
		<div class="col-sm-3 form-group">
		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
		<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
			<c:if test="${status.first}"><select id="counterpartyId" name="counterpartyId"></c:if>
				<option value="${counterparty.id}"><c:out value="${counterparty.name}" /></option>
			<c:if test="${status.last}"></select></c:if>
		</c:forEach>
		</div>
	</div>
	
	<div class="row">
    <spring:bind path="subjectCapacity.licenseVerificationDate">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="subjectCapacity.licenseVerificationDate"/>
        <form:input path="licenseVerificationDate" id="licenseVerificationDate" maxlength="20" cssClass="form-control"/>
        <form:errors path="licenseVerificationDate" cssClass="help-inline"/>
    </div>

    <spring:bind path="subjectCapacity.orgCodeVerificationDate">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="subjectCapacity.orgCodeVerificationDate"/>
        <form:input path="orgCodeVerificationDate" id="orgCodeVerificationDate" maxlength="20" cssClass="form-control"/>
        <form:errors path="orgCodeVerificationDate" cssClass="help-block"/>
    </div>
    
    <spring:bind path="subjectCapacity.checkTime">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="subjectCapacity.checkTime"/>
        <form:input path="checkTime" id="checkTime" maxlength="20" cssClass="form-control"/>
        <form:errors path="checkTime" cssClass="help-block"/>
    </div>
	</div>
        
    <div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.loanCardValid"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="loanCardValid" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="loanCardValid" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.nameChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="nameChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="nameChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    </div>
    
    <div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.ownerChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="ownerChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="ownerChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.ownershipChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="ownershipChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="ownershipChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    </div>
    
    <div class="row">
    <div class="col-sm-8 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.capitalChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="capitalChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="capitalChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    </div>
    
    <div class="row">
    <div class="col-sm-5 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.devCapacityChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="devCapacityChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="devCapacityChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    </div>
    
    <div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.bizScopeChanged"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="bizScopeChanged" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="bizScopeChanged" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="subjectCapacity.otherBigChanges"/>:
        <label class="checkbox-inline">
        	<form:radiobutton path="otherBigChanges" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="otherBigChanges" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="subjectCapacity.verifyResults">
    <div class="col-sm-5 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="subjectCapacity.verifyResults"/>
        <form:input path="verifyResults" id="verifyResults" maxlength="60" cssClass="form-control"/>
        <form:errors path="verifyResults" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="subjectCapacity.comments">
    <div class="col-sm-5 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="subjectCapacity.comments"/>
        <form:input path="comments" id="comments" maxlength="60" cssClass="form-control"/>
        <form:errors path="comments" cssClass="help-inline"/>
    </div>
    </div>
    
    <c:if test="${not empty subjectCapacity.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty subjectCapacity.updateUser}">
        <form:hidden path="updateUser.username" />     
        <form:hidden path="updateTime" />     
    </c:if>
    
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveSubjectCapacity" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>       
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

<v:javascript formName="subjectCapacity" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
