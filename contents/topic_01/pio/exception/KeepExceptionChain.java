package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeepExceptionChain {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        int number = 0;
//        while(true) {
//            try {
//                number = inputNumber();
//                break;
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getCause());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            number = inputNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getCause());
            inputNumber();
        }
        System.out.println(number);
    }

    //NumberFormatException 예외가 발생했을 때 예외에 대한 메시지를 출력하려면,
    //새롭게 예외를 생성해서 메시지를 담은 다음 throw해가지고 inputNumber를 호출한 부분에서
    //예외를 받아야 한다. 그래서 호출부에서 try catch를 감싸줘야 함..
    // 근데 enum으로 에러메시지 클래스를 만들어 사용한다? 그러면 inputNumber메서드 안에서만
    // try catch를 감싸고, 메시지 호출부에는 try catch를 감싸지 않아도 됨.

    public static int inputNumber() {
        int value = 0;
        try {
            value = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            // IllegalArgumentException으로 바꿔서
            //예외를 리턴하더라도 예외를 받는 쪽에서 NumberFormatException에 대한 예외정보을 이어 받을 수 있음(사슬)
            throw new IllegalArgumentException("숫자만 입력하세요", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("예기치 못한 IOException", e); // IOException은 throw안되나?
        }
        return value;
    }

}
