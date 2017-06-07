package org.ucomplex.ucomplex.Modules.FullscreenImageView;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;


public class FullscreenViewActivity extends BaseMVPActivity<MVPView, FullscreenViewPresenter> {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_TIME = "time";
    public static final String EXTRA_URI = "uri";
    private static final int FADE_DURATION = 300;

    public static Intent creteIntent(Context context, String name, String time, String uri) {
        Intent intent = new Intent(context, FullscreenViewActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_TIME, time);
        intent.putExtra(EXTRA_URI, uri);
        return intent;
    }

    private ImageView image;
    private Toolbar toolbar;
    private ViewGroup bottomLayout;
    private ViewGroup parentLayout;
    private Uri imageUri;
    private boolean saved = false;
    private boolean topBotVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);
        toolbar = setupToolbar("", R.drawable.ic_arrow_back_white);
        TextView name = (TextView) findViewById(R.id.name);
        TextView time = (TextView) findViewById(R.id.time);
        ImageView download = (ImageView) findViewById(R.id.download);
        bottomLayout = (ViewGroup) findViewById(R.id.bottom_layout);
        parentLayout = (ViewGroup) findViewById(R.id.parent_layout);
        this.image = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra(EXTRA_NAME));
        time.setText(intent.getStringExtra(EXTRA_TIME));
        this.imageUri = Uri.parse(intent.getStringExtra(EXTRA_URI));
        presenter.loadData(this.imageUri);
        download.setOnClickListener(v -> {
            saved = true;
            showToast(R.string.image_saved);
        });
        this.image.setOnClickListener(v -> {
            topBotVisible = !topBotVisible;
            hideShowTopBot(topBotVisible);
        });
    }

    private void hideShowTopBot(boolean topBotVisible) {
        if (topBotVisible) {
            toolbar.animate().alpha(1.0f).setDuration(FADE_DURATION);
            bottomLayout.animate().alpha(1.0f).setDuration(FADE_DURATION);
            parentLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        } else {
            toolbar.animate().alpha(0.0f).setDuration(100);
            bottomLayout.animate().alpha(0.0f).setDuration(100);
            parentLayout.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        }
    }

    @Override
    public void dataLoaded() {
        this.image.setImageBitmap(presenter.getData());
    }

    @Override
    public void onBackPressed() {
        if (!saved) {
            deleteUnsavedImage();
        }
        super.onBackPressed();
    }

    private void deleteUnsavedImage() {
        String[] projection = {MediaStore.Images.Media._ID};
        ContentResolver contentResolver = getContentResolver();
        Cursor c = contentResolver.query(imageUri, projection, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                long id = c.getLong(c.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                Uri deleteUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                contentResolver.delete(deleteUri, null, null);
            }
            c.close();
        }
    }
}
