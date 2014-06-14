<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="collateral.form.title"/></title>
</head>

<div class="col-sm-2">
    <h3><fmt:message key='collateral.form.head'/></h3>
</div>
 
<div class="col-sm-8">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="collateralDataModel" method="post" action="${ctx}/collateral/collateralForm" 
    	id="collateralForm" cssClass="well"  onsubmit="return validateCollateral(this)">
    <form:hidden path="id"/>
    
    <spring:bind path="collateralDataModel.projectName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="collateralDataModel.description">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.description"/>
        <form:input path="description" id="description" maxlength="20" cssClass="form-control"/>
        <form:errors path="description" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralDataModel.evaluatedValue">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.evaluatedValue"/>
        <form:input path="evaluatedValue" id="evaluatedValue" maxlength="20" cssClass="form-control"/>
        <form:errors path="evaluatedValue" cssClass="help-block"/>
    </div>
    
    <spring:bind path="collateralDataModel.rate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="collateral.rate"/>
        <form:input path="rate" id="rate" maxlength="20" cssClass="form-control"/>
        <form:errors path="rate" cssClass="help-block"/>
    </div>
    
	<c:if test='${not empty collateralDataModel.id}'>
    <div class="form-group">
		<appfuse:label styleClass="control-label" key="collateral.detailList"/>	
		<label class="checkbox-inline">
        	<form:radiobutton path="addType" value="LAND" /><fmt:message key="land"/> 
        </label>
        <label class="checkbox-inline">
			<form:radiobutton path="addType" value="PROPERTY" /><fmt:message key="property"/>
		</label>
		<label class="checkbox-inline">
			<form:radiobutton path="addType" value="CONSTRUCTING_PROJECT" /><fmt:message key="constructing_project"/>
		</label>
		<label style="padding-left: 15px;"></label>
		<button  type="submit" name="method" value="AddCollateral">
                <fmt:message key="button.add"/>
        </button>    
	</div>	
	<div class="form-group">	
		<display:table name="collateralDataModel.detailList" id="detail" class="table table-condensed table-striped table-hover">
  			<display:column titleKey="collateral.form.title">
    			<c:url value="${ctx}/collateral/editCollateralDetail" var="editUrl">
        			<c:param name="id" value="${detail.realId}"/>
        			<c:param name="collateralType" value="${detail.type}"/>
    			</c:url>
    			<a href="<c:out value="${editUrl}" escapeXml="false" />"><c:out value="${detail.displayId}"/> </a>
  			</display:column>
  			<display:column titleKey="collateral.type">
       			<fmt:message key="${detail.type.type}"/>
  			</display:column>
  			<display:column property="evaluatedTime" sortable="false" titleKey="collateral.evaluatedTime"/>
  			<display:column property="collateralValue" sortable="false" titleKey="collateral.collateralValue"/>
		</display:table>
     </div>
     </c:if>
     
     <div class="form-group">
     	<div id="actions" >
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <a class="btn btn-default" href="<c:url value='${ctx}/collateral/collateralList'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    	</div>
     </div>	
     
    </form:form>
</div>


<v:javascript formName="collateralForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
  
