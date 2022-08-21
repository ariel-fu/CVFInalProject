; Filename: test.asm
; Author: Ariel Fu
; Description: gives me a sanity-test

	.ORIG x0200
START
       LEA R0, ARRAY
       LDR R1, R0, #-1
       STR R1, R0, #1
       LDR R1, R0, #0
       STR R1, R0, #-1
       LDR R1, R0, #1
       STR R1, R0, #0

	LDR R4, R0, #-1
	LDR R5, R0, #0
	LDR R6, R0, #1
	
       BR START             ; repeat forever  

       .FILL x1111          ; 3 element array
ARRAY  .FILL x2222
       .FILL x3333

       .END