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

    private static boolean isEmailValid(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    private static boolean isPasswordValid(String password) {
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

}
