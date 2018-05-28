package ro.irian.well.well_delivery.view.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.irian.well.well_delivery.R;
import ro.irian.well.well_delivery.di.Injectable;
import ro.irian.well.well_delivery.view.main.MainActivity;
import ro.irian.well.well_delivery.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements Injectable {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.email)
    TextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    View focusView;
    String email, password;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    LoginViewModel loginViewModel;

    @Inject
    EventBus eventbus;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);


    }

    /**
     * Attempts to sign in the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.email_sign_in_button)
    void attemptLogin() {

        boolean cancel = checkErrors();

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            this.loginViewModel.login(email, password).observe(this, user -> {
                if (user != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uid", user.getId());
                    editor.commit();

                    loginViewModel.getDriverLiveData(user.getId()).observe(this, observer -> {

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    });
                } else {
                    Log.w(TAG, "signInWithEmail:failure");
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean checkErrors() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        boolean cancel = false;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        return cancel;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}

