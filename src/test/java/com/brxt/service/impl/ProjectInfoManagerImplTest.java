package com.brxt.service.impl;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.BaseManagerMockTestCase;
import org.getahead.dwrdemo.people.Person;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.model.ProjectInfo;

public class ProjectInfoManagerImplTest extends BaseManagerMockTestCase{
	
	@InjectMocks
    private ProjectInfoManagerImpl manager;
 
    @Mock
    private ProjectInfoDao dao;
 
    @Test
    public void testGetProjectInfo() {
        log.debug("testing get...");
        //given
        final Long id = 1L;
        final ProjectInfo projectInfo = new ProjectInfo();
        given(dao.get(id)).willReturn(projectInfo);
        //when
        ProjectInfo result = manager.get(id);
        //then
        assertSame(projectInfo, result);
    }
 
    @Test
    public void testGetProjectInfos() {
        log.debug("testing getAll...");
        //given
        final List projectInfos = new ArrayList();
        given(dao.getAll()).willReturn(projectInfos);
        //when
        List result = manager.getAll();
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
         
        given(dao.save(projectInfo)).willReturn(projectInfo);
        //when
        manager.save(projectInfo);
        //then
        verify(dao).save(projectInfo);
    }
    @Test
    public void testRemoveProjectInfo() {
        log.debug("testing remove...");
        //given
        final Long id = 1L;
        willDoNothing().given(dao).remove(id);
        //when
        manager.remove(id);
        //then
        verify(dao).remove(id);
    }

}
