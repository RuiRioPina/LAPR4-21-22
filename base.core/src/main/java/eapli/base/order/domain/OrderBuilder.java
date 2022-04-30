package eapli.base.order.domain;

import eapli.base.clientusermanagement.domain.Address;
import eapli.base.product.domain.Price;
import eapli.base.product.domain.Product;
import eapli.framework.domain.model.DomainFactory;

import java.time.LocalDate;
import java.util.Map;

public class OrderBuilder implements DomainFactory<Order> {

    private Long customerId;
    private Address billingAddress = null;
    private Address deliveryAddress = null;
    private Price totalAmount = new Price((double) 0, (double) 0);
    private Map<Product, Integer> productIntegerMap;
    //both payment and shipment defaulted to PAYPAL and STANDARD respectively since they aren't of much use for the current SPRINT
    private Payment payment = Payment.PAYPAL;
    private Shipment shipment = Shipment.STANDARD;

    public OrderBuilder(){

    }

    public OrderBuilder(final Long customerId){
        this.customerId = customerId;
    }

    public OrderBuilder withBillingAddress(Address billingAddress){
        this.billingAddress = billingAddress;
        return this;
    }

    public OrderBuilder withDeliveryAddress(Address deliveryAddress){
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public OrderBuilder addProduct(Product product, Integer quantity){
        this.productIntegerMap.put(product,quantity);
        return this;
    }

    public Price calculateTotalAmount(){
        this.totalAmount.clear();
        for (Product p : productIntegerMap.keySet()) {
            this.totalAmount.addPrices(p.getPrice());
        }
        return this.totalAmount;
    }

    @Override
    public Order build() {
        this.calculateTotalAmount();
        Order order = new Order(LocalDate.now(), this.customerId, this.deliveryAddress, this.billingAddress, this.productIntegerMap, this.totalAmount, this.payment, this.shipment);
        return order;
    }

    @Override
    public String toString(){
        return "Order: \n" +
                "----------------------------" + "\n" +
                "Customer Id:                " + this.customerId + "\n" +
                "Billing Address:            " + this.billingAddress + "\n" +
                "Delivery Address:           " + this.deliveryAddress + "\n" +
                "Total Amount With Taxes:    " + String.format("%.2f", this.totalAmount.priceWithTaxes()) + "\n" +
                "Total Amount Without Taxes: " + String.format("%.2f", this.totalAmount.priceWithoutTaxes()) + "\n" +
                "----------------------------" + "\n" +
                "Products Ordered: \n" +
                productListToString() +
                "----------------------------" + "\n";
    }

    private String productListToString() {
        String productList = "";
        for (Product product: this.productIntegerMap.keySet()) {
            productList += String.format(this.productIntegerMap.get(product).toString() + " x " + product.getName() + "\n");
        }

        return productList;
    }
}
