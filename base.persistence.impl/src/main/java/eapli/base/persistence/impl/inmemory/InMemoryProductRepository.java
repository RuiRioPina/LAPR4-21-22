package eapli.base.persistence.impl.inmemory;

import eapli.base.product.domain.Product;
import eapli.base.product.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainAutoNumberRepository;

public class InMemoryProductRepository extends InMemoryDomainAutoNumberRepository<Product>
        implements ProductRepository {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    static {
        InMemoryInitializer.init();
    }

}
