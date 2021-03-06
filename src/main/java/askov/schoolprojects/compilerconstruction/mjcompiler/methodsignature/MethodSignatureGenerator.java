/*
 * Copyright (C) 2018  Danijel Askov
 *
 * This file is part of MicroJava Compiler.
 *
 * MicroJava Compiler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MicroJava Compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature;

import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ActParsEnd;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ActParsStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IdentDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MemberAccessDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MultipleExprExprList;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.SingleExprExprList;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VisitorAdaptor;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import rs.etf.pp1.symboltable.concepts.Obj;

/**
 *
 * @author Danijel Askov
 */
public class MethodSignatureGenerator extends VisitorAdaptor {

    private MethodSignature methodSignature;
    private int level = -1;

    public MethodSignature getMethodSignature() {
        return methodSignature;
    }

    public void visit(IdentDesignator identDesignator) {
        if (methodSignature == null) {
            Obj identDesignatorObj = identDesignator.obj;
            methodSignature = new GlobalMethodSignature(identDesignatorObj.getName());
        }
    }

    public void visit(MemberAccessDesignator memberAccessDesignator) {
        if (methodSignature == null) {
            Obj identDesignatorObj = memberAccessDesignator.obj;
            methodSignature = new ClassMethodSignature(identDesignatorObj.getName(),
                    memberAccessDesignator.getDesignatorStart().obj.getType());
        }
    }

    public void visit(ActParsStart actParsStart) {
        level++;
    }

    public void visit(ActParsEnd actParsEnd) {
        level--;
    }

    public void visit(MultipleExprExprList multipleExprExprList) {
        if (level == 0) {
            methodSignature.addParameter(multipleExprExprList.getExpr().obj);
            if (multipleExprExprList.getExpr().obj.getType() == MJTab.noType
                    && multipleExprExprList.getExpr().obj.getKind() != Obj.Meth) {
                methodSignature.setContainsUndeclaredType();
            }
        }
    }

    public void visit(SingleExprExprList singleExprExprList) {
        if (level == 0) {
            methodSignature.addParameter(singleExprExprList.getExpr().obj);
            if (singleExprExprList.getExpr().obj.getType() == MJTab.noType
                    && singleExprExprList.getExpr().obj.getKind() != Obj.Meth) {
                methodSignature.setContainsUndeclaredType();
            }
        }
    }

}
