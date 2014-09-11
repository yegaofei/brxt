<c:set var="option1" scope="request"><fmt:message key="report.riskcontrol.tab8.option1" /></c:set>
<c:set var="option2" scope="request"><fmt:message key="report.riskcontrol.tab8.option2" /></c:set>
<c:if test="${empty param.preview}">
<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<div class="form-group">
		<label for="comments" class="col-lg-2 control-label"><fmt:message key="report.riskcontrol.tab8" /></label>
		<div class="col-lg-10">
		    <label class="radio">
                <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" <c:if test="${riskControlReport.tab8Option == 'option1'}"> checked </c:if>>
                    <c:out value="${option1}" />
            </label>
            <label class="radio">
                <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" <c:if test="${riskControlReport.tab8Option == 'option2'}"> checked </c:if>>
                    <c:out value="${option2}" />
            </label>  
            <label class="radio">
                <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" <c:if test="${riskControlReport.tab8Option == 'option3'}"> checked </c:if>>
			</label>  
			<div class="col-lg-10">
			     <c:if test="${riskControlReport.tab8Option == 'option3'}">
			         <textarea class="form-control" rows="4" id="comments" name="comments" placeholder="<fmt:message key="report.riskcontrol.tab8" />"><c:out value="${riskControlReport.comments}" /></textarea>
			     </c:if>
			     <c:if test="${riskControlReport.tab8Option != 'option3'}">
                     <textarea class="form-control" rows="4" id="comments" name="comments" placeholder="<fmt:message key="report.riskcontrol.tab8" />"></textarea>
                 </c:if>	
		    </div>
		</div>		
	</div>
	
	<c:if test="${fn:contains(user.roles, 'ROLE_RISK_DIRECTOR')}">
	<div class="form-group">
		<label for="comments_report" class="col-lg-2 control-label"><fmt:message key="report.riskcontrol.tab8" />(<fmt:message key="report.comments.modified" />)</label>
		<div class="col-lg-10">
			<textarea class="form-control" rows="4" id="comments_report" name="comments_report" placeholder="<fmt:message key="report.riskcontrol.tab8" />"><c:out value="${riskControlReport.comments_report}" /></textarea>
		</div>
	</div>
	</c:if>
	
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
</c:if>

<c:if test="${not empty param.preview and param.preview}">
<div class="col-lg-12">
<div class="form-horizontal">
	<fieldset disabled>
		<div class="col-lg-12">
			<textarea class="form-control" rows="4" id="comments" name="comments"><c:out value="${riskControlReport.comments}" /></textarea>			
		</div>
	</fieldset>
</div>
</div>
</c:if>
