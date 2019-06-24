package com.itsu.app.utils;

import com.itsu.app.shrio.MySimpleByteSource;
import org.apache.shiro.util.ByteSource;

/**
 * @author 苏犇
 * @date 2019/6/24 21:39
 */

public class ByteSourceUtil {
    public static ByteSource bytes(byte[] bytes) {
        return new MySimpleByteSource(bytes);
    }

    public static ByteSource bytes(String arg0) {
        return new MySimpleByteSource(arg0.getBytes());
    }

}
