
;******************************************************************************
; Filename:    hw12.asm
; Author:      Ariel Fu - 908 168 5910
; Description: Prompts user to enter decimal number then compute the square
;              root (through successive approx) and displays the result
;******************************************************************************
	.ORIG x3000
START    

	; Prompt user to enter a decimal number
	LEA R0, PROMPT		; get address of prompt
	PUTS				; display prompt for number entry
	JSR GET_NUM			; number returned in R1
	ADD R3, R1, #0		; move number to R3

    ; Compute square root and display
	JSR SQRT			; compute R2 = sqrt(R3) via call to SQRT
	
	LEA R0, RSLT_STR	; get address of result display string
	PUTS				; display result display string
	JSR DISP_NUM		; wait for a key press
	
	; Do it all again
	BR START			; jump to start of program

;; Reserve Prompt strings
PROMPT .STRINGZ "\nEnter number (0-32767):"
RSLT_STR .STRINGZ "The square root is:"

;******************************************************************************
; Subroutine:  DISP_NUM
; Description: Displays up to a 3-digit decimal number as ASCII to console 
; 
; NOTE: Use successive subtraction to figure 100's, 10's, 1's places
;
; NOTE: it is OK to print with leading 0's.  If R2 was 23 could print 023
;
; Assumes      R2 is argument of number to display (0-999)
; Returns      Nothing
;
;******************************************************************************
DISP_NUM
	; YOUR CODE GOES BELOW HERE
	
	; context save
	ST R0, DISPLAY_R0
	ST R1, DISPLAY_R1
	ST R2, DISPLAY_R2
	ST R3, DISPLAY_R3
	ST R4, DISPLAY_R4
	ST R5, DISPLAY_R5
	ST R7, DISPLAY_R7
	
	
    ; initialize
	LD R3, ASCII_ADD	; 0x0030 to convert int to ASCII char
	AND R5, R5, #0		; set the accumulator to 0
	
	; hundredths place computation & display
	LD R0, PLACE100		; -100 to calculate 100ths place
	HUNDREDTHS
		ADD R4, R2, R0	; subtract 100 from it once
		BRn PRINT_100	; if the number is less than 100, then go to print it out
		ADD R5, R5, #1 	; else, increment the accumulator (number of 100s)	
		ADD R2, R4, #0	; R2 = (R2-100)
		BR HUNDREDTHS	; subract again
	
	PRINT_100			; prints out the hundrendths place
		ADD R1, R5, #0	; store the number of 100s for later
		BRz TENTHS		; if the accumulator (R5) = 0, then move onto the tenths place
			
		ADD R0, R5, R3	; else, convert to an ASCII char
		OUT				;then print out the hundredths place
		AND R5, R5, #0	; set the accumulator to 0 (set up for TENTHS)
		
		
	; tens place computation and display
	TENTHS 
	
		LD R0, PLACE10		; set to -10 to calculate 10ths place
		
		LOOP_TEN			; loop through
		
			ADD R4, R2, R0	; subtract 10 from the arg
			BRn PRINT_10	; if the number is less than 10, then go print it out
			ADD R5, R5, #1 	; otherwise, increment the number of 10s
			ADD R2, R4, #0	; R2 = (R2-10)
		BR LOOP_TEN			; subtract again

	PRINT_10
		AND R1, R1, #1		; get the stored number of 100s
		BRnp PRINT_OUT_10	
		; if there are no 100s and there are no 10s, move onto ONES
		AND R5, R5, R5
		BRz ONES			; if there are 10s, we can print them out		
		
		PRINT_OUT_10
			ADD R0, R5, R3	; convert to its ASCII counterpart
			OUT 			; print out
			AND R5, R5, #0	; set the accumulator back to 0 
		

	; ones place display		
	ONES
		LD R0, PLACE1		; set R0 to -1 to calculate 1s place
		
		LOOP_ONES
			ADD R4, R2, R0	; subtract 1 from the arg
			BRn PRINT_1		; if the number is less than 1, go to print it out
			ADD R2, R4, #0	; R2 = (R2-1)
			ADD R5, R5, #1	; else, increment the number of 1s and loop again
		BR LOOP_ONES
	
	PRINT_1	
		ADD R0, R5, R3	; convert to its ASCII counterpart
		OUT				; then print it out

	RESTORE				; restore all the regsiters used
	; context restore
	LD R0, DISPLAY_R0
	LD R1, DISPLAY_R1
	LD R2, DISPLAY_R2
	LD R3, DISPLAY_R3
	LD R4, DISPLAY_R4
	LD R5, DISPLAY_R5
	LD R7, DISPLAY_R7
	
	RET
	
	; allocate for context save and constants
