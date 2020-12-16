package pl.sdacademy.backend.user;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
