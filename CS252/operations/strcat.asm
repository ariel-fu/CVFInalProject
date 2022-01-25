        .orig x3000

START   
	; first show initial strings before modification
	
        ; print all three strings on one line
	LEA	R0, LF	; print newline
	PUTS
	LEA	R0, S1	; print the strings
	PUTS
	LEA	R0, S2
	PUTS
	LEA	R0, S3
	PUTS
	LEA	R0, LF	; print newline
	PUTS

TEST1	; first test
	LEA	R1, S2
	LEA	R2, S3
	JSR	STRCAT	; append S2 to S3
			; after subroutine returns, S3 should be "horse+bird+"

        ; print all three strings on one line
	LEA	R0, S1	; print the strings
	PUTS
	; TODO: remove
	LEA	R0, LF	; print newline
	PUTS
	LEA	R0, S2
	PUTS
	; TODO: remove
	LEA	R0, LF	; print newline
	PUTS
	; TODO: remove
	LEA	R0, S3
	PUTS
	LEA	R0, LF	; print newline
	PUTS

TEST2	; second test
	LEA	R1, S1
	LEA	R2, S3
	JSR	STRCAT	; append S1 to S3
			; after subroutine returns, S3 should be "horse+bird+cat+"

   ; print all three strings on one line
	LEA	R0, S1	; print the strings
	PUTS
	; TODO: remove
	LEA	R0, LF	; print newline
	PUTS
	LEA	R0, S2
	PUTS
	; TODO: remove
	LEA	R0, LF	; print newline
	PUTS
	; TODO: remove
	LEA	R0, S3
	PUTS
	LEA	R0, LF	; print newline
	PUTS
	HALT		; end program

; test data
LF	.STRINGZ	"\n" 
S1	.STRINGZ	"cat+"
S2	.STRINGZ	"bird+"
S3	.STRINGZ	"horse+"
	.BLKW	16	; extra space



; STRCPY
; Copy source string to destination
; Assumes: R1 - source string address
;          R2 - destination address
; Returns: Nothing

; -- put your STRCPY subroutine and its data below here --

; -- put your STRCPY subroutine and its data above here --


; ONLY TURN IN YOUR STRCAT SUBROUTINE CODE AND ITS DATA.
; DO NOT TURN IN THE CALLER CODE AND DATA ABOVE

; DO NOT MODIFY ANYTHING ABOVE HERE *EXCEPT* FOR COPYING
; IN YOUR **TESTED AND WORKING** STRCPY SUBROUTINE

; STRCAT
; Append source string to destination
; Assumes: R1 - source string address
;          R2 - destination address
; Returns: Nothing
STRCAT
	
	; loop through all the characters
	STCAT_LOOP
	
		
		LDR R0, R2, #0 		; get the current char in the destination string, the string to be appended on
		BRz GOTOSTRCPY 	; null character, quit. 		
		ADD R2, R2, #1 		; increment the address in R2 to get the destination address
		
	BR STCAT_LOOP
	
	GOTOSTRCPY
		ST R7, STORE_R7; store the curr address into R7
		JSR STRCPY 	; R2 = address of null terminator of the origional string
					; R1 = address of string to be copied to address of R2
					
		LD R7, STORE_R7			; context restore
	

STRCAT_EXIT
	
	RET
	
STORE_R7 .BLKW 1	; save space for R7
STORER1	.BLKW 1 	; save space for R1
	

; STRCPY
; Copy source string to destination
; Assumes: R1 - source string address
;          R2 - destination address
; Returns: Nothing
STRCPY
	ST R1, STORE_R1			; context save
	AND R3, R3, #0			; stores the curr char
	; loop through all the characters
	STCOPY_LOOP
		LDR R0, R1, #0 		; get the current char
		STR R0, R2, #0		; MEM[R2[address]] <- curr char
		BRz STRCPY_EXIT 	; null character, quit. 		
		
		ADD R1, R1, #1 		; increment the string pointer
		ADD R2, R2, #1 		; increment the address in R2
	BR STCOPY_LOOP
	
STRCPY_EXIT
	LD R1, STORE_R1			; context restore
	RET

STORE_R1 .BLKW 1

	.end
       