package com.chinarewards.elt.domain.information;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.chinarewards.elt.domain.org.Team;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "team")
public class TeamObject extends ReceivingObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private Team team;
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}


}
