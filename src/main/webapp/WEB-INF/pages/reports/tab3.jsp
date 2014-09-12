<c:if test="${empty param.preview}">
	<div class="col-lg-12">
		<div class="well form-horizontal">
			<fieldset>
				<c:if test="${not empty investmentStatus}"> 
				<div class="form-group">
					<label for="investmentTab3" class="col-lg-3 control-label"><fmt:message
							key="investmentProject.name" /></label>
					<div class="col-lg-4">
						<c:forEach var="investment" items="${investmentStatus}" varStatus="status">
							<c:if test="${status.first}">
								<select id="investmentTab3" name="investmentTab3" class="form-control  input-sm">
									<option value="">--- <fmt:message key="report.select.default" /> ---</option>
							</c:if>
							<option value="${investment.id}" <c:if test = "${investment.id == param.investmentTab3}" > selected </c:if>>
							     <c:out value="${investment.projectName}" /> -- <fmt:message key="${investment.projectType}" />
							</option>
							<c:if test="${status.last}">
								</select>
							</c:if>
						</c:forEach>
					</div>
				</div>

				<div class="form-group">
					<label for="projectEndTime" class="col-lg-3 control-label"><fmt:message key="projectProgress.deadline" /></label>
					<div class="row">
					<div class="col-lg-3">
						<select id="projectEndTime" name="projectEndTime" class="form-control input-sm">
							<c:if test="${not empty projectEndTimeList}">
							 <c:forEach var="projectEndTimeVar" items="${projectEndTimeList}" varStatus="status">
							     <option value="${projectEndTimeVar}" <c:if test="${projectEndTimeVar == param.projectEndTime}">selected</c:if>><c:out value='${projectEndTimeVar}' /></option>
							 </c:forEach>
							</c:if>
						</select>
					</div>
					<div class="help-inline" style="display:none;" id="errMsgProjectEndTime">
					   <span class="text-danger"><fmt:message key="report.investmentProjects.projectEndTime.empty"/></span>
					   <a href="${ctx}/projectInfoForm?id=${param.id}" class="alert-link"><fmt:message key="report.nav.back.projectInfoForm"/></a>
					</div>
					</div>
				</div>
				</c:if>
				
				<c:if test="${empty investmentStatus}"> 
				    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert">�</button>
                        <span class="text-danger"><fmt:message key="report.investmentProjects.empty"/></span>
                        <a href="${ctx}/projectInfoForm?id=${param.id}" class="alert-link"><fmt:message key="report.nav.back.projectInfoForm"/></a>
                    </div>
				</c:if>
				
				<c:if test="${not empty selectedInvestmentStatus and selectedInvestmentStatus.projectType == 'real_estate'}">
					<div class="form-group">
						<label for="policyChanges" class="col-lg-3 control-label"><fmt:message
								key="investmentProject.policyChanges" /></label>
						<div class="col-lg-9">
							<c:if test="${empty riskControlReport.policyChanges}">
								<textarea name="policyChanges" id="policyChanges"
									class="form-control input-sm"><c:out value="${param.policyChanges}"/></textarea>
							</c:if>
							<c:if test="${not empty riskControlReport.policyChanges }">
								<textarea name="policyChanges" id="policyChanges"
									class="form-control input-sm"><c:out value="${riskControlReport.policyChanges}"/></textarea>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label for="priceChanges" class="col-lg-3 control-label"><fmt:message
								key="investmentProject.priceChanges" /></label>
						<div class="col-lg-9">
							<textarea class="form-control" rows="4" id="priceChanges"
								name="priceChanges"><c:out
									value="${riskControlReport.priceChanges}"
									default="${param.priceChanges}" /></textarea>
							<span class="help-block"><fmt:message
									key="investmentProject.priceChanges.help" /></span>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label for="investmentEvaluation" class="col-lg-3 control-label"><fmt:message
							key="investmentProject.evaluation" /></label>
					<div class="col-lg-9">
						<c:if test="${empty riskControlReport.investmentEvaluation}">
							<textarea id="investmentEvaluation"
								name="investmentEvaluation" class="form-control input-sm"><c:out value="${param.investmentEvaluation}"/></textarea>
						</c:if>
						<c:if test="${not empty riskControlReport.investmentEvaluation}">
							<textarea id="investmentEvaluation"
								name="investmentEvaluation" class="form-control input-sm"><c:out value="${riskControlReport.investmentEvaluation}"/></textarea>
						</c:if>
					</div>
				</div>
				
				<c:if test="${fn:contains(user.roles, 'ROLE_RISK_DIRECTOR')}">
					<div class="form-group">
						<label for="investmentEvaluation_report" class="col-lg-3 control-label"><fmt:message key="investmentProject.evaluation" />(<fmt:message key="report.comments.modified" />)</label>
						<div class="col-lg-9">
							<textarea class="form-control input-sm" rows="4" id="investmentEvaluation_report" name="investmentEvaluation_report"><c:out value="${riskControlReport.investmentEvaluation_report}" /></textarea>
						</div>
					</div>
				</c:if>
				
				<div class="form-group">
					<div class="col-lg-2 col-lg-offset-10">
						<button type="submit" class="btn btn-primary" name="method"
							value="ProjectProgressTab3" onclick="$('#activeTab').val('tab3')">
							<i class="icon-ok icon-white"></i>
							<fmt:message key="button.ok" />
						</button>
						<button type="submit" class="btn btn-primary form-control"
							style="width: 50px;" name="method" value="SaveTab3"
							onclick="$('#activeTab').val('tab3')">
							<i class="icon-ok icon-white"></i>
							<fmt:message key="button.save" />
						</button>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
	<input type="hidden" name="projectEndTimeTab3"
		value="<c:out value='${param.projectEndTime}'/>">
	<input type="hidden" name="investmentIdTab3"
		value="<c:out value='${param.investmentTab3}'/>">

	<div class="col-lg-12">
		<c:if test="${not empty investmentProjects}">
			<c:forEach var="investmentProject" items="${investmentProjects}" varStatus="status">
				<div class="page-header">
					<h4>
						<fmt:message key="investmentProject.name" />
						:
						<c:out value="${investmentProject.investmentStatus.projectName}" />
						(
						<fmt:message key="${investmentProject.investmentProjectType}" />
						)
					</h4>
				</div>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th><fmt:message key="subjectCapacity.checkContent" /></th>
						<th><fmt:message key="subjectCapacity.checkResults" /></th>
					</thead>
					<tbody>
						<tr>
							<td><fmt:message key="investmentProject.startTime" /></td>
							<td><fmt:formatDate value="${investmentProject.startTime}"
									pattern="${datePattern}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.endTime" /></td>
							<td><fmt:formatDate value="${investmentProject.endTime}"
									pattern="${datePattern}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.totalAmount" /></td>
							<td><fmt:formatNumber type="number">
									<c:out value="${investmentProject.totalAmount}" />
								</fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.investmentAmount" /></td>
							<td><fmt:formatNumber type="number">
									<c:out value="${investmentProject.investmentAmount}" />
								</fmt:formatNumber></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.description" /></td>
							<td><c:out value="${investmentProject.description}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.delayed" /></td>
							<td><c:if test="${investmentProject.delayed}">
									<fmt:message key="label.yes" />
								</c:if> <c:if test="${not investmentProject.delayed}">
									<fmt:message key="label.no" />
								</c:if></td>
						</tr>
						<tr>
							<td><fmt:message key="investmentProject.comments" /></td>
							<td><c:out value="${investmentProject.comments}" /></td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>

		<c:if test="${not empty supplyLiquidProjects}">
			<c:forEach var="supplyLiquidProject" items="${supplyLiquidProjects}" varStatus="status">
			 <div class="page-header">
                    <h4>
                        <fmt:message key="supplyLiquidProject.name" />
                        :
                        <c:out value="${supplyLiquidProject.investmentStatus.projectName}" />
                        (
                        <fmt:message key='supplyLiquidProject.title' />
                        )
                    </h4>
                </div>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<th><fmt:message key="subjectCapacity.checkContent" /></th>
						<th><fmt:message key="subjectCapacity.checkResults" /></th>
					</thead>
					<tbody>
						<tr>
							<td><fmt:message key="supplyLiquidProject.industryVista" /></td>
							<td><textarea name="industryVista" class="form-control input-sm"><c:out value="${supplyLiquidProject.industryVista}"/></textarea></td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.firmSize" /></td>
							<td><input type="text" name="firmSize"
								value="${supplyLiquidProject.firmSize}"
								class="form-control input-sm"></td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.description" /></td>
							<td><textarea name="description" class="form-control input-sm"><c:out value="${supplyLiquidProject.description}"/></textarea></td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.saleSituation" /></td>
							<td><textarea name="saleSituation" class="form-control input-sm"><c:out value="${supplyLiquidProject.saleSituation}"/></textarea></td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.bigChanges" /></td>
							<td><label class="checkbox-inline"> <input
									id="bigChanges1" name="bigChanges" type="radio" value="true"
									<c:if test="${supplyLiquidProject.bigChanges}">checked="checked"</c:if> />
									<fmt:message key='label.yes' />
							</label> <label class="checkbox-inline"> <input id="bigChanges2"
									name="bigChanges" type="radio" value="false"
									<c:if test="${not supplyLiquidProject.bigChanges}">checked="checked"</c:if> />
									<fmt:message key='label.no' />
							</label></td>
						</tr>
						<tr>
							<td><fmt:message
									key="supplyLiquidProject.investmentProgress" /></td>
							<td><textarea name="investmentProgress" class="form-control input-sm"><c:out value="${supplyLiquidProject.investmentProgress}"/></textarea></td>
						</tr>
						<tr>
							<td><fmt:message key="supplyLiquidProject.comments" /></td>
							<td><textarea name="supplyLiquidProject.comments" class="form-control input-sm"><c:out value="${supplyLiquidProject.comments}"/></textarea></td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
	</div>
