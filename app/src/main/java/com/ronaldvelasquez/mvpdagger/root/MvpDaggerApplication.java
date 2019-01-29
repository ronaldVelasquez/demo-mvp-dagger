package com.ronaldvelasquez.mvpdagger.root;

import android.app.Application;

import com.ronaldvelasquez.mvpdagger.login.LoginModule;

public class MvpDaggerApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // ApplicationComponent donde vivira dagger.
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
