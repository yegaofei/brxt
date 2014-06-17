<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="subjectCapacity.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>    
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='subjectCapacity.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="subjectCapacity" method="post" action="subjectCapacityForm" id="subjectCapacityForm" cssClass="well" onsubmit="return validateSubjectCapacity(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	 
	<div class="row">
		<div class="col-sm-7 form-group">
		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
		<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
			<c:if test="${status.first}"><select id="counterpartyId" name="counterpartyId" class="form-control"></c:if>
				<option value="${counterparty.id}" <c:if test = "${counterparty.id == subjectCapacity.counterparty.id}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
			<c:if test="${status.last}"></select></c:if>
		</c:forEach>
		</div>
		
		<spring:bind path="subjectCapacity.checkTime">
    	<div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    	</spring:bind>
        	<appfuse:label styleClass="control-label" key="subjectCapacity.checkTime"/>
        	<form:input path="checkTime" id="checkTime" maxlength="20" cssClass="form-control"/>
        	<form:errors path="checkTime" cssClass="help-block"/>
    	</div>
	</div>
	
	<div class="form-group">
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th> 
							<th><fmt:message key="subjectCapacity.comments"/></th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="subjectCapacity.licenseVerificationDate"/></td>
							<td><form:input path="licenseVerificationDate" id="licenseVerificationDate" maxlength="20" cssClass="form-control input-sm"/></td>
							<td><form:input path="comment_lvd" id="comment_lvd" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.orgCodeVerificationDate"/></td>
							<td><form:input path="orgCodeVerificationDate" id="orgCodeVerificationDate" maxlength="20" cssClass="form-control input-sm"/></td>
							<td><form:input path="comment_ocvd" id="comment_ocvd" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.loanCardValid"/></td>
							<td>
								<label class="checkbox-inline">
        						<form:radiobutton path="loanCardValid" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
								<form:radiobutton path="loanCardValid" value="False" /><fmt:message key='label.no'/>
								</label>
							</td>
							<td><form:input path="comment_lcv" id="comment_lcv" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.nameChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="nameChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="nameChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_nc" id="comment_nc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.ownerChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="ownerChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="ownerChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_oc" id="comment_oc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.ownershipChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="ownershipChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="ownershipChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_osc" id="comment_osc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.capitalChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="capitalChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="capitalChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_cc" id="comment_cc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.devCapacityChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="devCapacityChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="devCapacityChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_dcc" id="comment_dcc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.bizScopeChanged"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="bizScopeChanged" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="bizScopeChanged" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_bsc" id="comment_bsc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.otherBigChanges"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="otherBigChanges" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="otherBigChanges" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_obc" id="comment_obc" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.verifyResults"/></td>
							<td><label class="checkbox-inline">
        							<form:radiobutton path="verifyResults" value="True" /><fmt:message key='label.yes'/> 
        						</label>
        						<label class="checkbox-inline">
									<form:radiobutton path="verifyResults" value="False" /><fmt:message key='label.no'/>
								</label></td>
							<td><form:input path="comment_vr" id="comment_vr" maxlength="20" cssClass="form-control input-sm"/></td>
						</tr>
						</tbody>
					</table>		
	</div>
	
    <c:if test="${not empty subjectCapacity.createUser}">
        <form:hidden path="createUser" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty subjectCapacity.updateUser}">
        <form:hidden path="updateUser" />     
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
<script>
  $(function() {
    $('#checkTime').datepicker({
				//language: 'zh-CN'
				format: '<c:out value="${shortDatePatternJs}" />'
			});
  });
</script>
