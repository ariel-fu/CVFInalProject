;******************************************************************************
; Filename:    hw11.asm
; Author:      Ariel Fu - 9081685910
; Description: Prompts user to enter two numbers, computes product
;              and prompts user to check result prior to continuing
;
; READ! the comments that indicate what to do
;******************************************************************************
	.ORIG x3000
START    
	
	; Prompt user to enter two numbers, store them in R2 & R1 (respectively)
	LEA R0, PROMPT1				; get address of first prompt
	PUTS						; display prompt for first number
	
	JSR GET_NUM					; call GET_NUM, number returned in R1
	ADD R2, R1, #0				; move number entered to R2 
	
	LEA R0, PROMPT2				; get address of second prompt
	PUTS						; display prompt for first number
	
	JSR GET_NUM					; call GET_NUM, number returned in R1
	

    ; Compute product and display
	JSR MULT					; compute R0 = R1*R2 via call to MULT
	ADD R1, R0, #0				; move result from R0 to R1
	
	LEA R0, PROMPT3				; get address of 3rd prompt
	PUTS						; display 3rd prompt
	GETC						; wait for a key press	
	
	; Do it all again
	BR START			; jump to start of program

;; Reserve Prompt strings
PROMPT1 .STRINGZ "\nEnter 1st decimal number (0-255):"
PROMPT2 .STRINGZ "Enter 2nd decimal number (0-255):"
PROMPT3 .STRINGZ "R1 now contains product (hit any key to continue)"


;******************************************************************************
; Subroutine:  MULT
; Description: Multiplies R1 * R2, returns in R0
; 
; NOTE: Does not need to work for negative values of R1 and R2, but
;       should work for zero values
; NOTE: Can assume product will never exceeed 16-bit value (<65535)
;
; Assumes      R1, and R2 are multiplier and multiplicand
; Returns      R0 - the product of R1 * R2
;
;******************************************************************************
MULT
	; YOUR CODE GOES BELOW HERE
	; context save R1 and R2
	ST R1, MULT_R1
	
	AND R0, R0, #0 ; set R0 to x0000
	; Loop R2 times
	LOOP
		ADD R1, R1, -1	; decrement the multiplier
		BRn RESTORE_RETURN
		; else, add the product again
		ADD R0, R0, R2	; add the multiplicand (again)
		BR LOOP			; Loop again, no matter what. 
		
	RESTORE_RETURN	; context restore
		LD R1, MULT_R1

	RET

	; reserve space for context save/restore
	MULT_R1 .BLKW 1
	MULT_R2 .BLKW 1

	; YOUR CODE GOES ABOVE HERE

;******************************************************************************
; Subroutine:  GET_NUM
; Description: Returns decimal number user entered in R1
; 
; NOTE: Processes characters till it sees x000A (new line) (Enter Key)
;       User should only enter decimal digits and numbers less than 255
;       Routine does not do any error checking of bad user data
; 
; NOTE: This routine does call another routine (MULT10)
;
; Assumes      Nothing
; Returns      R1 - the decimal number user entered
;
; R0 will hold result of GETC
; R1 (which is return value) will be accumulator
; R2 is general purpose temporary register
; R3 will hold -0x000A for compare to new line
; R4 will hold -0x0030 for casting char to num
;******************************************************************************
GET_NUM
	; YOUR CODE GOES BELOW HERE
	
	; store context
	ST R0, GET_NUM_R0
	ST R2, GET_NUM_R2
	ST R3, GET_NUM_R3
	ST R4, GET_NUM_R4
	ST R7, GET_NUM_R7
	
	; initialize stuff
	AND R1, R1, #0 			; set the accumulator to 0
	
	LD R3, NEGATIVE_TEN		; initialize the -A to check for 'enter'
	LD R4, CAST_CHAR		; initialize the char to decimal "converter"
	
	
	; get number via a loop with GETC and accumulate result in R1
	GET_C_LOOP
		GETC						; GETC, and store value into R0
		
		PRINT_OUT	
			OUT						; print out char just entered
		
		ADD R2, R0, R3				; compare to x000A/R3
		BRz RESTORE_VALUES			; if the char entered was 'enter', quit loop
						
		ADD_TO_ACCUMULATOR
			JSR MULT10				; multiply the value in R1 by 10 
			ADD R0, R0, R4			; cast the char value  entered
			ADD R1, R1, R0			; add the value entered by the user
	
	BR GET_C_LOOP			; loop again!
		
	
		
	; restore context
	RESTORE_VALUES
	; reminder: do not restore R1, because that is the return register
		LD R0, GET_NUM_R0
		LD R2, GET_NUM_R2
		LD R3, GET_NUM_R3
		LD R4, GET_NUM_R4
		LD R7, GET_NUM_R7		; context restore R7
		
	RET

	; reserve space for context save and constants (-0x0A and -0x30)
	GET_NUM_R0 .BLKW 1
	GET_NUM_R2 .BLKW 1
	GET_NUM_R3 .BLKW 1
	GET_NUM_R4 .BLKW 1
	GET_NUM_R7 .BLKW 1
	NEGATIVE_TEN .FILL #-10	; -A (-10)
	CAST_CHAR .FILL #-48	; value to cast from char to decimal value (-x30)

	; YOUR CODE GOES ABOVE HERE
	
	
;******************************************************************************
; Subroutine:  MULT10 (complete as provided)
; Description: Multiplies number in R1 by 10
;
; Assumes      R1
; Returns      R1 => R1*10
;
;******************************************************************************	
MULT10
    ; context save
    ST R0, MULT10_R0
	
	; perform computation
	ADD R0, R1, R1;		; have 2X now in R0
	ADD R1, R0, R0		; have 4X now in R1
	ADD R1, R1, R1		; have 8X now in R1
	ADD R1, R1, R0		; have 10X now in R1
	
	; context restore
	LD R0, MULT10_R0
	RET
	
	; reserve space for context save
MULT10_R0 .BLKW 1

	.END

 