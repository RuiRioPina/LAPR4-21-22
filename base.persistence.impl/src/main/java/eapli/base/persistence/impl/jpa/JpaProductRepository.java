package eapli.base.persistence.impl.jpa;

import eapli.base.product.domain.Product;
import eapli.base.product.repositories.ProductRepository;


public class JpaProductRepository extends BasepaRepositoryBase <Product,Long,Long>
implements ProductRepository {

    public JpaProductRepository() {
        super("id");
    }

    }
