package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;
import javax.persistence.*;

@Entity
public class AGVDocks {
    @Id
    @Column(name = "id", nullable = false)
    @SerializedName("Id")
    private String id;

    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
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
    @Column
    @SerializedName("accessibility")
    private String accessibility;

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AGVDocks{");
        sb.append("id=").append(id);
        sb.append(", begin=").append(begin);
        sb.append(", end=").append(end);
        sb.append(", depth=").append(depth);
        sb.append(", accessibility='").append(accessibility).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
