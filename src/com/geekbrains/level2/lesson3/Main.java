package com.geekbrains.level2.lesson3;

public class Main {
    public static void main(String[] args) {
        Task1.initWordsMap();
        Task1.printUniqWord();
        Task1.printMap();

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Petrov", "79998887766");
        phoneBook.add("Sidorov", "79998887765");
        phoneBook.add("Ivanov", "79998887764");
        phoneBook.add("Petrov", "79998887763");

        phoneBook.get("Petrov");
        phoneBook.get("Ivanov");
        phoneBook.get("Sidorov");
    }
}
