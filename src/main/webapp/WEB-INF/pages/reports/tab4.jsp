<c:if test="${empty param.preview}">
<div class="col-lg-12">
<div class="well form-horizontal">
	<fieldset>
	<c:if test="${not empty repaymentInvestmentStatus}">
	<div class="form-group">
		<label for="investmentTab4" class="col-lg-3 control-label"><fmt:message key="repaymentProject.name" /></label>
		<div class="col-lg-4">
			<c:forEach var="investment" items="${repaymentInvestmentStatus}" varStatus="status">
				<c:if test="${status.first}">
								<select id="investmentTab4" name="investmentTab4" class="form-control  input-sm">
									<option value=""> ---<fmt:message key="report.select.default"/>--- </option>
				</c:if>
				<option value="${investment.id}" <c:if test = "${investment.id == param.investmentTab4}" > selected </c:if>><c:out value="${investment.projectName}" /> -- <fmt:message key="${investment.projectType}"/></option>
				<c:if test="${status.last}"></select></c:if>
			</c:forEach>
		</div>
	</div>
	
	<div class="form-group">
		<label for="projectEndTimeTab4" class="col-lg-3 control-label"><fmt:message key="projectProgress.deadline" /></label>
		<div class="row">
		<div class="col-lg-3">
			<select id="projectEndTimeTab4" name="projectEndTimeTab4" class="form-control input-sm">
                <c:if test="${not empty projectEndTimeList}">
                    <c:forEach var="projectEndTimeVar" items="${projectEndTimeList}" varStatus="status">
                        <option value="${projectEndTimeVar}" <c:if test="${projectEndTimeVar == param.projectEndTimeTab4}">selected</c:if>><c:out value='${projectEndTimeVar}' /></option>
                    </c:forEach>
                </c:if>
            </select>
		</div>
		<div class="help-inline" style="display:none;" id="errMsgRepaymentEndTime">
            <span class="text-danger"><fmt:message key="report.investmentProjects.projectEndTime.empty"/></span>
            <a href="${ctx}/projectInfoForm?id=${param.id}" class="alert-link"><fmt:message key="report.nav.back.projectInfoForm"/></a>
        </div>
        </div>
	</div>
	</c:if>
	
	<c:if test="${empty repaymentInvestmentStatus}">
	   <div class="alert alert-dismissable alert-danger">
          <button type="button" class="close" data-dismiss="alert">�</button>
          <span class="text-danger"><fmt:message key="report.repaymentProjects.empty"/></span>
          <a href="${ctx}/projectInfoForm?id=${param.id}" class="alert-link"><fmt:message key="report.nav.back.projectInfoForm"/></a>
       </div>
	</c:if>
	
	<div class="form-group">
		<label for="repaymentEvaluation" class="col-lg-3 control-label"><fmt:message key="repaymentProject.evaluation" /></label>
		<div class="col-lg-9">
			<textarea class="form-control" rows="4" id="repaymentEvaluation" name="repaymentEvaluation"><c:out value="${riskControlReport.repaymentEvaluation}" default="${param.repaymentEvaluation }"/></textarea>			
		</div>
	</div>
	
	<c:if test="${fn:contains(user.roles, 'ROLE_RISK_DIRECTOR')}">
        <div class="form-group">
            <label for="repaymentEvaluation_report" class="col-lg-3 control-label"><fmt:message key="repaymentProject.evaluation" />(<fmt:message key="report.comments.modified" />)</label>
            <div class="col-lg-9">
                <textarea class="form-control" rows="4" id="repaymentEvaluation_report" name="repaymentEvaluation_report"><c:out value="${riskControlReport.repaymentEvaluation_report}" /></textarea>
            </div>
        </div>
    </c:if>
	
	<div class="form-group">
		<div class="col-lg-2 col-lg-offset-10">
			<button type="submit" class="btn btn-primary" name="method" value="ProjectProgressTab4" onclick="$('#activeTab').val('tab4')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.ok"/>
			</button>
			<button type="submit" class="btn btn-primary form-control" style="width:50px;" name="method" value="SaveTab4" onclick="$('#activeTab').val('tab4')">
            		<i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
			</button>
		</div>
	</div>
	</fieldset>
