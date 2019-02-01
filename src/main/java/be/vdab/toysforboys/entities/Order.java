package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.toysforboys.enums.Status;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findUnShipped", query = "select o from Order o where o.status not in ('SHIPPED', 'CANCELLED') order by o.id")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String comments;
	private long customerId;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Version
	private long version;
	
	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, long customerId,
			Status status) {
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		this.customerId = customerId;
		this.status = status;
	}
	protected Order() {
	}
	
	public long getId() {
		return id;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public LocalDate getRequiredDate() {
		return requiredDate;
	}
	public LocalDate getShippedDate() {
		return shippedDate;
	}
	public String getComments() {
		return comments;
	}
	public long getCustomerId() {
		return customerId;
	}
	public Status getStatus() {
		return status;
	}
	public long getVersion() {
		return version;
	}
}