DISPLAY_R0 .BLKW 1
DISPLAY_R1 .BLKW 1
DISPLAY_R2 .BLKW 1
DISPLAY_R3 .BLKW 1
DISPLAY_R4 .BLKW 1
DISPLAY_R5 .BLKW 1
DISPLAY_R7 .BLKW 1

PLACE100 .FILL #-100
PLACE10  .FILL #-10
PLACE1	 .FILL xFFFF
ASCII_ADD .FILL x0030	
	; YOUR CODE GOES ABOVE HERE


;******************************************************************************
; Subroutine:  SQRT
; Description: Computes sqrt(R3) via successive approximation.  It starts with
;			   MSB set (0x0080) and multiplies. If product is greater than R3
;              the bit is cleared and the next bit is set and tested (0x0040).
;              If, however, the product was less than R3 the bit would be kept
;              set and the next bit would be set and tested (0x00C0).  This
;              process continues 7 more times till all bits have been tested
;              and set/cleard            
; 
; NOTE: Assumes input argument R3 is in range (0-65535)
;
; Assumes      R3 is argument to take sqrt of
; Returns      R2 = sqrt(R3)
;
;******************************************************************************
SQRT
	; YOUR CODE GOES BELOW HERE
	; context save
	
	ST R0, SQRT_R0	
	ST R1, SQRT_R1	
	ST R3, SQRT_R3	
	ST R4, SQRT_R4	
	ST R5, SQRT_R5	
	ST R6, SQRT_R6	
	ST R7, SQRT_R7	

	; initialize
	LEA R4, MASK_BASE 				; set R4 to the address of the masking "array"
	
	AND R2, R2, #0					; clear the return register

	AND R5, R5, #0 
	ADD R5, R5, #8 					; counter for the loop
	
	; compute sqrt using successive approx and check
	SUCC_APPROX 
		LDR R6, R4, #0				; get the mask value into R6
		
		ADD_TO_CURR_VALUE
			ADD R2, R2, R6			; add the offset to the current sqrt value
			AND R1, R2, R2			; put the value in R2 in R1		
			
		JSR MULT					; get R2 squared, store in R0
	
		COMPARE_SQUARES 
			; negate R3 for comparison, store in R1
			NOT R1, R3
			ADD R1, R1, #1
		
			ADD R1, R0, R1			; add R0 ((curr sqrt value)^2) to R1 (-R3)
			BRp CLEAR_BIT			; the (curr sqrt value)^2 is too big
			BRn SET_FOR_NEXT_LOOP	; if the (curr sqrt value)^2 is too small, go to setup for another loop
			
		IF_EQUAL					; if (curr sqrt value)^2 equal to R3, go to QUIT
			BR QUIT 
					
		
		CLEAR_BIT					; need to clear the curr bit back to 0 
			NOT R0, R6				; NOT the mask, so only the new bit is = 0
			AND R2, R2, R0			; AND the series of all 1s (except for the bit to clear) with the current sqrt value
			
		SET_FOR_NEXT_LOOP			; sets up the values for the next loop
			ADD R4, R4, #1 			; increment the address of the mask bits
			ADD R5, R5, #-1 		; if R5 = 0, then we have gone through all the bits
			BRnp SUCC_APPROX
			
	QUIT							; context restore every register, but R2, then RET
	; context restore
		LD R0, SQRT_R0
		LD R1, SQRT_R1
		LD R3, SQRT_R3
		LD R4, SQRT_R4
		LD R5, SQRT_R5
		LD R6, SQRT_R6
		LD R7, SQRT_R7

	RET
	
	; allocate for context save
SQRT_R0 .BLKW 1
SQRT_R1 .BLKW 1
SQRT_R3 .BLKW 1
SQRT_R4 .BLKW 1
SQRT_R5 .BLKW 1
SQRT_R6 .BLKW 1
SQRT_R7 .BLKW 1
MASK_BASE .FILL x0080
          .FILL x0040
		  .FILL x0020
		  .FILL x0010
		  .FILL x0008
		  .FILL x0004
		  .FILL x0002
		  .FILL x0001
		  .FILL x0000
	
	; YOUR CODE GOES ABOVE HERE

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
	; context save R1
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


;*************************

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
