package ua.voloschenko;

public class Email {

    private String email;

    public Email(String email) {
        if(email == null || !email.contains("@"))
            throw new IllegalArgumentException("Invalid email address");
    }

    public String getValue() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }

}
