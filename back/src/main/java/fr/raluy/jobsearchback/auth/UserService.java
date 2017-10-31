package fr.raluy.jobsearchback.auth;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}