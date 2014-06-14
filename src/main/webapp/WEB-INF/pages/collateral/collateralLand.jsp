<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="collateral.form.title"/></title>
</head>

<div class="col-sm-2">
    <h3><fmt:message key='collateral.form.head'/></h3>
    <span>
    	<fmt:message key='collateral.nonrequired.projectName'/>: &nbsp; 
    	<c:out value="${collateralLand.collateralOverview.projectInfo.projectName}" />
    </span>
</div>
 
<div class="col-sm-10">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="collateralLand" method="post" action="${ctx}/collateral/collateralLand" 
    	id="collateralLandForm" cssClass="well"  onsubmit="return validateCollateralLand(this)">
    <form:hidden path="id"/>
    <input type="hidden" name="overviewId" value="${param.overviewId}" >

	<div class="row">
    <spring:bind path="collateralLand.certificateNo">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificateNo"/>
        <form:input path="certificateNo" id="certificateNo" maxlength="20" cssClass="form-control"/>
        <form:errors path="certificateNo" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralLand.certificate">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificate"/>
        <form:input path="certificate" id="certificate" maxlength="20" cssClass="form-control"/>
        <form:errors path="certificate" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralLand.bookedFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.bookedFundUsage"/>
        <form:input path="bookedFundUsage" id="bookedFundUsage" maxlength="20" cssClass="form-control"/>
        <form:errors path="bookedFundUsage" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralLand.contractFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.contractFundUsage"/>
        <form:input path="contractFundUsage" id="contractFundUsage" maxlength="20" cssClass="form-control"/>
        <form:errors path="contractFundUsage" cssClass="help-block"/>
    </div>    
    
    <spring:bind path="collateralLand.privilegesOrder">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.privilegesOrder"/>
        <form:input path="privilegesOrder" id="privilegesOrder" maxlength="20" cssClass="form-control"/>
        <form:errors path="privilegesOrder" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralLand.archived">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.archived"/>
        <form:input path="archived" id="archived" maxlength="20" cssClass="form-control"/>
        <form:errors path="archived" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralLand.executor">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.executor"/>
        <form:input path="executor" id="executor" maxlength="20" cssClass="form-control"/>
        <form:errors path="executor" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralLand.evaluatedTime">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedTime"/>
        <form:input path="evaluatedTime" id="evaluatedTime" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedTime" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralLand.evaluatedMethod1">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod1"/>
        <form:input path="evaluatedMethod1" id="evaluatedMethod1" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedMethod1" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralLand.value1">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value1"/>
        <form:input path="value1" id="value1" maxlength="20" cssClass="form-control"/>
        <form:errors path="value1" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralLand.evaluatedMethod2">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod2"/>
        <form:input path="evaluatedMethod2" id="evaluatedMethod2" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedMethod2" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralLand.value2">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value2"/>
        <form:input path="value2" id="value2" maxlength="20" cssClass="form-control"/>
        <form:errors path="value2" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralLand.owner">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.owner"/>
        <form:input path="owner" id="owner" maxlength="20" cssClass="form-control"/>
        <form:errors path="owner" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralLand.location">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.location"/>
        <form:input path="location" id="location" maxlength="20" cssClass="form-control"/>
        <form:errors path="location" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralLand.district">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.district"/>
        <form:input path="district" id="district" maxlength="20" cssClass="form-control"/>
        <form:errors path="district" cssClass="help-block"/>
    </div>           
    </div>     
    
    <div class="row">
    <spring:bind path="collateralLand.evaluatedValue">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedValue"/>
        <form:input path="evaluatedValue" id="evaluatedValue" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedValue" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralLand.rate">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.rate"/>
        <form:input path="rate" id="rate" maxlength="20" cssClass="form-control"/>
        <form:errors path="rate" cssClass="help-block"/>
    </div>   
    </div>   
    
    <div class="form-group">
        <appfuse:label styleClass="control-label" key="collateral.type"/>: &nbsp; 
        <fmt:message key="${collateralLand.collateralType.type}" />
    </div>  
    
    <div class="row">
    <spring:bind path="collateralLand.land.owner">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.owner"/>
        <form:input path="land.owner" id="owner" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.owner" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralLand.land.sourceMethod">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.sourceMethod"/>
        <form:input path="land.sourceMethod" id="sourceMethod" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.sourceMethod" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralLand.land.landAttribute">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.landAttribute"/>
        <form:input path="land.landAttribute" id="landAttribute" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.landAttribute" cssClass="help-block"/>
    </div>   
    </div>  
     
    <div class="row">
    <spring:bind path="collateralLand.land.size">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.size"/>
        <form:input path="land.size" id="size" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.size" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralLand.land.yearsLimit">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.yearsLimit"/>
        <form:input path="land.yearsLimit" id="yearsLimit" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.yearsLimit" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralLand.land.price">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="land.price"/>
        <form:input path="land.price" id="price" maxlength="20" cssClass="form-control"/>
        <form:errors path="land.price" cssClass="help-block"/>
    </div>                
    </div>           
    
    <div class="form-group">
     	<div id="actions" >
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <a class="btn btn-default" href="
        	<c:url value='${ctx}/collateral/collateralForm'> 
        		<c:param name="id" value="${collateralLand.collateralOverview.id}"/>
        	</c:url>">
            <i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
    	</div>
    </div>     
    
    </form:form>
</div>


<v:javascript formName="collateralForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
  
