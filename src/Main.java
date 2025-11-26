import objects.Product;
import interfaces.DiscountCalculator;
import objects.DiscountService;

import java.util.List;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        DiscountCalculator discountXPrice =
                p -> p.price() > 100 ? p.price() * 0.9 : p.price();

        DiscountCalculator discountXCategory =
                p -> p.category().equalsIgnoreCase("technology") ? p.price() * 0.95 : p.price();
        
        DiscountService service = new DiscountService(List.of(
                discountXPrice,
                discountXCategory
        ));

        List<Product> products = List.of(
                new Product("Keyboard", 120, "technology"),
                new Product("Shirt", 25, "ropa"),
                new Product("Mouse", 60, "technology"),
                new Product("Table", 150, "furniture")
        );

        System.out.println("=== Product Admin ===");
        System.out.println("Products with price > 50€:");
        products.stream()
                .filter(p -> p.price() > 50)
                .forEach(p -> System.out.println(p.basicFormat()));

        System.out.println("\n=== Products with upper case names ===");
        products.stream()
                .map(p -> "- " + p.name().toUpperCase())
                .forEach(System.out::println);

        System.out.println("\n=== Products ordered by descending price ===");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::price).reversed())
                .forEach(p -> System.out.println(p.basicFormat()));

        System.out.println("\n=== Final prices with applied discounts ===");
        List<Product> discountedProducts = service.applyDiscounts(products);
        for (int i = 0; i < products.size(); i++) {
            Product original = products.get(i);
            Product wDiscount = discountedProducts.get(i);
            System.out.printf("%s → %.2f€%n",
                    original.formatWCategory(),
                    wDiscount.price());
        }
    }
}