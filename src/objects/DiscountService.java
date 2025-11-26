package objects;

import interfaces.DiscountCalculator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DiscountService {

    private final List<DiscountCalculator> strategies;

    public DiscountService(List<DiscountCalculator> strategies) {
        this.strategies = List.copyOf(strategies); 
    }

    public double calculateFinalPrice(Product product) {
        double finalPrice = product.price();
        for (DiscountCalculator strategy : strategies) {
            finalPrice = strategy.apply(new Product(product.name(), finalPrice, product.category()));
        }
        return finalPrice;
    }

    public List<Product> applyDiscounts(List<Product> products) {
        return products.stream()
                .map(p -> new Product(p.name(), calculateFinalPrice(p), p.category()))
                .collect(Collectors.toUnmodifiableList());
    }
}

