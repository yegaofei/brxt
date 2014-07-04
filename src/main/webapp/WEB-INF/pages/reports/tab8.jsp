

<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<div class="form-group">
		<label for="comments" class="col-lg-2 control-label"><fmt:message key="report.riskcontrol.tab8" /></label>
		<div class="col-lg-10">
			<textarea class="form-control" rows="4" id="comments" name="comments"><c:out value="${riskControlReport.comments}" /></textarea>			
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-lg-1 col-lg-offset-11">
			<button type="submit" class="btn btn-primary" name="method" value="SaveTab8" onclick="$('#activeTab').val('tab8')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>
	</div>
	</fieldset>
</div>
</div>

