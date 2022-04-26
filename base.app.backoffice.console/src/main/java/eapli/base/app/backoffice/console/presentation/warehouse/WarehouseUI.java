package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.app.backoffice.console.presentation.productCategory.ProductCategoryPrinter;
import eapli.base.product.application.SpecifyNewProductController;
import eapli.base.product.domain.*;
import eapli.base.productCategory.domain.Category;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.FileNotFoundException;

public class WarehouseUI extends AbstractUI {

    private final WarehouseController theController = new WarehouseController();

    @Override
    protected boolean doShow() {

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
            System.out.println(theController.buildWarehousePlant(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Set up Warehouse Plant";
    }
}
