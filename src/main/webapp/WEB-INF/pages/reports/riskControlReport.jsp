<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.riskcontrol.title"/></title>
	<link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
    
</head>
	<div class="col-lg-12">
			<ul class="breadcrumb" style="margin-bottom: 5px;">
  				<li><a href="<c:url value='${ctx}/reports/reportSearch' />"><fmt:message key="report.search.heading"/></a></li>
  				<li class="active"><fmt:message key="button.edit"/></li>
  				<li><a href="<c:url value='${ctx}/reports/previewReport'>
					<c:param name="reportId" value="${param.reportId}"/>
					<c:param name="id" value="${param.id}"/>
					<c:param name="preview" value="true"/>
				</c:url>"><fmt:message key="button.preview"/></a></li>
			</ul>		
			<form name="riskControlReport" id="riskControlReport" action="${ctx}/reports/riskControlReport" method="post">
            <div class="bs-example">
              <ul class="nav nav-tabs" style="margin-bottom: 5px;">
                <li <c:if test="${param.activeTab == 'tab1' or empty param.activeTab}"> class="active" </c:if>><a href="#tab1" data-toggle="tab"><fmt:message key="report.riskcontrol.tab1"/></a></li>
                <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <fmt:message key="report.riskcontrol.tab2"/> <span class="caret"></span> </a>
                  <ul class="dropdown-menu">
                    <li><a href="#tab2" data-toggle="tab"><fmt:message key="subjectCapacity.heading"/></a></li>                    
                    <li><a href="#tab2-1" data-toggle="tab"><fmt:message key="report.riskcontrol.financeOtherCheck"/></a></li>
                  </ul>
                </li>
                <c:if test="${riskControlReport.projectInfo.projectType == 'non_transaction_management' }">
                    <li <c:if test="${param.activeTab == 'tab3'}"> class="active" </c:if>><a href="#tab3" data-toggle="tab"><fmt:message key="report.riskcontrol.tab3"/></a></li>
                    <li <c:if test="${param.activeTab == 'tab4'}"> class="active" </c:if>><a href="#tab4" data-toggle="tab"><fmt:message key="report.riskcontrol.tab4"/></a></li>
                    <li <c:if test="${param.activeTab == 'tab5'}"> class="active" </c:if>><a href="#tab5" data-toggle="tab"><fmt:message key="report.riskcontrol.tab5"/></a></li>
                </c:if>
                <li <c:if test="${param.activeTab == 'tab6'}"> class="active" </c:if>><a href="#tab6" data-toggle="tab"><fmt:message key="report.riskcontrol.tab6"/></a></li>
                <c:if test="${riskControlReport.projectInfo.projectType == 'non_transaction_management' }">
                    <li <c:if test="${param.activeTab == 'tab7'}"> class="active" </c:if>><a href="#tab7" data-toggle="tab"><fmt:message key="report.riskcontrol.tab7"/></a></li>
                </c:if>
                <li <c:if test="${param.activeTab == 'tab8'}"> class="active" </c:if>><a href="#tab8" data-toggle="tab"><fmt:message key="report.riskcontrol.tab8"/></a></li>
              </ul>
              
              	<input type="hidden" name="id" value="<c:out value="${param.id}" />" >
              	<input type="hidden" name="reportId" value="<c:out value="${param.reportId}" />" >
              	<input type="hidden" name="counterpartyId" id="counterpartyId" value="<c:out value="${param.counterpartyId}" />" >
              	<input type="hidden" name="guarantorId" id="guarantorId" value="<c:out value="${param.guarantorIdTab6}" />" >
              	<input type="hidden" name="activeTab" id="activeTab" value="<c:out value="${param.activeTab}" />" >
              <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab1' or empty param.activeTab}"> active in </c:if>" id="tab1">
                  	<%@ include file="./tab1.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab2'}"> active in </c:if>" id="tab2">
                	<%@ include file="./tab2.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab2-1'}"> active in </c:if>" id="tab2-1">
                  <%@ include file="./tab2-1.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab3'}"> active in </c:if>" id="tab3">
                	<%@ include file="./tab3.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab4'}"> active in </c:if>" id="tab4">
                	<%@ include file="./tab4.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab5'}"> active in </c:if>" id="tab5">
                	<%@ include file="./tab5.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab6'}"> active in </c:if>" id="tab6">
                	<%@ include file="./tab6.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab7'}"> active in </c:if>" id="tab7">
                	<%@ include file="./tab7.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab8'}"> active in </c:if>" id="tab8">
                	<%@ include file="./tab8.jsp" %>
                </div>
              </div>
          	  </form>
            </div>
	</div> 


  
