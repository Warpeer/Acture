package acture.homepage.models;

public class Customer {
    private int id;
    private String fullName;
    private String email;
    private String dateStarted;
    private int messenger;

    public Customer(int id, String fullName, String email, String dateStarted, int messenger) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateStarted = dateStarted;
        this.messenger = messenger;
    }

    public Customer(){

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

    public String getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(String dateStarted) {
        this.dateStarted = dateStarted;
    }

    public int getMessenger() {
        return messenger;
    }

    public void setMessenger(int messenger) {
        this.messenger = messenger;
    }
}
