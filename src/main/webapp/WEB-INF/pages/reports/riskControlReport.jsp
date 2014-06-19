<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.riskcontrol.title"/></title>
</head>

	<div class="col-lg-15">
            <h2 id="nav-tabs"><fmt:message key="report.riskcontrol.heading"/></h2>
            <div class="bs-example">
              <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li <c:if test="${param.activeTab == 'tab1' or empty param.activeTab}"> class="active" </c:if>><a href="#tab1" data-toggle="tab"><fmt:message key="report.riskcontrol.tab1"/></a></li>
                <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <fmt:message key="report.riskcontrol.tab2"/> <span class="caret"></span> </a>
                  <ul class="dropdown-menu">
                    <li <c:if test="${param.activeTab == 'tab2'}"> class="active" </c:if>><a href="#tab2" data-toggle="tab"><fmt:message key="subjectCapacity.heading"/></a></li>                    
                    <li><a href="#tab2-1" data-toggle="tab"><fmt:message key="report.riskcontrol.financeCheck"/></a></li>
                    <li><a href="#tab2-2" data-toggle="tab"><fmt:message key="report.riskcontrol.otherCheck"/></a></li>
                  </ul>
                </li>
                
                <li <c:if test="${param.activeTab == 'tab3'}"> class="active" </c:if>><a href="#tab3" data-toggle="tab"><fmt:message key="report.riskcontrol.tab3"/></a></li>
                <li <c:if test="${param.activeTab == 'tab4'}"> class="active" </c:if>><a href="#tab4" data-toggle="tab"><fmt:message key="report.riskcontrol.tab4"/></a></li>
                <li <c:if test="${param.activeTab == 'tab5'}"> class="active" </c:if>><a href="#tab5" data-toggle="tab"><fmt:message key="report.riskcontrol.tab5"/></a></li>
                <li <c:if test="${param.activeTab == 'tab6'}"> class="active" </c:if>><a href="#tab6" data-toggle="tab"><fmt:message key="report.riskcontrol.tab6"/></a></li>
                <li <c:if test="${param.activeTab == 'tab7'}"> class="active" </c:if>><a href="#tab7" data-toggle="tab"><fmt:message key="report.riskcontrol.tab7"/></a></li>
                <li <c:if test="${param.activeTab == 'tab8'}"> class="active" </c:if>><a href="#tab8" data-toggle="tab"><fmt:message key="report.riskcontrol.tab8"/></a></li>
              </ul>
              
              <form name="riskControlReport" id="riskControlReport" action="${ctx}/reports/riskControlReport" method="post">
              	<input type="hidden" name="id" value="<c:out value="${param.id}" />" >
              	<input type="hidden" name="counterpartyId" id="counterpartyId" value="<c:out value="${param.counterpartyId}" />" >
              	<input type="hidden" name="activeTab" id="activeTab" value="<c:out value="${param.activeTab}" />" >
              
              <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab1' or empty param.activeTab}"> active in </c:if>" id="tab1">
                  	<%@ include file="./tab1.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab2'}"> active in </c:if>" id="tab2">
                	<%@ include file="./tab2.jsp" %>
                </div>
                <div class="tab-pane fade" id="tab2-1">
                  <p><fmt:message key="report.riskcontrol.financeCheck"/></p>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab2-2'}"> active in </c:if>" id="tab2-2">
                  <%@ include file="./tab2-2.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab3'}"> active in </c:if>" id="tab3">
                	<%@ include file="./tab3.jsp" %>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab4'}"> active in </c:if>" id="tab4">
                	<%@ include file="./tab4.jsp" %>
                </div>
              </div>
          	  </form>
            </div>
	</div> 


  
