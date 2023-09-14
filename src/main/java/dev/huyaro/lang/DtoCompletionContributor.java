package dev.huyaro.lang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import dev.huyaro.lang.psi.DtoTypes;
import kotlinx.html.MAP;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.element.Name;
import java.util.*;
import java.util.stream.Collectors;

public class DtoCompletionContributor extends CompletionContributor {

    private static final List<String> keywords;

    static {
        keywords = InsideType.getAllTypes();
    }

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

    /**
     * 内置类型合集
     */
    private enum InsideType {
        NATIVE(List.of("Boolean", "Char", "Byte", "Short", "Int", "Long", "Float", "Double")),
        ANY(List.of("Any", "String")),
        ARRAY(List.of("Array")),
        COLLECTION(List.of("Iterable", "MutableIterable", "Collection", "MutableCollection",
                "List", "MutableList", "Set", "MutableSet")),
        MAPPING(List.of("Map", "MutableMap"));

        private final List<String> value;

        InsideType(List<String> value) {
            this.value = value;
        }

        public static List<String> getAllTypes() {
            List<String> allTypes = new ArrayList<>(NATIVE.value);
            allTypes.addAll(NATIVE.value.stream().map(key -> key + "?").toList());
            List<String> arrayTypes = ARRAY.value.stream()
                    .map(arr -> allTypes.stream().map(key -> arr + "<" + key + ">").toList())
                    .flatMap(Collection::stream)
                    .toList();
            allTypes.addAll(arrayTypes);
            allTypes.addAll(Set.of("Array<*>", "Array<>"));
            allTypes.addAll(ANY.value);
            allTypes.addAll(COLLECTION.value.stream().map(key -> key + "<E>").toList());
            allTypes.addAll(MAPPING.value.stream().map(key -> key + "<K,V>").toList());
            return allTypes;
        }
    }
}