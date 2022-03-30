
# Servlet
## Servlet이란?
- 웹 어플리케이션을 만들 때 필요한 인터페이스

## Servlet 동작방식
- 각각의 Servlet이 Servlet Interface를 상속받아 구현하고 있고, 이를 Servlet Container가 갖고 있고 Container를 통해 Server와 통신한다.

## Servlet 이 등장한 배경
### 정적 데이터만 전달가능했던 Web Server

![](https://images.velog.io/images/dragon9265/post/bd4be691-df57-4703-b1e0-7e2ff2f24106/image.png)

### 동적 데이터를 처리하는 CGI(Common Gateway Interface) 등장
![](https://images.velog.io/images/dragon9265/post/24e67fdf-af2e-4454-97b0-038d4778df5d/image.png)
![](https://images.velog.io/images/dragon9265/post/a86f838c-34d1-4495-9c13-320fd975bd23/image.png)
![](https://images.velog.io/images/dragon9265/post/751a724a-dddb-49e5-8512-a9c5a4c1ccbf/image.png)
![](https://images.velog.io/images/dragon9265/post/24fb2733-312b-4059-a501-eb2c1c782635/image.png)

### CGI(Common Gateway Interface)의 한계
![](https://images.velog.io/images/dragon9265/post/d1f69e70-757f-41fb-a554-8c3aa1151dfc/image.png)
![](https://images.velog.io/images/dragon9265/post/6aa7e0ae-d8b0-4c8b-a8a6-700205223587/image.png)
![](https://images.velog.io/images/dragon9265/post/5e00beb0-1f7b-48d4-8dc3-401eb47547de/image.png)
![](https://images.velog.io/images/dragon9265/post/aec5d4ff-5781-4972-87ec-c96e296089e7/image.png)

- WebContainer 역할 : 요청이 들어오면, 요청마다 Thread를 생성하고, Servlet 구현체를 연결해주고, Servlet Interface내 메소드를 호출해주는 역할을 한다.

## Servlet의 생명주기
![](https://images.velog.io/images/dragon9265/post/08229feb-14bc-4f79-9e0f-67fc4951866a/image.png)
![](https://images.velog.io/images/dragon9265/post/c22397f3-cb48-4506-88ec-2ba7a3e6ee34/image.png)
- Init : Servlet Instance 생성하는 곳 (initalize)
- Service : 실제 기능이 수행되는 곳(요청을 처리할 때 호출이 되는 메서드)
~~~java
abstract class HttpServlet extends Servlet
	//HTTP Method(GET, POST, PUT, DELETE)에 따라
    //doGet(), doPost(), doPut(), doDelete()메서드를 호출한다.
    //doxxx() : 개발자가 구현하는 부
~~~
- Destroy : Servlet Instance가 사라지는곳
    - 보통 container가 종료되는 시점에 destroy()호출
    - 특정 servlet 로드/언로드 시에도 사용
- 각 메서드는 Servlet Container(Tomcat)이 호출해준다.

## Servlet Container & Servlet 호출과정
![](https://images.velog.io/images/dragon9265/post/bab0f729-b5f6-4fd9-bc3a-d6d107c9cb54/image.png)
![](https://images.velog.io/images/dragon9265/post/bdfbdd3c-c033-4141-9b74-6c29094a0c6b/image.png)
- 1. 요청이 들어오면 Servlet Request / Servlet Reponse 객체 생성
- 2. 설정 파일을 참고하여 매핑할 Servlet을 확인
- 3. 해당 서블릿 인스턴스 존재 유무를 확인하여 없으면 생성(Init())
- 4. ServletContainer에 스레드를 생성하고, Res, Req를 인자로 Service 실행
- 5. 응답을 처리한다.
     ![](https://images.velog.io/images/dragon9265/post/a3b46a9f-2982-46eb-ab25-547c63070469/image.png)

## Dispatcher Servlet
![](https://images.velog.io/images/dragon9265/post/d41446f8-fb40-4579-860b-7318863551b3/image.png)
![](https://images.velog.io/images/dragon9265/post/8d37f072-6373-4a6e-a78a-238670b643a0/image.png)
![](https://images.velog.io/images/dragon9265/post/a08286dd-9ff5-4785-90da-e90128771f79/image.png)
![](https://images.velog.io/images/dragon9265/post/dc6ac9c2-3460-43dc-9fc4-2caf1c7dabf8/image.png)
![](https://images.velog.io/images/dragon9265/post/9b5fbccb-474c-48c3-8126-d44682e1c645/image.png)
![](https://images.velog.io/images/dragon9265/post/493d56f2-7f72-4a34-a6d8-fc28492c50f3/image.png)
![](https://images.velog.io/images/dragon9265/post/d04cb08f-277c-4c69-9b66-94a0a3651ccb/image.png)

### Web 요청 처리과정
![](https://images.velog.io/images/dragon9265/post/dc6ac9c2-3460-43dc-9fc4-2caf1c7dabf8/image.png)
![](https://images.velog.io/images/dragon9265/post/61c476ae-f231-42ff-8b68-f033cb485e72/image.png)
![](https://images.velog.io/images/dragon9265/post/8214bd15-0f19-4f72-a05e-481f5aeb5f63/image.png)
![](https://images.velog.io/images/dragon9265/post/750782c3-0b4a-413a-bdf0-1503ac083a74/image.png)
![](https://images.velog.io/images/dragon9265/post/fc8bbc1c-97e2-45b6-a915-4307b9199b3c/image.png)
![](https://images.velog.io/images/dragon9265/post/d8b73e84-0469-4ea7-8ebb-a34b3e040b5d/image.png)

![](https://images.velog.io/images/dragon9265/post/f8400ade-fd63-43ef-96db-568a49d1cebf/image.png)

***
ref.
https://www.youtube.com/watch?v=cmwmamOQmPc
https://www.youtube.com/watch?v=calGCwG_B4Y
https://www.youtube.com/watch?v=2pBsXI01J6M