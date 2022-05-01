package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.agv.application.CreateAGVController;
import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.DockingPoint;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGVDocks;
import eapli.base.warehousemanagement.domain.WarehouseInfo;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class CreateAGVUI extends AbstractUI {

    private final CreateAGVController ctrl = new CreateAGVController();
    WarehouseInfo warehouseInfo = null;
    boolean validation = false;
    private final AGVRepository repo = PersistenceContext.repositories().agvs();


    public CreateAGVUI() throws FileNotFoundException {
        try {
            warehouseInfo = new WarehouseInfo();
            validation = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchElementException exception) {
            System.out.println("To configure an AGV you need a warehouse plant for its base location. Please upload one first.");
            validation = false;
        }
    }

    @Override
    protected boolean doShow() {

        Integer autonomy = null;
        if (validation) {
            do {
                try {
                    autonomy = Integer.parseInt(Console.readLine("Introduce the AGV autonomy: "));
                    validation = true;
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    validation = false;
                }

            }
            while (!validation);


            Double capacity = null;
            do {
                try {
                    capacity = Double.parseDouble(Console.readLine("Introduce the AGV capacity: "));
                    validation = true;
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    validation = false;
                }
            } while (!validation);

            Double weight = null;
            do {
                try {
                    weight = Double.parseDouble(Console.readLine("Introduce the AGV weight: "));
                    validation = true;
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    validation = false;
                }
            }
            while (!validation);

            Double volume = null;
            do {
                try {
                    volume = Double.parseDouble(Console.readLine("Introduce the AGV volume: "));
                    validation = true;
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    validation = false;
                }
            } while (!validation);

            String shortDescription;
            do {
                shortDescription = Console.readLine("Introduce the AGV short description: ");

                if (shortDescription.isEmpty() || shortDescription.length() > 30) {
                    System.out.println("This field can't be empty and must have a maximum of 30 chars.");
                }
            } while (shortDescription.isEmpty() || shortDescription.length() > 30);


            AGVDocks agvDock;
            agvDock = selectAgvDock(warehouseInfo);

            DockingPoint dockingPoint = new DockingPoint(agvDock.getId());
            System.out.println(dockingPoint);
            AGV agv = this.ctrl.createAGV(autonomy, capacity, weight, volume, shortDescription, dockingPoint);

            System.out.println(agv);

            this.ctrl.saveAGV(agv);


//        List<DockingPoint> lAGVdocks = this.ctrl.getFreeDocks();
//
//        if (this.ctrl.checkAGVDockAvailability(lAGVdocks)) {
//            System.out.println("The warehouse has free AGV docks!\n");
//            DockingPoint agvDock = (DockingPoint) Utils.selectsObject(lAGVdocks);
//
//            this.ctrl.setAGVDock(agvDock);
//            System.out.print("\n" +
//                    "Resulting AGV class: \n" +
//                    this.ctrl.showAGVBuilder());
//
//            String confirmation;
//            do {
//                confirmation = Utils.readLineFromConsole("Do you wish to confirm the creation of this order?(Y/N)\n");
//                if (confirmation.equalsIgnoreCase("y")) {
//                    AGV newAGV = this.ctrl.saveAGV(agv);
//
//                    if (newAGV != null) {
//                        System.out.print("\nOperation successfully completed!");
//                    } else {
//                        System.out.println("\nOh no! Something went wrong when creating the AGV!");
//                    }
//                } else if (confirmation.equalsIgnoreCase("n")) {
//                    System.out.print("\nOperation successfully canceled!");
//                } else {
//                    System.out.println("Enter Y to confirm, or N to cancel the AGV!");
//                }
//            } while (!confirmation.equalsIgnoreCase("y") || !confirmation.equalsIgnoreCase("n"));
//        } else {
//            System.out.println("There are no free docks in the warehouse!");
//        }
        }
        return false;
    }

    public AGVDocks selectAgvDock(WarehouseInfo warehouseInfo) {
        assert warehouseInfo != null;
        final Iterable<AGVDocks> agvDocks = warehouseInfo.getAVGDocks();
        final SelectWidget<AGVDocks> agvDockSelector = new SelectWidget<>("Agv Docks: ", agvDocks, new AgvDockPrinter());
        agvDockSelector.show();
        return agvDockSelector.selectedElement();
    }


    @Override
    public String headline() {
        return "Configure AGV";
    }
}
