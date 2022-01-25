; practice with subroutines

	.ORIG x0200
	
START

LEA R3, MyData
AND R0, R0, #0	; set the max to 0 
ADD R4, R4, #15
ADD R4, R4, #5

LOOP	
	LDR R1, R3, #0 	; get the next number
	JSR MAXIMUM 		; get the max between R0 and R1
	; the max is now in R2, so we need to set it to R0
	AND R0, R0, #0
	ADD R0, R2, #0
	
	; decrement R4, which holds our origonal value of 20
	ADD R4, R4, #-1
	BRz DONE ; if we have looped 20 times, quit
	
	; otherwise, increment the array pointer and loop again
	ADD R3, R3, #1
	BR LOOP
	


DONE BR DONE



; Values come in from R0 and R1
; Return maximum value in R2
MAXIMUM
ST R1, STORE_MAX	; context store
	; confirm R2 = 0
	AND  R2, R2, #0
	
	; negate R1
	NOT R1, R1
	ADD R1, R1, #1
	
	ADD R1, R0, R1 ; Add R0 and -R1 to determine the bigger value
	BRp POSITIVE
	BRn NEGATIVE
	; R0 - R1 = 0, so they are equal
	ADD R2, R0, #0
	BR MAX_EXIT
	
	POSITIVE
	; R0 > R1
	ADD R2, R0, #0
	BR MAX_EXIT
	
	NEGATIVE ; R0 < R1
	LD R1, STORE_MAX ; context restore
	ADD R2, R1, #0

	MAX_EXIT
RET

STORE_MAX .BLKW 1


MyData .FILL #0001
.FILL #0001
.FILL #0003
.FILL #0002
.FILL #0006
.FILL #0011
.FILL #0012
.FILL #0016
.FILL #0008
.FILL #0002
.FILL #0010
.FILL #0012
.FILL #0000
.FILL #0003
.FILL #0032
.FILL #0006
.FILL #0009
.FILL #0004
.FILL #0005
.FILL #0002
.END