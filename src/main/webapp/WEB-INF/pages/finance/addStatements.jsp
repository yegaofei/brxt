<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="financialStatements.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
    
<div class="col-sm-10">
    <h2><fmt:message key='financialStatements.title'/></h2>
    <span class="pagebanner"><fmt:message key="financialStatements.heading"/></span>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="projectInfo" method="post" action="${ctx}/finance/financialStatements" id="projectInfoForm" cssClass="well">
    <form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<div class="row">
		<div class="col-sm-4 form-group">
		<display:table name="projectInfo.counterparties" id="counterparty" class="table table-condensed table-striped table-hover">  			
  			<display:column titleKey="projectInfo.counterparty.name">
  				<c:url value="/finance/addStatements" var="counterpartyUrl">
        			<c:param name="counterpartyId" value="${counterparty.id}"/>
        			<c:param name="counterpartyType" value="${counterparty.counterpartyType}"/>
    			</c:url>
    			<a href="<c:out value="${counterpartyUrl}" escapeXml="false" />"><c:out value="${counterparty.name}"/> </a>
  			</display:column>
  			<display:column titleKey="projectInfo.counterparty.type">
       			<fmt:message key="${counterparty.counterpartyType}"/>
  			</display:column>
		</display:table>
		</div>
   
   		<div class="col-sm-2 form-group">
   			<span></span>
   		</div>
   		
   		<div class="col-sm-4 form-group">
		<display:table name="projectInfo.guarantors" id="guarantor" class="table table-condensed table-striped table-hover">  			
  			<display:column titleKey="projectInfo.guarantor.name">   
  				<c:url value="/finance/addStatements" var="guarantorUrl">
        			<c:param name="guarantorId" value="${guarantor.id}"/>
        			<c:param name="counterpartyType" value="${guarantor.counterpartyType}"/>
    			</c:url>
    			<a href="<c:out value="${guarantorUrl}" escapeXml="false" />"><c:out value="${guarantor.name}"/></a>
  			</display:column>
  			<display:column titleKey="projectInfo.guarantor.type">
       			<fmt:message key="${guarantor.counterpartyType}"/>
  			</display:column>
		</display:table>
     </div>
     </div>
     
     </form:form>
    
</div>
