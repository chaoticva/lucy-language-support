package de.chaoticva.language.psi;

import com.intellij.psi.tree.IElementType;
import de.chaoticva.language.LucyLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LucyTokenType extends IElementType {
    public LucyTokenType(@NonNls @NotNull String debugName) {
        super(debugName, LucyLanguage.INSTANCE);
    }
}
