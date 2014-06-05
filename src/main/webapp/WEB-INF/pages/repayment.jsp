<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="repayment.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
<c:set var="delObject" scope="request"><fmt:message key="repayment.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-10">
    <h2><fmt:message key='repayment.heading'/></h2>
 	<form name="repayment" action="/repayment" method="post">
 	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/repaymentForm'/>">
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
 
    <display:table name="repaymentList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="repayment" export="false" pagesize="25">
		<display:column style="width: 5%">
    		<input type="checkbox" name="id" value="<c:out value='${repayment.id}'/>" 
    		<c:if test="${param.id == repayment.id and method != 'Save'}">checked="checked"</c:if> style="margin: 0 0 0 4px" onclick="radio(this)" />
  		</display:column>
        <display:column property="repaymentTime" format="{0,date,yyyy-MM-dd}" sortable="true" titleKey="repayment.repaymentTime"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="repayment.id"/>
        <display:column sortable="false" titleKey="repayment.type">
        		<fmt:message key="${repayment.type}"/>
        </display:column>
        <display:column property="amount" sortable="false" titleKey="repayment.amount"/>
        <display:column property="comment" sortable="false" titleKey="repayment.comment"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="repayment.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="repayment.heading"/></display:setProperty>
    </display:table>
    </form> 
    
    
</div>
