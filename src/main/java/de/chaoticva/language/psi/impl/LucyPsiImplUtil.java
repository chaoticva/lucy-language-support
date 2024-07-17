package de.chaoticva.language.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import de.chaoticva.language.psi.*;

public class LucyPsiImplUtil {
    public static String getName(LucyVarDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getTypeText(LucyVarDef el) {
        ASTNode typeNode = el.getNode().findChildByType(LucyTypes.TYPE);
        if (typeNode == null) return "any";
        return typeNode.getText().split(":")[1].trim();
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

    public static PsiElement setName(LucyVarDef el, String newName) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return el;
        LucyVarDef varDef = LucyElementFactory.createVarDef(el.getProject(), newName);
        ASTNode newKeyNode = varDef.getFirstChild().getNode();
        el.getNode().replaceChild(identifierNode, newKeyNode);

        return el;
    }

    public static PsiElement getNameIdentifier(LucyVarDef element) {
        ASTNode keyNode = element.getNode().findChildByType(LucyTypes.IDENTIFIER);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public static String getName(LucyDefDef el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getTypeText(LucyDefDef el) {
        ASTNode typeNode = el.getNode().findChildByType(LucyTypes.TYPE);
        if (typeNode == null) return "void";
        return typeNode.getText().split(":")[1].trim();
    }

    public static String getName(LucyParameter el) {
        ASTNode identifierNode = el.getNode().findChildByType(LucyTypes.IDENTIFIER);
        if (identifierNode == null) return null;
        return identifierNode.getText();
    }

    public static String getTypeText(LucyParameter el) {
        ASTNode typeNode = el.getNode().findChildByType(LucyTypes.TYPE);
        if (typeNode == null) return "any";
        return typeNode.getText().split(":")[1].trim();
    }
}
