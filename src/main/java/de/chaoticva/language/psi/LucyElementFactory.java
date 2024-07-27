package de.chaoticva.language.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import de.chaoticva.language.LucyFileType;
import de.chaoticva.language.LucyLanguage;

import java.util.Objects;

public class LucyElementFactory {
    public static LucyVarDef createVarDef(Project project, String name) {
        LucyFile lucyFile = createFile(project, name);
        return (LucyVarDef) lucyFile.getFirstChild();
    }

    public static LucyFile createFile(Project project, String name) {
        String fileName = "dummy.lc";
        return (LucyFile) PsiFileFactory.getInstance(project).createFileFromText(fileName, LucyFileType.INSTANCE, name);
    }

    public static PsiElement createConstElement(Project project) {
        LucyFile dummyFile = (LucyFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.lc", LucyLanguage.INSTANCE, "const");
        return PsiTreeUtil.firstChild(dummyFile);
    }

    public static PsiElement createWhitespace(Project project) {
        LucyFile dummyFile = (LucyFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.lc", LucyLanguage.INSTANCE, "var ");
        return PsiTreeUtil.lastChild(dummyFile);
    }

    public static PsiElement createType(Project project, String type) {
        LucyFile dummyFile = (LucyFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.lc", LucyLanguage.INSTANCE, type);
        return PsiTreeUtil.firstChild(dummyFile);
    }

    public static PsiElement createDef(Project project, LucyDefCall defCall) {
        StringBuilder builder = new StringBuilder();
        for (LucyArgument arg : defCall.getArgumentList()) {
            int index = defCall.getArgumentList().indexOf(arg) + 1;
            builder.append("auto arg%s, ".formatted(index));
        }

        String parameters = builder.isEmpty() ? "" : builder.substring(0, builder.length() - 2);

        LucyFile dummyFile = (LucyFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.lc", LucyLanguage.INSTANCE, "def auto %s(%s) {\n".formatted(defCall.getName(), parameters) + "}");
        return Objects.requireNonNull(PsiTreeUtil.getChildrenOfType(dummyFile, LucyDefDef.class))[0];
    }

    public static PsiElement createNewLine(Project project) {
        LucyFile dummyFile = (LucyFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.lc", LucyLanguage.INSTANCE, "var\n");
        return PsiTreeUtil.lastChild(dummyFile);
    }
}
