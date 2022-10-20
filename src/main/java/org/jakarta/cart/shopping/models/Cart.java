package org.jakarta.cart.shopping.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        if (items.contains(item)) {
            Optional<CartItem> cartItem = items.stream().filter(i -> i.equals(item)).findAny();
            if (cartItem.isPresent()) {
                CartItem newItem = cartItem.get();
                newItem.setQuantity(newItem.getQuantity() + 1);
            }
        } else {
            this.items.add(item);
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt(CartItem::getTotalPrice).sum();
    }

}
