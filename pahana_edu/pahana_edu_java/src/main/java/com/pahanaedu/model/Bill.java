package com.pahanaedu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Bill entity representing customer bills and transactions
 */
@Entity
@Table(name = "bills")
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bill_number", unique = true, nullable = false, length = 20)
    @NotBlank(message = "Bill number is required")
    private String billNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "Customer is required")
    private Customer customer;
    
    @Column(name = "bill_date", nullable = false)
    @NotNull(message = "Bill date is required")
    private LocalDate billDate;
    
    @Column(name = "units_billed", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Units billed must be greater than 0")
    private BigDecimal unitsBilled;
    
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Total amount must be greater than 0")
    private BigDecimal totalAmount;
    
    @Column(name = "payment_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // Relationships
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BillItem> billItems;
    
    // Constructors
    public Bill() {}
    
    public Bill(String billNumber, Customer customer, LocalDate billDate, BigDecimal unitsBilled, BigDecimal totalAmount) {
        this.billNumber = billNumber;
        this.customer = customer;
        this.billDate = billDate;
        this.unitsBilled = unitsBilled;
        this.totalAmount = totalAmount;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBillNumber() {
        return billNumber;
    }
    
    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public LocalDate getBillDate() {
        return billDate;
    }
    
    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
    
    public BigDecimal getUnitsBilled() {
        return unitsBilled;
    }
    
    public void setUnitsBilled(BigDecimal unitsBilled) {
        this.unitsBilled = unitsBilled;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<BillItem> getBillItems() {
        return billItems;
    }
    
    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
    
    /**
     * Calculate total amount from bill items
     */
    public BigDecimal calculateTotalAmount() {
        if (billItems == null || billItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        return billItems.stream()
                .map(BillItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Calculate total units from bill items
     */
    public BigDecimal calculateTotalUnits() {
        if (billItems == null || billItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        return billItems.stream()
                .map(item -> BigDecimal.valueOf(item.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billNumber='" + billNumber + '\'' +
                ", billDate=" + billDate +
                ", totalAmount=" + totalAmount +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}

/**
 * Enumeration for payment status
 */
enum PaymentStatus {
    PENDING, PAID, CANCELLED, REFUNDED
}

