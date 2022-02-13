import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntermediateOperation {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        Stream stream = list.stream().map((s) -> {
            System.out.println("최종 연산시에 출력됨.");
            return s.toLowerCase();
        });
        stream.forEach(System.out::println);


        //-----------------------------------

        Integer[][] array = {{1,2,3},{4,5,6},{7,8,9}};
        Arrays.stream(array).map(Arrays::stream).forEach(System.out::println);
        Arrays.stream(array).flatMap(Arrays::stream).forEach(System.out::println);


        //---------------------------------------


        List<List<String>> twoDemensionList = new ArrayList<>();
        List<String> listOne = new ArrayList<>();
        List<String> listTwo = new ArrayList<>();
        listOne.add("list1 - 1");
        listOne.add("list1 - 2");
        listOne.add("list1 - 3");
        listTwo.add("list2 - A");
        listTwo.add("list2 - B");
        listTwo.add("list2 - C");
        twoDemensionList.add(listOne);
        twoDemensionList.add(listTwo);

        twoDemensionList.stream().map(Collection::stream).forEach(System.out::println);
        twoDemensionList.stream().flatMap(Collection::stream).forEach(System.out::println);



    }
}
