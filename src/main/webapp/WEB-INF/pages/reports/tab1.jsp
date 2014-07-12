<div class="col-lg-12">
	<div class="<c:if test='${empty param.preview }'>well</c:if>">
					<div class="row">
                  			<label class="col-sm-4"><fmt:message key="projectInfo.nonrequired.projectName" /> : <c:out value="${projectInfo.projectName}" /></label>
                  			<label class="col-sm-4"><fmt:message key="projectInfo.expectedReturn" /> : <c:out value="${projectInfo.expectedReturn}" />%</label>
                  			<label class="col-sm-4"><fmt:message key="projectInfo.trustManager" /> : <c:out value="${projectInfo.trustManager}" /></label>
                  	</div>	
                  	<div class="row">
                  			<label class="col-lg-9"><fmt:message key="projectSize.title" /></label>
	                  		<div class="col-lg-6">
							<display:table name="projectInfo.projectSizes" id="projectSize" class="table table-condensed table-bordered  table-striped table-hover">
  								<display:column titleKey="projectSize.startTime">
    								<fmt:formatDate value="${projectSize.startTime}" pattern="${datePattern}" />
  								</display:column>
  								<display:column titleKey="projectSize.endTime">
      								<fmt:formatDate value="${projectSize.endTime}" pattern="${datePattern}" />
  								</display:column>
  								<display:column titleKey="projectSize.size">
  									<fmt:formatNumber type="currency" value="${projectSize.projectSize}" />
  								</display:column>      			
							</display:table>
							</div>
     				</div>
     					
                  	<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.fundUsage"/>:
                  			<c:out value="${projectInfo.fundUsage}" />
                  	</div>
                  		
                  	<div class="form-group">
                  			<appfuse:label styleClass="control-label" key="projectInfo.guaranteeMode"/>:
                  			<c:out value="${projectInfo.guaranteeMode}" />
                  	</div>
                  		
                  		
                  	<div class="row">
                  		<label class="col-lg-9"><fmt:message key="repayment.heading" /></label>
                  		<div class="col-lg-9">
                  		<display:table name="repaymentList" class="table table-condensed table-bordered table-striped table-hover" id="repayment">
        					<display:column sortable="false" titleKey="repayment.repaymentTime">
        						<fmt:formatDate value="${repayment.repaymentTime}" pattern="${datePattern}" />
        					</display:column>
        					<display:column sortable="false" titleKey="repayment.type">
        						<fmt:message key="${repayment.type}"/>
        					</display:column>
        					<display:column sortable="false" titleKey="repayment.amount">
        						<fmt:formatNumber type="currency" value="${repayment.amount}" />
        					</display:column>
        					<display:column property="comment" sortable="false" titleKey="repayment.comment"/>
        					<display:setProperty name="paging.banner.item_name"><fmt:message key="repayment.heading"/></display:setProperty>
        					<display:setProperty name="paging.banner.items_name"><fmt:message key="repayment.heading"/></display:setProperty>
    					</display:table>  
    					</div>
    				</div>
                  </div>
</div>
