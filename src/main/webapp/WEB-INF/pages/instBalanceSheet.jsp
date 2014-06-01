<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="instBalanceSheet.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='instBalanceSheet.heading'/></h2>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="instBalanceSheetModel" method="post" action="instBalanceSheet" id="instBalanceSheetModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
	<div class="row">
    <spring:bind path="instBalanceSheetModel.projectName">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="instBalanceSheet.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" autofocus="true" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="instBalanceSheetModel.counterpartyName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="instBalanceSheet.counterpartyName"/>
        <form:input path="counterpartyName" id="counterpartyName" maxlength="20" cssClass="form-control"/>
        <form:errors path="counterpartyName" cssClass="help-block"/>
    </div>
	</div>
	

	<div class="row">
    <spring:bind path="instBalanceSheetModel.reportName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="instBalanceSheet.reportName"/>        
    </div>	
	    
    <spring:bind path="instBalanceSheetModel.reportYear">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="instBalanceSheet.reportYear"/>
        <form:select path="reportYear">    		
			<form:options items="${availReportYears}" />
		</form:select>
        <form:errors path="reportYear" cssClass="help-block"/>
    </div>	
	</div>
	
	<div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.itemName"/> </div>
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.beginValue"/> </div>
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.endValue"/>   </div>     
    </div>
    
    <div class="row">	 	
        <div class="col-md-4"> <appfuse:label key="instBalanceSheet.assetGroupTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">
    	
    	</spring:bind>        
        <form:input path="beginBalSheet.assetGroupTotal" id="assetGroupTotal" maxlength="20" />
        <form:errors path="beginBalSheet.assetGroupTotal" />
        </div>
        
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.endBalSheet">
    	</spring:bind>        
        <form:input path="endBalSheet.assetGroupTotal" id="assetGroupTotal" maxlength="20" />
        <form:errors path="endBalSheet.assetGroupTotal" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.assetTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.assetTotal" id="assetTotal" maxlength="20"/>
        <form:errors path="beginBalSheet.assetTotal"/>
 		</div>
 		
 		<div class="col-md-4">       
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.assetTotal" id="assetTotal" maxlength="20" />
        <form:errors path="endBalSheet.assetTotal" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.expenseTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.expenseTotal" id="expenseTotal" maxlength="20" />
        <form:errors path="beginBalSheet.expenseTotal"/>
		</div>
		
		<div class="col-md-4">        
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.expenseTotal" id="expenseTotal" maxlength="20" />
        <form:errors path="endBalSheet.expenseTotal" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.debtGroupTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.debtGroupTotal" id="debtGroupTotal" maxlength="20" />
        <form:errors path="beginBalSheet.debtGroupTotal" />
        </div>
        
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.debtGroupTotal" id="debtGroupTotal" maxlength="20" />
        <form:errors path="endBalSheet.debtGroupTotal" />
        </div>
    </div>
    
     <div class="row">	 	
      <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.debtTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.debtTotal" id="debtTotal" maxlength="20" />
        <form:errors path="beginBalSheet.debtTotal"/>
		</div>
		
		<div class="col-md-4">        
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.debtTotal" id="debtTotal" maxlength="20" />
        <form:errors path="endBalSheet.debtTotal" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.netAssetTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.netAssetTotal" id="netAssetTotal" maxlength="20" />
        <form:errors path="beginBalSheet.netAssetTotal" />
        </div>
        
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.netAssetTotal" id="netAssetTotal" maxlength="20" />
        <form:errors path="endBalSheet.netAssetTotal" />
        </div>
    </div>
    
     <div class="row">	 	
       <div class="col-md-4"> <appfuse:label styleClass="control-label" key="instBalanceSheet.incomeTotal"/> </div>
       
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.beginBalSheet">    	
    	</spring:bind>        
        <form:input path="beginBalSheet.incomeTotal" id="incomeTotal" maxlength="20" />
        <form:errors path="beginBalSheet.incomeTotal" />
        </div>
        
        <div class="col-md-4">
        <spring:bind path="instBalanceSheetModel.endBalSheet">    	
    	</spring:bind>        
        <form:input path="endBalSheet.incomeTotal" id="incomeTotal" maxlength="20" />
        <form:errors path="endBalSheet.incomeTotal" />
        </div>
    </div>

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty instBalanceSheetModel.beginBalSheet.id}">
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

<v:javascript formName="instBalanceSheetModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
