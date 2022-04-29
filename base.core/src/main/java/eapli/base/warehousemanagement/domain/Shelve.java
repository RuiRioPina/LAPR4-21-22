package eapli.base.warehousemanagement.domain;

import java.util.ArrayList;
import java.util.List;
public class Shelve {
    private Integer position;

    private Row row;


    private List<Bin> bin = new ArrayList<>();

    public Shelve(int position) {
        this.position = position;
    }

    protected Shelve() {
        //ORM only
    }

    @Override
    public String toString() {
        return "\nShelve: " +
                "position=" + position + " " +
                "bin=" + bin +" ";
    }
}
