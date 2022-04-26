package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.product.application.SpecifyNewProductController;
import eapli.base.product.domain.*;
import eapli.base.productCategory.application.RegisterNewCategoryController;
import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.base.productCategory.domain.Category;
import eapli.base.productCategory.repositories.CategoryRepository;
import eapli.framework.actions.Action;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

public class ProductBootstrapper implements Action {

    private final CategoryRepository repo = PersistenceContext.repositories().categories();

    @Override
    public boolean execute() {
        AlphaNumericCode ac = AlphaNumericCode.valueOf("123");
        Description description = Description.valueOf("Smartphones Category");
        Designation designation = Designation.valueOf("Smartphones");

        registerCategory(ac,description,designation);

        Category c = getCategory();

        registerProduct(c,Designation.valueOf("Radmi II"),new ProductDescription("Smartphone Radmi II",
                "Smarthphone Radmi II - ANDROID - RAM 4GB - 48 MP"," "),
                new Brand("MIAOXI"),new Price(219.0,270.0),new Reference("1111111"),
                new InternalCode("ic0000"),new ProductionCode(""),new Barcode("1122334455667"));

        registerProduct(c,Designation.valueOf("6W Plus"),new ProductDescription("Smarthphone 6W Plus",
                        "Smarthphone 6W Plus - YOZ - RAM 2GB - 48 MP"," "),
                new Brand("PEAR"),new Price(500.0,625.0),new Reference("2222111"),
                new InternalCode("ic0007"),new ProductionCode(""),new Barcode("0022884455667"));

        registerProduct(c,Designation.valueOf("Galatic"),new ProductDescription("Smarthphone ZAMZUNG Galatic",
                        "Smarthphone ZAMZUNG Galatic - ADROID - RAM 3GB - 50 MP"," "),
                new Brand("ZAMZUNG"),new Price(119.0,150.0),new Reference("1111110"),
                new InternalCode("ic0002"),new ProductionCode(""),new Barcode("1188334455667"));
        return true;
    }


    private void registerCategory (AlphaNumericCode alphanumericCode, Description description, Designation name) {
        final RegisterNewCategoryController controller = new RegisterNewCategoryController();
        controller.registerCategory(alphanumericCode, description, name);
    }

    private Category getCategory() {
        return repo.findAll().iterator().next();
    }

    private void registerProduct(Category category, Designation name, ProductDescription description, Brand brand,
                                 Price price, Reference reference, InternalCode internalCode,
                                 ProductionCode productionCode, Barcode barcode) {
        final SpecifyNewProductController controller = new SpecifyNewProductController();
        controller.specifyNewProduct(category,name,description,brand,price,reference,internalCode,productionCode,barcode);
    }

}
