package exception;

public enum ErrorMessage {

    NUMBER_FORMAT_EXCEPTION("숫자만 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println(message);
    }
}
