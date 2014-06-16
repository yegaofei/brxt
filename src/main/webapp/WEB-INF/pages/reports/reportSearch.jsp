<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.search.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='report.search.heading'/></h2>
 
    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='${ctx}/projectInfoForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.new"/></a>
        <a class="btn btn-default" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.modify"/></a>
        <a class="btn btn-default" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.commit"/></a>
        <a class="btn btn-default" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.audit"/></a>
        <a class="btn btn-default" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.export"/></a>
        <a class="btn btn-default" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.print"/></a>                
    </div>
    	<form:form commandName="projectInfo" method="post" action="${ctx}/reportSearch" id="reportSearchForm" cssClass="well">
    		<div class="row">
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.nonrequired.projectName"/>
        		<form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control"/>
        	</div>	
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.riskManager"/>
        		<form:input path="riskManager" id="riskManager" maxlength="20" cssClass="form-control"/>
    		</div>
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.createTime"/>
        		<form:input path="createTime" id="createTime" maxlength="20" cssClass="form-control"/>
    		</div>
    		</div>

			<div class="row">
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.delegateManager"/>
        		<form:input path="delegateManager" id="delegateManager" maxlength="20" cssClass="form-control"/>
    		</div>

    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>
        		<form:input path="trustManager" id="trustManager" maxlength="20" cssClass="form-control"/>
    		</div>
    		
    		<div class="col-sm-1 form-group">
    			<label></label>
    			<button type="submit" class="btn btn-primary form-control" name="method" value="SearchProjectInfo" onclick="bCancel=false">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.search"/>
        		</button>
        	</div>
        	</div>
    	</form:form>
    
    <display:table name="projectInfoList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="projectInfoList" export="false" pagesize="20">
        <display:column property="id" media="csv excel xml pdf" titleKey="projectInfo.id"/>
        <display:column property="projectName" sortable="true" href="${ctx}/reports/riskControlReport" media="html"
            paramId="id" paramProperty="id"  titleKey="projectInfo.projectName"/>
        <display:column property="expectedReturn" sortable="true" titleKey="projectInfo.expectedReturn"/>
        <display:column property="riskManager" sortable="true" titleKey="projectInfo.riskManager"/>
        <display:column property="delegateManager" sortable="true" titleKey="projectInfo.delegateManager"/>
        <display:column property="trustManager" sortable="true" titleKey="projectInfo.trustManager"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="projectInfoList.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="projectInfoList.projects"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="projectInfoList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="projectInfoList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="projectInfoList.title"/>.pdf</display:setProperty>
    </display:table>
</div>

<script>
  $(function() {
    $('#createTime').datepicker({
				language: 'zh-CN'
			});	
  });
</script>
