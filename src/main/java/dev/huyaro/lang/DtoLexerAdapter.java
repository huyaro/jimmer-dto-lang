package dev.huyaro.lang;

import com.intellij.lexer.FlexAdapter;
import dev.huyaro.lang.parser._DtoLexer;

public class DtoLexerAdapter extends FlexAdapter {

  public DtoLexerAdapter() {
    super(new _DtoLexer(null));
  }

}
