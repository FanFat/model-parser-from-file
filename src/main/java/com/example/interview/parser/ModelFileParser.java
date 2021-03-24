package com.example.interview.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModelFileParser<T> {

  private Supplier<T> supplier;
  private List<BiConsumer<T, String>> modelSetterFunctions;

  public ModelFileParser(Supplier<T> supplier,
      List<BiConsumer<T, String>> modelSetterFunctions) {
    this.supplier = supplier;
    this.modelSetterFunctions = modelSetterFunctions;
  }

  public List<T> parseModelsFromFile(String fileName) {

    List<String> lines = readFile(fileName);

    List<T> modelList = new ArrayList<>();

    int functionsSize = modelSetterFunctions.size();

    T newModel = createModel();
    for (int i = 0; i < lines.size(); i++) {
      modelSetterFunctions.get(i % functionsSize).accept(newModel, lines.get(i));

      if (i % functionsSize == functionsSize - 1) {
        modelList.add(newModel);
        newModel = createModel();
      }
    }

    return modelList;
  }

  private List<String> readFile(String fileName) {
    Path path = Paths.get(fileName);

    List<String> lines;
    try {
      lines = Files.readAllLines(path);
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Not found file: " + fileName);
    }

    return lines;
  }

  private T createModel() {
    return supplier.get();
  }
}
