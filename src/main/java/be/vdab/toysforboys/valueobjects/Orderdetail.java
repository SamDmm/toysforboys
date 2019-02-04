package be.vdab.toysforboys.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.toysforboys.entities.Product;

@Embeddable
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int quantityOrdered;
	private BigDecimal priceEach;
	@ManyToOne(optional = false)
	@JoinColumn(name = "productId")
	private Product product;
	
	public Orderdetail(int quantityOrdered, BigDecimal priceEach, Product product) {
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		setProduct(product);
	}
	protected Orderdetail() {
	}
	
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
	public BigDecimal getPriceEach() {
		return priceEach;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		if (product == null) {
			throw new NullPointerException();
		}
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Orderdetail))
			return false;
		Orderdetail other = (Orderdetail) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
}