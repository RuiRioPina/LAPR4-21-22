package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {

    private Integer autonomy;
    private Double capacity;
    private Double weight;
    private Double volume;

    private AGVState agvState;
    private AGVModel agvModel;
    private Warehouse warehouse;

    public AGVBuilder(){

    }

    public AGVBuilder(Integer autonomy, Double capacity, Double weight, Double volume){
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
    }

    public AGVBuilder(Integer autonomy, Double capacity, Double weight, Double volume, Warehouse warehouse) {
        this.autonomy = autonomy;
        this.capacity = capacity;
        this.weight = weight;
        this.volume = volume;
        this.warehouse = warehouse;
    }

    @Override
    public AGV build() {
        return null;
    }
}
