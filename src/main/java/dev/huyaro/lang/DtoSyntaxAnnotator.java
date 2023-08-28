package dev.huyaro.lang;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import dev.huyaro.lang.psi.DtoAnnotationName;
import dev.huyaro.lang.psi.DtoVisitor;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class DtoSyntaxAnnotator implements Annotator {

    public static final TextAttributesKey DTO_ANNOTATION_NAME =
            createTextAttributesKey("DTO_ANNOTATION_NAME", DefaultLanguageHighlighterColors.METADATA);

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        element.accept(new DtoSyntaxAnnotatorVisitor(holder));
    }

    private static class DtoSyntaxAnnotatorVisitor extends DtoVisitor {
        private final AnnotationHolder myHolder;

        public DtoSyntaxAnnotatorVisitor(AnnotationHolder holder) {
            this.myHolder = holder;
        }

        @Override
        public void visitAnnotationName(@NotNull DtoAnnotationName o) {
            super.visitAnnotationName(o);
            applyTextAttributes(o, DTO_ANNOTATION_NAME);
        }

        private void applyTextAttributes(DtoAnnotationName ele, TextAttributesKey attributes) {
            if (null == ele) return;
            myHolder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(ele.getTextRange())
                    .textAttributes(attributes)
                    .create();
        }
    }
}