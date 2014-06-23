			<div class="row">	
				<div class="col-sm-3 form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.guarantor.name"/> : 
                		<c:if test="${empty guarantors}">
                			<fmt:message key="report.creditInfo.empty"/>
                		</c:if>
						<c:forEach var="guarantor" items="${guarantors}" varStatus="status">
							<c:if test="${status.first}">
								<select id="guarantors" name="guarantors" class="form-control">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
							</c:if>
							<option value="${guarantor.id}" <c:if test = "${guarantor.id == param.guarantorId}" > selected </c:if>><c:out value="${guarantor.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>	
						
					<div class="col-sm-1 form-group form-actions">
      					<label></label>
						<button type="submit" class="btn btn-primary form-control" name="method" value="SaveFinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
						</button>
					</div>	
					
				</div>	
					<div class="form-group">
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> :
					<c:if test="${not empty creditInformationTab6}" >
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/></td>
							<td><c:out value="${creditInformationTab6.debtBalance}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/></td>
							<td><c:out value="${creditInformationTab6.debt}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/></td>
							<td>
								<c:out value="${creditInformationTab6.outstanding}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/></td>
							<td>
								<c:out value="${creditInformationTab6.balance}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.overdue"/></td>
							<td>
								<c:if test="${creditInformationTab6.overdue}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not creditInformationTab6.overdue}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.comment"/></td>
							<td>
								<c:out value="${creditInformationTab6.comment}" />
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
					</c:if>
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
					