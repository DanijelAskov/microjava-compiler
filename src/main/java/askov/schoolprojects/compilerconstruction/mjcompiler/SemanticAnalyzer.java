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
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VectorGlobalVar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Mulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MultipleExprExprList;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IdentDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReadStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodDecl;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NonEmptyStatementList;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.CorrectExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NonVoidFormPars;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodCallDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.AndCondTerm;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodEnd;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ExprCondFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.BreakStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.PlusAddop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ContinueStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Program;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NewScalarFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VoidFormPars;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DecrDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ScalarGlobalVar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DelimitedFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MulopTerm;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MemberAccessDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DivMulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.FactorCondTerm;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.LeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.AssignmentDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.CharLiteral;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodCallFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.FactorTerm;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.BoolLiteral;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Relop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReturnExprStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DoWhileStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.EqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.RelOpCondFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ClassDecl;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IntLiteral;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.GeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ScalarFormPar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Const;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VectorLocalVar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NonVoidReturnType;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.TermExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Addop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ArrayElemAccessDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.CorrectCondition;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ProgramEnd;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NonVoidSuperclass;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ScalarField;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodBodyStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.OrCondition;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VectorField;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DesignatorFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.GtRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.TermCondition;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MethodName;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.BoolFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IncrDesignatorStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MinusTermExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.Type;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VoidSuperclass;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ArrayElemAccessDesignator;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.CharFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IntFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ProgramName;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NeqRelop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VectorFormPar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.DoWhileStatementStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.TimesMulop;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ClassName;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ScalarLocalVar;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.VoidReturnType;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.SyntaxNode;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.NewVectorFactor;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.AddopExpr;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.PrintExprIntConstStatement;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.IdentDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.MemberAccessDesignatorStart;
import askov.schoolprojects.compilerconstruction.mjcompiler.ast.ReturnNothingStatement;
import java.util.Stack;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongStructKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.inheritancetree.InheritanceTree;
import askov.schoolprojects.compilerconstruction.mjcompiler.loggers.SemanticErrorMJLogger;
import askov.schoolprojects.compilerconstruction.mjcompiler.loggers.SymbolUsageMJLogger;
import askov.schoolprojects.compilerconstruction.mjcompiler.loggers.SemanticErrorMJLogger.SemanticErrorKind;
import askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature.ClassMethodSignature;
import askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature.GlobalMethodSignature;
import askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature.MethodSignature;
import askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature.MethodSignatureGenerator;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

/**
 *
 * @author Danijel Askov
 */
public class SemanticAnalyzer extends VisitorAdaptor {

    private boolean semanticErrorDetected = false;

    private final SymbolUsageMJLogger symbolUsageMJLogger = new SymbolUsageMJLogger();
    private final SemanticErrorMJLogger semanticErrorMJLogger = new SemanticErrorMJLogger();

    public boolean semanticErrorDetected() {
        return semanticErrorDetected;
    }

    public void detectSemnaticError() {
        semanticErrorDetected = true;
    }

    private void detectSemanticError(Obj symbolObj, SyntaxNode syntaxNode, SemanticErrorKind semanticErrorKind,
            Object... context) {
        semanticErrorDetected = true;
        semanticErrorMJLogger.log(symbolObj, syntaxNode.getLine(), null, semanticErrorKind, context);
    }

    private void detectSemanticError() {
        semanticErrorDetected = true;
    }

    public static final String MAIN = "main";
    public static final String THIS = "this";
    private static final String VMT_POINTER = "$vmtPointer";
    private static final String CLASS_ID = "$classId";

    private enum ScopeType {
        UNIVERSE, PROGRAM, GLOBAL_METHOD, CLASS, CLASS_METHOD
    }

    private ScopeType currentScopeType = ScopeType.UNIVERSE;
    private Obj currentClassObj = MJTab.noObj;
    private Obj currentMethodObj = Tab.noObj;
    private int staticVarsCount = 0;
    private Struct currentType = MJTab.noType;
    private Struct currentMethodReturnType = MJTab.noType;
    private boolean voidMethod = false;
    private int formParCounter = 0;
    private boolean returnStatementFound = false;
    private int doWhileStatementCount = 0;
    private Scope programScope = null;
    private boolean detectErrors = true;
    public Stack<Obj> thisParameterObjs = new Stack<>();

    public int getFormParCounter() {
        return formParCounter;
    }

    public int getStaticVarsCount() {
        return staticVarsCount;
    }

    public SemanticErrorMJLogger getSemanticErrorMJLogger() {
        return semanticErrorMJLogger;
    }

    private boolean printBoolMethodIsUsed = false;
    private boolean readBoolMethodIsUsed = false;
    private boolean vecTimesVecMethodIsUsed = false;
    private boolean vecPlusVecMethodIsUsed = false;
    private boolean vecTimesScalarMethodIsUsed = false;
    private boolean scalarTimesVectorMethodIsUsed = false;

    private Obj findNearestDeclaration(String identName, boolean skipCurrentScope) {
        Obj result = Tab.noObj;

        if (!skipCurrentScope) {
            result = findInCurrentScope(identName);
            // Prvo treba pogledati listu formalnih parametara (CLASS_METHOD opseg).
        }

        if (result == Tab.noObj) {
            result = findInOuterScope(identName);
            // Zatim treba pogledati do sada deklarisana sopstvena polja klase (CLASS
            // opseg).

            if (result == Tab.noObj) {
                // Pretrazuju se nasledjena polja
                Struct superclass = currentClassObj.getType().getElemType();
                Obj foundMethod = null;
                while (superclass != null) {
                    foundMethod = superclass.getMembersTable().searchKey(identName);
                    if (foundMethod != null) {
                        result = foundMethod;
                        break;
                    }
                    superclass = superclass.getElemType();
                }
                if (result == Tab.noObj) {
                    result = findInSomeOuterScope(identName);
                    // Zatim treba pogledati globalne i predeklarisane simbole (PROGRAM i UNIVERSE
                    // opsezi).
                }
            }
        }

        return result;
    }

    private Obj findNearestDeclaration(String identName, Obj instanceObj) {
        Obj result = Tab.noObj;

        SymbolDataStructure targetSymbolDataStructure = null;
        if (currentClassObj.getType() == instanceObj.getType()) {
            // Ako se metoda unutar klase poziva nad instancom klase kojoj sama ona pripada
            targetSymbolDataStructure = MJTab.currentScope.getOuter().getLocals();
        } else {
            targetSymbolDataStructure = instanceObj.getType().getMembersTable();
        }

        Struct superclass = instanceObj.getType();
        Obj foundMethod = null;
        while (targetSymbolDataStructure != null) {
            foundMethod = targetSymbolDataStructure.searchKey(identName);
            if (foundMethod != null) {
                result = foundMethod;
                break;
            }
            superclass = superclass.getElemType();
            targetSymbolDataStructure = superclass != null ? superclass.getMembersTable() : null;
        }

        return result;
    }

