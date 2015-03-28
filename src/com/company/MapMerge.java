package com.company;


import com.google.common.collect.ImmutableMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ramdurga on 3/28/15.
 */
public class MapMerge {
    public static void main(String[] args) {
        Map<String,Double> map1 = new HashMap<>();
        map1.put("Kittu", 2.0);
        map1.put("Jyothi", 1.0);
        map1.put("Pavan", 3.0);
        map1.put("Mom", 40.0);
        map1.put("Dad", 15.0);
        map1.put("Ram", Double.NaN);

        Map<String,Double> map2 = new HashMap<>();
        map2.put("Kittu", 4.0);
        map2.put("Jyothi", 7.0);
        map2.put("Pavan", 30.0);
        map2.put("Mom", 4.0);
        map2.put("Dad", 5.0);
        map1.put("Ram", 1.5);


        List<Map<String,Double>> mapList = new ArrayList<>();
        mapList.add(ImmutableMap.copyOf(map1));
        mapList.add(ImmutableMap.copyOf(map2));

        Map<String, Double> mx = Stream.of(ImmutableMap.copyOf(map2), ImmutableMap.copyOf(map1))
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                        // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                Double::max         // such that if a value already exist for

                                // a given key, the max of the old
                                // and new value is taken
                        )
                );

        System.out.println(mx);
        Predicate<Map.Entry<String,Double>> nanPredicate = (d -> !d.getValue().isNaN());

        Map<String, Double> filterdValues = Stream.of(ImmutableMap.copyOf(map2),ImmutableMap.copyOf(map1))
                .map(Map::entrySet)

                .flatMap(Collection::stream)
                .filter(nanPredicate)

                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                Double::max


                        )
                );

        System.out.println(filterdValues);


        Map<String, Long> collect = Stream.of(ImmutableMap.copyOf(map2), ImmutableMap.copyOf(map1))
                .map(Map::entrySet)

                .flatMap(Collection::stream)
                .filter(nanPredicate)


                .collect(Collectors.groupingBy(e -> e.getKey(), Collectors.counting()));

        System.out.println(collect);

        Map<String, Double> sums = Stream.of(ImmutableMap.copyOf(map2),ImmutableMap.copyOf(map1))
                .map(Map::entrySet)

                .flatMap(Collection::stream)
                .filter(nanPredicate)

                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.summingDouble(Map.Entry::getValue)


                        )
                );

        System.out.println(sums);

        Map<String, Double> averages = Stream.of(ImmutableMap.copyOf(map2),ImmutableMap.copyOf(map1))
                .map(Map::entrySet)

                .flatMap(Collection::stream)
                .filter(nanPredicate)

                .collect(
                        Collectors.groupingBy(m ->
                                m.getKey()+"_average",
                                Collectors.averagingDouble(Map.Entry::getValue)


                        )
                );

        System.out.println(averages);
//        Map<String, Double> mx2 = new HashMap<>(map1);
//        map2.forEach((k, v) -> mx2.merge(k, v, Double::max));
//        System.out.println(mx2);

//        Map<String, Double> collect = Stream.of(map1, map2)
//                .map(Map::entrySet)          // converts each map into an entry set
//                .flatMap(Collection::stream).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//
//        System.out.println(collect);
//        System.out.println(Stream.of(map1, map2).count());
//        Stream.of(map1, map2).map(Map::entrySet).flatMap(Collection::stream).forEach(System.out::println);

    }
}
