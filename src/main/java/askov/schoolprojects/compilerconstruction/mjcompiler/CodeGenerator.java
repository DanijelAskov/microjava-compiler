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

package askov.schoolprojects.compilerconstruction.mjcompiler;

import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VisitorAdaptor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.PrintExprStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Mulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IdentDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReadStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodDecl;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IfThenStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodCallDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ExprCondFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.BreakStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.PlusAddop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ContinueStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NewScalarFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.LtRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DecrDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Designator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ConditionEnd;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MulopTerm;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MemberAccessDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DivMulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.LeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.AssignmentDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodCallFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IfThenElseStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReturnExprStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DoWhileStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.EqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.RelOpCondFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ClassDecl;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.GeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ConditionStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ArrayElemAccessDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ArrayElemAcessDesignatorLBracket;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.OrCondition;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DesignatorFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.GtRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.TermCondition;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodName;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.BoolFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IncrDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MinusTermExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.CharFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ArrayElemAccessDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IntFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DoWhileStatementStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ActParsEnd;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.TimesMulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ClassName;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.SyntaxNode;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NewVectorFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.AddopExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.PrintExprIntConstStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Else;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IdentDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MemberAccessDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReturnNothingStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongStructKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTree;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTreeNode;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public class CodeGenerator extends VisitorAdaptor {

    private enum RuntimeError {
        DYNAMIC_TRACE_WITHOUT_RETURN(1), VECTOR_OPERATION_ERROR(2);

        private final int code;

        private RuntimeError(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private int mainPc;
    private Obj currentClassObj = Tab.noObj;
    private final Stack<Integer> currentDoWhileStartAddress = new Stack<>();
    private final Stack<Integer> currentSkipElseJump = new Stack<>();
    private final Stack<List<Integer>> currentBreakJumps = new Stack<>();
    private final Stack<List<Integer>> currentContinueJumps = new Stack<>();
    private final Stack<List<Integer>> currentNextCondTermJumps = new Stack<>();
    private final List<Integer> currentSkipNextCondTermJumps = new ArrayList<>();
    private int currentConditionalJump = 0;
    private Stack<Obj> thisParameterObjs = new Stack<>();
    private Map<Obj, List<Integer>> notYetDeclaredMethod = new HashMap<>();

    public int getMainPc() {
        return mainPc;
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     *  <b>void</b> printBool (bool b, int width1) int width2; int blank; {
     *    <b>if</b> (b == <b>false</b>) {
     *      width2 = 5;
     *    } <b>else</b> { <font color="green">// b == true</font>
     *      width2 = 4;
     *    }
     *    blank = width1 - width2;
     *    <b>if</b> (blank > 0) {
     *      <b>do</b> {
     *       <b>print</b>(' ');
     *       blank--;
     *      } <b>while</b> (blank > 0);
     *    }
     *    <b>if</b> (b == <b>false</b>) {
     *      <b>print</b>('f'); <b>print</b>('a'); <b>print</b>('l'); <b>print</b>('s'); <b>print</b>('e');
     *    } <b>else</b> { <font color="green">// b == true</font>
     *      <b>print</b>('t'); <b>print</b>('r'); <b>print</b>('u'); <b>print</b>('e');
     *    }
     *  }
     * </pre>
     */
    public static void generatePrintBoolMethod() {
        MJTab.printBoolMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(2);
        Code.put(4);

        // if (b) {
        // width2 = 4;
        Code.put(Code.load_n + 0);
        Code.put(Code.const_n + 1);
        Code.put(Code.jcc + Code.ne);
        Code.put2(8);
        Code.put(Code.const_4);
        Code.put(Code.store_2);
        Code.put(Code.jmp);
        Code.put2(5);
        // } else { // b == false;
        // width2 = 5;
        // }
        Code.put(Code.const_5);
        Code.put(Code.store_2);
        // blank = width1 - width2;
        Code.put(Code.load_1);
        Code.put(Code.load_2);
        Code.put(Code.sub);
        Code.put(Code.store_3);
        // if (blank > 0) {
        Code.put(Code.load_3);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(21);
        // do {
        // print(' ');
        Code.put(Code.const_);
        Code.put4(32);
        Code.put(Code.const_1);
        Code.put(Code.bprint);
        // blank--;
        Code.put(Code.inc);
        Code.put(3);
        Code.put(-1);
        // } while (blank > 0);
        Code.put(Code.load_3);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-15);
        // if (b) {
        Code.put(Code.load_n + 0);
        Code.put(Code.const_n + 1);
        Code.put(Code.jcc + Code.ne);
        Code.put2(34);
        // print('t'); print('r'); print('u'); print('e');
        for (int i = 0; i < MJTab.TRUE.length(); i++) {
            Code.load(new Obj(Obj.Con, "charValue", Tab.charType, MJTab.TRUE.charAt(i), 0));
            Code.load(new Obj(Obj.Con, "width", Tab.intType, 1, 0));
            Code.put(Code.bprint);
        }
        // } else { // b == false
        // print('f'); print('a'); print('l'); print('s'); print('e');
        // }
        Code.put(Code.jmp);
        Code.put2(38);
        for (int i = 0; i < MJTab.FALSE.length(); i++) {
            Code.load(new Obj(Obj.Con, "charValue", Tab.charType, MJTab.FALSE.charAt(i), 0));
            Code.load(new Obj(Obj.Con, "width", Tab.intType, 1, 0));
            Code.put(Code.bprint);
        }
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     *  bool readBool() char inp[]; int i; char skip; bool result; {
     *   inp = <b>new</b> char[5];
     *   i = 0;
     *   <b>do</b> {
     *     <b>do</b> {
     *       <b>if</b> (i < 5) {
     *         <b>read</b>(inp[i]);
     *         skip = inp[i];
     *       } <b>else</b> {
     *         <b>read</b>(skip);
     *       }
     *       i++;
     *     } <b>while</b> (ord(skip) != 13);
     *     <b>read</b>(skip); <font color=
     * "green">// Read line feed (new line) character</font>
     *     <b>if</b> (inp[0] == 't' && inp[1] == 'r' && inp[2] == 'u' && inp[3] == 'e' && i == 5) {
     *       result = <b>true</b>;
     *       <b>break</b>;
     *     }
     *     <b>if</b> (inp[0] == 'f' && inp[1] == 'a' && inp[2] == 'l' && inp[3] == 's' && inp[4] == 'e' && i == 6) {
     *       result = <b>false</b>;
     *       <b>break</b>;
     *     }
     *     i = 0;
     *   } <b>while</b> (<b>true</b>);
     *   <b>return</b> result;
     * }
     * </pre>
     */
    public static void generateReadBoolMethod() {
        MJTab.readBoolMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(0);
        Code.put(4);
        // inp = new char[5];
        Code.put(Code.const_5);
        Code.put(Code.newarray);
        Code.put(0);
        Code.put(Code.store_n + 0);
        // i = 0;
        Code.put(Code.const_n + 0);
        Code.put(Code.store_1);
        // do {
        // do {
        // if (i < 5) {
        Code.put(Code.load_1);
        Code.put(Code.const_5);
        Code.put(Code.jcc + Code.ge);
        Code.put2(14);
        // read(inp[i]);
        Code.put(Code.load_n + 0);
        Code.put(Code.load_1);
        Code.put(Code.bread);
        Code.put(Code.bastore);
        // skip = inp[i];
        Code.put(Code.load_n + 0);
        Code.put(Code.load_1);
        Code.put(Code.baload);
        Code.put(Code.store_2);
        Code.put(Code.jmp);
        Code.put2(5);
        // } else {
        // read(skip);
        // }
        Code.put(Code.bread);
        Code.put(Code.store_2);
        // i++;
        Code.put(Code.load_1);
        Code.put(Code.const_1);
        Code.put(Code.add);
        Code.put(Code.store_1);
        // } while (ord(skip) != 13);
        Code.put(Code.load_2);
        Code.put(Code.const_);
        Code.put4(13);
        Code.put(Code.jcc + Code.eq);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-31);
        // read(skip);
        Code.put(Code.bread);
        Code.put(Code.store_2);
        // if (inp[0] == 't' && inp[1] == 'r' && inp[2] == 'u' && inp[3] == 'e' && i <
        // 5) {
        // result = true;
        // break;
        // }
        int skipAddress = 46;
        for (int i = 0; i < MJTab.TRUE.length(); i++) {
            Code.put(Code.load_n + 0);
            Code.load(new Obj(Obj.Con, "", MJTab.intType, i, 0));
            Code.put(Code.baload);
            Code.load(new Obj(Obj.Con, "", MJTab.charType, MJTab.TRUE.charAt(i), 0));
            Code.put(Code.jcc + Code.ne);
            Code.put2(skipAddress);
            skipAddress -= 11;
        }
        Code.put(Code.load_1);
        Code.put(Code.const_5);
        Code.put(Code.jcc + Code.ne);
        Code.put2(8);
        Code.put(Code.const_1);
        Code.put(Code.store_3);
        Code.put(Code.jmp);
        Code.put2(82);
        // if (inp[0] == 'f' && inp[1] == 'a' && inp[2] == 'l' && inp[3] == 's' &&
        // inp[4] == 'e' && i < 6) {
        // result = false;
        // break;
        // }
        skipAddress = 61;
        for (int i = 0; i < MJTab.FALSE.length(); i++) {
            Code.put(Code.load_n + 0);
            Code.load(new Obj(Obj.Con, "", MJTab.intType, i, 0));
            Code.put(Code.baload);
            Code.load(new Obj(Obj.Con, "", MJTab.charType, MJTab.FALSE.charAt(i), 0));
            Code.put(Code.jcc + Code.ne);
            Code.put2(skipAddress);
            skipAddress -= 11;
        }
        Code.put(Code.load_1);
        Code.put(Code.const_);
        Code.put4(6);
        Code.put(Code.jcc + Code.ne);
        Code.put2(8);
        Code.put(Code.const_n + 0);
        Code.put(Code.store_3);
        Code.put(Code.jmp);
        Code.put2(13);
        // i = 0;
        Code.put(Code.const_n + 0);
        Code.put(Code.store_1);
        // } while (true);
        Code.put(Code.const_1);
        Code.put(Code.const_1);
        Code.put(Code.jcc + Code.ne);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-166);
        // return result;
        Code.put(Code.load_3);
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     * int vecTimesVec(int a[], int b[]) int la; int i; int result; {
     *   <b>if</b> (a != null && b != null) {
     *     la = len(a);
     *     <b>if</b> (la == len(b)) {
     *       result = 0;
     *       <b>if</b> (la > 0) {
     *         i = 0;
     *         <b>do</b> {
     *           result = result + a[i] * b[i];
     *           i++;
     *         } <b>while</b> (i < la);
     *       }
     *       <b>return</b> result;
     *     }
     *   }
     * }
     * </pre>
     */
    public static void generateVecTimesVecMethod() {
        MJTab.vecTimesVecMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(2);
        Code.put(5);
        // if (a != null && b != null) {
        // la = len(a);
        Code.put(Code.load_n + 0);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(54);
        Code.put(Code.load_n + 1);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(49);
        Code.put(Code.load_n + 0);
        Code.put(Code.arraylength);
        Code.put(Code.store_2);
        // if (la == len(b)) {
        // result = 0;
        Code.put(Code.load_2);
        Code.put(Code.load_1);
        Code.put(Code.arraylength);
        Code.put(Code.jcc + Code.ne);
        Code.put2(40);
        Code.put(Code.const_n + 0);
        Code.put(Code.store);
        Code.put(4);
        // if (la > 0) {
        // i = 0;
        Code.put(Code.load_2);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(28);
        Code.put(Code.const_n + 0);
        Code.put(Code.store_3);
        // do {
        // result = result + a[i] * b[i];
        // i++;
        // } while (i < la);
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.load_n + 0);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.load_1);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.mul);
        Code.put(Code.add);
        Code.put(Code.store);
        Code.put(4);
        Code.put(Code.inc);
        Code.put(3);
        Code.put(1);
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.put(Code.jcc + Code.ge);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-20);
        // return result;
        // }
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.exit);
        Code.put(Code.return_);
        Code.put(Code.trap);
        Code.put(RuntimeError.VECTOR_OPERATION_ERROR.getCode());
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     * int[] vecTimesScalar(int a[], int s) int la; int i; int result[]; {
     *   <b>if</b> (a != null) {
     *     la = len(a);
     *     result = <b>new</b> int[la];
     *     <b>if</b> (la > 0) {
     *       i = 0;
     *       <b>do</b> {
     *         result[i] = a[i] * s;
     *         i++;
     *       } <b>while</b> (i < la);
     *     }
     *     <b>return</b> result[0];
     *   }
     * }
     * </pre>
     */
    public static void generateVecTimesScalarMethod() {
        MJTab.vecTimesScalarMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(2);
        Code.put(5);
        // if (a != null) {
        // la = len(a);
        // result = new int[la];
        Code.put(Code.load_n + 0);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(42);
        Code.put(Code.load_n + 0);
        Code.put(Code.arraylength);
        Code.put(Code.store_2);
        Code.put(Code.load_2);
        Code.put(Code.newarray);
        Code.put(1);
        Code.put(Code.store);
        Code.put(4);
        // if (la > 0) {
        // i = 0;
        Code.put(Code.load_2);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(25);
        Code.put(Code.const_n + 0);
        Code.put(Code.store_3);
        // do {
        // result[i] = a[i] * s;
        // i++;
        // } while (i < la);
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.load_3);
        Code.put(Code.load_n + 0);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.load_1);
        Code.put(Code.mul);
        Code.put(Code.astore);
        Code.put(Code.inc);
        Code.put(3);
        Code.put(1);
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.put(Code.jcc + Code.ge);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-17);
        // }
        // return result[0];
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.exit);
        Code.put(Code.return_);
        Code.put(Code.trap);
        Code.put(RuntimeError.VECTOR_OPERATION_ERROR.getCode());
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     * int[] scalarTimesVec(int s, int a[]) int la; int i; int result[]; {
     *   <b>if</b> (a != null) {
     *     la = len(a);
     *     result = <b>new</b> int[la];
     *     <b>if</b> (la > 0) {
     *       i = 0;
     *       <b>do</b> {
     *         result[i] = a[i] * s;
     *         i++;
     *       } <b>while</b> (i < la);
     *     }
     *     <b>return</b> result[0];
     *   }
     * }
     * </pre>
     */
    public static void generateScalarTimesVectorMethod() {
        MJTab.scalarTimesVecMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(2);
        Code.put(5);
        // if (a != null) {
        // la = len(a);
        // result = new int[la];
        Code.put(Code.load_1);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(42);
        Code.put(Code.load_1);
        Code.put(Code.arraylength);
        Code.put(Code.store_2);
        Code.put(Code.load_2);
        Code.put(Code.newarray);
        Code.put(1);
        Code.put(Code.store);
        Code.put(4);
        // if (la > 0) {
        // i = 0;
        Code.put(Code.load_2);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(25);
        Code.put(Code.const_n + 0);
        Code.put(Code.store_3);
        // do {
        // result[i] = a[i] * s;
        // i++;
        // } while (i < la);
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.load_3);
        Code.put(Code.load_1);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.load_n + 0);
        Code.put(Code.mul);
        Code.put(Code.astore);
        Code.put(Code.inc);
        Code.put(3);
        Code.put(1);
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.put(Code.jcc + Code.ge);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-17);
        // }
        // return result[0];
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.exit);
        Code.put(Code.return_);
        Code.put(Code.trap);
        Code.put(RuntimeError.VECTOR_OPERATION_ERROR.getCode());
    }

    /**
     * <p>
     * U <code>rs.etf.pp1.mj.runtime.Code.buf</code> dodaje mašinski kod za
     * MikroJava virtuelnu mašinu koji bi se dobio prevođenjem sledeće MikroJava
     * funkcije:
     * </p>
     *
     * <pre>
     * int[] vecPlusVec(int a[], int b[]) int la; int i; int result[]; {
     *   <b>if</b> (a != null && b != null) {
     *   la = len(a);
     *   <b>if</b> (la == len(b)) {
     *     result = <b>new</b> int[la];
     *     <b>if</b> (la > 0) {
     *       i = 0;
     *       <b>do</b> {
     *         result[i] = a[i] + b[i];
     *         i++;
     *       } <b>while</b> (i < la);
     *     }
     *     <b>return</b> result;
     *   }
     * }
     * </pre>
     */
    public static void generateVecPlusVecMethod() {
        MJTab.vecPlusVecMethod.setAdr(Code.pc);

        Code.put(Code.enter);
        Code.put(2);
        Code.put(5);
        // if (a != null && b != null) {
        // la = len(a);
        Code.put(Code.load_n + 0);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(55);
        Code.put(Code.load_n + 1);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(50);
        Code.put(Code.load_n + 0);
        Code.put(Code.arraylength);
        Code.put(Code.store_2);
        // if (la == len(b)) {
        // result = new int[la];
        Code.put(Code.load_2);
        Code.put(Code.load_1);
        Code.put(Code.arraylength);
        Code.put(Code.jcc + Code.ne);
        Code.put2(41);
        Code.put(Code.load_2);
        Code.put(Code.newarray);
        Code.put(1);
        Code.put(Code.store);
        Code.put(4);
        // if (la > 0) {
        // i = 0;
        Code.put(Code.load_2);
        Code.put(Code.const_n + 0);
        Code.put(Code.jcc + Code.le);
        Code.put2(27);
        Code.put(Code.const_n + 0);
        Code.put(Code.store_3);
        // do {
        // result[i] = a[i] + b[i];
        // i++;
        // } while (i < la);
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.load_3);
        Code.put(Code.load_n + 0);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.load_1);
        Code.put(Code.load_3);
        Code.put(Code.aload);
        Code.put(Code.add);
        Code.put(Code.astore);
        Code.put(Code.inc);
        Code.put(3);
        Code.put(1);
        Code.put(Code.load_3);
        Code.put(Code.load_2);
        Code.put(Code.jcc + Code.ge);
        Code.put2(6);
        Code.put(Code.jmp);
        Code.put2(-19);
        // return result;
        // }
        // }
        Code.put(Code.load);
        Code.put(4);
        Code.put(Code.exit);
        Code.put(Code.return_);
        Code.put(Code.trap);
        Code.put(RuntimeError.VECTOR_OPERATION_ERROR.getCode());
    }

    public void generateMethodInvocationCode(Obj overriddenMethod) {
        List<Integer> jmpAddresses = new ArrayList<>();
        int jccAddress = 0;
        List<Obj> leafClasses = MJTab.getLeafClasses();
        List<Obj> filteredLeafClasses = new ArrayList<>();
        for (Obj clss : leafClasses) {
            for (Obj member : clss.getType().getMembers()) {
                if (member.getKind() == Obj.Meth) {
                    try {
                        if (MJUtils.haveSameSignatures(member, overriddenMethod)) {
                            filteredLeafClasses.add(clss);
                        }
                    } catch (WrongObjKindException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (Obj clss : filteredLeafClasses) {
            Code.put(Code.dup);
            Code.put(Code.getfield);
            Code.put2(1);
            Code.load(new Obj(Obj.Con, "", MJTab.intType, clss.getLevel(), 0));
            Code.put(Code.jcc + Code.ne);
            jccAddress = Code.pc;
            Code.put2(0);
            Code.put(Code.pop);
            Code.put(Code.call);
            try {
                Obj method = InheritanceTree.getNode(clss).getVMT().getSameSignatureMethod(overriddenMethod);
                int addr = method.getAdr();
                if (addr != 0) {
                    Code.put2(addr - Code.pc + 1);
                } else {
                    if (notYetDeclaredMethod.containsKey(method)) {
                        List<Integer> list = notYetDeclaredMethod.get(method);
                        list.add(Code.pc);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(Code.pc);
                        notYetDeclaredMethod.put(method, list);
                    }
                    Code.put2(0);
                }
            } catch (WrongObjKindException | WrongStructKindException e) {
                e.printStackTrace();
            }
            Code.put(Code.jmp);
            jmpAddresses.add(Code.pc);
            Code.put2(0);
            Code.fixup(jccAddress);
        }
        // methodDesignator.traverseBottomUp(new ThisParameterLoader());
        Code.put(Code.getfield);
        Code.put2(0);

        Code.put(Code.invokevirtual);
        String methodSignature;
        try {
            methodSignature = MJUtils.getCompactClassMethodSignature(overriddenMethod);
        } catch (WrongObjKindException e) {
            methodSignature = null;
            e.printStackTrace();
        }
        for (int i = 0; i < methodSignature.length(); i++) {
            Code.put4(methodSignature.charAt(i));
        }
        Code.put4(-1);
        for (int address : jmpAddresses) {
            Code.fixup(address);
        }
    }

    private class ThisParameterLoader extends CodeGenerator {

        public void visit(IdentDesignator identDesignator) {
            int identDesignatorKind = identDesignator.obj.getKind();
            Obj obj = Tab.noObj;
            if (!currentClassObj.equals(Tab.noObj)) {
                obj = new Obj(Obj.Var, "this", currentClassObj.getType(), 0, 1);
                if (identDesignatorKind == Obj.Fld) {
                    Code.load(obj);
                }
                if (identDesignatorKind == Obj.Meth) {
                    Struct superclass = currentClassObj.getType();
                    boolean found = false;
                    while (superclass != null) {
                        if (superclass.getMembersTable().searchKey(identDesignator.obj.getName()) != null) {
                            found = true;
                            break;
                        }
                        superclass = superclass.getElemType();
                    }
                    if (found) {
                        Code.load(obj);
                    }
                }
            }
        }

        public void visit(MemberAccessDesignator memberAccessDesignator) {
            Code.load(memberAccessDesignator.getDesignatorStart().obj);
        }

    }

    public void visit(ClassName className) {
        currentClassObj = className.obj;
    }

    public void visit(ClassDecl classDecl) {
        currentClassObj = Tab.noObj;
    }

    public void visit(MethodName methodName) {
        Obj methodNameObj = methodName.obj;
        methodNameObj.setAdr(Code.pc);
        if (notYetDeclaredMethod.containsKey(methodNameObj)) {
            List<Integer> list = notYetDeclaredMethod.get(methodNameObj);
            for (int addr : list) {
                Code.fixup(addr);
            }
        }
        if (methodNameObj.getName().equals(MJTab.MAIN)) {
            mainPc = Code.pc;
        }
        Code.put(Code.enter);
        Code.put(methodNameObj.getLevel());
        Code.put(methodNameObj.getLocalSymbols().size());
    }

    public void visit(MethodDecl methodDecl) {
        Obj methodNameObj = methodDecl.getMethodName().obj;
        if (methodNameObj.getType() == Tab.noType) {
            Code.put(Code.exit);
            Code.put(Code.return_);
        } else {
            Code.put(Code.trap);
            Code.put(RuntimeError.DYNAMIC_TRACE_WITHOUT_RETURN.getCode());
        }
    }

    public void visit(ActParsEnd actParsEnd) {
        Designator methodDesignator = (actParsEnd.getParent() instanceof MethodCallDesignatorStatement)
                ? ((MethodCallDesignatorStatement) actParsEnd.getParent()).getDesignator()
                : ((MethodCallFactor) actParsEnd.getParent()).getDesignator();
        int offset = methodDesignator.obj.getAdr() - Code.pc;
        Obj thisParameterObj = thisParameterObjs.pop();
        if (methodDesignator.obj == MJTab.lenMethod) {
            Code.put(Code.arraylength);
        } else if (!(methodDesignator.obj == MJTab.ordMethod || methodDesignator.obj == MJTab.chrMethod)) {
            if (!thisParameterObj.equals(Tab.noObj)) {
                try {
                    InheritanceTreeNode thisParameterTypeNode = InheritanceTree
                            .getNode((MJTab.findObjForClass(thisParameterObj.getType())));
                    if (thisParameterTypeNode.getVMT().containsSameSignatureMethod(methodDesignator.obj)
                            && thisParameterTypeNode.hasChildren()) {
                        methodDesignator.traverseBottomUp(new ThisParameterLoader());
                        generateMethodInvocationCode(methodDesignator.obj);
                    } else {
                        Code.put(Code.call);
                        Code.put2(offset);
                    }
                } catch (WrongObjKindException | WrongStructKindException e) {
                    e.printStackTrace();
                }
            } else {
                Code.put(Code.call);
                Code.put2(offset);
            }
        }
    }

    public void visit(ReturnNothingStatement returnNothingStatement) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(ReturnExprStatement returnExprStatement) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(MethodCallDesignatorStatement methodCallDesignatorStatement) {
        if (methodCallDesignatorStatement.getDesignator().obj.getType() != Tab.noType) {
            Code.put(Code.pop);
        }
    }

    public void visit(AssignmentDesignatorStatement assignmentDesignatorStatement) {
        Code.store(assignmentDesignatorStatement.getDesignator().obj);
    }

    public void visit(ReadStatement readStatement) {
        Struct designatorType = readStatement.getDesignator().obj.getType();

        if (designatorType.equals(Tab.charType)) {
            Code.put(Code.bread);
        } else if (designatorType.equals(Tab.intType)) {
            Code.put(Code.read);
        } else {
            int offset = MJTab.readBoolMethod.getAdr() - Code.pc;
            Code.put(Code.call);
            Code.put2(offset);
        }
        Code.store(readStatement.getDesignator().obj);
    }

    public void visit(PrintExprStatement printExprStatement) {
        Struct exprType = printExprStatement.getExpr().obj.getType();

        Code.load(new Obj(Obj.Con, "width", Tab.intType, 1, 0));
        if (exprType.equals(Tab.charType)) {
            Code.put(Code.bprint);
        } else if (exprType.equals(Tab.intType)) {
            Code.put(Code.print);
        } else {
            int offset = MJTab.printBoolMethod.getAdr() - Code.pc;
            Code.put(Code.call);
            Code.put2(offset);
        }
    }

    public void visit(PrintExprIntConstStatement printExprIntConstStatement) {
        Struct exprType = printExprIntConstStatement.getExpr().obj.getType();

        Code.load(new Obj(Obj.Con, "width", Tab.intType, printExprIntConstStatement.getIntValue(), 0));
        if (exprType.equals(Tab.charType)) {
            Code.put(Code.bprint);
        } else if (exprType.equals(Tab.intType)) {
            Code.put(Code.print);
        } else {
            int offset = MJTab.printBoolMethod.getAdr() - Code.pc;
            Code.put(Code.call);
            Code.put2(offset);
        }
    }

    public void visit(IncrDesignatorStatement incrDesignatorStatement) {
        Obj designatorObj = incrDesignatorStatement.getDesignator().obj;
        if (designatorObj.getKind() == Obj.Var && designatorObj.getLevel() == 1) {
            Code.put(Code.inc);
            Code.put(designatorObj.getAdr());
            Code.put(1);
        } else {
            if (incrDesignatorStatement.getDesignator() instanceof ArrayElemAccessDesignator) {
                incrDesignatorStatement.getDesignator().traverseBottomUp(this);
            } // Ova if-naredba je obavezna za ispravan rad kompajlera, ali je nedostajala na odbrani (24.06.2018).
            // Zato sam morao da je dodam na licu mesta.
            else if (incrDesignatorStatement.getDesignator() instanceof MemberAccessDesignator) {
                Code.put(Code.dup);
                // Napravi repliku pokazivaca na tekuci objekat (sada se na vrhu steka izraza nalaze dva ovakva pokazivaca, P1 i P2) 
            }
            Code.load(designatorObj);
            // U slucaju da se inkrementira polje objekta, generise se instrukcija getfield koja "pojede" prvi pokazivac, P1
            Code.put(Code.const_1);
            Code.put(Code.add);
            Code.store(designatorObj);
            // U slucaju da se inkrementira polje objekta, generise se instrukcija putfield koja "pojede" drugi pokazivac, P2
        }
    }

    public void visit(DecrDesignatorStatement decrDesignatorStatement) {
        Obj designatorObj = decrDesignatorStatement.getDesignator().obj;
        if (designatorObj.getKind() == Obj.Var && designatorObj.getLevel() == 1) {
            Code.put(Code.inc);
            Code.put(designatorObj.getAdr());
            Code.put(-1);
        } else {
            if (decrDesignatorStatement.getDesignator() instanceof ArrayElemAccessDesignator) {
                decrDesignatorStatement.getDesignator().traverseBottomUp(this);
            } // Ova if-naredba je obavezna za ispravan rad kompajlera, ali je nedostajala na odbrani (24.06.2018).
            // Zato sam morao da je dodam na licu mesta.
            else if (decrDesignatorStatement.getDesignator() instanceof MemberAccessDesignator) {
                Code.put(Code.dup);
                // Napravi repliku pokazivaca na tekuci objekat (sada se na vrhu steka izraza nalaze dva ovakva pokazivaca, P1 i P2)                  
            }
            Code.load(designatorObj);
            // U slucaju da se dekrementira polje objekta, generise se instrukcija getfield koja "pojede" prvi pokazivac, P1
            Code.put(Code.const_1);
            Code.put(Code.sub);
            Code.store(designatorObj);
            // U slucaju da se dekrementira polje objekta, generise se instrukcija putfield koja "pojede" drugi pokazivac, P2
        }
    }

    public void visit(DoWhileStatementStart doWhileStatementStart) {
        currentBreakJumps.push(new ArrayList<Integer>());
        currentContinueJumps.push(new ArrayList<Integer>());
        currentDoWhileStartAddress.push(Code.pc);
    }

    public void visit(DoWhileStatement doWhileStatement) {
        for (int address : currentBreakJumps.pop()) {
            Code.fixup(address);
        }
        int start = currentDoWhileStartAddress.pop().intValue();
        for (int address : currentSkipNextCondTermJumps) {
            Code.put2(address, (start - address + 1));
        }
        currentSkipNextCondTermJumps.clear();
        for (int address : currentNextCondTermJumps.pop()) {
            Code.fixup(address);
        }
    }

    public void visit(ConditionEnd conditionEnd) {
        if (conditionEnd.getParent() instanceof IfThenStatement
                || conditionEnd.getParent() instanceof IfThenElseStatement) {
            for (Integer address : currentSkipNextCondTermJumps) {
                Code.fixup(address);
            }
            currentSkipNextCondTermJumps.clear();
        } else {
            Code.putJump(0);
            currentSkipNextCondTermJumps.add(Code.pc - 2);
        }
    }

    public void visit(Else else_) {
        Code.putJump(0);
        for (Integer address : currentNextCondTermJumps.pop()) {
            Code.fixup(address);
        }
        currentSkipElseJump.push(new Integer(Code.pc - 2));
    }

    public void visit(IfThenStatement ifThenStatement) {
        for (Integer address : currentNextCondTermJumps.pop()) {
            Code.fixup(address);
        }
    }

    public void visit(IfThenElseStatement ifThenElseStatement) {
        Code.fixup(currentSkipElseJump.pop().intValue());
    }

    public void visit(BreakStatement breakStatement) {
        Code.putJump(0);
        currentBreakJumps.peek().add(Code.pc - 2);
    }

    public void visit(ContinueStatement continueStatement) {
        Code.putJump(0);
        currentContinueJumps.peek().add(Code.pc - 2);
    }

    public void visit(ConditionStart conditionStart) {
        if (conditionStart.getParent() instanceof DoWhileStatement) {
            List<Integer> continuesList = currentContinueJumps.pop();
            for (int address : continuesList) {
                Code.fixup(address);
            }
        }
        currentNextCondTermJumps.push(new ArrayList<Integer>());
    }

    public void visit(TermCondition termCondition) {
        if (termCondition.getParent() instanceof OrCondition) {
            Code.putJump(0);
            currentSkipNextCondTermJumps.add(Code.pc - 2);
            for (int address : currentNextCondTermJumps.pop()) {
                Code.fixup(address);
            }
            currentNextCondTermJumps.push(new ArrayList<Integer>());
        }
    }

    public void visit(ExprCondFactor exprCondFactor) {
        Code.load(new Obj(Obj.Con, "true", MJTab.BOOL_TYPE, 1, 0));
        Code.putFalseJump(Code.eq, 0);
        currentNextCondTermJumps.peek().add(Code.pc - 2);
    }

    public void visit(RelOpCondFactor relOpCondFactor) {
        Code.putFalseJump(currentConditionalJump, 0);
        currentNextCondTermJumps.peek().add(Code.pc - 2);
    }

    public void visit(EqRelop eqRelop) {
        currentConditionalJump = Code.eq;
    }

    public void visit(NeqRelop neqRelop) {
        currentConditionalJump = Code.ne;
    }

    public void visit(GtRelop gtRelop) {
        currentConditionalJump = Code.gt;
    }

    public void visit(GeqRelop geqRelop) {
        currentConditionalJump = Code.ge;
    }

    public void visit(LtRelop ltRelop) {
        currentConditionalJump = Code.lt;
    }

    public void visit(LeqRelop leqRelop) {
        currentConditionalJump = Code.le;
    }

    public void visit(IdentDesignator identDesignator) {
        int identDesignatorKind = identDesignator.obj.getKind();
        Obj obj = Tab.noObj;
        if (!currentClassObj.equals(Tab.noObj)) {
            obj = new Obj(Obj.Var, "this", currentClassObj.getType(), 0, 1);
            if (identDesignatorKind == Obj.Fld) {
                Code.load(obj);
            }
            if (identDesignatorKind == Obj.Meth) {
                Struct superclass = currentClassObj.getType();
                boolean found = false;
                while (superclass != null) {
                    if (superclass.getMembersTable().searchKey(identDesignator.obj.getName()) != null) {
                        found = true;
                        break;
                    }
                    superclass = superclass.getElemType();
                }
                if (found) {
                    Code.load(obj);
                }
            }
        }
        if (identDesignatorKind == Obj.Meth) {
            thisParameterObjs.push(obj);
        }
    }

    public void visit(ArrayElemAcessDesignatorLBracket arrAcessDesignatorLBracket) {
        SyntaxNode parent = arrAcessDesignatorLBracket.getParent();
        Code.load((parent instanceof ArrayElemAccessDesignator)
                ? ((ArrayElemAccessDesignator) parent).getDesignatorStart().obj
                : ((ArrayElemAccessDesignatorStart) parent).getDesignatorStart().obj);
    }

    public void visit(MemberAccessDesignator memberAccessDesignator) {
        Code.load(memberAccessDesignator.getDesignatorStart().obj);
        if (memberAccessDesignator.obj.getKind() == Obj.Meth) {
            thisParameterObjs.push(memberAccessDesignator.getDesignatorStart().obj);
        }
    }

    public void visit(IdentDesignatorStart identDesignatorStart) {
        if (!currentClassObj.equals(Tab.noObj)) {
            int identDesignatorStartKind = identDesignatorStart.obj.getKind();
            if (identDesignatorStartKind == Obj.Fld) {
                Obj obj = new Obj(Obj.Var, "this", currentClassObj.getType(), 0, 1);
                Code.load(obj);
            }
        }
    }

    public void visit(MemberAccessDesignatorStart memberAccessDesignatorStart) {
        Code.load(memberAccessDesignatorStart.getDesignatorStart().obj);
    }

    public void visit(MinusTermExpr minusTermExpr) {
        Code.put(Code.neg);
    }

    public void visit(AddopExpr addopExpr) {
        Struct exprType = addopExpr.obj.getType();
        Struct termType = addopExpr.obj.getType();
        if (addopExpr.getAddop() instanceof PlusAddop) {
            if (exprType.equals(MJTab.INT_ARRAY_TYPE) && termType.equals(MJTab.INT_ARRAY_TYPE)) {
                int offset = MJTab.vecPlusVecMethod.getAdr() - Code.pc;
                Code.put(Code.call);
                Code.put2(offset);
            } else {
                Code.put(Code.add);
            }
        } else {
            Code.put(Code.sub);
        }
    }

    public void visit(MulopTerm mulopTerm) {
        Mulop mulop = mulopTerm.getMulop();
        Struct termType = mulopTerm.getTerm().obj.getType();
        Struct factorType = mulopTerm.getFactor().obj.getType();
        if (mulop instanceof TimesMulop) {
            if (termType.equals(MJTab.intType) && factorType.equals(MJTab.intType)) {
                Code.put(Code.mul);
            } else if (termType.equals(MJTab.INT_ARRAY_TYPE) && factorType.equals(MJTab.INT_ARRAY_TYPE)) {
                int offset = MJTab.vecTimesVecMethod.getAdr() - Code.pc;
                Code.put(Code.call);
                Code.put2(offset);
            } else if (termType.equals(MJTab.INT_ARRAY_TYPE) && factorType.equals(MJTab.intType)) {
                int offset = MJTab.vecTimesScalarMethod.getAdr() - Code.pc;
                Code.put(Code.call);
                Code.put2(offset);
            } else if (termType.equals(MJTab.intType) && factorType.equals(MJTab.INT_ARRAY_TYPE)) {
                int offset = MJTab.scalarTimesVecMethod.getAdr() - Code.pc;
                Code.put(Code.call);
                Code.put2(offset);
            } else {
                Code.put(Code.mul);
            }
        } else if (mulop instanceof DivMulop) {
            Code.put(Code.div);
        } else {
            Code.put(Code.rem);
        }
    }

    public void visit(DesignatorFactor designatorFactor) {
        Code.load(designatorFactor.obj);
    }

    public void visit(IntFactor intFactor) {
        Code.load(intFactor.obj);
    }

    public void visit(CharFactor charFactor) {
        Code.load(charFactor.obj);
    }

    public void visit(BoolFactor boolFactor) {
        Code.load(boolFactor.obj);
    }

    public void visit(NewScalarFactor newScalarFactor) {
        Code.put(Code.new_);
        try {
            Code.put2(MJUtils.sizeOfClassInstance(newScalarFactor.getType().obj.getType()));
        } catch (WrongStructKindException e1) {
            e1.printStackTrace();
        }
        if (newScalarFactor.getType().obj.getType().getKind() == Struct.Class) {
            try {
                if (!InheritanceTree.getNode(newScalarFactor.obj).getVMT().isEmpty()) {
                    Obj constObj = new Obj(Obj.Con, "", Tab.intType, newScalarFactor.getType().obj.getAdr(), 1);
                    Code.put(Code.dup);
                    Code.load(constObj);
                    Code.put(Code.putfield);
                    Code.put2(0);
                    constObj.setAdr(newScalarFactor.getType().obj.getLevel());
                    Code.put(Code.dup);
                    Code.load(constObj);
                    Code.put(Code.putfield);
                    Code.put2(1);
                }
            } catch (WrongObjKindException | WrongStructKindException e) {
                e.printStackTrace();
            }
        }
    }

    public void visit(NewVectorFactor newVectorFactor) {
        Struct type = newVectorFactor.getType().obj.getType();
        Code.put(Code.newarray);
        Code.put(type.getKind() == Struct.Char ? 0 : 1);
    }

}
