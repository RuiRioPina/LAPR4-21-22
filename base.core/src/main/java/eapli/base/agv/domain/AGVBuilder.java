package eapli.base.agv.domain;

import eapli.base.warehousemanagement.domain.AGVDocks;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.domain.model.DomainFactory;

public class AGVBuilder implements DomainFactory<AGV> {

    private Integer autonomy;
    private Double capacity;
    private String shortDescription;
    private Double weight;
    private Double volume;

    private AGVState agvState = null;

    private AGVDocks agvDocks;
    //private AGVModel agvModel = null;

    public AGVBuilder(){

    }

    public AGVBuilder createAGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription){
        this.shortDescription = shortDescription;
        this.capacity = capacity;
        this.autonomy = autonomy;
        this.weight = weight;
        this.volume = volume;
        return this;
    }

    public AGVBuilder withAGVState(AGVState agvState){
        this.agvState = agvState;
        return this;
    }

    public AGVBuilder withAGVDock(AGVDocks agvDock){
        this.agvDocks = agvDock;
        return this;
    }

    /*public AGVBuilder withAGVModel(AGVModel agvModel){
        this.agvModel = agvModel;
        return this;
    }
    */

    @Override
    public AGV build() {
        return new AGV(this.autonomy,this.capacity,this.weight,this.volume,this.shortDescription,this.agvDocks);
    }

    @Override
    public String toString(){
        return "AGV: " + "\n" +
                "----------------------------" + "\n" +
                "Autonomy (in minutes):      " + this.autonomy +"\n" +
                "Capacity (in kg):           " + String.format("%.2f", this.capacity) + "\n" +
                "Weight (in kg):             " + String.format("%.2f", this.weight) + "\n" +
                "Volume (in dm^3):           " + String.format("%.2f", this.volume) + "\n" +
                "Short Description:          " + shortDescription + "\n" +
                "Base Location (AGV Dock):   " + this.agvDocks + "\n" +
                "----------------------------" + "\n";
    }
}