</c:if>

<c:if test="${not empty param.preview and param.preview}">
	<div class="form-horizontal">
		<fieldset disabled>
		  <c:if test="${not empty riskControlReport.policyChanges}">
			<div class="form-group">
				<label for="policyChanges" class="col-lg-3 control-label"><fmt:message
						key="investmentProject.policyChanges" /></label>
				<div class="col-lg-9">
					<textarea id="policyChanges" class="form-control input-sm"><c:out value="${riskControlReport.policyChanges}"/></textarea>
				</div>
			</div>
		  </c:if>	
		  
		  <c:if test="${not empty riskControlReport.priceChanges}">
			<div class="form-group">
				<label for="policyChanges" class="col-lg-3 control-label"><fmt:message key="investmentProject.priceChanges" /></label>
				<div class="col-lg-9">
					<textarea class="form-control" rows="4" id="priceChanges"
						name="priceChanges"><c:out value="${riskControlReport.priceChanges}" /></textarea>
					<span class="help-block"><fmt:message key="investmentProject.priceChanges.help" /></span>
				</div>
			</div>
		  </c:if>
			
			<div class="form-group">
				<label for="investmentEvaluation" class="col-lg-3 control-label"><fmt:message
						key="investmentProject.evaluation" /></label>
				<div class="col-lg-9">
					<textarea id="investmentEvaluation"
						name="investmentEvaluation" class="form-control input-sm"><c:out value="${riskControlReport.investmentEvaluation}"/></textarea>
				</div>
			</div>
		</fieldset>
	</div>
