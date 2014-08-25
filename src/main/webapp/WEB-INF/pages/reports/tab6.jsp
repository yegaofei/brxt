
<c:if test="${empty param.preview}">
<div class="col-lg-12">
	<div class="well form-horizontal">
		<fieldset>
			<div class="col-lg-12">
			<div class="form-group">
				<label for="guarantorsTab6" class="col-lg-3 control-label"><fmt:message key="projectInfo.guarantor.name" /></label>
				<div class="col-lg-3">
					<c:forEach var="guarantor" items="${guarantors}" varStatus="status">
						<c:if test="${status.first}">
								<select id="guarantorsTab6" name="guarantorsTab6" class="form-control input-sm">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
						</c:if>
						<option value="${guarantor.id}" <c:if test = "${guarantor.id == param.guarantorsTab6}" > selected </c:if>><c:out value="${guarantor.name}" /></option>
						<c:if test="${status.last}"></select></c:if>
					</c:forEach>
				</div>
			</div>
			
			<div class="form-group">
				<label for="prevTermTimeTab6" class="col-lg-3 control-label"><fmt:message key="report.prev.term.report" /></label>
				<div class="col-lg-3">
					<input type="text" id="prevTermTimeTab6" name="prevTermTimeTab6"  value="<c:out value='${param.prevTermTimeTab6}' />"  class="form-control input-sm" >
				</div>
			</div>
			
			<div class="form-group">
				<label for="currTermTimeTab6" class="col-lg-3 control-label"><fmt:message key="report.curr.term.report" /></label>
				<div class="col-lg-3">
					<input type="text" id="currTermTimeTab6" name="currTermTimeTab6" value="<c:out value='${param.currTermTimeTab6}' />" class="form-control input-sm" >
				</div>
			</div>		
			
			<div class="form-group">
				<label for="guarantorCheckComment" class="col-lg-3 control-label"><fmt:message key="creditInformation.guarantor.evaluation" /></label>
				<div class="col-lg-9">
					<textarea id="guarantorCheckComment" name="guarantorCheckComment" class="form-control input-sm"><c:out value="${riskControlReport.guarantorCheckComment}"/></textarea>
				</div>
			</div>
			
			</div>
			
			<div class="form-group">
				<div class="col-lg-3 col-lg-offset-9">
			 			<button type="submit" class="btn btn-primary" name="method" value="FinanceCheckTab6" onclick="$('#activeTab').val('tab6')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
						</button>	
					
						<button type="submit" class="btn btn-primary" name="method" value="SaveFinanceCheckTab6" onclick="$('#activeTab').val('tab6')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
						</button>
				</div>
			</div>
		</fieldset>
	</div>
</div>
		<input type="hidden" name="guarantorIdTab6" value="<c:out value='${param.guarantorsTab6}'/>" >
		<input type="hidden" name="pttTab6" value="<c:out value='${param.prevTermTimeTab6}'/>" >
		<input type="hidden" name="cttTab6" value="<c:out value='${param.currTermTimeTab6}'/>" >

