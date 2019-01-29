package com.ronaldvelasquez.mvpdagger.login;

public interface LoginMvp {
    interface Presenter {
        void setView(LoginMvp.View view);
        void loginButtonClicked();
        void getCurrentUser();
    }

    interface View {
        String getFirstName();
        String getLastName();
        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();
        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Model {
        void createUser(String firstName, String lastName);
        User getUser();
    }
}
