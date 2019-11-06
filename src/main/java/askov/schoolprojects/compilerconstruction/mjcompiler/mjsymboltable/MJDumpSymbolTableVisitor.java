/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

/**
 *
 * @author Danijel Askov
 */
public class MJDumpSymbolTableVisitor extends DumpSymbolTableVisitor {

    private enum ScopeType {
        UNIVERSE, PROGRAM, CLASS, GLOBAL_METHOD, CLASS_METHOD
    }

    private ScopeType currentScopeType = ScopeType.UNIVERSE;
    private boolean newLineEnabled = false;

    public MJDumpSymbolTableVisitor(boolean newLineEnabled) {
        this.newLineEnabled = newLineEnabled;
    }

    @Override
    public void visitObjNode(Obj objToVisit) {
        if (currentScopeType == ScopeType.PROGRAM) {
            output.append(indent.toString());
        }
        switch (objToVisit.getKind()) {
            case Obj.Con:
                if (currentScopeType == ScopeType.PROGRAM) {
                    output.append("[");
                }
                output.append("Con ");
                break;
            case Obj.Var:
                output.append("[Var ");
                break;
            case Obj.Type:
                output.append("Type ");
                if (objToVisit.getType().getKind() == Struct.Class) {
                    currentScopeType = ScopeType.CLASS;
                }
                break;
            case Obj.Meth:
                if (currentScopeType == ScopeType.PROGRAM) {
                    output.append("[Meth ");
                    currentScopeType = ScopeType.GLOBAL_METHOD;
                } else if (currentScopeType == ScopeType.CLASS) {
                    output.append("[Meth ");
                    currentScopeType = ScopeType.CLASS_METHOD;
                } else {
                    output.append("Meth ");
                }
                break;
            case Obj.Fld:
                output.append("[Fld ");
                break;
            case Obj.Prog:
                output.append("Prog ");
                currentScopeType = ScopeType.PROGRAM;
                break;
        }

        output.append(objToVisit.getName());
        if (objToVisit.getKind() != Obj.Elem) {
            output.append(": ");
        }

        if (objToVisit.getKind() == Obj.Type) {
            objToVisit.getType().accept(this);
        } else {
            if (objToVisit.getType().getKind() == Struct.Class) {
                Obj clss = MJTab.findObjForClass(objToVisit.getType());
                if (clss != null) {
                    output.append(clss.getName());
                }
            } else {
                objToVisit.getType().accept(this);
            }
        }

        output.append(", ");
        output.append(objToVisit.getAdr());
        output.append(", ");
        output.append(objToVisit.getLevel());

        if (objToVisit.getKind() == Obj.Meth && !objToVisit.getLocalSymbols().isEmpty()) {
            output.append(" ");
        }

        switch (objToVisit.getKind()) {
            case Obj.Prog:
                output.append("\n");
                nextIndentationLevel();
                break;
        }

        for (Obj o : objToVisit.getLocalSymbols()) {
            o.accept(this);
        }

        switch (objToVisit.getKind()) {
            case Obj.Var:
            case Obj.Fld:
                output.append("]");
                break;
            case Obj.Prog:
                previousIndentationLevel();
                currentScopeType = ScopeType.UNIVERSE;
                break;
            case Obj.Meth:
                if (currentScopeType == ScopeType.CLASS_METHOD) {
                    currentScopeType = ScopeType.CLASS;
                    output.append("]");
                } else if (currentScopeType == ScopeType.GLOBAL_METHOD) {
                    currentScopeType = ScopeType.PROGRAM;
                    output.append("]");
                }
                break;
            case Obj.Type:
                if (objToVisit.getType().getKind() == Struct.Class) {
                    currentScopeType = ScopeType.PROGRAM;
                }
                break;
            case Obj.Con:
                if (currentScopeType == ScopeType.PROGRAM) {
                    output.append("]");
                }
        }

        if (newLineEnabled && (currentScopeType == ScopeType.PROGRAM
                || (currentScopeType == ScopeType.UNIVERSE && objToVisit.getKind() != Obj.Var))) {
            output.append("\n");
        }

    }

    @Override
    public void visitStructNode(Struct structToVisit) {
        switch (structToVisit.getKind()) {
            case Struct.None:
                output.append("notype");
                break;
            case Struct.Int:
                output.append("int");
                break;
            case Struct.Char:
                output.append("char");
                break;
            case Struct.Array:
                output.append("Arr of ");

                switch (structToVisit.getElemType().getKind()) {
                    case Struct.None:
                        output.append("notype");
                        break;
                    case Struct.Int:
                        output.append("int");
                        break;
                    case Struct.Char:
                        output.append("char");
                        break;
                    case Struct.Class:
                        output.append("Class");
                        break;
                    case Struct.Bool:
                        output.append("bool");
                        break;
                }
                break;
            case Struct.Class:
                output.append("Class [");
                for (Obj obj : structToVisit.getMembers()) {
                    obj.accept(this);
                }
                output.append("]");
                break;
            case Struct.Bool:
                output.append("bool");
                break;
        }
    }

    @Override
    public void visitScopeNode(Scope scope) {
        for (Obj o : scope.values()) {
            o.accept(this);
        }
    }

    public void clearOutput() {
        output.setLength(0);
        currentScopeType = ScopeType.UNIVERSE;
    }

}
