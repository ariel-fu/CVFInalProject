
	.ORIG x3000

START

LD R0, VALUE3
ADD R0, R0, #3
ST R0, VALUE3
BR START




	; LEA R0, STR1
	; PUTS
	; JSR STRREV
	; PUTS

	; DONE BR DONE

VALUE5 .FILL #91
VALUE4 .BLKW 1
VALUE3 .BLKW 1
VALUE2 .BLKW 1
STR1 .STRINGZ "cat"




; Subroutine reverses all the chars in R0
; assumes R0 is the string address

STRREV
ST R0, STORE_R0 ; context save

LOOP ; get address of last character
	LDR R1, R0, #0
	BRz GETVALUES
	ADD R0, R0, #1
BR LOOP

GETVALUES
; R1 holds the address of the last character
	AND R1, R1, #0
	ADD R1, R0, #0
; R0 holds the address of the first character
	LD R0, STORE_R0	; context restore
; Swap the char at mem[R0] with char at mem[R1]

SWAP 	
	
	ST R7, STORE_R7 ; context store the return address
	JSR SWAPING
	LD R7, STORE_R7	; restore the address
	
	ST R1, STORE_R1 ; context store
	; negate R1
	NOT R1, R1
	ADD R1, R1, #1
	
	ADD R1, R0, R1
	
	; if negative, or zero, quit
	BRnz EXIT
	
	LD R1, STORE_R1 ; restore R1 to keep swapping
	; increment R0 and decrement R1 until R0 <= R1
	ADD R1, R1, #-1
	ADD R0, R0, #1
	

EXIT

RET

SWAP_R1 .BLKW 1
SWAP_R0 .BLKW 1
STORE_R7 .BLKW 1
STORE_R0 .BLKW 1
STORE_R1 .BLKW 1



; Subroutine that swaps the chars at R0 and R1
; assumes that R0 and R1 hold chars


; BUG: R1 is getting the value that is currently there after it gets swapped. leading to problems with it not being ; able to find a the old value. 
SWAPING 
	ST R1, SWAP_R1; context store R1
	ST R0, SWAP_R0 ; context store R0
	
	; put R0 into mem[R1]
	LDR R0, R0, #0
	STR R0, R1, #0
	
	LD R1, SWAP_R1 ; restore R1 to put that value into mem[R0]
	LD R0, SWAP_R0 ; restore R0 to get the address 
	
	
	LDR R1, R1, #0 ; get the value of R1
	STR R1, R0, #0
	
	RET
	
	
	
.END



