package observer2;

public interface Subjects extends Youtuber {
    void register(Observer observer);
    void update(String content);
    void notifyInformation();
}
