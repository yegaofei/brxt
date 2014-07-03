	<div class="row">	
				<div class="col-sm-3 form-group">
                		<appfuse:label styleClass="control-label" key="projectInfo.guarantor.name"/> : 
                		<c:if test="${empty guarantors}">
                			<fmt:message key="report.creditInfo.empty"/>
                		</c:if>
						<c:forEach var="guarantor" items="${guarantors}" varStatus="status">
							<c:if test="${status.first}">
								<select id="guarantorsTab6" name="guarantorsTab6" class="form-control input-sm">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
							</c:if>
							<option value="${guarantor.id}" <c:if test = "${guarantor.id == param.guarantorsTab6}" > selected </c:if>><c:out value="${guarantor.name}" /></option>
							<c:if test="${status.last}"></select></c:if>
						</c:forEach>
					</div>	
					
					<input type="hidden" name="guarantorIdTab6" value="<c:out value='${param.guarantorsTab6}'/>" >
					<input type="hidden" name="pttTab6" value="<c:out value='${param.prevTermTimeTab6}'/>" >
					<input type="hidden" name="cttTab6" value="<c:out value='${param.currTermTimeTab6}'/>" >
	
					<div class="col-sm-3 form-group">
						<appfuse:label styleClass="control-label" key="report.prev.term.report"/>
						<input type="text" id="prevTermTimeTab6" name="prevTermTimeTab6" maxlength="20" class="form-control input-sm" value="<c:out value='${param.prevTermTimeTab6}' />"/>
					</div>	
					<div class="col-sm-3 form-group">
						<appfuse:label styleClass="control-label" key="report.curr.term.report"/>
						<input type="text" id="currTermTimeTab6" name="currTermTimeTab6" maxlength="20" class="form-control input-sm" value="<c:out value='${param.currTermTimeTab6}' />"/>
      				</div>
      				<div class="col-sm-1 form-group form-actions">
      					<label></label>
						<button type="submit" class="btn btn-primary form-control" name="method" value="FinanceCheckTab6" onclick="$('#activeTab').val('tab6')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
						</button>			
					</div>
					<div class="col-sm-1 form-group form-actions">
      					<label></label>
						<button type="submit" class="btn btn-primary form-control" name="method" value="SaveFinanceCheckTab6" onclick="$('#activeTab').val('tab6')">
            				<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
						</button>
					</div>	
	</div>
	<div class="row">
		<appfuse:label styleClass="control-label" key="creditInformation.guarantor.evaluation"/>
		<input type="text" name="guarantorCheckComment" class="form-control input-sm" value="${riskControlReport.guarantorCheckComment}">
	</div>	
			
	 <c:if test="${not empty financeCheckTab6}" >
		<div class=" form-group">
		<div class="row">
			<appfuse:label styleClass="control-label" key="projectInfo.guarantor.name"/> : 
			<c:out value="${financeCheckTab6.counterparty.name}" />	
		</div>
		<appfuse:label styleClass="control-label" key="report.riskcontrol.financeCheck"/> <br>
		
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
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.totalAsset}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.totalAsset}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.totalAsset}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.cash"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.cash}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.cash}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.cash}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.inventory"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.inventory}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.inventory}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.inventory}" /></td>
				</tr>
			
				<tr>
					<td><fmt:message key="corpBalanceSheet.totalDebt"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.totalDebt}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.totalDebt}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.totalDebt}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.prereceive"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.prereceive}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.prereceive}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.prereceive}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.shortLoan"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.shortLoan}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.shortLoan}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.shortLoan}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.longLoan"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.longLoan}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.longLoan}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.longLoan}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.netAsset"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.netAsset}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.netAsset}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.netAsset}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="corpBalanceSheet.actualCapital"/></td>
					<td><c:out value="${financeCheckTab6.prevCorpBalanceSheet.actualCapital}" /></td>
					<td><c:out value="${financeCheckTab6.currCorpBalanceSheet.actualCapital}" /></td>
					<td><c:out value="${financeCheckTab6.corpBalanceSheetChanges.actualCapital}" /></td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingIncome"/></td>
					<td><c:out value="${financeCheckTab6.prevProfitStatement.operatingIncome}" /></td>
					<td><c:out value="${financeCheckTab6.currProfitStatement.operatingIncome}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.operatingProfit"/></td>
					<td><c:out value="${financeCheckTab6.prevProfitStatement.operatingProfit}" /></td>
					<td><c:out value="${financeCheckTab6.currProfitStatement.operatingProfit}" /></td>
					<td>-</td>
				</tr>
				<tr>
					<td><fmt:message key="profitStatement.netProfit"/></td>
					<td><c:out value="${financeCheckTab6.prevProfitStatement.netProfit}" /></td>
					<td><c:out value="${financeCheckTab6.currProfitStatement.netProfit}" /></td>
					<td>-</td>
				</tr>
			</tbody>
		</table>		
		</c:if>	
		
		<c:if test="${financeCheckTab6.counterparty.counterpartyType == 'institution'}">
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
						<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        			<td>
        				<c:out value="${financeCheckTab6.currInstituteBalanceSheet.assetGroupTotal}" />
		        	</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.assetTotal}" />
 					</td>
        			<td>
        				<c:out value="${financeCheckTab6.currInstituteBalanceSheet.assetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.expenseTotal}" />
					</td>
        			<td>
        				<c:out value="${financeCheckTab6.currInstituteBalanceSheet.expenseTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.debtGroupTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.debtTotal}" />
					</td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.debtTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.netAssetTotal}" />
        			</td>
        		</tr>
        		<tr>
        			<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        			<td>
        				<c:out value="${financeCheckTab6.prevInstituteBalanceSheet.incomeTotal}" />
        			</td>
        		</tr>
			</tbody>
			</table>
		</c:if>	
					<div class="form-group">
					<c:if test="${not empty creditInformationTab6}" >
					<appfuse:label styleClass="control-label" key="creditInformation.heading"/> :
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
						</tbody>
					</table>
					</c:if>
					</div>
					</div>
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
					