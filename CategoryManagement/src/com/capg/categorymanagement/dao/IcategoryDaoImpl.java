package com.capg.categorymanagement.dao;

import java.sql.SQLException;
import java.util.List;

import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface IcategoryDaoImpl {

	public List<Category> takeDetails() throws CategoryManagementException;

	public int createCategory(Category category) throws CategoryManagementException;

	public boolean editCategory(Category category) throws CategoryManagementException;
	
	
	public boolean deleteCategory(String deleteName) throws CategoryManagementException;
	

	public boolean adminEmail(Category category) throws CategoryManagementException;

	public boolean adminPassword(Category category) throws CategoryManagementException;
	
	public boolean existingCategory(Category category) throws CategoryManagementException, SQLException;

}
