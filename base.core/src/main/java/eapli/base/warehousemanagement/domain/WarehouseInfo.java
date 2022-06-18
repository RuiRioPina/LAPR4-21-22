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
        if (warehouse == null) throw new IllegalStateException("There is no warehouse plant.");
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

    public String [][] warehouseDashboard() {
        int Width = 18;
        int Length = 20;
        int down = 1;
        String[][] warehouse = new String[Width][Length];
        warehouse [3-down][1-down] ="dock";  warehouse [5-down][1-down] ="dock";  warehouse [13-down][1-down] ="dock";
        warehouse [15-down][1-down] ="dock"; warehouse [4-down][20-down] ="dock"; warehouse [14-down][20-down] ="dock";
        for (int i = 5; i < 17; i++) {
            warehouse [0] [i-down] = "grey";
            warehouse [7] [i-down] = "grey";
            warehouse [8] [i-down] = "grey";
            warehouse [17][i-down] = "grey";
            warehouse [9] [i-down] = "orange";
            warehouse [10][i-down] = "orange";
        }
        return warehouse;
    }
}
