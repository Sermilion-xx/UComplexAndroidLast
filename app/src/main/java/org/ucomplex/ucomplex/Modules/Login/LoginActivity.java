package org.ucomplex.ucomplex.Modules.Login;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.users.UserInterface;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType;
import org.ucomplex.ucomplex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.LOGIN_REQUIRED;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.INVALID_PASSWORD;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.NO_ERROR;
import static org.ucomplex.ucomplex.Modules.Login.model.LoginErrorType.PASSWORD_REQUIRED;

public class LoginActivity extends BaseMVPActivity<MVPView, LoginPresenter> implements View.OnClickListener {

    @BindView(R.id.login)
    EditText mLoginView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.forgot_pass_button)
    Button mForgotButton;
    @BindView(R.id.login_sign_in_button)
    Button mLoginSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        ButterKnife.bind(this);
        mLoginSignInButton.setOnClickListener(this);
        mForgotButton.setOnClickListener(this);
        UserInterface userInterface = UCApplication.getInstance().getLoggedUser();
        if (userInterface != null) {
            startActivity(EventsActivity.creteIntent(this));
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_sign_in_button:
                clickLoginButton();
                break;
            case R.id.forgot_pass_button:
                showRestorePasswordDialog();
                break;
        }
    }

    void clickLoginButton() {
        mLoginView.setError(null);
        mPasswordView.setError(null);
        String login = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();
        LoginParams params = new LoginParams(login, password, this);
        List<LoginErrorType> error = presenter.checkCredentials(params);
        if (error.get(0) == NO_ERROR) {
            presenter.loadData(params);
        } else {
            if (error.contains(PASSWORD_REQUIRED)) {
                mPasswordView.setError(getString(R.string.error_field_required));
            } else if (error.contains(INVALID_PASSWORD)) {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
            }
            if (error.contains(LOGIN_REQUIRED)) {
                mLoginView.setError(getString(R.string.error_field_required));
            }
        }
    }

    public void showRestorePasswordDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_forgot_password, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
                    if (UCApplication.getInstance().isConnectedToInternet()) {
                        final String email = editText.getText().toString();
                        presenter.restorePassword(email);
                    } else {
                        showToast(R.string.error_check_internet, Toast.LENGTH_LONG);
                    }
                })
                .setNegativeButton(getString(R.string.cancel),
                        (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void dataLoaded() {
        finish();
    }
}
