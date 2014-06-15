<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="instBalanceSheet.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='instBalanceSheet.heading'/></h2>
    <span>
    	<c:if test = '${instBalanceSheetModel.tradingRelationship.title == "counterparty"}' >
    		<fmt:message key='corpBalanceSheet.counterpartyName'/> 
    	</c:if>
    	<c:if test = '${instBalanceSheetModel.tradingRelationship.title == "guarantor"}' >
    		<fmt:message key='corpBalanceSheet.guarantorName'/> 
    	</c:if>
    	: &nbsp; <c:out value="${instBalanceSheetModel.counterpartyName}"/>, <fmt:message key="${instBalanceSheetModel.counterpartyType}"/>
    </span>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="instBalanceSheetModel" method="post" action="/finance/instBalanceSheet" id="instBalanceSheetModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="tradingRelationship"/>
	<form:hidden path="counterpartyType"/>
	<input type="hidden" name="type" value='<c:out value="${instBalanceSheetModel.tradingRelationship}" />' />
	<input type="hidden" name="ctype" value='<c:out value="${instBalanceSheetModel.counterpartyType}" />' />
 
	<div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <form:select path="statementType" id="statementType">    		
			<form:options items="${statementTypes}" />
		</form:select>        
    </div>  
    
    <div class="col-sm-5">
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.reportYear"/>:
        <fmt:formatDate value="${instBalanceSheetModel.reportTime}" pattern="${shortDatePattern}" />
    </div>	

	</div>
	
	<table class="table table-condensed">
		 <tr>
            <th><appfuse:label key="instBalanceSheet.itemName"/></th>
            <th><appfuse:label key="instBalanceSheet.beginValue"/></th>
            <th><appfuse:label key="instBalanceSheet.endValue"/></th>
        </tr>   
        <tr>
        	<td><appfuse:label key="instBalanceSheet.assetGroupTotal"/> </td>
        	<td>
        	<spring:bind path="instBalanceSheetModel.beginBalSheet">
        	<div >
    		</spring:bind>        
        	<form:input path="beginBalSheet.assetGroupTotal" id="assetGroupTotal1" maxlength="20" />
        	<form:errors path="beginBalSheet.assetGroupTotal" />
        	</div>
        	</td>
        	<td>
        	<spring:bind path="instBalanceSheetModel.endBalSheet">
        	<div >
    		</spring:bind>        
        	<form:input path="endBalSheet.assetGroupTotal" id="assetGroupTotal2" maxlength="20" />
        	<form:errors path="endBalSheet.assetGroupTotal" />
        	</div>
        	</td>
        </tr>
        <tr>
        	<td><appfuse:label  key="instBalanceSheet.assetTotal"/></td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.assetTotal" id="assetTotal" maxlength="20"/>
        		<form:errors path="beginBalSheet.assetTotal"/>
 				</div>
 			</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
 				<div >       
    			</spring:bind>        
        		<form:input path="endBalSheet.assetTotal" id="assetTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.assetTotal" />
        		</div>
        	</td>
        </tr>
        <tr>
        	<td><appfuse:label key="instBalanceSheet.expenseTotal"/></td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.expenseTotal" id="expenseTotal" maxlength="20" />
        		<form:errors path="beginBalSheet.expenseTotal"/>
				</div>
			</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
				<div >        
    			</spring:bind>        
        		<form:input path="endBalSheet.expenseTotal" id="expenseTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.expenseTotal" />
        		</div>
        	</td>
        </tr>
        <tr>
        	<td> <appfuse:label  key="instBalanceSheet.debtGroupTotal"/> </td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.debtGroupTotal" id="debtGroupTotal" maxlength="20" />
        		<form:errors path="beginBalSheet.debtGroupTotal" />
        		</div>
        	</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="endBalSheet.debtGroupTotal" id="debtGroupTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.debtGroupTotal" />
        		</div>
        	</td>
        </tr>
        <tr>
        	<td><appfuse:label  key="instBalanceSheet.debtTotal"/></td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.debtTotal" id="debtTotal" maxlength="20" />
        		<form:errors path="beginBalSheet.debtTotal"/>
				</div>
			</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
				<div >        
    			</spring:bind>        
        		<form:input path="endBalSheet.debtTotal" id="debtTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.debtTotal" />
        		</div>
        	</td>
        </tr>
        <tr>
        	<td><appfuse:label   key="instBalanceSheet.netAssetTotal"/></td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.netAssetTotal" id="netAssetTotal" maxlength="20" />
        		<form:errors path="beginBalSheet.netAssetTotal" />
        		</div>
        	</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="endBalSheet.netAssetTotal" id="netAssetTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.netAssetTotal" />
        		</div>
        	</td>
        </tr>
        <tr>
        	<td><appfuse:label  key="instBalanceSheet.incomeTotal"/> </td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.beginBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="beginBalSheet.incomeTotal" id="incomeTotal" maxlength="20" />
        		<form:errors path="beginBalSheet.incomeTotal" />
        		</div>
        	</td>
        	<td>
        		<spring:bind path="instBalanceSheetModel.endBalSheet">    	
        		<div >
    			</spring:bind>        
        		<form:input path="endBalSheet.incomeTotal" id="incomeTotal2" maxlength="20" />
        		<form:errors path="endBalSheet.incomeTotal" />
        		</div>
        	</td>
        </tr>
	</table>
    

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <a class="btn btn-default" href="<c:url value='/finance/financialStatements'/>">
            <i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
        <a class="btn btn-default" href="<c:url value='/finance/financialStatements'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>    
    </div>
    
	</form:form>
</div>

<v:javascript formName="instBalanceSheetModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#statementType').change(function(){
		var p1=$(this).children('option:selected').val(); 
		if (p1 == 'profit_sheet')
		{
			window.location.href='<c:url value="/finance/profitStatement">
				<c:param name="counterpartyId" value="${instBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${instBalanceSheetModel.tradingRelationship}"/>
				<c:param name="ctype" value="${instBalanceSheetModel.counterpartyType}"/>
				</c:url>'; 
		} 
		else if (p1 == 'budget_sheet')
		{
			window.location.href='<c:url value="/finance/budgetStatementForm">
				<c:param name="counterpartyId" value="${instBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${instBalanceSheetModel.tradingRelationship}"/>
				<c:param name="ctype" value="${instBalanceSheetModel.counterpartyType}"/>
				</c:url>'; 
		}
		else if (p1 == 'cash_flow_sheet')
		{
			
		}
		
	})
})
  
</script>
