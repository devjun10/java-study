package optional;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalExample {

    private MyClass myClass;

//    public void setMyClass(MyClass myClass) {
//        this.myClass = myClass;
//    }

    //매개변수로 Optional을 사용하지 말 것.
    //setMyClass(null)이 가능하기 때문 -> nullpoint exception 발생.
    public void setMyClass(Optional<MyClass> myClass) {
        if (myClass.isPresent()) {
            this.myClass = myClass.get();
        }


    }

    //Optional은 리턴값으로만 쓰는 것을 권장함.
    public Optional<MyClass> getMyClass() {
        return Optional.ofNullable(myClass);
    }

}
