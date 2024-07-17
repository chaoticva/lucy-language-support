package de.chaoticva.language;

import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import de.chaoticva.language.psi.LucyDefDef;
import de.chaoticva.language.psi.LucyFile;
import de.chaoticva.language.psi.LucyScope;
import de.chaoticva.language.psi.LucyVarDef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LucyStructureAwareNavbar extends StructureAwareNavBarModelExtension {
    @NotNull
    @Override
    protected Language getLanguage() {
        return LucyLanguage.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object object) {
        if (object instanceof LucyFile) {
            return ((LucyFile) object).getName();
        }
        if (object instanceof LucyVarDef) {
            return ((LucyVarDef) object).getName();
        }
        if (object instanceof LucyDefDef) {
            return ((LucyDefDef) object).getName();
        }
        if (object instanceof LucyScope) {
            return "scope";
        }

        return null;
    }

    @Override
    public @Nullable Icon getIcon(Object object) {
//        if (object instanceof LucyFile) {
//            return LucyIcons.FILE;
//        }
        if (object instanceof LucyVarDef) {
            return AllIcons.Nodes.Variable;
        }
        if (object instanceof LucyDefDef) {
            return AllIcons.Nodes.Method;
        }

        return null;
    }
}
