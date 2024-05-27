package hw5.service;

import hw5.annotation.validation.NotBlank;
import hw5.annotation.validation.Size;
import hw5.exception.ValidationException;
import org.junit.jupiter.api.Nested;

import java.lang.reflect.Field;

public class ValidatorImpl implements Validator {
    @Override
    public void validate(Object object) throws ValidationException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotBlank.class)) {
                NotBlank notBlank = field.getAnnotation(NotBlank.class);
                try {
                    String value = (String) field.get(object);
                    if (value == null || value.trim().isEmpty()) {
                        throw new ValidationException(notBlank.message());
                    }
                } catch (IllegalAccessException e) {
                    throw new ValidationException("Cannot access field: " + field.getName());
                }
            }
            if (field.isAnnotationPresent(Size.class)) {
                Size size = field.getAnnotation(Size.class);
                try {
                    String value = (String) field.get(object);
                    if (value == null || value.length() < size.min() || value.length() > size.max()) {
                        throw new ValidationException(size.message());
                    }
                } catch (IllegalAccessException e) {
                    throw new ValidationException("Cannot access field: " + field.getName());
                }
            }
        }
    }
}
