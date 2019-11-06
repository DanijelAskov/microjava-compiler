/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.methodsignature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

/**
 *
 * @author Danijel Askov
 */
public abstract class MethodSignature {

    private Obj method;

    private String methodName;
    private final List<Struct> parameters = new ArrayList<>();
    private String compactParameterList = "";
    private String parameterList = "";
    private boolean containsUndeclaredType = false;

    public MethodSignature(String name) {
        methodName = name;
    }

    public void setContainsUndeclaredType() {
        containsUndeclaredType = true;
    }

    public boolean containsUndeclaredType() {
        return containsUndeclaredType;
    }

    public void addParameter(Struct parameter) {
        parameters.add(parameter);
        compactParameterList += MJUtils.typeToString(parameter);
        parameterList += (parameterList.equals("") ? "" : ", ") + MJUtils.typeToString(parameter);
    }

    public void addParameter(Obj parameter) {
        addParameter(parameter.getType());
    }

    public MethodSignature(Obj method, boolean hasThisParameter) throws WrongObjKindException {
        if (method.getKind() != Obj.Meth) {
            throw new WrongObjKindException();
        }
        this.method = method;
        methodName = method.getName();
        Iterator<Obj> parametersIterator = method.getLocalSymbols().iterator();
        int parameterCount = method.getLevel();
        int i = hasThisParameter ? 1 : 0;
        if (hasThisParameter) {
            parametersIterator.next();
        }
        while (i < parameterCount) {
            Obj currentParameter = parametersIterator.next();
            this.parameters.add(currentParameter.getType());
            String parameterType = MJUtils.typeToString(currentParameter.getType());
            compactParameterList += parameterType;
            parameterList += parameterType;
            if (i < parameterCount - 1) {
                parameterList += ", ";
            }
            i++;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (super.equals(object)) {
            return true;
        } else {
            if (!(object instanceof MethodSignature)) {
                return false;
            } else {
                MethodSignature other = (MethodSignature) object;
                if (!methodName.equals(other.methodName)) {
                    return false;
                } else {
                    if (parameters.size() != other.parameters.size()) {
                        return false;
                    } else {
                        Iterator<Struct> thisParametersIterator = parameters.iterator();
                        Iterator<Struct> otherParametersIterator = other.parameters.iterator();
                        while (thisParametersIterator.hasNext()) {
                            if (!thisParametersIterator.next().equals(otherParametersIterator.next())) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
    }

    public boolean isInvokableBy(MethodSignature other) {
        if (!methodName.equals(other.methodName)) {
            return false;
        } else {
            if (parameters.size() != other.parameters.size()) {
                return false;
            } else {
                Iterator<Struct> thisParametersIterator = parameters.iterator();
                Iterator<Struct> otherParametersIterator = other.parameters.iterator();
                while (thisParametersIterator.hasNext()) {
                    if (!MJUtils.assignableTo(otherParametersIterator.next(), thisParametersIterator.next())) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public Obj getMethod() {
        return method;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getParameterList() {
        return "(" + (parameterList != null ? parameterList : "") + ")";
    }

    public String getSignature() {
        return methodName + " (" + (parameterList != null ? parameterList : "") + ")";
    }

    public String getCompactSignature() {
        return methodName + (compactParameterList != null ? compactParameterList : "");
    }

    @Override
    public String toString() {
        return getSignature();
    }

}
