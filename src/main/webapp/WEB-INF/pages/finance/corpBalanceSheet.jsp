<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="corpBalanceSheet.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>  
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='corpBalanceSheet.heading'/></h2>
    <span>
    	<c:if test = '${corpBalanceSheetModel.tradingRelationship.title == "counterparty"}' >
    		<fmt:message key='corpBalanceSheet.counterpartyName'/> 
    	</c:if>
    	<c:if test = '${corpBalanceSheetModel.tradingRelationship.title == "guarantor"}' >
    		<fmt:message key='corpBalanceSheet.guarantorName'/> 
    	</c:if>
    	: &nbsp; <c:out value="${corpBalanceSheetModel.counterpartyName}"/>, <fmt:message key="${corpBalanceSheetModel.counterpartyType}"/>
    </span>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="corpBalanceSheetModel" method="post" action="/finance/corpBalanceSheet" id="corpBalanceSheetModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="tradingRelationship"/>
	<form:hidden path="counterpartyType"/>
	<input type="hidden" name="type" value='<c:out value="${corpBalanceSheetModel.tradingRelationship}" />' />
	<input type="hidden" name="ctype" value='<c:out value="${corpBalanceSheetModel.counterpartyType}" />' />
	<input type="hidden" name="id" value='<c:out value="${param.id}" />' />
	
    <div class="row">
    <div class="col-sm-7 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <c:if test="${corpBalanceSheetModel.counterpartyType == 'institution'}">
        	<form:select path="statementType" id="statementType" class="form-control input-sm">    		
				<form:options items="${statementTypes1}" />
			</form:select>  
		</c:if>  
		<c:if test="${corpBalanceSheetModel.counterpartyType != 'institution'}">
        	<form:select path="statementType" id="statementType" class="form-control input-sm">    		
				<form:options items="${statementTypes2}" />
			</form:select>  
		</c:if>          
    </div>  
    
    <div class="col-sm-5">
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.reportYear"/>:        
        <c:if test="${empty param.id}">
        	<form:input path="reportTime" id="reportTime" maxlength="20"  class="form-control input-sm"/>
        </c:if>
        <c:if test="${not empty param.id}">
        	<form:input path="reportTime" readonly="true" id="nonReportTime" maxlength="20"  class="form-control input-sm"/>
        </c:if>
    </div>	
    </div>
    
    <table class="table table-condensed ">
    	<thead>
        <tr>
            <th><appfuse:label key="corpBalanceSheet.itemName"/></th>
            <th><appfuse:label key="corpBalanceSheet.beginValue"/><fmt:message key="currency.unit.yuan"/></th>
            <th><appfuse:label key="corpBalanceSheet.endValue"/><fmt:message key="currency.unit.yuan"/></th>
        </tr>  
        </thead>
        <tbody> 
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.cash"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">    
                <div class="">    
                </spring:bind>        
                    <form:input path="beginBalSheet.cash" id="cash" maxlength="20" class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.cash"/>
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.cash" id="cash" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.cash" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label key="corpBalanceSheet.inventory"/> </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet"> 
                <div>
                </spring:bind>        
                    <form:input path="beginBalSheet.inventory" id="inventory" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.inventory" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.inventory" id="inventory" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.inventory" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableNote"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.receivableNote" id="receivableNote" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.receivableNote"/>
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.receivableNote" id="receivableNote" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.receivableNote" />
                </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.receivables"/> </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.receivables" id="receivables" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.receivables" />
        </div>
            </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.receivables" id="receivables" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.receivables" />
        </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.prepayment"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.prepayment" id="prepayment" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.prepayment" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.prepayment" id="prepayment" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.prepayment" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableOther"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.receivableOther" id="receivableOther" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.receivableOther"/>
        </div>
            </td>
            <td>
                    <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.receivableOther" id="receivableOther" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.receivableOther" />
        </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.liquidAsset"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.liquidAsset" id="liquidAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.liquidAsset" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.liquidAsset" id="liquidAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.liquidAsset" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.nonLiquid"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.nonLiquid" id="nonLiquid" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.nonLiquid" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.nonLiquid" id="nonLiquid" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.nonLiquid" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.longInvestment"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.longInvestment" id="longInvestment" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.longInvestment" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.longInvestment" id="longInvestment" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.longInvestment" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.fixedAsset"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.fixedAsset" id="fixedAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.fixedAsset" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.fixedAsset" id="fixedAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.fixedAsset" />
                </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.intangibleAsset"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.intangibleAsset" id="intangibleAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.intangibleAsset" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.intangibleAsset" id="intangibleAsset" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.intangibleAsset" />
                </div>
            </td>
        </tr>                        
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.totalAsset"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.totalAsset" id="totalAsset" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.totalAsset" />
        </div>


            </td>
            <td>


                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.totalAsset" id="totalAsset" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.totalAsset" />
        </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.shortLoan"/> </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.beginBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.shortLoan" id="shortLoan" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.shortLoan" />
        </div>
            </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.shortLoan" id="shortLoan" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.shortLoan" />
        </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.payableNote"/> </td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.payableNote" id="payableNote" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.payableNote" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.payableNote" id="payableNote" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.payableNote" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.payable"/> </td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.payable" id="payable" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.payable" />
        </div>
        </td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.payable" id="payable" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.payable" />
        </div></td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.prereceive"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.prereceive" id="prereceive" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.prereceive" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.prereceive" id="prereceive" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.prereceive" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.liquidDebt"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.liquidDebt" id="liquidDebt" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.liquidDebt" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.liquidDebt" id="liquidDebt" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.liquidDebt" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.longLoan"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.longLoan" id="longLoan" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.longLoan" />
        </div></td>
            <td> <spring:bind path="corpBalanceSheetModel.endBalSheet">     
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.longLoan" id="longLoan" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.longLoan" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.totalDebt"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.totalDebt" id="totalDebt" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.totalDebt" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.totalDebt" id="totalDebt" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.totalDebt" />
        </div></td>
        </tr>

        <tr>
            <td> <appfuse:label styleClass="control-label" key="corpBalanceSheet.actualCapital"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.actualCapital" id="actualCapital" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.actualCapital" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.actualCapital" id="actualCapital" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.actualCapital" />
                </div>
            </td>
        </tr>
        
        <tr>
            <td> <appfuse:label styleClass="control-label" key="corpBalanceSheet.capitalReserve"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.capitalReserve" id="capitalReserve" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="beginBalSheet.capitalReserve" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.capitalReserve" id="capitalReserve" maxlength="20"  class="form-control input-sm"/>
                    <form:errors path="endBalSheet.capitalReserve" />
                </div>
            </td>
        </tr>        

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.unAssignedProfit"/> </td>
            <td> <spring:bind path="corpBalanceSheetModel.beginBalSheet">       
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.unAssignedProfit" />
        </div>
        </td>
            <td> <spring:bind path="corpBalanceSheetModel.endBalSheet">     
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.unAssignedProfit" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.netAsset"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.netAsset" id="netAsset" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="beginBalSheet.netAsset" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
        <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.netAsset" id="netAsset" maxlength="20"  class="form-control input-sm"/>
        <form:errors path="endBalSheet.netAsset" />
        </div></td>
        </tr>
        </tbody>
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

