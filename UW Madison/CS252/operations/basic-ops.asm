; practices basic operations

	.ORIG x0200
	

	
	LD R0, X	; load X into R0
	LD R1, Y	; load Y into R1
	
	ADD R2, R0, R1 ; add X and Y
	ST R2, SUM ; load X + Y into SUM (x3102)
	
	AND R2, R0, R1 ; AND X and Y together
	ST R2, ANDOP ; load X AND Y into ANDOP (x3103)
	
	; Use DeMorgan's law
	; -(-A AND -B)
	NOT R3, R0 ; get NOT(X) into R3
	NOT R4, R1 ; get NOT(Y) into R4
	; AND THEM TOGETHER
	AND R2, R3, R4 
	; NOT the AND operation
	NOT R2, R2
	
	; load that value into OROP
	ST R2, OROP
		
	
	
	NOT R2, R0 	; get the not of X
	ST R2, NOTX ; load NOT(X) into NOTX (x3105)
	
	NOT R2, R1 	; get the not of Y
	ST R2, NOTY ; load NOT(Y) into NOTY (x3106)
	
	ADD R2, R0, #3 ; get X + 3
	ST R2, ADDXCONST ; load X + 3 into ADDXCONST (x3107)
	
	ADD R2, R1, #-3
	ST R2, SUBYCONST ; load Y - 3 into SUBYCONST (x3108)
	
	AND R2, R0, #1

	BRnp ODD
	AND R2, R2, #0
	ST R2, EVENORODDX
	
	ODD
	AND R2, R2, #0
	ADD R2, R2, #1
	ST R2, EVENORODDX
	
	SPIN BR SPIN 
	
	
	; DATA
	X .FILL #2
	Y .FILL #4
	
		
	SUM .BLKW 1
	ANDOP .BLKW 1
	OROP .BLKW 1
	NOTX .BLKW 1
	NOTY .BLKW 1
	ADDXCONST .BLKW 1
	SUBYCONST .BLKW 1
	EVENORODDX .BLKW 1
	
	