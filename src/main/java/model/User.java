package model;

import java.util.UUID;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User getRandomUser() {
        String randomString = UUID.randomUUID().toString().substring(0, 8);
        return new User(
                "test-" + randomString + "@example.com",
                "password-" + randomString,
                "name-" + randomString
        );
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
}