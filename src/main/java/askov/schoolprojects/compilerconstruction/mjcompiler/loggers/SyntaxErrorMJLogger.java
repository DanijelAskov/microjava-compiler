/*
 * Copyright (C) 2018  Danijel Askov
 *
 * This file is part of MicroJava Compiler.
 *
 * MicroJava Compiler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MicroJava Compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
