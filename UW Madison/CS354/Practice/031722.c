#include <stdio.h>
#include <stdlib.h>

void foo1(int *xval)
{
    int x = 50;
    // x = *xval;
    printf("x: %d, x value %p, x address: 0x%x\n", x, &x);
    printf("xval: %d, x value %p, xval address: 0x%x\n", *xval, xval, &xval);
}

void foo2(int dummy)
{
    int y;
    printf("dummy: %d, dummy address: 0x%x\n", dummy, &dummy);
    printf("y: %d, y address: 0x%x\n", y, &y);
}

void swap_nums(int *x, int *y)
{
    int tmp;
    tmp = *x;
    *x = *y;
    *y = tmp;
}

void swap_pointers(char **x, char **y)
{
    char *tmp = *x;
    *x = *y;
    *y = tmp;
}

void main()
{
    // float dbl = 1.0;
    // int integer = 1;
    // char character = '1';

    // float *dlbp = &dbl;
    // int *intp = &integer;
    // char *charp = &character;

    // // addresses
    // printf("double: 0x%x \t int: 0x%x \t char: 0x%x\n", dlbp, intp, charp);

    // // value
    // printf("value from actual vars\n");
    // printf("double: %f, int: %d, char: %c\n", dbl, integer, character);

    // printf("value from pointer vars\n");
    // printf("double: %f, int: %d, char: %c\n", *dlbp, *intp, *charp);

    // // size of
    // printf("size of\n");
    // printf("double: %d, int: %d, char %d\n", sizeof(dbl), sizeof(integer), sizeof(character));

    // int x = 7;
    // int dummy = 11;
    // int *xp;
    // xp = &x;
    // foo1(xp);
    // foo2(dummy);

    // char c = 'z';
    // char *cp = &c;
    // printf("cp is 0x%08x\n", cp);
    // printf("character at cp: %c\n", *cp);

    // cp = cp + 1;
    // printf("cp is 0x%08x\n", cp);

    // int a, b;
    // char *s1, *s2;
    // a = 3;
    // b = 4;
    // swap_nums(&a, &b);
    // printf("a is %d\n", a);
    // printf("b is %d\n", b);
    // s1 = "I should print second";
    // s2 = "I should print first";
    // swap_pointers(&s1, &s2);
    // printf("s1 is %s\n", s1);
    // printf("s2 is %s\n", s2);

    // char **s;
    // char foo[] = "Hello World";

    // *s = foo;
    // printf("s is %s\n", s);
    // s[0] = foo;
    // printf("s[0] is %s\n", s[0]);

    // printf("-%*s- is this working -%*s-\n", 5, "\n", 9, " ");
    // printf("tab: -\t-");

    // printf("positive: %d | negative: %d", 23, -23);
    // printf("hex: %#hex");

    printf("%#x\n", 16);
    printf("%#o\n", 16);
    printf("%#d\n", 16);
    printf("%#f\n", 16);
}