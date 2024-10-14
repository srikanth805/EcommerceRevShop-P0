package com.revShop.models;

public class Product {
    private int id;         
    private String name;    
    private double price;   
    private String description; 
    private int quantity; 
    private int userId; // Add userId field
    private String imageUrl; // New field for product image URL

    // Constructor
    public Product(int id, String name, double price, String description, int quantity, int userId, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity; 
        this.userId = userId; 
        this.imageUrl = imageUrl; 
    }

    // Default constructor
    public Product() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", quantity=" + quantity + ", userId=" + userId + ", imageUrl=" + imageUrl + "]";
	}

 
}
