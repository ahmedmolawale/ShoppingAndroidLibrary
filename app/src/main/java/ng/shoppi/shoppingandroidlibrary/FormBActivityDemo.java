package ng.shoppi.shoppingandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ng.shoppi.androidfrontend.forms.LoginFormB;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;

public class FormBActivityDemo extends AppCompatActivity implements LoginOnClickListener{

    private LoginFormB loginFormB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_b_demo);

        loginFormB = (LoginFormB) findViewById(R.id.form_b);
        loginFormB.init(this);
    }

    @Override
    public void onSignInClick() {
        Toast.makeText(getBaseContext(),"Handle Sign In!!!",Toast.LENGTH_LONG).show();
        if(loginFormB.validateInput()){
            loginFormB.showProgressBar(true);
        }
    }

    @Override
    public void onSignUpClick() {
        Toast.makeText(getBaseContext(),"Handle Sign Up!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onForgetPasswordClick() {
        Toast.makeText(getBaseContext(),"Handle on forget Password!!!",Toast.LENGTH_LONG).show();
    }
}
