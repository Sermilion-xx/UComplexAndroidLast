package org.ucomplex.ucomplex.Modules.RoleInfo;

import org.ucomplex.ucomplex.Common.base.AbstractPresenter;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoItem;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class RoleInfoPresenter extends AbstractPresenter<
        RoleInfoRaw, List<RoleInfoItem>,
        Integer, RoleInfoModel> {

    @Override
    public void loadData(Integer params) {

    }
}
