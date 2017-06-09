package org.ucomplex.ucomplex.Modules.Messenger;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import org.ucomplex.ucomplex.Common.base.BaseMVPActivity;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class MessengerActivity extends BaseMVPActivity<MVPView, MessengerPresenter> {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_COMPANION = "EXTRA_COMPANION";

    public static Intent creteIntent(Context context, String name, int  companion) {
        Intent intent = new Intent(context, MessengerActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_COMPANION, companion);
        return intent;
    }

    private MessengerAdapter mAdapter;
    private int myId;
    @BindView(R.id.progressBarLoading)
    protected ProgressBar progressBarLoading;
    @BindView(R.id.cancelSending)
    protected ImageView cancelSending;
    @BindView(R.id.messages_text)
    protected EditText messageText;
    @BindView(R.id.send_button)
    protected Button sendButton;
    @BindView(R.id.message_image_temp)
    protected ImageView sendingImage;

    private List<Uri> filesToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        ButterKnife.bind(this);
        myId = UCApplication.getInstance().getLoggedUser().getId();
        filesToSend = new ArrayList<>();
        Intent intent = getIntent();
        String companionName = intent.getStringExtra(EXTRA_NAME);
        setupToolbar(companionName,R.drawable.ic_arrow_back_white);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MessengerAdapter(myId,
                companionName,
                (address, name) -> presenter.downloadFile(name, myId, address));
        mRecyclerView.setAdapter(mAdapter);
        presenter.loadData(intent.getIntExtra(EXTRA_COMPANION, -1));
        sendButton.setEnabled(false);
        messageText.addTextChangedListener(buttonStateChanger);
        sendButton.setOnClickListener(v -> {
            String message = messageText.getText().toString();
            if (message.length() == 0 && filesToSend.size() > 0) {
                message = getString(R.string.file) + ":" + filesToSend.get(filesToSend.size() - 1).getLastPathSegment();
            }
            presenter.sendMessage(message, filesToSend);
        });
    }

    @Override
    public void dataLoaded() {
        mAdapter.setItems(presenter.getData());
        mAdapter.notifyDataSetChanged();
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
            String message = messageText.getText().toString();
            if (message.length() > 0 || filesToSend.size() > 0) {
                sendButton.setEnabled(true);
            } else {
                sendButton.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            setButtonState();
        }
    };
}