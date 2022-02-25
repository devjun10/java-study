package 옵저버패턴_V2;

public interface Subjects extends Youtuber {
    void register(Observer observer);
    void update(String content);
    void notifyInformation();
}
