package ng.shoppi.shoppingandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ng.shoppi.androidfrontend.LoginA;
import ng.shoppi.androidfrontend.LoginB;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;

public class FormBActivityDemo extends AppCompatActivity implements LoginOnClickListener{

    private LoginB loginB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_b_demo);

        loginB = (LoginB) findViewById(R.id.form_b);
        loginB.init(this);
    }

    @Override
    public void onSignInClick() {
        Toast.makeText(getBaseContext(),"Handle Sign In!!!",Toast.LENGTH_LONG).show();
        if(loginB.validateInput()){
            loginB.showProgressBar(true);
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
