# SneakyThrows.java
A single-file library to bypass checked exceptions in Java.

## Example
This ability results in more composable and clearer code.

### Replaces 'wrapper' RuntimeExceptions
```java
try {
    input.read();
} catch (IOException e) {
    /*
     * this wrapper object obscures the true cause of the exception.
     *
     * The message will be blank and the stack trace will point
     * here instead of where the IOException was thrown.
     */
    throw new RuntimeException(e);
}

// Instaed
try {
    input.read();
} catch (IOException e) {
    /*
     * Propagates the exception directly, without constructing a wrapper.
     *
     * The stack trace will point where the exception was originally thrown.
     */
    throw SneakyThrows.sneakyThrow(e);
}
```

### Avoid try/catch with `sneakyThrows` lambda
```java
/*
 * The try-catch is verbrose and misleading.
 *
 * It makes it appear we are handling or processing the exeption
 * when we are really unconditonally propagating it.
 */
try {
    inputStream.read();
} catch (IOException e) {
    throw Sneakythrows.sneakyThrow(e);
}

// Same thing as above, but much clearer
byte returnValue = SneakyThrow.sneakyThrows(() -> inputStream.read());
```

## License
This project is dual-licensed under the [Apache 2.0] and [CC0 1.0] licenses.

Importantly, the CC0 allows you to copy this code directy into your project
without needing to give attribution or maintain a copy of the original license.

[Apache 2.0]: https://www.apache.org/licenses/LICENSE-2.0
[CC0 1.0]: https://creativecommons.org/publicdomain/zero/1.0/
