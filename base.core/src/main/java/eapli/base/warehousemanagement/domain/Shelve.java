package eapli.base.warehousemanagement.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Shelve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shelve_id", nullable = false)
    private Long id;
    private int position;

    @ManyToOne
    @JoinColumn(name="row_id", nullable=false)
    private Row row;


    @OneToMany
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
