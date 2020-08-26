package org.hardcore.gui.components;

import com.vaadin.event.MouseEvents;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.hardcore.gui.ui.MyUI;
import org.hardcore.model.objects.dto.UserDTO;
import org.hardcore.process.proxy.LoginControlProxy;
import org.hardcore.services.util.Roles;
import org.hardcore.services.util.Views;

public class  TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        //Logo links oben in der Ecke
        ThemeResource icon = new ThemeResource("iconNonCopyRight-removebg-preview.png");
        Image logo = new Image(null, icon);
        logo.setWidth("160");
        logo.setStyleName("HomeButtonStyle");
        logo.addClickListener(new MouseEvents.ClickListener() {

            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
            }
        });
        this.addComponent(logo);
        this.setComponentAlignment(logo, Alignment.TOP_LEFT);


        //Willkommenstext oben rechts
        HorizontalLayout hlayout = new HorizontalLayout();
        setStyleName("schrift-willkommen");
        UserDTO userDTO = ( (MyUI) MyUI.getCurrent() ).getUserDTO();
        Label welcome = new Label("Willkommen bei CarLook!");
        if (userDTO != null) {
            if (userDTO.hasRole(Roles.STUDENT) && userDTO.getVorname() != null) {
                welcome = new Label("Willkommen " + userDTO.getVorname() + "!");
            }
            if (userDTO.hasRole(Roles.UNTERNEHMEN) && userDTO.getName() != null) {
                welcome = new Label("Willkommen " + userDTO.getName() + "!");
            }
        }
        hlayout.addComponent(welcome);
        hlayout.setComponentAlignment(welcome, Alignment.MIDDLE_CENTER);


        //Menü rechts oben
        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", VaadinIcons.MENU,null);


        //Gast Menü
        if (userDTO == null) {
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
        if (userDTO != null) {
            item1.addItem("Profil", VaadinIcons.USER, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    UI.getCurrent().getNavigator().navigateTo(Views.PROFILE);
                }
            });

            //Unternehmer Menü
            if ( userDTO.hasRole(Roles.UNTERNEHMEN) ) {
                item1.addItem("Meine Stellenanzeigen", VaadinIcons.FILE_TEXT_O, new MenuBar.Command() {
                    @Override
                    public void menuSelected(MenuBar.MenuItem menuItem) {
                        UI.getCurrent().getNavigator().navigateTo(Views.STELLENANZEIGE);
                    }
                });
            }

            //Student Menü
            if ( userDTO.hasRole(Roles.STUDENT) ) {
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
                    LoginControlProxy.getInstance().logoutUser();
                }
            });
        }

        //Einfügen
        hlayout.addComponent(bar);
        this.addComponent(hlayout);
        this.setComponentAlignment(hlayout, Alignment.BOTTOM_RIGHT);
    }

}
