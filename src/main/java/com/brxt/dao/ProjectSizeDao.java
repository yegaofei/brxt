package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.ProjectSize;

public interface ProjectSizeDao extends GenericDao<ProjectSize, Long> {

	public void batchSave(List<ProjectSize> projectSizeList);
}
