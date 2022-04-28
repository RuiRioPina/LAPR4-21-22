package eapli.base.agv.application;

import eapli.base.agv.domain.AGV;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public class ManageAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SystemUser warehouseEmployee = authz.session().get().authenticatedUser();

    public ManageAGVController(){

    }

    public SystemUser getWarehouseEmployeeLoggedIn(){
        return warehouseEmployee;
    }

    public Warehouse getWarehouseFromSC(Long id){
        return null;
    }

    public List<AGV> getAGVListFromWarehouse(Warehouse warehouse) {
        return null;
    }

    public AGV ManageAGV(AGV agv){
        return null;
    }


}
