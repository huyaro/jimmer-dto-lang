package dev.huyaro.lang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DtoCompletionContributor extends CompletionContributor {

    private final Set<String> keywords = Set.of(
            "allScalars", "abstract", "input", "input-only",
            "Boolean", "Character", "Byte", "Short", "Int", "Long", "Float", "Double", "Any", "String",
            "Array", "Iterable", "MutableIterable", "Collection", "MutableCollection",
            "List", "MutableList", "Set", "MutableSet", "Map", "MutableMap"
    );

    public DtoCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(DtoTypes.ID),
                new CompletionProvider<>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        keywords.stream()
                                .filter(key -> resultSet.getPrefixMatcher().prefixMatches(key))
                                .forEach(key -> resultSet.addElement(LookupElementBuilder.create(key).bold()));
                    }
                }
        );
    }

}