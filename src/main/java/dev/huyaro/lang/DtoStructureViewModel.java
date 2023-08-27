package dev.huyaro.lang;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.NodeProvider;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.util.SmartList;
import com.intellij.util.containers.ContainerUtil;
import dev.huyaro.lang.psi.DtoDtoBody;
import dev.huyaro.lang.psi.DtoDtoType;
import dev.huyaro.lang.psi.DtoExplicitProp;
import dev.huyaro.lang.psi.DtoFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class DtoStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {

    public DtoStructureViewModel(@Nullable Editor editor, PsiFile psiFile) {
        super(psiFile, editor, new DtoStructureViewElement(psiFile));
        withSuitableClasses(DtoFile.class, DtoDtoType.class, DtoDtoBody.class, DtoExplicitProp.class);
        withSorters(Sorter.ALPHA_SORTER);
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return false;
    }

}
