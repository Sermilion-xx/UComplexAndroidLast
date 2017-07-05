package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.net.Uri;
import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.FacadeMedia;
import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Common.utility.FileUtils;
import org.ucomplex.ucomplex.Domain.MaterialsFile;
import org.ucomplex.ucomplex.Domain.role.Role;
import org.ucomplex.ucomplex.Modules.Portfolio.FileService;
import org.ucomplex.ucomplex.Modules.Portfolio.model.RequestResult;
import org.ucomplex.ucomplex.Modules.Portfolio.model.ShareFileList;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsModel implements MVPModel<MaterialsRaw, List<Pair<List<SubjectItemFile>, SubjectMaterialsModel.CurrentFolderNameAndCode>>, SubjectMaterialsParams> {

    public static class CurrentFolderNameAndCode {
        private String name;
        private String code;

        public CurrentFolderNameAndCode(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public CurrentFolderNameAndCode() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    private static final String DEFAULT_FOLDER_NAME = "null";
    private static final String PARAM_KEY = "file";

    private SubjectTeachersMaterialsService subjectTeachersMaterialsService;
    private FileService fileService;

    private List<Pair<List<SubjectItemFile>, CurrentFolderNameAndCode>> mPageHistory;
    private Map<Integer, Role> mTeachers;

    private int currentPage = -1;
    private String currentFolder;
    private String currentFolderCode;
    private boolean myFiles;
    private String myName;

    public String getCurrentFolder() {
        return currentFolder;
    }

    void setTeachers(Map<Integer, Role> mTeachers) {
        this.mTeachers = mTeachers;
    }

    public SubjectMaterialsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        mPageHistory = new ArrayList<>();
    }

    @Inject
    void setSubjectTeachersMaterialsService(SubjectTeachersMaterialsService service) {
        this.subjectTeachersMaterialsService = service;
    }

    @Inject
    void setFileService(FileService service) {
        this.fileService = service;
    }

    @Override
    public Observable<MaterialsRaw> loadData(SubjectMaterialsParams folder) {
        if (!folder.isMyFolder()) {
            return subjectTeachersMaterialsService.getMaterials(folder.getFileAddress()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            myFiles = true;
            myName = UCApplication.getInstance().getLoggedUser().getName();
            return fileService.getPortfolio(folder.getFileAddress()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    @Override
    public void setData(List<Pair<List<SubjectItemFile>, CurrentFolderNameAndCode>> data) {
        mPageHistory = data;
    }

    @Override
    public void addData(List<Pair<List<SubjectItemFile>, CurrentFolderNameAndCode>> data) {
        mPageHistory.addAll(data);
    }

    @Override
    public void clear() {
        mPageHistory.clear();
    }

    @Override
    public List<Pair<List<SubjectItemFile>, CurrentFolderNameAndCode>> getData() {
        return mPageHistory;
    }

    @Override
    public List<Pair<List<SubjectItemFile>, CurrentFolderNameAndCode>> processData(MaterialsRaw data) {
        List<SubjectItemFile> list = new ArrayList<>();
        for (MaterialsFile materialsFile : data.getFiles()) {
            list.add(extractFileItem(materialsFile));
        }
        mPageHistory.add(new Pair<>(list, new CurrentFolderNameAndCode()));
        return mPageHistory;
    }

    public void processDataToCurrentHistory(MaterialsRaw data) {
        for (MaterialsFile materialsFile : data.getFiles()) {
            mPageHistory.get(getHistoryCount() - 1).first.add(extractFileItem(materialsFile));
        }
    }

    private SubjectItemFile extractFileItem(MaterialsFile materialsFile) {
        SubjectItemFile item = new SubjectItemFile();
        item.setAddress(materialsFile.getAddress());
        item.setName(materialsFile.getName());
        item.setData(materialsFile.getData());
        if (!myFiles) {
            item.setOwnersName(mTeachers.get(materialsFile.getOwner()).getName());
        } else {
            item.setOwnersName(myName);
        }
        item.setOwnersId(materialsFile.getOwner());
        item.setSize(materialsFile.getSize());
        item.setType(materialsFile.getType());
        item.setTime(materialsFile.getTime());
        return item;
    }

    public void setCurrentFolderCode(String currentFolderCode) {
        this.currentFolderCode = currentFolderCode;
    }

    public String getCurrentFolderCode() {
        return currentFolderCode;
    }

    void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    int getCurrentPage() {
        return currentPage;
    }

    void pageUp() {
        currentPage++;
    }

    void pageDown() {
        currentPage--;
        mPageHistory.remove(mPageHistory.size() - 1);
        if (currentPage < 1) {
            currentFolder = DEFAULT_FOLDER_NAME;
            currentFolderCode = null;
        } else {
            currentFolder = getHistory(currentPage).second.getName();
            currentFolderCode = getHistory(currentPage).second.getCode();
        }
    }

    void addHistory(Pair<List<SubjectItemFile>, CurrentFolderNameAndCode> list) {
        this.mPageHistory.add(list);
    }

    int getHistoryCount() {
        return this.mPageHistory.size();
    }

    Pair<List<SubjectItemFile>, CurrentFolderNameAndCode> getHistory(int index) {
        if (index > -1 && index < this.mPageHistory.size()) {
            return this.mPageHistory.get(index);
        }
        return null;
    }

    Observable<RequestResult> deleteFile(String file) {
        return fileService.delete(file).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<RequestResult> renameFile(String file, String name) {
        return fileService.rename(file, name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<ShareFileList> shareFile(String file) {
        return fileService.getShareList(file).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<MaterialsRaw> createFolder(String folderName, String folder) {
        return fileService.createFolder(folderName, folder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Observable<MaterialsRaw> uploadFile(Uri uri) {
        HashMap<String, RequestBody> params = new HashMap<>();
        String filePath = FileUtils.getPath(UCApplication.getInstance(), uri);
        if (filePath != null) {
            File uploadFile = new File(filePath);
            RequestBody requestFile = RequestBody.create(MediaType.parse(filePath), uploadFile);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData(PARAM_KEY, uploadFile.getName(), requestFile);
            return fileService.upload(body, params).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return null;
    }
}
