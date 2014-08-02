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
	
	<c:if test="${fn:contains(tabList, 'Tab1')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab1"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab1"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab1.jsp" %>
  		</div>
	</div>
	</c:if>
	
	<c:if test="${fn:contains(tabList, 'Tab2')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab2"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab2"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab2.jsp" %>
  		    <%@ include file="./tab2-1.jsp" %>
  		</div>
	</div>
	</c:if>

	<c:if test="${riskControlReport.projectInfo.projectType == 'non_transaction_management' }">
	<c:if test="${fn:contains(tabList, 'Tab3')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab3"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab3"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab3.jsp" %>
  		</div>
	</div>
	</c:if>
	</c:if>
	
	<c:if test="${fn:contains(tabList, 'Tab6')}" >
	<div class="panel panel-default">
        <div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab6"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab6"/></div>
        <div class="panel-body">
            <%@ include file="./tab6.jsp" %>
        </div>
    </div>
    </c:if>
    
	<c:if test="${riskControlReport.projectInfo.projectType == 'non_transaction_management' }">
	<c:if test="${fn:contains(tabList, 'Tab4')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab4"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab4"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab4.jsp" %>
  		</div>
	</div>
	</c:if>
	
	<c:if test="${fn:contains(tabList, 'Tab5')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab5"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab5"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab5.jsp" %>
  		</div>
	</div>
	</c:if>
	</c:if>
	
	<c:if test="${riskControlReport.projectInfo.projectType == 'non_transaction_management' }">
	<c:if test="${fn:contains(tabList, 'Tab7')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab7"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab7"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab7.jsp" %>
	   </div>
	</div>
	</c:if>
	</c:if>
	
	<c:if test="${fn:contains(tabList, 'Tab8')}" >
	<div class="panel panel-default">
  		<div class="panel-heading"><a href="<c:url value='${ctx}/reports/removeTab'>
                        <c:param name="reportId" value="${param.reportId}"/>
                        <c:param name="id" value="${param.id}"/>
                        <c:param name="preview" value="true"/>
                        <c:param name="tabList" value="${tabList}"/>
                        <c:param name="tabId" value="Tab8"/>
                </c:url>" data-dismiss="alert" class="close">&times;</a> <fmt:message key="report.riskcontrol.tab8"/></div>
  		<div class="panel-body">
    		<%@ include file="./tab8.jsp" %>
  		</div>
	</div>
	</c:if>
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
		
		<a class="btn btn-primary" href="<c:url value='${ctx}/reports/generateReport'>
						<c:param name="reportId" value="${param.reportId}"/>
				</c:url>">
            	<i class="icon-ok"></i> <fmt:message key="button.pdf"/>
        </a>
	</div>
</div>
