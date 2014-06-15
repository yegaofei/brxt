<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="collateral.title"/></title>
    <meta name="menu" content="Collateral"/>
</head>
<c:set var="delObject" scope="request"><fmt:message key="collateral.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-10">
    <h2><fmt:message key='collateral.heading'/></h2>
    
    <form name="collateralListForm" action="${ctx}/collateral/collateralList" method="post">	
	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='${ctx}/collateral/collateralForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <button type="submit" class="btn btn-primary" name="method" value="Delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-remove"></i> <fmt:message key="button.delete"/>
        </button>  
        <button type="submit" class="btn btn-primary" name="method" value="Edit">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.edit"/>
        </button>    
    </div>
    
	<display:table name="collateralList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="collateral" export="false" pagesize="25">
		<display:column style="width: 5%">
    		<input type="checkbox" name="id" value="<c:out value='${collateral.id}'/>" 
    		<c:if test="${param.id == collateral.id and method != 'Save'}">checked="checked"</c:if> style="margin: 0 0 0 4px" onclick="radio(this)" />
  		</display:column>       
        <display:column property="projectName" sortable="true" titleKey="projectInfo.projectName"/>
        <display:column sortable="false" titleKey="collateral.collateralType">
        		<c:if test="${not empty collateral.collateralType}">
        			<fmt:message key="${collateral.collateralType.type}"/>
        		</c:if>
        </display:column>
        <display:column property="executor" sortable="false" titleKey="collateral.executor"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="collateral.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="collateral.heading"/></display:setProperty>
    </display:table>
     
    </form> 
    
</div>
