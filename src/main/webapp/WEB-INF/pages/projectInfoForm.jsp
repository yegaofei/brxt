<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoDetail.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='projectInfoDetail.heading'/></h2>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectInfo" method="post" action="projectInfoForm" id="projectInfoForm" cssClass="well">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	 
	<div class="row">
    <spring:bind path="projectInfo.projectName">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="projectInfo.expectedReturn">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.expectedReturn"/>
        <form:input path="expectedReturn" id="expectedReturn" maxlength="20" cssClass="form-control"/>
        <form:errors path="expectedReturn" cssClass="help-block"/>
    </div>
	</div>

	<c:if test="${not empty projectInfo.projectSizes}">
	<appfuse:label styleClass="control-label" key="projectSize.title"/>
    <div id="actions" class="btn-group">
		<c:choose>
		<c:when test="${method == 'EditProjectSize'}">
    		<button type="submit" name="method" value="SaveProjectSize">
                <fmt:message key="button.save"/>
            </button>
		</c:when>
		<c:otherwise>
        <button type="submit" name="method" value="AddProjectSize">
                <fmt:message key="button.add"/>
        </button>    
        </c:otherwise>
        </c:choose>
        <button type="submit" name="method" value="EditProjectSize">
                <fmt:message key="button.edit"/>
        </button>
        <button type="submit" name="method" value="DeleteProjectSize">
                <fmt:message key="button.delete"/>
        </button>    
	</div>
	
    <div class="form-group">
		<display:table name="projectInfo.projectSizes" id="projectSize" class="table table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="projectSizeid" value="<c:out value='${projectSize.id}'/>" 
    			<c:if test="${param.projectSizeid == projectSize.id and method != 'SaveProjectSize'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectSize.startTime">
    			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="startTime" style="padding: 0"
                			value="<fmt:formatDate value="${projectSize.startTime}" pattern="yyyy-MM-dd" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.startTime}" pattern="yyyy-MM-dd" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectSize.size">
      			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="projectSize" style="padding: 0"
                			value="<c:out value="${projectSize.projectSize}"/>" />
        			</c:when>
        			<c:otherwise><c:out value="${projectSize.projectSize}"/></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectSize.endTime">
      			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="endTime" style="padding: 0"
                			value="<fmt:formatDate value="${projectSize.endTime}" pattern="yyyy-MM-dd" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.endTime}" pattern="yyyy-MM-dd" /></c:otherwise>
    			</c:choose>
  			</display:column>
		</display:table>
     </div>
    </c:if>
    <c:if test="${empty projectInfo.projectSizes}">
    	<a href="<c:url value='/projectSizeForm?projectInfoId=${projectInfo.id}'/>"><fmt:message key="projectSize.addPage"/></a>
    </c:if>
    
    <div class="row">
    <spring:bind path="projectInfo.capitalInvestmentType">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.capitalInvestmentType"/>
        <form:select path="capitalInvestmentType">    		
			<form:options items="${capitalInvestmentTypes}" />
		</form:select>
        
        <form:errors path="capitalInvestmentType" cssClass="help-block"/>
    </div>
	</div>
	
	<div class="row">
    <spring:bind path="projectInfo.riskManager">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.riskManager"/>
        <form:input path="riskManager" id="riskManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="riskManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.delegateManager">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.delegateManager"/>
        <form:input path="delegateManager" id="delegateManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="delegateManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.trustManager">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>
        <form:input path="trustManager" id="trustManager" maxlength="20" cssClass="form-control"/>
        <form:errors path="trustManager" cssClass="help-block"/>
    </div>
    </div>

	<div class="row">
    <spring:bind path="projectInfo.projectType">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.projectType"/>:
        <form:select path="projectType">    		
			<form:options items="${projectTypes}" />
		</form:select>        
        <form:errors path="projectType" cssClass="help-block"/>
    </div>
	</div>
    
    <c:if test="${not empty projectInfo.createUser}">
    <spring:bind path="projectInfo.createUser">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.createUser.username"/>
        <c:out value="${projectInfo.createUser.username}" />
        <form:hidden path="createUser.username" id="createUser" maxlength="20" cssClass="form-control"/>        
    </div>
    
    <spring:bind path="projectInfo.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <form:hidden path="createTime" id="createTime" />
    </div>
    
    </c:if>
    

    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveProjectInfo" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty projectInfo.id}">
          <button type="submit" class="btn btn-default" name="method"  value="DeleteProjectInfo" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

<v:javascript formName="projectInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
