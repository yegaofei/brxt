<c:if test="${empty param.preview}">
<div class="col-lg-12">
<div class="well form-horizontal well-sm">
	<fieldset>
		<div class="col-lg-12">
		<div class="form-group">
			<label for="counterparties" class="col-lg-3 control-label"><fmt:message key="projectInfo.counterparty.name"/></label>
			<div class="col-lg-4">
			<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
				<c:if test="${status.first}">
								<select id="counterpartiesTab2" name="counterpartiesTab2" class="form-control  input-sm">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
				</c:if>
				<option value="${counterparty.id}" <c:if test = "${counterparty.id == param.counterpartiesTab2}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
				<c:if test="${status.last}"></select></c:if>
			</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
				<label for="checkTime" class="col-lg-3 control-label"><fmt:message key="subjectCapacity.checkTime" /></label>
				<div class="col-lg-3">
					<input type="text" id="checkTime" name="checkTime"  value="<c:out value='${param.checkTime}' />"  class="form-control  input-sm" >
				</div>
		</div>
		</div>
		
		<div class="form-group">
				<div class="col-lg-3 col-lg-offset-9">
			 			<button type="submit" class="btn btn-primary" name="method" value="SubjectCapacityCheck" onclick="$('#activeTab').val('tab2')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
						</button>
					
						<button type="submit" class="btn btn-primary" name="method" value="SaveTab2" onclick="$('#activeTab').val('tab2')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
						</button>	
				</div>
		</div>
			
	</fieldset>
</div>
</div>

<c:if test="${not empty subjectCapacity}" >
<div class="col-lg-12">			
					<div class="form-group">
					<div class="page-header">
							<h4>
								<fmt:message key="subjectCapacity.heading" />:
								<c:out value="${subjectCapacity.counterparty.name}" /> 
								<fmt:formatDate value="${subjectCapacity.checkTime}" pattern="${shortDatePattern}" />
							</h4>
					</div>
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th> 
							<th><fmt:message key="subjectCapacity.comments"/></th>
						</tr>
						</thead>
						<tbody>
						<c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">   
						<tr>
							<td><fmt:message key="subjectCapacity.licenseVerificationDate"/></td>
							<td><c:out value="${subjectCapacity.licenseVerificationDate}" /></td>
							<td></td>
						</tr>
						</c:if>
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
						<c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">   
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
						</c:if>
						<c:if test="${subjectCapacity.counterparty.counterpartyType == 'real_estate_firm'}">        
                        <tr>
                            <td><fmt:message key="subjectCapacity.devCapacityChanged"/></td>
                            <td>
                                <c:if test="${subjectCapacity.devCapacityChanged}"><fmt:message key="label.yes"/></c:if>
                                <c:if test="${not subjectCapacity.devCapacityChanged}"><fmt:message key="label.no"/></c:if>
                            </td>
                            <td><c:out value="${subjectCapacity.comment_dcc}" /></td>
                        </tr>
                        </c:if> 
                        <c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">     
						<tr>
							<td><fmt:message key="subjectCapacity.bizScopeChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.bizScopeChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.bizScopeChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						</c:if>
						<tr>
							<td><fmt:message key="subjectCapacity.otherBigChanges"/></td>
							<td>
								<c:if test="${subjectCapacity.otherBigChanges}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.otherBigChanges}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<c:if test="${subjectCapacity.counterparty.counterpartyType == 'institution'}">       
                        <tr>
                            <td><fmt:message key="subjectCapacity.verifyResults"/></td>
                            <td>
                                <c:if test="${subjectCapacity.verifyResults}"><fmt:message key="label.yes"/></c:if>
                                <c:if test="${not subjectCapacity.verifyResults}"><fmt:message key="label.no"/></c:if>
                            </td>
                            <td><c:out value="${subjectCapacity.comment_vr}" /></td>
                        </tr>
                        </c:if> 
						</tbody>
					</table>
					</div>
</div>		
</c:if>	
</c:if>	

<c:if test="${empty subjectCapacity}">
	<c:if test="${empty riskControlReport.subjectCapacities and not empty param.preview and param.preview}">
		<div class="col-lg-12">
		<div class="alert alert-danger alert-dismissable">
            <a href="#" data-dismiss="alert" class="close">&times;</a>
			<fmt:message key="report.subjectCapacities.empty" />
		</div>
		</div>
	</c:if>
	<c:forEach var="subjectCapacity" items="${riskControlReport.subjectCapacities}">
			<div class="col-lg-12">			
					<div class="form-group">
					   <div>
							<legend class="accordion-heading">
								<a data-toggle="collapse" href="#collapse-address<c:out value="${subjectCapacity.id}"/>">
								    <fmt:message key="subjectCapacity.heading" />:
								    <c:out value="${subjectCapacity.counterparty.name}" /> 
								    <fmt:formatDate value="${subjectCapacity.checkTime}" pattern="${shortDatePattern}" />
								</a>
							</legend>
					<div id="collapse-address<c:out value="${subjectCapacity.id}"/>" class="accordion-body collapse">
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th> 
							<th><fmt:message key="subjectCapacity.comments"/></th>
						</tr>
						</thead>
						<tbody>
						<c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">   
						<tr>
							<td><fmt:message key="subjectCapacity.licenseVerificationDate"/></td>
							<td><c:out value="${subjectCapacity.licenseVerificationDate}" /></td>
							<td></td>
						</tr>
						</c:if>
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
						<c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">   
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
						</c:if>
						<c:if test="${subjectCapacity.counterparty.counterpartyType == 'real_estate_firm'}">        
                        <tr>
                            <td><fmt:message key="subjectCapacity.devCapacityChanged"/></td>
                            <td>
                                <c:if test="${subjectCapacity.devCapacityChanged}"><fmt:message key="label.yes"/></c:if>
                                <c:if test="${not subjectCapacity.devCapacityChanged}"><fmt:message key="label.no"/></c:if>
                            </td>
                            <td><c:out value="${subjectCapacity.comment_dcc}" /></td>
                        </tr>
                        </c:if> 
                        <c:if test="${subjectCapacity.counterparty.counterpartyType != 'institution'}">  
						<tr>
							<td><fmt:message key="subjectCapacity.bizScopeChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.bizScopeChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.bizScopeChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						</c:if>
						<tr>
							<td><fmt:message key="subjectCapacity.otherBigChanges"/></td>
							<td>
								<c:if test="${subjectCapacity.otherBigChanges}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.otherBigChanges}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<c:if test="${subjectCapacity.counterparty.counterpartyType == 'institution'}">       
                        <tr>
                            <td><fmt:message key="subjectCapacity.verifyResults"/></td>
                            <td>
                                <c:if test="${subjectCapacity.verifyResults}"><fmt:message key="label.yes"/></c:if>
                                <c:if test="${not subjectCapacity.verifyResults}"><fmt:message key="label.no"/></c:if>
                            </td>
                            <td><c:out value="${subjectCapacity.comment_vr}" /></td>
                        </tr>
                        </c:if> 
						</tbody>
					</table>
					</div>
					</div>
					</div>
				</div>		
	</c:forEach> 
</c:if>

<script>
  $(function() {
    $('#checkTime').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});	
  });
  
</script>
					