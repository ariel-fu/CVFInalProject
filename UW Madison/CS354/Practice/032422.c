#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void equality_as_termination()
{
    char arr1[10] = "abc";
    char arr2[10] = "xyz";
    char *ptr1 = &arr1;
    char *ptr2 = &arr2;

    // char *ptr1 = malloc(10 * sizeof(char));
    // char *ptr2 = malloc(10 * sizeof(char));
    // ptr1 = "abc";
    // ptr2 = "xyz";
    // printf("%c", *ptr1);
    // printf("%c\n", *ptr2);

    while ((*ptr1 = *ptr2))
    {
        printf("%s | %s\n", ptr1, ptr2);
        ptr1++;
        ptr2++;
        printf("%d | %c\n", (*ptr1 = *ptr2), (*ptr1 = *ptr2));
    }

    // char arr1[10];
    // char arr2[10];
    // int i = 0;
    // while (arr1[i] = arr2[i])
    // {
    //     printf("%s | %s\n", arr1, arr2);
    //     i++;
    // }
}

void pointer_vs_array()
{
    char arr[10];
    char *pointer = malloc(10 * sizeof(char));
    char *pointer2 = malloc(10 * sizeof(char));
    strcpy(pointer, "abc");
    strcpy(pointer2, "xyz");
    printf("ptr1: %s | ptr2: %s\n", pointer, pointer2);
    char *temp = malloc(10 * sizeof(char));
    temp = pointer;
    pointer = pointer2;
    pointer2 = temp;

    printf("ptr1: %s | ptr2: %s\n", pointer, pointer2);

    // printf("arr: %p | ptr: %p", arr, pointer);
}

void q1()
{
    int a = 130;
    char *ptr;
    int *x = &a;
    ptr = (char *)x;
    printf("%d ", *ptr);
}

void q2()
{
    int i = 3;
    int *j;
    int **k;

    j = &i;
    k = &j;
    (*k)++;
    printf("%d \n", **k);
}

unsigned long int (*function())[5]
{
    static unsigned long int arr[5] = {2, 3, 5, 7, 11};
    printf("%d", *arr);
    return &arr;
}
void q3()
{
    unsigned long int(*ptr)[5];
    ptr = function();
    long unsigned int *x = *ptr + 4;
    long unsigned int *x_dif = *(ptr + 4);
    long unsigned int y = *x;
    printf(" %d\n", y);
}

void q4()
{
    char *charptr = 'a';
    int *ptr = 2;
}

void q5()
{
    int a = 36;
    int *ptr;
    ptr = &a;
    printf("%u %u", *&ptr, &*ptr);
}

void main()
{
    // pointer_vs_array();
    // equality_as_termination();
    q1();
    q2();
    q3();
    q4();
    q5();
}