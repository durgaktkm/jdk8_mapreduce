package com.company;

import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ramdurga on 3/28/15.
 */
public class Example {
    public static void main(String[] args) {
        Map<String, Integer> m1 = ImmutableMap.of("a", 4, "b", 3);
        Map<String, Integer> m2 = ImmutableMap.of("a", 3, "c", 4);

        Map<String, Integer> mx = Stream.of(m1, m2)
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                        // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                Integer::max         // such that if a value already exist for
                                // a given key, the max of the old
                                // and new value is taken
                        )
                )
                ;

    /* Use the following if you want to create the map with parallel streams
    Map<String, Integer> mx = Stream.of(m1, m2)
        .parallel()
        .map(Map::entrySet)          // converts each map into an entry set
        .flatMap(Collection::stream) // converts each set into an entry stream, then
                                     // "concatenates" it in place of the original set
        .collect(
            Collectors.toConcurrentMap(        // collects into a map
                Map.Entry::getKey,   // where each entry is based
                Map.Entry::getValue, // on the entries in the stream
                Integer::max         // such that if a value already exist for
                                     // a given key, the max of the old
                                     // and new value is taken
            )
        )
    ;
    */
        System.out.println(mx);

//        Map<String, Integer> expected = ImmutableMap.of("a", 3, "b", 3, "c", 4);
//        assertEquals(expected, mx);
    }
}
