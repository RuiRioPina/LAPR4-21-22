package eapli.base.agv.application;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.AGVBuilder;
import eapli.base.agv.domain.DockingPoint;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVDocks;
import eapli.base.warehousemanagement.domain.WarehouseInfo;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.FileNotFoundException;
import java.util.List;


public class CreateAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private AGVBuilder AB;
    private WarehouseInfo WI = new WarehouseInfo();
    private AGVRepository aRepo = PersistenceContext.repositories().agvs();
    private List<DockingPoint> lFreeDocks;

    public CreateAGVController() throws FileNotFoundException {

    }

    public void createAGV(Integer autonomy, Double capacity, Double weight, Double volume, String shortDescription){
        this.AB = AB.createAGV(autonomy,capacity,weight,volume,shortDescription);
    }

    public void setAGVDock(DockingPoint agvDock) {
        this.AB = AB.withAGVDock(agvDock);
    }

    public List<DockingPoint> getFreeDocks(){
        for (AGVDocks agvDock :this.WI.getAVGDocks()) {
            this.lFreeDocks.add(new DockingPoint(agvDock.getId()));
        }
        DockingPoint agvDock;
        for (AGV agv: aRepo.findAllActive()) {
            agvDock = agv.getAGVDock();
            this.lFreeDocks.remove(agvDock);
        }
        return this.lFreeDocks;
    }

    public boolean checkAGVDockAvailability(List<DockingPoint> lFreeDocks){
        return !lFreeDocks.isEmpty();
    }

    public String showAGVBuilder(){
        return this.AB.toString();
    }

    public AGV saveAGV(){
        return this.aRepo.save(this.AB.build());
    }

}
