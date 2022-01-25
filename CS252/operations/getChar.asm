; Filename:    <name of file>
; Author:      <your name>
; Description: <explanation of what the program does>

          .ORIG x0200
START
	LD R0, START_ADDRESS
; add code here
	GET_CHAR
		LDR R1, R0, #0		; get the status of the keyboard
		BRzp GET_CHAR
		LDR R2, R0, #2
		
	CONVERT_CHAR
		LD R3, ADDRESS_A
		LD R4, Z_ADDRESS
		
		ADD R2, R2, R3		; get distnace between A and current char
		; negate that value
		NOT R2, R2
		ADD R2, R2, #1
		
		; add the negative distance to z to get the char to print out
		ADD R2, R2, R4
	
	PRINT_OUT
		LDR R1, R0, #4
		BRzp PRINT_OUT
		STR R2, R0, #6

DONE      BR START  ; repeat forever


START_ADDRESS .FILL xFE00
ADDRESS_A .FILL xFF9F
Z_ADDRESS .FILL #122
; any test program data you need to create goes below here...

          .END