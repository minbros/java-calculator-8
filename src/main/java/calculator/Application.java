package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        System.out.println("Enter the string you want to add.");
        String input = Console.readLine();

        int result = 0;
        if (!input.isBlank()) {
            Set<Character> separators = Set.of(',', ':');
            int value = 0;

            for (int i = 0; i < input.length(); i++) {
                Character ch = input.charAt(i);
                if (separators.contains(ch)) {
                    result = result + value;
                    value = 0;
                } else {
                    value = value * 10 + Integer.parseInt(String.valueOf(ch));
                }
            }

            result = result + value;
        }

        System.out.printf("Result: %d%n", result);
    }
}
