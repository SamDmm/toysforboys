package be.vdab.toysforboys.exceptions;

public class NotEnoughQuantityInStockException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private long productId;
	
	public NotEnoughQuantityInStockException(long productId, String productName) {
		super("Not enough quantity in stock for product " + productId + ": " + productName);
		this.productId = productId;
	}

	public long getProductId() {
		return productId;
	}
}
