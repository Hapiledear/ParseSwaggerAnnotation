grammar ApiImplicitParams;


apiImplicitParams       : START apiImplicitParam+ END;
apiImplicitParam        : ITEM_START (name|value|defaultValue|allowableValues|required|dataType|dataTypeClass|paramType|example|readOnly)+ ITEM_END;

name                    : NAME  text_inline;
value                   : VALUE text_inline;
defaultValue            : DEFAULTVALUE text_inline;
allowableValues         : ALLOWABLEVALUES text_inline;
required                : REQUIRED  (FALSE|TRUE) COMMA?;
dataType                : DATATYPE text_inline;
dataTypeClass           : DATATYPECLASS class_name  ;
paramType               : PARAM_TYPE text_inline;
example                 : EXAMPLE text_inline;
readOnly                : READ_ONLY (FALSE|TRUE) COMMA?;

class_name              : text+ CLASS_SUFFIX COMMA?;
text_inline             : QUOTES text+ QUOTES COMMA?;
text                    : TIMEFORTAME2
                        | TIMEFORTAME1
                        | CHAR_SET
                        | DIGIT
                        | TRANSLATE_QUOTES
                        | COMMA
                        ;


START                   : '@ApiImplicitParams({';
END                     : '})';
ITEM_START              : '@ApiImplicitParam(';
ITEM_END                : ')'[,]?;

EXAMPLE                 : 'example' EQ;
READ_ONLY               : 'readOnly' EQ;
PARAM_TYPE              : 'paramType' EQ;
CLASS_SUFFIX            : '.class';
DATATYPECLASS           : 'dataTypeClass' EQ;
DATATYPE                : 'dataType' EQ;
ALLOWABLEVALUES         : 'allowableValues' EQ;
DEFAULTVALUE            : 'defaultValue' EQ;
REQUIRED                : 'required' EQ;
VALUE                   : 'value' EQ;
NAME                    : 'name' EQ;
FALSE                   : 'false';
TRUE                    : 'true';

TRANSLATE_QUOTES        : '\\"';
DIGIT                   : [0-9];
COMMA                   : ',';
TIMEFORTAME2            : 'yyyy-MM';
TIMEFORTAME1            : 'yyyy-MM-dd HH:mm:ss';
CHAR_SET                : [a-zA-Z.。_，:{}\u4e00-\u9fa5];

fragment EQ             : WS* '=';
WS                      : [ \n\t\r] -> skip;
QUOTES                  : '"';
