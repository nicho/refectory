package com.chinarewards.elt.domain.org;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "restaurant")
public class Restaurant extends Organization {

	private static final long serialVersionUID = -2314873131853974603L;

	private Corporation corp;

	public Corporation getCorp() {
		return corp;
	}

	public void setCorp(Corporation corp) {
		this.corp = corp;
	}

}


