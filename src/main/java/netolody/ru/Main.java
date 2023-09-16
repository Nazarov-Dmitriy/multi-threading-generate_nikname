package netolody.ru;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger beautifulLetter3 = new AtomicInteger(0);
    public static AtomicInteger beautifulLetter4 = new AtomicInteger(0);
    public static AtomicInteger beautifulLetter5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        List<Thread> threads = new ArrayList<>();
        List<Runnable> logics = new ArrayList<>();

        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Runnable logicPalindrome = () -> {
            beautifulNickname("palindrom", texts, 3);
            beautifulNickname("palindrom", texts, 4);
            beautifulNickname("palindrom", texts, 5);
        };

        Runnable logicAscending = () -> {
            beautifulNickname("ascending", texts, 3);
            beautifulNickname("ascending", texts, 4);
            beautifulNickname("ascending", texts, 5);
        };

        Runnable logicOneChar = () -> {
            beautifulNickname("one char", texts, 3);
            beautifulNickname("one char", texts, 4);
            beautifulNickname("one char", texts, 5);
        };

        logics.add(logicPalindrome);
        logics.add(logicAscending);
        logics.add(logicOneChar);

        for (Runnable item : logics) {
            Thread thread = new Thread(item);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Красивых слов с длиной 3: " + beautifulLetter3);
        System.out.println("Красивых слов с длиной 4: " + beautifulLetter4);
        System.out.println("Красивых слов с длиной 5: " + beautifulLetter5);
    }


    public static void beautifulNickname(String method, String[] texts, int length) {

        if (Objects.equals(method, "palindrom")) {
            for (String word : texts) {
                if (word.length() == length && isPalindrome(word)) {
                    switch (length) {
                        case 3 -> beautifulLetter3.incrementAndGet();
                        case 4 -> beautifulLetter4.incrementAndGet();
                        case 5 -> beautifulLetter5.incrementAndGet();
                        default -> {
                        }
                    }
                }
            }
        }

        if (Objects.equals(method, "ascending")) {
            for (String word : texts) {
                if (word.length() == length && isLettersAscending(word)) {
                    switch (length) {
                        case 3 -> beautifulLetter3.incrementAndGet();
                        case 4 -> beautifulLetter4.incrementAndGet();
                        case 5 -> beautifulLetter5.incrementAndGet();
                        default -> {
                        }
                    }
                }
            }
        }

        if (Objects.equals(method, "one char")) {
            for (String word : texts) {
                if (word.length() == length && isOneChar(word)) {

                    switch (length) {
                        case 3 -> beautifulLetter3.incrementAndGet();
                        case 4 -> beautifulLetter4.incrementAndGet();
                        case 5 -> beautifulLetter5.incrementAndGet();
                        default -> {
                        }
                    }
                }
            }
        }
    }

    public static boolean isPalindrome(String text) {
        return text.replaceAll("\\W", "")
                .equalsIgnoreCase(new StringBuilder(text.replaceAll("\\W", ""))
                        .reverse().toString());
    }

    public static boolean isLettersAscending(String text) {
        boolean matches = true;
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < text.charAt(i - 1)) {
                matches = false;
                break;
            }
        }
        return matches;
    }

    public static boolean isOneChar(String text) {
        boolean matches = true;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(0) != text.charAt(i)) {
                matches = false;
                break;
            }
        }
        return matches;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}