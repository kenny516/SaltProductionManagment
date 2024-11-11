package com.analytique.gestion_analytique.Models;

import java.io.Serializable;

public class CompetencesEmployesId implements Serializable {
	private Long employe;
	private Long competence;

	public Long getEmploye() {
			return employe;
	}

	public void setEmploye(Long employe_id) {
			this.employe = employe_id;
	}

	public Long getCompetence() {
			return competence;
	}

	public void setCompetence(Long competence_id) {
			this.competence = competence_id;
	}

	// Implement equals() and hashCode() for composite key comparison
	@Override
	public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			CompetencesEmployesId that = (CompetencesEmployesId) o;

			if (!employe.equals(that.employe)) return false;
			return competence.equals(that.competence);
	}

	@Override
	public int hashCode() {
			int result = employe.hashCode();
			result = 31 * result + competence.hashCode();
			return result;
	}
}
