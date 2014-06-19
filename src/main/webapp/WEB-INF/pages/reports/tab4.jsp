<div class="form-group">
	<c:if test="${not empty repaymentProjects}">
		<c:forEach var="repaymentProject" items="${repaymentProjects}" varStatus="status">
			<div class="row">
				<appfuse:label styleClass="control-label" key="repaymentProject.name"/>
				<c:out value="${repaymentProject.name}" />
			</div>
			<table class="table table-striped table-bordered table-hover">
						<thead>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th>
						</thead>
						<tbody>
						<c:if test="${repaymentProject.investmentProjectType == 'real_estate'}" >
						<tr>
							<td><fmt:message key="repaymentProject.policy"/></td>
							<td><input type="text" maxlength="20" class="form-control" /></td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.price"/></td>
							<td><input type="text" maxlength="20" class="form-control" /></td>
						</tr>
						</c:if>
						<tr>
							<td><fmt:message key="repaymentProject.startTime"/></td>
							<td>
								<fmt:formatDate value="${repaymentProject.startTime}" pattern="${datePattern}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.endTime"/></td>
							<td>
								<fmt:formatDate value="${repaymentProject.endTime}" pattern="${datePattern}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.totalAmout"/></td>
							<td>
								<c:out value="${repaymentProject.totalAmout}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.investmentAmount"/></td>
							<td>
								<c:out value="${repaymentProject.investmentAmount}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.description"/></td>
							<td>
								<c:out value="${repaymentProject.description}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.delayed"/></td>
							<td>
								<c:if test="${repaymentProject.delayed}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not repaymentProject.delayed}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.evaluation"/></td>
							<td>
								<input type="text" maxlength="20" class="form-control" />
							</td>
						</tr>
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
</div>