</c:if>

<div class="col-lg-12">
	<c:if
		test="${empty riskControlReport.investmentProjects and empty riskControlReport.supplyLiquidProjects and not empty param.preview and param.preview}">
		<div class="col-lg-12">
			<div class="alert alert-dismissable alert-danger">
				<fmt:message key="report.investmentProjects.empty" />
			</div>
		</div>
	</c:if>

	<c:if
		test="${not empty riskControlReport.investmentProjects and empty investmentProjects}">
		<c:forEach var="investmentProject"
			items="${riskControlReport.investmentProjects}" varStatus="status">
			<div class="page-header">
				<h4>
					<fmt:message key="investmentProject.name" />
					:
					<c:out value="${investmentProject.investmentStatus.projectName}" />
					(
					<fmt:message key="${investmentProject.investmentProjectType}" />
					)
				</h4>
			</div>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<th><fmt:message key="subjectCapacity.checkContent" /></th>
					<th><fmt:message key="subjectCapacity.checkResults" /></th>
				</thead>
				<tbody>
					<tr>
						<td><fmt:message key="investmentProject.startTime" /></td>
						<td><fmt:formatDate value="${investmentProject.startTime}"
								pattern="${datePattern}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.endTime" /></td>
						<td><fmt:formatDate value="${investmentProject.endTime}"
								pattern="${datePattern}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.totalAmount" /></td>
						<td><fmt:formatNumber type="number">
								<c:out value="${investmentProject.totalAmount}" />
							</fmt:formatNumber></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.investmentAmount" /></td>
						<td><fmt:formatNumber type="number">
								<c:out value="${investmentProject.investmentAmount}" />
							</fmt:formatNumber></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.description" /></td>
						<td><c:out value="${investmentProject.description}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.delayed" /></td>
						<td><c:if test="${investmentProject.delayed}">
								<fmt:message key="label.yes" />
							</c:if> <c:if test="${not investmentProject.delayed}">
								<fmt:message key="label.no" />
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="investmentProject.comments" /></td>
						<td><c:out value="${investmentProject.comments}" /></td>
					</tr>
				</tbody>
			</table>
		</c:forEach>
	</c:if>

	<c:if
		test="${not empty riskControlReport.supplyLiquidProjects and empty supplyLiquidProjects}">
		<c:forEach var="supplyLiquidProject"
			items="${riskControlReport.supplyLiquidProjects}" varStatus="status">
			<div class="page-header">
                    <h4>
                        <fmt:message key="supplyLiquidProject.name" />
                        :
                        <c:out value="${supplyLiquidProject.investmentStatus.projectName}" />
                        (
                        <fmt:message key='supplyLiquidProject.title' />
                        )
                    </h4>
            </div>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<th><fmt:message key="subjectCapacity.checkContent" /></th>
					<th><fmt:message key="subjectCapacity.checkResults" /></th>
				</thead>
				<tbody>
					<tr>
						<td><fmt:message key="supplyLiquidProject.industryVista" /></td>
						<td><c:out value="${supplyLiquidProject.industryVista}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.firmSize" /></td>
						<td><c:out value="${supplyLiquidProject.firmSize}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.description" /></td>
						<td><c:out value="${supplyLiquidProject.description}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.saleSituation" /></td>
						<td><c:out value="${supplyLiquidProject.saleSituation}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.bigChanges" /></td>
						<td><c:if test="${supplyLiquidProject.bigChanges}">
								<fmt:message key="label.yes" />
							</c:if> <c:if test="${not supplyLiquidProject.bigChanges}">
								<fmt:message key="label.no" />
							</c:if></td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.investmentProgress" /></td>
						<td><c:out value="${supplyLiquidProject.investmentProgress}" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="supplyLiquidProject.comments" /></td>
						<td><c:out value="${supplyLiquidProject.comments}" /></td>
					</tr>
				</tbody>
			</table>
		</c:forEach>
	</c:if>
</div>

<script>
	var reply12 = function(data) {
		if (data != null && typeof data == 'object') {
			$("#projectEndTime").empty();
			if(data.length == 0) {
				$("#errMsgProjectEndTime").show();
				return;
			}
			$("#errMsgProjectEndTime").hide();
			$.each(data, function(i) {
				$("#projectEndTime").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
			});
		}
	}

	$(function() {
		$('#investmentTab3').change(function() {
			var p1 = $(this).children('option:selected').val();
			if (p1 != null && p1 != "") {
				ProjProgressManager.getAvailableEndTimes(p1, reply12);
			}
			if (p1 == "") {
				$("#projectEndTime").empty();
			}
		});
	});
</script>
