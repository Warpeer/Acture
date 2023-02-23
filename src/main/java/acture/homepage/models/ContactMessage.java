package acture.homepage.models;

public class ContactMessage {
    private int id;
    private String fullName;
    private String email;
    private String message;
    private String received;
    private int messenger;
    private String messageStatus;

    public ContactMessage(int id, String fullName, String email, String message, String received, int messenger, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.message = message;
        this.received = received;
        this.messenger = messenger;
        this.messageStatus = status;
    }

    public ContactMessage(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public int getMessenger() {
        return messenger;
    }

    public void setMessenger(int messenger) {
        this.messenger = messenger;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }
}
