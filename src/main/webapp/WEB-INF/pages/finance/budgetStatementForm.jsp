<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="budgetStatementForm.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-2">
<h2><fmt:message key='budgetStatementForm.heading'/></h2> 
</div>
 
<div class="col-sm-8">	
	
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="budgetStatementFormModel" method="post" action="budgetStatementForm" id="budgetStatementFormModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
	<div class=row>
    <div class="col-sm-2">   
        <appfuse:label styleClass="control-label" key="budgetStatementForm.projectName"/>
    </div>    
    <div class="col-sm-2">                     
        <c:out value="TestProject" />            
    </div>
   
    <div class="col-sm-2">    
        <appfuse:label styleClass="control-label" key="budgetStatementForm.counterpartyName"/>
    </div>   
    <div class="col-sm-2">     
        <c:out value="Test CounterParty" />        
    </div>
    </div>	
	
	<div class=row>   	    
    <spring:bind path="budgetStatementFormModel.reportMonth">
    </spring:bind>
    <div class="col-sm-2">  
        <appfuse:label styleClass="control-label" key="budgetStatementForm.reportMonth"/>
    </div>
    <div class="col-sm-2">      
        <form:select path="reportMonth">    		
			<form:options items="${availReportMonths}" />
		</form:select>
        <form:errors path="reportMonth" cssClass="help-block"/>
    </div>	
    
    
    <spring:bind path="budgetStatementFormModel.budgetType">
    
    </spring:bind>
    <div class="col-sm-2">  
        <appfuse:label styleClass="control-label" key="budgetStatementForm.budgetType"/>
    </div>
    <div class="col-sm-2">      
        <form:select path="budgetType">    		
			<form:options items="${budgetTypes}" />
		</form:select>
        <form:errors path="budgetType" />
    </div>
    </div>   	
	
	 <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.incomeTotal"/> </div>
        <spring:bind path="budgetStatementFormModel.incomeTotal">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="incomeTotal" id="incomeTotal" maxlength="20" />
        <form:errors path="incomeTotal" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.budgetIncomeTotal"/> </div>
        <spring:bind path="budgetStatementFormModel.budgetIncomeTotal">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="budgetIncomeTotal" id="budgetIncomeTotal" maxlength="20" />
        <form:errors path="budgetIncomeTotal" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.taxIncome"/> </div>
        <spring:bind path="budgetStatementFormModel.taxIncome">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="taxIncome" id="taxIncome" maxlength="20" />
        <form:errors path="taxIncome" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.nonTaxIncome"/> </div>
        <spring:bind path="budgetStatementFormModel.nonTaxIncome">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="nonTaxIncome" id="nonTaxIncome" maxlength="20" />
        <form:errors path="nonTaxIncome" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.govFundIncome"/> </div>
        <spring:bind path="budgetStatementFormModel.govFundIncome">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="govFundIncome" id="govFundIncome" maxlength="20" />
        <form:errors path="govFundIncome" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.paymentTotal"/> </div>
        <spring:bind path="budgetStatementFormModel.paymentTotal">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="paymentTotal" id="paymentTotal" maxlength="20" />
        <form:errors path="paymentTotal" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.budgetPayTotal"/> </div>
        <spring:bind path="budgetStatementFormModel.budgetPayTotal">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="budgetPayTotal" id="budgetPayTotal" maxlength="20" />
        <form:errors path="budgetPayTotal" cssClass="help-block"/>
    	</div>        
    </div>    
    
    <div class="row">       
        <div class="col-md-2"> <appfuse:label key="budgetStatementForm.govFundPayment"/> </div>
        <spring:bind path="budgetStatementFormModel.govFundPayment">
    	<div class="col-md-2">
   		 </spring:bind>       
        <form:input path="govFundPayment" id="govFundPayment" maxlength="20" />
        <form:errors path="govFundPayment" cssClass="help-block"/>
    	</div>        
    </div>      

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty budgetStatementFormModel.id}">
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

<v:javascript formName="budgetStatementFormModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
