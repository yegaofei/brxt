				<div class="form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.guarantor.name"/> : 
						<c:forEach var="guarantor" items="${guarantors}" varStatus="status">
							<c:if test="${status.first}"><select id="guarantors" name="guarantors" class="form-control"></c:if>
							<option value="${guarantor.id}" <c:if test = "${guarantor.id == creditInformation.counterparty.id}" > selected </c:if>><c:out value="${guarantor.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>
					<div class="form-group">
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> :
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
						<tr>
							<td><fmt:message key="creditInformation.guarantor.evaluation"/></td>
							<td>
								<input type="text" maxlength="20" class="form-control input-sm" />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#guarantors').change(function(){
		var guarantorId=$(this).children('option:selected').val(); 
		$('#guarantorId').val(guarantorId);
		$('#activeTab').val("tab6");
		$('#riskControlReport').submit();
	})
})
</script>
					