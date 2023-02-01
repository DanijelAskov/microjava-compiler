// generated with ast extension for cup
// version 0.8
// 1/1/2023 19:22:28


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class BreakStatement extends Statement {

    private String dummy;

    public BreakStatement (String dummy) {
        this.dummy=dummy;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy=dummy;
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
        buffer.append("BreakStatement(\n");

        buffer.append(" "+tab+dummy);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BreakStatement]");
        return buffer.toString();
    }
}
