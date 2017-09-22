package ng.shoppi.androidfrontend;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;
import ng.shoppi.androidfrontend.util.Utility;

/**
 * @author Olawale
 */

public class LoginB extends RelativeLayout {

    private Button signInButton;
    private LinearLayout linearLayoutLoginForm;
    private ProgressBar progressBarSignIn;
    private CircleImageView logo;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView signUpTextView;
    private LoginOnClickListener loginOnClickListener;
    private TextView forgotPasswordTextView;

    public LoginB(Context context) {
        super(context);
        initView(null);
    }

    public LoginB(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public LoginB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public LoginB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attributeSet) {
        View view = inflate(getContext(), R.layout.olx_login, null);
        if (attributeSet == null) {
            addView(view);
            return;
        }
        //grab the views
        logo = view.findViewById(R.id.circle_image_olx);
        editTextEmail = view.findViewById(R.id.editTextEmail_olx);
        editTextPassword = view.findViewById(R.id.editTextPassword_olx);
        forgotPasswordTextView = view.findViewById(R.id.textViewForgotPassword_olx);
        signInButton = view.findViewById(R.id.buttonSignIn_olx);
        signUpTextView = view.findViewById(R.id.textViewRegister_olx);
        linearLayoutLoginForm = view.findViewById(R.id.linearLayoutLoginForm_olx);
        progressBarSignIn = view.findViewById(R.id.progressBarSignIn_olx);

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.LoginB);
        Drawable logoDrawable = typedArray.getDrawable(R.styleable.LoginB_logo_b);
        String userIdLabel = typedArray.getString(R.styleable.LoginB_user_id_label_b);
        String signInLabel = typedArray.getString(R.styleable.LoginB_sign_in_label_b);
        String signUpLabel = typedArray.getString(R.styleable.LoginB_sign_up_label_b);

        if (userIdLabel != null && !userIdLabel.equals(""))
            editTextEmail.setHint(userIdLabel);
        else
            editTextEmail.setHint("Email");

        if (signInLabel != null && !signInLabel.equals(""))
            signInButton.setText(signInLabel);

        if (signUpLabel != null && !signUpLabel.equals(""))
            signUpTextView.setText(signUpLabel);

        if (logoDrawable != null)
            logo.setImageDrawable(logoDrawable);
        addView(view);
        typedArray.recycle();
    }

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

    public boolean validateInput() {
        return Utility.validateInput(editTextEmail, editTextPassword, true);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgressBar(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
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
