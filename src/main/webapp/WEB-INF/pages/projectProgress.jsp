<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectProgress.title"/></title>
    <meta name="menu" content="ProjectInfoSubMenu"/>
</head>
<c:set var="delObject" scope="request"><fmt:message key="projectProgress.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-10">
    <h2><fmt:message key='projectProgress.heading'/></h2>
    <form name="projectProgress" action="/projectProgress" method="post">
 	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/projectProgress'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <button type="submit" class="btn btn-primary" name="method" value="Delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-remove"></i> <fmt:message key="button.delete"/>
        </button>  
        <button type="submit" class="btn btn-primary" name="method" value="Edit">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.edit"/>
        </button>    
        <a class="btn btn-default" href="<c:url value='/projectProgressForm?id=${sessionScope.project_info_id}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
 
    <display:table name="progressList" class="table table-condensed table-striped table-hover" requestURI=""
                   id="progress" export="false" pagesize="25">
		<display:column style="width: 5%">
    		<input type="checkbox" name="id" value="<c:out value='${progress.id}'/>" 
    		<c:if test="${param.id == progress.id and method != 'Save'}">checked="checked"</c:if> style="margin: 0 0 0 4px" onclick="radio(this)" />
  		</display:column>
        <display:column property="deadline" format="{0,date,yyyy-MM}" sortable="true" titleKey="projectProgress.deadline"/>
        <display:column sortable="false" titleKey="projectProgress.investmentType">
        		<fmt:message key="${progress.capitalInvestmentType.title}"/>
        </display:column>
        <display:column property="projectName" sortable="false" titleKey="projectProgress.projectName"/>
        <display:column sortable="false" titleKey="projectProgress.type">
        	<c:if test='${progress.investment}'>
        		<fmt:message key="projectProgress.type.investment"/>
        	</c:if>
        	<c:if test='${!progress.investment}'>
        		<fmt:message key="projectProgress.type.repayment"/>
        	</c:if>
        </display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="projectProgress.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="projectProgress.heading"/></display:setProperty>
    </display:table>
    </form> 
    
  
    
</div>
