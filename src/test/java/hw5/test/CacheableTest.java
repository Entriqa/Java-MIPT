package hw5.test;

import java.util.HashSet;
import java.util.Set;


import hw5.annotation.Cacheable;
import hw5.service.CacheUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author kzlv4natoly
 */
public class CacheableTest {

    public interface StringTransformation {
        @Cacheable
        String transform(String s);
    }

    @Test
    public void justWorks() {
        StringTransformation singleArgumentCallTransformation = new StringTransformation() {
            private final Set<String> seenArgs = new HashSet<>();

            @Override
            public String transform(String s) {
                if (seenArgs.contains(s)) {
                    throw new IllegalStateException("Second method call with the same argument!");
                }
                seenArgs.add(s);
                return s.toUpperCase();
            }
        };

        StringTransformation cachedProxy =
                CacheUtils.getCacheProxy(StringTransformation.class, singleArgumentCallTransformation);
        String firstCallResult = Assertions.assertDoesNotThrow(() -> cachedProxy.transform("abc"));
        String secondCallResult = Assertions.assertDoesNotThrow(() -> cachedProxy.transform("abc"));
        Assertions.assertEquals(firstCallResult, secondCallResult);
    }
}
