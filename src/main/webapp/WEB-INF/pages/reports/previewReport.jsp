<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="report.riskcontrol.title"/></title>	
</head>

<div class="col-lg-12">
	<ul class="breadcrumb" style="margin-bottom: 5px;">
  		<li><a href="<c:url value='${ctx}/reports/reportSearch' />"><fmt:message key="report.search.heading"/></a></li>
  		<li><a href="<c:url value='${ctx}/reports/riskControlReport'>
					<c:param name="id" value="${riskControlReport.projectInfo.id}"/>
        			<c:param name="reportId" value="${riskControlReport.id}"/>
				</c:url>"><fmt:message key="button.edit"/></a></li>
  		<li class="active"><fmt:message key="button.preview"/></li>
	</ul>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab1"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab1.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="subjectCapacity.heading"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab2.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.financeOtherCheck"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab2-1.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab3"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab3.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab4"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab4.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab5"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab5.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab6"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab6.jsp" %>
  		</div>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab7"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab7.jsp" %>
	</div>
	
	<div class="panel panel-default">
  		<div class="panel-heading"><fmt:message key="report.riskcontrol.tab8"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab8.jsp" %>
  		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-lg-3 col-lg-offset-9">
		<c:if test="${fn:contains(user.roles, 'ROLE_RISK_OPERATOR') or fn:contains(user.roles, 'ROLE_ADMIN')}">
			<a class="btn btn-primary" href="<c:url value='${ctx}/reports/commitReport'>
						<c:param name="reportId" value="${param.reportId}"/>
				</c:url>">
            	<i class="icon-ok"></i> <fmt:message key="button.commit"/>
            </a>
		</c:if>
		
		<c:if test="${fn:contains(user.roles, 'ROLE_RISK_MANAGER') }">			
		<button type="submit" class="btn btn-primary" name="method" value="AuditReport" >
			<i class="icon-ok icon-white"></i> <fmt:message key="button.audit"/>
		</button>	
		</c:if>
	</div>
</div>
