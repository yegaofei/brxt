<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="budgetStatementForm.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
 
<div class="col-sm-2">
	<h2><fmt:message key='budgetStatementForm.heading'/></h2> 
	<span>
    	<c:if test = '${param.type == "counterparty"}' >
    		<fmt:message key='corpBalanceSheet.counterpartyName'/> 
    	</c:if>
    	<c:if test = '${param.type == "guarantor"}' >
    		<fmt:message key='corpBalanceSheet.guarantorName'/> 
    	</c:if>
    	: &nbsp; <c:out value="${budgetStatementModel.counterpartyName}"/>, <fmt:message key="${param.ctype}"/>
    </span>
</div>
 
<div class="col-sm-8">	
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="budgetStatementModel" method="post" action="/finance/budgetStatementForm" id="budgetStatementModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
	<div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <form:select path="statementType" id="statementType">    		
			<form:options items="${statementTypes}" />
		</form:select>        
    </div>  
	    
	<spring:bind path="budgetStatementModel.reportYear">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="instBalanceSheetModel.reportYear"/>:
        <form:input path="reportYear" id="reportYear" maxlength="20" />
        <form:errors path="reportYear" cssClass="help-block"/>
    </div>	
	</div>
	
	<table class="table table-condensed">
		 <tr>
			<th><appfuse:label key="budgetStatementInfo.itemName"/> </th>
			<th><appfuse:label key="budgetStatementInfo.fullYearBudget"/> </th>
			<th><appfuse:label key="budgetStatementInfo.thisYear"/>   </th>
			<th><appfuse:label key="budgetStatementInfo.lastYear"/>   </th>   
			<th><appfuse:label key="budgetStatementInfo.budgetRatio"/>   </th> 
			<th><appfuse:label key="budgetStatementInfo.growthRate"/>   </th>    
        </tr>  
        
        <tr>
		          <td> <appfuse:label key="budgetStatementInfo.budgetIncomeTotal"/> </td>
                  <td> <c:out value="${budgetStatementModel.fullYearBudget.budgetIncomeTotal}" /> </td>
                  <td> <c:out value="${budgetStatementModel.thisYear.budgetIncomeTotal}" /> </td>
                  <td> <c:out value="${budgetStatementModel.lastYear.budgetIncomeTotal}" /> </td>
        
                  <td>          
        <c:out value="${budgetStatementModel.budgetRatio.budgetIncomeTotal}" />
        		</td>
        
                  <td>        
    	<c:out value="${budgetStatementModel.growthRate.budgetIncomeTotal}" />        
        		</td>
        </tr>
        
        <tr>
        	<td> <appfuse:label key="budgetStatementInfo.taxIncome"/> </td>
        	<td>        
        	<c:out value="${budgetStatementModel.fullYearBudget.taxIncome}" />
        	</td>
        
        	<td>          
        	<c:out value="${budgetStatementModel.thisYear.taxIncome}" />
        	</td>
        
        	<td>         
        	<c:out value="${budgetStatementModel.lastYear.taxIncome}" />
        	</td>
        
        	<td>          
        	<c:out value="${budgetStatementModel.budgetRatio.taxIncome}" />
        	</td>
        
        	<td>        
    		<c:out value="${budgetStatementModel.growthRate.taxIncome}" />        
        	</td>
        </tr>
	
		<tr>
			<td> <appfuse:label key="budgetStatementInfo.nonTaxIncome"/> </td>
       
        <td>        
        <c:out value="${budgetStatementModel.fullYearBudget.nonTaxIncome}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.thisYear.nonTaxIncome}" />
        </td>
        
        <td>         
        <c:out value="${budgetStatementModel.lastYear.nonTaxIncome}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.budgetRatio.nonTaxIncome}" />
        </td>
        
        <td>        
    	<c:out value="${budgetStatementModel.growthRate.nonTaxIncome}" />        
        </td>
		
		</tr>
		
		<tr>
			<td> <appfuse:label key="budgetStatementInfo.govFundIncome"/> </td>
       
        <td>        
        <c:out value="${budgetStatementModel.fullYearBudget.govFundIncome}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.thisYear.govFundIncome}" />
        </td>
        
        <td>         
        <c:out value="${budgetStatementModel.lastYear.govFundIncome}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.budgetRatio.govFundIncome}" />
        </td>
        
        <td>        
    	<c:out value="${budgetStatementModel.growthRate.govFundIncome}" />        
        </td>
		
		</tr>
		
		<tr>
			<td> <appfuse:label key="budgetStatementInfo.paymentTotal"/> </td>
       
        <td>        
        <c:out value="${budgetStatementModel.fullYearBudget.paymentTotal}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.thisYear.paymentTotal}" />
        </td>
        
        <td>         
        <c:out value="${budgetStatementModel.lastYear.paymentTotal}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.budgetRatio.paymentTotal}" />
        </td>
        
        <td>        
    	<c:out value="${budgetStatementModel.growthRate.paymentTotal}" />        
        </td>
		
		</tr>
		
		<tr>
			<td> <appfuse:label key="budgetStatementInfo.budgetPayTotal"/> </td>
       
        <td>        
        <c:out value="${budgetStatementModel.fullYearBudget.budgetPayTotal}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.thisYear.budgetPayTotal}" />
        </td>
        
        <td>         
        <c:out value="${budgetStatementModel.lastYear.budgetPayTotal}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.budgetRatio.budgetPayTotal}" />
        </td>
        
        <td>        
    	<c:out value="${budgetStatementModel.growthRate.budgetPayTotal}" />        
        </td>
		
		</tr>
		
		<tr>
		
			<td> <appfuse:label key="budgetStatementInfo.govFundPayment"/> </td>
       
        <td>        
        <c:out value="${budgetStatementModel.fullYearBudget.govFundPayment}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.thisYear.govFundPayment}" />
        </td>
        
        <td>         
        <c:out value="${budgetStatementModel.lastYear.govFundPayment}" />
        </td>
        
        <td>          
        <c:out value="${budgetStatementModel.budgetRatio.govFundPayment}" />
        </td>
        
        <td>        
    	<c:out value="${budgetStatementModel.growthRate.govFundPayment}" />        
        </td>
		
		</tr>
	</table>
	    

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty budgetStatementModel.thisYear.id}">
          <button type="submit" class="btn btn-default" name="method"  value="Delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="method" value="Cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    
	</form:form>
</div>

<v:javascript formName="budgetStatementModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#statementType').change(function(){
		var p1=$(this).children('option:selected').val(); 
		if (p1 == 'profit_sheet')
		{
			window.location.href='<c:url value="/finance/profitStatement">
				<c:param name="counterpartyId" value="${budgetStatementModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}" />
				</c:url>'; 
		} 
		else if (p1 == 'balance_sheet')
		{
			<c:if test='${param.ctype == institution}' >
				window.location.href='<c:url value="/finance/instBalanceSheet">
				<c:param name="counterpartyId" value="${budgetStatementModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}" />
				</c:url>';
			</c:if>
			
			<c:if test='${param.ctype != institution}' >
				window.location.href='<c:url value="/finance/corpBalanceSheet">
				<c:param name="counterpartyId" value="${budgetStatementModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}" />
				</c:url>';
			</c:if>
			
		}
		else if (p1 == 'cash_flow_sheet')
		{
			
		}
		
	})
})
</script>

