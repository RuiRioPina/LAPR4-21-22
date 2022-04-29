package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {

    private Integer autonomy;
    private Double capacity;
    private Double weight;
    private Double volume;

    private AGVState agvState = null;
    private AGVModel agvModel = null;
    private Warehouse warehouse = null;

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

    public AGVBuilder withAGVState(AGVState agvState){
        this.agvState = agvState;
        return this;
    }

    public AGVBuilder withAGVModel(AGVModel agvModel){
        this.agvModel = agvModel;
        return this;
    }

    @Override
    public AGV build() {
        return new AGV(this.autonomy,this.capacity,this.weight,this.volume,this.agvModel,this.agvState,this.warehouse);
    }
}
