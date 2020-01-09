// generated with ast extension for cup
// version 0.8
// 9/0/2020 16:10:51


package askov.schoolprojects.compilerconstruction.mjcompiler.ast;

public class ArrayElemAccessDesignatorStart extends DesignatorStart {

    private DesignatorStart DesignatorStart;
    private ArrayElemAcessDesignatorLBracket ArrayElemAcessDesignatorLBracket;
    private Expr Expr;

    public ArrayElemAccessDesignatorStart (DesignatorStart DesignatorStart, ArrayElemAcessDesignatorLBracket ArrayElemAcessDesignatorLBracket, Expr Expr) {
        this.DesignatorStart=DesignatorStart;
        if(DesignatorStart!=null) DesignatorStart.setParent(this);
        this.ArrayElemAcessDesignatorLBracket=ArrayElemAcessDesignatorLBracket;
        if(ArrayElemAcessDesignatorLBracket!=null) ArrayElemAcessDesignatorLBracket.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorStart getDesignatorStart() {
        return DesignatorStart;
    }

    public void setDesignatorStart(DesignatorStart DesignatorStart) {
        this.DesignatorStart=DesignatorStart;
    }

    public ArrayElemAcessDesignatorLBracket getArrayElemAcessDesignatorLBracket() {
        return ArrayElemAcessDesignatorLBracket;
    }

    public void setArrayElemAcessDesignatorLBracket(ArrayElemAcessDesignatorLBracket ArrayElemAcessDesignatorLBracket) {
        this.ArrayElemAcessDesignatorLBracket=ArrayElemAcessDesignatorLBracket;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStart!=null) DesignatorStart.accept(visitor);
        if(ArrayElemAcessDesignatorLBracket!=null) ArrayElemAcessDesignatorLBracket.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStart!=null) DesignatorStart.traverseTopDown(visitor);
        if(ArrayElemAcessDesignatorLBracket!=null) ArrayElemAcessDesignatorLBracket.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStart!=null) DesignatorStart.traverseBottomUp(visitor);
        if(ArrayElemAcessDesignatorLBracket!=null) ArrayElemAcessDesignatorLBracket.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayElemAccessDesignatorStart(\n");

        if(DesignatorStart!=null)
            buffer.append(DesignatorStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayElemAcessDesignatorLBracket!=null)
            buffer.append(ArrayElemAcessDesignatorLBracket.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayElemAccessDesignatorStart]");
        return buffer.toString();
    }
}
