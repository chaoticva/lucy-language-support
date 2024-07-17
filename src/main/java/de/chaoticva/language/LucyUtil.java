package de.chaoticva.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import de.chaoticva.language.psi.LucyDefDef;
import de.chaoticva.language.psi.LucyFile;
import de.chaoticva.language.psi.LucyVarDef;

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
            LucyFile simpleFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile == null) continue;
            LucyVarDef[] varDefs = PsiTreeUtil.getChildrenOfType(simpleFile, LucyVarDef.class);
            if (varDefs == null) continue;
            Collections.addAll(result, varDefs);
        }
        return result;
    }

    public static List<LucyDefDef> findDefDefs(Project project) {
        List<LucyDefDef> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles = FileTypeIndex.getFiles(LucyFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            LucyFile simpleFile = (LucyFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile == null) continue;
            LucyDefDef[] defDefs = PsiTreeUtil.getChildrenOfType(simpleFile, LucyDefDef.class);
            if (defDefs == null) continue;
            Collections.addAll(result, defDefs);
        }
        return result;
    }
}
