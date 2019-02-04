package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String scale;
	private String description;
	private int quantityInStock;
	private int quantityInOrder;
	private BigDecimal buyPrice;
	private long productlineId;
	@Version
	private long version;
	
	public Product(String name, String scale, String description, int quantityInStock, int quantityInOrder,
			BigDecimal buyPrice, long productlineId) {
		this.name = name;
		this.scale = scale;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.buyPrice = buyPrice;
		this.productlineId = productlineId;
	}
	protected Product() {
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getScale() {
		return scale;
	}
	public String getDescription() {
		return description;
	}
	public long getProductlineId() {
		return productlineId;
	}
	public long getVersion() {
		return version;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public int getQuantityInOrder() {
		return quantityInOrder;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyPrice == null) ? 0 : buyPrice.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (buyPrice == null) {
			if (other.buyPrice != null)
				return false;
		} else if (!buyPrice.stripTrailingZeros().equals(other.buyPrice.stripTrailingZeros()))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equalsIgnoreCase(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equalsIgnoreCase(other.scale))
			return false;
		return true;
	}
	
}
