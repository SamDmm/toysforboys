package be.vdab.toysforboys.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import org.springframework.format.annotation.NumberFormat;

import be.vdab.toysforboys.entities.Product;

@Embeddable
@NamedEntityGraph(name = "Orderdetail.metProduct", attributeNodes = @NamedAttributeNode("product"))
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int quantityOrdered;
	@NumberFormat(pattern = "0.00")
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
	@NumberFormat(pattern = "0.00")
	public BigDecimal getTotalPrice() {
		return priceEach.multiply(BigDecimal.valueOf(quantityOrdered));
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
