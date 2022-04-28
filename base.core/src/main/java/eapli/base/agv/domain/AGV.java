package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.Warehouse;

import javax.persistence.*;

@Entity
public class AGV {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "autonomy")
    private Integer autonomy;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "volume")
    private Double volume;

    @Embedded
    @Column(name = "agv_model", nullable = false)
    private AGVModel agvModel;

    @Embedded
    @Column(name = "avg_state", nullable = false)
    private AGVState agvState;

    @ManyToOne
    private Warehouse warehouse;



    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
