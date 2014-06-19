				<div class="form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
						<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
							<c:if test="${status.first}"><select id="counterparties" name="counterparties" class="form-control"></c:if>
							<option value="${counterparty.id}" <c:if test = "${counterparty.id == subjectCapacity.counterparty.id}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>
					<div class="form-group">
					<appfuse:label styleClass="control-label" key="subjectCapacity.heading"/> :
					<table class="table table-striped table-bordered table-hover">
						<thead>
						<tr>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th> 
							<th><fmt:message key="subjectCapacity.comments"/></th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="subjectCapacity.licenseVerificationDate"/></td>
							<td><c:out value="${subjectCapacity.licenseVerificationDate}" /></td>
							<td></td>
						</tr>
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
						<tr>
							<td><fmt:message key="subjectCapacity.bizScopeChanged"/></td>
							<td>
								<c:if test="${subjectCapacity.bizScopeChanged}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.bizScopeChanged}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><fmt:message key="subjectCapacity.otherBigChanges"/></td>
							<td>
								<c:if test="${subjectCapacity.otherBigChanges}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not subjectCapacity.otherBigChanges}"><fmt:message key="label.no"/></c:if>
							</td>
							<td></td>
						</tr>
						</tbody>
					</table>
					</div>
	
	
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#counterparties').change(function(){
		var counterpartyId=$(this).children('option:selected').val(); 
		$('#counterpartyId').val(counterpartyId);
		$('#activeTab').val("tab2");
		$('#riskControlReport').submit();
	})
})
</script>
					