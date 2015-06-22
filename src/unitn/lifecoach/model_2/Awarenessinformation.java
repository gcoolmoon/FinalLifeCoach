package unitn.lifecoach.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the awarenessinformation database table.
 * 
 */
@Entity
@NamedQuery(name="Awarenessinformation.findAll", query="SELECT a FROM Awarenessinformation a")
public class Awarenessinformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int awrenessinfoId;

	private String awarenessInformation;

	@Temporal(TemporalType.DATE)
	private Date date;

	public Awarenessinformation() {
	}

	public int getAwrenessinfoId() {
		return this.awrenessinfoId;
	}

	public void setAwrenessinfoId(int awrenessinfoId) {
		this.awrenessinfoId = awrenessinfoId;
	}

	public String getAwarenessInformation() {
		return this.awarenessInformation;
	}

	public void setAwarenessInformation(String awarenessInformation) {
		this.awarenessInformation = awarenessInformation;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}