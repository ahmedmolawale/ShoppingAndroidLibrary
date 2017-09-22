package ng.shoppi.androidfrontend.GenericFormHandler;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import ng.shoppi.androidfrontend.util.Utility;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;

/**
 * @author Olawale
 */

public class GenericLoginHandler {

    private View forgotPassword;
    private View register;
    private EditText editTextPassword;
    private View signIn;
    private boolean emailValidation;
    private EditText editTextEmail;
    private ProgressBar progressBar;
    Context context;

    private LoginOnClickListener loginOnClickListener;


    /**
     * @param username        The id of the form. Must be an EditText or any of its subclass
     * @param password        The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param signIn          The Sign in Button or TextView as the case may be.
     * @param emailValidation Pass true if you want email validation, false if the user id is not an email
     */
    public GenericLoginHandler(EditText username, EditText password, View signIn, ProgressBar progressBar, boolean emailValidation) {
        this.editTextEmail = username;
        this.editTextPassword = password;
        this.signIn = signIn;
        this.progressBar = progressBar;
        this.emailValidation = emailValidation;
        setUp();
    }

    /**
     * @param username        The id of the form. Must be an EditText or any of its subclass
     * @param password        The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param signIn          The Sign in Button or TextView as the case may be.
     * @param register        The Sign up Button or TextView as the case may be.
     * @param emailValidation Pass true if you want email validation, false if the user id is not an email
     */
    public GenericLoginHandler(EditText username, EditText password, View signIn, ProgressBar progressBar, View register, boolean emailValidation) {
        this.editTextEmail = username;
        this.editTextPassword = password;
        this.signIn = signIn;
        this.progressBar = progressBar;
        this.register = register;
        this.emailValidation = emailValidation;
        setUp();
    }

    /**
     * @param username        The id of the form. Must be an EditText or any of its subclass
     * @param password        The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param signIn          The Sign in Button or TextView as the case may be.
     * @param emailValidation Pass true if you want email validation, false if the user id is not an email
     * @param register        The Sign up Button or TextView as the case may be.
     * @param forgotPassword  The Forgot Password Button or TextView as the case may be.
     */
    public GenericLoginHandler(EditText username, EditText password, View signIn, ProgressBar progressBar, View register, View forgotPassword, boolean emailValidation) {

        this.editTextEmail = username;
        this.editTextPassword = password;
        this.signIn = signIn;
        this.progressBar = progressBar;
        this.register = register;
        this.emailValidation = emailValidation;
        this.forgotPassword = forgotPassword;
        setUp();
    }

    private void setUp() {
        context = editTextEmail.getContext();
        loginOnClickListener = (LoginOnClickListener) context;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOnClickListener.onSignInClick();
            }
        });
        //check that the user provides register button in constructor
        if(register != null){
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginOnClickListener.onSignUpClick();
                }
            });
        }

        if(forgotPassword != null){
            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loginOnClickListener.onForgetPasswordClick();
                }
            });
        }

    }
    public boolean validateInput() {
        return Utility.validateInput(editTextEmail,editTextPassword,emailValidation);
    }

    public void showProgressBar(final boolean show) {
        Utility.showProgressBar(show, progressBar);
    }

}
