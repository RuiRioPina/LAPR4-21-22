package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.product.domain.Product;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
@Table
public class Bin implements AggregateRoot<Long> {
    @Id
    @Column(name = "bin_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="shelve_id", nullable=false)
    private Shelve shelve;

    @OneToOne
    private Product product;


    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public Long identity() {
        return id;
    }
}
