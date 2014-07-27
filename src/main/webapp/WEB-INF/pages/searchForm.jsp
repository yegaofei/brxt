		<form:form commandName="projectInfo" method="post" action="${ctx}/projectInfo" id="projectInfoSearchForm" cssClass="well">
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
    			<button type="submit" class="btn btn-primary form-control" name="method" value="SearchProjectInfo" onclick="bCancel=false">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.search"/>
        		</button>
        	</div>
        	</div>
    	</form:form>
    	
<script>
  $(function() {
    $('#searchTimeStart').datepicker({
				autoclose: true,
				language: 'zh-CN'
			});	
  });
  
  $(function() {
    $('#searchTimeEnd').datepicker({
				autoclose: true,
				language: 'zh-CN'
			});	
  });
</script>