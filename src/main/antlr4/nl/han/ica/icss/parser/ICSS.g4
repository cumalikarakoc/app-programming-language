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
LOWER_IDENT: [a-z0-9\-]+;
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

stylesheet:(varAssignments |) styleRules;
varAssignments: varAssignment+;
varAssignment: varName ASSIGNMENT_OPERATOR expression SEMICOLON;
varName: CAPITAL_IDENT;
styleRules: styleRule+;
styleRule: tagSelector OPEN_BRACE declarations CLOSE_BRACE;
tagSelector: (idSelector | classSelector | elementSelector);
idSelector: ID_IDENT;
classSelector: CLASS_IDENT;
elementSelector: LOWER_IDENT;
declarations: declaration+;
declaration: propName COLON propVal SEMICOLON;
propVal: expression | operation*;
operation: multiplyOperation | addOperation | subtractOperation;
addOperation: expression PLUS (expression | operation);
multiplyOperation: expression MUL (expression | operation);
subtractOperation: expression MIN (expression | operation);
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