package com.capg.categorymanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface IcategoryServiceImpl {

	List<Category> takeDetails() throws CategoryManagementException;

	int createCategory(Category category ) throws CategoryManagementException;

	boolean editCategory(Category category) throws CategoryManagementException;
	
	boolean existingCategory(Category category) throws CategoryManagementException, SQLException;
 
	boolean deleteCategory( String deleteName) throws CategoryManagementException;

	public boolean emailPattern(Category category)  throws CategoryManagementException;

	public boolean passwordPattern(Category category) throws CategoryManagementException;

	public boolean emailCheck(Category category) throws CategoryManagementException;

	public boolean passwordCheck(Category category) throws CategoryManagementException;

	public boolean validateName(String name) throws CategoryManagementException;
}
