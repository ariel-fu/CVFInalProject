#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void q1()
{
    int a[2][3] = {{1, 2, 3}, {4, 5, 6}};
    int *b = &a[1][1];

    printf("%p\n", b);
}

void q2()
{
    int a[2][3] = {{1, 2, 3}, {4, 5, 6}};
    int *b = a[1];

    printf("%p\n", b);
}

void q5()
{
    int a[2][3] = {{1, 2, 3}, {4, 5, 6}};
    printf("a mem location: %p\n", a);
    char *b = &a;
    char *c = b + sizeof(int);
    printf("%d\n", *(b + sizeof(int)));
}

void main()
{
    q1();
    q5();
}