package org.ucomplex.ucomplex.Modules.Events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.ucomplex.ucomplex.Common.base.BaseActivity;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.R;

public class EventsActivity extends BaseActivity<MVPView, EventsPresenter> {

    private static final String EXTRA_KEY_USER = "user";

    public static Intent creteIntent (Context context, UserInterface user) {
        Intent intent = new Intent(context, EventsActivity.class);
        intent.putExtra(EXTRA_KEY_USER, (Parcelable) user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    @NonNull
    @Override
    public EventsPresenter createPresenter() {
        return presenter;
    }
}
