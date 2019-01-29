package com.ronaldvelasquez.mvpdagger.login;

public class MemoryRepository implements LoginRepository {
    private User user;

    @Override
    public void saveUser(User user) {
        if (user == null) {
            user = getUser();
        } else {
            this.user = user;
        }
    }

    @Override
    public User getUser() {
        if (user != null) {
            return user;
        } else {
            this.user = new User("Antonio", "Banderas");
            this.user.setId(1);
            return user;
        }
    }
}
