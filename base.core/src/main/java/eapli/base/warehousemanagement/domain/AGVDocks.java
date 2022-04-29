package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import javax.persistence.*;

@Entity
public class AGVDocks {
    @Id
    @Column(name = "id", nullable = false)
    @SerializedName("Id")
    private String id;

    @Transient
    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;

    @Transient
    @Embedded
    @SerializedName("begin")
    private Begin begin;
    @Transient
    @Embedded
    @SerializedName("end")
    private End end;
    @Transient
    @Embedded
    @SerializedName("depth")
    private Depth depth;
    @Transient
    @Column
    @SerializedName("accessibility")
    private String accessibility;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nAGVDock: \n" +
                "id=" + id + "\n" +
                begin + "\n" +
                end + "\n" +
                depth + "\n" +
                "accessibility=" + accessibility;
    }
}
