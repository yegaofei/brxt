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
								<c:out value="${repaymentProject.preAvgPrice}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.currAvgPrice"/></td>
							<td>
								<c:out value="${repaymentProject.currAvgPrice}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.comments"/></td>
							<td>
								<c:out value="${repaymentProject.comments}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.evaluation"/></td>
							<td>
								<input type="text" maxlength="20" class="form-control input-sm" />
							</td>
						</tr>
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
</div>