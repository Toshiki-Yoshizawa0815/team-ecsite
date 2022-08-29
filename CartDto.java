package jp.co.internous.sunflower.model.domain.dto;

import jp.co.internous.sunflower.model.domain.MstProduct;
import jp.co.internous.sunflower.model.domain.TblCart;

public class CartDto {
	

	private long id;

	private String imageFullPath;

	private String productName;

	private long price;

	private long productCount;
	
	private long subtotal;
	
	
	public CartDto() {}
	
	public CartDto(MstProduct mstProduct, TblCart tblCart) {
		this.setId(tblCart.getId());
		this.setImageFullPath(mstProduct.getImageFullPath());
		this.setProductName(mstProduct.getProductName());
		this.setPrice(mstProduct.getPrice());
		this.setProductCount(tblCart.getProductCount());
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getImageFullPath() {
		return imageFullPath;
	}
	public void setImageFullPath(String imageFullPath) {
		this.imageFullPath = imageFullPath;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	
	public long getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(long subtotal) {
		this.subtotal = subtotal;
	}

}
