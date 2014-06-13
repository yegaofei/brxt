<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoDetail.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="projectInfoList.heading"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
 
<div class="col-sm-2">
    <h3><fmt:message key='projectInfoDetail.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectInfo" method="post" action="${ctx}/projectInfoForm" id="projectInfoForm" cssClass="well" onsubmit="return validateProjectInfo(this)">
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
	</c:if>
	
	<c:if test="${empty projectInfo.projectSizes}">
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
		</div>
    </c:if>
	
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
            			<input type="text" name="startTime" style="padding: 0" id="startTime" 
                			value="<fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" /></c:otherwise>
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
            			<input type="text" name="endTime" style="padding: 0" id="endTime" 
                			value="<fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" /></c:otherwise>
    			</c:choose>
  			</display:column>
		</display:table>
     </div>
    
    <div class="row">
    <spring:bind path="projectInfo.fundUsage">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.fundUsage"/>:
        <form:input path="fundUsage" id="fundUsage" maxlength="300" cssClass="form-control"/>
        <form:errors path="fundUsage" cssClass="help-inline"/>
    </div>
    
    <div class="col-sm-5 form-group">
		<display:table name="projectInfo.investments" id="investment" class="table table-condensed table-striped table-hover">
  			<display:column property="projectName" sortable="false" titleKey="projectInfo.investmentName"/>
  			<display:column sortable="false" titleKey="projectInfo.capitalInvestmentType">
  				<fmt:message key="${investment.capitalInvestmentType.title}"/>
  			</display:column>
		</display:table>
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
	
	<spring:bind path="projectInfo.guaranteeMode">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.guaranteeMode"/>:
        <form:input path="guaranteeMode" id="guaranteeMode" maxlength="300" cssClass="form-control"/>
        <form:errors path="guaranteeMode" cssClass="help-inline"/>
    </div>
    
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
	
	<div class="row">    
	
    <div class="col-sm-6 form-group">
    	<appfuse:label styleClass="control-label" key="projectInfo.counterparty.list"/>
    	<div id="actions" class="btn-group">
			<c:choose>
			<c:when test="${method == 'EditCounterparty'}">
    		<button type="submit" name="method" value="SaveCounterparty">
                <fmt:message key="button.save"/>
            </button>
			</c:when>
			<c:otherwise>
        	<button type="submit" name="method" value="AddCounterparty">
                <fmt:message key="button.add"/>
        	</button>    
        	</c:otherwise>
        	</c:choose>
        	<button type="submit" name="method" value="EditCounterparty">
                <fmt:message key="button.edit"/>
        	</button>
        	<button type="submit" name="method" value="DeleteCounterparty">
                <fmt:message key="button.delete"/>
        	</button>    
		</div>
		<display:table name="projectInfo.counterparties" id="counterparty" class="table table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="counterpartyId" value="<c:out value='${counterparty.id}'/>" 
    			<c:if test="${param.counterpartyId == counterparty.id and method != 'SaveCounterparty'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectInfo.counterparty.name">
    			<c:choose>
        			<c:when test="${method == 'EditCounterparty' and param.counterpartyId == counterparty.id}">
            			<input type="text" name="counterpartyName" style="padding: 0"
                			value="<c:out value="${counterparty.name}" />" />
        			</c:when>
        			<c:otherwise><c:out value="${counterparty.name}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectInfo.counterparty.type">
      			<c:choose>
        			<c:when test="${method == 'EditCounterparty' and param.counterpartyId == counterparty.id}">
						<c:forEach var="counterpartyType" items="${counterpartyTypes}" varStatus="status">
							<c:if test="${status.first}"><select id="counterpartyType" name="counterpartyType"></c:if>
							<option value="${counterpartyType.title}" <c:if test = "${counterpartyType.title == counterparty.counterpartyType}" > selected </c:if>><fmt:message key="${counterpartyType.title}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>	
        			</c:when>
        			<c:otherwise>
        			<fmt:message key="${counterparty.counterpartyType}"/>
					</c:otherwise>
    			</c:choose>
  			</display:column>
		</display:table>
     </div>
     
     <div class="col-sm-6 form-group">
     	<appfuse:label styleClass="control-label" key="projectInfo.guarantor.list"/>
    	<div id="actions" class="btn-group">
			<c:choose>
			<c:when test="${method == 'EditGuarantor'}">
    		<button type="submit" name="method" value="SaveGuarantor">
                <fmt:message key="button.save"/>
            </button>
			</c:when>
			<c:otherwise>
        	<button type="submit" name="method" value="AddGuarantor">
                <fmt:message key="button.add"/>
        	</button>    
        	</c:otherwise>
        	</c:choose>
        	<button type="submit" name="method" value="EditGuarantor">
                <fmt:message key="button.edit"/>
        	</button>
        	<button type="submit" name="method" value="DeleteGuarantor">
                <fmt:message key="button.delete"/>
        	</button>    
		</div>
		<display:table name="projectInfo.guarantors" id="guarantor" class="table table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="guarantorId" value="<c:out value='${guarantor.id}'/>" 
    			<c:if test="${param.guarantorId == guarantor.id and method != 'SaveGuarantor'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectInfo.guarantor.name">
    			<c:choose>
        			<c:when test="${method == 'EditGuarantor' and param.guarantorId == guarantor.id}">
            			<input type="text" name="guarantorName" style="padding: 0"
                			value="<c:out value="${guarantor.name}" />" />
        			</c:when>
        			<c:otherwise><c:out value="${guarantor.name}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectInfo.guarantor.type">
      			<c:choose>
        			<c:when test="${method == 'EditGuarantor' and param.guarantorId == guarantor.id}">
                			<c:forEach var="guarantorType" items="${counterpartyTypes}" varStatus="status">
                    			<c:if test="${status.first}"><select id="guarantorType" name="guarantorType"></c:if>
                    			<option value="${guarantorType.title}" <c:if test = "${guarantorType.title == guarantor.counterpartyType}" > selected </c:if>><fmt:message key="${guarantorType.title}"/></option>
          						<c:if test="${status.last}"></select></c:if>
                			</c:forEach>
        			</c:when>
        			<c:otherwise><fmt:message key="${guarantor.counterpartyType}"/></c:otherwise>
    			</c:choose>
  			</display:column>
		</display:table>
     </div>
	</div>	
    
    <c:if test="${not empty projectInfo.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>        
    </c:if>
    
    <c:if test="${not empty projectInfo.updateUser}">
    <div class="form-group">
        <appfuse:label styleClass="control-label" key="projectInfo.updateUser.username"/>:
        <c:out value="${projectInfo.updateUser.username}" />     
    </div>
    
    <div class="form-group">
    	<appfuse:label styleClass="control-label" key="projectInfo.updateTime"/>:
        <fmt:formatDate value="${projectInfo.updateTime}" pattern="${datePattern} HH:mm:ss" />
    </div>
    </c:if>

    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveProjectInfo" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <!--
        <c:if test="${not empty projectInfo.id}">
          <button type="submit" class="btn btn-default" name="method"  value="DeleteProjectInfo" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        -->
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
        
    </div>

    
    </form:form>
</div>

<v:javascript formName="projectInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script>
  $(function() {
    $('#endTime').datepicker({
				language: 'zh-CN'
				//format: 'yyyy-mm-dd'
			});
	$('#startTime').datepicker({
				language: 'zh-CN'
				//format: 'yyyy-mm-dd'
			});		
  });
</script>
  
