package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import eapli.base.clientusermanagement.domain.Customer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aisle {
    @Column(name = "aisle_id", nullable = false)
    @SerializedName("Id")
    @Id
    private Long id;

    @ManyToOne()
    @JoinColumn(name="warehouse_id", nullable = true)
    private Warehouse warehouse;

    @Embedded
    @SerializedName("begin")
    private Begin begin;
    @Embedded
    @SerializedName("end")
    private End end;
    @Embedded
    @SerializedName("depth")
    private Depth depth;
    @SerializedName("accessibility")
    private String accessibility;
    @OneToMany
    @SerializedName("rows")
    private List<Row> row;

    public Long getId() {
        return id;
    }

    public List<Row> rows() {
        return row;
    }
}
