package org.ucomplex.ucomplex.Modules.MessagesList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.NewMessageBroadcastReceiver;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Messenger.MessengerActivity;
import org.ucomplex.ucomplex.Modules.Users.UsersActivity;
import org.ucomplex.ucomplex.R;

public class MessagesListActivity extends BaseMVPActivity<MVPView, MessagesListPresenter> {

    public static Intent creteIntent(Context context) {
        return new Intent(context, MessagesListActivity.class);
    }

    private MessagesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_messages_list);
        setupToolbar(getString(R.string.messages), R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MessagesListAdapter(object -> {
            startActivity(MessengerActivity.creteIntent(this, object.first, object.second));
            NewMessageBroadcastReceiver.subtractMessageCount();
            mDrawerAdapter.resetNotification();
        });
        mRecyclerView.setAdapter(mAdapter);
        if (presenter.getData() == null) {
            presenter.loadData(null);
        } else {
            dataLoaded();
        }
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.menu_add:
                startActivity(UsersActivity.creteIntent(this));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            presenter.clear();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}