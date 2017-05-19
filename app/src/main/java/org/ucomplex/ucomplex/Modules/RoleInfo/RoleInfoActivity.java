package org.ucomplex.ucomplex.Modules.RoleInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.UserProfile.UserProfileActivity;
import org.ucomplex.ucomplex.R;

public class RoleInfoActivity extends BaseMVPActivity<MVPView, RoleInfoPresenter> {

    public static final String ROLE_ID = "roleId";

    public static Intent creteIntent(Context context, int roleId) {
        Intent intent = new Intent(context, RoleInfoActivity.class);
        intent.putExtra(ROLE_ID, roleId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_info);
    }
}
