package eapli.base.warehousemanagement.domain;

import eapli.base.warehousemanagement.application.WarehouseController;

import java.io.FileNotFoundException;
import java.util.List;

public class WarehouseInfo {
    WarehouseController warehouseController = new WarehouseController();
    Warehouse warehouse;
    Warehouse warehouseWithTheLists;
    Aisle aisle;
    Row row;
    Shelf shelf;

    public WarehouseInfo() throws FileNotFoundException {
        warehouse = warehouseController.findWarehouse();
        warehouseWithTheLists = warehouseController.buildWarehousePlant(warehouse.getJsonPath());
        warehouseController.buildShelves(warehouseWithTheLists);
    }

    public List<Aisle> getAisles() {
        if (warehouseWithTheLists != null) {
            return warehouseWithTheLists.aisles();
        }
        return null;
    }


    public List<Row> getRows(Aisle aisle) {
        return aisle.rows();
    }


    public List<Shelf> getShelves(Row row) {
        return row.shelves();
    }

    public List<AGVDocks> getAVGDocks() {
        return warehouseWithTheLists.agvDocks();
    }
}
