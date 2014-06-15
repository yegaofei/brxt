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
        <a href="<c:url value='/projectInfo'/>"><fmt:message key="menu.projectInfo.list"/></a>
    </li>
    <li>
        <a href="<c:url value='/projectInfoForm'/>"><fmt:message key="menu.projectInfo.add"/></a>
    </li>
    <li>
        <a href="<c:url value='/collateral/collateralList'/>"><fmt:message key="collateral.title"/></a>
    </li>
</ul>
</body>
