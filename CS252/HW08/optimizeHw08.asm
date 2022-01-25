; Filename    hw08.asm
; Author(s)   Ariel Fu
;              N/A
;
; Description Performs various operations on values in
;              various memory locations        


	.ORIG x0200
START        
	LEA R5, data          ; get pointer to data array        
	
	LDR R3, R5, #3		; get the value of label A: data[3]
	
	; AND R4, R4, #0 		; "clear" the register, R4, for an operation (do i need this?)
	ADD R4, R3, #-2 	; get the value of label B: A-2
	
	LDR R0, R5, #0 		; get the value of data[0] to calculate C
	
	; AND R1, R1, #0 		; "clear" R1 (do i need this ???)
	
	ADD R1, R4, R0		; get the value of label C: B + data[0]
	

	LDR R0, R5, #2 		; get the value of data[2] 
	
	; AND R2, R2, #0 		; "clear" R2 need this??
	ADD R2, R4, R0		; get the value of data[1]: B + data[2]
	
	; store R2 into data[1] 	
	STR R2, R5, #1 		; store B + data[2], stored in R2, into data[1]
	
	; store the calculated values into their corresponding labels
	ST R3, A			; store data[3] into A
	ST R4, B 			; store A - 2 into B
	ST R1, C 			; store B + data[0] into C
	
	
	; YOUR CODE GOES ABOVE HERE        
	BR START        


A       .FILL x0004
B       .FILL x0002
C       .FILL xABCD      
	; Note normally we would not comment an array like this,
    ; but we wanted to make it easy to see which element is which
data    .FILL #1  ; data[0]        
		.FILL #195  ; data[1]        
		.FILL #1       ; data[2]        
		.FILL #4   ; data[3]

; not actual data, need to use data down below once done debugging


	
	; program data
;A       .FILL x1111
;B       .FILL x2222
;C       .FILL x3333      
	; Note normally we would not comment an array like this,
    ; but we wanted to make it easy to see which element is which
;	data    .FILL #10  ; data[0]        
;	.FILL #20  ; data[1]        
;	.FILL #-15       ; data[2]        
;	.FILL #1   ; data[3]
	.END