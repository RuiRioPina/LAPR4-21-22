package eapli.base.product.domain;

import org.junit.Test;


public class ProductTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Product instance = new Product(null, null, null,null, null, null, null, null, null, null);
    }

}