    private Obj findNearestDeclaration(MethodSignature classMethodSignature, Obj clss) {
        if (clss != null && clss.getType().getKind() == Struct.Class) {
            Struct currentClass = clss.getType().getElemType();
            while (currentClass != null) {
                Obj method = currentClass.getMembersTable().searchKey(classMethodSignature.getMethodName());
                if (method != null && method != Tab.noObj && method.getKind() == Obj.Meth) {
                    try {
                        if (new ClassMethodSignature(method, MJTab.noType).isInvokableBy(classMethodSignature)) {
                            return method;
                        }
                    } catch (WrongObjKindException e) {
                        e.printStackTrace();
                    }
                }
                currentClass = currentClass.getElemType();
            }
        }
        return Tab.noObj;
    }

    private void validateOverriding(MethodDecl methodDecl) {
        Struct clss = currentClassObj.getType().getElemType();
        Obj overridingMethod = methodDecl.getMethodName().obj;
        while (clss != null) {
            Obj overriddenMethod = clss.getMembersTable().searchKey(methodDecl.getMethodName().obj.getName());
            try {
                if (MJUtils.haveSameSignatures(overridingMethod, overriddenMethod)
                        && !MJUtils.returnTypesAssignmentCompatible(overridingMethod, overriddenMethod)) {
                    detectSemanticError(null, methodDecl, SemanticErrorKind.INCOMPATIBLE_RET_TYPE,
                            new ClassMethodSignature(overriddenMethod, clss));
                }
            } catch (WrongObjKindException e) {
                e.printStackTrace();
            }
            clss = clss.getElemType();
        }
    }

    private Obj findInCurrentOrSomeOuterScope(String identName) {
        return MJTab.find(identName);
    }

    private Obj findInCurrentScope(String identName) {
        Obj result = MJTab.currentScope.findSymbol(identName);
        if (result == null) {
            result = Tab.noObj;
        }
        return result;
    }

    private Obj findInOuterScope(String identName) {
        Obj result = MJTab.currentScope.getOuter().findSymbol(identName);
        if (result == null) {
            result = Tab.noObj;
        }
        return result;
    }

    private Obj findInSomeOuterScope(String identName) {
        Obj resultObj = null;
        for (Scope s = MJTab.currentScope.getOuter(); s != null; s = s.getOuter()) {
            if (s.getLocals() != null) {
                resultObj = s.getLocals().searchKey(identName);
                if (resultObj != null) {
                    break;
                }
            }
        }
        return (resultObj != null) ? resultObj : Tab.noObj;
    }

    private boolean isGlobalMethod(Obj method) {
        return programScope.getLocals().symbols().contains(method) || method == MJTab.chrMethod
                || method == MJTab.lenMethod || method == MJTab.ordMethod;
    }

    public void visit(ProgramName programName) {
        String programIdent = programName.getIdent();

        Obj progObj = findInCurrentScope(programIdent); // Pretražuje se UNIVERSE opseg.

        if (progObj == Tab.noObj) {
            programName.obj = MJTab.insert(Obj.Prog, programIdent, MJTab.noType);
        } else {
            programName.obj = new Obj(Obj.Prog, programIdent, MJTab.noType);
            detectSemanticError(programName.obj, programName, SemanticErrorKind.INV_PROG_NAME);
        }

        MJTab.openScope();
        programScope = MJTab.currentScope;
        currentScopeType = ScopeType.PROGRAM;
    }

    public void visit(ProgramEnd programEnd) {
        Obj mainObj = findInCurrentScope(MAIN); // Pretražuje se PROGRAM opseg.

        if (mainObj == MJTab.noObj) {
            detectSemanticError(null, programEnd, SemanticErrorKind.MAIN_METHOD_DECL_NOT_FOUND);
        }

        currentScopeType = ScopeType.UNIVERSE;
    }

    public void visit(Program program) {
        staticVarsCount = MJTab.currentScope().getnVars();

        MJTab.chainLocalSymbols(program.getProgramName().obj);

        MJTab.closeScope();
    }

    public void visit(Type type) {
        String typeIdent = type.getIdent();

        Obj typeObj = findInCurrentOrSomeOuterScope(typeIdent);

        if (typeObj.getKind() == Obj.Type) {
            type.obj = typeObj;
        } else {
            type.obj = new Obj(Obj.Type, typeIdent, MJTab.noType);
            detectSemanticError(type.obj, type, SemanticErrorKind.UNRESOLVED_TYPE);
        }

        currentType = type.obj.getType();
    }

    public void visit(NonVoidReturnType nonVoidReturnType) {
        currentMethodReturnType = nonVoidReturnType.getType().obj.getType();
        voidMethod = false;
    }

    public void visit(IntLiteral intLiteral) {
        intLiteral.obj = new Obj(Obj.Con, "", MJTab.intType, intLiteral.getValue(), 0);
    }

    public void visit(CharLiteral charLiteral) {
        charLiteral.obj = new Obj(Obj.Con, "", MJTab.charType, charLiteral.getValue(), 0);
    }

    public void visit(BoolLiteral boolLiteral) {
        boolLiteral.obj = new Obj(Obj.Con, "", MJTab.BOOL_TYPE, boolLiteral.getValue() ? 1 : 0, 0);
    }

    public void visit(Const constant) {
        String constantIdent = constant.getIdent();

        Obj constantObj = findInCurrentScope(constantIdent);
        // Pretražuju se PROGRAM i UNIVERSE opsezi.

        if (constantObj == Tab.noObj) {
            constantObj = MJTab.insert(Obj.Con, constant.getIdent(), currentType);
            Struct initializerType = constant.getLiteral().obj.getType();
            if (initializerType.equals(currentType)) {
                constantObj.setAdr(constant.getLiteral().obj.getAdr());
            } else {
                constantObj.setAdr(0);
                if (currentType != MJTab.noType) {
                    if (MJUtils.isPrimitiveDataType(currentType)) {
                        detectSemanticError(null, constant, SemanticErrorKind.TYPE_MISMATCH, initializerType,
                                currentType);
                    } else {
                        detectSemanticError(new Obj(Obj.NO_VALUE, "", currentType), constant,
                                SemanticErrorKind.NON_PRIMITIVE_TYPE);
                    }
                } else {
                    detectSemanticError();
                }
            }
        } else {
            detectSemanticError(constantObj, constant, SemanticErrorKind.DUP_GLOBAL_DECL);
        }
    }

    public void visit(ScalarGlobalVar scalarGlobalVar) {
        String varIdent = scalarGlobalVar.getIdent();
        Obj varObj = findInCurrentScope(varIdent);

        if (varObj == Tab.noObj) {
            MJTab.insert(Obj.Var, varIdent, currentType);
        } else {
            detectSemanticError(varObj, scalarGlobalVar, SemanticErrorKind.DUP_GLOBAL_DECL);
        }
    }

