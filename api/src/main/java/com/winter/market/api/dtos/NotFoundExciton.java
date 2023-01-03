package com.winter.market.api.dtos;

public class NotFoundExciton extends RuntimeException {
    public NotFoundExciton(String message) {
        super(message);
    }
}
