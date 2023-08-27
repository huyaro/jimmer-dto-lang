package dev.huyaro.lang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.spellchecker.inspections.CommentSplitter;
import com.intellij.spellchecker.inspections.IdentifierSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import dev.huyaro.lang.psi.DtoExplicitProp;
import dev.huyaro.lang.psi.DtoTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class DtoSpellcheckingStrategy extends SpellcheckingStrategy {

    @Override
    public @NotNull Tokenizer<?> getTokenizer(PsiElement element) {
        if (element instanceof PsiComment) {
            return new DtoCommentTokenizer();
        }

        if (element instanceof DtoExplicitProp) {
            return new DtoExplicitPropTokenizer();
        }

        return EMPTY_TOKENIZER;
    }

    private static class DtoCommentTokenizer extends Tokenizer<PsiComment> {

        @Override
        public void tokenize(@NotNull PsiComment element, TokenConsumer consumer) {
            // Exclude the start of the comment with its # characters from spell checking
            int startIndex = 0;
            for (char c : element.textToCharArray()) {
                if (c == '/' || Character.isWhitespace(c)) {
                    startIndex++;
                } else {
                    break;
                }
            }
            consumer.consumeToken(element, element.getText(), false, 0,
                    TextRange.create(startIndex, element.getTextLength()),
                    CommentSplitter.getInstance());
        }

    }

    private static class DtoExplicitPropTokenizer extends Tokenizer<DtoExplicitProp> {

        public void tokenize(@NotNull DtoExplicitProp element, TokenConsumer consumer) {
            //Spell check the keys and values of properties with different splitters
            TokenSet filterTypes = TokenSet.create(DtoTypes.ALIAS_GROUP, DtoTypes.USER_PROP, DtoTypes.NEGATIVE_PROP,
                    DtoTypes.POSITIVE_PROP, DtoTypes.ALL_SCALARS);
            Arrays.stream(element.getNode().getChildren(filterTypes))
                    .map(n -> n.getChildren(TokenSet.create(DtoTypes.IDENTIFIER)))
                    .filter(nodes -> nodes.length > 0)
                    .flatMap(Arrays::stream)
                    .forEach(keyNode -> {
                        final PsiElement keyPsi = keyNode.getPsi();
                        final String text = keyNode.getText();
                        //For keys, use a splitter for identifiers
                        consumer.consumeToken(keyPsi, text, false, 0,
                                TextRange.create(0, text.length()),
                                IdentifierSplitter.getInstance());
                    });
        }
    }

}
