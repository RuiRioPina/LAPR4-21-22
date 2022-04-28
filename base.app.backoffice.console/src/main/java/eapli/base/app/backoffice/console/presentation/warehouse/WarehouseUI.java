package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.warehousemanagement.application.WarehouseController;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.FileNotFoundException;

public class WarehouseUI extends AbstractUI {

    private final WarehouseController theController = new WarehouseController();

    @Override
    protected boolean doShow() {
        String response = "y";
        //boolean alreadyExistsInDatabase = theController.alreadyInDatabase();
        boolean alreadyExistsInDatabase = false;
        if (alreadyExistsInDatabase) {
            response = Console.readLine("Warehouse plant is already database do you want still to import it?");
        }
        /*&& theController.alreadyInDatabase()*/
        if ((response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes"))) {
            String fileName;
            do {
                fileName = Console.readLine("File Name");
                if (fileName.isEmpty()) {
                    System.out.println("This field can't be empty.");
                } else if (!fileName.contains(".json")) {
                    fileName = fileName + ".json";
                }
            } while (fileName.isEmpty());


            try {
                Warehouse warehouse = theController.buildWarehousePlant(fileName);
                theController.buildShelves(warehouse);
                // saveWarehouse(warehouse, alreadyExistsInDatabase);
                System.out.println(warehouse);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Set up Warehouse Plant";
    }

    public void saveWarehouse(Warehouse warehouse, boolean alreadyExistsInDatabase) {
        if (alreadyExistsInDatabase) {
            theController.deletePreviousWarehouse();
            theController.saveWarehouse(warehouse);
        } else {
            theController.saveWarehouse(warehouse);
        }
    }
}
