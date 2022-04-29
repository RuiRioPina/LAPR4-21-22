package eapli.base.warehousemanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private Integer position;
    private int id;
    private Row row;


    private List<Bin> bin = new ArrayList<>();

    public Shelf(int position) {
        this.position = position;
        id = position;
    }

    protected Shelf() {
        //ORM only
    }

    @Override
    public String toString() {
        return "\nShelve: " +
                "position=" + position + " " +
                "bin=" + bin + " ";
    }
}
