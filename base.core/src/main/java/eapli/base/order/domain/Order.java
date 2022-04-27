package eapli.base.order.domain;

import eapli.base.clientusermanagement.domain.Address;
import eapli.base.product.domain.Price;
import eapli.base.product.domain.Product;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * A Product Order
 * <p>
 *      This class represent orders. It follows the DDD approach where Order
 *      is the root identity of the Order Aggregate and some of its
 *      properties are instances of value objects.
 * </p>
 *      This approach may seem a little more complex than just having String or
 *      native type attributes but provides for real semantic of the domain and
 *      follows the Single Responsibility Pattern
 */

@Entity
@Table
public class Order implements Serializable, AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Id
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @Column(name = "billing_address")
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @Column(name = "delivery_address")
    private Address deliveryAddress;

    @Embedded
    @Column(name = "total_amount", nullable = false)
    private Price totalAmount;

    @OneToOne
    @Column(name = "order_line", nullable = true)
    private OrderLine orderLine;

    @OneToOne
    @Column(name = "order_state", nullable = true)
    private OrderState orderState;

    @OneToOne
    @Column(name = "payment", nullable = true)
    private Payment payment;

    @OneToOne
    @Column(name = "order_line", nullable = true)
    private Shipment shipment;

    public Order(LocalDate date, Long customerId, Price totalAmount) {
        this.date = date;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public Order() {

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return null;
    }

    @Id
    public Long getId() {
        return id;
    }

    public Price getTotalAmount() {
        return totalAmount;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public Payment getPayment() {
        return payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
