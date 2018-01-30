package com.thoughtworks.felix.util;

@FunctionalInterface
public interface FailableSupplier<T> {
    T get() throws Throwable;
}
