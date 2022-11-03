package org.jakarta.cart.shopping.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@SessionScoped
@Named
public class Cart implements Serializable {

    private List<CartItem> items;

    @Inject
    private transient Logger log;

//    public Cart() {}

    @PostConstruct
    public void init() {
        log.info("Creating new empty cart.");
        this.items = new ArrayList<>();
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying cart.");
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
