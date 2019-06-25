package com.capg.categorymanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.*;
import org.apache.log4j.PropertyConfigurator;

import com.capg.categorymanagement.connection.DbConnection;
import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;

public class ICategoryDao implements IcategoryDaoImpl {
	Category category = new Category();

	Scanner scanner = new Scanner(System.in);

	Connection connection = null;
	PreparedStatement statement = null;

	Logger logger = Logger.getRootLogger();

	public ICategoryDao() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : takeDetails() 
	 * - Input Parameters : 
	 * - Return Type : List 
	 * -  Throws : CategoryManagementException 
	 * - Author : CAPGEMINI 
	 * - Creation Date :24/06/2019 
	 * - Description : Listing categories
	 ********************************************************************************************************/

	public List<Category> takeDetails() throws CategoryManagementException {

		connection = DbConnection.getConnection();
		List<Category> list = new ArrayList<>();

		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(QueryMapper.LISTING_DETAILS_QUERY);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setCategoryId(resultSet.getInt(1));
				category.setCategoryName(resultSet.getString(2));
				list.add(category);

			}
			logger.info("Listing is done");
		} catch (SQLException e) {
			logger.error("error while listing the details");
			throw new CategoryManagementException("problem occured while creating ps");
		}
		return list;
	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : createCategory(Category category) 
	 * - Input Parameters :
	 * - Category category 
	 * - Return Type : integer 
	 * - Throws : CategoryManagementException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 24/06/2019
	 * - Description : Creating Category
	 ********************************************************************************************************/

	public int createCategory(Category category) throws CategoryManagementException {

		connection = DbConnection.getConnection();
		int createFlag = 0;

		try {

			PreparedStatement statement = null;
			PreparedStatement statement1 = null;
			ResultSet resultSet = null;

			statement = connection.prepareStatement(QueryMapper.CREATE_CATEGORY_QUERY);
			statement.setString(1, category.getCategoryName());
			int Flag = statement.executeUpdate();

			if (Flag == 1) {

				statement1 = connection.prepareStatement(QueryMapper.DISPLAYING_CATEGORY_ID_QUERY);

				resultSet = statement1.executeQuery();
				while (resultSet.next()) {

					createFlag = resultSet.getInt(1);

				}
			}

			logger.info("New Category is created");

		}

		catch (SQLException e) {

			logger.error("error while creating a category ");
			createFlag = 0;

		}
		return createFlag;

	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : editCategory(Category category) 
	 * - Input Parameters : Category category
	 * - Return Type : boolean 
	 * - Throws : CategoryManagementException
	 * - Author : CAPGEMINI 
	 * - Creation Date : 24/06/2019
	 * - Description : To Edit category
	 ********************************************************************************************************/

	public boolean editCategory(Category category) throws CategoryManagementException {

		connection = DbConnection.getConnection();
		PreparedStatement statement1 = null;
		try {
			statement1 = connection.prepareStatement(QueryMapper.EDITING_CATEGORY_QUERY);
			statement1.setString(1, category.getNewCategoryName());
			statement1.setString(2, category.getCategoryName());
			statement1.executeUpdate();

			logger.info("Editing is done");

		}

		catch (SQLException e) {

			//System.out.println("The name of the category is already existing");
			logger.error("error exist while editing category  ");
		}
		return true;

	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : existingCategory(Category category) 
	 * - Input Parameters :Category category
	 *  - Return Type : boolean 
	 *  - Throws : * CategoryManagementException 
	 * - Author : CAPGEMINI 
	 * - Creation Date : 24/06/2019
	 * - Description : To check whether the Category is existing or not.
	 ********************************************************************************************************/

	public boolean existingCategory(Category category) throws CategoryManagementException, SQLException {

		boolean existFlag = false;
		int Flag = 0;
		connection = DbConnection.getConnection();
		statement = connection.prepareStatement(QueryMapper.CHECKING_THE_CATEGORY_QUERY);
		statement.setString(1, category.getCategoryName());
		try {
			Flag = statement.executeUpdate();
			if (Flag == 1) {
				existFlag = true;
			}
		} catch (SQLException e) {

			logger.error("error exist while checking the category names ");
			e.printStackTrace();
		}
		return existFlag;
	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : deleteCategory(String deleteName) - Input Parameters :
	 * String deleteName - Return Type : boolean - Throws :
	 * CategoryManagementException - Author : CAPGEMINI - Creation Date : 24/06/2019
	 * - Description : To delete the categories.
	 ********************************************************************************************************/

	public boolean deleteCategory(String deleteName) throws CategoryManagementException {

		connection = DbConnection.getConnection();
		boolean deleteFlag = true;

		try {
			PreparedStatement ps1 = connection.prepareStatement(QueryMapper.DELETING_CATEGORY_QUERY);
			ps1.setString(1, deleteName);
			int Flag = ps1.executeUpdate();
			
			if (Flag == 1) {

				deleteFlag = true;
				logger.info("Category details deleted successfully:");
			} else {
				deleteFlag = false;

			}

			
		}

		catch (SQLException e) {

			e.printStackTrace();
			logger.error("error while deleting the category ");
		}
		return deleteFlag;

	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : adminEmail(Category category) - Input Parameters : Category
	 * category - Return Type : boolean - Throws : CategoryManagementException -
	 * Author : CAPGEMINI - Creation Date : 24/06/2019 - Description : To check
	 * whether the inserted admin email exists in the database or not.
	 ********************************************************************************************************/

	public boolean adminEmail(Category category) throws CategoryManagementException {

		connection = DbConnection.getConnection();
		boolean Flag = false;

		try {

			PreparedStatement ps = connection.prepareStatement(QueryMapper.CHECKING_ADMIN_EMAIL_QUERY);
			ps.setString(1, category.getAdminEmail());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Flag = true;
			}
			logger.info("Accessing the Email");
		}

		catch (SQLException e) {

			e.printStackTrace();
			logger.error("admin email not present ");
		}
		return Flag;

	}

	// ------------------------ 1. Category Management Application
	// --------------------------
	/*******************************************************************************************************
	 * - Function Name : adminPassword(Category category) - Input Parameters :
	 * Category category - Return Type : boolean - Throws :
	 * CategoryManagementException - Author : CAPGEMINI - Creation Date : 24/06/2019
	 * - Description : To check whether the inserted password exists in the database
	 * or not.
	 ********************************************************************************************************/

	public boolean adminPassword(Category category) throws CategoryManagementException {

		connection = DbConnection.getConnection();
		boolean Flag = false;

		try {

			PreparedStatement ps = connection.prepareStatement(QueryMapper.CHECKING_ADMIN_PASSWORD_QUERY);
			ps.setString(1, category.getAdminEmail());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Flag = true;
			}

			logger.info("Password is correct");

		}

		catch (SQLException e) {

			logger.error("admin login failed ");
			e.printStackTrace();
		}
		return Flag;
	}

}
