
	.ORIG x0200
START

; TEST       ADD R1, R0, #-5
           ; ;BRz R0_IS_5
           ; BRnp R0_ISNT_5
; R0_IS_5    ; execute if R0 = 5
          
           ; BR MORECODE
; R0_ISNT_5  ; execute if R0 ≠ 5
           
          
		   ; ;BR MORECODE
; MORECODE   ; execute afterwards 
           ; ; for either case
		 

	; Example program	 
	 	
	; BEGIN	LD R2, VAL_A	 ; 01BB
	 	; LD R3, VAL_B			;01BC
	 	; ADD R1, R2, R3			;01BD
	 	; LEA R0, SPACE			;01BE
	 	; STR R1, R0, #3	 		;01BF
		; BR BEGIN	 			; 01C0
; ; Data for the program	 
	; NAME .STRINGZ "logical"
	; SPACE	.BLKW 5	 			;01C1
	; VAL_A	.FILL #9	 		;01C6
	; GAP	.BLKW 3	 
	; VAL_B	.FILL #5	 
	
	
	LD R1, MYSTRING1
	LEA R3, MYSTRING2
	LDR R5, R3, #2
	
	SPIN BR SPIN
	
	MYSTRING1 .STRINGZ "Ice"
	MYSTRING2 .STRINGZ "orbs"
	
	 	.END	 	 

		   
