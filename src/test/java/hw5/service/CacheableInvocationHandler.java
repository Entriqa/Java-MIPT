package hw5.service;

import hw5.annotation.Cacheable;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kzlv4natoly
 */
public class CacheableInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<Method, Map<Object, Object>> cache = new HashMap<>();


    public CacheableInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            Map<Object, Object> methodCache = cache.computeIfAbsent(method, k -> new HashMap<>());
            Object arg = args[0]; // Предполагаем, что метод всегда с одним аргументом
            if (methodCache.containsKey(arg)) {
                return methodCache.get(arg);
            } else {
                Object result = method.invoke(target, args);
                methodCache.put(arg, result);
                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }
}
