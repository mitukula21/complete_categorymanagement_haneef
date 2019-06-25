package com.capg.categorymanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import com.capg.categorymanagement.dao.ICategoryDao;
import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;

public class ICategoryService implements IcategoryServiceImpl {

	ICategoryDao categoryDao = new ICategoryDao();
	Category category = new Category();

	public List<Category> takeDetails() throws CategoryManagementException {
		return categoryDao.takeDetails();
	}

	public int createCategory(Category category) throws CategoryManagementException {

		int createFlag = categoryDao.createCategory(category);

		return createFlag;

	}

	public boolean editCategory(Category category) throws CategoryManagementException {
		System.out.println(category.getNewCategoryName());
		boolean editFlag = categoryDao.editCategory(category);

		return editFlag;
	}

	public boolean existingCategory(Category category) throws CategoryManagementException, SQLException {
		// System.out.println(123);
		boolean existFlag;
		existFlag = categoryDao.existingCategory(category);
		// System.out.println(category.getCategoryName());
		return existFlag;
	}

	public boolean deleteCategory(String deleteName) throws CategoryManagementException {

		boolean deleteFlag = categoryDao.deleteCategory(deleteName);
		return deleteFlag;

	}

	public boolean emailPattern(Category category) {

		boolean emailPatternFlag = false;
		String emailpattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = category.getAdminEmail();
		if (email.matches(emailpattern)) {
			emailPatternFlag = true;
		} 
		return emailPatternFlag;
	}

	public boolean passwordPattern(Category category) {
		boolean passwordPatternFlag = false;
		if (category.getAdminPassword().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
			passwordPatternFlag = true;
		} 
			
		return passwordPatternFlag;
	}

	@Override
	public boolean emailCheck(Category category) throws CategoryManagementException {

		boolean emailCheckFlag2 = categoryDao.adminEmail(category);
		return emailCheckFlag2;
	}

	@Override
	public boolean passwordCheck(Category category) throws CategoryManagementException {

		boolean passwordCheckFlag2 = categoryDao.adminPassword(category);
		return passwordCheckFlag2;
	}

	public boolean validateName(String originalName) throws CategoryManagementException {

		boolean editFlag = true;
		String nameRegEx = "[A-Z]{1}[a-zA-Z]{4,9}";
		if (!Pattern.matches(nameRegEx, originalName)) {
			editFlag = false;

			throw new CategoryManagementException(
					"first letter should be capital and length must be in between 5 to 10 and does not accept any numbers \n");

		} else
			editFlag = true;
		return editFlag;

	}
}
