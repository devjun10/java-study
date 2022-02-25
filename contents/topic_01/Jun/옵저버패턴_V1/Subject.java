package 옵저버패턴_V1;

public interface Subject {
    void register(Observer observer);

    void remove(Observer observer);

    void notifyUpdate();
}
