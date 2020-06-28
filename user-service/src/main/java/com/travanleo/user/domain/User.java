package com.travanleo.user.domain;

import com.travanleo.core.domain.AbstractPersistableCustom;
import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.api.UserApiConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity(name = "User")
@Table(name = "user")
public class User extends AbstractPersistableCustom {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mobile", nullable = false)
    private Long mobile;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "email", nullable = false)
    private String email;

    public User() {
        // required
    }

    public User(final String firstName, final String lastName, final Long mobile,
                final Integer age, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void updateLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void updateAge(final Integer age) {
        this.age = age;
    }

    public void updateMobile(final Long mobile) {
        this.mobile = mobile;
    }

    public void updateEmail(final String email) {
        this.email = email;
    }

    public Map<String, Object> update(final JsonCommand command) {
        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);
        if (command.isChangeInStringParameterNamed(UserApiConstants.FIRST_NAME, this.firstName)) {
            final String newValue = command.stringValueOfParameterNamed(UserApiConstants.FIRST_NAME);
            actualChanges.put(UserApiConstants.FIRST_NAME, newValue);
        }
        if (command.isChangeInStringParameterNamed(UserApiConstants.LAST_NAME, this.lastName)) {
            final String newValue = command.stringValueOfParameterNamed(UserApiConstants.LAST_NAME);
            actualChanges.put(UserApiConstants.LAST_NAME, newValue);
        }
        if (command.isChangeInStringParameterNamed(UserApiConstants.email, this.email)) {
            final String newValue = command.stringValueOfParameterNamed(UserApiConstants.email);
            actualChanges.put(UserApiConstants.email, newValue);
        }
        if (command.isChangeInLongParameterNamed(UserApiConstants.MOBILE, this.mobile)) {
            final Long newValue = command.longValueOfParameterNamed(UserApiConstants.MOBILE);
            actualChanges.put(UserApiConstants.MOBILE, newValue);
        }
        if (command.isChangeInStringParameterNamed(UserApiConstants.email, this.email)) {
            final String newValue = command.stringValueOfParameterNamed(UserApiConstants.email);
            actualChanges.put(UserApiConstants.email, newValue);
        }
        return actualChanges;
    }
}
