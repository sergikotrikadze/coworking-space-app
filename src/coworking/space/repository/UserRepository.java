package coworking.space.repository;

import coworking.space.entities.User;
import java.util.HashSet;
import java.util.Set;

public class UserRepository {

    private Set<User> users;

    public UserRepository() {
        this.users = new HashSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByLogin(String login) {
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }
}
