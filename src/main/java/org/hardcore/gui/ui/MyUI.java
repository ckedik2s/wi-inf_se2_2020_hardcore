package org.hardcore.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.hardcore.gui.views.*;
import org.hardcore.model.objects.dto.UserDTO;
import org.hardcore.services.util.Views;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("Hausarbeit")
@PreserveOnRefresh
public class MyUI extends UI {
    private UserDTO userDTO = null;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Hintergrundbild
        setStyleName("hintergrundbild");

        Navigator navi = new Navigator(this,this);

        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.PROFILE, ProfileView.class);
        navi.addView(Views.REGISTRATION, RegistrationView.class);
        navi.addView(Views.LOGIN, LoginView.class);
        navi.addView(Views.STELLENANZEIGE, StellenanzeigeView.class);
        navi.addView(Views.BEWERBUNG, BewerbungView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }

    public  MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
