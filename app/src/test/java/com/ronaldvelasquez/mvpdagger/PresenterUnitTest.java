package com.ronaldvelasquez.mvpdagger;

import com.ronaldvelasquez.mvpdagger.login.LoginMvp;
import com.ronaldvelasquez.mvpdagger.login.LoginPresenter;
import com.ronaldvelasquez.mvpdagger.login.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PresenterUnitTest {
    // Marcador para cargar la configuracion de pruebas unitarias
    LoginPresenter presenter;
    User user;

    // no se necesitan instanciarlos porque seran simuladas por mockito
    LoginMvp.Model mockedModel;
    LoginMvp.View mockedView;

    // Se ejecuta antes de cada metodo de prueba. Si son 6 preubas se ejecutra 6 veces
    @Before
    public void initialize() {
        mockedModel = Mockito.mock(LoginMvp.Model.class);
        mockedView = Mockito.mock(LoginMvp.View.class);

        // No implemento  un repositorio porque no necesito que mi
        // prueba llame de otro lado sino que sea rapida porque
        // puede llamarse de varios lados pero seria lenta la app.
        user = new User("Pablo", "Escobar");

        // Cuando se llame al metodo del mocked retorno un comportamiento
        // para la prueba(Gracias a mockito)
//        Mockito.when(mockedModel.getUser()).thenReturn(user);
//        Mockito.when(mockedView.getFirstName()).thenReturn("Pablo");
//        Mockito.when(mockedView.getLastName()).thenReturn("Escobar");

        // Se pasas los mocked para instanciar el presenter concreto
        presenter = new LoginPresenter(mockedModel);
        presenter.setView(mockedView);
    }

    @Test
    public void noExistInteractorWithView() {
        presenter.getCurrentUser();
//        Mockito.verifyZeroInteractions(mockedView);
        Mockito.verify(mockedView, Mockito.times(1)).showUserNotAvailable();
//        Mockito.verify(mockedView, Mockito.never()).showUserNotAvailable();
    }

    @Test
    public void loadUserFromReportWhenValidUserPresent() {
        Mockito.when(mockedModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        // Valida el model
        Mockito.verify(mockedModel, Mockito.times(1)).getUser();

        // Valida la vista
        Mockito.verify(mockedView, Mockito.times(1)).setFirstName(user.getFirstName());
        Mockito.verify(mockedView, Mockito.times(1)).setLastName(user.getLastName());
        Mockito.verify(mockedView, Mockito.never()).showUserNotAvailable();
    }
    @Test
    public void showErrorMessageWhenUserIsNull() {
        Mockito.when(mockedModel.getUser()).thenReturn(null);
        presenter.getCurrentUser();
        Mockito.verify(mockedModel, Mockito.times(1)).getUser();
        Mockito.verify(mockedView, Mockito.times(1)).showUserNotAvailable();

        Mockito.verify(mockedView, Mockito.never()).setFirstName(user.getFirstName());
        Mockito.verify(mockedView, Mockito.never()).setLastName(user.getLastName());
    }

    @Test
    public void showMessageWhenUserSavedCorrect() {
        Mockito.when(mockedView.getFirstName()).thenReturn(user.getFirstName());
        Mockito.when(mockedView.getLastName()).thenReturn(user.getLastName());
        presenter.loginButtonClicked();
//        Mockito.verify(mockedModel, Mockito.times(1)).createUser(user.getFirstName(), user.getLastName());
        Mockito.verify(mockedView, Mockito.times(1)).showUserSaved();
    }

    @Test
    public void showMessageWhenUserInputIncorrect() {
        Mockito.when(mockedView.getFirstName()).thenReturn("");
        Mockito.when(mockedView.getLastName()).thenReturn("Escobar");
        presenter.loginButtonClicked();

        Mockito.verify(mockedView, Mockito.times(1)).getFirstName();
        Mockito.verify(mockedView, Mockito.never()).getLastName();
        Mockito.verify(mockedView, Mockito.times(1)).showInputError();

        Mockito.when(mockedView.getFirstName()).thenReturn("Pablo");
        Mockito.when(mockedView.getLastName()).thenReturn("");
        presenter.loginButtonClicked();

        Mockito.verify(mockedView, Mockito.times(1)).getLastName();
        Mockito.verify(mockedView, Mockito.times(2)).showInputError();
    }
}
