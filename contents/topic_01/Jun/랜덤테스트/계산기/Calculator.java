package 랜덤테스트.계산기;

import java.util.stream.IntStream;

public class Calculator {

    public int plus(int start, int end) {
        return IntStream.rangeClosed(start, end).sum();
    }
}
