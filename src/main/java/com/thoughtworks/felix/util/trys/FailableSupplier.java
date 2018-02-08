package com.thoughtworks.felix.util.trys;

@FunctionalInterface
public interface FailableSupplier<T> {
    T get() throws Throwable;
}
