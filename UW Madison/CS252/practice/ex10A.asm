; Count the number of non-negative values in an array

       .ORIG x0200

START

       ; your code goes below here
	LEA R0, Arr 	; load the pointer to the array in R0
	LD R1, ArrSz	; load the size of the array into R1
	ADD R3, R3, #0 	; clear R3 - sum of positive values in the array
	LOOP
		LDR R2, R0, #0 ; get the value at the front of the array
		BRn INCREMENT	; if the value is negative, skip to incrementing
		; else, add to the sum
		ADD R3, R3, R2 ; add to the Sum 	
		
		INCREMENT
		ADD R0, R0, #1 ; go to the next value
		ADD R1, R1, #-1 ; decrement the size of the array
		BRp LOOP 	; only go back into the loop if the size of the array > 0

	ST R3, Count 	; store the result

       ; your code goes above here


       BRnzp START     ; once is never enough...

Count  .BLKW #1        ; result goes here
ArrSz  .FILL #10       ; number of array elements
Arr    .FILL #12       ; array data
       .FILL #-23
       .FILL #0
       .FILL #42
       .FILL #17
       .FILL #-12
       .FILL #59
       .FILL #-76
       .FILL #45
       .FILL #26

       .END