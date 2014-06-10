<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="corpBalanceSheet.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='corpBalanceSheet.heading'/></h2>
    <span>
    	<c:if test = '${param.type == "counterparty"}' >
    		<fmt:message key='corpBalanceSheet.counterpartyName'/> 
    	</c:if>
    	<c:if test = '${param.type == "guarantor"}' >
    		<fmt:message key='corpBalanceSheet.guarantorName'/> 
    	</c:if>
    	: &nbsp; <c:out value="${corpBalanceSheetModel.counterpartyName}"/>, <fmt:message key="${param.ctype}"/>
    </span>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="corpBalanceSheetModel" method="post" action="/finance/corpBalanceSheet" id="corpBalanceSheetModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
	<input type="hidden" name="type" value='<c:out value="${param.type}" />' />
	<input type="hidden" name="ctype" value='<c:out value="${param.ctype}" />' />
	
    <div class="row">
    <div class="col-sm-7 form-group">
        <appfuse:label styleClass="control-label" key="report.type.name"/>: 
        <form:select path="statementType" id="statementType">    		
			<form:options items="${statementTypes}" />
		</form:select>        
    </div>  
    
    <div class="col-sm-5">
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.reportYear"/>:
        <fmt:formatDate value="${corpBalanceSheetModel.reportTime}" pattern="${shortDatePattern}" />
    </div>	
    </div>
    
    <table class="table table-condensed">
        <tr>
            <th><appfuse:label key="corpBalanceSheet.itemName"/></th>
            <th><appfuse:label key="corpBalanceSheet.beginValue"/></th>
            <th><appfuse:label key="corpBalanceSheet.endValue"/></th>
        </tr>   
        <tr>
            <td><appfuse:label key="corpBalanceSheet.inventory"/> </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet"> 
                <div>
                </spring:bind>        
                    <form:input path="beginBalSheet.inventory" id="inventory" maxlength="20" />
                    <form:errors path="beginBalSheet.inventory" />
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.inventory" id="inventory" maxlength="20" />
                    <form:errors path="endBalSheet.inventory" />
                </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.cash"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">    
                <div class="">    
                </spring:bind>        
                    <form:input path="beginBalSheet.cash" id="cash" maxlength="20"/>
                    <form:errors path="beginBalSheet.cash"/>
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.cash" id="cash" maxlength="20" />
                    <form:errors path="endBalSheet.cash" />
                </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableNote"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
                <div class="">
                </spring:bind>        
                    <form:input path="beginBalSheet.receivableNote" id="receivableNote" maxlength="20" />
                    <form:errors path="beginBalSheet.receivableNote"/>
                </div>
            </td>
            <td>
                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
                <div class="">
                </spring:bind>        
                    <form:input path="endBalSheet.receivableNote" id="receivableNote" maxlength="20" />
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
        <form:input path="beginBalSheet.receivables" id="receivables" maxlength="20" />
        <form:errors path="beginBalSheet.receivables" />
        </div>
            </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.receivables" id="receivables" maxlength="20" />
        <form:errors path="endBalSheet.receivables" />
        </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableOther"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.receivableOther" id="receivableOther" maxlength="20" />
        <form:errors path="beginBalSheet.receivableOther"/>
        </div>
            </td>
            <td>
                    <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.receivableOther" id="receivableOther" maxlength="20" />
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
        <form:input path="beginBalSheet.liquidAsset" id="liquidAsset" maxlength="20" />
        <form:errors path="beginBalSheet.liquidAsset" />
        </div>


            </td>
            <td>
                    <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.liquidAsset" id="liquidAsset" maxlength="20" />
        <form:errors path="endBalSheet.liquidAsset" />
        </div>

            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.totalAsset"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.totalAsset" id="totalAsset" maxlength="20" />
        <form:errors path="beginBalSheet.totalAsset" />
        </div>


            </td>
            <td>


                <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.totalAsset" id="totalAsset" maxlength="20" />
        <form:errors path="endBalSheet.totalAsset" />
        </div>
            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.prepayment"/></td>
            <td>
                <spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.prepayment" id="prepayment" maxlength="20" />
        <form:errors path="beginBalSheet.prepayment" />
        </div>


            </td>
            <td>
                    <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.prepayment" id="prepayment" maxlength="20" />
        <form:errors path="endBalSheet.prepayment" />
        </div>

            </td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.shortLoan"/> </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.beginBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.shortLoan" id="shortLoan" maxlength="20" />
        <form:errors path="beginBalSheet.shortLoan" />
        </div>
            </td>
            <td>
            <spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.shortLoan" id="shortLoan" maxlength="20" />
        <form:errors path="endBalSheet.shortLoan" />
        </div>
            </td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.payableNote"/> </td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.payableNote" id="payableNote" maxlength="20" />
        <form:errors path="beginBalSheet.payableNote" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.payableNote" id="payableNote" maxlength="20" />
        <form:errors path="endBalSheet.payableNote" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.payable"/> </td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.payable" id="payable" maxlength="20" />
        <form:errors path="beginBalSheet.payable" />
        </div>
        </td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.payable" id="payable" maxlength="20" />
        <form:errors path="endBalSheet.payable" />
        </div></td>
        </tr>
        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.prereceive"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.prereceive" id="prereceive" maxlength="20" />
        <form:errors path="beginBalSheet.prereceive" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.prereceive" id="prereceive" maxlength="20" />
        <form:errors path="endBalSheet.prereceive" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.liquidDebt"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
         <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.liquidDebt" id="liquidDebt" maxlength="20" />
        <form:errors path="beginBalSheet.liquidDebt" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.liquidDebt" id="liquidDebt" maxlength="20" />
        <form:errors path="endBalSheet.liquidDebt" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.longLoan"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.longLoan" id="longLoan" maxlength="20" />
        <form:errors path="beginBalSheet.longLoan" />
        </div></td>
            <td> <spring:bind path="corpBalanceSheetModel.endBalSheet">     
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.longLoan" id="longLoan" maxlength="20" />
        <form:errors path="endBalSheet.longLoan" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.totalDebt"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.totalDebt" id="totalDebt" maxlength="20" />
        <form:errors path="beginBalSheet.totalDebt" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.totalDebt" id="totalDebt" maxlength="20" />
        <form:errors path="endBalSheet.totalDebt" />
        </div></td>
        </tr>

        <tr>
            <td> <appfuse:label styleClass="control-label" key="corpBalanceSheet.actualCapital"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.actualCapital" id="actualCapital" maxlength="20" />
        <form:errors path="beginBalSheet.actualCapital" />
        </div>
        </td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.actualCapital" id="actualCapital" maxlength="20" />
        <form:errors path="endBalSheet.actualCapital" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.unAssignedProfit"/> </td>
            <td> <spring:bind path="corpBalanceSheetModel.beginBalSheet">       
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20" />
        <form:errors path="beginBalSheet.unAssignedProfit" />
        </div>
        </td>
            <td> <spring:bind path="corpBalanceSheetModel.endBalSheet">     
         <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20" />
        <form:errors path="endBalSheet.unAssignedProfit" />
        </div></td>
        </tr>

        <tr>
            <td><appfuse:label styleClass="control-label" key="corpBalanceSheet.netAsset"/></td>
            <td><spring:bind path="corpBalanceSheetModel.beginBalSheet">        
        <div class="">
        </spring:bind>        
        <form:input path="beginBalSheet.netAsset" id="netAsset" maxlength="20" />
        <form:errors path="beginBalSheet.netAsset" />
        </div></td>
            <td><spring:bind path="corpBalanceSheetModel.endBalSheet">      
        <div class="">
        </spring:bind>        
        <form:input path="endBalSheet.netAsset" id="netAsset" maxlength="20" />
        <form:errors path="endBalSheet.netAsset" />
        </div></td>
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
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}"/>
				</c:url>'; 
		} 
		else if (p1 == 'balance_sheet')
		{
			<c:if test='${param.ctype == institution}' >
				window.location.href='<c:url value="/finance/instBalanceSheet">
				<c:param name="counterpartyId" value="${corpBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}"/>
				</c:url>';
			</c:if>
		}
		else if (p1 == 'budget_sheet')
		{
			window.location.href='<c:url value="/finance/budgetStatementForm">
				<c:param name="counterpartyId" value="${corpBalanceSheetModel.counterpartyId}"/>
				<c:param name="type" value="${param.type}"/>
				<c:param name="ctype" value="${param.ctype}"/>
				</c:url>'; 
		}
		else if (p1 == 'cash_flow_sheet')
		{
			
		}
		
	})
})
</script>

