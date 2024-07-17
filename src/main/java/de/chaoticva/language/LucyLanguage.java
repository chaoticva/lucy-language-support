package de.chaoticva.language;

import com.intellij.lang.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LucyLanguage extends Language {
    public static final LucyLanguage INSTANCE = new LucyLanguage();
    private Logger logger = null;

    public Logger logger() {
        if (logger == null)
            logger = LogManager.getLogger(getClass());
        return logger;
    }

    public LucyLanguage() {
        super("Lucy");
    }
}
