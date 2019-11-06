/*
 * Copyright (C) 2018 Danijel Askov
 */

package askov.schoolprojects.compilerconstruction.mjcompiler.vmt;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import askov.schoolprojects.compilerconstruction.mjcompiler.exceptions.WrongObjKindException;
import askov.schoolprojects.compilerconstruction.mjcompiler.mjsymboltable.MJTab;
import askov.schoolprojects.compilerconstruction.mjcompiler.util.MJUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;

/**
 *
 * @author Danijel Askov
 */
public class VMT {

    private final Set<Obj> methods = new HashSet<>();
    private int size;

    public static final int NAME_ADDR_SEPARATOR = -1;
    public static final int TABLE_TERMINATOR = -2;

    public boolean add(Obj method) throws WrongObjKindException {
        if (method == null) {
            throw new NullPointerException();
        }
        if (method.getKind() != Obj.Meth) {
            throw new WrongObjKindException();
        }
        if (methods.contains(method)) {
            return false;
        } else {
            methods.add(method);
            size += MJUtils.getCompactClassMethodSignature(method).length() + 2;
            return true;
        }
    }

    private Obj sourceWord = new Obj(Obj.Con, "$currentChar", MJTab.charType);
    private Obj destinationWord = new Obj(Obj.Var, "$currentWordInStaticMemoryZone", MJTab.intType, 0, 0);

    private void putInStaticMemoryZone(int word) {
        sourceWord.setAdr(word);
        Code.load(sourceWord);
        Code.store(destinationWord);
        destinationWord.setAdr(destinationWord.getAdr() + 1);
    }

    public void generateCreationCode() {
        if (!methods.isEmpty()) {
            Iterator<Obj> iterator = methods.iterator();
            while (iterator.hasNext()) {
                Obj method = iterator.next();
                String methodSignature;
                try {
                    methodSignature = MJUtils.getCompactClassMethodSignature(method);
                } catch (WrongObjKindException e) {
                    methodSignature = null;
                    e.printStackTrace();
                }
                Integer methodAddress = method.getAdr();
                for (int i = 0; i < methodSignature.length(); i++) {
                    putInStaticMemoryZone(methodSignature.charAt(i));
                }
                putInStaticMemoryZone(NAME_ADDR_SEPARATOR);
                putInStaticMemoryZone(methodAddress.intValue());
            }
            putInStaticMemoryZone(TABLE_TERMINATOR);
        }
    }

    public int getSize() {
        return size != 0 ? size + 1 : 0;
    }

    public void setStartAddress(int startAddress) {
        destinationWord.setAdr(startAddress);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VMT {\n");
        int i = 1;
        Iterator<Obj> iterator = methods.iterator();
        while (iterator.hasNext()) {
            Obj method = iterator.next();
            stringBuilder
                    .append("(" + i++ + ") " + MJUtils.typeToString(method.getType()) + " " + method.getName() + "(");
            int formParsNumber = method.getLevel();
            int currentFormPar = 0;
            Iterator<Obj> pars = method.getLocalSymbols().iterator();
            while (pars.hasNext() && currentFormPar < formParsNumber) {
                stringBuilder.append(MJUtils.typeToString(pars.next().getType()));
                currentFormPar++;
                if (pars.hasNext() && currentFormPar < formParsNumber) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append(")");
            stringBuilder.append(" -> " + method.getAdr() + (iterator.hasNext() ? "\n" : ""));
        }
        return stringBuilder.toString() + "\n}";
    }

    public boolean isEmpty() {
        return methods.isEmpty();
    }

    public boolean containsSameSignatureMethod(Obj overriddenMethod) {
        for (Obj method : methods) {
            try {
                if (MJUtils.haveSameSignatures(method, overriddenMethod)) {
                    return true;
                }
            } catch (WrongObjKindException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean containsMethod(Obj method) {
        return methods.contains(method);
    }

    public Obj getSameSignatureMethod(Obj overriddenMethod) {
        for (Obj method : methods) {
            try {
                if (MJUtils.haveSameSignatures(method, overriddenMethod)) {
                    return method;
                }
            } catch (WrongObjKindException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
