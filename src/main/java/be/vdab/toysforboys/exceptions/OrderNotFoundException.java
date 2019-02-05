package be.vdab.toysforboys.exceptions;

public class OrderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private long id;
	public OrderNotFoundException(long id) {
		super("Geen order gevonden met id:" + id);
		this.id = id;
	}
	public long getId() {
		return id;
	}
}
