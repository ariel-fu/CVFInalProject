

		.ORIG	x0200	
	;
	; INITIALIZE VARIABLES
	START	AND	R3, R3, #0	; R3 will hold result--init to 0
		LD	R6, VALUE1	; load multiplication operands
		LD	R5, VALUE2	
	;
	; PERFORM COMPUTATION
	; Repeatedly add R6 to R3 (the number of times indicated by R5)
	; to determine the product R3 = R6 * R5
	; Note: after this, R5 will no longer contain VALUE2...
	;
	LOOP	ADD	R3, R3, R6	
		ADD	R5, R5, #-1	
		BRnp	LOOP	; repeat if R5 is not yet 0
	;
	; STORE RESULT
	;
		ST	R3, RESULT	
	;
	; PROGRAM DATA
	;
	VALUE1	.FILL	#7	
	VALUE2	.FILL	#5	
	RESULT	.FILL	#38	
;
		.END