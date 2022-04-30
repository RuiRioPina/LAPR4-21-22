package eapli.base.agv.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AGV implements Serializable, AggregateRoot<Long> {

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


    @Embedded
    @Column(name = "docking_point")
    private DockingPoint agvDocks;


    public AGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription) {
        Preconditions.noneNull(autonomy,capacity,weight,volume,shortDescription);
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.shortDescription = shortDescription;
        this.agvState = AGVState.INACTIVE;
    }

    public AGV() {

    }

    public AGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription, DockingPoint agvDock) {
        Preconditions.noneNull(autonomy,capacity,weight,volume,shortDescription,agvDock);
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.shortDescription = shortDescription;
        this.agvState = AGVState.INACTIVE;
        this.agvDocks = agvDock;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public DockingPoint getAGVDock() {
        return this.agvDocks;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
