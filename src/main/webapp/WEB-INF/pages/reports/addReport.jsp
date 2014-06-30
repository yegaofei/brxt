<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.add.title"/></title>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='report.add.heading'/></h2>
 	<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="riskControlReport" method="post" action="${ctx}/reports/addReport" cssClass="well">
    	
    <div class="row">	
    	<div class="col-sm-2">
    	<appfuse:label styleClass="control-label" key="report.add.projectName"/>
        <form:select path="projectInfo.projectName" id="projectName" cssClass="form-control input-sm" >    		
			<form:options items="${allProjectNames}" />
		</form:select>   
		</div>
		
		<div class="col-sm-2">
		<appfuse:label styleClass="control-label" key="report.add.reportSeason"/>
        <form:select path="reportSeason" id="reportSeason" cssClass="form-control input-sm" >    		
			<form:options items="${allReportSeasons}" />
		</form:select>
		</div> 	
		
		<div class="col-sm-1">
			<label>&nbsp;</label>
			<button type="submit" class="form-control input-sm btn btn-primary btn-sm" name="method" value="addRiskControlReport">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
			</button>
		</div>
		
		<div class="col-sm-1">
			<label>&nbsp;</label>
			<a class="form-control input-sm btn btn-default btn-sm" href="<c:url value='${ctx}/reports/reportSearch'/>">
            	<i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
		</div>
    	 
	</div>	
    </form:form>
</div>


