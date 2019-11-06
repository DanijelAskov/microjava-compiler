/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.vmt;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTree;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor.InheritanceTreeVisitor;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.symboltable.concepts.Obj;

/**
 *
 * @author Danijel Askov
 */
public class VMTCreator implements InheritanceTreeVisitor {

    private void updateVMTs(askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode node, Obj overriddenMethod) {
        try {
            node.getVMT().add(overriddenMethod);
        } catch (WrongObjKindException e) {
            e.printStackTrace();
        }
        for (InheritanceTreeNode child : node.getChildren()) {
            boolean childVisited = false;
            for (Obj member : child.getClss().getType().getMembers()) {
                if (member.getKind() == Obj.Meth) {
                    try {
                        if (MJUtils.haveSameSignatures(member, overriddenMethod)) {
                            updateVMTs(child, member);
                            childVisited = true;
                            break;
                        }
                    } catch (WrongObjKindException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!childVisited) {
                updateVMTs(child, overriddenMethod);
            }
        }
    }

    @Override
    public void visit(InheritanceTreeNode node) {
        if (!node.equals(InheritanceTree.ROOT_NODE)) {
            for (Obj member : node.getClss().getType().getMembers()) {
                if (member.getKind() == Obj.Meth) {
                    InheritanceTreeNode parent = node.getParent();
                    while (!parent.equals(InheritanceTree.ROOT_NODE)) {
                        boolean overridenMethodFound = false;
                        for (Obj parentMember : parent.getClss().getType().getMembers()) {
                            if (parentMember.getKind() == Obj.Meth) {
                                try {
                                    if (MJUtils.haveSameSignatures(member, parentMember)) {
                                        if (MJUtils.returnTypesAssignmentCompatible(member, parentMember)) {
                                            overridenMethodFound = true;
                                            updateVMTs(parent, parentMember);
                                            break;
                                        }
                                    }
                                } catch (WrongObjKindException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (overridenMethodFound) {
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
            }
        }
    }

}
