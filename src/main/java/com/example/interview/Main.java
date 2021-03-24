package com.example.interview;

import java.util.Comparator;
import java.util.List;

import com.example.interview.model.Country;
import com.example.interview.model.User;
import com.example.interview.parser.ModelFileParser;

public class Main {

  public static void main(String[] args) {

    System.out.println("Users:");
    printSortedUsersByAgeFromFile("src/main/resources/users.txt");

    System.out.println("Countries:");
    printCountriesFromFile("src/main/resources/countries.txt");
  }

  private static void printSortedUsersByAgeFromFile(String fileName) {

    ModelFileParser<User> userModelFileParser = new ModelFileParser<>(User::new,
        User.getFieldSettersFunctions());

    List<User> userList = userModelFileParser.parseModelsFromFile(fileName);

    userList.stream()
        .sorted(Comparator.comparing(User::getAge,
            Comparator.nullsFirst(Comparator.reverseOrder())))
        .forEach(System.out::println);
  }

  private static void printCountriesFromFile(String fileName) {

    ModelFileParser<Country> countryModelFileParser = new ModelFileParser<>(Country::new,
        Country.getFieldSettersFunctions());

    List<Country> countryList = countryModelFileParser.parseModelsFromFile(fileName);

    countryList.forEach(System.out::println);
  }
}
