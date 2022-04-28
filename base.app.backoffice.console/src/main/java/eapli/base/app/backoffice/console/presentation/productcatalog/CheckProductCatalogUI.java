package eapli.base.app.backoffice.console.presentation.productcatalog;

import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.product.domain.Product;
import eapli.base.productcatalog.CheckProductCatalogController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

import java.util.*;

public class CheckProductCatalogUI extends AbstractUI {
    final int ALL_ITEMS_MODE = 1;

    final int CATEGORY_MODE = 2;
    final int BRAND_MODE = 3;

    final int DESCRIPTION_MODE = 4;

    final int NO_SORT = 1;
    final int NAME_SORT_MODE = 2;

    final int PRICE_SORT_MODE=3;

    final List<Integer> sortList= new ArrayList<>(List.of(1,2,3));
    private int mode;



    private final CheckProductCatalogController theController = new CheckProductCatalogController();

    public CheckProductCatalogUI(int mode) {
        this.mode = mode;
    }

    @Override
    protected boolean doShow() {
        Scanner sc= new Scanner(System.in);
        int sort_mode=0;
        do {
            System.out.println("Please choose the sorting mode you wish to use:");
            System.out.println("1.No Sorting");
            System.out.println("2.Sort by Product name");
            System.out.println("3.Sort by Price");
        sort_mode=sc.nextInt();
        }while (!sortList.contains(sort_mode));

        if (this.mode == ALL_ITEMS_MODE) {

            Product selectedProduct = null;
            final Iterable<Product> allProducts = this.theController.allProducts();

            List<Product> productList = new ArrayList<>();
            for (Product product : allProducts) {

                    productList.add(product);

            }
            sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", productList, new ProductPrinter());
            do {
                try {
                    selector.show();
                    selectedProduct = selector.selectedElement();
                    System.out.println(selectedProduct.toString());
                } catch (NullPointerException e) {
                    System.out.println("Exiting...");
                }

            } while (selectedProduct != null);
        }
        if (this.mode == CATEGORY_MODE) {
            final String category = Console.readLine("Please type the name of the category you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProducts = this.theController.allProducts();
            List<Product> productList = new ArrayList<>();
            for (Product product : allProducts) {
                if (product.getCategoryString().equals(category)) {
                    productList.add(product);
                }
            }
            sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", productList, new ProductPrinter());
            do {
                try {
                    selector.show();
                    selectedProduct = selector.selectedElement();
                    System.out.println(selectedProduct.toString());
                } catch (NullPointerException e) {
                    System.out.println("Exiting...");
                }

            } while (selectedProduct != null);
        }
        if (this.mode == BRAND_MODE) {
            final String brand = Console.readLine("Please type the name of the brand you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProducts = this.theController.allProducts();
            List<Product> productList = new ArrayList<>();
            for (Product product : allProducts) {
                if (product.getBrandString().equals(brand)) {
                    productList.add(product);
                }
            }
            sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", productList, new ProductPrinter());
            do {
                try {
                    selector.show();
                    selectedProduct = selector.selectedElement();
                    System.out.println(selectedProduct.toString());
                } catch (NullPointerException e) {
                    System.out.println("Exiting...");
                }

            } while (selectedProduct != null);
        }

        if (this.mode == DESCRIPTION_MODE) {
            final String description = Console.readLine("Please type part of the description you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProducts = this.theController.allProducts();
            List<Product> productList = new ArrayList<>();
            for (Product product : allProducts) {
                if (product.getExtendedDescriptionString().contains(description) || product.getShortDescriptionString().contains(description) || product.getTechnicalDescriptionString().contains(description)) {
                    productList.add(product);
                }
            }
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", productList, new ProductPrinter());
            do {
                try {
                    selector.show();
                    selectedProduct = selector.selectedElement();
                    System.out.println(selectedProduct.toString());
                } catch (NullPointerException e) {
                    System.out.println("Exiting...");
                }

            } while (selectedProduct != null);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Check Product Catalog";
    }

    public void sortList(List<Product> productsList,int sort_mode) {
        if (sort_mode == NO_SORT) {
            return;
        }
        if (sort_mode == NAME_SORT_MODE) {

            Collections.sort(productsList, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
        if(sort_mode == PRICE_SORT_MODE){
            Collections.sort(productsList, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
        }
    }
}



