package com.example.interview.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class Country {
  private String name;
  private Integer population;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPopulation() {
    return population;
  }

  public void setPopulation(Integer population) {
    this.population = population;
  }

  @Override
  public String toString() {
    return "Country{" +
           "name='" + name + '\'' +
           ", population=" + population +
           '}';
  }

  public static List<BiConsumer<Country, String>> getFieldSettersFunctions() {
    return Arrays.asList(
        Country::setName,
        (country, value) -> country.setPopulation(Integer.valueOf(value))
    );
  }
}
