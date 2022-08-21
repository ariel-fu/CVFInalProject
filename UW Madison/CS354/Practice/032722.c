#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void p1()
{
    char *ptr = "hello";
    char a[22];

    strcpy(a, "world");

    printf("%s %s\n", ptr, a);
}

unsigned long int (*function())[5]
{
    static unsigned long int arr[5] = {2, 3, 5, 7, 11};
    printf("%d ", *arr);
    return &arr;
}
void p2()
{
    unsigned long int(*ptr)[5];
    ptr = function();

    unsigned long int *subptr = *ptr;
    printf("0x%p %d", subptr, *(subptr + 4));
}

void p3()
{
    int *ptr;
    printf("%d", sizeof(ptr));
}

void p4()
{
    int *ptr;
    *ptr = 5;
}

void p5()
{
    const int a = 5;
    int *ptr;
    ptr = &a;
    *ptr = 10;
    printf("%d\n", a);
}

void p6()
{
    struct node
    {
        int a, b, c;
    };

    struct node num = {3, 5, 6};
    struct node *ptr = &num;
    int first = (*ptr).a;

    printf("%p | %d\n", ptr, *(ptr));
    printf("%p | %d\n", (int *)ptr, *((int *)ptr));

    printf("%p | %d\n", (int *)ptr + 1, *((int *)ptr + 1));
    printf("%p | %d\n", (ptr + 1), *(ptr + 1));
}

void p7()
{
    char *ptr = "Pointer in c", arr[15];
    arr[0] = *ptr;
    printf("%s", ptr);
    printf("%c", arr[0]);

    // char *ptr, arr[15] = "Pointer in c";
    // ptr = arr;
    // printf("%c", *ptr);
}

void p8()
{
    char *x = "Delhi";

    int *y = (int *)x;
    x += 1;
    y += 1;
    printf("%s\n", (char *)x);
    printf("%s\n", (char *)y);
    // int x[2][2] = {{1, 2}, {3, 4}};
    // int *i1 = &x;
    // int *i2 = &x[0];
    // int *i3 = &x[0][1];
    // int *i4 = &x[1][0];
    // int *i5 = &x[1];

    // i2 += 1;
    // char *cities[] = {"Delhi", "London", "Sydney"};
    // int c = *cities;
    // int **c0 = &cities; //pointer to pointer
    // int *c00 = &cities; //pointer to cities
    // int *c10 = c00 + 1; //shift by 1 pointer (4)
    // //same as int c10 =  c00+4
    // int c000 = c00[0]; //get int value @ c00[0]
    // //get int value @ c10[0], since *c10 = c10[0]
    // int c100 = *c10;
    // //increment c000 by 1
    // int cNum = c000+1;
    // char *c0000 = (char*)c000; // cast the value in c000 to a pointer of type char
    // char *c0001 = (char*)(cNum); //
    // char *c0004 = (char*)(c000+6);
    // char *c1000 = (char*)c100;
    // char **c1 = &cities;
    // int **c2 = &cities[1];
    // int **c3 = &cities[2];
}

void p9()
{
    char *cities[] = {"UAE", "Spain", "America"};
    int *i = &cities;
    int *j = i + 1;
    int *k = i + 2;
    int j1 = *j;
    char *j2 = (char *)j1;
    char *j3 = (char *)(*j);
    int k1 = *k;
    char *k2 = (char *)k1;
    printf("%p | %s\n", i, *i);
    printf("%p | %s\n", j, (char *)(*j));
    printf("%p | %s\n", k, (char *)(*k));
}

void p10()
{
    int x[] = {5, 7, 8};
    int *ptr = (&x);

    // printf("%p %d \n", &x[0], x[0]);
    // printf("%p %d \n", &x[1], x[1]);
    // printf("%p %d \n", &x[2], x[2]);
    // printf("%p %d \n", &x[3], x[3]);
    // ++*ptr;
    printf("%d ", ++*ptr);

    printf("%d ", *ptr++);
}

void p11()
{
    char *ptr = "void pointer";
    void *vptr;
    vptr = &ptr;
    char **vptr1 = (char **)vptr;
    int vptr21 = *((int *)vptr);
    char *vptr22 = (char *)vptr21;
    // char *vptr2 = (char *)vptr;
    // int vptr21 = vptr2[0];
    // char *vptr22 = (char *)vptr21;

    printf("%s", vptr22);
}

void main()
{
    // p1();
    // p2();
    // p3();
    // p5();
    // p6();
    // p7();
    // p8();
    // p9();
    // p10();
    p11();
}