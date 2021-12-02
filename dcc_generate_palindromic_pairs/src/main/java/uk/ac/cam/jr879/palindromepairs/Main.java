/*
 * Copyright 2021 David Berry <dgb37@cam.ac.uk>, Joseph Rance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.cam.jr879.palindromepairs;

import java.net.JarURLConnection;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    System.out.println(findIndices(List.of("race", "car", "ra")));
    // race car [0, 1]
    // ra car [1, 2]
    System.out.println(findIndices(List.of("","a","b","c","aa","ab","ac","ba","bb","bc","ca","cb","cc")));
  }

  private static List<IndexPair> findIndices(List<String> strings) {
    // O(n^2m) algorithm is to check each pair of strings
    // and test each character for being a palindrome
    //
    // O(nm^2) algorithm is to take a hashmap of all the
    // words (keys) and their indices (values) in the list. Then
    // for each word in the list (n), generate all possible endings
    // (m^2) and add the pairs where the ending exists in the
    // hashmap to the output list
    //
    // All possile endings can be constructed from the string
    // "abcdcecd" using the following process:
    //
    // start with "". This is a palindrome, so the string "dcecdcba"
    // would create a palindrome after concatenation
    // Add the last character to our empty string to get "d". This
    // is also a palindrome so "cecdcba" also results in a palindrome
    // after concatenation
    // Add the second last character to get "dc". This is not a palindrome
    // so move on
    // Add next char to get "dce". Move on
    // "dcec" -> move on
    // "dcecd" -> palindrome so "cba" results in a palindrome when concatenated
    // and so on...
    // This also needs to be done from the other direction, to account for
    // cases such as ["car", "ra"]

    // I will implement the second algorithm because it is more interesting

    List<IndexPair> output = findIndicesOneDir(strings, false);
    output.addAll(findIndicesOneDir(reverseEach(strings), true));
    // reversed string is for case ["car", "ra"]

    return new ArrayList<IndexPair>(new HashSet<IndexPair>(output));
  }

  private static List<String> reverseEach(List<String> strings) {
    return strings.stream().map(Main::reverse).collect(Collectors.toList());
  }

  private static String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  private static List<IndexPair> findIndicesOneDir(List<String> strings, boolean rev) {

    List<IndexPair> output = new ArrayList<IndexPair>();

    HashMap<String, Integer> map = new HashMap<String, Integer>();
    for (int i = 0; i < strings.size(); i++) {
      map.put(strings.get(i), i);
    }

    for (int i = 0; i < strings.size(); i++) {

      String start = strings.get(i);
      String end = "";

      while (true) { // I've done this because I want to exit in the middle


        if (isPalindrome(end)
                && map.containsKey(reverse(start))
                && map.get(reverse(start)) != i) {
          if (rev) {
            output.add(new IndexPair(map.get(reverse(start)), i));;
          } else {
            output.add(new IndexPair(i, map.get(reverse(start))));
          }
        }

        if (Objects.equals(start, "")) {
          break;
        }

        end += start.substring(start.length() - 1);
        start = start.substring(0, start.length() - 1);
      }
    }

    return output;
  }

  private static boolean isPalindrome(String s) {
    return Objects.equals(s, reverse(s));
  }
}
