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
		
		<c:if test="${fn:contains(user.roles, 'ROLE_RISK_MANAGER') and not empty riskControlReport.reportStatus.commitReport and riskControlReport.reportStatus.commitReport}">			
		<button class="btn btn-primary" data-toggle="modal" data-target="#auditModal">
            <fmt:message key="button.audit"/>
        </button>
		</c:if>
		
		<c:if test="${not empty riskControlReport.reportStatus.reportAudit and riskControlReport.reportStatus.reportAudit}">
		  <a class="btn btn-primary" href="<c:url value='${ctx}/reports/generateReport'>
						<c:param name="reportId" value="${param.reportId}"/>
				</c:url>">
            	<i class="icon-ok"></i> <fmt:message key="button.pdf"/>
            </a>
        </c:if>
	</div>

<!-- Modal -->
<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="auditModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="auditModalLabel"><fmt:message key="report.audit.comments" /></h4>
      </div>
      <form method="post" action="${ctx}/reports/auditCommentForm" >
      <input type="hidden" name="reportId" value="<c:out value="${param.reportId}" />" >
      <div class="modal-body">
        <div class="input-group">
            <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="true" <c:if test="${riskControlReport.reportStatus.reportAudit == 'Y'}">checked</c:if>><fmt:message key='report.audit.pass'/> 
            </label>
            <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="false" <c:if test="${riskControlReport.reportStatus.reportAudit == 'N'}">checked</c:if>><fmt:message key='report.audit.fail'/>
            </label>
            <textarea id="auditComment" name="auditComment" autofocus="true" cols="60" class="form-control input-sm"><c:out value="${riskControlReport.reportStatus.auditComment}"/></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="button.close" /></button>
        <button type="submit" class="btn btn-primary" name="method" value="AuditReport" >
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save" />
        </button>   
      </div>
      </form>
    </div>
  </div>
</div>
</div>
