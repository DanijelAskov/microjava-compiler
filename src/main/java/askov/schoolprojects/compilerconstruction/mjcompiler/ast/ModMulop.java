// generated with ast extension for cup
// version 0.8
// 8/0/2020 20:7:40


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class ModMulop extends Mulop {

    public ModMulop () {
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
        buffer.append("ModMulop(\n");

        buffer.append(tab);
        buffer.append(") [ModMulop]");
        return buffer.toString();
    }
}
