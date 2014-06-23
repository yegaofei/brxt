<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>

<ul class="glassList">
    <li>
        <a href="<c:url value='${ctx}/projectInfo'/>"><fmt:message key="menu.projectInfo.list"/></a>
    </li>
    <li>
        <a href="<c:url value='${ctx}/projectInfoForm'/>"><fmt:message key="menu.projectInfo.add"/></a>
    </li>
    <li>
        <a href="<c:url value='${ctx}/collateral/collateralList'/>"><fmt:message key="collateral.title"/></a>
    </li>
    <li>
        <a href="<c:url value='${ctx}/reports/reportSearch'/>"><fmt:message key="menu.projectInfo.report"/></a>
    </li>
</ul>
</body>
