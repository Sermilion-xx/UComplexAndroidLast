package org.ucomplex.ucomplex.Common.navdrawer;

/**
 * Created by Sermilion on 02/11/2016.
 */


public class DrawerListItem {

    private String title;
    private int icon;
    private String uri;

    public DrawerListItem(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public DrawerListItem(String title, String uri) {
        this.title = title;
        this.uri = uri;
    }

    public String getTitle1() {
        return title;
    }

    public void setTitle1(String title1) {
        this.title = title1;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
