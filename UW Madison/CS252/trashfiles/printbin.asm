 .orig x3000

START
	; go to next line in console
	LEA R0, LF
	PUTS
	 ; run PRTBIN on TESTVAL1
	LD R1, TESTVAL1
	JSR PRTBIN
	 ; go to next line in console
	LEA R0, LF
	PUTS
	 ; run PRTBIN on TESTVAL2
	LD R1, TESTVAL2
	JSR PRTBIN
 ; loop forever

 BR START

; --- DATA FOR MAIN PROGRAM ---
TESTVAL1 .FILL x0001
TESTVAL2 .FILL xF078
LF .STRINGZ "\n"




; PRTBIN
; Prints 16 bit binary value to console display
; Assumes: R1 - value to print to console
; Returns: nothing
PRTBIN
; context save
	ST R1, PRTBIN_R1
	ST R0, PRTBIN_R0
	ST R2, PRTBIN_R2
	ST R3, PRTBIN_R3
	ST R7, PRTBIN_R7

	LD R2, ASCII
	LD R3, INDEX
	LOOP
		
		AND R1, R1, R1 			; check the value of R1
		BRn SET_ONE				; if the value is a negative value, the MSB = 1		
		
		SET_ZERO				; if MSB = 0, set R0 to ascii 0
			ADD R0, R2, #0		; set R0 to '0' (ascii 0)
			BR SET_UP
			
		SET_ONE					; if MSB - 1, set R0 to ascii 1
			ADD R0, R2, #1		; set R0 to ascii 1 (1 + x0030)
			
		SET_UP
			ADD R3, R3, #-1		; check the number of loops
			BRn CONTEXT_RESTORE
			
			OUT					; print out the value in R0
			ADD R1, R1, R1,		; do a left shift
			BR LOOP				; loop back to check the MSB
			
			
	CONTEXT_RESTORE				; have looped 16 times, but we need to restore all registers
		LD R1, PRTBIN_R1
		LD R0, PRTBIN_R0
		LD R2, PRTBIN_R2
		LD R3, PRTBIN_R3
		LD R7, PRTBIN_R7
RET


PRTBIN_R0 .BLKW 1
PRTBIN_R1 .BLKW 1
PRTBIN_R2 .BLKW 1
PRTBIN_R3 .BLKW 1
PRTBIN_R7 .BLKW 1

ASCII .FILL x0030
INDEX .FILL #16