package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Row {
    @Id
    @Column(name = "row_id", nullable = false)
    @SerializedName("Id")
    private Long id;


    @ManyToOne
    @JoinColumn(name="aisle_id", nullable=false)
    private Aisle aisle;

    @Embedded
    @SerializedName("begin")
    private Begin begin;

    @Embedded
    @SerializedName("end")
    private End end;
    @Column
    @SerializedName("shelves")
    private Integer numberShelves;
    public Row() {
        //ORM and GSON only
    }
    @OneToMany
    private List<Shelve> shelveList = new ArrayList<>();

    public Long getId() {
        return id;
    }


    public void convertShelveNumberToPosition() {
        for (int i = 1; i <= numberShelves; i++) {
            Shelve shelve = new Shelve(i);
            shelveList.add(shelve);
        }
    }

    @Override
    public String toString() {
        return "\nRow:\n" +
                "id=" + id + "\n" +
                begin + "\n" +
                end + "\n" +
                "Shelves:\n" + shelveList + "\n";
    }
}
