<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="budgetStatementInfo.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>

<div class="col-sm-10">
 
 <h2><fmt:message key='budgetStatementInfo.heading'/></h2>

 <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/budgetStatementForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <!--    <a class="btn btn-default" href="<c:url value='/budgetStatementList'/>">
            <i class="icon-ok"></i> <fmt:message key="button.list"/></a>  -->    
        <a class="btn btn-default" href="<c:url value='/projectInfoForm?id=${sessionScope.project_info_id}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
 </div>
 

    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="budgetStatementInfoModel" method="post" action="budgetStatementInfo" id="budgetStatementInfoModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
	<div class="row">
    <spring:bind path="budgetStatementInfoModel.projectName">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="budgetStatementInfo.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

    <spring:bind path="budgetStatementInfoModel.counterpartyName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="budgetStatementInfo.counterpartyName"/>
        <form:input path="counterpartyName" id="counterpartyName" maxlength="20" cssClass="form-control"/>
        <form:errors path="counterpartyName" cssClass="help-block"/>
    </div>
	</div>
	

	<div class="row">
    <spring:bind path="budgetStatementInfoModel.reportName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="budgetStatementInfo.reportName"/>        
    </div>	
	    
    <spring:bind path="budgetStatementInfoModel.reportYear">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="budgetStatementInfo.reportYear"/>
        <form:select path="reportYear">    		
			<form:options items="${availReportYears}" />
		</form:select>
        <form:errors path="reportYear" cssClass="help-block"/>
    </div>	
	</div>
	
	<div class="row">	 	
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.itemName"/> </div>
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.fullYearBudget"/> </div>
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.thisYear"/>   </div>
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.lastYear"/>   </div>   
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.budgetRatio"/>   </div> 
       <div class="col-md-2"> <appfuse:label styleClass="control-label" key="budgetStatementInfo.growthRate"/>   </div>    
    </div>
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.incomeTotal"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.incomeTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.incomeTotal}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.incomeTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.incomeTotal}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.incomeTotal}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.budgetIncomeTotal"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.budgetIncomeTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.budgetIncomeTotal}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.budgetIncomeTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.budgetIncomeTotal}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.budgetIncomeTotal}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.taxIncome"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.taxIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.taxIncome}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.taxIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.taxIncome}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.taxIncome}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.nonTaxIncome"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.nonTaxIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.nonTaxIncome}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.nonTaxIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.nonTaxIncome}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.nonTaxIncome}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.govFundIncome"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.govFundIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.govFundIncome}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.govFundIncome}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.govFundIncome}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.govFundIncome}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.paymentTotal"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.paymentTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.paymentTotal}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.paymentTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.paymentTotal}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.paymentTotal}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.budgetPayTotal"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.budgetPayTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.budgetPayTotal}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.budgetPayTotal}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.budgetPayTotal}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.budgetPayTotal}" />        
        </div>
    </div>    
    
    <div class="row">	 	
        <div class="col-md-2"> <appfuse:label key="budgetStatementInfo.govFundPayment"/> </div>
       
        <div class="col-md-2">        
        <c:out value="${budgetStatementInfoModel.fullYearBudget.govFundPayment}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.thisYear.govFundPayment}" />
        </div>
        
        <div class="col-md-2">         
        <c:out value="${budgetStatementInfoModel.lastYear.govFundPayment}" />
        </div>
        
        <div class="col-md-2">          
        <c:out value="${budgetStatementInfoModel.budgetRatio.govFundPayment}" />
        </div>
        
        <div class="col-md-2">        
    	<c:out value="${budgetStatementInfoModel.growthRate.govFundPayment}" />        
        </div>
    </div>    
    
        
	</form:form>
	
</div>	

<v:javascript formName="budgetStatementInfoModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
