/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.util;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

/**
 *
 * @author Danijel Askov
 */
public class Log4JUtils {

    private static Log4JUtils logs = new Log4JUtils();

    public static Log4JUtils instance() {
        return logs;
    }

    public URL findLoggerConfigFile() {
        return Thread.currentThread().getContextClassLoader().getResource("log4j.xml");
    }

    public void prepareLogFile(Logger root) {
        Appender appenderAll = root.getAppender("file_all"), appenderError = root.getAppender("file_err"),
                appenderInfo = root.getAppender("file_info");

        if (!(appenderAll instanceof FileAppender) || !(appenderError instanceof FileAppender)
                || !(appenderInfo instanceof FileAppender)) {
            return;
        }

        FileAppender fAppenderAll = (FileAppender) appenderAll, fAppenderError = (FileAppender) appenderError,
                fAppenderInfo = (FileAppender) appenderInfo;

        String fNameAll = fAppenderAll.getFile(), fNameError = fAppenderError.getFile(),
                fNameInfo = fAppenderInfo.getFile();

        fNameAll = fNameAll.substring(0, fNameAll.lastIndexOf('.'));
        fNameError = fNameError.substring(0, fNameError.lastIndexOf('.'));
        fNameInfo = fNameInfo.substring(0, fNameInfo.lastIndexOf('.'));

        File fAllRenamed = new File(fNameAll + "_" + System.currentTimeMillis() + ".log");
        File fErrorRenamed = new File(fNameError + "_" + System.currentTimeMillis() + ".log");
        File fInfoRenamed = new File(fNameInfo + "_" + System.currentTimeMillis() + ".log");

        fAppenderAll.setFile(fAllRenamed.getAbsolutePath());
        fAppenderAll.activateOptions();
        fAppenderError.setFile(fErrorRenamed.getAbsolutePath());
        fAppenderError.activateOptions();
        fAppenderInfo.setFile(fInfoRenamed.getAbsolutePath());
        fAppenderInfo.activateOptions();
    }

}
