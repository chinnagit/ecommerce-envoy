/**
 * 
 */
package com.ecommerce.products.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chinnb
 *
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

//    "unit_discount_amount":0,"min_price":6.35,"discount_amount":0,"created_on":"2016-12-26T09:28:48+00:00","product_name":"Basic T-shirt - dark blue/white","price":11.99,"taxful_price":11.99,"base_unit_price":11.99}{"base_price":24.99,"discount_percentage":0,"quantity":1,"manufacturer":"Oceanavigations","tax_amount":0,"product_id":19400,"category":"Men's Clothing","sku":"ZO0299602996","taxless_price":24.99,"unit_discount_amount":0,"min_price":11.75,"discount_amount":0,"created_on":"2016-12-26T09:28:48+00:00","product_name":"Sweatshirt - grey multicolor","price":24.99,"taxful_price":24.99,"base_unit_price":24.99

//    @JsonProperty("base_price")
//    BigDecimal basePrice;
//    @JsonProperty("discount_percentage")
//    BigDecimal discountPerc;
//    @JsonProperty("quantity")
//    int quantity;
    @JsonProperty("manufacturer")
    String manufacturer;
//    @JsonProperty("tax_amount")
//    BigDecimal taxAmount;
    @JsonProperty("product_id")
    int productId;
    @JsonProperty("category")
    String category;
    @JsonProperty("sku")
    String sku;
//    @JsonProperty("taxless_price")
//    BigDecimal taxLessPrice;
//    @JsonProperty("unit_discount_amount")
//    BigDecimal unitDiscount;
//    @JsonProperty("min_price")
//    BigDecimal minPrice;
//    @JsonProperty("discount_amount")
//    BigDecimal discountAmount;
    @JsonProperty("product_name")
    String productName;
    @JsonProperty("price")
    BigDecimal price;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

//    public BigDecimal getBasePrice() {
//        return basePrice;
//    }
//
//    public void setBasePrice(BigDecimal basePrice) {
//        this.basePrice = basePrice;
//    }
//
//    public BigDecimal getDiscountPerc() {
//        return discountPerc;
//    }
//
//    public void setDiscountPerc(BigDecimal discountPerc) {
//        this.discountPerc = discountPerc;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

//    public BigDecimal getTaxAmount() {
//        return taxAmount;
//    }
//
//    public void setTaxAmount(BigDecimal taxAmount) {
//        this.taxAmount = taxAmount;
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

//    public BigDecimal getTaxLessPrice() {
//        return taxLessPrice;
//    }
//
//    public void setTaxLessPrice(BigDecimal taxLessPrice) {
//        this.taxLessPrice = taxLessPrice;
//    }
//
//    public BigDecimal getUnitDiscount() {
//        return unitDiscount;
//    }
//
//    public void setUnitDiscount(BigDecimal unitDiscount) {
//        this.unitDiscount = unitDiscount;
//    }
//
//    public BigDecimal getMinPrice() {
//        return minPrice;
//    }
//
//    public void setMinPrice(BigDecimal minPrice) {
//        this.minPrice = minPrice;
//    }
//
//    public BigDecimal getDiscountAmount() {
//        return discountAmount;
//    }
//
//    public void setDiscountAmount(BigDecimal discountAmount) {
//        this.discountAmount = discountAmount;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //    private int id;
//
//    String name;
//
//    String category;
//
//    BigDecimal price;
//
//    String description;
//
//    public Product() {
//
//    }
//
//    public Product(int id, String name, String category, BigDecimal price, String description) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//        this.price = price;
//        this.description = description;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", description=" + description + "]";
//    }

}
