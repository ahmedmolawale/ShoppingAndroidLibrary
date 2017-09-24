package ng.shoppi.androidfrontend.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import ng.shoppi.androidfrontend.R;


/**
 * @author Olawale
 */

public class Utility {

    public static boolean isEmailValid(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public static boolean validateInput(EditText editTextEmail, EditText editTextPassword, boolean emailValidation) {
        Context context = editTextEmail.getContext();
        // Reset errors.
        editTextEmail.setError(null);
        editTextPassword.setError(null);
        // Store values at the time of the login attempt.
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !Utility.isPasswordValid(password)) {
            editTextPassword.setError(context.getResources().getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextEmail;
            cancel = true;
        } else if (emailValidation && !Utility.isEmailValid(email)) {
            editTextEmail.setError(context.getResources().getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static void showProgressBar(final boolean show, final ProgressBar progressBar) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        Context context = progressBar.getContext();
        int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBar.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

    }

    public static boolean validateInput(EditText editTextFirstname, EditText editTextLastname, EditText editTextEmail,
                                        EditText editTextPassword, EditText editTextConfirmPassword, EditText editTextPhone, boolean emailValidation) {
        Context context = editTextEmail.getContext();
        // Reset errors.
        editTextFirstname.setError(null);
        editTextLastname.setError(null);
        editTextEmail.setError(null);
        editTextPassword.setError(null);
        editTextConfirmPassword.setError(null);
        editTextPhone.setError(null);
        // Store values at the time of the registration attempt.
        String firstName = editTextFirstname.getText().toString().trim();
        String lastName = editTextLastname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String phone = editTextPhone.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        //check for emptiness of first name and lastname
        if (TextUtils.isEmpty(firstName)) {
            editTextFirstname.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextFirstname;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastName)) {
            editTextLastname.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextLastname;
            cancel = true;
        }
        if (TextUtils.isEmpty(password) || !Utility.isPasswordValid(password)) {
            editTextPassword.setError(context.getResources().getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(confirmPassword) || !Utility.isPasswordValid(confirmPassword)) {
            editTextConfirmPassword.setError(context.getResources().getString(R.string.error_invalid_password));
            focusView = editTextConfirmPassword;
            cancel = true;
        }
        if (!password.equals(confirmPassword)) {
            editTextPassword.setError(context.getResources().getString(R.string.error_password_mismatch));
            focusView = editTextPassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextEmail;
            cancel = true;
        } else if (emailValidation && !Utility.isEmailValid(email)) {
            editTextEmail.setError(context.getResources().getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        }
        //check for phone number
        if (phone.isEmpty()) {
            editTextPhone.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextPhone;
            cancel = true;
        }
        if (phone.length() != 11) {
            editTextPhone.setError(context.getResources().getString(R.string.error_phone_incomplete));
            focusView = editTextPhone;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
