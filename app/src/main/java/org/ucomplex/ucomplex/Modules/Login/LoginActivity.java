package org.ucomplex.ucomplex.Modules.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.LoginErrorType;
import org.ucomplex.ucomplex.Modules.Events.EventsActivity;
import org.ucomplex.ucomplex.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static org.ucomplex.ucomplex.Domain.LoginErrorType.EMPTY_EMAIL;
import static org.ucomplex.ucomplex.Domain.LoginErrorType.INVALID_PASSWORD;
import static org.ucomplex.ucomplex.Domain.LoginErrorType.PASSWORD_REQUIRED;

public class LoginActivity extends BaseActivity<MVPView, LoginPresenter> implements View.OnClickListener {

    @BindView(R.id.login)
    private AutoCompleteTextView mLoginView;
    @BindView(R.id.password)
    private EditText mPasswordView;
    @BindView(R.id.forgot_pass_button)
    Button mForgotButton;
    @BindView(R.id.login_sign_in_button)
    Button mLoginSignInButton;

    @Inject
    @Override
    public void setPresenter(@NonNull LoginPresenter presenter) {
        super.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_login);
        setupToolbar(getString(R.string.app_name), R.drawable.ic_menu);
        UCApplication.getInstance().getAppDiComponent().inject(this);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mLoginSignInButton.setOnClickListener(this);
        mForgotButton.setOnClickListener(this);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login_sign_in_button:
                clickLoginButton();
                break;
            case R.id.forgot_pass_button:
                clickForgotButton();
                break;
        }
    }

    void clickLoginButton() {
        mLoginView.setError(null);
        mPasswordView.setError(null);
        String login = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();
        LoginParams params = new LoginParams(login, password, presenter.getActivityContext());
        params.setPassword(password);
        params.setLogin(login);
        presenter.setParams(params);
        List<LoginErrorType> error = presenter.checkCredentials();
        if (error.contains(PASSWORD_REQUIRED)) {
            mPasswordView.setError(getString(R.string.error_field_required));
        } else if (error.contains(INVALID_PASSWORD)) {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
        }
        if (error.contains(EMPTY_EMAIL)) {
            mLoginView.setError(getString(R.string.error_field_required));
        }
    }

    void clickForgotButton() {
        showRestorePasswordDialog();
    }

    public void showRestorePasswordDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_forgot_password, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);
        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
                    if (FacadeCommon.isNetworkConnected(LoginActivity.this)) {
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

    public void onLogin() {
        Intent intent;
        if (presenter.getData().getRoles().size() == 1) {
            intent = EventsActivity.creteIntent(this, presenter.getData());
        } else {
            //TODO: role select activity
            intent = new Intent(this, null);
        }
        startActivity(intent);
        finish();
    }
}
