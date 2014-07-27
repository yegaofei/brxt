<c:set var="numberFormatType" scope="request">number</c:set>
<c:if test="${empty param.preview}">
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
					<input type="text" id="financeStatementSummary" name="financeStatementSummary" class="form-control input-sm" value="<c:out value='${riskControlReport.financeCheckComment}' default='${param.financeStatementSummary}'/>">
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
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.totalAsset}"/></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.totalAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.totalAsset}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.cash}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.cash}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.cash}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.inventory}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.inventory}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.inventory}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.nonLiquid"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.nonLiquid}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.nonLiquid}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.nonLiquid}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.totalDebt}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.totalDebt}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.totalDebt}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.prereceive}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.prereceive}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.prereceive}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.shortLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.shortLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.shortLoan}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.longLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.longLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.longLoan}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.netAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.netAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.netAsset}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.actualCapital}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.actualCapital}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.actualCapital}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.operatingIncome}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.operatingIncome}" /></fmt:formatNumber></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.operatingProfit}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.operatingProfit}" /></fmt:formatNumber></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.netProfit}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.netProfit}" /></fmt:formatNumber></td>
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
					<td><fmt:formatNumber type="percent" pattern="#0.00%"><c:out value="${financeCheck.prevFinanceRatio.assetLiabilityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent" pattern="#0.00%"><c:out value="${financeCheck.currFinanceRatio.assetLiabilityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.assetLiabilityRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.liquidityRatio"/></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.prevFinanceRatio.liquidityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.currFinanceRatio.liquidityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.liquidityRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.quickRatio"/></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.prevFinanceRatio.quickRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.currFinanceRatio.quickRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.quickRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td></td>
					<th><fmt:message key="report.lastYear"/></th>
					<th><fmt:message key="report.thisYear"/></th>
					<th><fmt:message key="report.years.changes"/></th>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.assetRoR"/></td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.salesIncrementRatio"/></td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
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
						<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.assetGroupTotal}" /></fmt:formatNumber>
		        	</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.assetGroupTotal}" /></fmt:formatNumber>
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.assetTotal}" /></fmt:formatNumber>
 					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.assetTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.expenseTotal}" /></fmt:formatNumber>
					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.expenseTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" /></fmt:formatNumber>
					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" /></fmt:formatNumber>
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
					<c:if test="${not empty creditInformation and financeCheck.counterparty.counterpartyType != 'institution'}" >
					<div class="row">
					<appfuse:label styleClass="control-label" key="report.riskcontrol.otherCheck"/> : 
					</div>
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/></td>
							<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${creditInformation.debtBalance}" /></fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/></td>
							<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${creditInformation.debt}" /></fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/></td>
							<td>
								<fmt:formatNumber type="${numberFormatType}"><c:out value="${creditInformation.outstanding}" /></fmt:formatNumber>
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/></td>
							<td>
								<fmt:formatNumber type="${numberFormatType}"><c:out value="${creditInformation.balance}" /></fmt:formatNumber>
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
</c:if>

