<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="subjectCapacity.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='subjectCapacity.heading'/></h2>
    
 	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/subjectCapacityForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/projectInfoForm?id=${sessionScope.project_info_id}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
 
    <display:table name="subjectCapacityList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="subjectCapacityList" export="false" pagesize="25">
        <display:column property="checkTime" format="{0,date,yyyy-MM}" sortable="true" titleKey="subjectCapacity.checkTime"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="subjectCapacity.id"/>
        <display:column property="counterparty.name" sortable="true" href="/subjectCapacityForm" media="html"
            paramId="id" paramProperty="id"  titleKey="projectInfo.counterparty.name"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="subjectCapacity.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="subjectCapacity.heading"/></display:setProperty>
        <display:setProperty name="export.excel.filename"><fmt:message key="subjectCapacity.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="subjectCapacity.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="subjectCapacity.title"/>.pdf</display:setProperty>
    </display:table>
    
</div>
