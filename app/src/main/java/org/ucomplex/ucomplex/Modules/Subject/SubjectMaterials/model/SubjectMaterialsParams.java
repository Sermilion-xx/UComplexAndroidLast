package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsParams {

    private String folder;
    private String folderName;
    private boolean isFolder;
    private  boolean isMyFolder;

    public boolean isMyFolder() {
        return isMyFolder;
    }

    public void setMyFolder(boolean myFolder) {
        isMyFolder = myFolder;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        isFolder = folder;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