<c:if test="${not empty param.preview and param.preview}">
	<c:if test="${empty financeCheckList}">
		<div class="col-lg-12">
		<div class="alert alert-dismissable alert-danger">
			<fmt:message key="report.financeCheck.empty" />
		</div>
		</div>
	</c:if>	
	
	<c:forEach var="financeCheck" items="${financeCheckList}">
		<div class="form-group">
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
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.totalAsset}"/></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.totalAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.totalAsset}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.cash}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.cash}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.cash}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.inventory}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.inventory}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.inventory}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.nonLiquid"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.nonLiquid}" /></fmt:formatNumber></td>
                    <td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.nonLiquid}" /></fmt:formatNumber></td>
                    <td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.nonLiquid}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.totalDebt}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.totalDebt}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.totalDebt}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.prereceive}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.prereceive}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.prereceive}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.shortLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.shortLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.shortLoan}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.longLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.longLoan}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.longLoan}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.netAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.netAsset}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.netAsset}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevCorpBalanceSheet.actualCapital}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currCorpBalanceSheet.actualCapital}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.corpBalanceSheetChanges.actualCapital}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.operatingIncome}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.operatingIncome}" /></fmt:formatNumber></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.operatingProfit}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.operatingProfit}" /></fmt:formatNumber></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevProfitStatement.netProfit}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currProfitStatement.netProfit}" /></fmt:formatNumber></td>
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
					<td><fmt:formatNumber type="percent" pattern="#0.00%"><c:out value="${financeCheck.prevFinanceRatio.assetLiabilityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent" pattern="#0.00%"><c:out value="${financeCheck.currFinanceRatio.assetLiabilityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.assetLiabilityRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.liquidityRatio"/></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.prevFinanceRatio.liquidityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.currFinanceRatio.liquidityRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.liquidityRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.quickRatio"/></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.prevFinanceRatio.quickRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="number" pattern="#0.00"><c:out value="${financeCheck.currFinanceRatio.quickRatio}" /></fmt:formatNumber></td>
					<td><fmt:formatNumber type="percent"><c:out value="${financeCheck.financeRatioChanges.quickRatio}" /></fmt:formatNumber></td>
				</tr>
				<tr>
					<td></td>
					<th><fmt:message key="report.lastYear"/></th>
					<th><fmt:message key="report.thisYear"/></th>
					<th><fmt:message key="report.years.changes"/></th>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.assetRoR"/></td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="report.financeCheck.salesIncrementRatio"/></td>
					<td>-</td>
					<td>-</td>
					<td>-</td>
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
						<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.assetGroupTotal}" /></fmt:formatNumber>
		        	</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.assetGroupTotal}" /></fmt:formatNumber>
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.assetTotal}" /></fmt:formatNumber>
 					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.assetTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.expenseTotal}" /></fmt:formatNumber>
					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.currInstituteBalanceSheet.expenseTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtGroupTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" /></fmt:formatNumber>
					</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.debtTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.netAssetTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" /></fmt:formatNumber>
        			</td>
        			<td>
        				<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.prevInstituteBalanceSheet.incomeTotal}" /></fmt:formatNumber>
        			</td>
        		</tr>
			</tbody>
			</table>
		</c:if>
		<div class="form-horizontal">
            <fieldset disabled>
            <div class="form-group">
                <label for="financeStatementSummary" class="col-lg-3 control-label"><fmt:message key="report.financeCheck.financeStatementSummary" /></label>
                <div class="col-lg-9">
                    <input type="text" id="financeStatementSummary" class="form-control input-sm" value="${riskControlReport.financeCheckComment}">
                </div>
            </div>
            </fieldset>
        </div>	
		<c:if test="${not empty financeCheck.creditInformation and financeCheck.counterparty.counterpartyType != 'institution'}" >
					<div class="row">
					<appfuse:label styleClass="control-label" key="report.riskcontrol.otherCheck"/> : 
					</div>
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/><fmt:message key="currency.unit.wan"/></td>
							<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.creditInformation.debtBalance}" /></fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/><fmt:message key="currency.unit.wan"/></td>
							<td><fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.creditInformation.debt}" /></fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/><fmt:message key="currency.unit.wan"/></td>
							<td>
								<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.creditInformation.outstanding}" /></fmt:formatNumber>
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/><fmt:message key="currency.unit.wan"/></td>
							<td>
								<fmt:formatNumber type="${numberFormatType}"><c:out value="${financeCheck.creditInformation.balance}" /></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.overdue"/></td>
							<td>
								<c:if test="${financeCheck.creditInformation.overdue}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not financeCheck.creditInformation.overdue}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.comment"/></td>
							<td>
								<c:out value="${financeCheck.creditInformation.comment}" />
							</td>
						</tr>
						</tbody>
					</table>
		</c:if>
</div>
	</c:forEach>
</c:if>		

