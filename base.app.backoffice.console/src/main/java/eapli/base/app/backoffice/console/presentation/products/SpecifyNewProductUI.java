package eapli.base.app.backoffice.console.presentation.products;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class SpecifyNewProductUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        final String alert = Console.readLine("NOT DONE");
        return false;
    }

    @Override
    public String headline() {
        return "Specify New Product";
    }
}
