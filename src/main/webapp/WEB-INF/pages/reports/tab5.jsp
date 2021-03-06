<c:if test="${empty param.preview}">
<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<div class="form-group">
		<label for="collateralSummary" class="col-lg-2 control-label"><fmt:message key="report.riskcontrol.tab5" /></label>
		<div class="col-lg-10">
			<textarea class="form-control" rows="4" id="collateralSummary" name="collateralSummary"><c:out value="${riskControlReport.collateralSummary}" /></textarea>			
		</div>
	</div>
	
	<c:if test="${fn:contains(user.roles, 'ROLE_RISK_DIRECTOR')}">
	<div class="form-group">
		<label for="collateralSummary_report" class="col-lg-2 control-label"><fmt:message key="report.riskcontrol.tab5" />(<fmt:message key="report.comments.modified" />)</label>
		<div class="col-lg-10">
			<textarea class="form-control" rows="4" id="collateralSummary_report" name="collateralSummary_report"><c:out value="${riskControlReport.collateralSummary_report}" /></textarea>
		</div>
	</div>
	</c:if>
	
	<div class="form-group">
		<div class="col-lg-1 col-lg-offset-11">
			<button type="submit" class="btn btn-primary" name="method" value="SaveTab5" onclick="$('#activeTab').val('tab5')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>
	</div>
	</fieldset>
</div>
</div>
</c:if>

<c:if test="${not empty param.preview and param.preview}">
<div class="col-lg-12">
<div class="form-horizontal">
	<fieldset disabled>
		<div class="col-lg-12">
			<textarea class="form-control" rows="4" id="collateralSummary" name="collateralSummary"><c:out value="${riskControlReport.collateralSummary}" /></textarea>			
		</div>
	</fieldset>
</div>
</div>
</c:if>