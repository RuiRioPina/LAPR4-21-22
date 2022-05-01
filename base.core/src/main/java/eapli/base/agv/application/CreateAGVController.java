package eapli.base.agv.application;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.AGVBuilder;
import eapli.base.agv.domain.DockingPoint;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.util.List;


public class CreateAGVController {

    private AGVBuilder AB;
    private AGVRepository aRepo = PersistenceContext.repositories().agvs();

    public CreateAGVController()  {
        AB = new AGVBuilder();
    }

    public AGV createAGV(Integer autonomy, Double capacity, Double weight, Double volume
                        , String shortDescription, DockingPoint dockingPoint) {
        this.AB = AB.createAGV(autonomy, capacity, weight, volume, shortDescription, dockingPoint);
        return AB.build();
    }

    public void setAGVDock(DockingPoint agvDock) {
        this.AB = AB.withAGVDock(agvDock);
    }

    public boolean checkAGVDockAvailability(List<DockingPoint> lFreeDocks) {
        return !lFreeDocks.isEmpty();
    }

    public String showAGVBuilder() {
        return this.AB.toString();
    }

    public void saveAGV(AGV agv) {
        this.aRepo.save(agv);
    }

}
