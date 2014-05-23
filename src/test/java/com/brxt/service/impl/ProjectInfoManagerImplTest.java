package com.brxt.service.impl;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.service.impl.BaseManagerMockTestCase;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.ProjectSizeDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;

public class ProjectInfoManagerImplTest extends BaseManagerMockTestCase{
	
	@InjectMocks
    private ProjectInfoManagerImpl manager;
 
    @Mock
    private ProjectInfoDao projectInfoDao;
    
    @Mock
    private ProjectSizeDao projectSizeDao;
 
    @Test
    public void testGetProjectInfo() {
        log.debug("testing get...");
        //given
        final Long id = 1L;
        final ProjectInfo projectInfo = new ProjectInfo();
        given(projectInfoDao.get(id)).willReturn(projectInfo);
        //when
        ProjectInfo result = manager.get(id);
        //then
        assertSame(projectInfo, result);
    }
 
    @Test
    public void testGetProjectInfos() {
        log.debug("testing getAll...");
        //given
        final List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
        given(projectInfoDao.getAll()).willReturn(projectInfos);
        //when
        List<ProjectInfo> result = manager.getAll();
        //then
        assertSame(projectInfos, result);
    }
 
    @Test
    public void testSaveProjectInfo() {
        log.debug("testing save...");
        //given
        final ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectName("TestProject1");
        // enter all required fields
         
        given(projectInfoDao.save(projectInfo)).willReturn(projectInfo);
        //when
        manager.save(projectInfo);
        //then
        verify(projectInfoDao).save(projectInfo);
    }
    
    @Test
    public void testBatchSaveProjectSizeList(){
    	log.debug("testing batch save ProjectSize list...");
    	
    	//given
        final ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setProjectName("TestProject1");
        List<ProjectSize> psList = new ArrayList<ProjectSize>();
        for (int i = 0; i < 10; i++) {
			ProjectSize ps = new ProjectSize();
			ps.setProjectSize(new BigDecimal(i));
			ps.setStartTime(new Date());
			ps.setEndTime(new Date());
			ps.setProjectInfo(projectInfo);
			psList.add(ps);
		}
     // enter all required fields
        
        //when
        manager.batchSaveProjectSizeList(psList);
        //then
        verify(projectSizeDao).batchSave(psList);
    }
    
    
    @Test
    public void testRemoveProjectInfo() {
        log.debug("testing remove...");
        //given
        final Long id = 1L;
        willDoNothing().given(projectInfoDao).remove(id);
        //when
        manager.remove(id);
        //then
        verify(projectInfoDao).remove(id);
    }

}
