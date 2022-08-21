; practices a pc-relative load and store
; pc-relative is a LD and ST


	; .ORIG x0200
; START
	; LD R0, A 	; load the value A into R0
	; LEA R1, B	; can we load a string into a register?
	; PUTS
	
; BRnzp START 	; go back to the start

	; ; fill some values
	; A .FILL #12
	; B .STRINGZ "ABC"
	
	
	
	
	.ORIG X0200
	LEA R0, HW
	PUTS
	HALTS
; data
HW .STRINGZ "Hello World"

	.END