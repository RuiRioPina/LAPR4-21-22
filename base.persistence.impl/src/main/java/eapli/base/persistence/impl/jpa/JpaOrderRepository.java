package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productOrder.domain.ProductOrder;
import eapli.base.productOrder.repositories.OrderRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaOrderRepository extends JpaAutoTxRepository<ProductOrder,Long, Long>
        implements OrderRepository {

    public JpaOrderRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaOrderRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<ProductOrder> findAllActive() {
        return match("e.systemUser.active = true");
    }

    @Override
    public Optional<ProductOrder> findById(final Long number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.id=:number", params);
    }

    public Optional<ProductOrder> findByCustomerId(final Long number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.customer_id=:number", params);
    }
}

