package de.chaoticva.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.LucyUtil;
import de.chaoticva.language.psi.*;

public class LucyPsiImplUtil {
    public static String getName(LucyVarDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getName(LucyDefCall el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getName(LucyReassign el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static boolean isConst(LucyVarDef el) {
        ASTNode constNode = el.getNode().findChildByType(LucyTypes.CONST);
        return constNode != null;
    }

    public static boolean isConst(LucyParameter el) {
        ASTNode constNode = el.getNode().findChildByType(LucyTypes.CONST);
        return constNode != null;
    }

    public static PsiElement getNameEl(LucyVarDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getPsi();
    }

    public static PsiElement getNameEl(LucyParameter el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getPsi();
    }

    public static String getValue(LucyVarDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.EXPR);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getValue(LucyExpr el) {
        ASTNode stringNode = el.getNode().findChildByType(LucyTypes.STRING);
        ASTNode numberNode = el.getNode().findChildByType(LucyTypes.NUMBER);
        ASTNode booleanNode = el.getNode().findChildByType(LucyTypes.BOOLEAN);
        ASTNode defCallNode = el.getNode().findChildByType(LucyTypes.DEF_CALL);
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (stringNode != null) return stringNode.getText().substring(1, stringNode.getText().length() - 1);
        if (numberNode != null) return numberNode.getText();
        if (booleanNode != null) return booleanNode.getText();
        if (defCallNode != null) return defCallNode.getText();
        if (identifierNode != null) return identifierNode.getText();

        return null;
    }

    public static String getType(LucyExpr el) {
        ASTNode factorNode = el.getNode().findChildByType(LucyTypes.FACTOR);
        if (factorNode == null) return null;
        return getType((LucyFactor) factorNode.getPsi());
    }

    public static String getType(LucyFactor el) {
        ASTNode stringNode = el.getNode().findChildByType(LucyTypes.STRING);
        ASTNode charNode = el.getNode().findChildByType(LucyTypes.CHAR);
        ASTNode numberNode = el.getNode().findChildByType(LucyTypes.NUMBER);
        ASTNode booleanNode = el.getNode().findChildByType(LucyTypes.BOOLEAN);
        ASTNode defCallNode = el.getNode().findChildByType(LucyTypes.DEF_CALL);
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        ASTNode newNode = el.getNode().findChildByType(LucyTypes.INSTANCE_DEF);
        if (stringNode != null) return "str";
        if (numberNode != null) return "num";
        if (charNode != null) return "char";
        if (booleanNode != null) return "bool";
        if (defCallNode != null) return LucyUtil.getDeclaration((LucyDefCall) defCallNode.getPsi()).getType().getText();
        if (identifierNode != null) return LucyUtil.getDeclaration(identifierNode.getPsi()).getType().getText();
        if (newNode != null) return ((LucyInstanceDef) newNode.getPsi()).getIdentifier().getText().split("\\(")[0];

        return null;
    }

    public static PsiElement getNameIdentifier(LucyDefDef element) {
        ASTNode keyNode = element.getNode().findChildByType(LucyTypes.IDENTIFIER);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public static String getName(LucyDefDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getName(LucyParameter el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }
}
