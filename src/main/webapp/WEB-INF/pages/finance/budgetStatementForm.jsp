<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="budgetStatementForm.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
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
	<input type="hidden" name="type" value='<c:out value="${param.type}" />' />
	<input type="hidden" name="ctype" value='<c:out value="${param.ctype}" />' />
 
	<div class="row">
    <div class="col-sm-6 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <form:select path="statementType" id="statementType">    		
			<form:options items="${statementTypes}" />
		</form:select>        
    </div>  

    <div class="col-sm-5 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        <appfuse:label styleClass="control-label" key="profitStatement.reportYear"/>:
		<fmt:formatDate value="${budgetStatementModel.reportTime}" pattern="${shortDatePattern}" />
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
				<td> 
					<spring:bind path="budgetStatementModel.thisYearBudget.budgetIncomeTotal">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.budgetIncomeTotal" id="budgetIncomeTotal1"  />
                   	<form:errors path="thisYearBudget.budgetIncomeTotal" /> 
				</td>
				<td> 
					<spring:bind path="budgetStatementModel.thisYear.budgetIncomeTotal">        
                	<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.budgetIncomeTotal" id="budgetIncomeTotal2"  />
                    <form:errors path="thisYear.budgetIncomeTotal" />
				</td>
				<td>
                  	 <c:out value="${budgetStatementModel.lastYear.budgetIncomeTotal}" /> 
				</td>
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
					<spring:bind path="budgetStatementModel.thisYearBudget.taxIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.taxIncome" id="taxIncome1"  />
                   	<form:errors path="thisYearBudget.taxIncome" />         	
        		</td>
        		<td>          
					<spring:bind path="budgetStatementModel.thisYear.taxIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.taxIncome" id="taxIncome2"  />
                   	<form:errors path="thisYear.taxIncome" />         	        	
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
					<spring:bind path="budgetStatementModel.thisYearBudget.nonTaxIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.nonTaxIncome" id="nonTaxIncome1"  />
                   	<form:errors path="thisYearBudget.nonTaxIncome" />         	        			
        		</td>
        		<td>          
					<spring:bind path="budgetStatementModel.thisYear.nonTaxIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.nonTaxIncome" id="nonTaxIncome2"  />
                   	<form:errors path="thisYear.nonTaxIncome" />         	             		
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
					<spring:bind path="budgetStatementModel.thisYearBudget.govFundIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.govFundIncome" id="govFundIncome1"  />
                   	<form:errors path="thisYearBudget.govFundIncome" />                  
        </td>
        <td>          
					<spring:bind path="budgetStatementModel.thisYear.govFundIncome">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.govFundIncome" id="govFundIncome2"  />
                   	<form:errors path="thisYear.govFundIncome" />             
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
					<spring:bind path="budgetStatementModel.thisYearBudget.paymentTotal">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.paymentTotal" id="paymentTotal1"  />
                   	<form:errors path="thisYearBudget.paymentTotal" />                   
        </td>
        <td>          
					<spring:bind path="budgetStatementModel.thisYear.paymentTotal">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.paymentTotal" id="paymentTotal2"  />
                   	<form:errors path="thisYear.paymentTotal" /> 
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
					<spring:bind path="budgetStatementModel.thisYearBudget.budgetPayTotal">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.budgetPayTotal" id="budgetPayTotal1"  />
                   	<form:errors path="thisYearBudget.budgetPayTotal" />                
        </td>
        <td>          
					<spring:bind path="budgetStatementModel.thisYear.budgetPayTotal">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.budgetPayTotal" id="budgetPayTotal2"  />
                   	<form:errors path="thisYear.budgetPayTotal" /> 
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
					<spring:bind path="budgetStatementModel.thisYearBudget.govFundPayment">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYearBudget.govFundPayment" id="govFundPayment1"  />
                   	<form:errors path="thisYearBudget.govFundPayment" /> 
        </td>
        
        <td>          
					<spring:bind path="budgetStatementModel.thisYear.govFundPayment">        
                		<div class="">
                	</spring:bind>        
                    <form:input path="thisYear.govFundPayment" id="govFundPayment2"  />
                   	<form:errors path="thisYear.govFundPayment" />         
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
        <a class="btn btn-default" href="<c:url value='/finance/financialStatements'/>">
            <i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
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
			<c:if test='${param.ctype == "institution"}' >
				window.location.href='<c:url value="/finance/instBalanceSheet">
				<c:param name="counterpartyId" value="${budgetStatementModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}" />
				</c:url>';
			</c:if>
			
			<c:if test='${param.ctype != "institution"}' >
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

