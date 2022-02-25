package 옵저버패턴_V2;

import java.util.ArrayList;
import java.util.List;

public class CookYoutuber implements Subjects {

    private Long id;
    private String name;
    private List<Long> subscribers = new ArrayList<>();
    private Youtube youtube;
    private String content;

    public CookYoutuber(String name, Youtube youtube) {
        this.name = name;
        this.id = Youtube.createYoutuberId();
        this.youtube = youtube;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        update(content);
    }

    @Override
    public void register(Observer observer) {
        subscribers.add(observer.getId());
    }

    @Override
    public void update(String content) {
        for (Long userId : subscribers) {
            youtube.update(this.id, userId, content);
        }
    }

    @Override
    public void notifyInformation() {
        System.out.println(this.name + "님이 이번 주 [" + content + "] 를 업로드 하셨습니다.");
    }

    @Override
    public Long getId() {
        return id;
    }
}
