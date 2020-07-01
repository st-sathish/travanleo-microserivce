package com.travanleo.user.data;

import javax.persistence.Column;

/**
 * Immutable object to serialize data to client application
 */
public final class UserData {

    private String firstName;

    private String lastName;

    private Long mobile;

    private Integer age;

    private String email;

    public static UserData newInstance(final String firstName, final String lastName, final Long mobile,
                                       final Integer age, final String email) {
        return new UserData(firstName, lastName, mobile, age, email);
    }

    public static UserData newInstance(final String firstName, final String lastName,
                                       final Integer age) {
        return new UserData(firstName, lastName, age);
    }

    private UserData(final String firstName, final String lastName, final Long mobile,
        final Integer age, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.age = age;
        this.email = email;
    }

    private UserData(final String firstName, final String lastName, final Long mobile,
                     final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.age = age;
    }

    private UserData(final String firstName, final String lastName,
                     final Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    private UserData(final String firstName, final String lastName,
                     final Integer age, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
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
}
