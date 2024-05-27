package hw5.service;

import hw5.exception.ValidationException;

/**
 * @author kzlv4natoly
 */
public interface Validator {
    void validate(Object object) throws ValidationException;
}
