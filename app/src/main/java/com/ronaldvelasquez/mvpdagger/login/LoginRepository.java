package com.ronaldvelasquez.mvpdagger.login;

public interface LoginRepository {
    void saveUser(User user);

    User getUser();
}
