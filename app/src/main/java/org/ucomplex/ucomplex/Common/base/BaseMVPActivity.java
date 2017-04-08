package org.ucomplex.ucomplex.Common.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.FragmentFactory;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.ViewExtensions;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPView;
import org.ucomplex.ucomplex.Modules.Login.LoginPresenter;
import org.ucomplex.ucomplex.Modules.Navdrawer.DrawerAdapter;
import org.ucomplex.ucomplex.Modules.Navdrawer.DrawerListItem;
import org.ucomplex.ucomplex.Modules.Navdrawer.FacadeDrawer;
import org.ucomplex.ucomplex.Domain.Users.UserInterface;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseMVPActivity<V extends MVPView, Presenter extends MvpPresenter<V>>
        extends MvpActivity<V, Presenter> implements ViewExtensions, MVPView {

    protected ProgressBar mProgress;
    protected Toolbar mToolbar;
    protected DrawerLayout mDrawer;
    protected String[] mDrawerTitles;
    protected int[] mDrawerIcons;
    protected NavigationView navigationView;
    protected DrawerAdapter mDrawerAdapter;
    protected ActionBarDrawerToggle mActionBarDrawerToggle;

    public abstract void dataLoaded();

    @Inject
    @Override
    public void setPresenter(@NonNull Presenter presenter) {
        super.setPresenter(presenter);
    }

    @NonNull
    @Override
    public Presenter createPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected Toolbar setupToolbar(String title, int... homeAsUpIndicator) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        if (homeAsUpIndicator.length > 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeAsUpIndicator(homeAsUpIndicator[0]);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
        return mToolbar;
    }

    protected Toolbar setupToolbar(String title, Drawable drawable) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(drawable);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        return mToolbar;
    }

    //=================Setup methods================//

    public void setupDrawer() {
        List<DrawerListItem> items = setupDrawerListItems();
        setupDrawerView(items);
    }

    private List<DrawerListItem> setupDrawerListItems() {
        UserInterface user = UCApplication.getInstance().getLoggedUser();
        setupDrawerItemListForUser(user);
        String code = null;
        if (user.getPhoto() == 1) {
            code = user.getCode();
        }
        DrawerListItem headerItem = new DrawerListItem(code, user.getName().split(" ")[1],
                FacadeCommon.getStringUserType(this, user.getType()), user.getPerson());
        return setupDrawerArrayList(headerItem, mDrawerIcons, mDrawerTitles);
    }

    protected void setContentViewWithNavDrawer(int layout) {
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(layout, contentFrameLayout);
    }

    private ArrayList<DrawerListItem> setupDrawerArrayList(DrawerListItem header, int[] icons, String[] titles) {
        ArrayList<DrawerListItem> drawerListItems = new ArrayList<>();
        drawerListItems.add(header);
        for (int i = 0; i < icons.length; i++) {
            drawerListItems.add(new DrawerListItem(icons[i], titles[i]));
        }
        return drawerListItems;
    }



    private void setupDrawerItemListForUser(UserInterface user) {
        Pair<int[], String[]> iconsAndItems;
        if(user!=null) {
            if (user.getType() == 0) {
                iconsAndItems = FacadeDrawer.getInstance(this).getDrawerItemsUser0();
            } else if (user.getType() == 4) {
                iconsAndItems = FacadeDrawer.getInstance(this).getDrawerItemsUser4();
            } else {
                iconsAndItems = FacadeDrawer.getInstance(this).getDrawerItemsUser0();
            }
            mDrawerIcons = iconsAndItems.first;
            mDrawerTitles = iconsAndItems.second;
        }
    }

    private void setupDrawerView(List<DrawerListItem> drawerListItems) {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerAdapter = new DrawerAdapter(drawerListItems, this);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.left_drawer);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mDrawerAdapter);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_closed);
        mDrawer.addDrawerListener(mActionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    mDrawer.closeDrawers();
                    break;
            }
            return false;
        });
        mDrawer.addDrawerListener(mActionBarDrawerToggle);
        mDrawer.post(() -> mActionBarDrawerToggle.syncState());

    }
    //===========================================================================================//

    protected Fragment setupFragment(Bundle inState, String name, int containerId) {
        Fragment fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (inState != null) {
            fragment = manager.getFragment(inState, name);
        } else {
            fragment = FragmentFactory.getFragmentForName(name);
            transaction.add(containerId, fragment, name);
            transaction.commit();
        }
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(int textId, int length) {
        Toast.makeText(this, textId, length).show();
    }

    @Override
    public void showProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public Context getAppContext() {
        return UCApplication.getInstance();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
