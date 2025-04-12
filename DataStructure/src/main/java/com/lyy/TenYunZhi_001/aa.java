package com.lyy.TenYunZhi_001;

import java.util.*;

public class aa {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        lists.forEach(System.out::println);

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> group = map.getOrDefault(key, new LinkedList<String>());
            group.add(str);
            map.put(key, group);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
