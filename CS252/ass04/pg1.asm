; Filename:    Read Keyboard
; Author:      Ariel Fu
; Description: Read a character from the keyboard and place in R0
;              Does not have to be a sub-routine (no context save/restore)
;			   Need to ensure keyboard has character ready before reading it

        .orig x0200

START   
	LD R1, IO_BASE
; add code here
	GET_CHAR
		LDR R2, R1, #0		; get the status of the keyboard
		BRzp GET_CHAR
	
	LDR R0, R1, #2
		
	
	BR	START	; loop forever (do it again)

IO_BASE .FILL xFE00	; KBSR lives here
					; KBDR is at xFE02
		
	.end