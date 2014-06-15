package com.brxt.model.collateral;

public class CollateralConstrProjModel extends Collateral{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1786234657921358019L;

	private ConstructingProject constrProj;

	public ConstructingProject getConstrProj() {
		return constrProj;
	}

	public void setConstrProj(ConstructingProject constrProj) {
		this.constrProj = constrProj;
	}
	
	public CollateralType getCollateralType()
	{
		return CollateralType.CONSTRUCTING_PROJECT;
	}
	
}
