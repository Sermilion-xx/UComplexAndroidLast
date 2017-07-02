package org.ucomplex.ucomplex.Modules.Settings.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 02/07/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class SettingsRaw {

    private final MyInfo my_info;
    private final Info info;

    public SettingsRaw() {
        this.my_info = null;
        this.info = null;
    }

    public MyInfo getMy_info() {
        return my_info;
    }

    public Info getInfo() {
        return info;
    }
}
