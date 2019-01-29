package com.ronaldvelasquez.mvpdagger.root;

import com.ronaldvelasquez.mvpdagger.http.TwitchModule;
import com.ronaldvelasquez.mvpdagger.login.LoginActivity;
import com.ronaldvelasquez.mvpdagger.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

// REFERENCIAS ACTIVIDADES, SERVICIOS Y FRAGMENTOS añadir como componentes se tiene que añadir con metodos individuales
// Componente es injectar una parte de codigo en otro lugar
@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class, TwitchModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity targeter);
}
