package de.chaoticva.language;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import de.chaoticva.language.psi.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LucyUtil {
    public static List<LucyVarDef> findVarDefs(Project project, String name) {
        List<LucyVarDef> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(LucyFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LucyFile lucyFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (lucyFile == null) continue;
            LucyVarDef[] properties = PsiTreeUtil.getChildrenOfType(lucyFile, LucyVarDef.class);
            if (properties == null) continue;
            for (LucyVarDef property : properties) {
                if (!name.equals(property.getName())) continue;
                result.add(property);
            }
        }
        return result;
    }

    public static List<LucyVarDef> findVarDefs(Project project) {
        List<LucyVarDef> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(LucyFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LucyFile lucyFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (lucyFile == null) continue;
            LucyVarDef[] varDefs = PsiTreeUtil.getChildrenOfType(lucyFile, LucyVarDef.class);
            if (varDefs == null) continue;
            Collections.addAll(result, varDefs);
        }
        return result;
    }

    public static LucyDefDef findDefDefs(Project project, String name) {
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(LucyFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LucyFile lucyFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (lucyFile == null) continue;
            LucyDefDef[] defDefs = PsiTreeUtil.getChildrenOfType(lucyFile, LucyDefDef.class);
            if (defDefs == null) continue;
            for (LucyDefDef defDef : defDefs) {
                if (defDef.getName().equals(name)) {
                    return defDef;
                }
            }
        }
        return null;
    }

    public static List<LucyDefDef> findDefDefs(LucyFile file) {
        List<LucyDefDef> result = new ArrayList<>();
        LucyDefDef[] defDefs = PsiTreeUtil.getChildrenOfType(file, LucyDefDef.class);
        if (defDefs == null) return Collections.emptyList();
        Collections.addAll(result, defDefs);
        return result;
    }

    public static List<LucyExpr> findExpressions(Project project, String name, LucyScope scope) {
        List<LucyExpr> result = new ArrayList<>();
        LucyExpr[] expressions = PsiTreeUtil.getChildrenOfType(scope, LucyExpr.class);
        if (expressions == null) return new ArrayList<>();
        for (LucyExpr expr : expressions) {
            for (LucyFactor factor : expr.getFactorList()) {
                ASTNode identifierNode = factor.getNode().findChildByType(LucyTypes.IDENTIFIER);
                if (identifierNode != null) {
                    if (name.equals(identifierNode.getText())) {
                        result.add(expr);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static List<LucyExpr> findExpressions(Project project, String name) {
        List<LucyExpr> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(LucyFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LucyFile lucyFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (lucyFile == null) continue;
            LucyExpr[] expressions = PsiTreeUtil.getChildrenOfType(lucyFile, LucyExpr.class);
            if (expressions == null) continue;
            for (LucyExpr expr : expressions) {
                for (LucyFactor factor : expr.getFactorList()) {
                    ASTNode identifierNode = factor.getNode().findChildByType(LucyTypes.IDENTIFIER);
                    if (identifierNode != null) {
                        if (name.equals(identifierNode.getText())) {
                            result.add(expr);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    // NEW
    public static boolean isReassigned(LucyVarDef varDef) {
        LucyReassign[] expressions = PsiTreeUtil.getChildrenOfType(varDef.getContainingFile(), LucyReassign.class);
        if (expressions == null) return false;
        for (LucyReassign expr : expressions) {
            if (expr.getName().equals(varDef.getName())) return true;
        }

        return false;
    }

    public static boolean isUsed(LucyVarDef varDef) {
        return isElementUsed(varDef, varDef.getContainingFile(), varDef.getName());
    }

    public static boolean isUsed(LucyParameter param) {
        return isElementUsed(param, param.getContainingFile(), param.getName());
    }

    private static boolean isElementUsed(LucyParameter varDef, PsiElement element, String varName) {
        if (element == null) {
            return false;
        }

        if (isVariableUsedInElement(element, varName)) {
            return true;
        }

        for (PsiElement child : element.getChildren()) {
            if (isElementUsed(varDef, child, varName)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isElementUsed(LucyVarDef varDef, PsiElement element, String varName) {
        if (element == null) {
            return false;
        }

        if (isVariableUsedInElement(element, varName)) {
            return true;
        }

        for (PsiElement child : element.getChildren()) {
            if (isElementUsed(varDef, child, varName)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isVariableUsedInElement(PsiElement element, String varName) {
        if (element instanceof LucyReassign reassignment) {
            return reassignment.getName().equals(varName);
        }

        if (element instanceof LucyDefDef defDef) {
            for (PsiElement child : defDef.getChildren()) {
                if (child instanceof LucyReassign reassignment) {
                    if (reassignment.getName().equals(varName)) {
                        return true;
                    }
                }
            }
        }

        if (element instanceof LucyDefCall defCall) {
            for (PsiElement child : defCall.getArgumentList()) {
                if (child.getText().contains(varName)) {
                    return true;
                }
            }
        }

        if (element instanceof LucyExpr) {
            for (PsiElement child : element.getChildren()) {
                if (child.getText().contains(varName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static @Nullable LucyVarDef getDeclaration(PsiElement el) {
        LucyVarDef[] varDefs = PsiTreeUtil.getChildrenOfType(el.getContainingFile(), LucyVarDef.class);
        if (varDefs == null) return null;
        for (LucyVarDef varDef : varDefs) {
            if (varDef.getName().equals(el.getText())) {
                return varDef;
            }
        }

        return null;
    }

    public static @Nullable LucyDefDef getDeclaration(LucyDefCall defCall) {
        LucyDefDef[] defDefs = PsiTreeUtil.getChildrenOfType(defCall.getContainingFile(), LucyDefDef.class);
        if (defDefs == null) return null;
        for (LucyDefDef defDef : defDefs) {
            if (defDef.getName().equals(defCall.getName())) {
                return defDef;
            }
        }

        return null;
    }

    public static @Nullable LucyVarDef getDeclaration(LucyReassign reassign) {
        LucyVarDef[] varDefs = PsiTreeUtil.getChildrenOfType(reassign.getContainingFile(), LucyVarDef.class);
        if (varDefs == null) return null;
        for (LucyVarDef varDef : varDefs) {
            if (varDef.getName().equals(reassign.getName())) {
                return varDef;
            }
        }

        return null;
    }

    public static LucyParameter getParameter(LucyDefDef defDef, String text) {
        for (LucyParameter parameter : defDef.getParameterList()) {
            if (parameter.getName().equals(text)) {
                return parameter;
            }
        }

        return null;
    }

    public boolean isCorrectType(LucyVarDef varDef) {
        String type = varDef.getType().getText();

        if (type.equals("auto")) return true;

        return false;
//        return type.equals(varDef.getExpr().getType());
    }
}
