// generated with ast extension for cup
// version 0.8
// 1/1/2023 19:22:27


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class ErrorFieldDecl2 extends ErrorProneFieldDecl {

    public ErrorFieldDecl2 () {
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
        buffer.append("ErrorFieldDecl2(\n");

        buffer.append(tab);
        buffer.append(") [ErrorFieldDecl2]");
        return buffer.toString();
    }
}
