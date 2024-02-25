// SPDX-License-Identifier: Apache-2.0 OR CC0-1.0
package net.techcable.utils.sneakythrows;

import java.util.Objects;

/**
 * A simple java class to bypass checked exception declarations.
 * <p>
 * Similar to lombok <a href="https://projectlombok.org/features/SneakyThrows">@SneakyThrows</a>,
 * but implemented in pure Java.
 * It supports Java 8 or any later version.
 * </p>
 * <p>
 * It is dual-licensed under the Apache-2.0 and the CC0 licenses.
 * The CC0 license doesn't require attribution,
 * so you can copy this file into your code without listing the license.
 * </p>
 * <p>
 * Version: 0.1.0 (Feb. 25, 2024)
 * Source code: <a href="https://github.com/Techcable/sneakythrows.java">Github</a>
 * </p>
 */
public final class SneakyThrows {
    private SneakyThrows() {}

    /**
     * Invoke the specified function,
     * returning the result and statically ignoring checked exceptions.
     * <p>
     * This can be used to avoid the verbosity of a try-catch statement,
     * ignoring checked exceptions in a single line.
     * </p>
     *
     * @param func the function to invoke
     * @return the result
     * @param <T> the type that is returned
     */
    public static <T> T sneakyThrows(ThrowingSupplier<T> func) {
        try {
            return func.get();
        } catch (Throwable t) {
            throw sneakyThrow(t);
        }
    }

    /**
     * Throw the specified checked exception without actually declaring it.
     * <p>
     * This function never actually returns.
     * To make the compiler understand this,
     * the static return type is {@code AssertionError}.
     * </p>
     *
     *
     * @param t the exception to throw
     * @return never actually returns
     */
    public static AssertionError sneakyThrow(Throwable t) {
        Objects.requireNonNull(t, "Can't throw null");
        //noinspection RedundantTypeArguments
        throw SneakyThrows.<RuntimeException>sneakyThrowInner(t);
    }

    // the actual implementation method (uses generics)
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> AssertionError sneakyThrowInner(Throwable t) throws T {
        throw (T) t;
    }

    /**
     * A modified version of {@link java.util.function.Supplier},
     * which throws an exception.
     * <p>
     * The exception type is fixed as a {@link Throwable} to aid type inference.
     * </p>
     *
     * @param <T> the type that is returned
     */
    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        /**
         * Invoke the function, returning a result.
         *
         * @return the result
         * @throws Throwable any sort of exception that occurs
         */
        T get() throws Throwable;
    }
}
