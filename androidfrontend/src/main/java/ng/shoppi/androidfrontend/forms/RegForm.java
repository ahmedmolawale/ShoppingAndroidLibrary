package ng.shoppi.androidfrontend.forms;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ng.shoppi.androidfrontend.R;
import ng.shoppi.androidfrontend.listeners.RegistrationOnClickListener;
import ng.shoppi.androidfrontend.models.User;
import ng.shoppi.androidfrontend.util.Utility;

/**
 * Simulation of Risk App login form
 *
 * @author Olawale
 */

public class RegForm extends RelativeLayout {

    private ImageView backgroundImageView;
    private ImageView imageView;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private RadioGroup radioGroupGender;
    private AppCompatSpinner appCompatMaritalStatus;
    private TextView editTextReligion;
    private Button buttonRegister;
    private ProgressBar progressBar;
    private TextView textViewLogin;
    private RelativeLayout relativeLayoutBackgroundColor;
    private LinearLayout linearLayoutRegForm;

    //linear layout for visibility toggle
    private LinearLayout phoneLayout;
    private LinearLayout addressLayout;
    private LinearLayout genderLayout;
    private LinearLayout maritalStatusLayout;
    private LinearLayout religionLayout;

    boolean validateEmail;
    private String maritalStatus = "Unknown";
    private String gender = "Male";

    private RegistrationOnClickListener registrationOnClickListener;


    public RegForm(Context context) {
        super(context);
        initViews(null);
    }

