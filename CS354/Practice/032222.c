#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct node
{
    int a, b, c;
};

void q1(char **ptr)
{
    char *ptr1;
    ptr1 = (ptr += sizeof(int))[-2];
    printf("%s\n", ptr1);
}

void q2()
{
    struct node num = {3, 5, 6};
    struct node *ptr = &num;
    int *casted_pointer = (int *)ptr;
    casted_pointer += 1;
    casted_pointer += 1;
    int casted_value = *casted_pointer;
    // printf("%d\n", *((int *)ptr + 1 + (3 - 2)));
    printf("%d\n", casted_value);
}

void q3()
{
    char *ptr = "Pointer in c", arr[15];
    arr[15] = *ptr;
    printf("%c", arr[0]);
}

void q4()
{
    char *ptr = "abc";
    char dest[] = "xyz";
    strcpy(dest, ptr);
    printf("copy %s\n", dest);
}

void main()
{
    char *arr[] = {"ant", "bat", "cat", "dog", "egg", "fly"};
    q1(arr);
    // printf("1st pointer: %p\n", arr[0]);
    // printf("2nd pointer: %p\n", arr[1]);
    // printf("3rd pointer: %p\n", arr[2]);
    // printf("4th pointer: %p\n", arr[3]);
    // printf("5th pointer: %p\n", arr[4]);
    // printf("6th pointer: %p\n", arr[5]);
    q2();
    q3();
    q4();
}