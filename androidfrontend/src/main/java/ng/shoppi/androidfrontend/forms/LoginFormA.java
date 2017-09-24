package ng.shoppi.androidfrontend.forms;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ng.shoppi.androidfrontend.R;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;
import ng.shoppi.androidfrontend.models.User;
import ng.shoppi.androidfrontend.util.Utility;

/**
 * Simulation of Risk App login form
 *
 * @author Olawale
 */

public class LoginFormA extends RelativeLayout {

    private ImageView backgroundImageView;
    private Button signInButton;
    private LinearLayout linearLayoutLoginForm;
    private ProgressBar progressBarSignIn;
    private ImageView imageView;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView signUpTextView;
    private LoginOnClickListener loginOnClickListener;
    private TextView forgotPasswordTextView;
    private boolean validateEmail;

    public LoginFormA(Context context) {
        super(context);
        initView(null);
    }

    public LoginFormA(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public LoginFormA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public LoginFormA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attributeSet) {
        View view = inflate(getContext(), R.layout.risk_login, null);
        if (attributeSet == null) {
            addView(view);
            return;
        }
        //grab the views
        backgroundImageView = view.findViewById(R.id.backgroundImage);
        imageView = view.findViewById(R.id.logo);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        forgotPasswordTextView = view.findViewById(R.id.textViewForgotPassword);
        signInButton = view.findViewById(R.id.buttonLogin);
        signUpTextView = view.findViewById(R.id.textViewSignUp);
        linearLayoutLoginForm = view.findViewById(R.id.linearLayoutLoginForm);
        progressBarSignIn = view.findViewById(R.id.progressBarSignIn);

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.LoginFormA);
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.LoginFormA_background_image);
        Drawable logoDrawable = typedArray.getDrawable(R.styleable.LoginFormA_logo);
        String userIdLabel = typedArray.getString(R.styleable.LoginFormA_user_id_label);
        String signInLabel = typedArray.getString(R.styleable.LoginFormA_sign_in_label);
        String signUpLabel = typedArray.getString(R.styleable.LoginFormA_sign_up_label);
        validateEmail = typedArray.getBoolean(R.styleable.LoginFormA_login_validate_email, true);

        if (userIdLabel != null && !userIdLabel.equals(""))
            editTextEmail.setHint(userIdLabel);
        else
            editTextEmail.setHint("Email");

        if (signInLabel != null && !signInLabel.equals(""))
            signInButton.setText(signInLabel);

        if (signUpLabel != null && !signUpLabel.equals(""))
            signUpTextView.setText(signUpLabel);

        if (logoDrawable != null)
            imageView.setImageDrawable(logoDrawable);

        if (backgroundDrawable != null)
            backgroundImageView.setImageDrawable(backgroundDrawable);
        addView(view);
        typedArray.recycle();
    }

    /**
     * Call to initialize the form
     *
     * @param activity Activity or Context
     */
    public void init(Activity activity) {

        loginOnClickListener = (LoginOnClickListener) activity;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOnClickListener.onSignInClick();
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOnClickListener.onSignUpClick();
            }
        });
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOnClickListener.onForgetPasswordClick();
            }
        });
    }

    /**
     * @return a user object containing the user information i.e email and password
     */
    public User getUser() {
        return new User(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    /**
     * @return true if the input fields are valid
     */
    public boolean validateInput() {
        return Utility.validateInput(editTextEmail, editTextPassword, validateEmail);

    }

    /**
     * @param show a boolean to indicate showing of progress bar
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgressBar(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

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

    }
}


