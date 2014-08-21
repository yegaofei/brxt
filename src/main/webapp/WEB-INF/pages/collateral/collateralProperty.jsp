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
    	<c:out value="${collateralProperty.collateralOverview.projectInfo.projectName}" />
    </span>
</div>
 
<div class="col-sm-10">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="collateralProperty" method="post" action="${ctx}/collateral/collateralProperty" 
    	id="collateralPropertyForm" cssClass="well"  onsubmit="return validateCollateralProperty(this)">
    <input type="hidden" name="collateralId" value="${collateralProperty.id}"/>
    <input type="hidden" name="overviewId" value="${param.overviewId}" >

	<div class="row">
    <spring:bind path="collateralProperty.certificateNo">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificateNo"/>
        <form:input path="certificateNo" id="certificateNo"  cssClass="form-control"/>
        <form:errors path="certificateNo" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralProperty.certificate">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.certificate"/>
        <form:input path="certificate" id="certificate"  cssClass="form-control"/>
        <form:errors path="certificate" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralProperty.bookedFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.bookedFundUsage"/>
        <form:input path="bookedFundUsage" id="bookedFundUsage"  cssClass="form-control"/>
        <form:errors path="bookedFundUsage" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralProperty.contractFundUsage">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.contractFundUsage"/>
        <form:input path="contractFundUsage" id="contractFundUsage"  cssClass="form-control"/>
        <form:errors path="contractFundUsage" cssClass="help-block"/>
    </div>    
    
    <spring:bind path="collateralProperty.privilegesOrder">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.privilegesOrder"/>
        <form:input path="privilegesOrder" id="privilegesOrder"  cssClass="form-control"/>
        <form:errors path="privilegesOrder" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralProperty.archived">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.archived"/>
        <form:input path="archived" id="archived"  cssClass="form-control"/>
        <form:errors path="archived" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralProperty.executor">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.executor"/>
        <form:input path="executor" id="executor"  cssClass="form-control"/>
        <form:errors path="executor" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralProperty.evaluatedTime">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedTime"/>
        <form:input path="evaluatedTime" id="evaluatedTime"  cssClass="form-control"/>
        <form:errors path="evaluatedTime" cssClass="help-block"/>
    </div>
    </div>
    
    <div class="row">
    <spring:bind path="collateralProperty.evaluatedMethod1">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod1"/>
        <form:input path="evaluatedMethod1" id="evaluatedMethod1"  cssClass="form-control"/>
        <form:errors path="evaluatedMethod1" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralProperty.value1">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value1"/>
        <form:input path="value1" id="value1"  cssClass="form-control"/>
        <form:errors path="value1" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralProperty.evaluatedMethod2">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedMethod2"/>
        <form:input path="evaluatedMethod2" id="evaluatedMethod2"  cssClass="form-control"/>
        <form:errors path="evaluatedMethod2" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralProperty.value2">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.value2"/>
        <form:input path="value2" id="value2"  cssClass="form-control"/>
        <form:errors path="value2" cssClass="help-block"/>
    </div>           
    </div>           
    
    <div class="row">
    <spring:bind path="collateralProperty.owner">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.owner"/>
        <form:input path="owner" id="owner"  cssClass="form-control"/>
        <form:errors path="owner" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralProperty.location">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.location"/>
        <form:input path="location" id="location"  cssClass="form-control"/>
        <form:errors path="location" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralProperty.district">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.district"/>
        <form:input path="district" id="district"  cssClass="form-control"/>
        <form:errors path="district" cssClass="help-block"/>
    </div>           
    </div>     
    
    <div class="row">
    <spring:bind path="collateralProperty.evaluatedValue">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedValue"/>
        <form:input path="evaluatedValue" id="evaluatedValue"  cssClass="form-control"/>
        <form:errors path="evaluatedValue" cssClass="help-block"/>
    </div>           
    
    <spring:bind path="collateralProperty.rate">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.rate"/>
        <form:input path="rate" id="rate"  cssClass="form-control"/>
        <form:errors path="rate" cssClass="help-block"/>
    </div>   
    </div>   
    
    <div class="form-group">
        <appfuse:label styleClass="control-label" key="collateral.type"/>: &nbsp; 
        <fmt:message key="${collateralProperty.collateralType.type}" />
    </div>  
    
    <div class="row">
    <spring:bind path="collateralProperty.property.yearsLimit">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="property.yearsLimit"/>
        <form:input path="property.yearsLimit" id="yearsLimit"  cssClass="form-control"/>
        <form:errors path="property.yearsLimit" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralProperty.property.landUsageType">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="property.landUsageType"/>
        <form:input path="property.landUsageType" id="landUsageType"  cssClass="form-control"/>
        <form:errors path="property.landUsageType" cssClass="help-block"/>
    </div>   
    
    <spring:bind path="collateralProperty.property.landUsageYears">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="property.landUsageYears"/>
        <form:input path="property.landUsageYears" id="landUsageYears"  cssClass="form-control"/>
        <form:errors path="property.landUsageYears" cssClass="help-block"/>
    </div>   
    </div>  
     
    <div class="row">
    <spring:bind path="collateralProperty.property.propertyType">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="property.propertyType"/>
        <form:input path="property.propertyType" id="propertyType"  cssClass="form-control"/>
        <form:errors path="property.propertyType" cssClass="help-block"/>
    </div>    
    
    <spring:bind path="collateralProperty.property.price">
    <div class="col-sm-3 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="property.price"/>
        <form:input path="property.price" id="price"  cssClass="form-control"/>
        <form:errors path="property.price" cssClass="help-block"/>
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


<v:javascript formName="collateralPropertyForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
  $(function() {
    $('#evaluatedTime').datepicker({
				language: 'zh-CN'
				//format: 'yyyy-mm-dd'
			});
  });
</script>