    public void visit(ScalarField scalarField) {
        String fieldIdent = scalarField.getIdent();
        Obj fieldObj = findInCurrentScope(fieldIdent);

        if (fieldObj == Tab.noObj) {
            currentClassObj.setAdr(currentClassObj.getAdr() + 1);
            MJTab.insert(Obj.Fld, fieldIdent, currentType).setAdr(currentClassObj.getAdr());
        } else {
            detectSemanticError(fieldObj, scalarField, SemanticErrorKind.DUP_MEMBER, currentClassObj);
        }
    }

    public void visit(ScalarLocalVar scalarLocalVar) {
        String varIdent = scalarLocalVar.getIdent();
        Obj localVarObj = findInCurrentScope(varIdent);

        if (localVarObj == Tab.noObj) {
            MJTab.insert(Obj.Var, varIdent, currentType);
        } else {
            detectSemanticError(localVarObj, scalarLocalVar, SemanticErrorKind.DUP_LOCAL_VAR);
        }
    }

    public void visit(VectorGlobalVar vectorGlobalVar) {
        String varIdent = vectorGlobalVar.getIdent();
        Obj varObj = findInCurrentScope(varIdent);

        if (varObj == Tab.noObj) {
            MJTab.insert(Obj.Var, varIdent, new Struct(Struct.Array, currentType));
        } else {
            detectSemanticError(varObj, vectorGlobalVar, SemanticErrorKind.DUP_GLOBAL_DECL);
        }
    }

    public void visit(VectorField vectorField) {
        String fieldIdent = vectorField.getIdent();
        Obj fieldObj = findInCurrentScope(fieldIdent);

        if (fieldObj == Tab.noObj) {
            currentClassObj.setAdr(currentClassObj.getAdr() + 1);
            MJTab.insert(Obj.Fld, fieldIdent, new Struct(Struct.Array, currentType)).setAdr(currentClassObj.getAdr());
        } else {
            detectSemanticError(fieldObj, vectorField, SemanticErrorKind.DUP_MEMBER);
        }
    }

    public void visit(VectorLocalVar vectorLocalVar) {
        String varIdent = vectorLocalVar.getIdent();
        Obj varObj = findInCurrentScope(varIdent);

        if (varObj == Tab.noObj) {
            MJTab.insert(Obj.Var, varIdent, new Struct(Struct.Array, currentType));
        } else {
            detectSemanticError(varObj, vectorLocalVar, SemanticErrorKind.DUP_LOCAL_VAR);
        }
    }

    public void visit(ClassName className) {
        String classIdent = className.getIdent();

        Obj classObj = findInCurrentScope(classIdent);

        if (classObj == Tab.noObj) {
            className.obj = currentClassObj = MJTab.insert(Obj.Type, className.getIdent(), new Struct(Struct.Class));
            currentClassObj.setLevel(MJTab.nextClassId());
        } else {
            className.obj = currentClassObj = new Obj(Obj.Type, className.getIdent(), new Struct(Struct.Class));
            detectSemanticError(className.obj, className, SemanticErrorKind.DUP_GLOBAL_DECL);
        }

        MJTab.openScope();
        currentScopeType = ScopeType.CLASS;
    }

    public void visit(NonVoidSuperclass nonVoidSuperclass) {
        Struct superclassType = nonVoidSuperclass.getType().obj.getType();
        if (superclassType != MJTab.noType) {
            if (superclassType.getKind() == Struct.Class && superclassType != currentClassObj.getType()) {
                Obj superclassObj = nonVoidSuperclass.getType().obj;
                try {
                    InheritanceTree.addNodeForClass(currentClassObj, superclassObj);
                } catch (WrongObjKindException | WrongStructKindException e) {
                    e.printStackTrace();
                }
                currentClassObj.setAdr(superclassObj.getAdr());
                currentClassObj.getType().setElementType(superclassType);
            } else {
                currentClassObj.getType().setElementType(MJTab.noType);
                detectSemanticError(nonVoidSuperclass.getType().obj, nonVoidSuperclass,
                        SemanticErrorKind.INV_SUPERCLASS);
            }
        }
    }

    public void visit(VoidSuperclass voidSuperclass) {
        MJTab.insert(Obj.Fld, VMT_POINTER, MJTab.intType);
        MJTab.insert(Obj.Fld, CLASS_ID, MJTab.intType);
        currentClassObj.setAdr(1);
        try {
            InheritanceTree.addNodeForClass(currentClassObj);
        } catch (WrongObjKindException | WrongStructKindException e) {
            e.printStackTrace();
        }
    }

    public void visit(ClassDecl classDecl) {
        MJTab.chainLocalSymbols(classDecl.getClassName().obj.getType());

        MJTab.closeScope();

        currentScopeType = ScopeType.PROGRAM;
        currentClassObj = MJTab.noObj;
    }

    public void visit(VoidFormPars voidFormPars) {
        if (formParCounter == 0 && currentScopeType == ScopeType.CLASS_METHOD) {
            MJTab.insert(Obj.Var, THIS, currentClassObj.getType());
            formParCounter++;
        }
        currentMethodObj.setLevel(formParCounter);
    }

    public void visit(NonVoidFormPars nonVoidFormPars) {
        currentMethodObj.setLevel(formParCounter);
    }

    public void visit(ScalarFormPar scalarFormPar) {
        if (formParCounter == 0 && currentScopeType == ScopeType.CLASS_METHOD) {
            MJTab.insert(Obj.Var, THIS, currentClassObj.getType());
            formParCounter++;
        }

        String scalarFormParIdent = scalarFormPar.getIdent();

        Obj formParObj = findInCurrentScope(scalarFormParIdent);

        if (formParObj == Tab.noObj) {
            formParObj = MJTab.insert(Obj.Var, scalarFormParIdent, scalarFormPar.getType().obj.getType());
            formParObj.setFpPos(formParCounter++);
        } else {
            detectSemanticError(formParObj, scalarFormPar, SemanticErrorKind.DUP_PAR);
        }
    }

    public void visit(VectorFormPar vectorFormPar) {
        if (formParCounter == 0 && currentScopeType == ScopeType.CLASS_METHOD) {
            MJTab.insert(Obj.Var, THIS, currentClassObj.getType());
            formParCounter++;
        }

        String vectorFormParIdent = vectorFormPar.getIdent();

        Obj formParObj = findInCurrentScope(vectorFormParIdent);

        if (formParObj == Tab.noObj) {
            formParObj = MJTab.insert(Obj.Var, vectorFormParIdent,
                    new Struct(Struct.Array, vectorFormPar.getType().obj.getType()));
            formParObj.setFpPos(formParCounter++);
        } else {
            detectSemanticError(formParObj, vectorFormPar, SemanticErrorKind.DUP_PAR);
        }
    }