    public RegForm(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public RegForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    public RegForm(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(attrs);
    }

    private void initViews(@Nullable AttributeSet attributeSet) {
        final View view = inflate(getContext(), R.layout.risk_register, null);
        if (attributeSet == null) {
            addView(view);
            return;
        }

        //grab the views
        relativeLayoutBackgroundColor = view.findViewById(R.id.background_color_risk);
        backgroundImageView = view.findViewById(R.id.backgroundImage_register_risk);
        imageView = view.findViewById(R.id.imageViewLogo);
        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        editTextEmail = view.findViewById(R.id.editTextEmail_risk);
        editTextPassword = view.findViewById(R.id.editTextPassword_risk);
        editTextConfirmPassword = view.findViewById(R.id.editTextConfirmPassword_risk);
        editTextPhone = view.findViewById(R.id.editTextPhone_risk);
        editTextAddress = view.findViewById(R.id.editTextAddress_risk);
        radioGroupGender = view.findViewById(R.id.radioGroupGender_risk);
        appCompatMaritalStatus = view.findViewById(R.id.appCompatSpinnerMaritalStatus);
        editTextReligion = view.findViewById(R.id.editTextReligion);
        buttonRegister = view.findViewById(R.id.buttonRegister_risk);
        textViewLogin = view.findViewById(R.id.textViewLogin_risk);
        progressBar = view.findViewById(R.id.progressBarSignIn_risk);
        linearLayoutRegForm = view.findViewById(R.id.linearLayoutRegisterForm);

        //linear layout for visibility toggle
        phoneLayout = view.findViewById(R.id.phone_layout_risk);
        genderLayout = view.findViewById(R.id.gender_layout_risk);
        addressLayout = view.findViewById(R.id.address_layout_risk);
        maritalStatusLayout = view.findViewById(R.id.marital_status_layout_risk);
        religionLayout = view.findViewById(R.id.religion_layout_risk);


        //getting values from the attribute set passed from xml
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.RegForm);
        Drawable backgroundDrawable = typedArray.getDrawable(R.styleable.RegForm_reg_background_image);
        int backgroundColor = typedArray.getColor(R.styleable.RegForm_reg_background_color, getResources().getColor(R.color.purple));
        Drawable logoDrawable = typedArray.getDrawable(R.styleable.RegForm_reg_logo);
        String userIdLabel = typedArray.getString(R.styleable.RegForm_reg_user_id);
        String signInLabel = typedArray.getString(R.styleable.RegForm_reg_sign_in_label);
        String signUpLabel = typedArray.getString(R.styleable.RegForm_reg_sign_up_label);
        boolean genderVisible = typedArray.getBoolean(R.styleable.RegForm_reg_gender_visible, true);
        boolean maritalStatusVisible = typedArray.getBoolean(R.styleable.RegForm_reg_marital_status_visible, true);
        boolean addressVisible = typedArray.getBoolean(R.styleable.RegForm_reg_address_visible, true);
        boolean phoneVisible = typedArray.getBoolean(R.styleable.RegForm_reg_phone_visible, true);
        boolean religionVisible = typedArray.getBoolean(R.styleable.RegForm_reg_religion_visible, true);
        validateEmail = typedArray.getBoolean(R.styleable.RegForm_reg_validate_email, true);

        if (userIdLabel != null && !userIdLabel.equals(""))
            editTextEmail.setHint(userIdLabel);

        if (signInLabel != null && !signInLabel.equals(""))
            textViewLogin.setText(signInLabel);

        if (signUpLabel != null && !signUpLabel.equals(""))
            buttonRegister.setText(signUpLabel);

        if (logoDrawable != null)
            imageView.setImageDrawable(logoDrawable);

        if (backgroundDrawable != null)
            backgroundImageView.setImageDrawable(backgroundDrawable);

        //set the background color
        relativeLayoutBackgroundColor.setBackgroundColor(backgroundColor);

        //toggle visibility
        if (!genderVisible)
            genderLayout.setVisibility(View.GONE);

        if (!maritalStatusVisible)
            maritalStatusLayout.setVisibility(View.GONE);

        if (!addressVisible)
            addressLayout.setVisibility(View.GONE);

        if (!religionVisible)
            religionLayout.setVisibility(View.GONE);

        if (!phoneVisible)
            phoneLayout.setVisibility(View.GONE);

        //set up some listeners
        appCompatMaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maritalStatus = appCompatMaritalStatus.getSelectedItem().toString();
                ((TextView) appCompatMaritalStatus.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton radioButton = view.findViewById(selectedId);
                gender = radioButton.getText().toString();
            }
        });
        addView(view);
        typedArray.recycle();
    }

    /**
     * @return a user object containing the user information i.e firstname, lastname, email and password etc
     */
    public User getUser() {
        User user = new User(editTextFirstName.getText().toString(),
                editTextLastName.getText().toString(), editTextEmail.getText().toString(),
                editTextPassword.getText().toString());
        if (phoneLayout.getVisibility() != View.GONE)
            user.setPhoneNumber(editTextPhone.getText().toString());
        if (addressLayout.getVisibility() != View.GONE)
            user.setAddress(editTextAddress.getText().toString());
        if (genderLayout.getVisibility() != View.GONE)
            user.setGender(gender);
        if (maritalStatusLayout.getVisibility() != View.GONE)
            user.setMaritalStatus(maritalStatus);
        if (religionLayout.getVisibility() != View.GONE)
            user.setReligion(editTextReligion.getText().toString());
        return user;
    }

    /**
     * @return true if the input fields are valid
     */
    public boolean validateInputs() {
        Context context = editTextEmail.getContext();
        // Reset errors.
        editTextFirstName.setError(null);
        editTextLastName.setError(null);
        editTextEmail.setError(null);
        editTextPassword.setError(null);
        editTextConfirmPassword.setError(null);
        editTextAddress.setError(null);
        editTextReligion.setError(null);
        editTextPhone.setError(null);

        // Store values at the time of the registration attempt.
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();
        String religion = editTextReligion.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();


        boolean cancel = false;
        View focusView = null;

        //check for emptiness of first name and lastname
        if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextFirstName;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextLastName;
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
        } else if (validateEmail && !Utility.isEmailValid(email)) {
            editTextEmail.setError(context.getResources().getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            cancel = true;
        }

        //check for phone number
        if (phoneLayout.getVisibility() != View.GONE) {
            if (phone.isEmpty()) {
                editTextPhone.setError(context.getResources().getString(R.string.error_field_required));
                focusView = editTextPhone;
                cancel = true;
            } else if (phone.length() != 11) {
                editTextPhone.setError(context.getResources().getString(R.string.error_phone_incomplete));
                focusView = editTextPhone;
                cancel = true;
            }
        }
        // Check for a religion.
        if (religionLayout.getVisibility() != View.GONE && TextUtils.isEmpty(religion)) {
            editTextReligion.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextReligion;
            cancel = true;
        }
        // Check for a address.
        if (addressLayout.getVisibility() != View.GONE && TextUtils.isEmpty(address)) {
            editTextAddress.setError(context.getResources().getString(R.string.error_field_required));
            focusView = editTextAddress;
            cancel = true;
        }

        if (maritalStatusLayout.getVisibility() != View.GONE && maritalStatus.equals("Unknown")) {
            Toast toast = Toast.makeText(context, "Select Marital Status", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            cancel = true;
        }
        if (cancel) {
            if (focusView != null)
                focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param show a boolean to indicate showing of progress bar
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgressBar(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        linearLayoutRegForm.setVisibility(show ? View.GONE : View.VISIBLE);
        linearLayoutRegForm.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                linearLayoutRegForm.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        progressBar.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void init(Activity activity) {

        registrationOnClickListener = (RegistrationOnClickListener) activity;
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationOnClickListener.onSignInClick();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationOnClickListener.onRegisterClick();
            }
        });
    }
}