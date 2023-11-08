package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		do {
			System.out.println("----------------------------------------");
			System.out.print("Select operation to perform : ");
			System.out.println();
			System.out.println("----------------------------------------");
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("| 1. Add product\n| 2.Remove product\n| 3.Update product details\n| 4.Fetch product \n| 0.Exit");
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.print("Enter digit respective to desired option : ");
			System.out.println();
			System.out.println("-----------------------------------------");
			int userInput = myInput.nextInt();
			myInput.nextLine();
			
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("- - - - EXITED - - - -");
				System.exit(0);
				break;
			case 1:
				System.out.println("do you want to add one(press 1) or muliple (any other number except 1) ");
				int productsCount = myInput.nextInt();
				myInput.nextLine();
				if (productsCount == 1) {
					System.out.print("Enter product id : ");
					int i_p_id = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product name : ");
					String i_p_name = myInput.nextLine();
					System.out.print("Enter product price : ");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product quantity : ");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}

					if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability)) != 0) {
						System.out.println("product added \n");
					} else {
						System.out.println("product not added \n");
					}

				} else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.println("enter id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.println("enter name : ");
						product.setP_name(myInput.nextLine());
						System.out.println("enter price ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.println("enter quantity");
						int quantity = myInput.nextInt();
						product.setP_quantity(quantity);
						myInput.nextLine();
						boolean availibility = false;
						if (quantity > 0) {
							availibility = true;
						}
						product.setP_availability(availibility);
						products.add(product);

						System.out.println("do you want to add further yes(1) or no(0) : ");
						int toAdd = myInput.nextInt();
						myInput.nextLine();
						if (toAdd == 0) {
							toContinue = false;
						}
					} while (toContinue);
					shopController.addMultipleProducts(products);
				}
				break;
			case 2:
				System.out.print("enter id to remove : ");
				int p_id_toRemove = myInput.nextInt();
				myInput.nextLine();
				int removeProduct = shopController.removeProduct(p_id_toRemove);
				if(removeProduct == 0) {
					System.out.println("data not found");
				}else {
					System.out.println("id: "+ p_id_toRemove+" removed successfully");
				}
				
				break;
			case 3:
				System.out.print("enter the id to update : ");
				int productIdtoUpdate = myInput.nextInt();
				myInput.nextLine();
				ResultSet product2 = shopController.fetchProduct(productIdtoUpdate);
				if(product2.next()) {
					System.out.println("what do you want to update : ");
					System.out.println("1.Name \n2.Price \n3.Quantity");
					System.out.println("enter number respective to desired option : ");
					byte updateOption = myInput.nextByte();
					myInput.nextLine();
					switch (updateOption) {
					case 1:

						System.out.println("enter new name");
						String pname = myInput.nextLine();
						shopController.UpdatebyID(productIdtoUpdate, pname);
						break;
					case 2:

						System.out.println("enter new price");
						int pprice = myInput.nextInt();
						shopController.UpdatebyPrice(productIdtoUpdate, pprice);
						break;
					case 3:
						System.out.println("enter new quantity");
						int pquantity = myInput.nextInt();
						shopController.UpdatebyQuantity(productIdtoUpdate, pquantity);
						break;
					default:
						System.out.println(" - invalid input - ");
						break;
					}
				}else {
					System.out.println("product with given id doesnt exists");
				}
				break;
			case 4:
				System.out.print("enter the id to get details : ");
				int productIdtoFind = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct = shopController.fetchProduct(productIdtoFind);
				boolean next = fetchProduct.next();
				if (next) {
					System.out.println("id : "+fetchProduct.getInt(1) + "\nName : "+fetchProduct.getString(2)+"\nPrice : "+fetchProduct.getInt(3)+"\nQuantity : "+fetchProduct.getInt(4));
					if(fetchProduct.getBoolean(5)) {
						System.out.println(" : available");
					}else {
						System.out.println("not available");
					}
					
				} else {
					System.out.println("product with id "+productIdtoFind+ " does not exist");
				}
				break;

			default:
				System.out.println("- - - - INVALID SELECTION - - - -");
				break;
			}
		} while (true);
		
	}

}
