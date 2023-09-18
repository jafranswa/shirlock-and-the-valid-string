import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        // hacker rank problem link
        // string too long can be used to test this for a string that tests time complexity
        // this string is sotred in a seperate file because its over 100k char and will cause compliation errors if not
        // string too long should pass as a "YES" valid string
        FileInputStream fis = new FileInputStream("src/main/resources/stringTooLong.txt");
        String stringTooLong = IOUtils.toString(fis, "UTF-8");
        String result = solution("aabbccddeefghi");
        System.out.println(result);
    }

    public static String solution(String s) {
        // Write your code here
        // if s is empty no
        if (s.isEmpty()) {
            return "NO";
        }
        // if s is one char yes
        if (s.length() < 2) {
            return "YES";
        }
        Map<Character, Integer> charCountsMap = getCharCounts(s);

        //compare character counts
        int[] charCounts = new int[charCountsMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> count : charCountsMap.entrySet()) {
            charCounts[index++] = count.getValue();
        };
        Arrays.sort(charCounts);

        int first = charCounts[0];
        int second = charCounts[1];
        int secondLast = charCounts[charCounts.length - 2];
        int last = charCounts[charCounts.length - 1];
        // all frequencies are the same
        if (first == last) {
            return "YES";
        }
        // all frequencies are the same except one frequency is 1
        if (first == 1 && second == last) {
            return "YES";
        }
        // all frequencies are the same except one is higher by one
        if (first==second && second == secondLast && secondLast == (last -1)) {
            return "YES";
        }

        return "NO";

    }

    public static Map<Character, Integer> getCharCounts(String s) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char currentChar = s.charAt(i);
            int count = charCounts.getOrDefault(currentChar, 0);
            charCounts.put(currentChar, ++count);
        }
        return charCounts;
    }
}
