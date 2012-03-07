package com.chinarewards.elt.domain.reward.rule;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

/**
 * Choose qualified peoples directly.
 * 
 * @author yanxin
 * @since 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "direct")
public class DirectCandidateRule extends CandidateRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2195065262746451688L;

	@Transient
	private List<DirectCandidateData> candidateDataList;

	public List<DirectCandidateData> getCandidateDataList() {
		return candidateDataList;
	}

	public void setCandidateDataList(List<DirectCandidateData> candidateDataList) {
		this.candidateDataList = candidateDataList;
	}
}
