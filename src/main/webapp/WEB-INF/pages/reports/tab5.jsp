<appfuse:label styleClass="control-label" key="report.riskcontrol.tab5"/>
<div class="input-group">
	<input type="text" name="collateralSummary" maxlength="100" class="form-control input-sm" value="<c:out value='${collateralSummary}'/>"/>
	<span class="input-group-btn">
			<button type="submit" class="btn btn-primary btn-sm" name="method" value="SaveTab5" onclick="$('#activeTab').val('tab5')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
	</span>
</div>
