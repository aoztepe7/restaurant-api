package com.ztp.restaurantapi.handler;

@FunctionalInterface
public interface Handler<T,R> {
    R execute(T request);
}
