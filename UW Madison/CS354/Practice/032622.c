#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void p1()
{
    int *ptr[10];
    int a = 10, b = 20, c = 30, d = 40;
    ptr[0] = &a;
    ptr[1] = &b;
    ptr[2] = &c;
    ptr[3] = &d;

    for (int i = 0; i < 4; i++)
    {
        printf("%p | %p | %d\n", &ptr[i], ptr[i], *ptr[i]);
    }
}

void p2()
{
    // int i = 4;
    // while (i == 4 --)
    // {
    //     printf("Ffow");
    // }
}

void p3()
{
    float ft = 7.5;
    while (ft)
    {
        printf("Loop");
        ft = ft - .5;
        if (ft == 5.0f)
            break;
    }
}

void p4()
{
    int i = 1;
    while (i)
    {
        printf("Hai Loop");
        if (i == 3)
            break;
        i++;
    }
}

void p5()
{
    // int i = 0;
    // while (i + 1)
    //     while (i << 2)
    //         while (i4)
    //         {
    //             printf("Loop ");
    //             if (i == 3)
    //                 break;
    //         }
}

void p6()
{
    int i = 1;
    do
    {
        while (i)
            i--;
        for (i++; 0; i++)
            ;
        break;
    } while (1);
    printf("%d", i);
}

void p7()
{
    int a[] = {1, 2, 3, 4, 5};
    int sum = 0;
    for (int i = 0; i < 5; i++)
    {
        if (i % 2 == 0)
        {
            sum += *(a + i);
        }
        else
        {
            sum -= *(a + i);
        }
    }
    printf("%d", sum);
}

void p8()
{
    int a = 3;
    int res = a++ + ++a + a++ + ++a;
    printf("%d", res);
}

void p9()
{
    int i = 8;
    for (int i = 0; i <= 3; ++i)
        printf("i: %d ", i);

    printf("%d", i);
}

void p10()
{
    char a = 'a';
    char astr[] = "a";
    printf("%d", a == astr);
}

void p11()
{
    char input_buffer[31] = "42 is Mike's favorite number!";
    char output_buffer[256] = {53, '3', 0, 0, 0, 0};
    for (int i = 0; i < 31; i++)
    {
        char curr = output_buffer[i];
        printf("%c = %d\n", output_buffer[i], !output_buffer[i]);
        if (!output_buffer[i])
            output_buffer[i] = input_buffer[i];
    }

    printf("output buffer: %s\n", output_buffer);
}

void p12()
{
    int app[37] = {0};
    int n = sizeof(app) / sizeof(app[0]);

    for (int i = 0; i < n; i++)
    {
        app[i] = n;
    }
}

void p13(int myarray[])
{
    int n = sizeof(myarray) / sizeof(myarray[0]);
    for (int i = 0; i < n; i++)
    {
        myarray[i] = n + 42;
    }
}

void test(int *p)
{
    *p = 4;
}
void p14()
{
    int c = 8;
    int *b = &c;
    test(b);
    printf("%d %d", *b, c);
}

void swap(char **str1, char **str2)
{
    char *temp = *str1;
    *str1 = *str2;
    *str2 = temp;
}
void p15()
{
    // char *str1 = malloc(sizeof(char) * 10);
    // char *str2 = malloc(sizeof(char) * 10);
    char s1[] = "abc";
    char s2[] = "xyz";
    char *str1 = s1;
    char *str2 = s2;

    // strcpy(str1, "abc");
    // strcpy(str2, "xyz");

    printf("str1: %s\n", str1);
    printf("str2: %s\n", str2);

    // char *temp = str1;
    // str1 = str2;
    // str2 = temp;

    swap(&str1, &str2);

    printf("str1: %s\n", str1);
    printf("str2: %s\n", str2);
}

void p16()
{
    /* String ptr on heap */
    // char *str1 = malloc(sizeof(char) * 10);
    // char *str2 = malloc(sizeof(char) * 10);
    // strcpy(str1, "abc");
    // strcpy(str2, "xyz");

    /* String ptr on stack */
    char s1[] = "abc";
    char s2[] = "xyz";
    char *str1 = s1;
    char *str2 = s2;
}

void main()
{
    // p1();
    // p3();
    // p4();
    // p6();
    // p7();
    // p8();
    // p9();
    // p10();
    // p11();
    // p12();
    // int regina_george[42] = {0};
    // p13(regina_george);
    // p14();
    p15();
}