package com.brxt.dao;

import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.collateral.Collateral;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.model.collateral.ConstructingProject;
import com.brxt.model.collateral.Land;
import com.brxt.model.collateral.Property;

public class CollateralOverviewDaoTest extends BaseDaoTestCase{

	@Autowired
	private CollateralOverviewDao collateralOverviewDao;
	
	@Test
	public void testSaveAndFind()
	{
		CollateralOverview co = new CollateralOverview();
		Collateral detail = new Collateral();
		detail.setCollateralOverview(co);
		co.getCollaterals().add(detail);
		
		Land land = new Land();
		land.setCollateral(detail);
		land.setOwner("user");
		detail.getLandList().add(land);
		
		Property p = new Property();
		p.setCollateral(detail);
		p.setPrice(new BigDecimal("58000"));
		detail.getPropertyList().add(p);
		
		ConstructingProject cp = new ConstructingProject();
		cp.setCollateral(detail);
		cp.setProjectSize(36.5D);
		detail.getConstructingProjectList().add(cp);
		
		CollateralOverview objInDB = collateralOverviewDao.save(co);
		assertNotNull(objInDB);
		
		CollateralOverview collateralOverview = collateralOverviewDao.get(objInDB.getId());
		assertNotNull(collateralOverview);
		assertNotNull(collateralOverview.getCollaterals());
		Collateral collateral = collateralOverview.getCollaterals().get(0);
		assertNotNull(collateral);
		
		assertNotNull(collateral.getLandList());
		land = collateral.getLandList().get(0);
		assertEquals("user", land.getOwner());
		
		assertNotNull(collateral.getPropertyList());
		Property property = collateral.getPropertyList().get(0);
		assertEquals(new BigDecimal("58000"), property.getPrice());
		
		assertNotNull(collateral.getConstructingProjectList());
		ConstructingProject constructingProject = collateral.getConstructingProjectList().get(0);
		assertEquals(Double.valueOf("36.5"), constructingProject.getProjectSize());
		
		
		
		
	}
	
}
