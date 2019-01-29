package com.ronaldvelasquez.mvpdagger.login;

import androidx.annotation.Nullable;

public class LoginPresenter implements LoginMvp.Presenter {
    @Nullable
    private LoginMvp.View view;
    private LoginMvp.Model model;

    // Dagger crea solo el modelo al momento de crear el presenter por inyeccion
    public LoginPresenter(LoginMvp.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginMvp.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") ||
                    view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName().trim(), view.getLastName().trim());
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if (user == null) {
            if (view != null) {
                view.showUserNotAvailable();
            }
        }else {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
