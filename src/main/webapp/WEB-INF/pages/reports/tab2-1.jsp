<div class="col-lg-12">
	<div class="well form-horizontal">
		<fieldset>
			<div class="col-lg-12">
			<div class="form-group">
				<label for="counterpartiesTab21" class="col-lg-3 control-label"><fmt:message key="projectInfo.counterparty.name" /></label>
				<div class="col-lg-3">
					<c:forEach var="counterparty" items="${counterparties}" varStatus="status">
						<c:if test="${status.first}">
							<select id="counterpartiesTab21" name="counterpartiesTab21" class="form-control  input-sm">
							<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
						</c:if>
						<option value="${counterparty.id}" <c:if test = "${counterparty.id == param.counterpartiesTab21}" > selected </c:if>><c:out value="${counterparty.name}" /></option>
						<c:if test="${status.last}"></select></c:if>
					</c:forEach>
				</div>
			</div>
			
			<div class="form-group">
				<label for="prevTermTime" class="col-lg-3 control-label"><fmt:message key="report.prev.term.report" /></label>
				<div class="col-lg-3">
					<input type="text" id="prevTermTime" name="prevTermTime"  value="<c:out value='${param.prevTermTime}' />"  class="form-control  input-sm" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="currTermTime" class="col-lg-3 control-label"><fmt:message key="report.curr.term.report" /></label>
				<div class="col-lg-3">
					<input type="text" id="currTermTime" name="currTermTime" maxlength="20" class="form-control input-sm" value="<c:out value='${param.currTermTime}' />"/>
				</div>
			</div>		
			
			<div class="form-group">
				<label for="financeStatementSummary" class="col-lg-3 control-label"><fmt:message key="report.financeCheck.financeStatementSummary" /></label>
				<div class="col-lg-9">
					<input type="text" id="financeStatementSummary" name="financeStatementSummary" class="form-control input-sm" value="${riskControlReport.financeCheckComment}">
				</div>
			</div>
			
			</div>
			
			<div class="form-group">
				<div class="col-lg-3 col-lg-offset-9">
			 			<button type="submit" class="btn btn-primary" name="method" value="FinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
						</button>
					
						<button type="submit" class="btn btn-primary" name="method" value="SaveFinanceCheckTab21" onclick="$('#activeTab').val('tab2-1')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
						</button>	
				</div>
			</div>
		</fieldset>
	</div>
</div>
		
		<input type="hidden" name="counterpartyIdTab21" value="<c:out value='${param.counterpartiesTab21}'/>" >
		<input type="hidden" name="prevTermTimeTab21" value="<c:out value='${param.prevTermTime}'/>" >
		<input type="hidden" name="currTermTimeTab21" value="<c:out value='${param.currTermTime}'/>" >
	
<div class="col-lg-12">			
	<c:if test="${not empty financeCheck}" >
	<div class=" form-group">
		<div class="page-header">
			<h4>
				<fmt:message key="projectInfo.counterparty.name" />:
				<c:out value="${financeCheck.counterparty.name}" />
			</h4>
		</div>
					
		<appfuse:label styleClass="control-label" key="report.riskcontrol.financeCheck"/> <br>
		
		<c:if test="${financeCheck.counterparty.counterpartyType != 'institution'}">
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
			</tbody>
		</table>
		</c:if>
		
		<c:if test="${financeCheck.counterparty.counterpartyType == 'institution'}">
			<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="instBalanceSheet.itemName"/></th>
					<th><fmt:message key="report.prev"/></th>
					<th><fmt:message key="report.curr"/></th>
				</tr>
			</thead>	
			<tbody>
				<tr>
        			<td><appfuse:label key="instBalanceSheet.assetGroupTotal"/> </td>
        			<td>
						<c:out value="${financeCheck.prevInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        			<td>
        				<c:out value="${financeCheck.currInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.assetTotal}" />
 					</td>
        			<td>
        				<c:out value="${financeCheck.currInstituteBalanceSheet.assetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.expenseTotal}" />
					</td>
        			<td>
        				<c:out value="${financeCheck.currInstituteBalanceSheet.expenseTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" />
					</td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        		</tr>
        		<tr>
					<td><appfuse:label key="report.financeCheck.financeStatementSummary"/></td>
					<td colspan="3"><input type="text" name="financeStatementSummary" class="form-control input-sm"></td>
				</tr>
			</tbody>
			</table>
		</c:if>
		
</div>
</c:if>
				<div class="form-group">
					<c:if test="${not empty creditInformation}" >
					<div class="row">
					<appfuse:label styleClass="control-label" key="report.riskcontrol.otherCheck"/> : 
					</div>
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
</div>
<script>
  $(function() {
    $('#prevTermTime').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});	
  });
  
  $(function() {
    $('#currTermTime').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});	
  });  
</script>

