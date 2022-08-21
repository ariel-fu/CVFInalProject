; Filename:    hw09.asm
; Author(s):   Ariel Fu
;              NAME
;
; Description: Performs various operations on values in
;              various memory locations


        .ORIG x0200
START

    ; YOUR CODE GOES BELOW HERE
	; set up some "constants" 
      LEA R0, VARS ; the pointer to the array of variables
	  LDR R1, R0, #0 ; the value of A for comparing
	  
	; the value B for the first comparison
	  LDR R2, R0, #1	  
	  ; negate B (in R2)
	  NOT R2, R2
	  ADD R2, R2, #1
	  
	; compare A and B by subtracting B from A
	ADD R2, R1, R2

	BRp COMPARETO32; if A > B, go to COMPARETO32
	
	; else, C <- D
	LDR R2, R0, #3 ; get the value of D
	STR R2, R0, #2 ; then store into C
	BR DONE	
	
	COMPARETO32 ; A is greater than B, compare A to 32	
		; the value of -32 for comparisons later
		AND R3, R3, #0
		ADD R3, R3, #-16
		ADD R3, R3, #-16
		
		; Add -32 to A
		ADD R2, R1, R3
		BRp GETB ; if A > 32, go to GETB
		
		; else, set R2 to the value of E
		LDR R2, R0, #4 ; get the value of E
		BR SETVALUES ; go to SETVALUES to set the values of C and D
			
		GETB ; (A > 32): set R2 to the value of B
			LDR R2, R0, #1 ; get the value of B
		
		SETVALUES 
			; Store R2 (either E or B) into C
			STR R2, R0, #2 
			; then store the value of A into D
			STR R1, R0, #3 
			
	


	
	
    ; YOUR CODE GOES ABOVE HERE

DONE    BR DONE


VARS .FILL  10      ; A
     .FILL  20      ; B
     .FILL  0       ; C
     .FILL  10      ; D
     .FILL  5       ; E

	.END
