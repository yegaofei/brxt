<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="profitStatement.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
 
<div class="col-sm-3">
    <h2><fmt:message key='profitStatement.heading'/></h2>
    <span><fmt:message key='profitStatement.counterpartyName'/>: &nbsp; <c:out value="${profitStatementModel.counterpartyName}"/></span>
</div>
 
<div class="col-sm-7">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="profitStatementModel" method="post" action="profitStatement" id="profitStatementModel" cssClass="well" >
	<form:hidden path="projectId"/>
	<form:hidden path="counterpartyId"/>
	<form:hidden path="reportName"/>
 
    <spring:bind path="profitStatementModel.projectName">
    <div class="col-sm-6 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="profitStatement.projectName"/>
        <form:input path="projectName" id="projectName" maxlength="50" autofocus="true" cssClass="form-control"/>
        <form:errors path="projectName" cssClass="help-inline"/>
    </div>

	

	<div class="row">
    <spring:bind path="profitStatementModel.reportName">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="profitStatement.reportName"/>        
    </div>	
	    
    <spring:bind path="profitStatementModel.reportYear">
    <div class="col-sm-4 form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="profitStatement.reportYear"/>
        <form:select path="reportYear">    		
			<form:options items="${availReportYears}" />
		</form:select>
        <form:errors path="reportYear" cssClass="help-block"/>
    </div>	
	</div>
	
	<table class="table table-condensed">
		 <tr>
            <th><appfuse:label key="profitStatement.itemName"/></th>
            <th><appfuse:label key="profitStatement.beginValue"/></th>
            <th><appfuse:label key="profitStatement.endValue"/></th>
        </tr>   
        
        <tr>
            <td><appfuse:label key="profitStatement.operatingIncome"/> </td>
            <td>
        <spring:bind path="profitStatementModel.beginBalSheet">
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.operatingIncome" id="operatingIncome" maxlength="20" />
        <form:errors path="beginBalSheet.operatingIncome" />
        </div>                 
            </td>
            <td>
         <spring:bind path="profitStatementModel.endBalSheet">
        <div class="">
    	</spring:bind>        
        <form:input path="endBalSheet.operatingIncome" id="operatingIncome" maxlength="20" />
        <form:errors path="endBalSheet.operatingIncome" />
        </div>               
            </td>
        </tr>
	
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingCost"/>  </td>
            <td>
 	        <spring:bind path="profitStatementModel.beginBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.operatingCost" id="operatingCost" maxlength="20"/>
        <form:errors path="beginBalSheet.operatingCost"/>
 		</div>                
            </td>
            <td>
               <spring:bind path="profitStatementModel.endBalSheet">    	
 		<div class="">       
    	</spring:bind>        
        <form:input path="endBalSheet.operatingCost" id="operatingCost" maxlength="20" />
        <form:errors path="endBalSheet.operatingCost" />
        </div>         
            </td>
        </tr>
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingProfit"/> </td>
            <td>
         <spring:bind path="profitStatementModel.beginBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.operatingProfit" id="operatingProfit" maxlength="20" />
        <form:errors path="beginBalSheet.operatingProfit"/>
		</div>                
            </td>
            <td>
                        <spring:bind path="profitStatementModel.endBalSheet">    	
		<div class="">        
    	</spring:bind>        
        <form:input path="endBalSheet.operatingProfit" id="operatingProfit" maxlength="20" />
        <form:errors path="endBalSheet.operatingProfit" />
        </div>
            </td>
        </tr>
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.interestCost"/> </td>
            <td>
               
        <spring:bind path="profitStatementModel.beginBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.interestCost" id="interestCost" maxlength="20" />
        <form:errors path="beginBalSheet.interestCost" />
        </div>         
            </td>
            <td>
        <spring:bind path="profitStatementModel.endBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="endBalSheet.interestCost" id="interestCost" maxlength="20" />
        <form:errors path="endBalSheet.interestCost" />
        </div>       
            </td>
        </tr>                	
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.operatingTax"/> </td>
            <td>
                         <spring:bind path="profitStatementModel.beginBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.operatingTax" id="operatingTax" maxlength="20" />
        <form:errors path="beginBalSheet.operatingTax"/>
		</div>
            </td>
            <td>
                        <spring:bind path="profitStatementModel.endBalSheet">    	
		<div class="">        
    	</spring:bind>        
        <form:input path="endBalSheet.operatingTax" id="operatingTax" maxlength="20" />
        <form:errors path="endBalSheet.operatingTax" />
        </div>
            </td>
        </tr>                	
        
        <tr>
            <td><appfuse:label styleClass="control-label" key="profitStatement.netProfit"/>  </td>
            <td>
                  <spring:bind path="profitStatementModel.beginBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="beginBalSheet.netProfit" id="netProfit" maxlength="20" />
        <form:errors path="beginBalSheet.netProfit" />
        </div>
            </td>
            <td>
                 <spring:bind path="profitStatementModel.endBalSheet">    	
        <div class="">
    	</spring:bind>        
        <form:input path="endBalSheet.netProfit" id="netProfit" maxlength="20" />
        <form:errors path="endBalSheet.netProfit" />
        </div>
            </td>
        </tr>                	                
	</table>

   <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="method" value="Save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty profitStatementModel.beginBalSheet.id}">
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

<v:javascript formName="profitStatementModel" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
