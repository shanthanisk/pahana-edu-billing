package com.pahanaedu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Customer entity representing bookshop customers
 */
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_number", unique = true, nullable = false, length = 20)
    @NotBlank(message = "Account number is required")
    private String accountNumber;
    
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Customer name is required")
    private String name;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Address is required")
    private String address;
    
    @Column(name = "telephone_number", nullable = false, length = 20)
    @NotBlank(message = "Telephone number is required")
    @Pattern(regexp = "^\\+94[0-9]{9,10}$", message = "Invalid Sri Lankan phone number format")
    private String telephoneNumber;
    
    @Column(name = "units_consumed", nullable = false, precision = 10, scale = 2)
    @PositiveOrZero(message = "Units consumed cannot be negative")
    private BigDecimal unitsConsumed = BigDecimal.ZERO;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bill> bills;
    
    // Constructors
    public Customer() {}
    
    public Customer(String accountNumber, String name, String address, String telephoneNumber) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    public BigDecimal getUnitsConsumed() {
        return unitsConsumed;
    }
    
    public void setUnitsConsumed(BigDecimal unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
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
    
    public List<Bill> getBills() {
        return bills;
    }
    
    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", unitsConsumed=" + unitsConsumed +
                '}';
    }
}

