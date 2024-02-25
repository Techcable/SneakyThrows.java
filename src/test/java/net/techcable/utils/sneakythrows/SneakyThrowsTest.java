package net.techcable.utils.sneakythrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Supplier;

public class SneakyThrowsTest {
    @Test
    public void testSneakyThrow() {
        Assertions.assertThrowsExactly(
                IOException.class,
                () -> verifyUnchecked(() -> SneakyThrows.sneakyThrow(new IOException("foo")))
        );
        Assertions.assertThrowsExactly(
                IOException.class,
                () -> verifyUnchecked(() -> SneakyThrows.sneakyThrow(new IOException("foo")))
        );
    }

    @SuppressWarnings("UnusedReturnValue")
    private static <R> R verifyUnchecked(Supplier<R> func) {
        return func.get();
    }
}
