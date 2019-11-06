/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree;

import java.util.ArrayList;
import java.util.List;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongStructKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor.InheritanceTreeVisitor;
import askov.schoolprojects.compilerconstruction.mjcompiler.vmt.VMT;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public class InheritanceTreeNode {

    private final List<InheritanceTreeNode> children = new ArrayList<>();
    private InheritanceTreeNode parent;

    private Obj clss;
    private final VMT vmt = new VMT();

    public InheritanceTreeNode(Obj clss, InheritanceTreeNode parent) throws WrongObjKindException, WrongStructKindException {
        if (clss == null || clss.getType() == null) {
            throw new NullPointerException();
        }
        if (clss.getKind() != Obj.Type) {
            throw new WrongObjKindException();
        }
        if (clss.getType().getKind() != Struct.Class) {
            throw new WrongStructKindException();
        }
        this.parent = parent;
        if (this.parent != null) {
            this.parent.children.add(this);
        }
        this.clss = clss;
    }

    public InheritanceTreeNode(Obj clss) throws WrongObjKindException, WrongStructKindException {
        this(clss, InheritanceTree.ROOT_NODE);
    }

    public Obj getClss() {
        return clss;
    }

    public VMT getVMT() {
        return vmt;
    }

    public InheritanceTreeNode getParent() {
        return parent;
    }

    public List<InheritanceTreeNode> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return children.size() != 0;
    }

    public void accept(InheritanceTreeVisitor inheritanceTreeNodeVisitor) {
        inheritanceTreeNodeVisitor.visit(this);
        for (InheritanceTreeNode child : children) {
            child.accept(inheritanceTreeNodeVisitor);
        }
    }
}
