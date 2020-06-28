package org.HardCore.gui.views;


import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.proxy.LoginControlProxy;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.NoSuchUserOrPassword;

public class LoginView extends VerticalLayout implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    private void setUp() {
        //Top Layer
        this.addComponent(new TopPanel());
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Login Felder
        final TextField userLogin = new TextField("Email:");
        userLogin.focus();
        userLogin.setPlaceholder("beispiel@gmx.de");
        Binder<UserDTO> binder = new Binder<>();
        binder.forField(userLogin)
                .withValidator(new EmailValidator("Bitte geben Sie eine korrekte Emailadresse ein!"))
                .bind(UserDTO::getEmail, UserDTO::setEmail);
        final PasswordField passwordField = new PasswordField("Passwort:");
        passwordField.setPlaceholder("Passwort");

        //Login Button
        Button loginButton = new Button("Login");
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String email = userLogin.getValue();
                String password = passwordField.getValue();

                try {
                    LoginControlProxy.getInstance().checkAuthentification(email, password);
                } catch (NoSuchUserOrPassword noSuchUserOrPassword) {
                    Notification.show("Benutzer-Fehler", "Login oder Passwort falsch!", Notification.Type.ERROR_MESSAGE);
                } catch (DatabaseException e) {
                    Notification.show("DB-Fehler", e.getReason(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        //Vertical Layout
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(userLogin);
        verticalLayout.addComponent(passwordField);
        verticalLayout.addComponent(loginButton);

        //Login Panel
        Panel panel = new Panel( "Bitte Login-Daten eingeben:");
        panel.setContent(verticalLayout);
        panel.setSizeUndefined();

        //Einfügen
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);


    }
}
