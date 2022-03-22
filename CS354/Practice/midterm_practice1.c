#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void q1()
{
    char *pointerArray[] = {"Pointers", "Are", "Fun", "!"};
    char **pointerToPointerArray[] = {pointerArray, pointerArray + 3, pointerArray + 1, pointerArray + 2};
    char ***pointerToPointerToPointerArray = pointerToPointerArray;

    printf("%s\n", **++pointerToPointerToPointerArray);
    printf("%s\n", *pointerToPointerToPointerArray[-1] + 1);
}

void q2()
{
    char *yearsStrArray[] = {"2018", "2019", "2020", "2021", "2022", "2023"};
    char **yearsPtr = yearsStrArray;

    printf("Best Year Ever = %s\n", (yearsPtr += sizeof(int))[-1]);
}

void q3()
{

    int *arr1[3][5];

    int *arr2 = malloc(sizeof(3 * 5 * sizeof(int)));

    printf("%ld,%ld\n", sizeof(arr1), sizeof(arr2));
}

void q4()
{
    char s[] = "cs354";
    printf("%d, %d, %d\n", strlen(s), sizeof(s), sizeof(*s));
}

int q5(char *s)
{
    char *cp = s;
    while (*cp++)
        ;
    return cp - s;
}

void q6()
{
    char s[] = "cs354";
    char t[3];
    strcpy(t, s);

    strlen(t);

    printf("");
}

void q7()
{
    int regina_george[42] = {0};
    int n = sizeof(regina_george) / sizeof(regina_george[0]);

    for (int i = 0; i < n; i++)
    {
        regina_george[i] = n + 42;
    }

    printf("%d\n", regina_george[7]);
}

void q8()
{
    int juandissimo[3][3] = {0};
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            **juandissimo = i + j;
        }
    }

    printf("%d\n", **juandissimo);
}

void q9()
{
    int rows = 3;
    int cols = 3;
    int **matrix[3][3];
    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            *(*(matrix + i) + j) = i + j;
        }
    }

    printf("%d\n", matrix[2][1]);
}

void q10()
{
    int rows = 3;
    int cols = 3;
    int **matrix[3][3];
    int val = 0;
    for (int j = 0; j < cols; j++)
    {
        for (int i = 0; i < rows; i++)
        {
            if ((i + j) % 2)
            {
                *(*(matrix + j) + i) = val;
            }
            val++;
        }
    }

    printf("%d\n", matrix[2][1]);
}
void main()
{
    q1();
    q2();
    q3();
    q4();
    int ret = q5("abcdefg");
    q6();
    q7();
    q8();
    q9();
    q10();
}