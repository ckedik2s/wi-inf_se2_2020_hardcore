package org.HardCore.gui.components;

import com.vaadin.event.MouseEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.LoginControl;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.awt.*;

public class  TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        //Logo links oben in der Ecke
        ThemeResource icon = new ThemeResource("logo_small.png");
        Image logo = new Image(null, icon);
        logo.setWidth("80");
        //logo.setStyleName("different-cursor");
        logo.addStyleName("pointer");
        logo.addClickListener(new MouseEvents.ClickListener() {

            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
            }
        });

        //Button logo = new Button("HardCore-Logo");
        //logo.addClickListener(new Button.ClickListener() {
        //    @Override
        //    public void buttonClick(Button.ClickEvent clickEvent) {
        //        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        //    }
        //});
        this.addComponent(logo);
        this.setComponentAlignment(logo, Alignment.TOP_LEFT);


        //Willkommenstext oben rechts
        HorizontalLayout hlayout = new HorizontalLayout();
        User user = ( (MyUI) MyUI.getCurrent() ).getUser();
        Label welcome = new Label("Willkommen bei HardCore!");
        if (user != null) {
            if (user.hasRole(Roles.STUDENT) && user.getVorname() != null) {
                welcome = new Label("Willkommen " + user.getVorname() + "!");
            }
            if (user.hasRole(Roles.UNTERNEHMEN) && user.getName() != null) {
                welcome = new Label("Willkommen " + user.getName() + "!");
            }
        }
        hlayout.addComponent(welcome);
        hlayout.setComponentAlignment(welcome, Alignment.MIDDLE_CENTER);


        //Menü rechts oben
        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", VaadinIcons.MENU,null);


        //Gast Menü
        if (user == null) {
            item1.addItem("Login", VaadinIcons.SIGN_IN, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
                }
            });
            item1.addItem("Registrieren", new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.REGISTRATION);
                }
            });
        }

        //Profil
        if (user != null) {
            item1.addItem("Profil", VaadinIcons.USER, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.PROFILE);
                }
            });

            //Unternehmer Menü
            if ( user.hasRole(Roles.UNTERNEHMEN) ) {
                item1.addItem("Meine Stellenanzeigen", VaadinIcons.FILE_TEXT_O, new MenuBar.Command() {
                    @Override
                    public void menuSelected(MenuBar.MenuItem menuItem) {
                        UI.getCurrent().getNavigator().navigateTo(Views.STELLENANZEIGE);
                    }
                });
            }

            //Student Menü
            if ( user.hasRole(Roles.STUDENT) ) {
                item1.addItem("Meine Bewerbungen", VaadinIcons.FILE_TEXT_O, new MenuBar.Command() {
                    @Override
                    public void menuSelected(MenuBar.MenuItem menuItem) {
                        UI.getCurrent().getNavigator().navigateTo(Views.BEWERBUNG);
                    }
                });
            }

            item1.addItem("Logout", VaadinIcons.SIGN_OUT, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    LoginControl.getInstance().logoutUser();
                }
            });
        }

        //Einfügen
        hlayout.addComponent(bar);
        this.addComponent(hlayout);
        this.setComponentAlignment(hlayout, Alignment.TOP_RIGHT);
    }

}
