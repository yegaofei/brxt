<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="repayment.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
    <h3><fmt:message key='repayment.heading'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="repayment" method="post" action="repaymentForm" id="repaymentForm" cssClass="well"  onsubmit="return validateRepayment(this)">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	<input type="hidden" name="projectInfoId" value="${projectInfoId}"/>
	
    <spring:bind path="repayment.repaymentTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repayment.repaymentTime"/>
        <form:input path="repaymentTime" id="repaymentTime" maxlength="20" cssClass="form-control"/>
        <form:errors path="repaymentTime" cssClass="help-block"/>
    </div>

    <spring:bind path="repayment.amount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repayment.amount"/>
        <form:input path="amount" id="amount" maxlength="60" cssClass="form-control"/>
        <form:errors path="amount" cssClass="help-inline"/>
    </div>
    
    <spring:bind path="repayment.type">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repayment.type"/>
        <c:forEach var="repaymentType" items="${repaymentTypes}" varStatus="status">
        	<label class="checkbox-inline">
        		<form:radiobutton path="type" value="${repaymentType}" /><fmt:message key='${repaymentType}'/> 
        	</label>
        </c:forEach>
    </div>
    
    <spring:bind path="repayment.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="repayment.comment"/>
        <form:input path="comment" id="comment" maxlength="60" cssClass="form-control"/>
        <form:errors path="comment" cssClass="help-inline"/>
    </div>
    
    <c:if test="${not empty repayment.createUser}">
        <form:hidden path="createUser.username" id="createUser"/>      
        <form:hidden path="createTime"/>        
    </c:if>
    
    <c:if test="${not empty repayment.updateUser}">
        <form:hidden path="updateUser.username" />     
        <form:hidden path="updateTime" />     
    </c:if>
    
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="SaveRepayment" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>       
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
    </form:form>
</div>

<v:javascript formName="repayment" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#repaymentTime').datepicker({
				autoclose: true,
				language: 'zh-CN'				
			});
  });
</script>
