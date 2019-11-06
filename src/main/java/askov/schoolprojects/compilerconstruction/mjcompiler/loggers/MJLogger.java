/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.loggers;

import org.apache.log4j.Logger;

/**
 *
 * @author Danijel Askov
 */
public abstract class MJLogger<T> {

    protected enum MJLoggerKind {
        INFO_LOGGER, ERROR_LOGER
    };

    protected final Logger log = Logger.getLogger(getClass());
    private final MJLoggerKind kind;
    protected final String messageHead;

    public MJLogger(MJLoggerKind kind, String messageHead) {
        this.kind = kind;
        this.messageHead = messageHead;
    }

    protected abstract String messageBody(T loggedObject, Object... context);

    public final void log(T loggedObject, Integer line, Integer column, Object... context) {
        String message = String.format("%-14s", this.messageHead)
                + (line != null
                        ? " (line " + String.format("%3d", line)
                        + (column != null ? ", column " + String.format("%3d", column) : "") + ")"
                        : "")
                + ": " + this.messageBody(loggedObject, context) + ".";
        switch (this.kind) {
            case INFO_LOGGER:
                log.info(message);
                break;
            case ERROR_LOGER:
                log.error(message);
                break;
        }
    }

}
