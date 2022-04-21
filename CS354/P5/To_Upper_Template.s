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
	pushq %rbp 			# make a copy of the curr base pointer
	movq %rsp, %rbp		# move the base pointer to the top of the curr stack
	subq $24, %rsp	# make space for the pointer at the top of the stack

	# This code prints the letter 'a' (ascii value 97)
	# Use this for debugging
	# Comment out when finished - your function should not print anything
	# Note putchar overwrites all caller saved registers including argument registers
#		movl	$97, %eax
#		movl	%eax, %edi
#		call	putchar@PLT


	# Body of function
	# set i = 0
	movl $0, -4(%rbp)
	# store input in %rdi = -24(%rbp)
	movq %rdi, -24(%rbp)

	TOP_LOOP:
		JMP CONDITION
	LOOP_BODY:
		# move i and str into registers rdx and rax, respectively
		movq -4(%rbp), %rdx
		movq -24(%rbp), %rax
		
		# get the curr character
		addq %rdx, %rax
		# move last byte in rax to %cL
		movzbl (%rax), %ecx
		movb %cl, -5(%rbp)
		movb -5(%rbp), %cl
		
		# check for lowercase char: 'a' < curr_char < 'z'
		CHECK_CASE:
			# check bottom bound
			cmpb $97, %cL
			JL NOT_UPPER	# not within bottom bound
			# check upper bound
			cmpb $122, %cL
			JG NOT_UPPER	# not within upper bound
			# within bounds: change from lowercase to uppercase
			IS_UPPER:
				addb $32, %cl		# change case
				movb %cl, (%rax)	# put the uppercase char back into mem
				JMP INCREMENT_I		# jump over the false block
			NOT_UPPER:
				# do nothing here
				
		# increment i
		INCREMENT_I:
			# i++
			addL $1, -4(%rbp)
			
	# check the condition of the loop
	CONDITION:
		# move i and str into registers %rdx and %rax, respectively
		movq -4(%rbp), %rdx
		movq -24(%rbp), %rax
		
		# get the curr character
		addq %rdx, %rax
		# move the last byte in %rax to %cl
		movzbl (%rax), %ecx
		movb %cl, -5(%rbp)
		movb -5(%rbp), %cl
		
		
		# check if the curr character is the null char (0)
		addb %cL, %cL
		# if it is not, continue looping
		JNE LOOP_BODY
	
	END:
# Epilogue
	addq $24, %rsp 	# move the top stack ptr back
	popq %rbp 			# move the base ptr back to what it was
	ret					# set the instruction ptr to the right address
