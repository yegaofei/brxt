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
                  <div class="col-sm-8">
                  		<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.nonrequired.projectName"/>:
                  			<c:out value="${projectInfo.projectName}" />
                  		</div>
                  		<div class="form-group">
	                  		<appfuse:label styleClass="control-label" key="projectSize.title"/>:
							<display:table name="projectInfo.projectSizes" id="projectSize" class="table table-condensed table-striped table-hover">
  								<display:column titleKey="projectSize.startTime">
    								<fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" />
  								</display:column>
  								<display:column property="projectSize" titleKey="projectSize.size" />      			
  								<display:column titleKey="projectSize.endTime">
      								<fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" />
  								</display:column>
							</display:table>
     					</div>
     					<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.expectedReturn"/>:
                  			<c:out value="${projectInfo.expectedReturn}" />
                  		</div>
                  		
                  		<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.fundUsage"/>:
                  			<c:out value="${projectInfo.fundUsage}" />
                  		</div>
                  		
                  		<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>:
                  			<c:out value="${projectInfo.trustManager}" />
                  		</div>
                  		
                  		<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.guaranteeMode"/>:
                  			<c:out value="${projectInfo.guaranteeMode}" />
                  		</div>
                  		
                  		<appfuse:label styleClass="control-label" key="repayment.heading"/>:
                  		<display:table name="repaymentList" class="table table-condensed table-striped table-hover" id="repayment">
        					<display:column sortable="true" titleKey="repayment.repaymentTime">
        						<fmt:formatDate value="${repayment.repaymentTime}" pattern="${datePattern}" />
        					</display:column>
        					<display:column sortable="false" titleKey="repayment.type">
        						<fmt:message key="${repayment.type}"/>
        					</display:column>
        					<display:column property="amount" sortable="false" titleKey="repayment.amount"/>
        					<display:column property="comment" sortable="false" titleKey="repayment.comment"/>
        					<display:setProperty name="paging.banner.item_name"><fmt:message key="repayment.heading"/></display:setProperty>
        					<display:setProperty name="paging.banner.items_name"><fmt:message key="repayment.heading"/></display:setProperty>
    					</display:table>                  	
                  </div>
                </div>
                <div class="tab-pane fade <c:if test="${param.activeTab == 'tab2'}"> active in </c:if>" id="tab2">
                	<div class="form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
						<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
							<c:if test="${status.first}"><select id="counterparties" name="counterparties" class="form-control"></c:if>
							<option value="${counterparty.id}" <c:if test = "${counterparty.id == subjectCapacity.counterparty.id}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>
					<div class="form-group">
					<appfuse:label styleClass="control-label" key="subjectCapacity.heading"/> :
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th> 
							<th><fmt:message key="subjectCapacity.comments"/></th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="subjectCapacity.licenseVerificationDate"/></td>
							<td><c:out value="${subjectCapacity.licenseVerificationDate}" /></td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.orgCodeVerificationDate"/></td>
							<td><c:out value="${subjectCapacity.orgCodeVerificationDate}" /></td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.loanCardValid"/></td>
							<td>
								<c:if test="${subjectCapacity.loanCardValid}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.loanCardValid}"><fmt:message key="label.no"/></c:if>
							 </td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.nameChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.nameChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.nameChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.ownerChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.ownerChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.ownerChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.ownershipChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.ownershipChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.ownershipChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.capitalChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.capitalChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.capitalChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.bizScopeChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.bizScopeChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.bizScopeChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.otherBigChanges"/></td>
							<td>
								<c:if test="${subjectCapacity.otherBigChanges}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.otherBigChanges}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						</tbody>
					</table>
					</div>
                </div>
            
                <div class="tab-pane fade" id="tab2-1">
                  <p><fmt:message key="report.riskcontrol.financeCheck"/></p>
                </div>
                <div class="tab-pane fade" id="tab2-2">
                  <p><fmt:message key="report.riskcontrol.otherCheck"/></p>
                  
                  
                  
                </div>
              </div>
          </form>
            </div>
	</div> 

<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#counterparties').change(function(){
		var counterpartyId=$(this).children('option:selected').val(); 
		$('#counterpartyId').val(counterpartyId);
		$('#activeTab').val("tab2");
		$('#riskControlReport').submit();
	})
})

</script>

  
