package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        System.out.println("Enter the string you want to add.");
        String input = Console.readLine();

        if (input.isEmpty()) {
            System.out.println("결과 : 0");
            return;
        }

        if (!Character.isDigit(input.charAt(input.length() - 1))) {
            throw new IllegalArgumentException("문자열은 숫자로 끝나야 합니다.");
        }

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

        int result = getResult(input, separators);
        System.out.printf("결과 : %d%n", result);
    }

    private static int getResult(String input, Set<Character> separators) {
        int value = 0;
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            if (i > 0 && separators.contains(input.charAt(i - 1)) && separators.contains(ch)) {
                throw new IllegalArgumentException("구분자가 두 개 연속으로 사용될 수 없습니다.");
            } else if (separators.contains(ch)) {
                result = result + value;
                value = 0;
            } else {
                value = value * 10 + Integer.parseInt(String.valueOf(ch));
            }
        }

        return result + value;
    }
}
