package cn.example.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author 一枚路过的程序猿
 * @Title: Request对象工具类
 * @date 2021/2/23 15:37
 */
public final class HttpServletRequestUtil {

    /**
     * 获取JSON请求类型的参数
     * @param request
     * @return  返回json串
     */
    public static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toString();

    }

}
