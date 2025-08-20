package com.pahanaedu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Item entity representing books and products in the inventory
 */
@Entity
@Table(name = "items")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "item_code", unique = true, nullable = false, length = 20)
    @NotBlank(message = "Item code is required")
    private String itemCode;
    
    @Column(name = "item_name", nullable = false, length = 100)
    @NotBlank(message = "Item name is required")
    private String itemName;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;
    
    @Column(name = "stock_quantity", nullable = false)
    @PositiveOrZero(message = "Stock quantity cannot be negative")
    private Integer stockQuantity = 0;
    
    @Column(length = 50)
    private String category;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BillItem> billItems;
    
    // Constructors
    public Item() {}
    
    public Item(String itemCode, String itemName, String description, BigDecimal unitPrice, Integer stockQuantity, String category) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<BillItem> getBillItems() {
        return billItems;
    }
    
    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
    
    /**
     * Check if item has sufficient stock for the requested quantity
     */
    public boolean hasSufficientStock(Integer requestedQuantity) {
        return this.stockQuantity >= requestedQuantity;
    }
    
    /**
     * Reduce stock quantity by the specified amount
     */
    public void reduceStock(Integer quantity) {
        if (quantity > this.stockQuantity) {
            throw new IllegalArgumentException("Insufficient stock available");
        }
        this.stockQuantity -= quantity;
    }
    
    /**
     * Increase stock quantity by the specified amount
     */
    public void increaseStock(Integer quantity) {
        this.stockQuantity += quantity;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", unitPrice=" + unitPrice +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + '\'' +
                '}';
    }
}

