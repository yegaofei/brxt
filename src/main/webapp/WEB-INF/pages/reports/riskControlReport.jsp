<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.riskcontrol.title"/></title>
</head>

	<div class="col-lg-15">
            <h2 id="nav-tabs"><fmt:message key="report.riskcontrol.heading"/></h2>
            <div class="bs-example">
              <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#tab1" data-toggle="tab"><fmt:message key="report.riskcontrol.tab1"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab2"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab3"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab4"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab5"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab6"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab7"/></a></li>
                <li><a href="#profile" data-toggle="tab"><fmt:message key="report.riskcontrol.tab8"/></a></li>
                
              </ul>
              <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade active in" id="tab1">
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
                  		<display:table name="repaymentList" class="table table-condensed table-striped table-hover" requestURI="" id="repayment" export="false" pagesize="25">
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
                <div class="tab-pane fade" id="profile">
                  <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit.</p>
                </div>
                <div class="tab-pane fade" id="dropdown1">
                  <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork.</p>
                </div>
                <div class="tab-pane fade" id="dropdown2">
                  <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater.</p>
                </div>
              </div>
            </div>
	</div> 

  
