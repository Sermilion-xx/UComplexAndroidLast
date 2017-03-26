package org.ucomplex.ucomplex.Modules.RoleSelect;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 26/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleItem{

    private int roleIcon;
    private String roleName;

    public RoleItem(int iconId, String name){
        this.roleIcon = iconId;
        this.roleName = name;
    }

    public RoleItem() {
    }

    public int getRoleIcon() {
        return roleIcon;
    }

    public void setRoleIcon(int roleIcon) {
        this.roleIcon = roleIcon;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}


