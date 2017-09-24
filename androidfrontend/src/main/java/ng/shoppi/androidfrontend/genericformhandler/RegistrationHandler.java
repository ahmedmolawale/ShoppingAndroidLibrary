package ng.shoppi.androidfrontend.genericformhandler;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import ng.shoppi.androidfrontend.listeners.RegistrationOnClickListener;
import ng.shoppi.androidfrontend.models.User;
import ng.shoppi.androidfrontend.util.Utility;

/**
 * A Registration Handler for any for
 *
 * @author Olawale
 */

public class RegistrationHandler {

    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPhone;
    private View signIn;
    private View register;
    private boolean emailValidation;
    private ProgressBar progressBar;
    private Context context;

    private RegistrationOnClickListener registrationOnClickListener;

    /**
     * Use to initialize the Registration handler
     *
     * @param editTextFirstname       The firstname on the form
     * @param editTextLastname        The lastname on the form
     * @param username                The id of the form. Must be an EditText or any of its subclass
     * @param password                The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param editTextConfirmPassword The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param editTextPhone           Phone number on the form
     * @param emailValidation         Pass true if you want email validation, false if the user id is not an email
     */
    public RegistrationHandler(EditText editTextFirstname, EditText editTextLastname, EditText username,
                               EditText password, EditText editTextConfirmPassword,
                               EditText editTextPhone, boolean emailValidation) {

        this.editTextFirstname = editTextFirstname;
        this.editTextLastname = editTextLastname;
        this.editTextEmail = username;
        this.editTextPassword = password;
        this.editTextConfirmPassword = editTextConfirmPassword;
        this.editTextPhone = editTextPhone;
        this.emailValidation = emailValidation;
        setUp();
    }

    /**
     * Use to initialize the Registration handler
     *
     * @param editTextFirstname       The firstname on the form
     * @param editTextLastname        The lastname on the form
     * @param username                The id of the form. Must be an EditText or any of its subclass
     * @param password                The editTextPassword EditText. Must be an EditText or any of its subclass
     * @param editTextConfirmPassword
     * @param editTextPhone           Phone number on the form
     * @param register                The Register in Button or TextView as the case may be.
     * @param signIn                  The Sign in Button or TextView as the case may be.
     * @param emailValidation         Pass true if you want email validation, false if the user id is not an email
     */
    public RegistrationHandler(EditText editTextFirstname, EditText editTextLastname, EditText username,
                               EditText password, EditText editTextConfirmPassword, EditText editTextPhone, View register, View signIn,
                               ProgressBar progressBar, boolean emailValidation) {

        this.editTextFirstname = editTextFirstname;
        this.editTextLastname = editTextLastname;
        this.editTextEmail = username;
        this.editTextPassword = password;
        this.editTextConfirmPassword = editTextConfirmPassword;
        this.editTextPhone = editTextPhone;
        this.signIn = signIn;
        this.register = register;
        this.progressBar = progressBar;
        this.emailValidation = emailValidation;
        setUp();
    }


    private void setUp() {
        context = editTextEmail.getContext();
        registrationOnClickListener = (RegistrationOnClickListener) context;
        if (signIn != null) {
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrationOnClickListener.onSignInClick();
                }
            });
        }
        if (register != null) {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registrationOnClickListener.onRegisterClick();
                }
            });
        }
    }

    /**
     * @return true if the input fields are valid
     */
    public boolean validateInput() {
        return Utility.validateInput(editTextFirstname, editTextLastname, editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhone, emailValidation);
    }

    /**
     * @param show a boolean to indicate showing of progress bar
     */
    public void showProgressBar(final boolean show) {
        if (progressBar != null)
            Utility.showProgressBar(show, progressBar);
    }

    /**
     * @return a user object containing the user information i.e email and password
     */
    public User getUser() {
        return new User(editTextFirstname.getText().toString(),
                editTextLastname.getText().toString(),
                editTextEmail.getText().toString(), editTextPassword.getText().toString(),
                editTextPhone.getText().toString());
    }
}
