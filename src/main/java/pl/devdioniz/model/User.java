package pl.devdioniz.model;

public class User {

    private Long id;
    private String email = "";
    private String password = "";
    private String message = "";

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validate() {

        // validate EMAIL
        if (email == null || email.isEmpty()) {
            message = "Email cannot be empty.";
            return false;
        } else  if (!email.matches("\\w+@\\w+\\.\\w+")){
            message = "Email does not appear to be valid.";
            return false;
        }
        // validate PASSWORD
        if (password == null || password.isEmpty()) {
            message = "Password cannot be empty.";
            return false;
        } else if (password.length() < 8) {
            message = "Password must have at least 8 characters.";
            return false;
        } else if (password.matches(".*\\s+.*")) {
            message = "Password cannot contain spaces.";
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