<div class="col-lg-12">		
	 <c:if test="${not empty financeCheckTab6}" >
		<div class=" form-group">
		<div class="page-header">
			<h4>
				<appfuse:label styleClass="control-label" key="report.riskcontrol.financeCheck"/>:<c:out value="${financeCheckTab6.counterparty.name}" />
			</h4>
		</div>
		<c:if test="${financeCheckTab6.counterparty.counterpartyType != 'institution'}">
		<c:if test="${not empty financeCheckTab6.prevCorpBalanceSheet or not empty financeCheckTab6.currCorpBalanceSheet }">
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
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.totalAsset / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.totalAsset / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.totalAsset}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.totalAsset}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.cash / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.cash / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.cash}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.cash}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.inventory / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.inventory / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.inventory}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.inventory}">-</c:if>
					</td>
				</tr>
			
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.totalDebt / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.totalDebt / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.totalDebt}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.totalDebt}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.prereceive / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.prereceive / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.prereceive}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.prereceive}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.shortLoan / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.shortLoan / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.shortLoan}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.shortLoan}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.longLoan / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.longLoan / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.longLoan}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.longLoan}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.netAsset / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.netAsset / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.netAsset}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.netAsset}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.actualCapital / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.actualCapital / 10000}" /></td>
					<td>
					   <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.actualCapital}" />
					   <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.actualCapital}">-</c:if>
					</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.operatingIncome / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.operatingIncome / 10000}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.operatingProfit / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.operatingProfit / 10000}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.netProfit / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.netProfit / 10000}" /></td>
					<td>-</td>
				</tr>
			</tbody>
		</table>	
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.prev.state">
                    <fmt:param><c:out value="${financeCheckTab6.prevCorpBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.prevCorpBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
            <li>
                <fmt:message key="report.curr.state">
                    <fmt:param><c:out value="${financeCheckTab6.currCorpBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.currCorpBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
        </ul>  			
		</c:if>	
		</c:if>	
		
		<c:if test="${financeCheckTab6.counterparty.counterpartyType == 'institution'}">
			<c:if test="${not empty financeCheckTab6.prevInstituteBalanceSheet or not empty financeCheckTab6.currInstituteBalanceSheet}" >
			<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="instBalanceSheet.itemName"/></th>
					<th><fmt:message key="report.prev"/><fmt:message key="currency.unit.wan"/></th>
					<th><fmt:message key="report.curr"/><fmt:message key="currency.unit.wan"/></th>
				</tr>
			</thead>	
			<tbody>
				<tr>
        			<td><appfuse:label key="instBalanceSheet.assetGroupTotal"/> </td>
        			<td>
						<fmt:formatNumber type="number" pattern="#0.00" value="${financeCheckTab6.prevInstituteBalanceSheet.assetGroupTotal}" /> 
		        	</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.assetTotal}" />
 					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.assetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.expenseTotal}" />
					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.expenseTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.debtTotal}" />
					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.debtTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.incomeTotal}" />
        			</td>
        		</tr>
			</tbody>
			</table>
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.prev.state">
                    <fmt:param><c:out value="${financeCheckTab6.prevInstituteBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.prevInstituteBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
            <li>
                <fmt:message key="report.curr.state">
                    <fmt:param><c:out value="${financeCheckTab6.currInstituteBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.currInstituteBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
        </ul>           			
			</c:if>
		</c:if>	
					<div class="form-group">
					<c:if test="${not empty creditInformationTab6 and financeCheckTab6.counterparty.counterpartyType != 'institution'}" >
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> :
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/></td>
							<td><fmt:formatNumber type="number" value="${creditInformationTab6.debtBalance}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/></td>
							<td><fmt:formatNumber type="number" value="${creditInformationTab6.debt}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/></td>
							<td>
								<fmt:formatNumber type="number" value="${creditInformationTab6.outstanding}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/></td>
							<td>
								<fmt:formatNumber type="number" value="${creditInformationTab6.balance}" />
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
						</tbody>
					</table>
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.creditInformation.querytime">
                    <fmt:param><fmt:formatDate value="${creditInformationTab6.queryTime}" pattern="${datePattern}" /></fmt:param>
                </fmt:message>
            </li>
        </ul>					
					</c:if>
					</div>
					</div>
	</c:if>						
</div>	
</c:if>

