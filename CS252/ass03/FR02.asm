; Filename:    Routine SWAP.asm
; Author(s):   NAME
;
; Description: Passed two arguments in R0 and R1
;              Swaps the data in R0 and R1
;
; Assumes: R0 & R1 contain data to be swapped
; Returns: R0 & R1 after swapping contents
; Use R2 as temporary/working register
; Do not modify caller's value of R2
.ORIG x0200
START 
	JSR CONDSWAP
	DONE BR DONE


; Filename:    Routine CondSwap.asm
; Author(s):   NAME
;
; Description: Passed two arguments in R0 and R1
;              if (R1>R0) it calls SWAP.
;              
;
; Assumes: R0 & R1 contain data to be compared and potentially swapped
; Returns: R0 & R1 might be modified.
; Use R2 as temporary/working register
; Do not modify caller's value of R2

CONDSWAP
        ; YOUR CODE GOES BELOW HERE
		ST R2, CONDSWAP_R2 ; context save R2
		
		; negate R0 into R2
		NOT R2, R0
		ADD R2, R2, #1
		
		; compare R0 and R1
		ADD R2, R1, R2
		BRnz NOSWAP
			ST R7, SAVE_R7
			JSR SWAP
			LD R7, SAVE_R7
		
		NOSWAP
		LD R2, CONDSWAP_R2
		RET		; return from routine

        ; context save allocation ?

SAVE_R7 .BLKW 1
CONDSWAP_R2 .BLKW 1








SWAP

        ; YOUR CODE GOES BELOW HERE
		ST R2, SAVE_R2 ; save R2 (context save)
		AND R2, R2, #0 ; set to 0 as temp var
		ADD R2, R2, R0 ; set R2 to R0
		
		; set R0 to R1
		AND R0, R0, #0
		ADD R0, R0, R1
		; set R1 to R0 (R2 due to R0 already being changed)
		AND R1, R1, #0
		ADD R1, R1, R2
		
		
		LD R2, SAVE_R2	; restore the value of R2	


		RET		; return from routine

        ; context save?

SAVE_R2 .BLKW 1

	.END