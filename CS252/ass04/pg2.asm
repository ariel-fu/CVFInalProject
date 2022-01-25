; Filename:    HelloWorld.asm
; Author:      Ariel Fu
; Description: A classic loved by all (prints Hello World! to console)

        .orig x3000

START   
	
	LEA R0, HELLO_WORLD
	PUTS
		
	BR	START	; loop forever (do it again)


HELLO_WORLD .STRINGZ "Hello World!"
		
	.end
	