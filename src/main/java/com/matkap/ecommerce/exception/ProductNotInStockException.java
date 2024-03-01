package com.matkap.ecommerce.exception;

public class ProductNotInStockException extends RuntimeException{

    public ProductNotInStockException(Long productItemId) {
        super("The Product with id '" + productItemId + "' is out of stock");
    }
}
