package com.example.sqlitewithimage;

public class Product {
    private int id;
    private String name;
    private String status;
    private double price;
    private byte[] image;

    public Product(int id, String name, String status, double price, byte[] image) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.image = image;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
