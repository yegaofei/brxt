<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoDetail.title"/></title>
    <meta name="menu" content="ProjectInfoMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='projectInfoDetail.heading'/></h2>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectInfo" method="post" action="projectInfoForm" id="projectInfoForm" cssClass="well">
    <form:hidden path="id"/>

    <spring:bind path="projectInfo.projectName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" autofocus="true" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="projectInfo.expectedReturn">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.expectedReturn"/>
        <form:input path="expectedReturn" id="expectedReturn" maxlength="20" cssClass="form-control"/>
        <form:errors path="expectedReturn" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.riskManager">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.riskManager"/>
        <form:input path="riskManager" id="riskManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="riskManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.delegateManager">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.delegateManager"/>
        <form:input path="delegateManager" id="delegateManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="delegateManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.trustManager">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>
        <form:input path="trustManager" id="trustManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="trustManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.capitalInvestmentType">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.capitalInvestmentType"/>
        <form:input path="capitalInvestmentType" id="capitalInvestmentType" maxlength="20" cssClass="form-control"/>
        <form:errors path="capitalInvestmentType" cssClass="help-block"/>
    </div>
    
    <c:if test="${not empty projectInfo.createUser}">
    <spring:bind path="projectInfo.createUser">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.createUser.username"/>
        <c:out value="${projectInfo.createUser.username}" />
        <form:hidden path="createUser.username" id="createUser" maxlength="20" cssClass="form-control"/>
    </div>
    </c:if>
    <c:if test="${not empty projectInfo.projectSizes}">
    <div class="readonly">
		<display:table name="projectInfo.projectSizes" class="table table-condensed table-striped table-hover">
			<display:column property="startTime" escapeXml="true" sortable="true" titleKey="projectSize.startTime" style="width: 30%"/>
			<display:column property="projectSize" escapeXml="true" sortable="true" titleKey="projectSize.size" style="width: 34%"/>
			<display:column property="endTime" sortable="true" titleKey="projectSize.endTime" style="width: 30%" />
		</display:table>
     </div>
    </c:if>

    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty projectInfo.id}">
          <button type="submit" class="btn btn-default" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

 

 
<v:javascript formName="projectInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
