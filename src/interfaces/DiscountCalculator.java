package interfaces;

import objects.Product;

@FunctionalInterface
public interface DiscountCalculator {
    double apply(Product product);
}
