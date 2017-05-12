package pers.zb.wechat.rpc.api.wxinf.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * 
 * @author FangYing
 * @CreateDate: 2013年11月7日 下午5:40:07
 *
 */
@SuppressWarnings("unchecked")
public class JsonUtils {

    private static final ObjectMapper mapper;

    private JsonUtils() {
    }

    static {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static final String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    public static final <T> T fromJson(final String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final <T> T fromJson(final String json, JavaType type) {
        try {
            return mapper.readValue(json, type);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final <T> T fromJson(final String json,
            TypeReference<T> reference) {
        try {
            return mapper.readValue(json, reference);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final Map<String, Object> fromJson(final String json) {
        return fromJson(json, Map.class);
    }

}
