// generated with ast extension for cup
// version 0.8
// 8/0/2020 20:7:40


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class IdentDesignator extends Designator {

    private String ident;

    public IdentDesignator (String ident) {
        this.ident=ident;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
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
        buffer.append("IdentDesignator(\n");

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentDesignator]");
        return buffer.toString();
    }
}
