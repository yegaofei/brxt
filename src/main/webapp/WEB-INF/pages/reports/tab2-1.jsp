
	<div class="row">
		<div class="col-sm-3 form-group">
		<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
		<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
			<c:if test="${status.first}">
				<select id="counterpartiesTab21" name="counterpartiesTab21" class="form-control">
					<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
			</c:if>
			<option value="${counterparty.id}" <c:if test = "${counterparty.id == param.counterpartiesTab21}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
			<c:if test="${status.last}"></select></c:if>
		</c:forEach>
		</div>
	
	<div class="col-sm-3 form-group">
			<appfuse:label styleClass="control-label" key="report.prev.term.report"/>
			<input type="text" id="prevTermTime" name="prevTermTime" maxlength="20" class="form-control" value="<c:out value='${param.prevTermTime}' />"/>
	</div>	
		<div class="col-sm-3 form-group">
			<appfuse:label styleClass="control-label" key="report.curr.term.report"/>
			<input type="text" id="currTermTime" name="currTermTime" maxlength="20" class="form-control" value="<c:out value='${param.currTermTime}' />"/>
      </div>
      	<div class="col-sm-1 form-group form-actions">
      	<label></label>
			<button type="submit" class="btn btn-primary form-control" name="method" value="FinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
			</button>
			
		</div>
		<div class="col-sm-1 form-group form-actions">
      	<label></label>
			<button type="submit" class="btn btn-primary form-control" name="method" value="SaveFinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>	

	</div>
		
	<c:if test="${not empty financeCheck}" >
	<div class=" form-group">
		<div class="row">
			<appfuse:label styleClass="control-label" key="projectInfo.counterparty.name"/> : 
			<c:out value="${creditInformation.counterparty.name}" />	
		</div>
		<appfuse:label styleClass="control-label" key="report.riskcontrol.financeCheck"/> <br>
		<appfuse:label styleClass="control-label" key="report.financeCheck.base"/>  
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
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.totalAsset}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.totalAsset}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.totalAsset}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.cash}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.cash}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.cash}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.inventory}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.inventory}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.inventory}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.nonLiquid"/></td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.totalDebt}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.totalDebt}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.totalDebt}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.prereceive}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.prereceive}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.prereceive}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.shortLoan}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.shortLoan}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.shortLoan}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.longLoan}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.longLoan}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.longLoan}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.netAsset}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.netAsset}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.netAsset}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><c:out value="${financeCheck.prevCorpBalanceSheet.actualCapital}" /></td>
					<td><c:out value="${financeCheck.currCorpBalanceSheet.actualCapital}" /></td>
					<td><c:out value="${financeCheck.corpBalanceSheetChanges.actualCapital}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><c:out value="${financeCheck.prevProfitStatement.operatingIncome}" /></td>
					<td><c:out value="${financeCheck.currProfitStatement.operatingIncome}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><c:out value="${financeCheck.prevProfitStatement.operatingProfit}" /></td>
					<td><c:out value="${financeCheck.currProfitStatement.operatingProfit}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><c:out value="${financeCheck.prevProfitStatement.netProfit}" /></td>
					<td><c:out value="${financeCheck.currProfitStatement.netProfit}" /></td>
					<td>-</td>
				</tr>
			</tbody>
		</table>
		
		<appfuse:label styleClass="control-label" key="report.financeCheck.ratio"/>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th> </th>
					<th><fmt:message key="report.prev"/></th>
					<th><fmt:message key="report.curr"/></th>
					<th><fmt:message key="report.financeCheck.changes"/></th>
				</tr>
			</thead>			
			<tbody>
				<tr>
					<td><fmt:message key="report.financeCheck.assetLiabilityRatio"/></td>
					<td><c:out value="${financeCheck.prevFinanceRatio.assetLiabilityRatio}" /></td>
					<td><c:out value="${financeCheck.currFinanceRatio.assetLiabilityRatio}" /></td>
					<td><c:out value="${financeCheck.financeRatioChanges.assetLiabilityRatio}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.liquidityRatio"/></td>
					<td><c:out value="${financeCheck.prevFinanceRatio.liquidityRatio}" /></td>
					<td><c:out value="${financeCheck.currFinanceRatio.liquidityRatio}" /></td>
					<td><c:out value="${financeCheck.financeRatioChanges.liquidityRatio}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.quickRatio"/></td>
					<td><c:out value="${financeCheck.prevFinanceRatio.quickRatio}" /></td>
					<td><c:out value="${financeCheck.currFinanceRatio.quickRatio}" /></td>
					<td><c:out value="${financeCheck.financeRatioChanges.quickRatio}" /></td>
				</tr>
				<tr>
					<td></td>
					<th><fmt:message key="report.lastYear"/></th>
					<th><fmt:message key="report.thisYear"/></th>
					<th><fmt:message key="report.years.changes"/></th>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.assetRoR"/></td>
					<td><c:out value="${financeCheck.prevFinanceRatio.assetRoR}" /></td>
					<td><c:out value="${financeCheck.currFinanceRatio.assetRoR}" /></td>
					<td><c:out value="${financeCheck.financeRatioChanges.assetRoR}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.salesIncrementRatio"/></td>
					<td><c:out value="${financeCheck.prevFinanceRatio.salesIncrementRatio}" /></td>
					<td><c:out value="${financeCheck.currFinanceRatio.salesIncrementRatio}" /></td>
					<td><c:out value="${financeCheck.financeRatioChanges.salesIncrementRatio}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.financeStatementSummary"/></td>
					<td colspan="3"><input type="text" class="form-control input-sm"></td>
				</tr>
			</tbody>
		</table>
</div>
</c:if>
				<div class="form-group">
					<div class="row">
					<appfuse:label styleClass="control-label" key="report.riskcontrol.otherCheck"/> : 
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

