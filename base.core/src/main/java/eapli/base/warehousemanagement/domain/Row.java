package eapli.base.warehousemanagement.domain;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Row {

    @SerializedName("begin")
    private Begin begin;

    @SerializedName("end")
    private End end;

    @SerializedName("shelves")
    private Integer numberShelves;
    public Row() {
        //ORM and GSON only
    }
    private List<Shelve> shelveList = new ArrayList<>();



    public void convertShelveNumberToPosition() {
        for (int i = 1; i <= numberShelves; i++) {
            Shelve shelve = new Shelve(i);
            shelveList.add(shelve);
        }
    }

    @Override
    public String toString() {
        return "\nRow:\n" +
                begin + "\n" +
                end + "\n" +
                "Shelves:\n" + shelveList + "\n";
    }
}
