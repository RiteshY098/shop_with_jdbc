package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {
	public int addProduct(int id, String name, int price, int quantity, boolean availibility) {
		int rowsAffected = 0;
		Connection connection = null;
		Driver driver = new Driver();
		try {
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?);");
			
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availibility);
			
			rowsAffected = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return rowsAffected;
	}
	
	public void addMultipleProducts(ArrayList<Product> products) {
		Connection connection = null;
		Driver driver = new Driver();
		
			try {
				DriverManager.registerDriver(driver);
				FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
				Properties properties = new Properties();
				properties.load(fileInputStream);
				
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
				PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO products VALUES (?,?,?,?,?);");
				for (Product product : products) {
					prepareStatement.setInt(1, product.getP_id());
					prepareStatement.setString(2, product.getP_name());
					prepareStatement.setInt(3, product.getP_price());
					prepareStatement.setInt(4, product.getP_quantity());
					prepareStatement.setBoolean(5, product.isP_availability());
					prepareStatement.addBatch();
				}
				prepareStatement.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	public ResultSet fetchProduct(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM products where p_id=?");
			prepareStatement.setInt(1, id);
			
			resultSet = prepareStatement.executeQuery();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	
	public int removeProduct(int id) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM products where p_id=?");
			prepareStatement.setInt(1, id);
			
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
	}
	
	public int UpdatebyID(int id,String name) {
		Connection connection = null;
		int updateName = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE products SET p_name=? where p_id=?;");
			prepareStatement.setInt(2, id);
			prepareStatement.setString(1, name);
			updateName = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	return updateName;
	}
	
	public int UpdatebyPrice(int id, int price) {
		Connection connection = null;
		int updatePrice = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE products SET p_price=? where p_id=?;");
			prepareStatement.setInt(2, id);
			prepareStatement.setInt(1, price);
			updatePrice = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	return updatePrice;
	}
	public int UpdatebyQuantity(int id, int quantity) {
		Connection connection = null;
		int updateQuantity = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc",properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE products SET p_quantity=? where p_id=?;");
			prepareStatement.setInt(2, id);
			prepareStatement.setInt(1, quantity);
			updateQuantity = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	return updateQuantity;
	}
}
