package com.capg.categorymanagement.dto;

public class Category {

	int categoryId;
	String categoryName;
	String adminEmail;
	String adminPassword;
	String newCategoryName;
	
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getNewCategoryName() {
		return newCategoryName;
	}
	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}
	@Override
	public String toString() {
		return "Bean [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	
	
}
