package org.ucomplex.ucomplex.Modules.Events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

import javax.inject.Inject;

public class EventsActivity extends BaseActivity<MVPView, EventsPresenter> {

    @Inject
    @Override
    public void setPresenter(@NonNull EventsPresenter presenter) {
        super.setPresenter(presenter);
    }

    public static Intent creteIntent (Context context) {
        Intent intent = new Intent(context, EventsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_events);
        setupToolbar(getString(R.string.events), R.drawable.ic_menu);
        setupDrawer();
    }

    @NonNull
    @Override
    public EventsPresenter createPresenter() {
        return presenter;
    }
}
