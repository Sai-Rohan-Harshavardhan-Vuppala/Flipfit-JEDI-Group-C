package flipfit.flipkart.bean;

public class Notification {

    private int notficationId;
    private String email;

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

    private String message;
    static int counter = 0;

    public Notification(String message, String email) {
        this.message = message;
        this.email = email;
        this.notficationId = counter++;
    }

    public int getNotficationId() {
        return notficationId;
    }
}