</div>
</div>
</c:if>

<div class="col-lg-12">
	<c:if test="${not empty repaymentProjects }">
		<c:set var="dataSource" scope="request" value="${repaymentProjects}" ></c:set>
	</c:if>
	
	<c:if test="${empty repaymentProjects and not empty riskControlReport.repaymentProjects }">
		<c:set var="dataSource" scope="request" value="${riskControlReport.repaymentProjects}" ></c:set>
	</c:if>
	
	<div class="form-group">		
	<c:if test="${not empty dataSource}">
		<c:forEach var="repaymentProject" items="${dataSource}" varStatus="status">
			<div class="page-header">
				<h4>
					<fmt:message key="repaymentProject.name" />:
					<c:out value="${repaymentProject.investmentStatus.projectName}" /> 
				</h4>
			</div>
			<table class="table table-striped table-bordered table-hover">
						<thead>
							<th><fmt:message key="subjectCapacity.checkContent"/></th>
							<th><fmt:message key="subjectCapacity.checkResults"/></th>
						</thead>
						<tbody>
						<tr>
							<td><fmt:message key="repaymentProject.totalSize"/></td>
							<td><c:out value="${repaymentProject.totalSize}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.totalSaleSize"/></td>
							<td><c:out value="${repaymentProject.totalSaleSize}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.permitSaleSize"/></td>
							<td>
								<c:out value="${repaymentProject.permitSaleSize}" />
							 </td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.openDate"/></td>
							<td>
								<fmt:formatDate value="${repaymentProject.openDate}" pattern="${datePattern}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.preSaleSize"/></td>
							<td>
								<c:out value="${repaymentProject.preSaleSize}" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.salePercentRate"/></td>
							<td>
								<c:out value="${repaymentProject.salePercentRate}" />%
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.preAvgPrice"/><fmt:message key="currency.unit.yuan"/></td>
							<td>
								<fmt:formatNumber type="currency"><c:out value="${repaymentProject.preAvgPrice}" /></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.currAvgPrice"/><fmt:message key="currency.unit.yuan"/></td>
							<td>
								<fmt:formatNumber type="currency"><c:out value="${repaymentProject.currAvgPrice}" /></fmt:formatNumber>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="repaymentProject.comments"/></td>
							<td>
								<c:out value="${repaymentProject.comments}" />
							</td>
						</tr>
						</tbody>
			</table>
		</c:forEach>	
	</c:if>
</div>
</div>

<c:if test="${not empty param.preview and param.preview}">
<div class="col-lg-12">
<div class="well form-horizontal">
    <fieldset disabled >
    <div class="form-group">
        <label for="repaymentEvaluation" class="col-lg-3 control-label"><fmt:message key="repaymentProject.evaluation" /></label>
        <div class="col-lg-9">
            <textarea class="form-control" rows="4" id="repaymentEvaluation" name="repaymentEvaluation"><c:out value="${riskControlReport.repaymentEvaluation}" default="${param.repaymentEvaluation}"/></textarea>         
        </div>
    </div>  
    </fieldset>
</div>
</div>
</c:if>

<script>
    var replyTab4 = function(data) {
        if (data != null && typeof data == 'object') {
            $("#projectEndTimeTab4").empty();
            if(data.length == 0) {
                $("#errMsgRepaymentEndTime").show();
                return;
            }
            $.each(data, function(i) {
                $("#projectEndTimeTab4").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
            });
        }
    }

    $(function() {
        $('#investmentTab4').change(function() {
            var p1 = $(this).children('option:selected').val();
            if (p1 != null && p1 != "") {
                ProjProgressManager.getAvailableEndTimes(p1, replyTab4);
            }
            if (p1 == "") {
                $("#projectEndTimeTab4").empty();
            }
        });
    });
</script>