package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.net.Uri;
import android.support.v4.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.File;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Modules.Portfolio.PortfolioService;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.MaterialsRaw;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectItemFile;
import org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials.model.SubjectMaterialsParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static org.ucomplex.ucomplex.Common.UCApplication.BASE_URL;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 01/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class SubjectMaterialsModel implements MVPModel<MaterialsRaw, List<Pair<List<SubjectItemFile>, String>>, SubjectMaterialsParams> {

    private static final String DEFAULT_FOLDER_NAME = "null";
    private static final String FILES_PATH = "storage.ucomplex.org/files/users/";
    public static final String EXTRA_KEY_MY_MATERIALS = "myMaterials";
    public static final String EXTRA_KEY_FILE = "file";
    public static final String EXTRA_KEY_NAME = "name";
    public static final String FILE_URI = "file_uri";

    private SubjectTeachersMaterialsService subjectTeachersMaterialsService;
    private PortfolioService portfolioService;
    private DownloadFileService downloadService;
    private List<Pair<List<SubjectItemFile> , String>> mPageHistory;
    private Map<Integer, Teacher> mTeachers;
    private int currentPage = -1;
    private String currentFolder = "null";
    private boolean myFiles;
    private String myName;

    void setTeachers(Map<Integer, Teacher> mTeachers) {
        this.mTeachers = mTeachers;
    }

    public SubjectMaterialsModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        mPageHistory = new ArrayList<>();
    }

    //TODO: create only one service
    @Inject
    void setSubjectTeachersMaterialsService(SubjectTeachersMaterialsService service) {
        this.subjectTeachersMaterialsService = service;
    }

    @Inject
    void setPortfolioService(PortfolioService service) {
        this.portfolioService = service;
    }

    @Inject
    void setDownloadService(DownloadFileService service) {
        this.downloadService = service;
    }

    @Override
    public Observable<MaterialsRaw> loadData(SubjectMaterialsParams folder) {
        if (!folder.isMyFolder()) {
            return subjectTeachersMaterialsService.getMaterials(folder.getFolder()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            myFiles = true;
            myName = UCApplication.getInstance().getLoggedUser().getName();
            return portfolioService.getPortfolio().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    Observable<ResponseBody> downloadFile(SubjectMaterialsParams params) {
        String mUrl = "https://" + FILES_PATH + params.getFileName();
        return downloadService.downloadFileWithDynamicUrlSync(mUrl);
    }

    @Override
    public void setData(List<Pair<List<SubjectItemFile>, String>> data) {
        mPageHistory = data;
    }

    @Override
    public void addData(List<Pair<List<SubjectItemFile>, String>> data) {
        mPageHistory.addAll(data);
    }

    @Override
    public void clear() {
        mPageHistory.clear();
    }

    @Override
    public List<Pair<List<SubjectItemFile>, String>> getData() {
        return mPageHistory;
    }

    @Override
    public void processData(MaterialsRaw data) {
        List<SubjectItemFile> list = new ArrayList<>();
        for (File file : data.getFiles()) {
            list.add(extractFileItem(file));
        }
        mPageHistory.add(new Pair<>(list, ""));
    }

    private SubjectItemFile extractFileItem(File file) {
        SubjectItemFile item = new SubjectItemFile();
        item.setAddress(file.getAddress());
        item.setName(file.getName());
        item.setData(file.getData());
        if (!myFiles) {
            item.setOwnersName(mTeachers.get(file.getOwner()).getName());
        } else {
            item.setOwnersName(myName);
        }
        item.setSize(file.getSize());
        item.setType(file.getType());
        item.setTime(file.getTime());
        return item;
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
        if(currentPage<1){
            currentFolder = DEFAULT_FOLDER_NAME;
        }else {
            currentFolder = getHistory(currentPage).second;
        }
    }

    void addHistory(Pair<List<SubjectItemFile>, String> list) {
        this.mPageHistory.add(list);
    }

    int getHistoryCount(){
        return this.mPageHistory.size();
    }

    Pair<List<SubjectItemFile>, String> getHistory(int index) {
        if(index<this.mPageHistory.size()){
            return this.mPageHistory.get(index);
        }else {
            return null;
        }
    }

    void deleteFile(String file) {

    }

    void renameFile(String file, String newName, String prevName) {

    }

    void shareFile(String file) {

    }

    void createFolder(String folderName) {


    }

    void uploadFile(Uri uri) {
    }
}
