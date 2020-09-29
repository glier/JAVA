package com.geekbrains.level2.lesson3;

import java.util.*;

public class PhoneBook {

    private final Map<String, List<String>> phoneBook = new HashMap<>();

    public void add(String name, String msisdn) {
        phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(msisdn);
    }

    public void get(String name) {
        if (phoneBook.containsKey(name)) {
            System.out.println(name + ": " + phoneBook.get(name));
        }
    }
}
