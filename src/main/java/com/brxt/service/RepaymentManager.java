package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.Repayment;

public interface RepaymentManager extends GenericManager<Repayment, Long> {

	public List<Repayment> findByProjId(Long projectInfoId);

}
