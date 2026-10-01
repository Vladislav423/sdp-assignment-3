public abstract class Notification {
    private final String id;
    private final String message;
    protected Channel channel;

    public Notification(String id, String message, Channel channel) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null.");
        }
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null.");
        }
        this.id = id;
        this.message = message;
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setImplementation(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null.");
        }
        this.channel = channel;
    }

    public abstract String execute();
}