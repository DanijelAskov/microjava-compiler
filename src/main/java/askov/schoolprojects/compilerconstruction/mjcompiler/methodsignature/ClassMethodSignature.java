/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public class ClassMethodSignature extends MethodSignature {

    private final Struct clss;

    public ClassMethodSignature(Obj method, Struct clss) throws WrongObjKindException {
        super(method, true);
        this.clss = clss;
    }

    public ClassMethodSignature(String name, Struct clss) {
        super(name);
        this.clss = clss;
    }

    public Struct getThisParameterType() {
        return clss;
    }

    @Override
    public String toString() {
        return clss != MJTab.noType ? MJUtils.typeToString(clss) + "." + super.toString() : super.toString();
    }

}
