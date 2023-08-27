package dev.huyaro.lang;

import com.intellij.lang.Language;

public class DtoLanguage extends Language {

    public static final DtoLanguage INSTANCE = new DtoLanguage();

    private DtoLanguage() {
        super("dto");
    }

}
