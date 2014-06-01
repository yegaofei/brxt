<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoList.title"/></title>
    <meta name="menu" content="ProjectInfoMenu"/>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='projectInfoList.heading'/></h2>
 
    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/projectInfoForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
 
    <display:table name="projectInfoList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="projectInfoList" export="true" pagesize="25">
        <display:column property="id" media="csv excel xml pdf" titleKey="projectInfo.id"/>
        <display:column property="projectName" sortable="true" href="/projectInfoForm" media="html"
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
