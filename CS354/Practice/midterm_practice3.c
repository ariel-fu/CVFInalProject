#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void q1()
{
    char a[99] = "CS354!!!";
    char b[99] = "rocks!";
    char *pa = a, *pb = b;
    while (*pb++ = *pa++)
        ;
    printf("%s\n", b);
}

void q2()
{
    int a[] = {1, 2, 3};
    printf("%p\n", a);
    int *iptr = a;
    printf("%p\n", iptr + 1);
}

void q3()
{
    int a[] = {1, 2, 3, 4, 5, 6};
    int *iptr = a;
    iptr += 1;
    printf("%d\n", iptr[1]);
}

// void q4()
// {
//     int a[] = {1, 3, 5};
//     int *iptr = a;
//     a++;
//     printf("%d\n", *a);
// }
void modify(int *b)
{
    ++*b;
    ++b;
}
void q5()
{
    int a = 2;
    int *b = &a;
    // for now assume that
    // printf("%p\n", b); prints out 0x200
    modify(b);
    printf("%d\n", a);
}

void q8()
{
    int *a = 0;
    char *b = 0x0;
    int **c = 0x0;

    a += 2;
    b += 1;
    c += 1;

    int a_int = (int)a;
    int b_int = (int)b;
    int c_int = (int)c;

    char a_char = (char)a;
    char b_char = (char)b;
    char c_char = (char)c;

    printf("%d,%d,%d\n", a, b, c);
}

void q9(int *ptr)
{
    int a = 10;
    // ptr = &a;
    *ptr = a;
}

void q10()
{
    char *arr[] = {"ant", "bat", "cat", "dog", "egg", "fly"};

    char **ptr = arr;
    char **ptr1;
    ptr1 = (ptr += sizeof(int));
    char *ptr2 = ptr1[-2];
    printf("%s\n", ptr2);
}

void main()
{
    // q1();
    // q2();
    // q3();
    // // q4();
    // q5();
    // q8();
    // int a = 2;
    // q9(&a);
    // printf("%d\n", a);
    q10();
}