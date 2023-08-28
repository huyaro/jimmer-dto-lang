// This is a generated file. Not intended for manual editing.
package dev.huyaro.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import dev.huyaro.lang.psi.impl.*;

public interface DtoTypes {

  IElementType ALIAS_GROUP = new DtoElementType("ALIAS_GROUP");
  IElementType ALIAS_GROUP_PROP = new DtoElementType("ALIAS_GROUP_PROP");
  IElementType ALIAS_PATTERN = new DtoElementType("ALIAS_PATTERN");
  IElementType ALL_SCALARS = new DtoElementType("ALL_SCALARS");
  IElementType ANNOTATION = new DtoElementType("ANNOTATION");
  IElementType ANNOTATION_ARGUMENTS = new DtoElementType("ANNOTATION_ARGUMENTS");
  IElementType ANNOTATION_ARRAY_VALUE = new DtoElementType("ANNOTATION_ARRAY_VALUE");
  IElementType ANNOTATION_NAME = new DtoElementType("ANNOTATION_NAME");
  IElementType ANNOTATION_NAMED_ARGUMENT = new DtoElementType("ANNOTATION_NAMED_ARGUMENT");
  IElementType ANNOTATION_SINGLE_VALUE = new DtoElementType("ANNOTATION_SINGLE_VALUE");
  IElementType ANNOTATION_VALUE = new DtoElementType("ANNOTATION_VALUE");
  IElementType BOOLEAN_LITERAL = new DtoElementType("BOOLEAN_LITERAL");
  IElementType DTO_BODY = new DtoElementType("DTO_BODY");
  IElementType DTO_TYPE = new DtoElementType("DTO_TYPE");
  IElementType EXPLICIT_PROP = new DtoElementType("EXPLICIT_PROP");
  IElementType GENERIC_ARGUMENT = new DtoElementType("GENERIC_ARGUMENT");
  IElementType IDENTIFIER = new DtoElementType("IDENTIFIER");
  IElementType IMPORTED_TYPE = new DtoElementType("IMPORTED_TYPE");
  IElementType IMPORT_STATEMENT = new DtoElementType("IMPORT_STATEMENT");
  IElementType MODIFIER = new DtoElementType("MODIFIER");
  IElementType NEGATIVE_PROP = new DtoElementType("NEGATIVE_PROP");
  IElementType NESTED_ANNOTATION = new DtoElementType("NESTED_ANNOTATION");
  IElementType POSITIVE_PROP = new DtoElementType("POSITIVE_PROP");
  IElementType QUALIFIED_NAME = new DtoElementType("QUALIFIED_NAME");
  IElementType TYPE_REF = new DtoElementType("TYPE_REF");
  IElementType USER_PROP = new DtoElementType("USER_PROP");

  IElementType ABSTRACT = new DtoTokenType("abstract");
  IElementType AT = new DtoTokenType("@");
  IElementType BLOCKCOMMENT = new DtoTokenType("BlockComment");
  IElementType CARET = new DtoTokenType("^");
  IElementType COLON = new DtoTokenType(":");
  IElementType COMMA = new DtoTokenType(",");
  IElementType DOLOR = new DtoTokenType("$");
  IElementType DOT = new DtoTokenType(".");
  IElementType EQUAL = new DtoTokenType("=");
  IElementType HASH = new DtoTokenType("#");
  IElementType ID = new DtoTokenType("Id");
  IElementType IMPORT = new DtoTokenType("import");
  IElementType INPUT = new DtoTokenType("input");
  IElementType KEY_AS = new DtoTokenType("as");
  IElementType LANGLE = new DtoTokenType("<");
  IElementType LBRACE = new DtoTokenType("{");
  IElementType LBRACK = new DtoTokenType("[");
  IElementType LINECOMMENT = new DtoTokenType("LineComment");
  IElementType LPAREN = new DtoTokenType("(");
  IElementType MINUS = new DtoTokenType("-");
  IElementType NUMBER = new DtoTokenType("Number");
  IElementType OPTNULL = new DtoTokenType("?");
  IElementType RANGLE = new DtoTokenType(">");
  IElementType RBRACE = new DtoTokenType("}");
  IElementType RBRACK = new DtoTokenType("]");
  IElementType RIGHT_ARROW = new DtoTokenType("->");
  IElementType RPAREN = new DtoTokenType(")");
  IElementType SEMICOLON = new DtoTokenType(";");
  IElementType STAR = new DtoTokenType("*");
  IElementType STRING = new DtoTokenType("String");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ALIAS_GROUP) {
        return new DtoAliasGroupImpl(node);
      }
      else if (type == ALIAS_GROUP_PROP) {
        return new DtoAliasGroupPropImpl(node);
      }
      else if (type == ALIAS_PATTERN) {
        return new DtoAliasPatternImpl(node);
      }
      else if (type == ALL_SCALARS) {
        return new DtoAllScalarsImpl(node);
      }
      else if (type == ANNOTATION) {
        return new DtoAnnotationImpl(node);
      }
      else if (type == ANNOTATION_ARGUMENTS) {
        return new DtoAnnotationArgumentsImpl(node);
      }
      else if (type == ANNOTATION_ARRAY_VALUE) {
        return new DtoAnnotationArrayValueImpl(node);
      }
      else if (type == ANNOTATION_NAME) {
        return new DtoAnnotationNameImpl(node);
      }
      else if (type == ANNOTATION_NAMED_ARGUMENT) {
        return new DtoAnnotationNamedArgumentImpl(node);
      }
      else if (type == ANNOTATION_SINGLE_VALUE) {
        return new DtoAnnotationSingleValueImpl(node);
      }
      else if (type == ANNOTATION_VALUE) {
        return new DtoAnnotationValueImpl(node);
      }
      else if (type == BOOLEAN_LITERAL) {
        return new DtoBooleanLiteralImpl(node);
      }
      else if (type == DTO_BODY) {
        return new DtoDtoBodyImpl(node);
      }
      else if (type == DTO_TYPE) {
        return new DtoDtoTypeImpl(node);
      }
      else if (type == EXPLICIT_PROP) {
        return new DtoExplicitPropImpl(node);
      }
      else if (type == GENERIC_ARGUMENT) {
        return new DtoGenericArgumentImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new DtoIdentifierImpl(node);
      }
      else if (type == IMPORTED_TYPE) {
        return new DtoImportedTypeImpl(node);
      }
      else if (type == IMPORT_STATEMENT) {
        return new DtoImportStatementImpl(node);
      }
      else if (type == MODIFIER) {
        return new DtoModifierImpl(node);
      }
      else if (type == NEGATIVE_PROP) {
        return new DtoNegativePropImpl(node);
      }
      else if (type == NESTED_ANNOTATION) {
        return new DtoNestedAnnotationImpl(node);
      }
      else if (type == POSITIVE_PROP) {
        return new DtoPositivePropImpl(node);
      }
      else if (type == QUALIFIED_NAME) {
        return new DtoQualifiedNameImpl(node);
      }
      else if (type == TYPE_REF) {
        return new DtoTypeRefImpl(node);
      }
      else if (type == USER_PROP) {
        return new DtoUserPropImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
