package com.capg.categorymanagement.dao.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.capg.categorymanagement.dao.ICategoryDao;
import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;

class IcategoryDaoTest {

	@Test
	public void testCreateCategory() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		category.setCategoryName("Haneef");
		assertEquals(0, categoryDao.createCategory(category));

	}

	@Test
	public void testDeleteCategory() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		String deleteName = "Sudheer";

		assertEquals(false, categoryDao.deleteCategory(deleteName));

	}

	@Test
	public void testListCategory() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		assertNotNull(categoryDao.takeDetails());

	}

	@Test
	public void testAdminEmail() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		category.setAdminEmail("haneef9010@gmail.com");
		assertEquals(true, categoryDao.adminEmail(category));

	}

	@Test
	public void testAdminPassword() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		category.setAdminEmail("haneef9010@gmail.com");
		category.setAdminPassword("haneef52");
		assertEquals(true, categoryDao.adminPassword(category));

	}

	@Test
	public void testExistingCategory() throws CategoryManagementException, SQLException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		category.setCategoryName("Fiction");
		assertEquals(true, categoryDao.existingCategory(category));

	}

	@Test
	public void testEditingCategory() throws CategoryManagementException {
		Category category = new Category();
		ICategoryDao categoryDao = new ICategoryDao();
		category.setCategoryName("Yashwanth");
		category.setNewCategoryName("Mohammad");
		assertEquals(true, categoryDao.editCategory(category));

	}
}
