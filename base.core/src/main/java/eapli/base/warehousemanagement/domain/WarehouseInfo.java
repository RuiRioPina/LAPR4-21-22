package eapli.base.warehousemanagement.domain;

import eapli.base.warehousemanagement.application.WarehouseController;

import java.util.List;

public class WarehouseInfo {
    WarehouseController warehouseController = new WarehouseController();
    Warehouse warehouse;
    Aisle aisle;
    Row row;
    Shelf shelf;

    public WarehouseInfo() {
        warehouse = warehouseController.findWarehouse();
    }

    public List<Aisle> getAisles() {
        if (warehouse != null) {
            return warehouse.aisles();
        }
        return null;
    }


    public List<Row> getRows(Aisle aisle) {
        return aisle.rows();
    }


    public List<Shelf> getShelves(Row row) {
        return row.shelves();
    }

    public List<AGVDocks> getAVGDocks(Row row) {
        return warehouse.agvDocks();
    }
}
