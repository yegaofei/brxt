<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="creditInformation.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='creditInformation.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="creditInformation" method="post" action="creditInforForm" id="creditInformationForm" cssClass="well" onsubmit="return validateCreditInformation(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	 
	<div class="row"> 
	<div class="form-group col-sm-6">
		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
		<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
			<c:if test="${status.first}"><select id="counterpartyId" name="counterpartyId" class="form-control input-sm"></c:if>
				<option value="${counterparty.id}" <c:if test = "${counterparty.id == creditInformation.counterparty.id}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
			<c:if test="${status.last}"></select></c:if>
		</c:forEach>
	</div>
	
    <spring:bind path="creditInformation.queryTime">
    <div class="form-group col-sm-4">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.queryTime"/>
        <form:input path="queryTime" id="queryTime" maxlength="20" cssClass="form-control input-sm"/>
        <form:errors path="queryTime" cssClass="help-block"/>
    </div>
    </div>

    <spring:bind path="creditInformation.debt">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.debt"/><fmt:message key="currency.unit.wan"/>
        <form:input path="debt" id="debt" maxlength="60" cssClass="form-control input-sm"/>
        <form:errors path="debt" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="creditInformation.outstanding">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.outstanding"/><fmt:message key="currency.unit.wan"/>
        <form:input path="outstanding" id="outstanding" maxlength="60" cssClass="form-control input-sm"/>
        <form:errors path="outstanding" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="creditInformation.balance">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.balance"/><fmt:message key="currency.unit.wan"/>
        <form:input path="balance" id="balance" maxlength="60" cssClass="form-control input-sm"/>
        <form:errors path="balance" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="creditInformation.overdue">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.overdue"/>
        <label class="checkbox-inline">
        	<form:radiobutton path="overdue" value="True" /><fmt:message key='label.yes'/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="overdue" value="False" /><fmt:message key='label.no'/>
		</label>
    </div>
    
    <spring:bind path="creditInformation.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="creditInformation.comment"/>
        <form:textarea path="comment" id="comment" cssClass="form-control input-sm"/>
        <form:errors path="comment" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty creditInformation.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty creditInformation.updateUser}">
        <form:hidden path="updateUser.username" />     
        <form:hidden path="updateTime" />     
    </c:if>
    
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveCreditInfor" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>       
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

<v:javascript formName="creditInformation" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#queryTime').datepicker({
				autoclose: true,
				language: 'zh-CN'				
			});
  });
</script>
