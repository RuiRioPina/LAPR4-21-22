package eapli.base.app.backoffice.console.presentation.productcatalog;

import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.product.domain.Product;
import eapli.base.productcatalog.CheckProductCatalogController;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

public class CheckProductCatalogUI extends AbstractUI {

    private final CheckProductCatalogController theController = new CheckProductCatalogController();

    @Override
    protected boolean doShow(){
        Product selectedProduct=null;
        final Iterable<Product> allProducts=this.theController.allProducts();

    final SelectWidget<Product> selector = new SelectWidget<>("Products:",allProducts,new ProductPrinter());
    do {
        try {
            selector.show();
            selectedProduct=selector.selectedElement();
            System.out.println(selectedProduct.toString());
        }catch (NullPointerException e){
            System.out.println("Exiting...");
        }

    }while (selectedProduct!=null);

        return true;
    }

    @Override
    public String headline() {
        return "Check Product Catalog";
    }
}
