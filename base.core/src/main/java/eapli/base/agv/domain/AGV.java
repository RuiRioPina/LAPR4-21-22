package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.AGVDocks;
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

    @Column(name= "short_description")
    private String shortDescription;

    /*@Enumerated
    @Column(name = "agv_model", nullable = false)
    private AGVModel agvModel;
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "agv_state")
    private AGVState agvState;

    /*
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agv")
    @Column(name = "agv_dock")
    private AGVDocks agvDocks;
     */

    public AGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription) {
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.shortDescription = shortDescription;
        this.agvState = AGVState.INACTIVE;
    }

    public AGV() {
        
    }

    /*
    public AGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription, AGVDocks agvDock) {
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.shortDescription = shortDescription;
        this.agvState = AGVState.INACTIVE;
        this.agvDocks = agvDock;
    }
     */

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
        return this;
    }
}
