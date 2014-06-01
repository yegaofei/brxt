package com.brxt.dao;
import static org.junit.Assert.*;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.Counterparty;
import com.brxt.model.CounterpartyType;

public class CounterpartyDaoTest extends BaseDaoTestCase {

	@Autowired
	private CounterpartyDao counterpartyDao;
	
	@Test
	public void testSaveAndFind() throws Exception {
		Counterparty cp = new Counterparty();
		String cpName = "CP1";
		cp.setName(cpName);
		cp.setCounterpartyType(CounterpartyType.REAL_ESTATE_FIRM.toString());
		counterpartyDao.save(cp);
		flush();
		
		Counterparty cp2 = counterpartyDao.findByCounterpartyName(cpName);
		assertNotNull(cp2);
		assertEquals(cp, cp2);
	}
}
