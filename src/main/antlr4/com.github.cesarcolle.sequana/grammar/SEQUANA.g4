grammar SEQUANA;

root : (area | device | frequency | pipe | NL)+ (NL)* EOF;

pipe                            : 'pipe_name ' name=BASIC_STRING ' {'NL pip_def '}';
    pip_def                     : pipe_pin flow_meter?;
        flow_meter              : TAB 'flow_meter ' status=BOOLEAN NL;
        pipe_pin                : TAB 'pin ' amount=INTEGER NL;


frequency                       : 'frequency ' name=BASIC_STRING ' {'NL frequency_def+ '}';
    frequency_def               : TAB day=DAYS ' at ' hours=TIME ' for ' period=PERIOD NL;

device                          : 'device ' nameDevice=BASIC_STRING ' {'NL device_def '}';
    device_def                  : device_pin_range (pins_configuration)+ hardware pipe_list;
        device_pin_range        : TAB'pin_range ' interval NL;
        pins_configuration      : TAB'pin_number ' number=INTEGER ' ' frequencie_name=BASIC_STRING NL;
        hardware                : TAB'model_device ' model=MODEL NL;
        pipe_list               : TAB'pipe_list ' elem+=BASIC_STRING (COMMA elem+=BASIC_STRING)* NL;



area                    : 'area ' name=BASIC_STRING ' {'NL area_def '}';
    area_def            : TAB list_devices;
        list_devices    : elem+=BASIC_STRING (COMMA elem+=BASIC_STRING)* NL;


interval            : '[' min=INTEGER COMMA max=INTEGER ']';



/*****************
 ** Lexer rules **
 *****************/

COMMENT             :   '\n'* ' '* '#' ~( '\r' | '\n' )*  -> skip;     // Single line comments, starting with a #

MODEL               :   'arduino' | 'raspberry' | 'ARDUINOUNO';
DAYS                :   'monday'  | 'tuesday'| 'wednesday'| 'thursday'| 'friday'| 'saturday'| 'sunday';
FILE_LOCATION       :   'local' | 'distant';
HEADER_TYPE         :   'time'|'value'|'name';

TIME                :   '0'..'9''0'..'9''h''0'..'5''0'..'9';

PERIOD              :   '1'..'9''0'..'9'*('m'|'h');
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