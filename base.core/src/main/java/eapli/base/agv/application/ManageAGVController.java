package eapli.base.agv.application;

import eapli.base.agv.domain.AGVBuilder;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


public class ManageAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AGVBuilder AB = new AGVBuilder();

    public ManageAGVController(){

    }

    public SystemUser getWarehouseEmployeeLoggedIn(){
        return authz.session().get().authenticatedUser();
    }

    public Warehouse getWarehouseFromWE(SystemUser systemUser){
        WarehouseRepository wRepo = PersistenceContext.repositories().warehouse();
        for (Warehouse w: wRepo.findAllActive()) {
            if(w.getlWarehouseEmployee().contains(systemUser)){
                return w;
            }
        }
        return null;
    }

    public boolean createAGV(){
        return false;
    }

    public boolean saveAGV(){
        return false;
    }

}
