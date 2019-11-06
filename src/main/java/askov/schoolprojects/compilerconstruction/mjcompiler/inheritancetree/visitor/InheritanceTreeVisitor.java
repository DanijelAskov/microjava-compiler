/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor;

import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;

public interface InheritanceTreeVisitor {

    public void visit(InheritanceTreeNode node);

}
