package be.vdab.toysforboys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String streetAndNumber;
	private String city;
	private String state;
	private String postalCode;
	private long countryId;
	@Version
	long version;
	
	public Customer(String name, String streetAndNumber, String city, String state, String postalCode, long countryId) {
		this.name = name;
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.countryId = countryId;
	}
	protected Customer() {
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public long getCountryId() {
		return countryId;
	}
	public long getVersion() {
		return version;
	}
	
}
