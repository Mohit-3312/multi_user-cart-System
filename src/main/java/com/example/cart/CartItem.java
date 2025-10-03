package com.example.cart;

public class CartItem {
    private int cartId;
    private String userId;
    private Product product;

    public CartItem(int cartId, String userId, Product product) {
        this.cartId = cartId;
        this.userId = userId;
        this.product = product;
    }

    public int getCartId() { return cartId; }
    public String getUserId() { return userId; }
    public Product getProduct() { return product; }
}