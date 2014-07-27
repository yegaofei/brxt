package com.brxt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

@Entity
@Table(name = "manager")
public class Manager extends BaseObject{
    private static final long serialVersionUID = -5160212783005915599L;
    private Long id;  
    private String employeeId;
    private String name;
    private String managerType;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getManagerType() {
        return managerType;
    }
    public void setManagerType(String managerType) {
        this.managerType = managerType;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Manager)) {
            return false;
        }

        final Manager manager = (Manager) o;
        return !(name != null ? !name.equals(manager.name) : manager.name != null);
    }
    
    @Override
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
                this.name).toString();
    }
}
