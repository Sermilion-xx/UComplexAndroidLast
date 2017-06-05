package org.ucomplex.ucomplex.Modules.UserProfile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoActivity;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherActivity;
import org.ucomplex.ucomplex.Modules.UserProfile.model.ProfileRequestType;
import org.ucomplex.ucomplex.R;

public class UserProfileActivity extends BaseMVPActivity<MVPView, UserProfilePresenter> {

    private static final String USER_ID = "USER_ID";

    public static Intent creteIntent(Context context, int id) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

    private UserProfileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentViewWithNavDrawer(R.layout.activity_user_profile);
        setupToolbar(getString(R.string.events), R.drawable.ic_menu);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        setupDrawer();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UserProfileAdapter(onListItemClicked);
        mRecyclerView.setAdapter(mAdapter);
        presenter.loadData(getIntent().getIntExtra(USER_ID, -1));
    }

    private OnListItemClicked<Object, ProfileRequestType> onListItemClicked = (params, type) -> {

        DownloadCallback downloadCallback = new DownloadCallback() {
            @Override
            public void onResponse(Object response) {
            }

            @Override
            public void onError(Throwable t) {
                mAdapter.revertChanges(type);
            }
        };

        switch (type) {
            case PHOTO:
                expandProfile((String) params);
                break;
            case FRIEND:
                presenter.addAsFriend((int) params, downloadCallback);
                break;
            case UNFRIEND:
                presenter.unfriend((int) params, downloadCallback);
                break;
            case BLOCK:
                presenter.block((int) params, downloadCallback);
                break;
            case UNBLOCK:
                presenter.unblock((int) params, downloadCallback);
                break;
            case OPEN_ROLE:
                startActivity(RoleInfoActivity.creteIntent(this, (int) params, presenter.getPersonName()));
                break;
            case OPEN_TEACHER_ROLE:
                startActivity(RoleInfoTeacherActivity.creteIntent(this, (int) params, presenter.getPersonName()));
                break;
        }
    };

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }

    private void expandProfile(String url) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_full_image, null);
        ImageView image = (ImageView) layout.findViewById(R.id.image);
        ProgressBar progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Glide.with(this).load(url).asBitmap()
                .priority(Priority.HIGH).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(image);
        imageDialog.setView(layout);
        imageDialog.create();
        imageDialog.show();
    }
}
