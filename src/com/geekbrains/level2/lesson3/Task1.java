package com.geekbrains.level2.lesson3;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    private static final String[] WORDS = {
            "алмаз", "альбатрос", "альбом", "альманах", "альтернатива", "альфа-лучи",
            "алюминий", "амбразура", "аморальный", "амплитуда", "амплуа", "бинокль", "биография",
            "биология", "бирюзовый", "благодарность", "благоразумный", "благородный",
            "блаженство", "блеснуть", "блестеть", "блистать", "блокнот", "блокпост", "богатство",
            "богатырь", "амбразура", "аморальный", "амплитуда", "амплуа", "бинокль", "биография",
            "биология", "бирюзовый", "биография", "блеснуть", "биография"
    };

    private static Map<String, Integer> wordsMap = new HashMap<>();

    public static void initWordsMap() {
        for(String w: WORDS) {
            if (wordsMap.containsKey(w)) {
                wordsMap.put(w, wordsMap.get(w) + 1);
            } else {
                wordsMap.put(w, 1);
            }
        }
    }

    public static void printUniqWord() {
        for(Map.Entry<String, Integer> entry: wordsMap.entrySet()) {
            if (entry.getValue() == 1)
                System.out.println(entry.getKey());
        }
    }

    public static void printMap() {
        System.out.println(wordsMap);
    }
}
