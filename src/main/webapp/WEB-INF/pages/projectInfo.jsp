<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectInfoList.title"/></title>
    <meta name="menu" content="ProjectInfoMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='projectInfoList.heading'/></h2>
 
    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='${ctx}/projectInfoForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='${ctx}/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    	
    	<%@ include file="./searchForm.jsp" %>
    
    <display:table name="projectInfoList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="projectInfo" export="false" pagesize="20">
        <display:column property="id" media="csv excel xml pdf" titleKey="projectInfo.id"/>
        <display:column property="projectName" sortable="true" href="${ctx}/projectInfoForm" media="html"
            paramId="id" paramProperty="id"  titleKey="projectInfo.projectName"/>
        <display:column titleKey="subjectCapacity.title">
        	<c:if test="${projectInfo.projectInfoStatus.subjectCapacity}">
        		<fmt:message key='report.input'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.subjectCapacity}">
        		<fmt:message key='report.non.input'/>
        	</c:if>
        </display:column>
        <display:column titleKey="financeStatement.title">
        	<c:if test="${projectInfo.projectInfoStatus.financeStatement}">
        		<fmt:message key='report.input'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.financeStatement}">
        		<fmt:message key='report.non.input'/>
        	</c:if>
        </display:column>
        <display:column titleKey="creditInformation.title">
        	<c:if test="${projectInfo.projectInfoStatus.creditInformation}">
        		<fmt:message key='report.input'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.creditInformation}">
        		<fmt:message key='report.non.input'/>
        	</c:if>
        </display:column>
        <display:column titleKey="repayment.title">
        	<c:if test="${projectInfo.projectInfoStatus.repayment}">
        		<fmt:message key='report.input'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.repayment}">
        		<fmt:message key='report.non.input'/>
        	</c:if>
        </display:column>
        <display:column titleKey="projectProgress.title">
        	<c:if test="${projectInfo.projectInfoStatus.projectProgress}">
        		<fmt:message key='report.input'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.projectProgress}">
        		<fmt:message key='report.non.input'/>
        	</c:if>
        </display:column>
        <display:column titleKey="projectInfo.commit.title">
        	<c:if test="${projectInfo.projectInfoStatus.committed}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not projectInfo.projectInfoStatus.committed}">
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

