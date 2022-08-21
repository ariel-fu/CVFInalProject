; Filename:    STLENGTH.asm
; Author:      252 Staff
; Description: test program for finding the end of
;              a null-terminated string

        .orig x0200

START   
	LEA	R1, S1 ; get the address of the first char in S1 --> 'C'
	JSR	STLENGTH ; go to STLENGTH, which will save R0 into STLENGTH_R0, but R0 is x0000
	LEA	R1, S6
	JSR	STLENGTH
	LEA	R1, S2
	JSR	STLENGTH
	LEA	R1, S5
	JSR	STLENGTH
	LEA	R1, S3
	JSR	STLENGTH
	LEA	R1, S4
	JSR	STLENGTH
	LEA	R1, S7
	JSR 	STLENGTH
		
	BR	START	; loop forever

S1	.STRINGZ	"Ciao!"
S2	.STRINGZ	"Hi!"
S3	.STRINGZ	"Salve!"
S4	.STRINGZ	"Hello!"
S5	.STRINGZ	"Gelato!"
S6	.STRINGZ	""
S7	.STRINGZ	"Italiano"

; Subroutine:  STLENGTH
; Description: Finds the length of the string
; Assumes:     R1 = address of start of ASCIIZ string
; Returns:     R2 = length of the string
STLENGTH
	ST	R0, STLENGTH_R0	; context save
	ST R1, STPOINTER_R1 ; context save
	AND, R2, R2, #0 ; set R2 to 0 as the counter
STLENGTH_LOOP
	
	LDR	R0, R1, #0	; get current character
	BRz	STLENGTH_EXIT	; at null terminator?
	ADD R2, R2, #1 	; increment the count
	ADD	R1, R1, #1	; increment string pointer
	BR	STLENGTH_LOOP

STLENGTH_EXIT
	LD	R0, STLENGTH_R0	; context restore
	LD R1, STPOINTER_R1 ; context restore
	RET

STLENGTH_R0	.BLKW	1	
STPOINTER_R1	.BLKW	1	

		
	.end
        