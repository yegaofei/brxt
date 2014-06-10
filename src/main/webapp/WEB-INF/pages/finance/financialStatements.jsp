<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="financialStatements.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
<c:set var="delObject" scope="request"><fmt:message key="financialStatements.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    
<div class="col-sm-10">
    <h2><fmt:message key='financialStatements.title'/></h2>
    
    <form name="financialStatements" action="/finance/financialStatements" method="post">
	
	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/finance/addStatements'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <button type="submit" class="btn btn-primary" name="method" value="Delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-remove"></i> <fmt:message key="button.delete"/>
        </button>  
        <button type="submit" class="btn btn-primary" name="method" value="Edit">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.edit"/>
        </button>    
        <a class="btn btn-default" href="<c:url value='/projectInfoForm?id=${sessionScope.project_info_id}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    
	<display:table name="financeStatementList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="financeStatement" export="false" pagesize="25">
		<display:column style="width: 5%">
    		<input type="checkbox" name="id" value="<c:out value='${financeStatement.id}'/>" 
    		<c:if test="${param.id == financeStatement.id and method != 'Save'}">checked="checked"</c:if> style="margin: 0 0 0 4px" onclick="radio(this)" />
  		</display:column>       
        <display:column sortable="false" titleKey="financeStatement.statementType">
        		<fmt:message key="${financeStatement.statementType.title}"/>
        </display:column>
        <display:column sortable="false" titleKey="financeStatement.tradingRelationship">
        		<fmt:message key="${financeStatement.tradingRelationship.title}"/>
        </display:column>
        <display:column property="counterpartyName" sortable="false" titleKey="financeStatement.counterpartyName"/>
        <display:column property="statementTime" sortable="false" titleKey="financeStatement.statementTime"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="financeStatement.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="financeStatement.heading"/></display:setProperty>
    </display:table>
     
    </form> 
    
</div>
