package org.ucomplex.ucomplex.Modules.Subject.SubjectMaterials;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.Pair;

import org.ucomplex.ucomplex.Common.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.DownloadCallback;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Domain.Users.MaterialsFile;
import org.ucomplex.ucomplex.Domain.Users.Teacher;
import org.ucomplex.ucomplex.Modules.Portfolio.model.RequestResult;
import org.ucomplex.ucomplex.Modules.Portfolio.model.ShareFileList;
import org.ucomplex.ucomplex.Modules.Portfolio.retrofit.DownloadFileService;
import org.ucomplex.ucomplex.Modules.Portfolio.retrofit.FileService;
import org.ucomplex.ucomplex.Modules.Portfolio.retrofit.PortfolioService;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private static final String FILES_PATH = "/files/users/";
    private static final String PARAM_KEY = "file";

    private SubjectTeachersMaterialsService subjectTeachersMaterialsService;
    private PortfolioService portfolioService;
    private DownloadFileService downloadService;
    private FileService fileService;

    private List<Pair<List<SubjectItemFile>, String>> mPageHistory;
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
            return portfolioService.getPortfolio().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    void downloadFile(SubjectMaterialsParams params, DownloadCallback<Response<ResponseBody>> callback) {
        String mUrl = FILES_PATH + params.getOwnersId() + "/" + params.getFileName();
        Call<ResponseBody> call = downloadService.downloadFileWithDynamicUrlSync(mUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t);
            }
        });
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
    public List<Pair<List<SubjectItemFile>, String>> processData(MaterialsRaw data) {
        List<SubjectItemFile> list = new ArrayList<>();
        for (MaterialsFile materialsFile : data.getFiles()) {
            list.add(extractFileItem(materialsFile));
        }
        mPageHistory.add(new Pair<>(list, ""));
        return mPageHistory;
    }

    public void processDataToCurrentHistory(MaterialsRaw data) {
        for (MaterialsFile materialsFile : data.getFiles()) {
            mPageHistory.get(getHistoryCount()-1).first.add(extractFileItem(materialsFile));
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
        if (currentPage < 1) {
            currentFolder = DEFAULT_FOLDER_NAME;
        } else {
            currentFolder = getHistory(currentPage).second;
        }
    }

    void addHistory(Pair<List<SubjectItemFile>, String> list) {
        this.mPageHistory.add(list);
    }

    int getHistoryCount() {
        return this.mPageHistory.size();
    }

    Pair<List<SubjectItemFile>, String> getHistory(int index) {
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

    void createFolder(String folderName) {


    }

    Observable<MaterialsRaw> uploadFile(Uri uri, Context context) {
        HashMap<String, RequestBody> params = new HashMap<>();
        File myFile = new File(uri.getPath());
        String filePath = myFile.getAbsolutePath();
        File uploadFile = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse(filePath), uploadFile);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(PARAM_KEY, uploadFile.getName(), requestFile);
        return fileService.upload(body, params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
