				<div class="form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
						<c:forEach var="counterparty" items="${counterpartiesTab22}" varStatus="status">
							<c:if test="${status.first}">
								<select id="counterparties22" name="counterparties22" class="form-control">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
							</c:if>
							<option value="${counterparty.id}" <c:if test = "${counterparty.id == creditInformation.counterparty.id}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>
					<div class="form-group">
					<div class="row">
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> : 
					<c:out value="${creditInformation.counterparty.name}" />
					</div>
					<c:if test="${not empty creditInformation}" >
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/></td>
							<td><c:out value="${creditInformation.debtBalance}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/></td>
							<td><c:out value="${creditInformation.debt}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/></td>
							<td>
								<c:out value="${creditInformation.outstanding}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/></td>
							<td>
								<c:out value="${creditInformation.balance}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.overdue"/></td>
							<td>
								<c:if test="${creditInformation.overdue}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not creditInformation.overdue}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.comment"/></td>
							<td>
								<c:out value="${creditInformation.comment}" />
							</td>
						</tr>
						</tbody>
					</table>
					</c:if>
					</div>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#counterparties22').change(function(){
		var counterpartyId=$(this).children('option:selected').val(); 
		$('#counterpartyId').val(counterpartyId);
		$('#guarantorId').val("");
		$('#activeTab').val("tab2-2");
		$('#riskControlReport').submit();
	})
})
</script>
					