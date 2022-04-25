package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.warehousemanagement.JsonParser;
import eapli.base.warehousemanagement.domain.Warehouse;

import java.io.FileNotFoundException;

public class WarehouseController {
    public Warehouse buildWarehousePlant(String fileName) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.readJson();
    }
}
