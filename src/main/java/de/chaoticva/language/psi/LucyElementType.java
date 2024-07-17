package de.chaoticva.language.psi;

import com.intellij.psi.tree.IElementType;
import de.chaoticva.language.LucyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LucyElementType extends IElementType {
    public LucyElementType(@NonNls @NotNull String debugName) {
        super(debugName, LucyLanguage.INSTANCE);
    }
}
