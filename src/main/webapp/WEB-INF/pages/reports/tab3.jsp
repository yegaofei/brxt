<div class="form-group">
	
	<appfuse:label styleClass="control-label" key="investmentProject.policyChanges"/>
	<input type="text" name="policyChanges" class="form-control input-sm" value="${riskControlReport.policyChanges}">
	<appfuse:label styleClass="control-label" key="investmentProject.priceChanges"/>						
	<input type="text" name="priceChanges" class="form-control input-sm" value="${riskControlReport.priceChanges}">
	<appfuse:label styleClass="control-label" key="investmentProject.evaluation"/>	
	<input type="text" name="investmentEvaluation" class="form-control input-sm" value="${riskControlReport.investmentEvaluation}" >									
	<c:if test="${not empty investmentProjects}">
		<c:forEach var="investmentProject" items="${investmentProjects}" varStatus="status">
			<div class="row">
				<appfuse:label styleClass="control-label" key="investmentProject.name"/>:
				<c:out value="${investmentProject.investmentStatus.projectName}" /> (<fmt:message key="${investmentProject.investmentProjectType}" />)
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