package jp.co.internous.sunflower.model.form;

import java.io.Serializable;
import java.util.List;

public class CartForm implements Serializable {
	private static final long serialVersionUID = -6277463226827431577L;

	
	private List<Long> id;
	
	private long userId;
	
	private long productId;
	
	private long productCount;
	
	private long price;
	
	
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}

}
