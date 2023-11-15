package org.example.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "sale_price")
    private double salePrice;

    @ElementCollection
    @CollectionTable(name = "previous_purchase_prices", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "price")
    private List<Double> previousPurchasePrices;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Assume Image is a byte array representing the image
    @Lob
    @Column(name = "image")
    private byte[] image;

    // Constructors, getters, and sett
    public Product(){}
    public Product(Long id, String name, String description, double purchasePrice, double salePrice, List<Double> previousPurchasePrices, Vendor vendor, byte[] image) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.previousPurchasePrices = previousPurchasePrices;
        this.vendor = vendor;
        this.image = image;
    }

    public Product(String productName) {
        this.name = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public List<Double> getPreviousPurchasePrices() {
        return previousPurchasePrices;
    }

    public void setPreviousPurchasePrices(List<Double> previousPurchasePrices) {
        this.previousPurchasePrices = previousPurchasePrices;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


