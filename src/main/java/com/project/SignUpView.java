package com.project;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * Created by jackieflash on 2017-02-23.
 */
public class SignUpView extends CustomComponent implements View {

    public static final String NAME = "Signup";
    private final TextField username;
    private final PasswordField password, confirmPassword;
    private final Button signUpButton, clearButton;
    private static final String WIDTH_DEFAULT = "300px";
    private static final String USER_INPUT_PROMPT_MESSAGE 
            = "\"Your email here (e.g. joe_blow@mail.com)\"";

    public SignUpView() {
        setSizeFull();
        username = new TextField("User email: ");
        password = new PasswordField("Password: ");
        confirmPassword = new PasswordField("Confirm Password: ");
        signUpButton = new Button("Sign Up");
        clearButton = new Button("Clear");
        configureComponents();
        configureActions();
        setCompositionRoot(createLayout());
    }

    private void configureComponents() {
        username.setWidth(WIDTH_DEFAULT);
        username.setRequired(true);
        username.setInputPrompt(USER_INPUT_PROMPT_MESSAGE);
        username.setInvalidAllowed(false);
        password.setWidth(WIDTH_DEFAULT);
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");
        confirmPassword.setWidth(WIDTH_DEFAULT);
        confirmPassword.setRequired(true);
        confirmPassword.setValue("");
        confirmPassword.setNullRepresentation("");
        signUpButton.setCaption("Sign Up");
        clearButton.setCaption("Clear all feilds");
    }

    private void configureActions() {
        signUpButton.addClickListener((Button.ClickListener) clickEvent -> {
            User newUser = new User(username.getValue(),
                    password.getValue(),
                    confirmPassword.getValue());
            if (newUser.isValidUser()) {
                this.getUI().setContent(new LoginView());
                Notification.show("Signed up with username: " 
                        + username.getValue());
            }
        });
        
        clearButton.addClickListener((Button.ClickListener) clickEvent -> {
            username.clear();
            password.clear();
            confirmPassword.clear();
            username.setInputPrompt(USER_INPUT_PROMPT_MESSAGE);
        });
    }

    private Layout createLayout() {
        HorizontalLayout buttons = new HorizontalLayout(signUpButton, 
                clearButton);
        buttons.setSpacing(true);
        buttons.setMargin(new MarginInfo(true, true));
        VerticalLayout fields = new VerticalLayout(username,
                password, confirmPassword, buttons);
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, true));
        fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLACK);
        return viewLayout;
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        username.focus();
    }
}
