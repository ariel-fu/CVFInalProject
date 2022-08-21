; Filename:    A3Code1.asm
; Author(s):   NAME
;
; Description: Reads data from memory at label SIGNED_DATA
;              Writes the absolute value of that data to
;              memory at label ABS_DATA
;
; NOTE: this is not a sub-routine...you don't have to worry
; about context save/restore
;
; Even though data is x1111 it should work with any value

        .ORIG x0200
START

        ; YOUR CODE GOES BELOW HERE
		LD R0, SIGNED_DATA
		; if negative, negate it
		BRzp WRITE
		NOT R0, R0
		ADD R0, R0, #1
		
		WRITE ; write the (now) positive value to ABS_DATA
		ST R0, ABS_DATA

        ; YOUR CODE GOES ABOVE HERE

        BR START

        ; program data

SIGNED_DATA  .FILL #-3	; This is data to take ABS of
ABS_DATA	 .BLKW 1		; ABS of data is stored here

	.END