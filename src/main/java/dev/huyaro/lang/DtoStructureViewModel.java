package dev.huyaro.lang;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import dev.huyaro.lang.psi.DtoExplicitProp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DtoStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {

  public DtoStructureViewModel(@Nullable Editor editor, PsiFile psiFile) {
    super(psiFile, editor, new DtoStructureViewElement(psiFile));
  }

  @NotNull
  public Sorter @NotNull [] getSorters() {
    return new Sorter[]{Sorter.ALPHA_SORTER};
  }


  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement element) {
    return element.getValue() instanceof DtoExplicitProp;
  }

  @Override
  protected Class<?> @NotNull [] getSuitableClasses() {
    return new Class[]{DtoExplicitProp.class};
  }
}
