<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="report.search.title"/></title>
    <link rel="stylesheet" type="text/css" href="${base}/webjars/bootstrap-datepicker/1.2.0/css/datepicker.css"/>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${base}/webjars/bootstrap-datepicker/1.2.0/js/locales/bootstrap-datepicker.zh-CN.js" charset="UTF-8"></script>
</head>
<div class="col-sm-10">
    <h2><fmt:message key='report.search.heading'/></h2>
 
    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='${ctx}/reports/addReport'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.new"/></a>
        <a class="btn btn-default disabled" href="<c:url value='${ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.modify"/></a>
        <a class="btn btn-default disabled" href="<c:url value='${ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.commit"/></a>
        <a class="btn btn-default disabled" href="<c:url value='${ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.audit"/></a>
        <a class="btn btn-default disabled" href="<c:url value='${ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.export"/></a>
        <a class="btn btn-default disabled" href="<c:url value='${ctx}/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.print"/></a>                
    </div>
    
    <form:form commandName="projectInfo" method="post" action="${ctx}/reports/reportSearch" id="projectInfoSearchForm" cssClass="well">
    		<div class="row">
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.nonrequired.projectName"/>
        		<form:input path="projectName" id="projectName" maxlength="50" cssClass="form-control"/>
        	</div>	
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.riskManager"/>
        		<form:select path="riskManager" class="form-control input-sm">          
                    <form:options items="${allRiskManagers}" />
                </form:select> 
    		</div>
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.search.startTime"/>
        		<form:input path="searchTimeStart" id="searchTimeStart" maxlength="20" cssClass="form-control"/>
    		</div>
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.search.endTime"/>
        		<form:input path="searchTimeEnd" id="searchTimeEnd" maxlength="20" cssClass="form-control"/>
    		</div>
    		</div>

			<div class="row">
    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.delegateManager"/>
        		<form:select path="delegateManager" class="form-control input-sm">          
                    <form:options items="${allDelegateManagers}" />
                </form:select>
    		</div>

    		<div class="col-sm-3 form-group">
        		<appfuse:label styleClass="control-label" key="projectInfo.trustManager"/>
                <form:select path="trustManager" class="form-control input-sm">          
                    <form:options items="${allTrustManagers}" />
                </form:select>
    		</div>
    		
    		<div class="col-sm-2 form-group">
    			<label></label>
    			<button type="submit" class="btn btn-primary form-control" name="method" value="SearchReport" onclick="bCancel=false">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.search"/>
        		</button>
        	</div>
        	</div>
	</form:form>
    	
    <display:table name="reportList" class="table table-condensed table-bordered table-striped table-hover" requestURI=""
                   id="riskControlReport" export="false" pagesize="20">        
        <display:column style="width: 5%">
                <input type="checkbox"  style="margin: 0 0 0 4px" onclick="radio(this)" />
        </display:column>           
        <display:column titleKey="projectInfo.projectName">
            <c:url value="${ctx}/reports/riskControlReport" var="reportUrl">
        			<c:param name="id" value="${riskControlReport.projectInfo.id}"/>
        			<c:param name="reportId" value="${riskControlReport.id}"/>
    		</c:url>
    		<a href="<c:out value="${reportUrl}" escapeXml="false" />"><c:out value="${riskControlReport.projectInfo.projectName}"/> </a>
        </display:column>    
        <display:column property="reportSeason" titleKey="report.riskcontrol.reportSeason" />    
        <display:column titleKey="report.offsite.committed">
        	<c:if test="${riskControlReport.projectInfo.projectInfoStatus.committed}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not riskControlReport.projectInfo.projectInfoStatus.committed}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
        </display:column>
        <display:column titleKey="report.riskcontrol.committed">
        	<c:if test="${riskControlReport.reportStatus.commitReport}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not riskControlReport.reportStatus.commitReport}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
        </display:column>
        <display:column titleKey="report.riskcontrol.audit">
        	<c:if test="${riskControlReport.reportStatus.reportAudit}">
        		<fmt:message key='report.committed'/>
        	</c:if>
        	<c:if test="${not riskControlReport.reportStatus.reportAudit}">
        		<fmt:message key='report.non.committed'/>
        	</c:if>
         </display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="projectInfoList.heading"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="projectInfoList.projects"/></display:setProperty>
    </display:table>
</div>

<script>
  $(function() {
    $('#searchTimeStart').datepicker({
				language: 'zh-CN',
				autoclose: true
			});	
  });
  
  $(function() {
    $('#searchTimeEnd').datepicker({
				language: 'zh-CN',
				autoclose: true
			});	
  });
</script>
