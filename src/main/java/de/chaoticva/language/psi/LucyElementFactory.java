package de.chaoticva.language.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import de.chaoticva.language.LucyFileType;

public class LucyElementFactory {
    public static LucyVarDef createVarDef(Project project, String name) {
        LucyFile lucyFile = createFile(project, name);
        return (LucyVarDef) lucyFile.getFirstChild();
    }

    public static LucyFile createFile(Project project, String name) {
        String fileName = "dummy.lc";
        return (LucyFile) PsiFileFactory.getInstance(project).createFileFromText(fileName, LucyFileType.INSTANCE, name);
    }
}
