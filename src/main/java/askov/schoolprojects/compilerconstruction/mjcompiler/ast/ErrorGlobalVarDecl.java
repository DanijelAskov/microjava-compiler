// generated with ast extension for cup
// version 0.8
// 8/0/2020 20:7:39


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class ErrorGlobalVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public ErrorGlobalVarDecl () {
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("ErrorGlobalVarDecl(\n");

        buffer.append(tab);
        buffer.append(") [ErrorGlobalVarDecl]");
        return buffer.toString();
    }
}
