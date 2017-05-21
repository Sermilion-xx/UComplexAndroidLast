package org.ucomplex.ucomplex;

import com.google.gson.Gson;

import org.junit.Test;
import org.ucomplex.ucomplex.Common.FacadeCommon;
import org.ucomplex.ucomplex.Modules.RoleInfo.RoleInfoModel;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class UserInfoTests {

    private static final String ROLE_INFO_RAW_JSON = "role_info.json";

    private RoleInfoRaw jsonToRaw(String fileName) {
        String json = FacadeCommon.readFile(fileName);
        Gson gson = new Gson();
        return gson.fromJson(json, RoleInfoRaw.class);
    }

    @Test
    public void jsonStringToSubjectRaw() {
        RoleInfoRaw subjectRaw = jsonToRaw(ROLE_INFO_RAW_JSON);
        assertThat("RoleInfoRaw must not be null", subjectRaw != null);
    }

    @Test
    public void rawToSubjectItems() {
        RoleInfoModel model = RoleInfoModel.createTestModel();
        model.processData(jsonToRaw(ROLE_INFO_RAW_JSON));
        assertThat("Too few items. Must be: 12.", model.getData().size() == 12);
    }

}
