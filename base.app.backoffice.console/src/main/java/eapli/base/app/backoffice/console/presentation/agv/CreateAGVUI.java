package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.agv.application.CreateAGVController;
import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.DockingPoint;
import eapli.base.app.backoffice.console.presentation.Utils.Utils;
import eapli.base.warehousemanagement.domain.AGVDocks;
import eapli.framework.presentation.console.AbstractUI;

import java.io.FileNotFoundException;
import java.util.List;

public class CreateAGVUI extends AbstractUI {

    private final CreateAGVController ctrl = new CreateAGVController();

    public CreateAGVUI() throws FileNotFoundException {
    }

    @Override
    protected boolean doShow() {

        Integer autonomy;
        do {
            autonomy = Integer.parseInt(Utils.readLineFromConsole("Introduce the AGV autonomy: "));

            if (autonomy == null) {
                System.out.println("Autonomy can't be null!");
            }
        }
        while (autonomy != null);


        Double capacity;
        do {
            capacity = Double.parseDouble(Utils.readLineFromConsole("Introduce the AGV capacity: "));

            if (capacity == null) {
                System.out.println("Capacity can't be null!");
            }
        }
        while (capacity != null);

        Double weight;
        do {
            weight = Double.parseDouble(Utils.readLineFromConsole("Introduce the AGV weight: "));

            if (weight == null) {
                System.out.println("Weight can't be null!");
            }
        }
        while (weight != null);

        Double volume;
        do {
            volume = Double.parseDouble(Utils.readLineFromConsole("Introduce the AGV volume: "));

            if (volume == null) {
                System.out.println("Volume can't be null!");
            }
        }
        while (volume != null);

        String shortDescription;
        do {
            shortDescription = Utils.readLineFromConsole("Introduce the AGV short description: ");

            if (shortDescription.isEmpty() || shortDescription.length() > 30) {
                System.out.println("This field can't be empty and must have a maximum of 30 chars.");
            }
        }
        while (shortDescription.isEmpty() || shortDescription.length() > 30);


        this.ctrl.createAGV(autonomy, capacity, weight, volume, shortDescription);

        List<DockingPoint> lAGVdocks = this.ctrl.getFreeDocks();

        if (this.ctrl.checkAGVDockAvailability(lAGVdocks)){
            System.out.println("The warehouse has free AGV docks!\n");
            DockingPoint agvDock = (DockingPoint) Utils.selectsObject(lAGVdocks);

            this.ctrl.setAGVDock(agvDock);
            System.out.print("\n" +
                    "Resulting AGV class: \n" +
                    this.ctrl.showAGVBuilder());

            String confirmation;
            do {
                confirmation= Utils.readLineFromConsole("Do you wish to confirm the creation of this order?(Y/N)\n");
                if(confirmation.equalsIgnoreCase("y")) {
                    AGV newAGV = this.ctrl.saveAGV();

                    if( newAGV != null) {
                        System.out.print("\nOperation successfully completed!");
                    } else {
                        System.out.println("\nOh no! Something went wrong when creating the AGV!");
                    }
                } else if (confirmation.equalsIgnoreCase("n")) {
                    System.out.print("\nOperation successfully canceled!");
                } else {
                    System.out.println("Enter Y to confirm, or N to cancel the AGV!");
                }
            }while(!confirmation.equalsIgnoreCase("y") || !confirmation.equalsIgnoreCase("n"));
        } else {
            System.out.println("There are no free docks in the warehouse!");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure AGV";
    }
}
