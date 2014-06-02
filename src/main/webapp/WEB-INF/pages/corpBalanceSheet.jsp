<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="corpBalanceSheet.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='corpBalanceSheet.heading'/></h2>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="corpBalanceSheetModel" method="post" action="corpBalanceSheet" id="corpBalanceSheetModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
	<div class="row">
    <spring:bind path="corpBalanceSheetModel.counterpartyName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.counterpartyName"/>
        <form:input path="counterpartyName" id="counterpartyName" maxlength="20" cssClass="form-control"/>
        <form:errors path="counterpartyName" cssClass="help-block"/>
    </div>
	</div>
	

	<div class="row">
    <spring:bind path="corpBalanceSheetModel.reportName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.reportName"/>        
    </div>	
	    
    <spring:bind path="corpBalanceSheetModel.reportYear">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="corpBalanceSheet.reportYear"/>
        <form:select path="reportYear">    		
			<form:options items="${availReportYears}" />
		</form:select>
        <form:errors path="reportYear" cssClass="help-block"/>
    </div>	
	</div>
	
	<div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.itemName"/> </div>
         <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.beginValue"/> </div>
         <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.endValue"/> </div>        
    </div>
    
    <div class="row">	 	
       
        <div class="col-md-4"> <appfuse:label key="corpBalanceSheet.inventory"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.inventory" id="inventory" maxlength="20" />
        <form:errors path="beginBalSheet.inventory" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">
    	</spring:bind>        
        <form:input path="endBalSheet.inventory" id="inventory" maxlength="20" />
        <form:errors path="endBalSheet.inventory" />
        </div>
        
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.cash"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.cash" id="cash" maxlength="20"/>
        <form:errors path="beginBalSheet.cash"/>
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.cash" id="cash" maxlength="20" />
        <form:errors path="endBalSheet.cash" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableNote"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.receivableNote" id="receivableNote" maxlength="20" />
        <form:errors path="beginBalSheet.receivableNote"/>
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.receivableNote" id="receivableNote" maxlength="20" />
        <form:errors path="endBalSheet.receivableNote" />
        </div>
        
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.receivables"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.receivables" id="receivables" maxlength="20" />
        <form:errors path="beginBalSheet.receivables" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.receivables" id="receivables" maxlength="20" />
        <form:errors path="endBalSheet.receivables" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.receivableOther"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.receivableOther" id="receivableOther" maxlength="20" />
        <form:errors path="beginBalSheet.receivableOther"/>
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.receivableOther" id="receivableOther" maxlength="20" />
        <form:errors path="endBalSheet.receivableOther" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.liquidAsset"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.liquidAsset" id="liquidAsset" maxlength="20" />
        <form:errors path="beginBalSheet.liquidAsset" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.liquidAsset" id="liquidAsset" maxlength="20" />
        <form:errors path="endBalSheet.liquidAsset" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.totalAsset"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.totalAsset" id="totalAsset" maxlength="20" />
        <form:errors path="beginBalSheet.totalAsset" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.totalAsset" id="totalAsset" maxlength="20" />
        <form:errors path="endBalSheet.totalAsset" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.prepayment"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.prepayment" id="prepayment" maxlength="20" />
        <form:errors path="beginBalSheet.prepayment" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.prepayment" id="prepayment" maxlength="20" />
        <form:errors path="endBalSheet.prepayment" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.shortLoan"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.shortLoan" id="shortLoan" maxlength="20" />
        <form:errors path="beginBalSheet.shortLoan" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.shortLoan" id="shortLoan" maxlength="20" />
        <form:errors path="endBalSheet.shortLoan" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.payableNote"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.payableNote" id="payableNote" maxlength="20" />
        <form:errors path="beginBalSheet.payableNote" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.payableNote" id="payableNote" maxlength="20" />
        <form:errors path="endBalSheet.payableNote" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.payable"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.payable" id="payable" maxlength="20" />
        <form:errors path="beginBalSheet.payable" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.payable" id="payable" maxlength="20" />
        <form:errors path="endBalSheet.payable" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.prereceive"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.prereceive" id="prereceive" maxlength="20" />
        <form:errors path="beginBalSheet.prereceive" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.prereceive" id="prereceive" maxlength="20" />
        <form:errors path="endBalSheet.prereceive" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.liquidDebt"/> </div>
       
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.liquidDebt" id="liquidDebt" maxlength="20" />
        <form:errors path="beginBalSheet.liquidDebt" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.liquidDebt" id="liquidDebt" maxlength="20" />
        <form:errors path="endBalSheet.liquidDebt" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.longLoan"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.longLoan" id="longLoan" maxlength="20" />
        <form:errors path="beginBalSheet.longLoan" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.longLoan" id="longLoan" maxlength="20" />
        <form:errors path="endBalSheet.longLoan" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.totalDebt"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.totalDebt" id="totalDebt" maxlength="20" />
        <form:errors path="beginBalSheet.totalDebt" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.totalDebt" id="totalDebt" maxlength="20" />
        <form:errors path="endBalSheet.totalDebt" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.actualCapital"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.actualCapital" id="actualCapital" maxlength="20" />
        <form:errors path="beginBalSheet.actualCapital" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.actualCapital" id="actualCapital" maxlength="20" />
        <form:errors path="endBalSheet.actualCapital" />
        </div>
    </div>
    
     <div class="row">	 	
        <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.unAssignedProfit"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20" />
        <form:errors path="beginBalSheet.unAssignedProfit" />
        </div>
        
         <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.unAssignedProfit" id="unAssignedProfit" maxlength="20" />
        <form:errors path="endBalSheet.unAssignedProfit" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="corpBalanceSheet.netAsset"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.netAsset" id="netAsset" maxlength="20" />
        <form:errors path="beginBalSheet.netAsset" />
        </div>
        
        <div class="col-md-4">
        <spring:bind path="corpBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.netAsset" id="netAsset" maxlength="20" />
        <form:errors path="endBalSheet.netAsset" />
        </div>
    </div>
    

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty corpBalanceSheetModel.beginBalSheet.id}">
          <button type="submit" class="btn btn-default" name="method"  value="Delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <a class="btn btn-default" href="<c:url value='/projectInfoForm?id=${sessionScope.project_info_id}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.cancel"/></a>
    </div>
    
	</form:form>
</div>

<v:javascript formName="corpBalanceSheetModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
