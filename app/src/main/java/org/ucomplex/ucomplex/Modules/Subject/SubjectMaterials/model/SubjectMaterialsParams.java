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

    private  boolean isMyFolder;
    private int ownersId;
    private String fileAddress;
    private String fileName;
    private String newName;
    private int position;

    private SubjectItemFile file;
    private FileOperationType operationType;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SubjectMaterialsParams(FileOperationType type) {
        this.operationType = type;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public SubjectItemFile getFile() {
        return file;
    }

    public void setFile(SubjectItemFile file) {
        this.file = file;
    }

    public FileOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(FileOperationType operationType) {
        this.operationType = operationType;
    }

    public int getOwnersId() {
        return ownersId;
    }

    public void setOwnersId(int ownersId) {
        this.ownersId = ownersId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public boolean isMyFolder() {
        return isMyFolder;
    }

    public void setMyFolder(boolean myFolder) {
        isMyFolder = myFolder;
    }

}
