package com.spring.study.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.spring.study.domain.User;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass); // 검증하려는 객체가 User 또는 그 자손인지 확인
    }

    @Override
    // 객체를 검증하는 메서드 - target : 검증할 객체, errors : 검증 시 발생한 에러 저장소
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String id = user.getId();

//        if (id == null || "".equals(id.trim())) {
//            errors.rejectValue("id", "required");
//        }
        // 비어있거나 공백 or 탭인 경우 errors 객체에 필드 이름은 id, 에러코드는 required로 해서 저장.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if (id == null || id.length() < 5 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[] {"5", "2"}, null);
        }
    }
}
