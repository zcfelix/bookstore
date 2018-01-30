package com.thoughtworks.felix.util;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Try<T> {
    private Try() {
    }

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract T get() throws GetOfFailureException;

    public abstract T checkedGet() throws Exception;

    public abstract void forEach(Consumer<? super T> action);

    public abstract <U> Try<U> map(Function<? super T, ? extends U> mapper);

    public abstract <U> Try<U> flatMap(Function<? super T, ? extends Try<U>> mapper);

    public abstract Try<T> filter(Predicate<? super T> predicate);

    public abstract <U> Try<U> recover(Function<? super Exception, ? extends U> recoverFunc);

    public abstract <U> Try<U> recoverWith(Function<? super Exception, ? extends Try<U>> recoverFunc);

    public abstract Try<Exception> failed();

    public abstract Optional<T> toOptional();

    public abstract T getOrElse(T defaultValue);

    public abstract Try<T> orElse(Try<T> defaultValue);

    public abstract <U> Try<U> transform(Function<? super T, ? extends Try<U>> successFunc,
                                         Function<Exception, ? extends Try<U>> failureFunc);

    public static <T extends AutoCloseable, R> Function<T, Try<R>> apply(Function<T, R> consumer) {
        return closeable -> Try.apply(() -> {
            try (T in = closeable) {
                return consumer.apply(in);
            }
        });
    }

    public static <T> Try<T> apply(FailableSupplier<T> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable e) {
            if (e instanceof Exception) return new Failure<>((Exception) e);
            else throw ((Error) e);
        }
    }

    public static <T> Try<T> join(Try<Try<T>> t) {
        if (t == null) return new Failure<>(new NullPointerException("t is null"));
        else if (t instanceof Failure<?>) return ((Try<T>) t);
        else return t.get();
    }

    public static final class Success<T> extends Try<T> {
        private final T value;

        public Success(T value) {
            this.value = value;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public T get() throws GetOfFailureException {
            return value;
        }

        @Override
        public T checkedGet() throws Exception {
            return get();
        }

        @Override
        public void forEach(Consumer<? super T> action) {
            action.accept(value);
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
            return Try.apply(() -> mapper.apply(value));
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, ? extends Try<U>> mapper) {
            return Try.join(Try.apply(() -> mapper.apply(value)));
        }

        @Override
        public Try<T> filter(Predicate<? super T> predicate) {
            return Try.join(Try.apply(() -> {
                if (predicate.test(value)) {
                    return this;
                } else {
                    return new Failure<>(new NoSuchElementException("Predicate does not hold for " + value));
                }
            }));
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> recover(Function<? super Exception, ? extends U> recoverFunc) {
            return (Try<U>) this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> recoverWith(Function<? super Exception, ? extends Try<U>> recoverFunc) {
            return (Try<U>) this;
        }

        @Override
        public Try<Exception> failed() {
            return new Failure<>(new UnsupportedOperationException("Success.failed"));
        }

        @Override
        public Optional<T> toOptional() {
            return Optional.ofNullable(value);
        }

        @Override
        public T getOrElse(T defaultValue) {
            return value;
        }

        @Override
        public Try<T> orElse(Try<T> defaultValue) {
            return this;
        }

        @Override
        public <U> Try<U> transform(Function<? super T, ? extends Try<U>> successFunc, Function<Exception, ? extends Try<U>> failureFunc) {
            return Try.join(Try.apply(() -> successFunc.apply(value)));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Success<?> success = (Success<?>) o;
            return Objects.equals(value, success.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Success{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class Failure<T> extends Try<T> {
        private final Exception exception;
        private final GetOfFailureException unCheckedException;

        public Failure(Exception exception) {
            this.exception = exception;
            this.unCheckedException = new GetOfFailureException(exception);
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public T get() throws GetOfFailureException {
            throw unCheckedException;
        }

        @Override
        public T checkedGet() throws Exception {
            throw exception;
        }

        @Override
        public void forEach(Consumer<? super T> action) {

        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
            return (Try<U>) this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Try<U> flatMap(Function<? super T, ? extends Try<U>> mapper) {
            return (Try<U>) this;
        }

        @Override
        public Try<T> filter(Predicate<? super T> predicate) {
            return this;
        }

        @Override
        public <U> Try<U> recover(Function<? super Exception, ? extends U> recoverFunc) {
            return Try.apply(() -> recoverFunc.apply(exception));
        }

        @Override
        public <U> Try<U> recoverWith(Function<? super Exception, ? extends Try<U>> recoverFunc) {
            return Try.join(Try.apply(() -> recoverFunc.apply(exception)));
        }

        @Override
        public Try<Exception> failed() {
            return new Success<>(exception);
        }

        @Override
        public Optional<T> toOptional() {
            return Optional.empty();
        }

        @Override
        public T getOrElse(T defaultValue) {
            return defaultValue;
        }

        @Override
        public Try<T> orElse(Try<T> defaultValue) {
            return defaultValue;
        }

        @Override
        public <U> Try<U> transform(Function<? super T, ? extends Try<U>> successFunc, Function<Exception, ? extends Try<U>> failureFunc) {
            return Try.join(Try.apply(() -> failureFunc.apply(exception)));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Failure<?> failure = (Failure<?>) o;
            return Objects.equals(exception, failure.exception);
        }

        @Override
        public int hashCode() {
            return Objects.hash(exception);
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "exception=" + exception +
                    ", unCheckedException=" + unCheckedException +
                    '}';
        }
    }
}
