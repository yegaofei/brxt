package com.brxt.service.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.appfuse.service.impl.BaseManagerMockTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.RepaymentDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.enums.RepaymentType;

public class RepaymentManagerImplTest extends BaseManagerMockTestCase{

	@InjectMocks
    private RepaymentManagerImpl manager;
	
	@Mock
    private ProjectInfoDao projectInfoDao;
	
	@Mock
	private RepaymentDao repaymentDao;
	
	 @Test
	    public void testSaveRepayment() {
	        log.debug("testing save...");
	        //given
	        final Repayment repayment = new Repayment();
	        ProjectInfo project = projectInfoDao.get(1L);
	        repayment.setProjectInfo(project);
	        repayment.setType(RepaymentType.INTEREST.getTitle());
	        
	        // enter all required fields
	         
	        given(repaymentDao.save(repayment)).willReturn(repayment);
	        //when
	        manager.save(repayment);
	        //then
	        verify(repaymentDao).save(repayment);
	    }
	    
}
