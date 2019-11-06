/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import rs.etf.pp1.symboltable.concepts.Obj;

/**
 *
 * @author Danijel Askov
 */
public class GlobalMethodSignature extends MethodSignature {

    public GlobalMethodSignature(Obj method) throws WrongObjKindException {
        super(method, false);
    }

    public GlobalMethodSignature(String name) {
        super(name);
    }

}
