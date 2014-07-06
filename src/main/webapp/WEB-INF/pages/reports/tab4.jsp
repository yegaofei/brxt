<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<div class="form-group">
		<label for="repaymentEvaluation" class="col-lg-2 control-label"><fmt:message key="repaymentProject.evaluation" /></label>
		<div class="col-lg-10">
			<textarea class="form-control" rows="4" id="repaymentEvaluation" name="repaymentEvaluation"><c:out value="${riskControlReport.repaymentEvaluation}" /></textarea>			
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-lg-1 col-lg-offset-11">
			<button type="submit" class="btn btn-primary form-control" style="width:50px;" name="method" value="SaveTab4" onclick="$('#activeTab').val('tab4')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>
	</div>
	</fieldset>
</div>
</div>


<div class="col-lg-12">
<div class="form-group">		
	<c:if test="${not empty repaymentProjects}">
		<c:forEach var="repaymentProject" items="${repaymentProjects}" varStatus="status">
			<div class="page-header">
				<h4>
					<fmt:message key="repaymentProject.name" />:
					<c:out value="${repaymentProject.investmentStatus.projectName}" /> 
				</h4>
			</div>
			<table class="table table-striped table-bordered table-hover">
						<thead>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="repaymentProject.totalSize"/></td>
							<td><c:out value="${repaymentProject.totalSize}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.totalSaleSize"/></td>
							<td><c:out value="${repaymentProject.totalSaleSize}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.permitSaleSize"/></td>
							<td>
								<c:out value="${repaymentProject.permitSaleSize}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.openDate"/></td>
							<td>
								<fmt:formatDate value="${repaymentProject.openDate}" pattern="${datePattern}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.preSaleSize"/></td>
							<td>
								<c:out value="${repaymentProject.preSaleSize}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.salePercentRate"/></td>
							<td>
								<c:out value="${repaymentProject.salePercentRate}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.preAvgPrice"/></td>
							<td>
								<fmt:formatNumber type="currency"><c:out value="${repaymentProject.preAvgPrice}" /></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.currAvgPrice"/></td>
							<td>
								<fmt:formatNumber type="currency"><c:out value="${repaymentProject.currAvgPrice}" /></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.comments"/></td>
							<td>
								<c:out value="${repaymentProject.comments}" />
							</td>
						</tr>
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
</div>
</div>
