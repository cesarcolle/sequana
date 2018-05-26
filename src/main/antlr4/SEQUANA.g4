grammar SEQUANA;

root : (frenquencies | monitoring)+ (NL)* EOF;

frenquencies         : 'frequence' name=BASIC_STRING ' {'NL frequency_def '}';
    frequency_def   : 'frequence';


monitoring : 'monitor';




/*****************
 ** Lexer rules **
 *****************/

COMMENT             :   '\n'* ' '* '#' ~( '\r' | '\n' )*  -> skip;     // Single line comments, starting with a #

FILE_TYPE           :   'json'  | 'csv';
FILE_LOCATION       :   'local' | 'distant';
HEADER_TYPE         :   'time'|'value'|'name';
PERIOD              :   '1'..'9''0'..'9'*('ms'|'s'|'m'|'h'|'d');
DATE                :   DIGIT DIGIT '/' DIGIT DIGIT '/' DIGIT DIGIT DIGIT DIGIT ' ' DIGIT DIGIT ':' DIGIT DIGIT;
BOOLEAN             :   ('true'|'TRUE'|'false'|'FALSE');
INTEGER             :   ('-'|'+')?DIGIT+;
DOUBLE              :   ('-'|'+')?'0'..'9'*'.'?'0'..'9'+;
BASIC_STRING        :   (LETTERS)(LETTERS|'0'..'9'|'_'|'-')*;
EXPRESSION          :   '`' (LETTERS|DIGIT|SYMBOLS)+ '`';
STRING              :   '"'.+?'"';
COMMA               :   ',' ' '?;
TAB                 :   '    ';
NL                  :   '\n';

fragment LETTERS    :   'a'..'z'|'A'..'Z';
fragment DIGIT      :   '0'..'9';
fragment SYMBOLS    :   '('|')'|'/'|'*'|'-'|'+'|'^'|'%'|'='|'!'|'<'|'>'|'&'|'|'|'.'|' ';
