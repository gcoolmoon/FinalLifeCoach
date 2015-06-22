package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dailysuggestions database table.
 * 
 */
@Entity
@Table(name="dailysuggestions")
@NamedQuery(name="Dailysuggestion.findAll", query="SELECT d FROM Dailysuggestion d")
public class Dailysuggestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dailysuggId;

	private int date;

	private String improvemetSuggestions;

	private String motivationalSuggestion;

	//bi-directional many-to-one association to Lifestylemeasuremnt
	@ManyToOne
	@JoinColumn(name="lifestyleDefId")
	private Lifestylemeasuremnt lifestylemeasuremnt;

	public Dailysuggestion() {
	}

	public int getDailysuggId() {
		return this.dailysuggId;
	}

	public void setDailysuggId(int dailysuggId) {
		this.dailysuggId = dailysuggId;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getImprovemetSuggestions() {
		return this.improvemetSuggestions;
	}

	public void setImprovemetSuggestions(String improvemetSuggestions) {
		this.improvemetSuggestions = improvemetSuggestions;
	}

	public String getMotivationalSuggestion() {
		return this.motivationalSuggestion;
	}

	public void setMotivationalSuggestion(String motivationalSuggestion) {
		this.motivationalSuggestion = motivationalSuggestion;
	}

	public Lifestylemeasuremnt getLifestylemeasuremnt() {
		return this.lifestylemeasuremnt;
	}

	public void setLifestylemeasuremnt(Lifestylemeasuremnt lifestylemeasuremnt) {
		this.lifestylemeasuremnt = lifestylemeasuremnt;
	}

}