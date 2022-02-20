package com.iamo.ShoppingAPI.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private String productId;
	private String productName;
	private float saleprice;
	private float fullprice;
	private Date promotionExpDate;
	private String storeId;
	private String productDetails;
	private String size;
	private String picture;
	private int sku;

//	"1","Adidas รองเท้า OG RN Women NMD_R1 GZ7997",200.00f,500,new Date(),"1","ของปลอม","7,8,9,10"

	public Product(String productId, String productName, float saleprice, float fullprice, Date promotionExpDate,
			String storeId, String productDetails, String size, String picture, int sku) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.saleprice = saleprice;
		this.fullprice = fullprice;
		this.promotionExpDate = promotionExpDate;
		this.storeId = storeId;
		this.productDetails = productDetails;
		this.size = size;
		this.picture = picture;
		this.sku = sku;
	}

	public Product() {
	}

	public Product(String productId, String productName, float saleprice, float fullprice, Date promotionExpDate,
			String storeId, String productDetails, String size, String picture) {
		this.productId = productId;
		this.productName = productName;
		this.saleprice = saleprice;
		this.fullprice = fullprice;
		this.promotionExpDate = promotionExpDate;
		this.storeId = storeId;
		this.productDetails = productDetails;
		this.size = size;
		this.picture = picture;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}

	public float getFullprice() {
		return fullprice;
	}

	public void setFullprice(float fullprice) {
		this.fullprice = fullprice;
	}

	public Date getPromotionExpDate() {
		return promotionExpDate;
	}

	public void setPromotionExpDate(Date promotionExpDate) {
		this.promotionExpDate = promotionExpDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

}
