// generated with ast extension for cup
// version 0.8
// 1/1/2023 19:22:28


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class EmptyLocalVarDeclList extends LocalVarDeclList {

    public EmptyLocalVarDeclList () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EmptyLocalVarDeclList(\n");

        buffer.append(tab);
        buffer.append(") [EmptyLocalVarDeclList]");
        return buffer.toString();
    }
}
