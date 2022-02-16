package exception;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.UnknownFormatConversionException;

public class ConcreteException {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int value;

        while (true) {
            try {
                value = Integer.parseInt(sc.nextLine());
                validate(value);
                break;
            } catch (IllegalFormatException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                NumberFormatExceptionHandling();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                IllegalArgumentExceptionHandling();
            }
        }
        System.out.println("value: " + value);

    }

    private static void IllegalArgumentExceptionHandling() {
        System.out.println("IllegalArgumentExceptionHandling 처리합니다..");
    }

    private static void NumberFormatExceptionHandling() {
        System.out.println("NumberFormatException을 처리합니다..");
    }

    private static void validate(int value) {
    }

}
