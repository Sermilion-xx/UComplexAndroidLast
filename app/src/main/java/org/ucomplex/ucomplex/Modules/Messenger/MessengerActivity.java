package org.ucomplex.ucomplex.Modules.Messenger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.OnListItemClicked;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Messenger.model.MessengerItem;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessengerActivity extends BaseMVPActivity<MVPView, MessengerPresenter> {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_COMPANION = "EXTRA_COMPANION";
    private static final int REQUEST_CODE_PICK_FILE = 100;

    public static Intent creteIntent(Context context, String name, int companion) {
        Intent intent = new Intent(context, MessengerActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_COMPANION, companion);
        return intent;
    }

    private MessengerAdapter mAdapter;
    private int myId;
    @BindView(R.id.progressBarLoading)
    protected ProgressBar mProgressBarLoading;
    @BindView(R.id.cancelSending)
    protected ImageView mCancelSending;
    @BindView(R.id.messages_text)
    protected EditText mMessageText;
    @BindView(R.id.send_button)
    protected Button mSendButton;
    @BindView(R.id.buttonAddFile)
    protected Button mButtonAddFile;
    @BindView(R.id.message_image_temp)
    protected ImageView mSendingImage;
    @BindView(R.id.filesRecyclerView)
    protected RecyclerView mFileRecyclerView;

    private List<Uri> mFilesToSend;
    private int mCompanion;
    private MessengerAddFileAdapterAdapter mFileAdapterAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        myId = UCApplication.getInstance().getLoggedUser().getId();
        mFilesToSend = new ArrayList<>();
        Intent intent = getIntent();
        String companionName = intent.getStringExtra(EXTRA_NAME);
        mCompanion = intent.getIntExtra(EXTRA_COMPANION, -1);
        setupToolbar(companionName, R.drawable.ic_arrow_back_white);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MessengerAdapter(myId,
                companionName,
                (address, name) -> {
                    if (address == null) {
                        presenter.cancelMessage();
                    } else {
                        presenter.downloadFile(name, myId, address);
                    }
                } );
        mRecyclerView.setAdapter(mAdapter);

        presenter.loadData(mCompanion);

        mSendButton.setEnabled(false);
        mMessageText.addTextChangedListener(buttonStateChanger);

        mSendButton.setOnClickListener(v -> {
            String message = mMessageText.getText().toString();
            presenter.sendMessage(message, mCompanion, mFilesToSend, this);
            mAdapter.setItems(presenter.getData());
            mAdapter.notifyItemInserted(0);
            mLayoutManager.scrollToPosition(0);
        });

        mButtonAddFile.setOnClickListener(v -> {
            openFilePicker();
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        mFileRecyclerView.setLayoutManager(layoutManager);
        mFileAdapterAdapter = new MessengerAddFileAdapterAdapter(new OnListItemClicked<Integer, Void>() {
            @Override
            public void onClick(Integer params, Void type) {
                mFilesToSend.remove((int)params);
                if (mFilesToSend.size() == 0) {
                    mFileRecyclerView.setVisibility(View.GONE);
                    mSendButton.setEnabled(false);
                }
            }
        });
        mFileAdapterAdapter.setItems(mFilesToSend);
        mFileRecyclerView.setAdapter(mFileAdapterAdapter);
    }

    public void resetMessegeView() {
        mMessageText.setText("");
        mFilesToSend.clear();
        mFileAdapterAdapter.setItems(mFilesToSend);
        mFileAdapterAdapter.notifyDataSetChanged();
    }

    private void openFilePicker() {
        Intent mediaIntent = new Intent(Intent.ACTION_GET_CONTENT);
        mediaIntent.setType("*/*");
        if (Build.VERSION.SDK_INT < 19) {
            mediaIntent.setAction(Intent.ACTION_GET_CONTENT);
            mediaIntent = Intent.createChooser(mediaIntent, "Select file");
        } else {
            mediaIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            mediaIntent.addCategory(Intent.CATEGORY_OPENABLE);
            String[] mimetypes = {"*/*"};
            mediaIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        }
        startActivityForResult(mediaIntent, REQUEST_CODE_PICK_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_FILE
                && resultCode == Activity.RESULT_OK) {
            Uri file = data.getData();
            if (!mFilesToSend.contains(file)) {
                mFilesToSend.add(0, file);
                mFileRecyclerView.setVisibility(View.VISIBLE);
                mFileAdapterAdapter.notifyItemInserted(0);
            }
            if (!mSendButton.isEnabled() && mFilesToSend.size() > 0) {
                mSendButton.setEnabled(true);
            }
        }
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
    }

    public void updateMessageList() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyItemChanged(0);
        mLayoutManager.scrollToPosition(0);
    }

    TextWatcher buttonStateChanger = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            setButtonState();
        }

        private void setButtonState() {
            String message = mMessageText.getText().toString();
            if (message.length() > 0 || mFilesToSend.size() > 0) {
                mSendButton.setEnabled(true);
            } else {
                mSendButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            setButtonState();
        }
    };
}