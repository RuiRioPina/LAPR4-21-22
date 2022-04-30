package eapli.base.app.backoffice.console.presentation.productcatalog;

import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.product.domain.Brand;
import eapli.base.product.domain.Product;
import eapli.base.productCategory.domain.Category;
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

    final int BRAND_CATEGORY_MODE =5;

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
            final Iterable<Product> allProducts = this.theController.allProducts(sort_mode);

            List<Product> productList = new ArrayList<>();
            for (Product product : allProducts) {

                    productList.add(product);

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
        if (this.mode == CATEGORY_MODE) {
            System.out.println("Available Categories:");
            Iterable<Category> categories=theController.allCategories();
            for (Category category:categories ){
                System.out.println(category.toString());
            }
            final String category = Console.readLine("Please type the name of the category you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProductsWithCategory = this.theController.allProductsWithCategory(category,sort_mode);
            //sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", allProductsWithCategory, new ProductPrinter());
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
            System.out.println("Available Brands:");
            Iterable<Brand> brands = theController.allBrands();
            for (Brand brand:brands){
                System.out.println(brand.toString());
            }
            final String brand = Console.readLine("Please type the name of the brand you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProductsWithBrand = this.theController.allProductsWithBrand(brand,sort_mode);
            //sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", allProductsWithBrand, new ProductPrinter());
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
            final Iterable<Product> allProducts = this.theController.allProducts(sort_mode);
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
        if (this.mode == BRAND_CATEGORY_MODE) {
            System.out.println("Available Brands:");
            Iterable<Brand> brands = theController.allBrands();
            for (Brand brand:brands){
                System.out.println(brand.toString());
            }
            final String brand = Console.readLine("Please type the name of the brand you wish to search:");
            System.out.println("Available Categories:");
            Iterable<Category> categories=theController.allCategories();
            for (Category category:categories ){
                System.out.println(category.toString());
            }
            final String category = Console.readLine("Please type the name of the category you wish to search:");
            Product selectedProduct = null;
            final Iterable<Product> allProductsWithBrandCategory = this.theController.allProductsWithBrandCategory(brand,category,sort_mode);
            //sortList(productList,sort_mode);
            final SelectWidget<Product> selector = new SelectWidget<>("Products:", allProductsWithBrandCategory, new ProductPrinter());
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


}



