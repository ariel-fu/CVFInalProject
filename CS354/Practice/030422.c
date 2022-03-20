// Ariel's practice for CS354 on 03/04/2022

#include <stdio.h>
#include <string.h>

typedef struct person
{

    char *name;
    int age;

} PERSON;

void print_array(int arr[], int length)
{
    for (int i = 0; i < length; i++)
    {
        printf("arr[%d]: %i \t", i, arr[i]);
    }
}

// sort from desc to asc.
void sort_bracket(int arr[])
{
    for (int i = 0; i < 4; i++)
    {
        int curr = arr[i];
        for (int j = 0; j < 5 - i - 1; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

    print_array(arr, 5);
}

void sort(int *arr)
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = i + 1; j < 5; j++)
        {
            if (*(arr + i) > *(arr + j))
            {
                int temp = *(arr + i);
                *(arr + i) = *(arr + j);
                *(arr + j) = temp;
            }
        }
        print_array(arr, 5 - i);
        printf("\n");
    }
}

int sum(char *arr)
{
    int sum = 0;
    while (*arr)
    {
        int val = *arr - '0';
        sum += val;
        arr++;
    }
    return sum;
}

void print_reverse(char *arr)
{
    // move the pointer to the end
    int length = 0;
    while (*arr)
    {
        arr++;
        length++;
    }

    for (int i = 0; i < length; i++)
    {
        arr--;
        printf("%c ", *arr);
    }
}

void swap_people(PERSON *p1, PERSON *p2)
{
    // match the same type here
    char *p1Name = p1->name;
    // strncpy(p1Name, p1->name, 500);
    int p1Age = p1->age;

    // strncpy(p1->name, p2->name, 500);
    p1->name = p2->name;
    // p1->age = p2->age;

    // strncpy(p2->name, p1Name, 500);
    p2->name = p1Name;
    // p2->age = p1Age;
}

void print_person(PERSON p1)
{
    printf("name: %s\n", p1.name);
    printf("age: %d\n", p1.age);
}

void main()
{

    // int arrint[] = {25, 45, 89, 15, 82};
    // sort(arrint);
    // print_array(arrint, 5);

    // char arr[] = "23456";
    // int arrsum = sum(arr);
    // printf("%d\n", arrsum);

    // char reverse[] = "arielfu";
    // print_reverse(reverse);

    PERSON p1;
    char ariel[] = "ariel";
    // strncpy(p1.name, ariel, 500);
    p1.name = "ariel";
    p1.age = 18;

    PERSON p2;
    char mom[] = "mom";
    // strncpy(p2.name, mom, 500);
    p2.name = "mom";
    p2.age = 28;

    printf("before swapping\n");
    print_person(p1);
    print_person(p2);
    swap_people(&p1, &p2);
    printf("after swapping\n");
    print_person(p1);
    print_person(p2);
}