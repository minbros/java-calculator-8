package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        System.out.println("Enter the string you want to add.");
        String input = Console.readLine();

        int result = 0;
        if (!input.isBlank()) {
            Set<Character> separators = new HashSet<>(Set.of(',', ':'));
            if (input.startsWith("//")) {
                char separator = input.charAt(2);
                if (input.startsWith("\\n", 3) && !Character.isDigit(separator)) {
                    separators.add(separator);
                    input = input.substring(5);
                } else {
                    throw new IllegalArgumentException("커스텀 구분자 정의가 잘못됨");
                }
            }

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

        System.out.printf("결과 : %d%n", result);
    }
}
