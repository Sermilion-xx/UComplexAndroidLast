package org.ucomplex.ucomplex.Modules.Settings;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.target.Target;

import org.ucomplex.ucomplex.Common.base.BaseMvpFragment;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Modules.Settings.model.Info;
import org.ucomplex.ucomplex.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.ucomplex.ucomplex.Common.base.UCApplication.PHOTOS_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 04/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SettingsProfileFragment extends BaseMvpFragment<SettingsProfilePresenter> {

    public static SettingsProfileFragment getInstance() {
        return new SettingsProfileFragment();
    }

    @BindView(R.id.photo)
    ImageView profilePictures;
    @BindView(R.id.button_change_image)
    FloatingActionButton changeProfileButton;

    @BindView(R.id.password_current)
    TextView passwordCurrent;
    @BindView(R.id.password_new)
    TextView passwordNew;
    @BindView(R.id.password_new_again)
    TextView passwordNewAgain;

    @BindView(R.id.phone_current)
    TextView phoneCurrent;
    @BindView(R.id.phone_new)
    TextView phoneNew;
    @BindView(R.id.phone_password)
    TextView phonePassword;

    @BindView(R.id.email_current)
    TextView emailCurrent;
    @BindView(R.id.email_new)
    TextView emailNew;
    @BindView(R.id.email_password)
    TextView emailPassword;

    @BindView(R.id.privacy_closed_profile)
    Switch closedProfile;
    @BindView(R.id.privacy_hide_profile)
    Switch hiddenProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.loadData(null);
    }

    @Override
    public void dataLoaded() {
        Info info = presenter.getData().getInfo();
        String url = PHOTOS_URL + info.getCode() + ".jpg";
        Glide.with(this)
                .load(url)
                .asBitmap()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .priority(Priority.HIGH)
                .into(profilePictures);
        phoneCurrent.setText(info.getPhone());
        emailCurrent.setText(info.getEmail());
        closedProfile.setChecked(info.getClosed() == 1);
        hiddenProfile.setChecked(info.getSearchable() == 0);
    }

    public void saveSettings() {
        String currpass;
        if (phonePassword.getText().length() == 0) {
            currpass = emailPassword.getText().toString();
        } else {
            currpass = phonePassword.getText().toString();
        }
        String oldpass = passwordCurrent.getText().toString();
        String pass = passwordNew.getText().toString();
        String email = emailNew.getText().toString();
        String phone = phoneNew.getText().toString();
        Integer closed = closedProfile.isChecked() ? 1 : 0;
        Integer searchable = hiddenProfile.isChecked() ? 1 : 0;
        String currentPassword = UCApplication.getInstance().getLoggedUser().getPassword();

        if (currpass.length() > 0 && currpass.equals(currentPassword) || pass.length() > 0) {
            presenter.saveSettings(currpass, oldpass, pass, email, phone, closed, searchable);
        } else {
            showToast(R.string.error_incorrect_password);
        }
    }
}
