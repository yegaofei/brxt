
	<div class="row">
		<div class="col-sm-3 form-group">
		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
		<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
			<c:if test="${status.first}">
				<select id="counterpartiesTab21" name="counterpartiesTab21" class="form-control">
					<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
			</c:if>
			<option value="${counterparty.id}" <c:if test = "${counterparty.id == param.counterpartyId}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
			<c:if test="${status.last}"></select></c:if>
		</c:forEach>
		</div>
	
	<div class="col-sm-3 form-group">
			<appfuse:label styleClass="control-label" key="report.prev.term.report"/>
			<input type="text" id="prevTermTime" name="prevTermTime" maxlength="20" class="form-control"/>
	</div>	
		<div class="col-sm-3 form-group">
			<appfuse:label styleClass="control-label" key="report.curr.term.report"/>
			<input type="text" id="currTermTime" name="currTermTime" maxlength="20" class="form-control"/>
      </div>
      	<div class="col-sm-1 form-group form-actions">
      	<label></label>
			<button type="submit" class="btn btn-primary form-control" name="method" value="FinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
			</button>
		</div>	

	</div>
		
	
	<div class=" form-group">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="instBalanceSheet.itemName"/></th>
					<th><fmt:message key="report.financeCheck.prevTerm"/></th>
					<th><fmt:message key="report.financeCheck.currTerm"/></th>
					<th><fmt:message key="report.financeCheck.changes"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><fmt:message key="instBalanceSheet.assetTotal"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.nonLiquid"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
</div>

<script>
  $(function() {
    $('#prevTermTime').datepicker({
				//language: 'zh-CN'
				format: '<c:out value="${shortDatePatternJs}" />'
			});	
  });
  
  $(function() {
    $('#currTermTime').datepicker({
				//language: 'zh-CN'
				format: '<c:out value="${shortDatePatternJs}" />'
			});	
  });  
</script>

