package com.capg.categorymanagement.ui;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capg.categorymanagement.dto.Category;
import com.capg.categorymanagement.exception.CategoryManagementException;
import com.capg.categorymanagement.service.ICategoryService;

public class Main {

	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		ICategoryService categoryService = new ICategoryService();
		PropertyConfigurator.configure("resources//log4j.properties");

		Category category = new Category();
		boolean emailCheckFlag1 = false;
		boolean passwordCheckFlag1 = false;
		boolean emailCheckFlag2 = false;
		boolean passwordCheckFlag2 = false;

		do {
			System.out.println("Enter the Email");
			String admin_email = scanner.next();
			category.setAdminEmail(admin_email);
			emailCheckFlag1 = categoryService.emailPattern(category);
			if(!emailCheckFlag1)
			{
				System.err.println("Email Pattern is wrong");
			}
			
			emailCheckFlag2 = categoryService.emailCheck(category);
			if(!emailCheckFlag2)
			{
				System.err.println("Email does not exists");
			}
			

			
		} while (!emailCheckFlag1 || !emailCheckFlag2);
             
		System.out.println("Enter the password");
		
		do {
			
			String admin_password = scanner.next();
			category.setAdminPassword(admin_password);
			passwordCheckFlag1 = categoryService.passwordPattern(category);
			if(!passwordCheckFlag1)
			{
				System.err.println("Password Pattern is wrong");
			}
			
			passwordCheckFlag2 = categoryService.passwordCheck(category);

		} while (!passwordCheckFlag1 || !passwordCheckFlag2);
		try {

			int option = 0;
			boolean optionFlag = true;
			boolean continueValue = false;
			String continueChoice = null;
			boolean deleteFlag = true;
			boolean editFlag = false;
			boolean patternFlag = false;
			boolean Flag1 = false;
			do {
				do {
					System.out.println("   CATEGORY MANAGEMENT ");

					System.out.println("_______________________________\n");

					System.out.println("1.Listing Categories ");
					System.out.println("2.Creating Category");
					System.out.println("3.Editing Category");
					System.out.println("4.Deleting Category");
					System.out.println("________________________________");
					System.out.println("Select an option:");

					try {
						Scanner s = new Scanner(System.in);
						option = s.nextInt();

						switch (option) {

						case 1:
							System.out.println("Listing ");

							try {
								List<Category> list = categoryService.takeDetails();
								if (list != null) {
									Iterator<Category> iterator = list.iterator();
									while (iterator.hasNext()) {
										System.out.println(iterator.next());
									}
								}
							} catch (CategoryManagementException e) {
								System.err.println(e.getMessage());
							}
							continueValue = true;
							break;

						case 2:
							int createFlag = 0;
							String createCategory = null;
							do {

								try {
									do {

										System.out.println("Enter the category Name");
										createCategory = scanner.next();

										editFlag = categoryService.validateName(createCategory);

									} while (!editFlag);
									
									category.setCategoryName(createCategory);

									createFlag = categoryService.createCategory(category);
								
									if (createFlag != 0) {
										Flag1 = true;
										System.out.println(
												"New Category is successfully created with categoryId " + createFlag);

									}

									else {
										Flag1 = false;
										System.out.println("Category is already existing");
									}
								} catch (CategoryManagementException e) {

									System.err.println(e.getMessage());
								}
							} while (!Flag1);

							break;
						case 3:
							System.out.println("Editing ");

							
							boolean existFlag = false;
							
							
							do {
								Category category1 = new Category();
								System.out.println("Enter the name of the category to Edit");
								try {

									String originalName = scanner.next();

									category1.setCategoryName(originalName);
									patternFlag = categoryService.validateName(originalName);
                                 
									existFlag = categoryService.existingCategory(category1);
								}
									 catch (CategoryManagementException e) {
											System.err.println(e.getMessage());
										}
										
										
									} while (!patternFlag || !existFlag);
							boolean patternFlag1 = false;

                               do {			System.out.println("Enter the new Name");					
										try {
										
										String newName = scanner.next();
										patternFlag1 = categoryService.validateName(newName);
										category.setNewCategoryName(newName);
									
										editFlag = categoryService.editCategory(category);
									
									}
									catch (CategoryManagementException e) {
										System.err.println(e.getMessage());
									}
									

								 } while(!patternFlag1);
								
                              
							continueValue = true;
							break;

						case 4:
							do {
								System.out.println("deleting ");
								System.out.println("Enter the name of the category to delete");
								String deleteName = scanner.next();
								deleteFlag = categoryService.deleteCategory(deleteName);
								if (deleteFlag) {
									System.out.println("The category is deleted successfully");
								} else {
									System.err.println("This category is not existing");
								}
							} while (!deleteFlag);
							break;
						case 5:

							System.exit(0);
							continueValue = true;
							break;
						default:
							System.err.println("Enter only digits");
							continueValue = true;
							break;

						}

					} catch (InputMismatchException e) {

						System.err.println("Input Mismatch ");
						optionFlag = false;
						// throw new CategoryManagementException("Input Mismatch");

					}
				} while (!optionFlag);
                     
				   
				System.out.println("do you want to continue again [yes/no]");
				
				do {

					
					continueChoice = scanner.nextLine();
					if (continueChoice.equalsIgnoreCase("yes")) {
						continueValue = true;
						break;
					} else if (continueChoice.equalsIgnoreCase("no")) {
						System.out.println("_______Thank you________");
						continueValue = false;
						break;
					} else {
						System.out.println("Enter yes or no");
						continueValue = false;
						continue;
					}
				} while (!continueValue);
			} while (continueValue);
		} catch (StackOverflowError e) {
			System.out.println(e);
		}
	}
}
