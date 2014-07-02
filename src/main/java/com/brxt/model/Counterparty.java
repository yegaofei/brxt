package com.brxt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

@Entity
@Table(name = "counterparty")
// 交易对手,担保人表
public class Counterparty extends BaseObject implements Serializable {

	private static final long serialVersionUID = 8483145366662490418L;
	private Long id;
	private String name; // 交易对手名称
	private String counterpartyType; //交易对手类型:real_estate_firm,commerce_company,institution  

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getCounterpartyType() {
		return counterpartyType;
	}

	public void setCounterpartyType(String counterpartyType) {
		this.counterpartyType = counterpartyType;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
        .append(this.name).append(this.counterpartyType)
        .toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (!(o instanceof Counterparty)) {
            return false;
        }

        final Counterparty counterparty = (Counterparty) o;
        return !(name != null ? !name.equals(counterparty.name) : counterparty.name != null)
        		&&
        		!(counterpartyType != null ? !counterpartyType.equals(counterparty.counterpartyType) : counterparty.counterpartyType != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + (counterpartyType != null ? counterpartyType.hashCode() : 0);
        return result;
	}

}
