package client.validator;

import client.forms.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserFormValidator extends AbstractValidator<UserForm> {
    @Override
    protected void doValidate(UserForm form, Errors errors) {
       if(!form.getPassword().equals(form.getPasswordConfirm())) {
           errors.rejectValue("passwordConfirm",
                   "unmatchPassword",
                   "パスワードと確認用パスワードが一致していません");
       }
    }
}
