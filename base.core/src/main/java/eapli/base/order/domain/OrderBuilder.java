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
        Order order = new Order(LocalDate.now(), this.customerId, this.deliveryAddress, this.billingAddress, this.productIntegerMap, this.totalAmount);
        return order;
    }
}
