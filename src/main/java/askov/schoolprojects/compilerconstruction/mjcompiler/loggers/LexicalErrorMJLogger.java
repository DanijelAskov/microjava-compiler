/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.loggers;

/**
 *
 * @author Danijel Askov
 */
public class LexicalErrorMJLogger extends MJLogger<String> {

    public LexicalErrorMJLogger() {
        super(MJLoggerKind.ERROR_LOGER, "Lexical error");
    }

    @Override
    protected String messageBody(String symbol, Object... context) {
        return "token \"" + symbol + "\" not recognized";
    }

}
