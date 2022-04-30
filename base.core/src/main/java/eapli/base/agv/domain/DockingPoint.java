package eapli.base.agv.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class DockingPoint implements ValueObject {

    private int agvDockId;

    public DockingPoint(int agvDockId){
        this.agvDockId = agvDockId;
    }

    public DockingPoint() {

    }

    @Override
    public String toString(){
        return "AGVDock Id: " + this.agvDockId;
    }
}
