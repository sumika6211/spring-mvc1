package com.example.spring_mvc1.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Item {
	private Long id;
	
	@NotBlank(message="商品名を記入してください")
	private String name;
	
	@Min(value=10, message="10以上の数値を入力してください")
	@Max(value=10000, message="10000以下の数値を入力してください")
	private float price;
	
	@Size(max=50, message="ベーダ―名は50文字を超えないでください")
	private String vendor;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
