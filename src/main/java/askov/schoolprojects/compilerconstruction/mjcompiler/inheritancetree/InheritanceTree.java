/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree;

import java.util.HashMap;
import java.util.Map;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongStructKindException;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public class InheritanceTree {

    public static final InheritanceTreeNode ROOT_NODE;

    static {
        InheritanceTreeNode root = null;
        try {
            root = new InheritanceTreeNode(new Obj(Obj.Type, "$RootClassNode", new Struct(Struct.Class)), null);
        } catch (WrongObjKindException | WrongStructKindException e) {
            e.printStackTrace();
        }
        ROOT_NODE = root;
    }

    private static final Map<Obj, InheritanceTreeNode> MAP = new HashMap<>();

    public static boolean addNodeForClass(Obj clss) throws WrongObjKindException, WrongStructKindException {
        if (MAP.containsKey(clss)) {
            return false;
        }
        MAP.put(clss, new InheritanceTreeNode(clss));
        return true;
    }

    public static boolean addNodeForClass(Obj subclass, Obj superclass)
            throws WrongObjKindException, WrongStructKindException {
        if (MAP.containsKey(subclass) || !MAP.containsKey(superclass)) {
            return false;
        }
        MAP.put(subclass, new InheritanceTreeNode(subclass, MAP.get(superclass)));
        return true;
    }

    public static InheritanceTreeNode getNode(Obj clss) throws WrongObjKindException, WrongStructKindException {
        if (clss == null) {
            throw new NullPointerException();
        }
        if (clss.getKind() != Obj.Type) {
            throw new WrongObjKindException();
        }
        if (clss.getType().getKind() != Struct.Class) {
            throw new WrongStructKindException();
        }
        return MAP.get(clss);
    }

}
