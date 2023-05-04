package com.beautify.qtools.log;

import android.text.TextUtils;
import android.util.Log;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogItem;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.flattener.ClassicFlattener;
import com.elvishew.xlog.flattener.PatternFlattener;
import com.elvishew.xlog.interceptor.AbstractFilterInterceptor;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.FileSizeBackupStrategy;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.ChangelessFileNameGenerator;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生
 */
public class LogUtils {

    private static final String TagPrefix = "LOG";
    public static final int LOG_LEVEL_NONE = 0;     //不输出任和log
    public static final int LOG_LEVEL_DEBUG = 1;    //调试 蓝色
    public static final int LOG_LEVEL_INFO = 2;     //提现 绿色
    public static final int LOG_LEVEL_WARN = 3;     //警告 橙色
    public static final int LOG_LEVEL_ERROR = 4;    //错误 红色
    public static final int LOG_LEVEL_ALL = 5;      //输出所有等级

    /**
     * 允许输出的log日志等级
     * 当出正式版时,把mLogLevel的值改为 LOG_LEVEL_NONE,
     * 就不会输出任何的Log日志了.
     */
    private static int mLogLevel = LOG_LEVEL_ALL;

    /**
     * 给输出的Log等级赋值
     *
     * @param level
     */
    public static void setLogLevel(int level) {
        LogUtils.mLogLevel = level;
    }

    /**
     * 在输出到Logcat时,过滤匿名内部类只需要显示外部类
     * <p>
     * WingApp$2{.java} -> WingApp{.java}
     */
    private static String filtrateInnerClass(String className) {
        if (className.contains("$")) {
            return className.substring(0, className.indexOf('$'));
        }
        return className;
    }

    public static void init(){
        init("MY_TAG","/sdcard/xlog",true);
    }
    public static void init(String tag,boolean saveFiles){
        init(tag,"/sdcard/xlog",saveFiles);
    }
    public static void init(String tag){
        init(tag,"/sdcard/xlog",false);
    }
    public static void init(String tag,String path){
        init(tag,path,true);
    }

    public static void init(String tag,String path,boolean saveFiles){
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(LogLevel.ALL)
                .tag(tag)                                              // 指定 TAG
                .enableStackTrace(3)                            // 允许打印深度为 3 的调用栈信息，默认禁止
                .enableBorder()
                .addInterceptor(new AbstractFilterInterceptor() {
                    @Override
                    protected boolean reject(LogItem log) {
                        if(log.stackTraceInfo != null)
                            log.stackTraceInfo = log.stackTraceInfo.substring(log.stackTraceInfo.indexOf(")")+1);
                        return false;
                    }
                })
                .build();
        Printer androidPrinter = new AndroidPrinter(true);         // 通过 android.util.Log 打印日志的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder(path)                             // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                .backupStrategy(new FileSizeBackupStrategy(1024 * 1024))             // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
                .cleanStrategy(new FileLastModifiedCleanStrategy(7L * 24L * 60L * 60L * 1000L))     // 指定日志文件清除策略，默认为 NeverCleanStrategy()
                .flattener(new ClassicFlattener())
                .build();
        if(saveFiles){
            XLog.init(config,androidPrinter,filePrinter);
        }else{
            XLog.init(config);
        }

    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void d(String content) {
        if (mLogLevel < LOG_LEVEL_DEBUG) return;
        XLog.d(content);
    }

    public static void d(String content, Throwable tr) {
        if (mLogLevel < LOG_LEVEL_DEBUG) return;
        XLog.d( content, tr);
    }

    public static void e(String content) {
        if (mLogLevel < LOG_LEVEL_ERROR) return;
        XLog.e( content);
    }

    public static void e(String content, Throwable tr) {
        if (mLogLevel < LOG_LEVEL_ERROR) return;
        XLog.e(content, tr);
    }

    public static void i(String content) {
        if (mLogLevel < LOG_LEVEL_INFO) return;
        XLog.i(content);
    }

    public static void i(String content, Throwable tr) {
        if (mLogLevel < LOG_LEVEL_INFO) return;
        XLog.i(content, tr);
    }

    public static void v(String content) {
        if (mLogLevel < LOG_LEVEL_NONE) return;
        XLog.v(content);
    }

    public static void v(String content, Throwable tr) {
        if (mLogLevel < LOG_LEVEL_NONE) return;
        XLog.v(content, tr);
    }

    public static void w(String content) {
        if (mLogLevel < LOG_LEVEL_WARN) return;
        XLog.w(content);
    }

    public static void w(String content, Throwable tr) {
        if (mLogLevel < LOG_LEVEL_WARN) return;
        XLog.w(content, tr);
    }

    public static void w(Throwable tr) {
        if (mLogLevel < LOG_LEVEL_WARN) return;
        XLog.w(tr);
    }
}