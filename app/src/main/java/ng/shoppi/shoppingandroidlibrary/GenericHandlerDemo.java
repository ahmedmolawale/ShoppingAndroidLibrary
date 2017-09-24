package ng.shoppi.shoppingandroidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ng.shoppi.androidfrontend.genericformhandler.LoginHandler;
import ng.shoppi.androidfrontend.listeners.LoginOnClickListener;

public class GenericHandlerDemo extends AppCompatActivity implements LoginOnClickListener {

    LoginHandler loginHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_form);

        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        Button signIn = (Button) findViewById(R.id.login);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBarSignIn);
        TextView signUp = (TextView) findViewById(R.id.signUp);
        TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        loginHandler = new LoginHandler(email,password,
                signIn,progressBar,signUp,forgotPassword,true);



    }

    @Override
    public void onSignInClick() {
        Toast.makeText(getBaseContext(),"Handle Sign In Activity!!!",Toast.LENGTH_LONG).show();
        if(loginHandler.validateInput()){
            loginHandler.showProgressBar(true);
        }
    }

    @Override
    public void onSignUpClick() {
        Toast.makeText(getBaseContext(),"Handle Sign Up Activity!!!",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onForgetPasswordClick() {
        Toast.makeText(getBaseContext(),"Handle Forgot Activity!!!",Toast.LENGTH_LONG).show();

    }
}
