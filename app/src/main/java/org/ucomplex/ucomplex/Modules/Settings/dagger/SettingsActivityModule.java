package org.ucomplex.ucomplex.Modules.Settings.dagger;

import org.ucomplex.ucomplex.Modules.Settings.SettingsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 03/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@Module
public class SettingsActivityModule {

    @Provides
    @Singleton
    SettingsPresenter providePresenter() {
        return new SettingsPresenter();
    }
}
