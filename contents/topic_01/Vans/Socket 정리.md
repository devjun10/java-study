

# Socket
## 웹 소켓이란?
- 현재 인터넷 환경(HTML5)에서 가장 많이 사용되는것이다.
- 웹 소켓을 지원하는 브라우저(익스플로어, 엣지등)의 경우 웹 소켓 프로토콜을 지원한다.
- 두 프로그램 간의 메시지를 교환하기 위한 통신 방법중 하나이다.

>Client(Web socket) <-----Message-----> Server(Web socket)

## 웹 소켓의 특징
- `양방향 통신(Full-Duplex)`
    - 데이터 송수신을 동시에 처리할 수 있는 통신 방법이다.
    - 클라이언트와 서버가 서로에게 원할 때 데이터를 주고 받을 수 있다.
    - ❗️통상적인 HTTP 통신은 Client가 요청을 보내는 경우에만 Server가 응답하는 단방향 통신이다.

- `실시간 네트워킹(Real Time-Networking)`
    - 웹 환경에서 연속된 데이터(채팅, 주식, 비디오 데이터등)를 빠르게 노출시켜준다.
    - 여러 단발기에 빠르게 데이터를 교환해준다.

## 웹 소켓 이전의 비슷한 기술
### Polling
![](https://images.velog.io/images/dragon9265/post/217c8d57-85d2-407c-a7e8-1dc4b2003b6c/image.png)
- 서버에 일정 주기로 요청을 송신한다.
- Real-time 통신에서는 언제 통신이 발생할지 예측이 불가능하므로, 불필요한 Request와 Connection을 생성한다.
  (바뀐 내역이 없는데도 지속적으로 요청을 해야하는 상황)
- Real-time 통신이라고 부르기 애매할 정도의 실시간성

### Long Polling
![](https://images.velog.io/images/dragon9265/post/050a1f8c-4baa-4e6d-8858-77a71b0a827f/image.png)
- 서버에 요청을 보내고 이벤트가 생겨 응답을 받을때 까지 연결을 유지시킨다.
- 응답 받으면 연결을 끊고 재요청한다.
- 많은 양의 메시지가 쏟아지게되면 결국 Polling과 같아진다.

### Streaming
![](https://images.velog.io/images/dragon9265/post/1cefd9b4-7369-47eb-8220-13f8d20f527a/image.png)
- 서버에 요청을 보내고 끊키지 않은 연결상태에서 끊임없이 데이터를 수신한다.
- 클라이언트에서 서버로의 데이터 송신이 어렵다.

> 결과적으로 이 모든 방법들이 HTTP를 통해 통신하기 때문에 Request, Response 모두 Header가 불필요하게 컸다.
(빠르게 데이터를 주고 받아야 하는데 Header가 너무 크다는 의미)

## 웹 소켓 동작방법 - 데이터 전송
### 핸드 쉐이크

** # Client ---요청---> 서버 **

![](https://images.velog.io/images/dragon9265/post/0a238a21-a8f0-4a26-aa04-e7fe8c8d6615/image.png)

** # Client <---응답--- 서버 **

![](https://images.velog.io/images/dragon9265/post/b4c0f1b0-4555-4015-89ca-d62a9b369907/image.png)

** 핸드 쉐이크 완료 **

![](https://images.velog.io/images/dragon9265/post/e29665a8-f4a1-4374-9888-436879a4dbec/image.png)

- 프로토콜이 ws로 변경된다.
- `wss(443)`: 데이터 보안을 위해서 SSL을 적용한 프로토콜
- `Message`: 데이터 주고받을때 쓰이는 단위로, 여러 `frame`이 모여서 구성하는 하나의 논리적 메시지 단위이다.
- `frame`: communication에서 가장 작은 단위의 데이터로 **작은헤더 + payload(3계층 패킷 단위)로 구성**
- 웹소켓 통신에 사용되는 데이터는 UTF8 인코딩을 통해서만 지원된다.
  (ex. 0x00 (보내고 싶은 데이터) Oxff)

> 데이터 양방향 전송이 완료되면 **Close frame**을 주고 받으며 연결이 종료된다.

![](https://images.velog.io/images/dragon9265/post/6277af4c-f42e-443d-b5a8-6e4cd68d2108/image.png)

## 웹 소켓 프로토콜의 특징
- 최초 접속에서만 http 프로토콜 위에서 handshaking을 하기 때문에 http header를 사용한다.
- 웹 소켓을 위한 별도의 포트는 없으며, 기존 포트(http(80), https(442))을 사용한다.
- `frame`으로 구성된 `Message`라는 논리적 단위로 송수신한다.
- `Message`에 포함될 수 있는 교환 가능한 `Message`는 텍스트와 바이너리 뿐이다.


## 웹 소켓의 한계
- HTML5 이전의 기술로 구현된 서비스에서는 웹소켓을 어떻게 해야할까?

#### Socket.io / SockJS
- HTML5 이전의 기술로 구현된 서비스에서 **웹 소켓처럼** 사용할 수 있도록 도와주는 기술
  (실시간 통신을 도와준다는 의미)
- JS를 이용하여 브라우저 종류에 상관없이 실시간 웹을 구현
- Websocket, FlashSocket, AJAX Long Polling, AJAX Multi part Streaming, IFrame, JSONP Polling을 하나의 API로 추상화
- 즉, 브라우저와 웹 서버의 종류와 버전을 파악하여 가장 적합한 기술을 선택하여 웹소켓 처럼 보여지게 기능을 콜백하는 방식이다.

#### STOMP
- Websocket은 문자열들을 주고 받을 수 있게 해줄 뿐 그 이상의 일을 해주진 않는다.
- 주고받은 문자열의 해독은 온전히 어플리케이션에 맡긴다.
- HTTP는 형식을 정해두었기 때문에 모두가 약속을 따르기만 하면 해석할 수 있다.
- 하지만 Websocket은 형식이 정해져 있기 때문에 어플리케이션에서 쉽게 해석하기 힘들다.
- 때문에 Websocket 방식은 sub-protocols을 사용해서 주고 받는 메시지의 형태를 약속하는 경우가 많다.
  (서로가 해석하기 편하기 위함)

** sub-protocols : STOMP(Simple Text Oriented Message Protoco) **
- STOMP는 채팅 통신을 하기 위한 형식을 정의한다.
- HTTP와 유사하게 간단히 정의되어 해석하기 편한 프로토콜이다.
- 일반적으로 웹소켓 위에서 사용된다.
- 프레임 구조
~~~java
COMMAND
header1:value1
header2:value2

BodyBodyBodyBody^@
~~~
- 프레임 기반의 프로토콜이고 프레임은 명령(Command), 헤더(header), 바디로(body) 구성된다.
- 자주 사용되는 명령은 CONNET, SEND, SUBSCRIBE, DISCONNECT등이 있다.
- 헤더와 바디는 빈 라인으로 구분하며, 바디의 끝은 NULL문자로 설정한다.

# Socket 프로그래밍

![](https://images.velog.io/images/dragon9265/post/4025bec9-7c68-4bad-a2b1-2f8fac200950/image.png)

## Socket을 이용한 클라이언트와 서버 통신 프로그램 구조

![](https://images.velog.io/images/dragon9265/post/18eff583-dc38-48b1-86a9-32e4a7613b47/image.png)

![](https://images.velog.io/images/dragon9265/post/f57f2c48-2a1e-4b2d-b32f-58f7bec537fe/image.png)

![](https://images.velog.io/images/dragon9265/post/4206278c-b878-4f32-8d83-b204f5fae02d/image.png)

![](https://images.velog.io/images/dragon9265/post/1621b4de-990f-4f20-862a-55032f4546ca/image.png)

![](https://images.velog.io/images/dragon9265/post/1db90ff9-a41e-4dc0-a9f4-ec5edd466321/image.png)

![](https://images.velog.io/images/dragon9265/post/5ea9d020-f8ab-4575-8c9d-9132afa42b16/image.png)

![](https://images.velog.io/images/dragon9265/post/e26548ce-b61a-44ae-8500-dfd6ffb43ec1/image.png)

![](https://images.velog.io/images/dragon9265/post/57c95872-33f3-45c8-b6d5-483848d171ad/image.png)

***
ref
https://www.youtube.com/watch?v=dX82Wuc18wk
https://www.youtube.com/watch?v=MPQHvwPxDUw