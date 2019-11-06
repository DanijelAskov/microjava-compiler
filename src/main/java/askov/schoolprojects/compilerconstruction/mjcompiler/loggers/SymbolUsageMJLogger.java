/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.loggers;

import askov.schoolprojects.compilerconstruction.mjcompiler.SemanticAnalyzer;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJDumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public final class SymbolUsageMJLogger extends MJLogger<Obj> {

    private final MJDumpSymbolTableVisitor symbolTableVisitor = new MJDumpSymbolTableVisitor(false);

    public SymbolUsageMJLogger() {
        super(MJLoggerKind.INFO_LOGGER, "Symbol usage");
    }

    @Override
    protected String messageBody(Obj obj, Object... context) {
        String message = null;

        symbolTableVisitor.clearOutput();
        obj.accept(symbolTableVisitor);
        switch (obj.getKind()) {
            case Obj.NO_VALUE:
                message = "undeclared symbol \"" + obj.getName() + "\"";
                break;
            case Obj.Con:
                message = "symbolic constant \"" + obj.getName() + "\"";
                break;
            case Obj.Var: {
                String scalarOrVector = obj.getType().getKind() == Struct.Array ? "vector" : "scalar";
                switch (obj.getLevel()) {
                    case 0:
                        message = "global " + scalarOrVector + " variable \"" + obj.getName() + "\"";
                        break;
                    case 1:
                        boolean thisParameter = obj.getName().equals(SemanticAnalyzer.THIS);
                        if (obj.getAdr() < ((Obj) context[0]).getLevel()) {
                            message = (thisParameter ? "implicit " : "") + scalarOrVector + " formal parameter \""
                                    + obj.getName() + "\"";
                        } else {
                            message = "local " + scalarOrVector + " variable \"" + obj.getName() + "\"";
                        }
                        break;
                }
            }
            break;
            case Obj.Meth:
                boolean globalMethod = true;
                for (Obj formalPar : obj.getLocalSymbols()) {
                    if (formalPar.getName().equals(SemanticAnalyzer.THIS)) {
                        globalMethod = false;
                        break;
                    }
                }
                if (globalMethod) {
                    message = "global method \"" + obj.getName() + "\" invocation";
                } else {
                    message = "inner class method \"" + obj.getName() + "\" invocation";
                }
                break;
            case Obj.Fld:
                String scalarOrVector = obj.getType().getKind() == Struct.Array ? "vector" : "scalar";
                message = scalarOrVector + " inner class field \"" + obj.getName() + "\" access";
                break;
            case Obj.Elem:
                message = "vector \"" + ((Obj) context[0]).getName() + "\" element access";
                break;
            case Obj.Type:
                message = "inner class \"" + obj.getName() + "\" instantiation";
        }

        return message + ", " + symbolTableVisitor.getOutput();
    }

}
