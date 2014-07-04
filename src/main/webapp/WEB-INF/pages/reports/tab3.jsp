<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<div class="form-group">
		<label for="policyChanges" class="col-lg-3 control-label"><fmt:message key="investmentProject.policyChanges" /></label>
		<div class="col-lg-9">
			<input type="text" name="policyChanges" id="policyChanges" class="form-control input-sm" value="${riskControlReport.policyChanges}">
		</div>
	</div>
	<div class="form-group">
		<label for="policyChanges" class="col-lg-3 control-label"><fmt:message key="investmentProject.priceChanges" /></label>
		<div class="col-lg-9">
			<textarea class="form-control" rows="4" id="priceChanges" name="priceChanges"><c:out  value="${riskControlReport.priceChanges}" /></textarea>
			<span class="help-block"><fmt:message key="investmentProject.priceChanges.help" /></span>
		</div>
	</div>
	<div class="form-group">
		<label for="investmentEvaluation" class="col-lg-3 control-label"><fmt:message key="investmentProject.evaluation" /></label>
		<div class="col-lg-9">
			<input type="text" id="investmentEvaluation" name="investmentEvaluation" class="form-control input-sm" value="${riskControlReport.investmentEvaluation}" >	
		</div>
	</div>
	<div class="form-group">
		<div class="col-lg-1 col-lg-offset-11">
			<button type="submit" class="btn btn-primary form-control" name="method" value="SaveTab3" onclick="$('#activeTab').val('tab3')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>
	</div>
	</fieldset>
</div>
</div>

<div class="col-lg-12">
	<c:if test="${not empty investmentProjects}">
		<c:forEach var="investmentProject" items="${investmentProjects}" varStatus="status">
			<div class="page-header">
				<h4>
					<fmt:message key="investmentProject.name" />:
					<c:out value="${investmentProject.investmentStatus.projectName}" /> (<fmt:message key="${investmentProject.investmentProjectType}" />)
				</h4>
			</div>
			<table class="table table-striped table-bordered table-hover">
						<thead>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="investmentProject.startTime"/></td>
							<td>
								<fmt:formatDate value="${investmentProject.startTime}" pattern="${datePattern}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.endTime"/></td>
							<td>
								<fmt:formatDate value="${investmentProject.endTime}" pattern="${datePattern}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.totalAmount"/></td>
							<td>
								<c:out value="${investmentProject.totalAmount}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.investmentAmount"/></td>
							<td>
								<c:out value="${investmentProject.investmentAmount}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.description"/></td>
							<td>
								<c:out value="${investmentProject.description}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.delayed"/></td>
							<td>
								<c:if test="${investmentProject.delayed}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not investmentProject.delayed}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
	
	<c:if test="${not empty supplyLiquidProjects}">
		<c:forEach var="supplyLiquidProject" items="${supplyLiquidProjects}" varStatus="status">
			<div class="row">
				<appfuse:label styleClass="control-label" key="supplyLiquidProject.name"/>
				<c:out value="${supplyLiquidProject.investmentStatus.projectName}" /> (<fmt:message key='supplyLiquidProject.title' />)
			</div>
			<table class="table table-striped table-bordered table-hover">
						<thead>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="supplyLiquidProject.industryVista"/></td>
							<td>
								<c:out value="${supplyLiquidProject.industryVista}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.firmSize"/></td>
							<td>
								<c:out value="${supplyLiquidProject.firmSize}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.description"/></td>
							<td>
								<c:out value="${supplyLiquidProject.description}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.saleSituation"/></td>
							<td>
								<c:out value="${supplyLiquidProject.saleSituation}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.bigChanges"/></td>
							<td>
								<c:if test="${supplyLiquidProject.bigChanges}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not supplyLiquidProject.bigChanges}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.investmentProgress"/></td>
							<td>
								<c:out value="${supplyLiquidProject.investmentProgress}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.comments"/></td>
							<td>
								<c:out value="${supplyLiquidProject.comments}" />
							</td>
						</tr>					
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
</div>