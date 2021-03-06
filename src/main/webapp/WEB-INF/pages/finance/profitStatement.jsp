<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="profitStatement.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>  
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='profitStatement.heading'/></h2>
    <span>
    	<c:if test = '${profitStatementModel.tradingRelationship.title == "counterparty"}' >
    		<fmt:message key='corpBalanceSheet.counterpartyName'/> 
    	</c:if>
    	<c:if test = '${profitStatementModel.tradingRelationship.title == "guarantor"}' >
    		<fmt:message key='corpBalanceSheet.guarantorName'/> 
    	</c:if>
    	: &nbsp; <c:out value="${profitStatementModel.counterpartyName}"/>, <fmt:message key="${profitStatementModel.counterpartyType}"/>
    </span>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="profitStatementModel" method="post" action="profitStatement" id="profitStatementModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="tradingRelationship"/>
	<form:hidden path="counterpartyType"/>
 	<input type="hidden" name="type" value='<c:out value="${profitStatementModel.tradingRelationship}" />' />
	<input type="hidden" name="ctype" value='<c:out value="${profitStatementModel.counterpartyType}" />' />
	<input type="hidden" name="id" value='<c:out value="${param.id}" />' />
	
	<div class="row">
    <div class="col-sm-5 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <c:if test="${profitStatementModel.counterpartyType == 'institution'}">
        	<form:select path="statementType" id="statementType" class="form-control input-sm">    		
				<form:options items="${statementTypes1}" />
			</form:select>  
		</c:if>  
		<c:if test="${profitStatementModel.counterpartyType != 'institution'}">
        	<form:select path="statementType" id="statementType" class="form-control input-sm">    		
				<form:options items="${statementTypes2}" />
			</form:select>  
		</c:if>         
    </div>  
	    
    <div class="col-sm-7 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        <appfuse:label styleClass="control-label" key="profitStatement.reportYear"/>:
		<c:if test="${empty param.id}">
        	<form:input path="reportTime" id="reportTime" maxlength="20"  class="form-control input-sm"/>
        </c:if>
        <c:if test="${not empty param.id}">
        	<form:input path="reportTime" readonly="true" id="nonReportTime" maxlength="20"  class="form-control input-sm"/>
        </c:if>
    </div>	
	</div>
	
	<table class="table table-condensed">
		 <tr>
            <th><appfuse:label key="profitStatement.itemName"/></th>
            <th><appfuse:label key="profitStatement.endValue"/><fmt:message key="currency.unit.yuan"/></th>
        </tr>   
        
        <tr>
            <td><appfuse:label key="profitStatement.operatingIncome"/> </td>
            <td>
				<spring:bind path="profitStatementModel.endBalSheet">
        		<div class="">
    			</spring:bind>        
        		<form:input path="endBalSheet.operatingIncome" id="operatingIncome" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.operatingIncome" />
        		</div>               
            </td>
        </tr>
	
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingCost"/>  </td>
            <td>
				<spring:bind path="profitStatementModel.endBalSheet">    	
 				<div class="">       
    			</spring:bind>        
        		<form:input path="endBalSheet.operatingCost" id="operatingCost" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.operatingCost" />
        		</div>         
            </td>
        </tr>
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingProfit"/> </td>
            <td>
				<spring:bind path="profitStatementModel.endBalSheet">    	
				<div class="">        
    			</spring:bind>        
        		<form:input path="endBalSheet.operatingProfit" id="operatingProfit" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.operatingProfit" />
        		</div>
            </td>
        </tr>
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.interestCost"/> </td>
            <td>
        		<spring:bind path="profitStatementModel.endBalSheet">    	
        		<div class="">
    			</spring:bind>        
        		<form:input path="endBalSheet.interestCost" id="interestCost" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.interestCost" />
        		</div>       
            </td>
        </tr>                	
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingTax"/> </td>
            <td>
				<spring:bind path="profitStatementModel.endBalSheet">    	
				<div class="">        
    			</spring:bind>        
        		<form:input path="endBalSheet.operatingTax" id="operatingTax" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.operatingTax" />
        		</div>
            </td>
        </tr>                	
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.netProfit"/>  </td>
            <td>
                <spring:bind path="profitStatementModel.endBalSheet">    	
        		<div class="">
    			</spring:bind>        
        		<form:input path="endBalSheet.netProfit" id="netProfit" maxlength="20" class="form-control input-sm"/>
        		<form:errors path="endBalSheet.netProfit" />
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

<v:javascript formName="profitStatementModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#statementType').change(function(){
		var p1=$(this).children('option:selected').val(); 
		if (p1 == 'balance_sheet')
		{
			<c:if test='${profitStatementModel.counterpartyType == "institution"}' >
				window.location.href='<c:url value="/finance/instBalanceSheet">
				<c:param name="counterpartyId" value="${profitStatementModel.counterpartyId}"/>
				<c:param name="type" value="${profitStatementModel.tradingRelationship}"/>
				<c:param name="ctype" value="${profitStatementModel.counterpartyType}" />
				</c:url>';
			</c:if>
			
			<c:if test='${profitStatementModel.counterpartyType != "institution"}' >
				window.location.href='<c:url value="/finance/corpBalanceSheet">
				<c:param name="counterpartyId" value="${profitStatementModel.counterpartyId}"/>
				<c:param name="type" value="${profitStatementModel.tradingRelationship}"/>
				<c:param name="ctype" value="${profitStatementModel.counterpartyType}" />
				</c:url>';
			</c:if>
		}
		else if (p1 == 'budget_sheet')
		{
			window.location.href='<c:url value="/finance/budgetStatementForm">
				<c:param name="counterpartyId" value="${profitStatementModel.counterpartyId}"/>
				<c:param name="type" value="${profitStatementModel.tradingRelationship}"/>
				<c:param name="ctype" value="${profitStatementModel.counterpartyType}"/>
				</c:url>'; 
		}
		else if (p1 == 'cash_flow_sheet')
		{
			
		}
		
	})
})
</script>
<script>
  $(function() {
    $('#reportTime').datepicker({
				language: 'zh-CN',
				format: '<c:out value="${shortDatePatternJs}" />',
				autoclose: true,
				minViewMode: 1
			});
  });
</script>
