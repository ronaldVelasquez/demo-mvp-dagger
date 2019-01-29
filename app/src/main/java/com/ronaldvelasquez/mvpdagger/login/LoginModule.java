package com.ronaldvelasquez.mvpdagger.login;

import dagger.Module;
import dagger.Provides;

// Punto de conexion con dagger
@Module
public class LoginModule {

    @Provides
    public LoginMvp.Presenter provideLoginPresenter(LoginMvp.Model model) {
        return new LoginPresenter(model);
    }

    @Provides
    public LoginMvp.Model provideLoginModel(LoginRepository loginRepository) {
        return new LoginModel(loginRepository);
    }

    @Provides
    public LoginRepository provideLoginRepository() {
        // Cambiar de donde obtenga la data, para ejemplo es en memoria
        return new MemoryRepository();
    }
}
