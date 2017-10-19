package fr.raluy.jobsearchback.auth;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}