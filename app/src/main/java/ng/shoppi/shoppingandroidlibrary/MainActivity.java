package ng.shoppi.shoppingandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import ng.shoppi.androidfrontend.LoginA;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;
import ng.shoppi.androidfrontend.onboarding.OnboardingListener;
import ng.shoppi.androidfrontend.onboarding.OnboardingViews;

public class MainActivity extends AppCompatActivity implements LoginOnClickListener, OnboardingListener {

    private LoginA loginA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_a_demo);
        loginA = (LoginA) findViewById(R.id.form_a);
        loginA.init(this);
    }

    @Override
    public void onSignInClick() {
        Toast.makeText(getBaseContext(),"Handle Sign In Activity!!!",Toast.LENGTH_LONG).show();
        if(loginA.validateInput()){
             OnboardingViews.init(this, new int[]{R.layout.default_onboarding_fragment,R.layout.default_onboarding_fragment,
                    R.layout.default_onboarding_fragment,R.layout.default_onboarding_fragment,R.layout.default_onboarding_fragment,
                    R.layout.default_onboarding_fragment,R.layout.default_onboarding_fragment});
        }
    }

    @Override
    public void onSignUpClick() {
        Toast.makeText(getBaseContext(),"Trigger Sign Up Activity!!!",Toast.LENGTH_LONG).show();
        //Testing the Recycler View
       startActivity(new Intent(MainActivity.this, RecylcerListDemo.class));
    }

    @Override
    public void onForgetPasswordClick() {
        Toast.makeText(getBaseContext(),"Handle Forgot Password!!!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSkipClick() {
        Toast.makeText(getBaseContext(),"Handle Skip Password!!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onReady() {
        Toast.makeText(getBaseContext(),"Handle Ready Password!!!",Toast.LENGTH_LONG).show();

    }
}
