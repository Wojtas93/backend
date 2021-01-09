package pl.sdacademy.backend.user;

import java.util.List;

public class UserResponseDto {
    private List<User> users;

    public UserResponseDto(List<User> users) {
        users.forEach(user ->user.setPassword("******"));
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;

    }
}
