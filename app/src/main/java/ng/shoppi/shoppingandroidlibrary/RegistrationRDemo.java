package ng.shoppi.shoppingandroidlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ng.shoppi.androidfrontend.forms.RegForm;
import ng.shoppi.androidfrontend.listeners.RegistrationOnClickListener;
import ng.shoppi.androidfrontend.models.User;

/**
 * @author Olawale
 */

public class RegistrationRDemo extends AppCompatActivity implements RegistrationOnClickListener {

    private RegForm regForm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.risk_r_demo);
        regForm = (RegForm) findViewById(R.id.reg_form);
        regForm.init(this);
    }

    @Override
    public void onRegisterClick() {
        Toast.makeText(getBaseContext(), "Handle Register Activity!!!", Toast.LENGTH_LONG).show();
        if (regForm.validateInputs()) {
            //grab the inputs
            User user = regForm.getUser();
            Toast.makeText(getBaseContext(), "User's name: " + user.getEmail() + " Gender " + user.getGender(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSignInClick() {
        Toast.makeText(getBaseContext(), "Handle Sign in Activity!!!", Toast.LENGTH_LONG).show();
    }
}
