package client.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

   @Override
   @SuppressWarnings("unchecked")
   public void validate(Object target, Errors errors) {
        boolean hasErrors = errors.hasErrors();

        if (!hasErrors || passThruBeanValidator(hasErrors)) {
            doValidate((T) target, errors);
        }
   }

   protected abstract void doValidate(T form, Errors errors);

   protected boolean passThruBeanValidator(boolean hasErrors) {
        return false;
   }
}