    public void visit(VoidReturnType voidReturnType) {
        currentMethodReturnType = MJTab.noType;
        voidMethod = true;
    }

    public void visit(MethodName methodName) {
        String methodIdent = methodName.getIdent();

        currentScopeType = (currentScopeType == ScopeType.PROGRAM) ? ScopeType.GLOBAL_METHOD : ScopeType.CLASS_METHOD;

        Obj methodObj = findInCurrentScope(methodIdent);

        if (methodObj == Tab.noObj) {
            methodName.obj = MJTab.insert(Obj.Meth, methodIdent, currentMethodReturnType);
        } else {
            if (currentScopeType == ScopeType.CLASS_METHOD) {
                detectSemanticError(methodObj, methodName, SemanticErrorKind.DUP_MEMBER, currentClassObj);
            } else {
                detectSemanticError(methodObj, methodName, SemanticErrorKind.DUP_GLOBAL_DECL);
            }
            methodName.obj = new Obj(Obj.Meth, methodIdent, currentMethodReturnType);
        }

        currentMethodObj = methodName.obj;
        MJTab.openScope();
        formParCounter = 0;
    }

    public void visit(MethodBodyStart methodBodyStart) {
        MJTab.chainLocalSymbols(currentMethodObj);
    }

    public void visit(MethodDecl methodDecl) {
        String methodIdent = methodDecl.getMethodName().getIdent();

        if (methodIdent.equals(MAIN) && currentScopeType == ScopeType.GLOBAL_METHOD) {
            if (!(methodDecl.getReturnType() instanceof VoidReturnType)) {
                detectSemanticError(null, methodDecl, SemanticErrorKind.NON_VOID_MAIN);
            }
            if (!(methodDecl.getFormPars() instanceof VoidFormPars)) {
                detectSemanticError(null, methodDecl, SemanticErrorKind.MAIN_WITH_PARAMS);
            }
        }

        if (currentScopeType == ScopeType.CLASS_METHOD) {
            validateOverriding(methodDecl);
        }

        currentMethodObj = Tab.noObj;
        MJTab.closeScope();
        currentScopeType = (currentScopeType == ScopeType.GLOBAL_METHOD) ? ScopeType.PROGRAM : ScopeType.CLASS;
    }

    public void visit(ReturnNothingStatement returnNothingStatement) {
        if (!voidMethod) {
            if (!currentMethodObj.getType().equals(MJTab.noType)
                    && !(currentMethodObj.getName().equals(MAIN) && currentScopeType == ScopeType.GLOBAL_METHOD)) {
                detectSemanticError(currentMethodObj, returnNothingStatement, SemanticErrorKind.RETURN_NOT_FOUND);
            } else {
                detectSemanticError();
            }
        }
        returnStatementFound = true;
    }

    public void visit(ReturnExprStatement returnExprStatement) {
        returnStatementFound = true;
        if (voidMethod) {
            detectSemanticError(null, returnExprStatement, SemanticErrorKind.RET_VAL_FROM_VOID_METHOD);
        } else {
            Obj exprObj = returnExprStatement.getExpr().obj;
            Struct exprStruct = exprObj.getType();
            if (MJUtils.assignableTo(exprStruct, currentMethodReturnType)) {
                return;
            } else {
                if (!currentMethodReturnType.equals(MJTab.noType)
                        && !(currentMethodObj.getName().equals(MAIN) && currentScopeType == ScopeType.GLOBAL_METHOD)) {
                    if (exprStruct != Tab.noType || exprObj.getKind() == Obj.Meth) {
                        detectSemanticError(null, returnExprStatement, SemanticErrorKind.TYPE_MISMATCH, exprStruct,
                                currentMethodReturnType);
                    }
                } else {
                    detectSemanticError();
                }
            }
        }
    }

    public void visit(MethodEnd methodEnd) {
        if (!voidMethod && !returnStatementFound) {
            if (!currentMethodObj.getType().equals(MJTab.noType)
                    && !(currentMethodObj.getName().equals(MAIN) && currentScopeType == ScopeType.GLOBAL_METHOD)) {
                detectSemanticError(currentMethodObj, methodEnd, SemanticErrorKind.RETURN_NOT_FOUND);
            } else {
                detectSemanticError();
            }
        }
        returnStatementFound = false;
    }

    public void visit(NonEmptyStatementList nonEmptyStatementList) {
        detectErrors = true;
    }

