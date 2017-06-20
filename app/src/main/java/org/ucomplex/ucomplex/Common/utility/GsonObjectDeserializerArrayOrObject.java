package org.ucomplex.ucomplex.Common.utility;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 20/06/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class GsonObjectDeserializerArrayOrObject<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        T result = null;
        if (json.isJsonObject()) {
            result = gson.fromJson(json.getAsJsonObject(), typeOfT);
        }
        return result;
    }
}