package com.chinarewards.elt.domain.org;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "corporation")
public class Corporation extends Organization {

	private static final long serialVersionUID = -2314873131853974603L;



}
