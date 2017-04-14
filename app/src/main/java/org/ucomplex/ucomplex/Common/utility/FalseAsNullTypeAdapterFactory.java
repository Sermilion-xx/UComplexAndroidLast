package org.ucomplex.ucomplex.Common.utility;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;

import static com.google.gson.stream.JsonToken.BOOLEAN;


/**
 * ---------------------------------------------------
 * Created by Sermilion on 05/04/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class FalseAsNullTypeAdapterFactory
        implements TypeAdapterFactory {

    // Let Gson instantiate it itself
    private FalseAsNullTypeAdapterFactory() {
    }

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        // Get a downstream parser (for simplicity: get the default parser for the given type)
        final TypeAdapter<T> delegateTypeAdapter = gson.getDelegateAdapter(this, typeToken);
        return new TypeAdapter<T>() {
            @Override
            public void write(final JsonWriter out, final T value) {
                throw new UnsupportedOperationException();
            }

            @Override
            public T read(final JsonReader in)
                    throws IOException {
                // Peek whether the next JSON token is a boolean
                if (in.peek() == BOOLEAN) {
                    // And take the this JSON token as a boolean value
                    // Is it true?
                    if (in.nextBoolean()) {
                        // Then it's not something we can handle -- probably a boolean field annotated with @JsonAdapter(FalseAsNullTypeAdapterFactory.class)?
                        throw new MalformedJsonException("Unexpected boolean marker: true");
                    }
                    // We're assuming it's null
                    return null;
                }
                // If it's not a boolean value, then we just delegate parsing to the original type adapter
                return delegateTypeAdapter.read(in);
            }
        };
    }

}