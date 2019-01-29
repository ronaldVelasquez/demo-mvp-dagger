package com.ronaldvelasquez.mvpdagger.login;

public class LoginModel implements LoginMvp.Model {
    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }
    @Override
    public void createUser(String firstName, String lastName) {
        // Agregar logica de business
        repository.saveUser(new User(firstName, lastName));
    }

    @Override
    public User getUser() {
        // Agregar logica de business
        return repository.getUser();
    }
}
