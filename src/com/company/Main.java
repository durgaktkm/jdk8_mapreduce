package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Main {

    public static void main(String[] args) {
	// write your code here

        List<String> wordList = Arrays.asList(
            "abc","abc","bcd","kittu","pavan","kittu","jyothi"
        );

        Map<String, List<String>> collect = wordList.stream().collect(Collectors.groupingBy(identity()));
        System.out.println(collect);
    }
}
