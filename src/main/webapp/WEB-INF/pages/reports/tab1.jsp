<div class="col-sm-8">
                  		<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.nonrequired.projectName"/>:
                  			<c:out value="${projectInfo.projectName}" />
                  		</div>
                  		<div class="form-group">
	                  		<appfuse:label styleClass="control-label" key="projectSize.title"/>:
							<display:table name="projectInfo.projectSizes" id="projectSize" class="table table-condensed table-bordered  table-striped table-hover">
  								<display:column titleKey="projectSize.startTime">
    								<fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" />
  								</display:column>
  								<display:column titleKey="projectSize.endTime">
      								<fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" />
  								</display:column>
  								<display:column property="projectSize" titleKey="projectSize.size" />      			
							</display:table>
     					</div>
     					<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.expectedReturn"/>:
                  			<c:out value="${projectInfo.expectedReturn}" />%
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
                  		<display:table name="repaymentList" class="table table-condensed table-bordered table-striped table-hover" id="repayment">
        					<display:column sortable="false" titleKey="repayment.repaymentTime">
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
                  