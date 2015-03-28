package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.invoke.MethodHandles.identity;

/**
 * Created by ramdurga on 3/27/15.
 */
public class HashMapReduce {
    public static void main(String[] args) {
      List<Person> personList = Arrays.asList(
          new Person("Kittu",20.2),new Person("Kittu",10.2),new Person("Pavan",100.50)
      )  ;
        Map<String, Double> collect = personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getValue)));
        
        System.out.println(collect);

    }
}
class Person{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    String name;
    Double value;
    Person(String name,Double value){

        this.name = name;
        this.value = value;
    }
}
