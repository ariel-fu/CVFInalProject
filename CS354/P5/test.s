	.file	"ToUpper_c.c"
	.text
	.globl	_To_Upper
	.def	_To_Upper;	.scl	2;	.type	32;	.endef
_To_Upper:
LFB14:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$16, %esp
	movl	$0, -4(%ebp)
	jmp	L2
L4:
	movl	-4(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	movb	%al, -5(%ebp)
	cmpb	$96, -5(%ebp)
	jle	L3
	cmpb	$122, -5(%ebp)
	jg	L3
	movzbl	-5(%ebp), %eax
	leal	-32(%eax), %ecx
	movl	-4(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	%ecx, %edx
	movb	%dl, (%eax)
L3:
	addl	$1, -4(%ebp)
L2:
	movl	-4(%ebp), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movzbl	(%eax), %eax
	testb	%al, %al
	jne	L4
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE14:
	.def	___main;	.scl	2;	.type	32;	.endef
	.section .rdata,"dr"
LC0:
	.ascii "%s\0"
	.text
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB15:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	subl	$64, %esp
	call	___main
	movl	$1948283753, 44(%esp)
	movl	$544434536, 48(%esp)
	movl	$1802661751, 52(%esp)
	movl	$6778473, 56(%esp)
	movl	$0, 60(%esp)
	leal	44(%esp), %eax
	movl	%eax, (%esp)
	call	_To_Upper
	leal	44(%esp), %eax
	movl	%eax, 4(%esp)
	movl	$LC0, (%esp)
	call	_printf
	movl	$1633771873, 24(%esp)
	movl	$1948283753, 28(%esp)
	movl	$544434536, 32(%esp)
	movl	$1802661751, 36(%esp)
	movl	$6778473, 40(%esp)
	leal	24(%esp), %eax
	movl	%eax, (%esp)
	call	_To_Upper
	leal	24(%esp), %eax
	movl	%eax, 4(%esp)
	movl	$LC0, (%esp)
	call	_printf
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE15:
	.ident	"GCC: (i686-posix-dwarf-rev0, Built by MinGW-W64 project) 8.1.0"
	.def	_printf;	.scl	2;	.type	32;	.endef