<c:if test="${empty financeCheckTab6}">
	<c:if test="${empty financeCheckListTab6 and not empty param.preview and param.preview}">
		<div class="col-lg-12">
		<div class="alert alert-dismissable alert-danger">
			<fmt:message key="report.financeCheck.empty" />
		</div>
		</div>
	</c:if>	
	
	<c:if test="${not empty financeCheckListTab6}">
	<c:forEach var="financeCheckTab6" items="${financeCheckListTab6}">
		<div class=" form-group">
		<div class="page-header">
			<h4>
				<appfuse:label styleClass="control-label" key="report.riskcontrol.financeCheck"/>:<c:out value="${financeCheckTab6.counterparty.name}" />
			</h4>
		</div>
		<c:if test="${financeCheckTab6.counterparty.counterpartyType != 'institution'}">
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
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.totalAsset / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.totalAsset / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.totalAsset}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.totalAsset}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.cash / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.cash / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.cash}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.cash}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.inventory / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.inventory / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.inventory}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.inventory}">-</c:if>
                    </td>
				</tr>
			
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.totalDebt / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.totalDebt / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.totalDebt}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.totalDebt}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.prereceive / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.prereceive / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.prereceive}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.prereceive}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.shortLoan / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.shortLoan / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.shortLoan}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.shortLoan}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.longLoan / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.longLoan / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.longLoan}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.longLoan}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.netAsset / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.netAsset / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.netAsset}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.netAsset}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevCorpBalanceSheet.actualCapital / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currCorpBalanceSheet.actualCapital / 10000}" /></td>
					<td>
                       <fmt:formatNumber type="percent" value="${financeCheckTab6.corpBalanceSheetChanges.actualCapital}" />
                       <c:if test="${empty financeCheckTab6.corpBalanceSheetChanges.actualCapital}">-</c:if>
                    </td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.operatingIncome / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.operatingIncome / 10000}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.operatingProfit / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.operatingProfit / 10000}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.prevProfitStatement.netProfit / 10000}" /></td>
					<td><fmt:formatNumber type="number" value="${financeCheckTab6.currProfitStatement.netProfit / 10000}" /></td>
					<td>-</td>
				</tr>
			</tbody>
		</table>		
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.prev.state">
                    <fmt:param><c:out value="${financeCheckTab6.prevCorpBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.prevCorpBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
            <li>
                <fmt:message key="report.curr.state">
                    <fmt:param><c:out value="${financeCheckTab6.currCorpBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.currCorpBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
        </ul>   		
		</c:if>	
		
		<c:if test="${financeCheckTab6.counterparty.counterpartyType == 'institution'}">
			<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="instBalanceSheet.itemName"/></th>
					<th><fmt:message key="report.prev"/><fmt:message key="currency.unit.wan"/></th>
					<th><fmt:message key="report.curr"/><fmt:message key="currency.unit.wan"/></th>
				</tr>
			</thead>	
			<tbody>
				<tr>
        			<td><appfuse:label key="instBalanceSheet.assetGroupTotal"/> </td>
        			<td>
						<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.assetTotal}" />
 					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.assetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.expenseTotal}" />
					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.expenseTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.debtTotal}" />
					</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.debtTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        			<td>
        				<fmt:formatNumber type="number" value="${financeCheckTab6.currInstituteBalanceSheet.incomeTotal}" />
        			</td>
        		</tr>
			</tbody>
			</table>
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.prev.state">
                    <fmt:param><c:out value="${financeCheckTab6.prevInstituteBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.prevInstituteBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
            <li>
                <fmt:message key="report.curr.state">
                    <fmt:param><c:out value="${financeCheckTab6.currInstituteBalanceSheet.reportYear}"/></fmt:param>
                    <fmt:param value="${financeCheckTab6.currInstituteBalanceSheet.reportMonth}"/>
                </fmt:message>
            </li>
        </ul>  			
		</c:if>	
					<div class="form-group">
					<c:if test="${not empty financeCheckTab6.creditInformation and financeCheckTab6.counterparty.counterpartyType != 'institution'}" >
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> :
					<table class="table table-striped table-bordered table-hover">
						<tbody>
						<tr>
							<td><fmt:message key="creditInformation.debtBalance"/></td>
							<td><fmt:formatNumber type="number" value="${financeCheckTab6.creditInformation.debtBalance}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.debt"/></td>
							<td><fmt:formatNumber type="number" value="${financeCheckTab6.creditInformation.debt}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.outstanding"/></td>
							<td>
								<fmt:formatNumber type="number" value="${financeCheckTab6.creditInformation.outstanding}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.balance"/></td>
							<td>
								<fmt:formatNumber type="number" value="${financeCheckTab6.creditInformation.balance}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.overdue"/></td>
							<td>
								<c:if test="${financeCheckTab6.creditInformation.overdue}"><fmt:message key="label.yes"/></c:if>
								<c:if test="${not financeCheckTab6.creditInformation.overdue}"><fmt:message key="label.no"/></c:if>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="creditInformation.comment"/></td>
							<td>
								<c:out value="${financeCheckTab6.creditInformation.comment}" />
							</td>
						</tr>					
						</tbody>
					</table>
        <ul class="unstyled">  
            <li>
                <fmt:message key="report.creditInformation.querytime">
                    <fmt:param><fmt:formatDate value="${financeCheckTab6.creditInformation.queryTime}" pattern="${datePattern}" /></fmt:param>
                </fmt:message>
            </li>
        </ul>   					
					</c:if>
					</div>
					</div>
	</c:forEach>
	
	<c:if test="${not empty param.preview and param.preview}">
        <div class="form-horizontal">
            <fieldset disabled>
            <div class="form-group">
                <label for="guarantorCheckComment" class="col-lg-3 control-label"><fmt:message key="report.financeCheck.guarantorCheckComment" /></label>
                <div class="col-lg-9">
                    <textarea id="guarantorCheckComment" class="form-control input-sm"><c:out value="${riskControlReport.guarantorCheckComment}"/></textarea>
                </div>
            </div>
            </fieldset>
        </div>  
     </c:if>
     
	</c:if>
</c:if>
<script>
  $(function() {
    $('#prevTermTimeTab6').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});	
  });
  
  $(function() {
    $('#currTermTimeTab6').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});	
  });  
</script>
					