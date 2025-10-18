package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @ParameterizedTest
    @CsvSource({
            "//;\\n1;2, 3",
            "'1,2:3', 6"
    })
    void 계산_테스트(String input, int expectedResult) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains(String.format("결과 : %d", expectedResult));
        });
    }

    @ParameterizedTest
    @CsvSource({
            "//;1, '커스텀 구분자 정의가 잘못되었습니다.'",
            "//0\\n102, '커스텀 구분자 정의가 잘못되었습니다.'",
            "'1,2,a', '문자열은 숫자로 끝나야 합니다.'",
            "'1::2', '구분자가 두 개 연속으로 사용될 수 없습니다.'",
            "'-1,2:3', '음수는 사용할 수 없습니다.'",
            "'1,%,2', '문자열은 양수와 구분자로만 구성되어야 합니다.'",
            "'1,2.1', '소수는 사용할 수 없습니다.'"
    })
    void 예외_테스트(String input, String expectedMessage) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(expectedMessage)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
