package eapli.base.app.backoffice.console.presentation.productCategory;

import eapli.base.productCategory.application.RegisterNewCategoryController;
import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class RegisterNewCategoryUI extends AbstractUI {

    private final RegisterNewCategoryController theController= new RegisterNewCategoryController();
    @Override
    protected boolean doShow(){
        final AlphaNumericCode alphanumericCode=AlphaNumericCode.valueOf(Console.readLine("Category alphanumeric code:"));
        final Description description=Description.valueOf(Console.readLine("Category description:"));
        final Designation name = Designation.valueOf(Console.readLine("Category name:"));
        this.theController.registerCategory(alphanumericCode,description,name);
        return false;
    }

    @Override
    public String headline() {
        return "Register New Category";
    }
}
