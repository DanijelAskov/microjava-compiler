/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.vmt;

import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor.InheritanceTreeVisitor;

/**
 *
 * @author Danijel Askov
 */
public class VMTCodeGenerator implements InheritanceTreeVisitor {

    @Override
    public void visit(InheritanceTreeNode node) {
        node.getVMT().generateCreationCode();
    }

}
