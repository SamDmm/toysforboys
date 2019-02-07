package be.vdab.toysforboys.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "countryId")
	private Country country;
	@Version
	long version;
	
	public Customer(String name, String streetAndNumber, String city, String state, String postalCode, Country country) {
		this.name = name;
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		setCountry(country);
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
	public Country getCountry() {
		return country;
	}
	public long getVersion() {
		return version;
	}
	public void setCountry(Country country) {
		if(country == null) {
			throw new NullPointerException();
		}
		this.country = country;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.toUpperCase().hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.toUpperCase().hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.toUpperCase().hashCode());
		result = prime * result + ((state == null) ? 0 : state.toUpperCase().hashCode());
		result = prime * result + ((streetAndNumber == null) ? 0 : streetAndNumber.toUpperCase().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equalsIgnoreCase(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equalsIgnoreCase(other.postalCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equalsIgnoreCase(other.state))
			return false;
		if (streetAndNumber == null) {
			if (other.streetAndNumber != null)
				return false;
		} else if (!streetAndNumber.equalsIgnoreCase(other.streetAndNumber))
			return false;
		return true;
	}
}
