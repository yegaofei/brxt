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
        <a class="btn btn-primary disabled" href="<c:url value='${ctx}/projectInfoForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.new"/></a>
        <a class="btn btn-default disabled" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.modify"/></a>
        <a class="btn btn-default disabled" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.commit"/></a>
        <a class="btn btn-default disabled" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.audit"/></a>
        <a class="btn btn-default disabled" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.export"/></a>
        <a class="btn btn-default disabled" href="<c:url value='{ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.print"/></a>                
    </div>
    
    	<%@ include file="../searchForm.jsp" %>
    	
    <display:table name="projectInfoList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="projectInfo" export="false" pagesize="20">
        <display:column property="id" media="csv excel xml pdf" titleKey="projectInfo.id"/>
        <display:column property="projectName" sortable="true" href="${ctx}/reports/riskControlReport" media="html"
            paramId="id" paramProperty="id"  titleKey="projectInfo.projectName"/>
        <display:column titleKey="report.offsite.committed">
        	<c:if test="${projectInfo.projectInfoStatus.committed}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.committed}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
        </display:column>
        <display:column titleKey="report.riskcontrol.committed">
        	<c:if test="${projectInfo.riskControlReport.reportStatus.commitReport}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not projectInfo.riskControlReport.reportStatus.commitReport}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
        </display:column>
        <display:column titleKey="report.riskcontrol.audit">
        	<c:if test="${projectInfo.riskControlReport.reportStatus.reportAudit}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not projectInfo.riskControlReport.reportStatus.reportAudit}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
         </display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="projectInfoList.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="projectInfoList.projects"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="projectInfoList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="projectInfoList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="projectInfoList.title"/>.pdf</display:setProperty>
    </display:table>
</div>


