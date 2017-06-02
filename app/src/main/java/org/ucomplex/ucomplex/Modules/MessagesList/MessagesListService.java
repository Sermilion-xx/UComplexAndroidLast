package org.ucomplex.ucomplex.Modules.MessagesList;

import org.ucomplex.ucomplex.Modules.MessagesList.model.MessagesListRaw;
import org.ucomplex.ucomplex.Modules.RoleInfo.model.RoleInfoRaw;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 18/05/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public interface MessagesListService {
    @POST("/user/messages?mobile=1")
    Observable<MessagesListRaw> getMessagesList();
}
