/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.visitor;

import java.util.ArrayList;
import java.util.List;

import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;

public class LeafNodeVisitor implements InheritanceTreeVisitor {

    public List<InheritanceTreeNode> leafNodes = new ArrayList<>();

    @Override
    public void visit(InheritanceTreeNode node) {
        if (!node.hasChildren()) {
            leafNodes.add(node);
        }
    }

    public List<InheritanceTreeNode> getLeafNodes() {
        return leafNodes;
    }

}
