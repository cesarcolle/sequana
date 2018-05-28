grammar SEQUANA;

root : (frenquencies | monitoring | area | device)+ hardware?  (NL)* EOF;

hardware            : 'hardware' name=MODEL NL;



area                    : 'area ' name=BASIC_STRING ' {'NL area_def '}';
    area_def            : list_devices;
        list_devices    : elem+=BASIC_STRING (COMMA elem+=BASIC_STRING)* NL;


device                      : 'device ' nameDevice=BASIC_STRING ' {'NL device_def '}';
    device_def              : device_pin_range pipe_number;
        device_pin_range    :'pin_range ' interval NL;
        pipe_number         :'pipe_number' pipeNumber=INTEGER;




frenquencies        : 'frequence ' name=BASIC_STRING ' {'NL frequency_def '}';
    frequency_def   : ;


monitoring : 'monitor';

interval            : '[' min=INTEGER COMMA max=INTEGER ']';



/*****************
 ** Lexer rules **
 *****************/

COMMENT             :   '\n'* ' '* '#' ~( '\r' | '\n' )*  -> skip;     // Single line comments, starting with a #

MODEL               : 'arduino' | 'raspberry';
DAYS                :   'monday'  | 'tuesday'| 'wednesday'| 'thursday'| 'friday'| 'saturday'| 'sunday';
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
fragment SYMBOLS : '('|')'|'/'|'*'|'-'|'+'|'^'|'%'|'='|'!'|'<'|'>'|'&'|'|'|'.'|' ';