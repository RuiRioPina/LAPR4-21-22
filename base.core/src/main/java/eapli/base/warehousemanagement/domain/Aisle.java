package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aisle {
    @Id
    @Column(name = "id", nullable = false)
    @SerializedName("Id")
    private Long id;

    @SerializedName("begin")
    private Begin begin;
    @SerializedName("end")
    private End end;
    @SerializedName("depth")
    private Depth depth;
    @SerializedName("accessibility")
    private String accessibility;
    @SerializedName("rows")
    private List<Row> row;

    public Long getId() {
        return id;
    }


}
