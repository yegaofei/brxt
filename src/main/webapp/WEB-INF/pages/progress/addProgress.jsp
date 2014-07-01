<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectProgress.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
    
<div class="col-sm-10">
    <h2><fmt:message key='projectProgress.title'/></h2>
	<div class="row">
		<div class="col-sm-4 form-group">
		<display:table name="investments" id="investment" class="table table-bordered table-condensed table-striped table-hover">  			
  			<display:column titleKey="projectProgress.projectName">
  				<c:url value="${ctx}/progress/investmentProjectForm" var="investmentProjectUrl">
        			<c:param name="investmentStatusId" value="${investment.id}"/>
    			</c:url>
    			<c:url value="${ctx}/progress/supplyLiqProjectForm" var="supplyLiqProjectUrl">
        			<c:param name="investmentStatusId" value="${investment.id}"/>
    			</c:url>
    			<c:if test="${investment.capitalInvestmentType.title != 'supplemental_liquidity'}">
    				<a href="<c:out value="${investmentProjectUrl}" escapeXml="false" />"><c:out value="${investment.projectName}"/> </a>
    			</c:if>
    			<c:if test="${investment.capitalInvestmentType.title == 'supplemental_liquidity'}">
    				<a href="<c:out value="${supplyLiqProjectUrl}" escapeXml="false" />"><c:out value="${investment.projectName}"/> </a>
    			</c:if>
  			</display:column>
  			<display:column sortable="false" titleKey="projectProgress.investmentType">
        		<fmt:message key="${investment.capitalInvestmentType.title}"/>
        	</display:column>
        	<display:caption><fmt:message key="projectProgress.type.investment"/></display:caption>
		</display:table>
		</div>
   
   		<div class="col-sm-4 form-group">
		<display:table name="repayments" id="repayment" class="table table-bordered table-condensed table-striped table-hover">  			
  			<display:column titleKey="projectProgress.projectName">   
  				<c:url value="${ctx}/progress/repaymentProjectForm" var="repaymentProjectUrl">
        			<c:param name="investmentStatusId" value="${repayment.id}"/>
    			</c:url>
    			<a href="<c:out value="${repaymentProjectUrl}" escapeXml="false" />"><c:out value="${repayment.projectName}"/></a>
  			</display:column>
  			<display:column sortable="false" titleKey="projectProgress.investmentType">
        		<fmt:message key="${repayment.capitalInvestmentType.title}"/>
        	</display:column>
        	<display:caption><fmt:message key="projectProgress.type.repayment"/></display:caption>
		</display:table>
     </div>
     </div>
     
     
</div>
