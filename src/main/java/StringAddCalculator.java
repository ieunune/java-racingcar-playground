import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringAddCalculator {

    /// Fields

    /// Constructors

    /// Methods
    public static int splitAndSum(String data) {
        int result = 0;

        // 1. 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.(예 : “” => 0, null => 0)
        if (data == null || data.isBlank()) {
            return result;
        }

        // 2. 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.(예 : “1”)
        if (data.length() == 1) {
           return Integer.parseInt(data);
        }

        // 3. 숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.(예 : “1,2”)
        String[] tokens = data.split(",|:");

        // 4. 구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다. (예 : “1,2:3” => 6)
        // tokens = data.split(",|:");

        // 5. “//”와 “\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. (예 : “//;\n1;2;3” => 6)
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(data);
        if (m.find()) {
            String customDelimiter = m.group(1);
            tokens= m.group(2).split(customDelimiter);
        }

        // 덧셈 구현
        result = getSum(tokens);

        return result;
    }

    private static int getSum(String[] tokens) {
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).peek(num -> {
            if (num < 0) {
                throw new RuntimeException("음수는 처리할 수 없습니다.");

            }
        }).sum();
    }
}