    public void visit(AssignmentDesignatorStatement assignmentDesignatorStatement) {
        Obj exprObj = ((CorrectExpr) assignmentDesignatorStatement.getErrorProneExpr()).getExpr().obj;
        Struct exprStruct = exprObj.getType();
        Struct designatorStruct = assignmentDesignatorStatement.getDesignator().obj.getType();
        if (assignmentDesignatorStatement.getDesignator().obj.getKind() == Obj.Con) {
            detectSemanticError(assignmentDesignatorStatement.getDesignator().obj, assignmentDesignatorStatement,
                    SemanticErrorKind.ASSIGINING_SYM_CONST);
            return;
        }
        if (!MJUtils.assignableTo(exprStruct, designatorStruct)) {
            if ((exprStruct.getKind() != Struct.None && designatorStruct.getKind() != Struct.None)
                    || (exprStruct.getKind() == Struct.None && exprObj.getKind() == Obj.Meth)) {
                detectSemanticError(null, assignmentDesignatorStatement, SemanticErrorKind.TYPE_MISMATCH, exprStruct,
                        designatorStruct);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(MethodCallDesignatorStatement methodCallDesignatorStatement) {
        Obj methodObj = methodCallDesignatorStatement.getDesignator().obj;

        MethodSignatureGenerator invokedMethodSignatureGenerator = new MethodSignatureGenerator();
        methodCallDesignatorStatement.traverseTopDown(invokedMethodSignatureGenerator);
        if (methodObj.getKind() != Obj.Meth) {
            if (invokedMethodSignatureGenerator.getMethodSignature() instanceof ClassMethodSignature) {
                ClassMethodSignature classMethodSignature = (ClassMethodSignature) invokedMethodSignatureGenerator
                        .getMethodSignature();
                if (classMethodSignature.getThisParameterType() != MJTab.noType
                        && classMethodSignature.getThisParameterType().getElemType() != MJTab.noType
                        && !classMethodSignature.containsUndeclaredType()) {
                    if (classMethodSignature.getThisParameterType().getKind() != Struct.Class) {
                        detectSemanticError(null, methodCallDesignatorStatement, SemanticErrorKind.UNINVOKABLE_METHOD,
                                invokedMethodSignatureGenerator.getMethodSignature(),
                                classMethodSignature.getThisParameterType());
                    } else {
                        detectSemanticError(null, methodCallDesignatorStatement, SemanticErrorKind.UNDEF_METHOD,
                                invokedMethodSignatureGenerator.getMethodSignature());
                    }
                }
            } else {
                GlobalMethodSignature globalMethodSignature = (GlobalMethodSignature) invokedMethodSignatureGenerator
                        .getMethodSignature();
                if (!globalMethodSignature.containsUndeclaredType()) {
                    detectSemanticError(null, methodCallDesignatorStatement, SemanticErrorKind.UNDEF_METHOD,
                            invokedMethodSignatureGenerator.getMethodSignature());
                }
            }
        } else {
            MethodSignature methodSignature = null;
            try {
                if (isGlobalMethod(methodObj)) {
                    methodSignature = new GlobalMethodSignature(methodObj);
                } else {
                    methodSignature = new ClassMethodSignature(methodObj, thisParameterObjs.peek().getType());
                }
            } catch (WrongObjKindException e) {
            }
            if (methodSignature != null) {
                if (!methodSignature.isInvokableBy(invokedMethodSignatureGenerator.getMethodSignature())) {
                    Obj overriddenMethodObj = findNearestDeclaration(
                            invokedMethodSignatureGenerator.getMethodSignature(), thisParameterObjs.pop());
                    if (overriddenMethodObj.equals(Tab.noObj)) {
                        if (!invokedMethodSignatureGenerator.getMethodSignature().containsUndeclaredType()) {
                            detectSemanticError(null, methodCallDesignatorStatement,
                                    SemanticErrorKind.INAPPLICABLE_METHOD, methodSignature.toString(),
                                    invokedMethodSignatureGenerator.getMethodSignature().getParameterList());
                        } else {
                            detectSemanticError();
                        }
                    } else {
                        methodCallDesignatorStatement.getDesignator().obj = overriddenMethodObj;
                    }
                }
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(IncrDesignatorStatement incrDesignatorStatement) {
        Struct designatorType = incrDesignatorStatement.getDesignator().obj.getType();
        if (!designatorType.equals(MJTab.intType)) {
            if (!designatorType.equals(MJTab.noType) && !(designatorType.getKind() == Struct.Array
                    && designatorType.getElemType().equals(MJTab.noType))) {
                detectSemanticError(incrDesignatorStatement.getDesignator().obj, incrDesignatorStatement,
                        SemanticErrorKind.TYPE_MISMATCH, designatorType, MJTab.intType);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(DecrDesignatorStatement decrDesignatorStatement) {
        Struct designatorType = decrDesignatorStatement.getDesignator().obj.getType();
        if (!designatorType.equals(MJTab.intType)) {
            if (!designatorType.equals(MJTab.noType) && !(designatorType.getKind() == Struct.Array
                    && designatorType.getElemType().equals(MJTab.noType))) {
                detectSemanticError(decrDesignatorStatement.getDesignator().obj, decrDesignatorStatement,
                        SemanticErrorKind.TYPE_MISMATCH, decrDesignatorStatement.getDesignator().obj.getType(),
                        MJTab.intType);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(DoWhileStatementStart doWhileStatementStart) {
        doWhileStatementCount++;
    }

    public void visit(DoWhileStatement doWhileStatement) {
        doWhileStatementCount--;
    }

    public void visit(BreakStatement breakStatement) {
        if (doWhileStatementCount <= 0) {
            detectSemanticError(null, breakStatement, SemanticErrorKind.MISPLACED_BREAK);
        }
    }

    public void visit(ContinueStatement continueStatement) {
        if (doWhileStatementCount <= 0) {
            detectSemanticError(null, continueStatement, SemanticErrorKind.MISPLACED_CONTINUE);
        }
    }

    public void visit(ReadStatement readStatement) {
        Struct designatorType = readStatement.getDesignator().obj.getType();
        if (designatorType.equals(MJTab.BOOL_TYPE)) {
            readBoolMethodIsUsed = true;
        }
        if (!designatorType.equals(MJTab.intType) && !designatorType.equals(MJTab.charType) && !designatorType.equals(MJTab.BOOL_TYPE)) {
            if (!designatorType.equals(MJTab.noType) && !(designatorType.getKind() == Struct.Array
                    && designatorType.getElemType().equals(MJTab.noType))) {
                detectSemanticError(readStatement.getDesignator().obj, readStatement,
                        SemanticErrorKind.NON_PRIMITIVE_TYPE);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(PrintExprStatement printExprStatement) {
        Struct exprType = printExprStatement.getExpr().obj.getType();
        if (exprType.equals(MJTab.BOOL_TYPE)) {
            printBoolMethodIsUsed = true;
        }
        if (!exprType.equals(MJTab.intType) && !exprType.equals(MJTab.charType) && !exprType.equals(MJTab.BOOL_TYPE)) {
            if (!exprType.equals(MJTab.noType)
                    && !(exprType.getKind() == Struct.Array && exprType.getElemType().equals(MJTab.noType))) {
                detectSemanticError(printExprStatement.getExpr().obj, printExprStatement,
                        SemanticErrorKind.NON_PRIMITIVE_TYPE);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(PrintExprIntConstStatement printExprIntConstStatement) {
        Struct exprType = printExprIntConstStatement.getExpr().obj.getType();
        if (exprType.equals(MJTab.BOOL_TYPE)) {
            printBoolMethodIsUsed = true;
        }
        if (!exprType.equals(MJTab.intType) && !exprType.equals(MJTab.charType) && !exprType.equals(MJTab.BOOL_TYPE)) {
            if (!exprType.equals(MJTab.noType)
                    && !(exprType.getKind() == Struct.Array && exprType.getElemType().equals(MJTab.noType))) {
                detectSemanticError(printExprIntConstStatement.getExpr().obj, printExprIntConstStatement,
                        SemanticErrorKind.NON_PRIMITIVE_TYPE);
            } else {
                detectSemanticError();
            }
        }
    }

    public void visit(MultipleExprExprList multipleExprExprList) {
        detectErrors = true;
    }

    public void visit(CorrectCondition correctCondition) {
        detectErrors = true;
    }

    public void visit(OrCondition orCondition) {
        Obj condObj = orCondition.getCondition().obj;
        Struct condType = condObj.getType();
        Obj termObj = orCondition.getCondTerm().obj;
        Struct termType = termObj.getType();
        if (condType.equals(MJTab.BOOL_TYPE) && termType.equals(MJTab.BOOL_TYPE)) {
            orCondition.obj = new Obj(Obj.Var, "", MJTab.BOOL_TYPE);
        } else {
            String operator = "||";
            if (detectErrors) {
                detectSemanticError(null, orCondition, SemanticErrorKind.UNDEF_OP, condType, termType, operator);
            }
            orCondition.obj = MJTab.noObj;
            detectErrors = false;
        }
    }

    public void visit(TermCondition termCondition) {
        termCondition.obj = termCondition.getCondTerm().obj;
    }

    public void visit(AndCondTerm andCondTerm) {
        Struct termType = andCondTerm.getCondTerm().obj.getType();
        Struct factorType = andCondTerm.getCondFactor().obj.getType();
        if (termType.equals(MJTab.BOOL_TYPE) && factorType.equals(MJTab.BOOL_TYPE)) {
            andCondTerm.obj = new Obj(Obj.Var, "", MJTab.BOOL_TYPE);
        } else {
            String operator = "&&";
            if (detectErrors) {
                detectSemanticError(null, andCondTerm, SemanticErrorKind.UNDEF_OP, termType, factorType, operator);
            }
            andCondTerm.obj = MJTab.noObj;
            detectErrors = false;
        }
    }

    public void visit(FactorCondTerm factorCondTerm) {
        factorCondTerm.obj = factorCondTerm.getCondFactor().obj;
    }

    public void visit(ExprCondFactor exprCondFactor) {
        exprCondFactor.obj = exprCondFactor.getExpr().obj;
        if (!exprCondFactor.obj.getType().equals(MJTab.BOOL_TYPE)) {
            if ((exprCondFactor.obj.getType() != MJTab.noType || exprCondFactor.obj.getKind() == Obj.Meth)) {
                detectSemanticError(null, exprCondFactor, SemanticErrorKind.TYPE_MISMATCH, exprCondFactor.obj.getType(),
                        MJTab.BOOL_TYPE);
            } else {
                detectSemanticError();
                detectErrors = false;
            }
        }
    }

    public void visit(RelOpCondFactor relOpCondFactor) {
        relOpCondFactor.obj = new Obj(Obj.Var, "", MJTab.BOOL_TYPE);
        Obj exprObj = relOpCondFactor.getExpr().obj;
        Struct exprType = exprObj.getType();
        Obj expr1Obj = relOpCondFactor.getExpr1().obj;
        Struct expr1Type = expr1Obj.getType();
        String operator;
        Relop relop = relOpCondFactor.getRelop();
        if (relop instanceof EqRelop) {
            operator = "==";
        } else if (relop instanceof NeqRelop) {
            operator = "!=";
        } else if (relop instanceof GeqRelop) {
            operator = ">=";
        } else if (relop instanceof GtRelop) {
            operator = ">";
        } else if (relop instanceof LeqRelop) {
            operator = "<=";
        } else {
            operator = "<";
        }
        if (!exprType.compatibleWith(expr1Type)) {
            if ((exprType != MJTab.noType || exprObj.getKind() == Obj.Meth)
                    && (expr1Type != MJTab.noType || expr1Obj.getKind() == Obj.Meth)) {
                detectSemanticError(null, relOpCondFactor, SemanticErrorKind.UNDEF_OP, exprType, expr1Type, operator);
            } else {
                detectSemanticError();
                detectErrors = false;
            }
        } else {
            if (exprType.getKind() == Struct.Class || exprType.getKind() == Struct.Array) {
                if (!(relOpCondFactor.getRelop() instanceof EqRelop
                        || relOpCondFactor.getRelop() instanceof NeqRelop)) {
                    detectSemanticError(null, relOpCondFactor, SemanticErrorKind.UNDEF_OP, exprType, expr1Type,
                            operator);
                }
            }
        }
    }

    public void visit(TermExpr termExpr) {
        termExpr.obj = termExpr.getTerm().obj;
    }

    public void visit(MinusTermExpr minusTermExpr) {
        Struct termType = minusTermExpr.getTerm().obj.getType();
        if (!termType.equals(MJTab.intType)) {
            minusTermExpr.obj = Tab.noObj;
            if (detectErrors) {
                detectSemanticError(null, minusTermExpr, SemanticErrorKind.UNDEF_OP, termType, "-");
            }
            detectErrors = false;
        } else {
            minusTermExpr.obj = minusTermExpr.getTerm().obj;
        }
    }

    public void visit(AddopExpr addopExpr) {
        Struct exprType = addopExpr.getExpr().obj.getType();
        Struct termType = addopExpr.getTerm().obj.getType();
        if (exprType.equals(MJTab.intType) && termType.equals(MJTab.intType)) {
            addopExpr.obj = new Obj(Obj.Var, "", MJTab.intType);
        } else if (exprType.equals(MJTab.INT_ARRAY_TYPE) && termType.equals(MJTab.INT_ARRAY_TYPE)) {
            addopExpr.obj = new Obj(Obj.Var, "", MJTab.INT_ARRAY_TYPE);
            vecPlusVecMethodIsUsed = true;
        } else {
            String operator;
            Addop addop = addopExpr.getAddop();
            if (addop instanceof PlusAddop) {
                operator = "+";
            } else {
                operator = "-";
            }
            if (detectErrors) {
                detectSemanticError(null, addopExpr, SemanticErrorKind.UNDEF_OP, exprType, termType, operator);
            }
            addopExpr.obj = MJTab.noObj;
            detectErrors = false;
        }
    }

    public void visit(FactorTerm factorTerm) {
        factorTerm.obj = factorTerm.getFactor().obj;
        if (factorTerm.obj.getType() == MJTab.noType && factorTerm.obj.getKind() != Obj.Meth) {
            detectErrors = false;
        }
    }

    public void visit(MulopTerm mulopTerm) {
        Obj termObj = mulopTerm.getTerm().obj;
        Struct termType = termObj.getType();
        Obj factorObj = mulopTerm.getFactor().obj;
        Struct factorType = factorObj.getType();
        if (termType.equals(MJTab.intType) && factorType.equals(MJTab.intType)) {
            mulopTerm.obj = new Obj(Obj.Var, "", MJTab.intType);
        } else if (termType.equals(MJTab.INT_ARRAY_TYPE) && factorType.equals(MJTab.INT_ARRAY_TYPE)) {
            mulopTerm.obj = new Obj(Obj.Var, "", MJTab.intType);
            vecTimesVecMethodIsUsed = true;
        } else if (termType.equals(MJTab.intType) && factorType.equals(MJTab.INT_ARRAY_TYPE)) {
            mulopTerm.obj = new Obj(Obj.Var, "", MJTab.INT_ARRAY_TYPE);
            scalarTimesVectorMethodIsUsed = true;
        } else if (termType.equals(MJTab.INT_ARRAY_TYPE) && factorType.equals(MJTab.intType)) {
            mulopTerm.obj = new Obj(Obj.Var, "", MJTab.INT_ARRAY_TYPE);
            vecTimesScalarMethodIsUsed = true;
        } else {
            if (factorType != MJTab.noType || factorObj.getKind() == Obj.Meth) {
                String operator;
                Mulop mulop = mulopTerm.getMulop();
                if (mulop instanceof TimesMulop) {
                    operator = "*";
                } else if (mulop instanceof DivMulop) {
                    operator = "/";
                } else {
                    operator = "%";
                }
                if (detectErrors) {
                    detectSemanticError(null, mulopTerm, SemanticErrorKind.UNDEF_OP, termType, factorType, operator);
                }
            }
            mulopTerm.obj = MJTab.noObj;
            detectErrors = false;
        }
    }

    public void visit(DesignatorFactor designatorFactor) {
        designatorFactor.obj = designatorFactor.getDesignator().obj;
    }

    public void visit(MethodCallFactor methodCallFactor) {
        Obj methodObj = methodCallFactor.getDesignator().obj;

        MethodSignatureGenerator invokedMethodSignatureGenerator = new MethodSignatureGenerator();
        methodCallFactor.traverseTopDown(invokedMethodSignatureGenerator);
        if (methodObj.getKind() != Obj.Meth) {
            if (invokedMethodSignatureGenerator.getMethodSignature() instanceof ClassMethodSignature) {
                ClassMethodSignature classMethodSignature = (ClassMethodSignature) invokedMethodSignatureGenerator
                        .getMethodSignature();
                if (classMethodSignature.getThisParameterType() != MJTab.noType
                        && classMethodSignature.getThisParameterType().getElemType() != MJTab.noType
                        && !classMethodSignature.containsUndeclaredType()) {
                    if (classMethodSignature.getThisParameterType().getKind() != Struct.Class) {
                        detectSemanticError(null, methodCallFactor, SemanticErrorKind.UNINVOKABLE_METHOD,
                                invokedMethodSignatureGenerator.getMethodSignature(),
                                classMethodSignature.getThisParameterType());
                    } else {
                        detectSemanticError(null, methodCallFactor, SemanticErrorKind.UNDEF_METHOD,
                                invokedMethodSignatureGenerator.getMethodSignature());
                    }
                }
            } else {
                GlobalMethodSignature globalMethodSignature = (GlobalMethodSignature) invokedMethodSignatureGenerator
                        .getMethodSignature();
                if (!globalMethodSignature.containsUndeclaredType()) {
                    detectSemanticError(null, methodCallFactor, SemanticErrorKind.UNDEF_METHOD,
                            invokedMethodSignatureGenerator.getMethodSignature());
                }
            }
        } else {
            MethodSignature methodSignature = null;
            try {
                if (isGlobalMethod(methodObj)) {
                    methodSignature = new GlobalMethodSignature(methodObj);
                } else {
                    methodSignature = new ClassMethodSignature(methodObj, thisParameterObjs.peek().getType());
                }
            } catch (WrongObjKindException e) {
            }
            if (methodSignature != null) {
                if (!methodSignature.isInvokableBy(invokedMethodSignatureGenerator.getMethodSignature())) {
                    Obj overriddenMethodObj = findNearestDeclaration(
                            invokedMethodSignatureGenerator.getMethodSignature(), thisParameterObjs.pop());
                    if (overriddenMethodObj.equals(Tab.noObj)) {
                        if (!invokedMethodSignatureGenerator.getMethodSignature().containsUndeclaredType()) {
                            detectSemanticError(null, methodCallFactor, SemanticErrorKind.INAPPLICABLE_METHOD,
                                    methodSignature.toString(),
                                    invokedMethodSignatureGenerator.getMethodSignature().getParameterList());
                        } else {
                            detectSemanticError();
                        }
                    } else {
                        methodObj = overriddenMethodObj;
                    }
                }
            } else {
                detectSemanticError();
            }
        }
        methodCallFactor.obj = methodObj;
    }

    public void visit(IntFactor intFactor) {
        intFactor.obj = new Obj(Obj.Con, "", MJTab.intType, intFactor.getValue(), 1);
    }

    public void visit(CharFactor charFactor) {
        charFactor.obj = new Obj(Obj.Con, "", MJTab.charType, charFactor.getValue(), 1);
    }

    public void visit(BoolFactor boolFactor) {
        boolFactor.obj = new Obj(Obj.Con, "", MJTab.BOOL_TYPE, boolFactor.getValue() ? 1 : 0, 1);
    }

    public void visit(NewScalarFactor newScalarFactor) {
        newScalarFactor.obj = newScalarFactor.getType().obj;
        if (newScalarFactor.obj.getType().getKind() == Struct.Class) {
            symbolUsageMJLogger.log(newScalarFactor.obj, newScalarFactor.getLine(), null);
        }
    }

    public void visit(NewVectorFactor newVectorFactor) {
        newVectorFactor.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, newVectorFactor.getType().obj.getType()));
    }

    public void visit(DelimitedFactor delimitedFactor) {
        delimitedFactor.obj = delimitedFactor.getExpr().obj;
    }

    public void visit(IdentDesignator identDesignator) {
        String identDesignatorIdent = identDesignator.getIdent();
        Obj identObj = Tab.noObj;
        SyntaxNode parent = identDesignator.getParent();

        if (currentScopeType == ScopeType.CLASS_METHOD) {
            identObj = findNearestDeclaration(identDesignatorIdent,
                    (parent instanceof MethodCallFactor) || (parent instanceof MethodCallDesignatorStatement));
        } else { // currentScopeType == GLOBAL_METHOD
            if ((parent instanceof MethodCallFactor) || (parent instanceof MethodCallDesignatorStatement)) {
                identObj = findInSomeOuterScope(identDesignatorIdent);
            } else {
                identObj = findInCurrentOrSomeOuterScope(identDesignatorIdent);
            }
        }
        if (identObj == MJTab.noObj || identObj.getKind() == Obj.Type || identObj.getKind() == Obj.Prog) {
            identObj = new Obj(Obj.NO_VALUE, identDesignatorIdent, MJTab.noType);
            if (!(parent instanceof MethodCallFactor) && !(parent instanceof MethodCallDesignatorStatement)) {
                detectSemanticError(identObj, identDesignator, SemanticErrorKind.UNRESOLVED_VARIABLE);
            } else {
                detectSemanticError();
            }
            symbolUsageMJLogger.log(identObj, identDesignator.getLine(), null, currentMethodObj);
        } else {
            symbolUsageMJLogger.log(identObj, identDesignator.getLine(), null, currentMethodObj);
        }

        identDesignator.obj = identObj;

        if (identObj.getKind() == Obj.Meth) {
            thisParameterObjs.push(new Obj(Obj.Var, "this", currentClassObj.getType(), 0, 1));
        }
    }

    public void visit(ArrayElemAccessDesignator arrayElemAcessDesignator) {
        Obj array = arrayElemAcessDesignator.getDesignatorStart().obj;
        if (array.getType().getKind() != Struct.Array) {
            if (!array.getType().equals(MJTab.noType)) {
                detectSemanticError(null, arrayElemAcessDesignator, SemanticErrorKind.INDEXING_NON_ARRAY,
                        array.getType());
            } else {
                detectSemanticError();
            }
            arrayElemAcessDesignator.obj = MJTab.noObj;
        } else {
            Struct indexType = arrayElemAcessDesignator.getExpr().obj.getType();
            if (!indexType.equals(MJTab.intType)) {
                detectSemanticError(null, arrayElemAcessDesignator, SemanticErrorKind.TYPE_MISMATCH, indexType,
                        MJTab.intType);
            }
            arrayElemAcessDesignator.obj = new Obj(Obj.Elem, "",
                    array.getType().getElemType() != null ? array.getType().getElemType() : MJTab.noType);
        }
        symbolUsageMJLogger.log(array, arrayElemAcessDesignator.getLine(), null, array);
    }

    public void visit(MemberAccessDesignator memberAccessDesignator) {
        String memberName = memberAccessDesignator.getIdent();

        Obj designatorStartObj = memberAccessDesignator.getDesignatorStart().obj;
        Obj memberAccessDesignatorObj = new Obj(Obj.NO_VALUE, memberName, MJTab.noType);

        if (designatorStartObj.getType().getKind() != Struct.Class) {
            if (designatorStartObj.getType() != MJTab.noType
                    && designatorStartObj.getType().getElemType() != MJTab.noType) {
                detectSemanticError(null, memberAccessDesignator, SemanticErrorKind.ACCESSING_MEMBER_OF_NON_OBJECT,
                        designatorStartObj.getType());
            } else {
                detectSemanticError();
            }
        } else {
            memberAccessDesignatorObj = findNearestDeclaration(memberName, designatorStartObj);
            if (memberAccessDesignatorObj != Tab.noObj) {
                symbolUsageMJLogger.log(memberAccessDesignatorObj, memberAccessDesignator.getLine(), null);
            } else {
                memberAccessDesignatorObj = new Obj(Obj.NO_VALUE, memberName, MJTab.noType);
                detectSemanticError(memberAccessDesignatorObj, memberAccessDesignator,
                        SemanticErrorKind.UNRESOLVED_MEMBER);
                symbolUsageMJLogger.log(memberAccessDesignatorObj, memberAccessDesignator.getLine(), null);
                memberAccessDesignator.obj = MJTab.noObj;
            }
        }

        memberAccessDesignator.obj = memberAccessDesignatorObj;

        if (memberAccessDesignator.obj.getKind() == Obj.Meth) {
            thisParameterObjs.push(memberAccessDesignator.getDesignatorStart().obj);
        }
    }

    public void visit(IdentDesignatorStart identDesignatorStart) {
        String identDesignatorStartIdent = identDesignatorStart.getIdent();
        Obj identObj = Tab.noObj;

        if (currentScopeType == ScopeType.CLASS_METHOD) {
            identObj = findNearestDeclaration(identDesignatorStartIdent, false);
        } else { // currentScopeType == GLOBAL_METHOD
            identObj = findInCurrentOrSomeOuterScope(identDesignatorStartIdent);
        }
        if (identObj == MJTab.noObj || identObj.getKind() == Obj.Type || identObj.getKind() == Obj.Prog) {
            identObj = new Obj(Obj.NO_VALUE, identDesignatorStartIdent, MJTab.noType);
            detectSemanticError(identObj, identDesignatorStart, SemanticErrorKind.UNRESOLVED_VARIABLE);
            symbolUsageMJLogger.log(identObj, identDesignatorStart.getLine(), null, currentMethodObj);
        } else {
            symbolUsageMJLogger.log(identObj, identDesignatorStart.getLine(), null, currentMethodObj);
        }

        identDesignatorStart.obj = identObj;
    }

    public void visit(ArrayElemAccessDesignatorStart arrayElemAcessDesignatorStart) {
        Obj array = arrayElemAcessDesignatorStart.getDesignatorStart().obj;
        if (array.getType().getKind() != Struct.Array) {
            if (!array.getType().equals(MJTab.noType)) {
                detectSemanticError(null, arrayElemAcessDesignatorStart, SemanticErrorKind.INDEXING_NON_ARRAY,
                        array.getType());
            } else {
                detectSemanticError();
            }
            arrayElemAcessDesignatorStart.obj = MJTab.noObj;
        } else {
            Struct indexType = arrayElemAcessDesignatorStart.getExpr().obj.getType();
            if (!indexType.equals(MJTab.intType)) {
                detectSemanticError(null, arrayElemAcessDesignatorStart, SemanticErrorKind.TYPE_MISMATCH, indexType,
                        MJTab.intType);
            }
            arrayElemAcessDesignatorStart.obj = new Obj(Obj.Elem, "",
                    array.getType().getElemType() != null ? array.getType().getElemType() : MJTab.noType);
        }
        symbolUsageMJLogger.log(array, arrayElemAcessDesignatorStart.getLine(), null, array);
    }

    public void visit(MemberAccessDesignatorStart memberAccessDesignatorStart) {
        String memberName = memberAccessDesignatorStart.getIdent();

        Obj designatorStartObj = memberAccessDesignatorStart.getDesignatorStart().obj;
        Obj memberAccessDesignatorStartObj = new Obj(Obj.NO_VALUE, memberName, MJTab.noType);

        if (designatorStartObj.getType().getKind() != Struct.Class) {
            if (designatorStartObj.getType() != MJTab.noType
                    && designatorStartObj.getType().getElemType() != MJTab.noType) {
                detectSemanticError(null, memberAccessDesignatorStart, SemanticErrorKind.ACCESSING_MEMBER_OF_NON_OBJECT,
                        designatorStartObj.getType());
            } else {
                detectSemanticError();
            }
        } else {
            memberAccessDesignatorStartObj = findNearestDeclaration(memberName, designatorStartObj);
            if (memberAccessDesignatorStartObj != MJTab.noObj) {
                symbolUsageMJLogger.log(memberAccessDesignatorStartObj, memberAccessDesignatorStart.getLine(), null);
            } else {
                memberAccessDesignatorStartObj = new Obj(Obj.NO_VALUE, memberName, MJTab.noType);
                detectSemanticError(memberAccessDesignatorStartObj, memberAccessDesignatorStart,
                        SemanticErrorKind.UNRESOLVED_MEMBER);
                symbolUsageMJLogger.log(memberAccessDesignatorStartObj, memberAccessDesignatorStart.getLine(), null);
                memberAccessDesignatorStart.obj = MJTab.noObj;
            }
        }

        memberAccessDesignatorStart.obj = memberAccessDesignatorStartObj;
    }

    public boolean printBoolMethodIsUsed() {
        return printBoolMethodIsUsed;
    }

    public boolean readBoolMethodIsUsed() {
        return readBoolMethodIsUsed;
    }

    public boolean vecTimesVecMethodIsUsed() {
        return vecTimesVecMethodIsUsed;
    }

    public boolean vecPlusVecMethodIsUsed() {
        return vecPlusVecMethodIsUsed;
    }

    public boolean vecTimesScalarMethodIsUsed() {
        return vecTimesScalarMethodIsUsed;
    }

    public boolean scalarTimesVectorMethodIsUsed() {
        return scalarTimesVectorMethodIsUsed;
    }

}