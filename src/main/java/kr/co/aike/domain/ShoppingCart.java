package kr.co.aike.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCart {
	private List<CartItem> items;
	
	// Constructor
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }
    
	// Add an item to the cart or update its quantity if it already exists
    public void addItem(CartItem item) {
        for (CartItem cartItem : items) {
        	Long cartItemPrdNo=cartItem.getPrdNo();
        	Long itemPrdNo=item.getPrdNo();
            if (cartItemPrdNo.equals(itemPrdNo)) {
                // Item already exists in the cart, update its quantity
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // Item doesn't exist in the cart, add it
        items.add(item);
    }

    // Update the quantity of an item in the cart
    public void updateItem(CartItem item) {
        for (CartItem cartItem : items) {
        	Long cartItemPrdNo=cartItem.getPrdNo();
        	Long itemPrdNo=item.getPrdNo();
            if (cartItemPrdNo.equals(itemPrdNo)) {
                // Update the quantity of the matching item
                cartItem.setQuantity(item.getQuantity());
                return;
            }
        }
    }

    // Remove an item from the cart
    public void removeItem(CartItem item) {
        Iterator<CartItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            System.out.println("items"+cartItem.getPrdNo());
        	System.out.println("item"+item.getPrdNo());
        	Long cartItemPrdNo=cartItem.getPrdNo();
        	Long itemPrdNo=item.getPrdNo();
            if (cartItemPrdNo.equals(itemPrdNo)) {
                // Remove the matching item from the cart
                iterator.remove();
                return;
            }
        }
    }

    // Other methods...
}
