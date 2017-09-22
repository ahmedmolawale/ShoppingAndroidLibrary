package ng.shoppi.androidfrontend;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author Olawale
 *         <p>
 *         This form is based on risk app
 */

public class LoginFormA {

    private static FormAOnClickListener formAOnClickListener;
    View view;
    Activity mActivity;

    public interface FormAOnClickListener {
        void onSignInClick();

        void onSignUpClick();

        void onForgetPasswordClick();
    }
    public static void init(Activity activity){
        View view = LayoutInflater.from(activity).inflate(R.layout.risk_login, null);
       // mActivity = activity;
        formAOnClickListener = (FormAOnClickListener) activity;
        setupClickListener(view, activity);

    }

    public LoginFormA(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.risk_login, null);
        //view = LayoutInflater.from(activity).inflate(R.layout.risk_login, null);
        mActivity = activity;
        formAOnClickListener = (FormAOnClickListener) mActivity;
        setupClickListener(view, mActivity);
    }

    private static void setupClickListener(View view, final Activity activity) {
        Button button = view.findViewById(R.id.buttonLogin);
        if(button == null)
            Toast.makeText(activity, "Hey You", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(activity, "Hey You...."+ button.getText().toString(), Toast.LENGTH_SHORT).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Hey You", Toast.LENGTH_SHORT).show();
                Log.d("Hey","Its here.....................");
                formAOnClickListener.onSignInClick();
            }
        });

        TextView signUp = view.findViewById(R.id.textViewSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Hey You", Toast.LENGTH_SHORT).show();
                formAOnClickListener.onSignUpClick();
            }
        });
        TextView forgetPassword = view.findViewById(R.id.textViewForgotPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Hey You", Toast.LENGTH_SHORT).show();
                formAOnClickListener.onForgetPasswordClick();
            }
        });
        //Toast.makeText(activity, button.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private  boolean isEmailValid(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public boolean validateInput() {
        // Reset errors.
        EditText editTextEmail = view.findViewById(R.id.editTextEmail);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextEmail.setError(null);
        editTextPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            editTextPassword.setError(mActivity.getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Required");
            focusView = editTextEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            editTextEmail.setError(mActivity.getString(R.string.error_invalid_email));
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
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgressBar(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        final LinearLayout linearLayoutLoginForm = view.findViewById(R.id.linearLayoutLoginForm);
        final ProgressBar progressBarSignIn = view.findViewById(R.id.progressBarSignIn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = mActivity.getResources().getInteger(android.R.integer.config_shortAnimTime);

            linearLayoutLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            linearLayoutLoginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    linearLayoutLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressBarSignIn.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBarSignIn.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBarSignIn.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBarSignIn.setVisibility(show ? View.VISIBLE : View.GONE);
            linearLayoutLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
