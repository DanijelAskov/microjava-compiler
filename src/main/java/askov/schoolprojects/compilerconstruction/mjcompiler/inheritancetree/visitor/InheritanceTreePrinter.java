/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor;

import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;

public class InheritanceTreePrinter implements InheritanceTreeVisitor {

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void visit(InheritanceTreeNode node) {
        stringBuilder.append("InheritanceTreeNode (" + node.getClss().getName()
                + (node.getParent() != null ? " extends " + node.getParent().getClss().getName() : "") + ") : ");
        stringBuilder.append(node.getVMT() + "\n");
    }

    public String getOutput() {
        return stringBuilder.toString();
    }

}
