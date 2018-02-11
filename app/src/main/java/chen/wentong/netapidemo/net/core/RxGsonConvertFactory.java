package chen.wentong.netapidemo.net.core;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by wentong.chen on 18/2/9.
 * 功能：自定义网络请求requestBody， responseBody转换
 */
public final class RxGsonConvertFactory extends Converter.Factory {
    private final Gson gson;

    /**
     *  生成一个RxGsonConvertFactory
     * @return RxGsonConvertFactory
     */
    public static RxGsonConvertFactory create() {
        return create(new Gson());
    }

    /**
     * 生成一个RxGsonConvertFactory
     * @param gson gosn对象
     * @return RxGsonConvertFactory
     */
    public static RxGsonConvertFactory create(Gson gson) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        return new RxGsonConvertFactory(gson);
    }

    private RxGsonConvertFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new MyGsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new MyGsonRequestBodyConverter<>(gson, adapter);
    }

    /**
     * 请求体转换
     * @param <T> 转换实体对象
     */
    static class MyGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        MyGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
    /**
     * 响应转换
     * @param <T> 转换实体对象
     */
    static class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final Type type;

        MyGsonResponseBodyConverter(Gson gson, Type type) {
            this.gson = gson;
            this.type = type;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            T t = null;
            String value = responseBody.string();
            if (type == String.class) {
                t = (T) value;
            }
            if (type == JSONObject.class) {
                try {
                    t = (T) new JSONObject(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (type == JSONArray.class) {
                try {
                    t = (T) new JSONArray(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (t == null) {
                t = gson.fromJson(value, type);
            }
            return t;
        }
    }
}
