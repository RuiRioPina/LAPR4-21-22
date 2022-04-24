package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

@Entity
public class Row {
    @Id
    @Column(name = "id", nullable = false)
    @SerializedName("Id")
    private Long id;

    @SerializedName("begin")
    private Begin begin;
    @SerializedName("end")
    private End end;
    @SerializedName("shelves")
    private int shelve;

    public Long getId() {
        return id;
    }


}
