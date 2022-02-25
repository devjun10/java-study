package 옵저버패턴_V2;

//
public interface Observer {
    Long getId();
    void update(Long youtuberId, Long userId, String content);
    void subscribe(Subjects subject);
}
