package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.application.WarehouseController;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.actions.Action;

import java.io.FileNotFoundException;
import java.util.Objects;

public class WarehouseBootstrapper implements Action {
    WarehouseController ctrl = new WarehouseController();
    private final WarehouseRepository pRepo = PersistenceContext.repositories().warehouse();
    private static final String defaultFile = "warehouse1";

    @Override
    public boolean execute() {
        Warehouse warehouse = null;
        try {
            warehouse = ctrl.buildWarehousePlant(defaultFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ctrl.buildShelves(Objects.requireNonNull(warehouse));
        warehouse.setJsonPath(defaultFile);
        if (ctrl.alreadyInDatabase()) {
            ctrl.deletePreviousWarehouse();
        }
        ctrl.saveWarehouse(warehouse);

        return false;
    }
}
