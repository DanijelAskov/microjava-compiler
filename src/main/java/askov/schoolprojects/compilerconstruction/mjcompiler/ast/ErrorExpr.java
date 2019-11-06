// generated with ast extension for cup
// version 0.8
// 7/9/2019 23:59:0


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class ErrorExpr extends ErrorProneExpr {

    public ErrorExpr () {
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
        buffer.append("ErrorExpr(\n");

        buffer.append(tab);
        buffer.append(") [ErrorExpr]");
        return buffer.toString();
    }
}
