package org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank;

import android.support.v4.util.Pair;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import org.ucomplex.ucomplex.Common.base.UCApplication;
import org.ucomplex.ucomplex.Common.interfaces.mvp.MVPModel;
import org.ucomplex.ucomplex.Modules.Login.model.LoginUser;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankItem;
import org.ucomplex.ucomplex.Modules.RoleInfoTeacher.RoleInfoTeacherRank.model.RoleInfoTeacherRankRaw;
import org.ucomplex.ucomplex.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
public class RoleInfoTeacherRankModel implements MVPModel<RoleInfoTeacherRankRaw, List<RoleInfoTeacherRankItem>, Integer> {

    private List<RoleInfoTeacherRankItem> mData;
    private RoleInfoTeacherRankService mService;
    private Map<Integer, Pair<Integer, Integer>> questionHint;

    public RoleInfoTeacherRankModel() {
        UCApplication.getInstance().getAppDiComponent().inject(this);
        questionHint = new HashMap<>();
        questionHint.put(1, new Pair<>(R.string.question1, R.string.questionHint1));
        questionHint.put(2, new Pair<>(R.string.question2, R.string.questionHint2));
        questionHint.put(3, new Pair<>(R.string.question3, R.string.questionHint3));
        questionHint.put(4, new Pair<>(R.string.question4, R.string.questionHint4));
        questionHint.put(5, new Pair<>(R.string.question5, R.string.questionHint5));
        questionHint.put(6, new Pair<>(R.string.question6, R.string.questionHint6));
        questionHint.put(7, new Pair<>(R.string.question7, R.string.questionHint7));
        questionHint.put(8, new Pair<>(R.string.question8, R.string.questionHint8));
        questionHint.put(9, new Pair<>(R.string.question9, R.string.questionHint9));
        questionHint.put(10, new Pair<>(R.string.question10, R.string.questionHint10));

    }

    private RoleInfoTeacherRankModel(boolean ignored) {

    }

    public static RoleInfoTeacherRankModel createTestModel() {
        return new RoleInfoTeacherRankModel(true);
    }

    @Inject
    public void setService(RoleInfoTeacherRankService service) {
        this.mService = service;
    }

    @Override
    public Observable<RoleInfoTeacherRankRaw> loadData(Integer params) {
        return mService.getRank(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void setData(List<RoleInfoTeacherRankItem> data) {
        this.mData = data;
    }

    @Override
    public void addData(List<RoleInfoTeacherRankItem> data) {
        mData.addAll(data);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public List<RoleInfoTeacherRankItem> getData() {
        return mData;
    }

    /**
     * Transfers received data into representation suitable for RoleInfoTeacherRankAdapter.
     * Note: it is feasible to suppress warning because if a key is a numeric string,
     * it is definitely a Map<String, Map<String, Integer>> and otherwise it is Map<String, Integer>
     * with key "count".
     * @param data: data received from server
     * @return list of items for RoleInfoTeacherRankAdapter.
     */
    @Override @SuppressWarnings("unchecked")
    public List<RoleInfoTeacherRankItem> processData(RoleInfoTeacherRankRaw data) {

        mData = new ArrayList<>();
        Map<String, Object> votes = data.getVotes();

        for (int i = 1; i < votes.size(); i++) {
            String key = String.valueOf(i);
            if (votes.containsKey(key)) {
                Map<String, Double> vote = (Map<String, Double>) votes.get(key);
                double rank = 0;
                int count = 0;
                for (int j = 0; j <= 10; j++) {
                    String key2 = String.valueOf(j);
                    if (vote.containsKey(key2)) {
                        double value = vote.get(key2);
                        rank += j * value;
                        count += value;
                    }
                }
                RoleInfoTeacherRankItem item =
                        new RoleInfoTeacherRankItem(questionHint.get(i).first, questionHint.get(i).second, rank/count);
                mData.add(item);
            }
        }
        return mData;
    }

    Observable<LoginUser> rate(SparseIntArray rating) {
        return mService.sendRank(
                rating.get(0),
                rating.get(1),
                rating.get(2),
                rating.get(3),
                rating.get(4),
                rating.get(5),
                rating.get(6),
                rating.get(7),
                rating.get(8),
                rating.get(9)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
