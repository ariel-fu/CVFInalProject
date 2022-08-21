          .ORIG x0200
START

	AND R1, R1, #0
	LD R4, VALUE1
	LD R7, VALUE2

LOOP 
	ADD R1, R1, R4
	ADD R7, R7, #-1
	BRnp LOOP

	ST R1, RESULT

DONE      BR START  ; repeat forever

VALUE1 .FILL #5
VALUE2 .FILL #8
RESULT .FILL #4
MY_VAL .FILL x2048

; any program data you need to create goes below here

          .END