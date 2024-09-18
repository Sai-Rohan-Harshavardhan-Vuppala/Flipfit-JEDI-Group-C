package flipfit.flipkart.bean;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;

    public User(String name, String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

//    public User(String username, String password, String name) {
//        this.username = username;
//        this.password = password;
//        this.email = name;
//    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.email = email;
    }

}
