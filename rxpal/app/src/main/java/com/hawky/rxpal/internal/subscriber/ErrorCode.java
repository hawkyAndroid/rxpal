package com.hawky.rxpal.internal.subscriber;

/**
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.0
 */
public class ErrorCode {

    /**
     * 服务器繁忙
     */
    public static final int ERROR_BUSY = -100;
    /**
     * 网络连接异常
     */
    public static final int ERROR_CONNECT = -101;
    /**
     * 请求超时
     */
    public static final int ERROR_SOCKET_TIMEOUT = -102;
    /**
     * 解析错误
     */
    public static final int ERROR_PARSE = -103;
    /**
     * 文件下载失败
     */
    public static final int ERROR_DOWNLOAD_FAILED = -104;
    /**
     * 文件上传失败
     */
    public static final int ERROR_UPLOAD_FAILED = -105;
    /**
     * 文件路径不存在
     */
    public static final int ERROR_FILE_NOT_EXIST = -106;
    
}
