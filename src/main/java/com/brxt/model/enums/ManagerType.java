package com.brxt.model.enums;

public enum ManagerType {
    
    DELEGATE_MANAGER("delegate_manager"), //托管经理 
    RISK_MANAGER("risk_manager"), //风险经理
    TRUST_MANAGER("trust_manager"); //信托经理
    
    private String managerType;
    
    private ManagerType(String type)
    {
        this.managerType = type;
    }
    
    public String toString()
    {
        return this.managerType;
    }
    
    public String getManagerType()
    {
        return this.managerType;
    }
}
