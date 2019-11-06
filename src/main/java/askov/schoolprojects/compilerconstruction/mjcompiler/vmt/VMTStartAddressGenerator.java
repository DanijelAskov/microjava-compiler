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
public class VMTStartAddressGenerator implements InheritanceTreeVisitor {

    private final int firstVMTStartAddress;
    private int currentVMTStartAddress;

    public VMTStartAddressGenerator(int firstVMTStartAddress) {
        this.firstVMTStartAddress = currentVMTStartAddress = firstVMTStartAddress;
    }

    @Override
    public void visit(InheritanceTreeNode node) {
        VMT vmt = node.getVMT();
        vmt.setStartAddress(currentVMTStartAddress);
        node.getClss().setAdr(currentVMTStartAddress);
        currentVMTStartAddress += vmt.getSize();
    }

    public int getTotalVMTSize() {
        return currentVMTStartAddress - firstVMTStartAddress;
    }

}
