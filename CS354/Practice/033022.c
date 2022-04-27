#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void p1()
{
    unsigned int x = 1;
    unsigned int y = 2;

    if (((int)x - (int)y) > 0)
    {
        printf("greater");
    }
    else
    {
        printf("not greater");
    }
}

void p2()
{
    struct temp
    {
        int a;
        int b;
        char c;
        char c2;
        short s;
    };
    struct temp2
    {
        char c;
        char c2;
        int a;
        int b;
        short s;
    };

    struct temp3
    {
        char c;
        int a;
        char c2;
        short s;
        int b;
    };
    struct temp4
    {
        char c;
        int a;
        char c2;
        int b;
        short s;
    };

    printf("%d = 12, ", sizeof(struct temp));
    printf("%d = 16, ", sizeof(struct temp2));
    printf("%d = 16, ", sizeof(struct temp3));
    printf("%d = 20, ", sizeof(struct temp4));
}

char *p3(char *s)
{

    char *d = malloc(strlen(s));

    if (d == NULL)
    {
        printf("no malloc");
        return NULL;
    }

    strcpy(d, s);
    printf("%s ", d);
    return d;
}

int *func()
{
    int res = 42;
    return &res;
}

void p4()
{
    int *x = func();
}

void p5()
{
    int i = 10;
    int arr[10];
    for (; i >= 0; i--)
    {
        arr[i] = i;
    }

    for (int i = 0; i < 10; i++)
    {
        printf("%d ", arr[i]);
    }
}

void p6()
{
    int *p = malloc(sizeof(int));
    int *q = p;
    p[0] = 3;
    free(p);
    q[0] = 3;
}

void p7()
{
    struct user
    {
        char name[100];
    };

    // struct user *puser = malloc(sizeof(puser));
    // puser->name = "ariel";
}

void p8()
{
    int *ptr;
    int n = 50;
    printf("%d, %d, %d\n", ptr, *ptr == n, ptr && *ptr == n);

    ptr = &n;
    printf("%d, %d, %d ", ptr, *ptr == n, ptr && *ptr == n);
}

void p9()
{
    int *p = malloc(sizeof(int) * 5);
    for (int i = 0; i < 5; ++i)
    {
        printf("%d, %d\n", i, p[i]);
    }

    int a[] = {1, 2, 3, 4, 5};
    for (int i = 0; i < 5; ++i)
    {
        printf("%d, %d\n", i, a[i]);
    }
}

void p10()
{
    int x = 10;
    printf("%d ", !x);

    x = 0;
    printf("%d ", !x);

    char c = 'a';
    printf("%d ", !c);

    c = '\0';
    printf("%d ", !c);
}

void p11()
{
    int a = 97;
    int *x = &a;
    char *p = (char *)x;
    printf("%x %x %x %x, %c, %d", *(p + 3), *(p + 2), *(p + 1), *p, *p, *p);
}

/* function to show bytes in memory, from location start to start+n*/
void show_mem_rep(char *start, int n)
{
    int i;
    for (i = 0; i < n; i++)
        printf(" %.2x", start[i]);
    printf("\n");
}

/*Main function to call above function for 0x01234567*/
void p12()
{
    int i = 0x1234567;
    show_mem_rep((char *)&i, sizeof(i));
    getchar();
}

void p13()
{
    int i, numbers[1];
    numbers[0] = 9;
    free(numbers);
    printf("\nStored integers are ");
    printf("\nnumbers[%d] = %d ", 0, numbers[0]);
}

void p14()
{
    int *num = (int *)malloc(sizeof(int) * 4);
    num[0] = 9;
    free(num);
    printf("%d", num[0]);
}

void p15()
{
    int *ptr = (int *)malloc(sizeof(int));
    *ptr = 10;
    free(ptr);
    ptr = 9;
    ptr = 'a';
}
char *fun()
{
    char d[30];
    // char *d = malloc(30 * sizeof(char));
    strcpy(d, "memory");
    return d;
}
void p16()
{
    char *ptr = fun();
    printf("%s", ptr);
}

void main()
{
    // p1();
    // p2();
    // char *s = "abc";
    // char *x = p3(s);
    // printf("%s", x);
    // p4();
    // p5();
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
    p16();
}