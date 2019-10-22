grammar ICSS;

//--- LEXER: ---
// IF support:
IF: 'if';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---
stylesheet:(varAssignment | styleRule)*;
varAssignment: varName ASSIGNMENT_OPERATOR (expression | operation) SEMICOLON;
varName: CAPITAL_IDENT;
styleRule: tagSelector OPEN_BRACE selectorBody CLOSE_BRACE;
tagSelector: (idSelector | classSelector | elementSelector);
idSelector: ID_IDENT;
classSelector: CLASS_IDENT;
elementSelector: LOWER_IDENT;
selectorBody: varAssignment* declaration* ifClause*;
declaration: propName COLON propVal SEMICOLON;
ifClause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE OPEN_BRACE selectorBody CLOSE_BRACE;
propVal: expression | operation;
operation: expression                         # literalExpression
       |   operation MUL operation            # multiplyOperation
       |   operation (PLUS | MIN) operation   # addSubtractOperation;
expression: literal | varName;
propName:LOWER_IDENT;
literal: colorLiteral | pixelLiteral | percentageLiteral | boolLiteral | scalarLiteral;
colorLiteral: COLOR;
pixelLiteral: PIXELSIZE;
percentageLiteral: PERCENTAGE;
boolLiteral: boolLiteralTrue | boolLiteralFalse;
boolLiteralTrue: TRUE;
boolLiteralFalse: FALSE;
scalarLiteral: SCALAR;
