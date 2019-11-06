/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.loggers;

import java_cup.runtime.Symbol;

/**
 *
 * @author Danijel Askov
 */
public class SyntaxErrorMJLogger extends MJLogger<Symbol> {

    public enum SyntaxErrorKind {
        INV_GLOBAL_VAR_DECL, INV_CLASS_INHERITANCE, INV_CLASS_FIELD_DECL, INV_FORM_PAR, INV_ASSIGNMENT, INV_IF_STMT_COND, FATAL_ERROR, INV_DECL,
    }

    public SyntaxErrorMJLogger() {
        super(MJLoggerKind.ERROR_LOGER, "Syntax error");
    }

    @Override
    protected String messageBody(Symbol loggedObject, Object... context) {
        SyntaxErrorKind syntaxErrorKind = (SyntaxErrorKind) context[0];
        String message = null;
        switch (syntaxErrorKind) {
            case INV_GLOBAL_VAR_DECL:
                message = "Invalid global variable declaration. Parsing continued";
                break;
            case INV_CLASS_INHERITANCE:
                message = "Invalid class inheritance declaration. Parsing continued";
                break;
            case INV_CLASS_FIELD_DECL:
                message = "Invalid class field declaration. Parsing continued";
                break;
            case INV_FORM_PAR:
                message = "Invalid formal parameter declaration. Parsing continued";
                break;
            case INV_ASSIGNMENT:
                message = "Invalid assignment statement. Parsing continued";
                break;
            case INV_IF_STMT_COND:
                message = "Invalid if-statement condition. Parsing continued";
                break;
            case FATAL_ERROR:
                message = "Fatal syntax error. Parsing aborted";
                break;
            case INV_DECL:
                message = "Invalid declaration. Parsing continued";
                break;
        }
        return message;
    }

}
