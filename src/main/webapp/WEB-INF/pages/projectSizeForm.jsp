<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="projectSize.title"/></title>
    <meta name="menu" content="ProjectInfoMenu"/>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='projectSize.heading'/></h2>
 
<form name="projectSizeForm" action="/projectSizeForm" method="post">
<input type="hidden" name="projectInfoId" value="<c:out value='${projectInfoId}'/>" />
<div id="actions" class="btn-group">
		<c:choose>
		<c:when test="${method == 'Edit'}">
    		<button type="submit" class="btn btn-primary" name="method" value="Save">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
		</c:when>
		<c:otherwise>
        <button type="submit" class="btn btn-primary" name="method" value="Add">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.add"/>
        </button>    
        </c:otherwise>
        </c:choose>
        <button type="submit" class="btn btn-primary" name="method" value="Edit">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.edit"/>
        </button>
        <button type="submit" class="btn btn-primary" name="method" value="Delete">
                <i class="icon-remove"></i> <fmt:message key="button.delete"/>
        </button>    
        <a class="btn btn-default" href="<c:url value='/projectInfoForm?id=${projectInfoId}'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>
    
<br/>
<display:table name="projectSizeList" id="projectSize" class="table table-condensed table-striped table-hover">
  <display:column style="width: 5%" title='<input type="checkbox" name="allbox" id="allbox" onclick="javascript:checkAll(this.form)" />'>
    <input type="checkbox" name="id" value="<c:out value='${projectSize.id}'/>" 
    <c:if test="${param.id == projectSize.id and method != 'Save'}">checked="checked"</c:if>
        style="margin: 0 0 0 4px" onclick="radio(this)" />
  </display:column>
  <display:column titleKey="projectSize.startTime" format="{0,date,yyyy-MM-dd}">
    <c:choose>
        <c:when test="${method == 'Edit' and param.id == projectSize.id}">
            <input type="text" name="startTime" style="padding: 0"
                value="<fmt:formatDate value="${projectSize.startTime}" pattern="yyyy-MM-dd" />" />
        </c:when>
        <c:otherwise><fmt:formatDate value="${projectSize.startTime}" pattern="yyyy-MM-dd" /></c:otherwise>
    </c:choose>
  </display:column>
  <display:column titleKey="projectSize.size">
      <c:choose>
        <c:when test="${method == 'Edit' and param.id == projectSize.id}">
            <input type="text" name="projectSize" style="padding: 0"
                value="<c:out value="${projectSize.projectSize}"/>" />
        </c:when>
        <c:otherwise><c:out value="${projectSize.projectSize}"/></c:otherwise>
    </c:choose>
  </display:column>
  <display:column titleKey="projectSize.endTime" format="{0,date,yyyy-MM-dd}">
      <c:choose>
        <c:when test="${method == 'Edit' and param.id == projectSize.id}">
            <input type="text" name="endTime" style="padding: 0"
                value="<fmt:formatDate value="${projectSize.endTime}" pattern="yyyy-MM-dd" />" />
        </c:when>
        <c:otherwise><fmt:formatDate value="${projectSize.endTime}" pattern="yyyy-MM-dd" /></c:otherwise>
    </c:choose>
  </display:column>
</display:table>
</form> 
 
</div>


<script type="text/javascript" src="<c:url value="/scripts/global.js"/>"></script>
