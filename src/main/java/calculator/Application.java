package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
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
                throw new IllegalArgumentException("커스텀 구분자 정의가 잘못되었습니다.");
            }
        }

        int result = getResult(input, separators);
        System.out.printf("결과 : %d%n", result);
    }

    private static int getResult(String input, Set<Character> separators) {
        int value = 0;
        int result = 0;
        boolean wasSeparator = false;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (separators.contains(ch)) {
                if (wasSeparator) {
                    throw new IllegalArgumentException("구분자가 두 개 연속으로 사용될 수 없습니다.");
                }
                result = result + value;
                value = 0;
                wasSeparator = true;
            } else if (ch == '-') {
                throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
            } else if (Character.isDigit(ch)) {
                value = value * 10 + Integer.parseInt(String.valueOf(ch));
                wasSeparator = false;
            } else {
                throw new IllegalArgumentException("문자열은 양수와 구분자로만 구성되어야 합니다.");
            }
        }

        return result + value;
    }
}
