package com.example.interview.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class User {
  private String firstName;
  private String lastName;
  private String middleName;
  private Integer age;
  private String passport;

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

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  @Override
  public String toString() {
    return "User{" +
           "firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", middleName='" + middleName + '\'' +
           ", age=" + age +
           ", passport='" + passport + '\'' +
           '}';
  }

  public static List<BiConsumer<User, String>> getFieldSettersFunctions() {
    return Arrays.asList(
        User::setLastName,
        User::setFirstName,
        User::setMiddleName,
        (user, value) -> {
          if (value.isEmpty()) {
            user.setAge(null);
          }
          else {
            user.setAge(Integer.valueOf(value));
          }
        },
        User::setPassport
    );
  }
}
