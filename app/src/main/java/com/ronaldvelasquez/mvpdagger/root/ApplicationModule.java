package com.ronaldvelasquez.mvpdagger.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

// Crear modulo de dagger para la aplicación
@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule (Application application) {
        this.application = application;
    }

    // Métodos con valor de retorno
    // singleton para solo valores que quieren que se creen una sola vez en toda la app
    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}
