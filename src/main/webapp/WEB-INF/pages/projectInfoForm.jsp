<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoDetail.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>

<c:set var="delObject" scope="request"><fmt:message key="projectInfoList.heading"/></c:set>
<c:set var="delInvestment" scope="request"><fmt:message key="projectInfo.investment.projects"/></c:set>
<script type="text/javascript">
	var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
	var msgCommit = "<fmt:message key="commit.confirm"><fmt:param value="${delObject}"/></fmt:message>";
	var msgDelInvesmentConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delInvestment}"/></fmt:message>";
</script>

<div class="col-sm-2">
    <h3><fmt:message key='projectInfoDetail.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectInfo" method="post" action="${ctx}/projectInfoForm" id="projectInfoForm" cssClass="well" onsubmit="return validateProjectInfo(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<fieldset <c:if test="${projectInfo.projectInfoStatus.committed}">disabled</c:if>> 
	<div class="row">
    <spring:bind path="projectInfo.projectName">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control input-sm"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="projectInfo.expectedReturn">
    <div class="col-sm-2 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.expectedReturn"/>
        <div class="input-group"><form:input path="expectedReturn" id="expectedReturn" maxlength="20" cssClass="form-control input-sm"/><span class="input-group-addon input-sm">%</span></div>
        <form:errors path="expectedReturn" cssClass="help-block"/>
    </div>
	</div>

	<c:if test="${not empty projectInfo.projectSizes and not empty param.id}">
	<appfuse:label styleClass="control-label" key="projectSize.title"/>
    <div id="actions" class="btn-group">
		<c:choose>
		<c:when test="${method == 'EditProjectSize'}">
    		<button type="submit" name="method" value="SaveProjectSize" class="btn btn-default btn-xs">
                <fmt:message key="button.save"/>
            </button>
            <button type="submit" name="method" value="CancelProjectSize" class="btn btn-default btn-xs">
                	<fmt:message key="button.cancel"/>
			</button>
		</c:when>
		<c:otherwise>
        	<button type="submit" name="method" value="AddProjectSize" class="btn btn-default btn-xs">
                <fmt:message key="button.add"/>
        	</button>    
        	<button type="submit" name="method" value="EditProjectSize" class="btn btn-default btn-xs">
                <fmt:message key="button.edit"/>
        	</button>  
        	<button type="submit" name="method" value="DeleteProjectSize" class="btn btn-default btn-xs">
                <fmt:message key="button.delete"/>
        	</button>        
        </c:otherwise>
        </c:choose>
	</div>
	<div class="form-group">
		<display:table name="projectInfo.projectSizes" id="projectSize" class="table table-bordered table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="projectSizeid" value="<c:out value='${projectSize.id}'/>" 
    			<c:if test="${param.projectSizeid == projectSize.id and method != 'SaveProjectSize'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectSize.startTime" class="input-sm">
    			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="startTime" style="padding: 0" id="startTime" class="form-control input-sm"
                			value="<fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectSize.endTime"  class="input-sm">
      			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="endTime" style="padding: 0" id="endTime" class="form-control input-sm"
                			value="<fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" />" />
        			</c:when>
        			<c:otherwise><fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectSize.size"  class="input-sm">
      			<c:choose>
        			<c:when test="${method == 'EditProjectSize' and param.projectSizeid == projectSize.id}">
            			<input type="text" name="projectSize" style="padding: 0" class="form-control input-sm"
                			value="<c:out value="${projectSize.projectSize}"/>" />
        			</c:when>
        			<c:otherwise><c:out value="${projectSize.projectSize}"/></c:otherwise>
    			</c:choose>
  			</display:column>
		</display:table>
     </div>
	</c:if>
	
	<c:if test="${empty projectInfo.projectSizes and not empty param.id}">
    	<appfuse:label styleClass="control-label" key="projectSize.title"/>
    	<div id="actions" class="btn-group">	
        <button type="submit" name="method" value="AddProjectSize" class="btn btn-default btn-xs">
                <fmt:message key="button.add"/>
        </button>          
		</div>
    </c:if>
    
    <div class="row">
    <spring:bind path="projectInfo.fundUsage">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.fundUsage"/>:
        <form:input path="fundUsage" id="fundUsage" maxlength="300" cssClass="form-control input-sm"/>
        <form:errors path="fundUsage" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty param.id and not empty projectInfo.investments}">
    <div class="col-sm-6 form-group">
    	<appfuse:label styleClass="control-label" key="projectInfo.investment.projects"/>
    	<div id="actions" class="btn-group">
			<c:choose>
			<c:when test="${method == 'EditInvestment'}">
    			<button type="submit" name="method" value="SaveInvestment" class="btn btn-default btn-xs">
                	<fmt:message key="button.save"/>
            	</button>
            	<button type="submit" name="method" value="CancelInvestment" class="btn btn-default btn-xs">
                	<fmt:message key="button.cancel"/>
        		</button>
			</c:when>
			<c:otherwise>
        		<button type="submit" name="method" value="AddInvestment" class="btn btn-default btn-xs">
                	<fmt:message key="button.add"/>
        		</button>
        		<button type="submit" name="method" value="EditInvestment" class="btn btn-default btn-xs">
                	<fmt:message key="button.edit"/>
        		</button>  
        		<button type="submit" name="method" value="DeleteInvestment" class="btn btn-default btn-xs" onclick="return confirmMessage(msgDelInvesmentConfirm)">
    	            <fmt:message key="button.delete"/>
	        	</button>  
        	</c:otherwise>
        	</c:choose>        
		</div>
		<display:table name="projectInfo.investments" id="investment" class="table table-bordered table-condensed table-striped table-hover">
			<display:column style="width: 5%">
    			<input type="checkbox" name="investmentId" value="<c:out value='${investment.id}'/>" 
    			<c:if test="${param.investmentId == investment.id and method != 'SaveInvestment'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectInfo.investmentName"  class="input-sm">
    			<c:choose>
        			<c:when test="${method == 'EditInvestment' and param.investmentId == investment.id}">
            			<input type="text" name="investmentProjectName" style="padding: 0" class="form-control input-sm"
                			value="<c:out value="${investment.projectName}" />" />
        			</c:when>
        			<c:otherwise><c:out value="${investment.projectName}" /></c:otherwise>
    			</c:choose>
  			</display:column>
			<display:column titleKey="projectInfo.capitalInvestmentType"  class="input-sm">
      			<c:choose>
        			<c:when test="${method == 'EditInvestment' and param.investmentId == investment.id}">
                			<c:forEach var="investmentType" items="${capitalInvestmentTypes}" varStatus="status">
                    			<c:if test="${status.first}"><select id="investmentType" name="investmentType" class="form-control input-sm"></c:if>
                    			<option value="${investmentType.title}" <c:if test = "${investmentType.title == investment.projectType}" > selected </c:if>><fmt:message key="${investmentType.title}"/></option>
          						<c:if test="${status.last}"></select></c:if>
                			</c:forEach>
                			<input type="hidden" name="oldInvestmentType" value="${investment.capitalInvestmentType.title}" >
        			</c:when>
        			<c:otherwise><fmt:message key="${investment.projectType}"/></c:otherwise>
    			</c:choose>
  			</display:column>  			
		</display:table>
     </div>
     </c:if>
     <c:if test="${not empty param.id and empty projectInfo.investments}">
     	<appfuse:label styleClass="control-label" key="projectInfo.investment.projects"/>
     	<div id="actions" class="btn-group">
        	<button type="submit" name="method" value="AddInvestment" class="btn btn-default btn-xs">
                <fmt:message key="button.add"/>
        	</button>    
		</div>
     </c:if>
    </div>
    
	<div class="row">
    <spring:bind path="projectInfo.riskManager">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.riskManager"/>
        <form:input path="riskManager" id="riskManager" maxlength="10" cssClass="form-control input-sm"/>
        <form:errors path="riskManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.delegateManager">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.delegateManager"/>
        <form:input path="delegateManager" id="delegateManager" maxlength="10" cssClass="form-control input-sm"/>
        <form:errors path="delegateManager" cssClass="help-block"/>
    </div>

    <spring:bind path="projectInfo.trustManager">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>
        <form:input path="trustManager" id="trustManager" maxlength="10" cssClass="form-control input-sm"/>
        <form:errors path="trustManager" cssClass="help-block"/>
    </div>
    </div>

	<div class="row">
	<spring:bind path="projectInfo.guaranteeMode">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.guaranteeMode"/>:
        <form:input path="guaranteeMode" id="guaranteeMode" maxlength="300" cssClass="form-control input-sm"/>
        <form:errors path="guaranteeMode" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="projectInfo.projectType">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="projectInfo.projectType"/>:
        <form:select path="projectType" class="form-control input-sm">    		
			<form:options items="${projectTypes}" />
		</form:select>        
        <form:errors path="projectType" cssClass="help-block"/>
    </div>
	</div>
	
	<c:if test="${not empty param.id}">
	<div class="row">    
    <div class="col-sm-6 form-group">
    	<appfuse:label styleClass="control-label" key="projectInfo.counterparty.list"/>
    	<c:if test="${not empty projectInfo.counterparties}">
    	<div id="actions" class="btn-group">
			<c:choose>
			<c:when test="${method == 'EditCounterparty'}">
    			<button type="submit" name="method" value="SaveCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.save"/>
            	</button>
            	<button type="submit" name="method" value="CancelCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.cancel"/>
        		</button>
			</c:when>
			<c:otherwise>
        		<button type="submit" name="method" value="AddCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.add"/>
        		</button>    
        		<button type="submit" name="method" value="EditCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.edit"/>
        		</button>
        		<button type="submit" name="method" value="DeleteCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.delete"/>
        		</button>
        	</c:otherwise>
        	</c:choose>        	
		</div>
		<display:table name="projectInfo.counterparties" id="counterparty" class="table table-bordered table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="counterpartyId" value="<c:out value='${counterparty.id}'/>" 
    			<c:if test="${param.counterpartyId == counterparty.id and method != 'SaveCounterparty'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectInfo.counterparty.name"  class="input-sm">
    			<c:choose>
        			<c:when test="${method == 'EditCounterparty' and param.counterpartyId == counterparty.id}">
            			<input type="text" name="counterpartyName" style="padding: 0"
                			value="<c:out value="${counterparty.name}" />" />
        			</c:when>
        			<c:otherwise><c:out value="${counterparty.name}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectInfo.counterparty.type"  class="input-sm">
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
		</c:if>
		<c:if test="${empty projectInfo.counterparties}">
			<div id="actions" class="btn-group">
        		<button type="submit" name="method" value="AddCounterparty" class="btn btn-default btn-xs">
                	<fmt:message key="button.add"/>
        		</button>    
			</div>
		</c:if>
     </div>
     
     <div class="col-sm-6 form-group">
     	<appfuse:label styleClass="control-label" key="projectInfo.guarantor.list"/>
     	<c:if test="${not empty projectInfo.guarantors}">
    	<div id="actions" class="btn-group">
			<c:choose>
			<c:when test="${method == 'EditGuarantor'}">
    			<button type="submit" name="method" value="SaveGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.save"/>
            	</button>
            	<button type="submit" name="method" value="CancelGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.cancel"/>
        		</button>
			</c:when>
			<c:otherwise>
        		<button type="submit" name="method" value="AddGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.add"/>
        		</button>    
        		<button type="submit" name="method" value="EditGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.edit"/>
        		</button>
        		<button type="submit" name="method" value="DeleteGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.delete"/>
        		</button>
        	</c:otherwise>
        	</c:choose>
		</div>
		<display:table name="projectInfo.guarantors" id="guarantor" class="table table-bordered table-condensed table-striped table-hover">
  			<display:column style="width: 5%">
    			<input type="checkbox" name="guarantorId" value="<c:out value='${guarantor.id}'/>" 
    			<c:if test="${param.guarantorId == guarantor.id and method != 'SaveGuarantor'}">checked="checked"</c:if>
        			style="margin: 0 0 0 4px" onclick="radio(this)" />
  			</display:column>
  			<display:column titleKey="projectInfo.guarantor.name"  class="input-sm">
    			<c:choose>
        			<c:when test="${method == 'EditGuarantor' and param.guarantorId == guarantor.id}">
            			<input type="text" name="guarantorName" style="padding: 0"
                			value="<c:out value="${guarantor.name}" />" />
        			</c:when>
        			<c:otherwise><c:out value="${guarantor.name}" /></c:otherwise>
    			</c:choose>
  			</display:column>
  			<display:column titleKey="projectInfo.guarantor.type"  class="input-sm">
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
		</c:if>
		<c:if test="${empty projectInfo.guarantors}">
			<div id="actions" class="btn-group">
        		<button type="submit" name="method" value="AddGuarantor" class="btn btn-default btn-xs">
                	<fmt:message key="button.add"/>
        		</button>    
			</div>
		</c:if>
     </div>
	</div>	
    </c:if>
    
    <c:if test="${not empty projectInfo.createUser}">
        <form:hidden path="createUser" id="createUser"/>        
    </c:if>
    
    <c:if test="${not empty projectInfo.updateUser}">
    <div class="row">
    <div class="col-sm-5 form-group">
        <appfuse:label styleClass="control-label" key="projectInfo.updateUser.username"/>:
        <c:out value="${projectInfo.updateUser}" />     
    </div>
    
    <div class="col-sm-5 form-group">
    	<appfuse:label styleClass="control-label" key="projectInfo.updateTime"/>:
        <fmt:formatDate value="${projectInfo.updateTime}" pattern="${datePattern} HH:mm:ss" />
    </div>
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
        <c:if test="${not empty projectInfo.id}">
          <button type="submit" class="btn btn-primary" name="method"  value="CommitProjectInfo" onclick="bCancel=true;return confirmMessage(msgCommit)">
              <i class="icon-trash"></i> <fmt:message key="button.commit"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.done"/>
        </button>
    </div>
    </fieldset>
    </form:form>
</div>

<v:javascript formName="projectInfo" cdata="false" dynamicJavascript="true" staticJavascript="true"/>
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
  
