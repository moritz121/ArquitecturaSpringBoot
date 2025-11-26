package objects;

import java.util.Objects;

public record Product(String name, double price, String category) {

    public Product {
        Objects.requireNonNull(name, "INVALID. Name is null");
        Objects.requireNonNull(category, "INVALID. Category is null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("INVALID. Name is empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("INVALID. Price must be more than 0");
        }
    }

    // Print methods
    public String basicFormat() {
        return "- " + name + ": " + String.format("%.2f€", price);
    }

    public String formatWCategory() {
        return name + " (" + category + "): " + String.format("%.2f€", price);
    }

}