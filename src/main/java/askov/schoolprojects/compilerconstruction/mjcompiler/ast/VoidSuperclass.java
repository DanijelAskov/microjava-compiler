// generated with ast extension for cup
// version 0.8
// 7/9/2019 23:58:59


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class VoidSuperclass extends ErrorProneSuperclass {

    public VoidSuperclass () {
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
        buffer.append("VoidSuperclass(\n");

        buffer.append(tab);
        buffer.append(") [VoidSuperclass]");
        return buffer.toString();
    }
}
