// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static dev.huyaro.lang.psi.DtoTypes.*;
import static dev.huyaro.lang.parser.DtoParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class DtoParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return dtoFile(b, l + 1);
  }

  /* ********************************************************** */
  // 'true' | 'false'
  public static boolean BooleanLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BooleanLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN_LITERAL, "<boolean literal>");
    r = consumeToken(b, "true");
    if (!r) r = consumeToken(b, "false");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Id
  public static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // aliasPattern '{' aliasGroupProp* '}'
  public static boolean aliasGroup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasGroup")) return false;
    if (!nextTokenIs(b, KEY_AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = aliasPattern(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && aliasGroup_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ALIAS_GROUP, r);
    return r;
  }

  // aliasGroupProp*
  private static boolean aliasGroup_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasGroup_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!aliasGroupProp(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "aliasGroup_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // allScalars | positiveProp
  public static boolean aliasGroupProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasGroupProp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ALIAS_GROUP_PROP, "<alias group prop>");
    r = allScalars(b, l + 1);
    if (!r) r = positiveProp(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'as' '(' '^'? Identifier? '$'? '->' Identifier? ')'
  public static boolean aliasPattern(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasPattern")) return false;
    if (!nextTokenIs(b, KEY_AS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY_AS, LPAREN);
    r = r && aliasPattern_2(b, l + 1);
    r = r && aliasPattern_3(b, l + 1);
    r = r && aliasPattern_4(b, l + 1);
    r = r && consumeToken(b, RIGHT_ARROW);
    r = r && aliasPattern_6(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, ALIAS_PATTERN, r);
    return r;
  }

  // '^'?
  private static boolean aliasPattern_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasPattern_2")) return false;
    consumeToken(b, CARET);
    return true;
  }

  // Identifier?
  private static boolean aliasPattern_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasPattern_3")) return false;
    Identifier(b, l + 1);
    return true;
  }

  // '$'?
  private static boolean aliasPattern_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasPattern_4")) return false;
    consumeToken(b, DOLOR);
    return true;
  }

  // Identifier?
  private static boolean aliasPattern_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "aliasPattern_6")) return false;
    Identifier(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '#' Identifier
  //     ('(' qualifiedName (',' qualifiedName)* ')')?
  //     ('?'|'!')?
  public static boolean allScalars(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars")) return false;
    if (!nextTokenIs(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HASH);
    r = r && Identifier(b, l + 1);
    r = r && allScalars_2(b, l + 1);
    r = r && allScalars_3(b, l + 1);
    exit_section_(b, m, ALL_SCALARS, r);
    return r;
  }

  // ('(' qualifiedName (',' qualifiedName)* ')')?
  private static boolean allScalars_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_2")) return false;
    allScalars_2_0(b, l + 1);
    return true;
  }

  // '(' qualifiedName (',' qualifiedName)* ')'
  private static boolean allScalars_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && qualifiedName(b, l + 1);
    r = r && allScalars_2_0_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' qualifiedName)*
  private static boolean allScalars_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_2_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!allScalars_2_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "allScalars_2_0_2", c)) break;
    }
    return true;
  }

  // ',' qualifiedName
  private static boolean allScalars_2_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_2_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && qualifiedName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('?'|'!')?
  private static boolean allScalars_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_3")) return false;
    allScalars_3_0(b, l + 1);
    return true;
  }

  // '?'|'!'
  private static boolean allScalars_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "allScalars_3_0")) return false;
    boolean r;
    r = consumeToken(b, OPTNULL);
    if (!r) r = consumeToken(b, "!");
    return r;
  }

  /* ********************************************************** */
  // '@' annotationName ('(' annotationArguments? ')')?
  public static boolean annotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AT);
    r = r && annotationName(b, l + 1);
    r = r && annotation_2(b, l + 1);
    exit_section_(b, m, ANNOTATION, r);
    return r;
  }

  // ('(' annotationArguments? ')')?
  private static boolean annotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_2")) return false;
    annotation_2_0(b, l + 1);
    return true;
  }

  // '(' annotationArguments? ')'
  private static boolean annotation_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && annotation_2_0_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // annotationArguments?
  private static boolean annotation_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotation_2_0_1")) return false;
    annotationArguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // annotationNamedArgument (',' annotationNamedArgument)*
  //     | annotationValue (',' annotationNamedArgument)*
  public static boolean annotationArguments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_ARGUMENTS, "<annotation arguments>");
    r = annotationArguments_0(b, l + 1);
    if (!r) r = annotationArguments_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // annotationNamedArgument (',' annotationNamedArgument)*
  private static boolean annotationArguments_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotationNamedArgument(b, l + 1);
    r = r && annotationArguments_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' annotationNamedArgument)*
  private static boolean annotationArguments_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationArguments_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationArguments_0_1", c)) break;
    }
    return true;
  }

  // ',' annotationNamedArgument
  private static boolean annotationArguments_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotationNamedArgument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // annotationValue (',' annotationNamedArgument)*
  private static boolean annotationArguments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotationValue(b, l + 1);
    r = r && annotationArguments_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' annotationNamedArgument)*
  private static boolean annotationArguments_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationArguments_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationArguments_1_1", c)) break;
    }
    return true;
  }

  // ',' annotationNamedArgument
  private static boolean annotationArguments_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArguments_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotationNamedArgument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '[' annotationSingleValue (',' annotationSingleValue)* ']'
  //     | '{' annotationSingleValue (',' annotationSingleValue)* '}'
  public static boolean annotationArrayValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue")) return false;
    if (!nextTokenIs(b, "<annotation array value>", LBRACE, LBRACK)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_ARRAY_VALUE, "<annotation array value>");
    r = annotationArrayValue_0(b, l + 1);
    if (!r) r = annotationArrayValue_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '[' annotationSingleValue (',' annotationSingleValue)* ']'
  private static boolean annotationArrayValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACK);
    r = r && annotationSingleValue(b, l + 1);
    r = r && annotationArrayValue_0_2(b, l + 1);
    r = r && consumeToken(b, RBRACK);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' annotationSingleValue)*
  private static boolean annotationArrayValue_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationArrayValue_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationArrayValue_0_2", c)) break;
    }
    return true;
  }

  // ',' annotationSingleValue
  private static boolean annotationArrayValue_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotationSingleValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '{' annotationSingleValue (',' annotationSingleValue)* '}'
  private static boolean annotationArrayValue_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && annotationSingleValue(b, l + 1);
    r = r && annotationArrayValue_1_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' annotationSingleValue)*
  private static boolean annotationArrayValue_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationArrayValue_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationArrayValue_1_2", c)) break;
    }
    return true;
  }

  // ',' annotationSingleValue
  private static boolean annotationArrayValue_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationArrayValue_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && annotationSingleValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Identifier ('.' Identifier)*
  public static boolean annotationName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationName")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && annotationName_1(b, l + 1);
    exit_section_(b, m, ANNOTATION_NAME, r);
    return r;
  }

  // ('.' Identifier)*
  private static boolean annotationName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationName_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotationName_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "annotationName_1", c)) break;
    }
    return true;
  }

  // '.' Identifier
  private static boolean annotationName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Identifier '=' annotationValue
  public static boolean annotationNamedArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationNamedArgument")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, EQUAL);
    r = r && annotationValue(b, l + 1);
    exit_section_(b, m, ANNOTATION_NAMED_ARGUMENT, r);
    return r;
  }

  /* ********************************************************** */
  // BooleanLiteral
  //     | Number
  //     | StringLiteral
  //     | annotation
  //     | nestedAnnotation
  //     | qualifiedName
  public static boolean annotationSingleValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationSingleValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_SINGLE_VALUE, "<annotation single value>");
    r = BooleanLiteral(b, l + 1);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRINGLITERAL);
    if (!r) r = annotation(b, l + 1);
    if (!r) r = nestedAnnotation(b, l + 1);
    if (!r) r = qualifiedName(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // annotationSingleValue
  //     | annotationArrayValue
  public static boolean annotationValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "annotationValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ANNOTATION_VALUE, "<annotation value>");
    r = annotationSingleValue(b, l + 1);
    if (!r) r = annotationArrayValue(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '{' (explicitProp (','|';')?)* '}'
  public static boolean dtoBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoBody")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && dtoBody_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, DTO_BODY, r);
    return r;
  }

  // (explicitProp (','|';')?)*
  private static boolean dtoBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoBody_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dtoBody_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoBody_1", c)) break;
    }
    return true;
  }

  // explicitProp (','|';')?
  private static boolean dtoBody_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoBody_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = explicitProp(b, l + 1);
    r = r && dtoBody_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (','|';')?
  private static boolean dtoBody_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoBody_1_0_1")) return false;
    dtoBody_1_0_1_0(b, l + 1);
    return true;
  }

  // ','|';'
  private static boolean dtoBody_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoBody_1_0_1_0")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  /* ********************************************************** */
  // importStatement* dtoType*
  static boolean dtoFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = dtoFile_0(b, l + 1);
    r = r && dtoFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // importStatement*
  private static boolean dtoFile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoFile_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!importStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoFile_0", c)) break;
    }
    return true;
  }

  // dtoType*
  private static boolean dtoFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dtoType(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoFile_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // annotation*
  //     modifier* Identifier
  //     (':' Identifier (',' Identifier)*)?
  //     dtoBody
  public static boolean dtoType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DTO_TYPE, "<dto type>");
    r = dtoType_0(b, l + 1);
    r = r && dtoType_1(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && dtoType_3(b, l + 1);
    r = r && dtoBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // annotation*
  private static boolean dtoType_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoType_0", c)) break;
    }
    return true;
  }

  // modifier*
  private static boolean dtoType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!modifier(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoType_1", c)) break;
    }
    return true;
  }

  // (':' Identifier (',' Identifier)*)?
  private static boolean dtoType_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_3")) return false;
    dtoType_3_0(b, l + 1);
    return true;
  }

  // ':' Identifier (',' Identifier)*
  private static boolean dtoType_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && Identifier(b, l + 1);
    r = r && dtoType_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' Identifier)*
  private static boolean dtoType_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_3_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!dtoType_3_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dtoType_3_0_2", c)) break;
    }
    return true;
  }

  // ',' Identifier
  private static boolean dtoType_3_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dtoType_3_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // allScalars
  //     | aliasGroup
  //     | negativeProp
  //     | userProp
  //     | positiveProp
  public static boolean explicitProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "explicitProp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPLICIT_PROP, "<explicit prop>");
    r = allScalars(b, l + 1);
    if (!r) r = aliasGroup(b, l + 1);
    if (!r) r = negativeProp(b, l + 1);
    if (!r) r = userProp(b, l + 1);
    if (!r) r = positiveProp(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '*' | (Identifier ',' Identifier | Identifier '?'?)
  public static boolean genericArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericArgument")) return false;
    if (!nextTokenIs(b, "<generic argument>", ID, STAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GENERIC_ARGUMENT, "<generic argument>");
    r = consumeToken(b, STAR);
    if (!r) r = genericArgument_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Identifier ',' Identifier | Identifier '?'?
  private static boolean genericArgument_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericArgument_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = genericArgument_1_0(b, l + 1);
    if (!r) r = genericArgument_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier ',' Identifier
  private static boolean genericArgument_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericArgument_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier '?'?
  private static boolean genericArgument_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericArgument_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && genericArgument_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '?'?
  private static boolean genericArgument_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericArgument_1_1_1")) return false;
    consumeToken(b, OPTNULL);
    return true;
  }

  /* ********************************************************** */
  // 'in' | 'out'
  public static boolean genericModifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "genericModifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GENERIC_MODIFIER, "<generic modifier>");
    r = consumeToken(b, "in");
    if (!r) r = consumeToken(b, "out");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // 'import' Identifier ('.' Identifier)*
  //     (
  //     '.' '{' importedType (',' importedType)* '}'
  //     | 'as' Identifier
  //     )?
  public static boolean importStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && Identifier(b, l + 1);
    r = r && importStatement_2(b, l + 1);
    r = r && importStatement_3(b, l + 1);
    exit_section_(b, m, IMPORT_STATEMENT, r);
    return r;
  }

  // ('.' Identifier)*
  private static boolean importStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!importStatement_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "importStatement_2", c)) break;
    }
    return true;
  }

  // '.' Identifier
  private static boolean importStatement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (
  //     '.' '{' importedType (',' importedType)* '}'
  //     | 'as' Identifier
  //     )?
  private static boolean importStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3")) return false;
    importStatement_3_0(b, l + 1);
    return true;
  }

  // '.' '{' importedType (',' importedType)* '}'
  //     | 'as' Identifier
  private static boolean importStatement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = importStatement_3_0_0(b, l + 1);
    if (!r) r = importStatement_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '.' '{' importedType (',' importedType)* '}'
  private static boolean importStatement_3_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, LBRACE);
    r = r && importedType(b, l + 1);
    r = r && importStatement_3_0_0_3(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (',' importedType)*
  private static boolean importStatement_3_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3_0_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!importStatement_3_0_0_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "importStatement_3_0_0_3", c)) break;
    }
    return true;
  }

  // ',' importedType
  private static boolean importStatement_3_0_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3_0_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && importedType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // 'as' Identifier
  private static boolean importStatement_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importStatement_3_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_AS);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Identifier ('as' Identifier)?
  public static boolean importedType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importedType")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && importedType_1(b, l + 1);
    exit_section_(b, m, IMPORTED_TYPE, r);
    return r;
  }

  // ('as' Identifier)?
  private static boolean importedType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importedType_1")) return false;
    importedType_1_0(b, l + 1);
    return true;
  }

  // 'as' Identifier
  private static boolean importedType_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importedType_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_AS);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // 'abstract' | 'input' | 'input-only'
  public static boolean modifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "modifier")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MODIFIER, "<modifier>");
    r = consumeToken(b, ABSTRACT);
    if (!r) r = consumeToken(b, INPUT);
    if (!r) r = consumeToken(b, "input-only");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '-' Identifier
  public static boolean negativeProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "negativeProp")) return false;
    if (!nextTokenIs(b, MINUS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, NEGATIVE_PROP, r);
    return r;
  }

  /* ********************************************************** */
  // annotationName '(' annotationArguments? ')'
  public static boolean nestedAnnotation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedAnnotation")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = annotationName(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && nestedAnnotation_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, NESTED_ANNOTATION, r);
    return r;
  }

  // annotationArguments?
  private static boolean nestedAnnotation_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedAnnotation_2")) return false;
    annotationArguments(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // annotation*
  //     '+'?
  //     (Identifier '(' Identifier ')' | Identifier)
  //     ('?'|'!')?
  //     ('as' Identifier)?
  //     (annotation* dtoBody '*'?)?
  public static boolean positiveProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, POSITIVE_PROP, "<positive prop>");
    r = positiveProp_0(b, l + 1);
    r = r && positiveProp_1(b, l + 1);
    r = r && positiveProp_2(b, l + 1);
    r = r && positiveProp_3(b, l + 1);
    r = r && positiveProp_4(b, l + 1);
    r = r && positiveProp_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // annotation*
  private static boolean positiveProp_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "positiveProp_0", c)) break;
    }
    return true;
  }

  // '+'?
  private static boolean positiveProp_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_1")) return false;
    consumeToken(b, "+");
    return true;
  }

  // Identifier '(' Identifier ')' | Identifier
  private static boolean positiveProp_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = positiveProp_2_0(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Identifier '(' Identifier ')'
  private static boolean positiveProp_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('?'|'!')?
  private static boolean positiveProp_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_3")) return false;
    positiveProp_3_0(b, l + 1);
    return true;
  }

  // '?'|'!'
  private static boolean positiveProp_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_3_0")) return false;
    boolean r;
    r = consumeToken(b, OPTNULL);
    if (!r) r = consumeToken(b, "!");
    return r;
  }

  // ('as' Identifier)?
  private static boolean positiveProp_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_4")) return false;
    positiveProp_4_0(b, l + 1);
    return true;
  }

  // 'as' Identifier
  private static boolean positiveProp_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_AS);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (annotation* dtoBody '*'?)?
  private static boolean positiveProp_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_5")) return false;
    positiveProp_5_0(b, l + 1);
    return true;
  }

  // annotation* dtoBody '*'?
  private static boolean positiveProp_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = positiveProp_5_0_0(b, l + 1);
    r = r && dtoBody(b, l + 1);
    r = r && positiveProp_5_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // annotation*
  private static boolean positiveProp_5_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_5_0_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "positiveProp_5_0_0", c)) break;
    }
    return true;
  }

  // '*'?
  private static boolean positiveProp_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "positiveProp_5_0_2")) return false;
    consumeToken(b, STAR);
    return true;
  }

  /* ********************************************************** */
  // Identifier ('.' Identifier)*
  public static boolean qualifiedName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && qualifiedName_1(b, l + 1);
    exit_section_(b, m, QUALIFIED_NAME, r);
    return r;
  }

  // ('.' Identifier)*
  private static boolean qualifiedName_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!qualifiedName_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "qualifiedName_1", c)) break;
    }
    return true;
  }

  // '.' Identifier
  private static boolean qualifiedName_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "qualifiedName_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // qualifiedName
  //     ('<' genericModifier? genericArgument '>')?
  //     '?'?
  public static boolean typeRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeRef")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = qualifiedName(b, l + 1);
    r = r && typeRef_1(b, l + 1);
    r = r && typeRef_2(b, l + 1);
    exit_section_(b, m, TYPE_REF, r);
    return r;
  }

  // ('<' genericModifier? genericArgument '>')?
  private static boolean typeRef_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeRef_1")) return false;
    typeRef_1_0(b, l + 1);
    return true;
  }

  // '<' genericModifier? genericArgument '>'
  private static boolean typeRef_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeRef_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LANGLE);
    r = r && typeRef_1_0_1(b, l + 1);
    r = r && genericArgument(b, l + 1);
    r = r && consumeToken(b, RANGLE);
    exit_section_(b, m, null, r);
    return r;
  }

  // genericModifier?
  private static boolean typeRef_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeRef_1_0_1")) return false;
    genericModifier(b, l + 1);
    return true;
  }

  // '?'?
  private static boolean typeRef_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeRef_2")) return false;
    consumeToken(b, OPTNULL);
    return true;
  }

  /* ********************************************************** */
  // annotation*
  //     Identifier ':' typeRef
  public static boolean userProp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "userProp")) return false;
    if (!nextTokenIs(b, "<user prop>", AT, ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, USER_PROP, "<user prop>");
    r = userProp_0(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeRef(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // annotation*
  private static boolean userProp_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "userProp_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!annotation(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "userProp_0", c)) break;
    }
    return true;
  }

}
