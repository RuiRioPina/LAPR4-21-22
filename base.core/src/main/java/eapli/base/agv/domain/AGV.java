package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;

@Entity
public class AGV implements DomainEntity<AGV> {

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


    public AGV(){

    }

    public AGV(Integer autonomy, Double capacity, Double weight, Double volume, AGVModel agvModel, AGVState agvState, Warehouse warehouse) {
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.agvModel = agvModel;
        this.agvState = agvState;
        this.warehouse = warehouse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public AGV identity() {
        return null;
    }
}
