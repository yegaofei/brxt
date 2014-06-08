package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;

public class FinanceStatement {
	
	private Long id;
	private String tableName;
	
	private String statementTime;
	private StatementType statementType;
	private TradingRelationship tradingRelationship;
	private String counterpartyName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getStatementTime() {
		return statementTime;
	}
	public void setStatementTime(String statementTime) {
		this.statementTime = statementTime;
	}
	public StatementType getStatementType() {
		return statementType;
	}
	public void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}
	public TradingRelationship getTradingRelationship() {
		return tradingRelationship;
	}
	public void setTradingRelationship(TradingRelationship tradingRelationship) {
		this.tradingRelationship = tradingRelationship;
	}
	public String getCounterpartyName() {
		return counterpartyName;
	}
	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}
}
