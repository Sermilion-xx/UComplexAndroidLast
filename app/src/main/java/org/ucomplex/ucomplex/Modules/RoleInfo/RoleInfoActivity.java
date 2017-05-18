package org.ucomplex.ucomplex.Modules.RoleInfo;

import android.os.Bundle;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

public class RoleInfoActivity extends BaseMVPActivity<MVPView, RoleInfoPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_info);
    }
}
