/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.util;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongStructKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature.ClassMethodSignature;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public final class MJUtils {

    private MJUtils() {
    }

    public static boolean haveSameSignatures(Obj method1, Obj method2) throws WrongObjKindException {
        if (method1 == null || method2 == null) {
            return false;
        }
        return new ClassMethodSignature(method1, MJTab.noType).equals(new ClassMethodSignature(method2, MJTab.noType));
    }

    public static boolean returnTypesAssignmentCompatible(Obj overridingMethod, Obj overriddenMethod)
            throws WrongObjKindException {
        if (overridingMethod.getKind() != Obj.Meth || overriddenMethod.getKind() != Obj.Meth) {
            throw new WrongObjKindException();
        }
        return assignableTo(overridingMethod.getType(), overriddenMethod.getType());
    }

    public static String typeToString(Struct type) {
        switch (type.getKind()) {
            case Struct.Bool:
                return "bool";
            case Struct.Int:
                return "int";
            case Struct.Char:
                return "char";
            case Struct.Array:
                return typeToString(type.getElemType()) + "[]";
            case Struct.Class:
                if (type == MJTab.nullType) {
                    return "null";
                } else {
                    return MJTab.findObjForClass(type).getName();
                }
            case Struct.None:
                return "void";
            default:
                return null;
        }
    }

    public static String getCompactClassMethodSignature(Obj method) throws WrongObjKindException {
        return new ClassMethodSignature(method, MJTab.noType).getCompactSignature();
    }

    public static String getClassMethodDeclaration(Obj method, Struct clss) throws WrongObjKindException {
        return typeToString(method.getType()) + " " + new ClassMethodSignature(method, clss);
    }

    public static boolean assignableTo(Struct source, Struct destination) {
        if (!canSubstitute(source, destination)) {
            return source.assignableTo(destination);
        }
        return true;
    }

    private static boolean canSubstitute(Struct subclass, Struct superclass) {
        if (subclass.getKind() == Struct.Class && superclass.getKind() == Struct.Class) {
            if (subclass == superclass) {
                return true;
            }
            Struct subclass1 = subclass.getElemType();
            while (subclass1 != null) {
                if (subclass1 == superclass) {
                    return true;
                }
                subclass1 = subclass1.getElemType();
            }
        }
        if (subclass.getKind() == Struct.Array && superclass.getKind() == Struct.Array) {
            return canSubstitute(subclass.getElemType(), superclass.getElemType());
        }
        return false;
    }

    public static int sizeOfClassInstance(Struct clss) throws WrongStructKindException {
        if (clss.getKind() != Struct.Class) {
            throw new WrongStructKindException();
        }
        int numberOfFields = 0;
        Struct superclass = clss;
        while (superclass != null) {
            numberOfFields += superclass.getNumberOfFields();
            superclass = superclass.getElemType();
        }
        return numberOfFields * 4;
    }

    public static boolean isPrimitiveDataType(Struct type) {
        return type.equals(MJTab.intType) || type.equals(MJTab.charType) || type.equals(MJTab.BOOL_TYPE);
    }

}
