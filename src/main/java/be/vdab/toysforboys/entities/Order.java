package be.vdab.toysforboys.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import be.vdab.toysforboys.enums.Status;
import be.vdab.toysforboys.valueobjects.Orderdetail;

@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findUnShipped", query = "select o from Order o where o.status not in ('SHIPPED', 'CANCELLED') order by o.id")
@NamedEntityGraph(name = "Order.metCustomerEnCountry", attributeNodes = @NamedAttributeNode(value = "customer", subgraph = "metCountry"), subgraphs = @NamedSubgraph(name = "metCountry", attributeNodes = @NamedAttributeNode("country")))
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@DateTimeFormat(style = "S-")
	private LocalDate orderDate;
	@DateTimeFormat(style = "S-")
	private LocalDate requiredDate;
	@DateTimeFormat(style = "S-")
	private LocalDate shippedDate;
	private String comments;
	@ManyToOne(optional = false)
	@JoinColumn(name = "customerId")
	private Customer customer;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Version
	private long version;
	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
	private Set<Orderdetail> orderdetails;
	
	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, long customerId, Customer customer, Status status) {
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		setCustomer(customer);
		this.status = status;
		this.orderdetails = new LinkedHashSet<>();
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
	public Customer getCustomer() {
		return customer;
	}
	public Status getStatus() {
		return status;
	}
	public long getVersion() {
		return version;
	}
	@NumberFormat(pattern = "0.00")
	public BigDecimal getTotalPrice() {
		return orderdetails.stream().map(orderdetail -> orderdetail.getPriceEach().multiply(BigDecimal.valueOf(orderdetail.getQuantityOrdered()))).reduce(BigDecimal.ZERO, (vorigeSom, getal) -> vorigeSom.add(getal));
	}
	
	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}
	public void setCustomer(Customer customer) {
		if (customer == null) {
			throw new NullPointerException();
		}
		this.customer = customer;
	}
	public void addOrderDetail(Orderdetail orderdetail) {
		if (orderdetail == null) {
			throw new NullPointerException();
		}
		orderdetails.add(orderdetail);
	}
	public void setAsShipped() {
		for(Orderdetail orderdetail : orderdetails) {
			orderdetail.getProduct().takeQuantity(orderdetail.getQuantityOrdered());
		}
		status = Status.SHIPPED;
		shippedDate = LocalDate.now();
	}
}
