#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void p1()
{
    char *ptr = "Pointer in c", arr[15];
    arr[15] = *ptr;
    printf("%c", arr[0]);
}

void p2()
{
    int x = 150;
    int *charptr = &x;
    char *arr;
    arr = charptr;
    printf("%d ", arr[1]);

    printf("%d\n", charptr[1]);
}

void p3()
{
    int *iptr;
    int i, arr[2][2] = {10, 11, 15};
    iptr = *arr;
    printf("%d ", *(iptr + 2));
}

void p4()
{
    char *cities[] = {"Delhi", "London", "Sydney"};
    char *delhi = cities[0];
    int **i = (int **)&delhi;
    int deref = **i;
    char crep = (char)deref;
    printf("%c\n", **i);
}

void p5()
{
    char arr[10] = "Mango", *ptr;

    ptr = (&arr[1] + 1);
    printf("%s", ptr++);
}

void p6()
{
    char *ptr = "void ptr";
    void *vptr;
    vptr = &ptr;
    printf("%s\n", (char *)vptr);
    char **x = (char **)vptr;
    char x_def = *x;
    printf("%s\n", *(char *)vptr);
    printf("%s\n", (char **)vptr);
    printf("%s\n", *(char **)vptr);
}

void p7()
{
    char *ptr = NULL;
    printf("%s", ptr);
}

void p8()
{
    // int i = 5, *ptr;
    // ptr = &i;

    // void *vptr;
    // vptr = &ptr;

    // int **ptr1 = (int **)vptr;
    // int ***ptr2 = (int ***)vptr;
    // int ****ptr3 = (int ****)vptr;

    // int val1 = **ptr1;
    // int *val2 = **ptr2;
    // int **val3 = **ptr3;
    // printf("%d\n", **(int **)vptr);
    // printf("%d\n", **(int ***)vptr);
    // printf("%d\n", **(int ****)vptr);
    char i[] = "abc", *ptr;
    ptr = &i;

    void *vptr;
    vptr = &ptr;

    char **ptr1 = (char **)vptr;
    char ***ptr2 = (char ***)vptr;
    char ****ptr3 = (char ****)vptr;

    char val1 = **ptr1;
    char *val2 = **ptr2;
    char **val3 = **ptr3;
    printf("%s\n", **(char **)vptr);
    printf("%s\n", **(char ***)vptr);
    printf("%s\n", **(char ****)vptr);
}

void p9()
{
    void *ptr;
    int num = 10;
    ptr = &num;
    printf("%d ", ptr);
    printf("%d", *(int ****)ptr);
}

void p10()
{
    float me = 5.25;
    double you = 5.25;

    if (me == you)
    {
        printf("I love u");
    }
    else
    {
        printf("I hate u");
    }
}

void p11()
{
    int num = - -2;
    printf("%d", num);
}

void p12()
{
    10;
    printf("%d", 10);
}

void p13()
{
    int sizevolatile = sizeof(volatile);
    int sizeconst = sizeof(const);
    printf("%d + %d", sizevolatile, sizeconst);
}

void p14()
{
    if (sizeof(char) > -12)
    {
        printf("yest");
    }
    else
    {
        printf("no");
    }
}

void p15()
{
    char *ptr = "c%s";
    printf(ptr);
}

void p16()
{
    printf(" %%% ");
}

void p17()
{
    float x = 3.14;
    double y = 3.14;

    printf("%f %ff", x, y);
}

struct
{
    int i;
    float ft;
} decl;

void p18()
{
    decl.i = 4;
    decl.ft = 7.99;
    printf("%d, %f", decl.i, decl.ft);
}

void p19()
{
    struct zoho
    {
        int employees;
        char comp[5];

        struct founder
        {
            char ceo[10];
        } p;
    };
    struct zoho zs = {4000, "zoho", "sridhar"};
    printf("%s %d %s", zs.comp, zs.employees, zs.p.ceo);
}

void p20()
{
    struct branch
    {
        char bran[10];
        int bpin;
    };
    struct headoff
    {
        char head[10];
        int hpin;
    };
    struct headoff h = {"Chennai", 01};
    struct branch b;
    printf("HO - %s \n hpin - %d\n", h.head, h.hpin);
    printf("BO - %s \n bpin - %d", b.bran, b.bpin);
}

void p21()
{
    // char x[] = "ariel";
    // char y[] = x;

    // int a[] = {1, 2, 3};
    // int b[] = a;

    // if (x == y)
    // {
    //     printf("working");
    // }
}

void p22()
{
    struct num
    {
        int i, j, k, l;
    };

    struct num n = {1, 2, 3};
    printf("%d %d %d %d", n.i, n.j, n.k, n.l);
}

void p23()
{
    int arr[5] = {0};
    int *arrptr = &arr;
    int *ptr = &arr + 1;
    int x = *(arr + 1);
    int y = *(ptr - 1);

    printf("%d, %d", x, y);
}

void p24()
{
    int arr[5][5][5] = {0};
    int *x = &arr + 1;
    int *y = &arr;
    printf("%d", (x - y));
}

void p25()
{
    // int arr[5][5][5] = {0};
    // int *b = arr;
    // int *c = arr + 1;
    // printf("%d", c - b);
    int a[] = {1, 2, 3};
    int *b = &a + 1;
    int *c = a + 1;
    printf("%p, %p", b, c);
}

void p26()
{
    int a[] = {0, 1, 2, 3, 4};
    int *aptr = a;
    int *p[] = {a, a + 1, a + 2, a + 3, a + 4};
    int **ptr = p;
    printf("%p, %p, %p", a, p, ptr);
    ++*ptr;
    printf("%d  %d  %d", ptr - p, *ptr - a, **ptr);
}

void p27()
{
    int i = 0;
    printf("Hello");
    char s[4] = {'\b', '\r', '\t', '\n'};
    for (i = 0; i < 4; i++)
    {
        printf("%c", s[i]);
    }
    // printf("%d", 'c' + ('\0' - 32));
}

void p28()
{
    // int arr[2] = {1, 2, 3, 4, 5};
    // printf("%d", arr[3]);
}

void p29()
{
    int a, b, c;
    int arr[5] = {1, 2, 3, 25, 7};
    a = ++arr[1];
    b = arr[1]++;
    c = arr[a++];
    printf("%d--%d--%d", a, b, c);
}

void p30()
{
    static char *arr[] = {"bike", "bus", "car", "van"};
    char **ptr[] = {arr + 3, arr + 2, arr + 1, arr};
    char ***p;
    p = ptr;
    **++p;
    char **pderef = *p;
    char **pderef2 = *--p;
    printf("%s", *--*++p + 2);
}

void main()
{
    // p1();
    // p2();
    // p3();
    // p4();
    // p6();
    // p7();
    // p8();
    // p9();
    // p10();
    // p11();
    // p12();
    // p13();
    // p14();
    // p15();
    // p16();
    // p17();
    // p18();
    // decl.i = 5;
    // decl.ft = 534.21;
    // printf("%d, %f", decl.i, decl.ft);
    // p19();
    // p20();
    // p21();
    // p22();
    // p23();
    // p24();
    // p25();
    // p26();
    // p27();
    // p29();
    p30();
}