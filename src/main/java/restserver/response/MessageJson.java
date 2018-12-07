package restserver.response;

public class MessageJson {
    private String message;

    public MessageJson(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}