<v:javascript formName="corpBalanceSheetModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$('#statementType').change(function(){
		var p1=$(this).children('option:selected').val(); 
		if (p1 == 'profit_sheet')
		{
			window.location.href='<c:url value="/finance/profitStatement">
				<c:param name="counterpartyId" value="${corpBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${corpBalanceSheetModel.tradingRelationship}"/>
				<c:param name="ctype" value="${corpBalanceSheetModel.counterpartyType}"/>
				</c:url>'; 
		} 
		else if (p1 == 'balance_sheet')
		{
			<c:if test='${corpBalanceSheetModel.counterpartyType == "institution"}' >
				window.location.href='<c:url value="/finance/instBalanceSheet">
				<c:param name="counterpartyId" value="${corpBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${corpBalanceSheetModel.tradingRelationship}"/>
				<c:param name="ctype" value="${corpBalanceSheetModel.counterpartyType}"/>
				</c:url>';
			</c:if>
		}
		else if (p1 == 'budget_sheet')
		{
			window.location.href='<c:url value="/finance/budgetStatementForm">
				<c:param name="counterpartyId" value="${corpBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${corpBalanceSheetModel.tradingRelationship}"/>
				<c:param name="ctype" value="${corpBalanceSheetModel.counterpartyType}"/>
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
