<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="collateral.form.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
    
</head>

<div class="col-sm-2">
    <h3><fmt:message key='collateral.form.head'/></h3>
    <span>
    	<fmt:message key='collateral.nonrequired.projectName'/>: &nbsp; 
    	<c:out value="${collateralConstrProj.collateralOverview.projectInfo.projectName}" />
    </span>
</div>
 
<div class="col-sm-10">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="collateralConstrProj" method="post" action="${ctx}/collateral/collateralConstrProj" 
    	id="collateralConstrProjForm" cssClass="well"  onsubmit="return validateCollateralConstrProj(this)">
    <input type="hidden" name="collateralId" value="${collateralConstrProj.id}"/>
    <input type="hidden" name="overviewId" value="${param.overviewId}" >

	<div class="row">
    <spring:bind path="collateralConstrProj.certificateNo">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificateNo"/>
        <form:input path="certificateNo" id="certificateNo" maxlength="20" cssClass="form-control"/>
        <form:errors path="certificateNo" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralConstrProj.certificate">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificate"/>
        <form:input path="certificate" id="certificate" maxlength="20" cssClass="form-control"/>
        <form:errors path="certificate" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralConstrProj.bookedFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.bookedFundUsage"/>
        <form:input path="bookedFundUsage" id="bookedFundUsage" maxlength="20" cssClass="form-control"/>
        <form:errors path="bookedFundUsage" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralConstrProj.contractFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.contractFundUsage"/>
        <form:input path="contractFundUsage" id="contractFundUsage" maxlength="20" cssClass="form-control"/>
        <form:errors path="contractFundUsage" cssClass="help-block"/>
    </div>    
    
    <spring:bind path="collateralConstrProj.privilegesOrder">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.privilegesOrder"/>
        <form:input path="privilegesOrder" id="privilegesOrder" maxlength="20" cssClass="form-control"/>
        <form:errors path="privilegesOrder" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralConstrProj.archived">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.archived"/>
        <form:input path="archived" id="archived" maxlength="20" cssClass="form-control"/>
        <form:errors path="archived" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralConstrProj.executor">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.executor"/>
        <form:input path="executor" id="executor" maxlength="20" cssClass="form-control"/>
        <form:errors path="executor" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralConstrProj.evaluatedTime">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedTime"/>
        <form:input path="evaluatedTime" id="evaluatedTime" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedTime" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralConstrProj.evaluatedMethod1">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod1"/>
        <form:input path="evaluatedMethod1" id="evaluatedMethod1" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedMethod1" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralConstrProj.value1">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value1"/>
        <form:input path="value1" id="value1" maxlength="20" cssClass="form-control"/>
        <form:errors path="value1" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralConstrProj.evaluatedMethod2">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod2"/>
        <form:input path="evaluatedMethod2" id="evaluatedMethod2" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedMethod2" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralConstrProj.value2">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value2"/>
        <form:input path="value2" id="value2" maxlength="20" cssClass="form-control"/>
        <form:errors path="value2" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralConstrProj.owner">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.owner"/>
        <form:input path="owner" id="owner" maxlength="20" cssClass="form-control"/>
        <form:errors path="owner" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralConstrProj.location">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.location"/>
        <form:input path="location" id="location" maxlength="20" cssClass="form-control"/>
        <form:errors path="location" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralConstrProj.district">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.district"/>
        <form:input path="district" id="district" maxlength="20" cssClass="form-control"/>
        <form:errors path="district" cssClass="help-block"/>
    </div>           
    </div>     
    
    <div class="row">
    <spring:bind path="collateralConstrProj.evaluatedValue">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedValue"/>
        <form:input path="evaluatedValue" id="evaluatedValue" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedValue" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralConstrProj.rate">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.rate"/>
        <form:input path="rate" id="rate" maxlength="20" cssClass="form-control"/>
        <form:errors path="rate" cssClass="help-block"/>
    </div>   
    </div>   
    
    <div class="form-group">
        <appfuse:label styleClass="control-label" key="collateral.type"/>: &nbsp; 
        <fmt:message key="${collateralConstrProj.collateralType.type}" />
    </div>  
    
    <div class="row">
    <spring:bind path="collateralConstrProj.constrProj.type">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.type"/>
        <form:input path="constrProj.type" id="owner" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.type" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralConstrProj.constrProj.projectSize">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.projectSize"/>
        <form:input path="constrProj.projectSize" id="projectSize" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.projectSize" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralConstrProj.constrProj.floor">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.floor"/>
        <form:input path="constrProj.floor" id="floor" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.floor" cssClass="help-block"/>
    </div>   
    </div>  
     
    <div class="row">
    <spring:bind path="collateralConstrProj.constrProj.rightsType">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.rightsType"/>
        <form:input path="constrProj.rightsType" id="rightsType" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.rightsType" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralConstrProj.constrProj.yearsLimit">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.yearsLimit"/>
        <form:input path="constrProj.yearsLimit" id="yearsLimit" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.yearsLimit" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralConstrProj.constrProj.price">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="constrProj.price"/>
        <form:input path="constrProj.price" id="price" maxlength="20" cssClass="form-control"/>
        <form:errors path="constrProj.price" cssClass="help-block"/>
    </div>                
    </div>           
    
    <div class="form-group">
     	<div id="actions" >
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <a class="btn btn-default" href="
        	<c:url value='${ctx}/collateral/collateralForm'> 
        		<c:param name="id" value="${collateralConstrProj.collateralOverview.id}"/>
        	</c:url>">
            <i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
    	</div>
    </div>     
    
    </form:form>
</div>


<v:javascript formName="collateralConstrProjForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#evaluatedTime').datepicker({
				language: 'zh-CN'
				//format: 'yyyy-mm-dd'
			});
  });
</script>
  
