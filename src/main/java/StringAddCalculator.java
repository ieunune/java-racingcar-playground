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

        // 1. �� ���ڿ� �Ǵ� null ���� �Է��� ��� 0�� ��ȯ�ؾ� �Ѵ�.(�� : ���� => 0, null => 0)
        if (data == null || data.isBlank()) {
            return result;
        }

        // 2. ���� �ϳ��� ���ڿ��� �Է��� ��� �ش� ���ڸ� ��ȯ�Ѵ�.(�� : ��1��)
        if (data.length() == 1) {
           return Integer.parseInt(data);
        }

        // 3. ���� �ΰ��� �ĸ�(,) �����ڷ� �Է��� ��� �� ������ ���� ��ȯ�Ѵ�.(�� : ��1,2��)
        String[] tokens = data.split(",|:");

        // 4. �����ڸ� �ĸ�(,) �̿ܿ� �ݷ�(:)�� ����� �� �ִ�. (�� : ��1,2:3�� => 6)
        // tokens = data.split(",|:");

        // 5. ��//���� ��\n�� ���� ���̿� Ŀ���� �����ڸ� ������ �� �ִ�. (�� : ��//;\n1;2;3�� => 6)
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(data);
        if (m.find()) {
            String customDelimiter = m.group(1);
            tokens= m.group(2).split(customDelimiter);
        }

        // ���� ����
        result = getSum(tokens);

        return result;
    }

    private static int getSum(String[] tokens) {
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).peek(num -> {
            if (num < 0) {
                throw new RuntimeException("������ ó���� �� �����ϴ�.");

            }
        }).sum();
    }
}
