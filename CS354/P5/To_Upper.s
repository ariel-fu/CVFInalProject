	.file	"To_Upper.s"
	.text
	.globl	To_Upper
	.type	To_Upper, @function

/* **********************
    Name: Ariel Fu
    Wisc ID Number: 9081685910
    Wisc Email: afu5@wisc.edu
************************ */


To_Upper:

# C version
/* **********************
    Write the equivalent C code to this function here (in the comment block)
	
	void To_Upper(char *str){
		for(int i = 0; str[i]; i++){
			// check if the str is a lowercase char: a < str < z
			char curr = str[i];
			if(curr >= 97 && curr <= 122){
				// convert to upper case
				str[i] = (char)((int)curr - 32);
			}
		}
	}
	
************************ */


# Memory Layout
/* ************************ 
    Make a table showing the stack frame for your function (in the comment block)
    The first column should have a memory address (e.g. 8(%rsp))
    The second column should be the name of the C variable used above
    Update the example below with your variables and memory addresses
        -24(%rbp)	|	str		|		base address of the string
        -4(%rbp)	|	i		| 		index for the array/string
		-5(%rbp)	| curr		| 		current character
		
************************ */

# Prologue
	pushq	%rbp 					# make a copy of the curr base pointer
	movq	%rsp, %rbp				# move the base pointer to the top of the curr stack
	subq	$32, %rsp				# make space for the pointer at the top of the stack

	# This code prints the letter 'a' (ascii value 97)
	# Use this for debugging
	# Comment out when finished - your function should not print anything
	# Note putchar overwrites all caller saved registers incLuding argument registers
	# movl	$97, %eax
	# movl	%eax, %edi
	# call	putchar@PLT


	# Body of function	
	movq	%rdi, -24(%rbp)			# store input in %rdi = -24(%rbp)
	movl	$0, -4(%rbp)			# set i = 0
# check if null or empty
	movq -24(%rbp), %rdi			# grab the input from memory
	cmpq $0, %rdi					# check if the input is 0/NULL
	je END							# if equal to 0/NULL, skip to the END

# remove the first "do" - check condition first
TOP_LOOP:
		JMP CONDITION
LOOP_BODY:	
    movl	-4(%rbp), %edx			# get i
    movslq	%edx, %rdx				# sign extend to the 64-bit register
		
    movq	-24(%rbp), %rax			# get str into %rax
		
    addq	%rdx, %rax				# get the curr char		
    movb	(%rax), %cL				# move the curr char to its correct register: %cL
	movb	%cL, -5(%rbp)			# store the current char in memory
		
# check for lowercase char: 'a' < curr_char < 'z'
CHECK_CASE:		
	movb	-5(%rbp), %cL			# get the current char from memory
		
	cmpb	$97, %cL				# check bottom bound
	JL NOT_UPPER					# not within bottom bound		
			
	cmpb 	$122, %cL				# check upper bound
	JG NOT_UPPER					# not within upper bound
			
# within bounds: change from lowercase to uppercase
IS_UPPER:
# perform (curr - 32)			
	movb	-5(%rbp), %cL			# get curr
	addb	$-32, %cL				# add -32 to get the uppercase letter
	movb %cl, -5(%rbp)				# store the new uppercase char into memory
				
# perform str[i] = (curr - 32)
	movq	-24(%rbp), %rax			# get the string into %rax

	movl    -4(%rbp), %edx			# get i
	movslq  %edx, %rdx				# sign extend to the 64-bit register
				
	addq    %rdx, %rax				# get the address of where curr is
	movb	-5(%rbp), %cL			# get the current char from memory
	movb    %cL, (%rax)				# put (curr - 32) into the address
				
	JMP INCREMENT_I					# jump over the false block

# do nothing here				
NOT_UPPER:					
				
# increment i
INCREMENT_I:	
	addL	$1, -4(%rbp)			# i++
			
# check the condition of the loop
CONDITION:
    movl	-4(%rbp), %edx			# get i
    movslq	%edx, %rdx				# sign extend to the 64-bit register

    movq	-24(%rbp), %rax			# get string into %rax

    addq	%rdx, %rax				# get the curr char
    movb	(%rax), %cL				# move the curr char to its correct register: %cL - throwing SegFault when input is NULL
	movb	%cL, -5(%rbp)			# store the current char in memory	
		
	movb	-5(%rbp), %cL			# get the current char
	addb	%cL, %cL				# check if the curr character is the null char (0)
	
	JNZ LOOP_BODY					# if it is not, continue looping
	
# otherwise, the end of the string has been reached
END:
# Epilogue
	addq	$32, %rsp 				# move the top stack ptr back
	popq	%rbp 					# move the base ptr back to what it was
	ret								# set the instruction ptr to the right address
