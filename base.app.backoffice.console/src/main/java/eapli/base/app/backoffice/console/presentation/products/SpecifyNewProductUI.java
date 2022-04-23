package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.app.backoffice.console.presentation.productCategory.ProductCategoryPrinter;
import eapli.base.product.application.SpecifyNewProductController;
import eapli.base.product.domain.*;
import eapli.base.productCategory.domain.Category;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class SpecifyNewProductUI extends AbstractUI {

    private final SpecifyNewProductController theController = new SpecifyNewProductController();

    @Override
    protected boolean doShow() {

        final Iterable<Category> productCategories = this.theController.categories();

        final SelectWidget<Category> selector = new SelectWidget<>("Product Categories:", productCategories,
                new ProductCategoryPrinter());
        selector.show();
        final Category theProductCategory = selector.selectedElement();

        String name;
        do {
            name = Console.readLine("Name");
            if (name.isEmpty()) {
                System.out.println("This field can't be empty.");
            }
        } while (name.isEmpty());

        String shortDescription;
        do {
            shortDescription = Console.readLine("Short Description");
            if (shortDescription.isEmpty() || shortDescription.length() > 30) {
                System.out.println("This field can't be empty and must have a maximum of 30 chars.");
            }
        } while (shortDescription.isEmpty() || shortDescription.length() > 30);

        String extendedDescription;
        do {
            extendedDescription = Console.readLine("Extended Description");
            if (extendedDescription.length() < 20 || extendedDescription.length() > 100){
                System.out.println("This field must have a minimum of 20 chars and a maximum of 100 chars.");
            }
        } while (extendedDescription.length() < 20 || extendedDescription.length() > 100);

        String techDescription = Console.readLine("Technical Description");

        String brand;
        do {
            brand = Console.readLine("Brand");
            if(brand.isEmpty()){
                System.out.println("This field can't be empty.");
            }
            if(brand.length() > 50) {
                System.out.println("Brand name must have a maximum of 50 chars.");
            }
        } while (brand.isEmpty() || brand.length() > 50);

        Double priceWoTaxes = Console.readDouble("Price without Taxes");
        Double priceWiTaxes = Console.readDouble("Price with Taxes");

        String reference;
        do {
            reference = Console.readLine("Reference");
            if (!reference.matches("^[a-zA-Z0-9]*$") && !reference.isEmpty()){
                System.out.println("Reference must be alphanumeric.");
            }
            if (reference.length() > 23) {
                System.out.println("Reference must have a maximum of 23 chars.");
            }
        } while (!reference.matches("^[a-zA-Z0-9]*$") && !reference.isEmpty() || reference.length() > 23);

        String internalCode;
        do {
            internalCode = Console.readLine("Internal Code");
            if(internalCode.isEmpty()){
                System.out.println("This field can't be empty.");
            }
            if (!internalCode.matches("^[a-zA-Z0-9]*$")){
                System.out.println("Internal Code must be alphanumeric.");
            }
            if (internalCode.length() > 23) {
                System.out.println("Internal Code must have a maximum of 23 chars.");
            }
        } while (internalCode.isEmpty() || !internalCode.matches("^[a-zA-Z0-9]*$") || internalCode.length() > 23);

        String productionCode;
        do {
            productionCode = Console.readLine("Production Code");
            if (!productionCode.matches("^[a-zA-Z0-9]*$") && !productionCode.isEmpty()){
                System.out.println("Production Code must be alphanumeric.");
            }
            if (productionCode.length() > 23) {
                System.out.println("Production Code must have a maximum of 23 chars.");
            }
        }  while (!productionCode.matches("^[a-zA-Z0-9]*$") && !productionCode.isEmpty() || productionCode.length()>23);

        String barcode;
        do {
            barcode = Console.readLine("Barcode");
            if (barcode.isEmpty()) {
                System.out.println("This field can´t be empty.");
            }
        } while (barcode.isEmpty());

        theController.specifyNewProduct(theProductCategory,Designation.valueOf(name),new ProductDescription(shortDescription,
                extendedDescription, techDescription), new Brand(brand),new Price(priceWoTaxes, priceWiTaxes),
                new Reference(reference), new InternalCode(internalCode), new ProductionCode(productionCode),
                new Barcode(barcode));

        return true;
    }

    @Override
    public String headline() {
        return "Specify New Product";
    }
}
