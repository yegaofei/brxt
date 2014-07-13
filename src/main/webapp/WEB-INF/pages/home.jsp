<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>

<ul class="glassList">
<menu:useMenuDisplayer name="Velocity" config="navMainMenu.vm" permissions="rolesAdapter">
    <menu:displayMenu name="ProjectInfoMenu"/>
    <menu:displayMenu name="Collateral"/>
    <menu:displayMenu name="RiskControlReport"/>
</menu:useMenuDisplayer>
</ul>
</body>
