package 옵저버패턴_V2;

public class Main {
    public static void main(String[] args) throws Exception {
        Youtube youtube = new Youtube();
        Subjects cookYoutuber = new CookYoutuber("백종원", youtube);
        Subjects gameYoutuber = new GameYoutuber("보겸", youtube);

        Observer jun = new User("Jun");
        jun.subscribe(cookYoutuber);
        jun.subscribe(gameYoutuber);

        cookYoutuber.setContent("새우초밥 만들기");
        gameYoutuber.setContent("스타크래프트 5연승");
    }
}
