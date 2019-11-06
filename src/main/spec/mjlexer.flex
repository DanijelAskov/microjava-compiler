package askov.schoolprojects.compilerconstruction.mjcompiler;

import java_cup.runtime.Symbol;
import askov.schoolprojects.compilerconstruction.mjcompiler.loggers.LexicalErrorMJLogger;

%%

%class MJLexer
%cup
%line
%column

%{
	LexicalErrorMJLogger lexicalErrorMJLogger = new LexicalErrorMJLogger();

	private Symbol newSymbol(int type) {
		return new Symbol(type, yyline + 1, yycolumn + 1);
	}

	private Symbol newSymbol(int type, Object value) {
		return new Symbol(type, yyline + 1, yycolumn + 1, value);
	}
%}

%eofval{
	return newSymbol(sym.EOF);
%eofval}

LineTerminator = \n|\r|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [\t\f\b ]

EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
Comment = {EndOfLineComment}

DecIntegerLiteral = [0-9]+
PrintableCharLiteral = "'" [\040-\176] "'"
BooleanLiteral = "true" | "false"
Identifier = ([a-z]|[A-Z])[a-zA-Z0-9_]*

%%

<YYINITIAL> {
	// Whitespace characters (Beline)
	{WhiteSpace}           { /* ignore */ }
	
	// Keywords (Ključne reci)
	"program"              { return newSymbol(sym.PROGRAM); }
	"break"                { return newSymbol(sym.BREAK); }
	"class"                { return newSymbol(sym.CLASS); }
	"else"                 { return newSymbol(sym.ELSE); }
	"if"                   { return newSymbol(sym.IF); }
	"new"                  { return newSymbol(sym.NEW); }
	"print"                { return newSymbol(sym.PRINT); }
	"read"                 { return newSymbol(sym.READ); }
	"return"               { return newSymbol(sym.RETURN); }
	"void"                 { return newSymbol(sym.VOID); }
	"do"                   { return newSymbol(sym.DO); }
	"while"                { return newSymbol(sym.WHILE); }
	"extends"              { return newSymbol(sym.EXTENDS);}
	"continue"             { return newSymbol(sym.CONTINUE); }
	"const"                { return newSymbol(sym.CONST); }
	
	// Operators (Operatori)
	// Arithmetic (Aritmetički)
	"+"                    { return newSymbol(sym.PLUS); }
	"-"                    { return newSymbol(sym.MINUS); }
	"*"                    { return newSymbol(sym.TIMES); }
	"/"                    { return newSymbol(sym.DIV); }
	"%"                    { return newSymbol(sym.MOD); }
	"++"                   { return newSymbol(sym.INCR); }
	"--"                   { return newSymbol(sym.DECR); }
	// Relational (Relacioni)
	"=="                   { return newSymbol(sym.EQ); }
	"!="                   { return newSymbol(sym.NEQ); }
	"<"                    { return newSymbol(sym.LT); }
	"<="                   { return newSymbol(sym.LEQ); }
	">"                    { return newSymbol(sym.GT); }
	">="                   { return newSymbol(sym.GEQ); }
	// Logical (Logički)
	"&&"                   { return newSymbol(sym.AND); }
	"||"                   { return newSymbol(sym.OR); }
	// Assignment (Dodele)
	"="                    { return newSymbol(sym.ASSIGN); }
	
	// Separators (Separatori)
	";"                    { return newSymbol(sym.SEMI); }
	","                    { return newSymbol(sym.COMMA); }
	"."                    { return newSymbol(sym.DOT); }
	
	// Delimiters (Delimiteri)
	"("                    { return newSymbol(sym.LPAREN); } // Parentheses (Oble (male) zagrade).
	")"                    { return newSymbol(sym.RPAREN); }
	"["                    { return newSymbol(sym.LBRACKET); } // (Square) Brackets (Uglaste (srednje) zagrade).
	"]"                    { return newSymbol(sym.RBRACKET); }
	"{"                    { return newSymbol(sym.LBRACE); } // (Curly) Braces (Vitičaste (velike) zagrade).
	"}"                    { return newSymbol(sym.RBRACE); }
	
	// Comments (Komentari)
	{Comment}              { /* ignore */ }
	
	// Literals (Literali)
	{DecIntegerLiteral}    { return newSymbol(sym.INT, new Integer(yytext())); }
	{BooleanLiteral}       { return newSymbol(sym.BOOL, Boolean.valueOf(yytext())); }  
	{PrintableCharLiteral} { return newSymbol(sym.CHAR, new Character(yytext().charAt(1))); }
	
	// Identifiers (Identifikatori)
	{Identifier}           { return newSymbol(sym.IDENT, yytext()); }
	
	// Lexical error (Leksička greška)
	// Svi tekst editori numerisu linije počev od broja 1, pa je zato na yyline dodat broj 1.
	[^]                    { lexicalErrorMJLogger.log(yytext(), yyline + 1, yycolumn + 1); return newSymbol(sym.ERROR); }
}