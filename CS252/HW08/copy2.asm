; File name:   copy2.asm
; Author:      
; Description: copy a value from one memory location
;              to another using LDR and STR

	.ORIG x0200
START
	LEA R0, ANIMALS	; get base address for ANIMALS array
        ; your code below
	LDR R1, R0, #2 	; read from DOG and copy to R1
	STR R1, R0, #6	; copy DOG (R1) to MONKEY

	; your code above
        BR START

; program data
ANIMALS	.FILL x1234	; BIRD
	.FILL x7890	; CAT
	.FILL xABCD	; DOG
	.FILL x00FF	; FISH
	.FILL xFF00	; HORSE
	.FILL x1248	; LIZARD
	.FILL x1337	; MONKEY
	.FILL xCAFE	; SALMON
	.FILL xFACE	; TURKEY
	.FILL xBEEF	; ZEBRA

	.END