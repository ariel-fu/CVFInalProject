#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Car
{
    char *make;
    char *model;
    int year;
    unsigned int miles;
} car_t;

int drive_car(car_t *car, unsigned int miles)
{
    int new_miles = car->miles + miles;
    car->miles = new_miles;
    return new_miles;
}

void p1()
{
    car_t jasons;
    jasons.make = "Toyota";
    jasons.model = "Camry";
    jasons.year = 1996;
    jasons.miles = 181020;
    int new_mileage = drive_car(&jasons, 80);

    if (new_mileage != jasons.miles)
    {
        printf("not equal ");
    }
    else
    {
        printf("equal");
    }
}

void p2()
{
    int i_array[10] = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    int diff_array[9] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    char *str = "Hello, all";
    int *i_ptr1, *i_ptr2;
    char *cp;
    printf("%p\n", &i_array);
    printf("%p\n", &i_array[5]);

    cp = str;
    int i = 0;
    while (*cp != '\0')
    {
        cp++;
        i++;
    }
    printf("%d\n", i);
}

void p3()
{
    int i_array[10] = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    int diff_array[9] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    char *str = "Hello, all";
    int *i_ptr1, *i_ptr2;
    char *cp;

    i_ptr1 = i_array;
    i_ptr2 = &i_array[1];
    int i = 0;
    int *total_size = i_array + (sizeof(i_array) / sizeof(i_array[0]));
    while (i_ptr2 < total_size)
    {
        int diff = *i_ptr2 - *i_ptr1;
        diff_array[i] = diff;
        i_ptr1++;
        i_ptr2++;
        i++;
    }

    for (int i = 0; i < 9; i++)
    {
        printf("%d ", diff_array[i]);
    }
}

void p4()
{
    int a = 10;
    int *p = &a;
    int arr[10] = {2, 3, 4, 5};
    int *q = arr;

    printf("arr: %p\n", &arr);

    printf("*p: %d\n", *p);

    printf("q[0]: %d\n", q[0]);

    printf("&arr[2]: %p\n", &arr[2]);

    printf("q+3: %p\n", q + 3);

    printf("*(q+1): %d\n", *(q + 1));
}

void p5()
{
    int sum = 0;
    for (int i = 1; i <= 10; i++)
    {
        if (i % 2 == 1)
        {
            continue;
        }
        sum += i;
    }

    printf("%d == 30", sum);
}

struct node
{
    int data;
    struct node *next;
};

void usefulFunc1(struct node *curr)
{
    while (curr != NULL)
    {
        printf("%d ", curr->data);
        curr = curr->next;
    }
    printf("\n");
}

void usefulFunc2(struct node **head, int val)
{
    struct node *prev = NULL;
    struct node *curr;
    if (head == NULL)
        return;
    curr = *head;
    while (curr != NULL)
    {
        if (curr->data == val)
        {
            if (prev != NULL)
            {
                prev->next = curr->next;
                free(curr);
                curr = prev;
            }
            else
            {
                *head = curr->next;
                free(curr);
                curr = *head;
                continue;
            }
        }
        prev = curr;
        curr = curr->next;
    }
}

void p6()
{
    struct node *five = malloc(sizeof(struct node));
    five->data = 50;
    five->next = NULL;
    struct node *four = malloc(sizeof(struct node));
    four->data = 40;
    four->next = five;
    struct node *three = malloc(sizeof(struct node));
    three->data = 30;
    three->next = four;
    struct node *two = malloc(sizeof(struct node));
    two->data = 20;
    two->next = three;
    struct node *one = malloc(sizeof(struct node));
    one->data = 10;
    one->next = two;
    struct node *head_ref = one;

    usefulFunc1(head_ref);
    usefulFunc2(&head_ref, 30);
    usefulFunc1(head_ref);
}

void p7()
{
    typedef struct test
    {
        int data;
    } test;
    typedef test temp;
    struct test test1 = {1};
    test test2 = {2};
    temp test3 = {3};
    temp test4 = {1.1};

    printf("%d, %d, %d, %d", test1.data, test2.data, test3.data, test4.data);
}

#define ARSIZE 5
void p8()
{
    int arrA[ARSIZE];
    int *arrB[ARSIZE];
    int i;

    for (i = 0; i < ARSIZE; i++)
    {
        *(arrB + i) = arrA + i;
    }
    for (i = 0; i < ARSIZE; i++)
    {
        printf("%d ", *(arrB + i));
    }
    for (i = 0; i < ARSIZE; i++)
    {
        *(arrB[i]) = i * 2;
    }
    for (i = 0; i < ARSIZE; i++)
    {
        printf("%d ", *(arrB[i]));
    }
}

void p9()
{
    int *p = malloc(sizeof(int));
    *p = 42;
    p = malloc(sizeof(int));
    free(p);
}
void pop(int **a)
{
    int *parray = malloc(2 * sizeof(int));
    parray[0] = 37;
    parray[1] = 73;
    *a = parray;
}

void p10()
{
    int *a = NULL;
    pop(&a);

    printf("[%d], [%d]", a[0], a[1]);
}

void p11()
{
    int number = 288;
    char *ptr;
    ptr = (char *)&number;

    printf("%x", *ptr);
}

void p12()
{
    char string[] = "BADGERS";
    char *ptr = string;
    *ptr = *ptr + 2;
    ptr = ptr + 2;
    printf("%c", *ptr);
    ptr--;
    printf("%c", *ptr);
    ptr = string;
    printf("%c", *ptr);
}

void myst(struct node *head)
{
    struct node *temp = head;
    struct node *prev;
    if (temp == NULL)
    {
        printf("EMPTY\n");
    }
    else
    {
        prev = temp;
        while (temp != NULL)
        {
            temp = temp->next;
            free(prev);
            prev = temp;
        }
    }
}
void p13()
{
    struct node *five = malloc(sizeof(struct node));
    five->data = 50;
    five->next = NULL;
    struct node *four = malloc(sizeof(struct node));
    four->data = 40;
    four->next = five;
    struct node *three = malloc(sizeof(struct node));
    three->data = 30;
    three->next = four;
    struct node *two = malloc(sizeof(struct node));
    two->data = 20;
    two->next = three;
    struct node *one = malloc(sizeof(struct node));
    one->data = 10;
    one->next = two;
    struct node *head_ref = one;
    usefulFunc1(head_ref);
    myst(head_ref);
    usefulFunc1(head_ref);
}

void p14()
{
    struct foo
    {
        int d1;
        char c1;
        int d2;
    };
    printf("%d ", (int)sizeof(struct foo));

    struct foo1
    {
        int d1;
        char c1;
        int d2;
        char c2;
        short s;
    };
    printf("%d ", (int)sizeof(struct foo1));

    struct foo2
    {
        int d1;
        int d2;
        char c1;
        char c2;
        short s;
    };
    printf("%d ", (int)sizeof(struct foo2));

    struct foo3
    {
        char c1;
        int d1;
        short s;
        int d2;
        char c2;
    };
    printf("%d ", (int)sizeof(struct foo3));
}

void p15()
{
    int a[10];
    int *p = &a[7];
    printf("%d | ", (p - a));

    struct foo1
    {
        int x;
        int y;
        int z;
    };
    struct foo1 a1;
    int *p1 = &a1.y;
    printf("%p, %p | ", p1, &a1);

    struct foo2
    {
        int x[4];
        int y[4];
    };
    struct foo2 a2;
    int *p2 = &a2.y[2];
    printf("%p, %p | ", p2, &a2);

    struct foo2 a3[10];
    int *p3 = &a3[7].y[2];
    printf("%p, %p ", p3, &a3);
}

void p16()
{
    char a[9] = "CS354~~~~";
    char b[7] = "rocks-";
    // char *pa = a;
    // char *pb = b;
    // while (*pa++ = *pb++)
    //     ;
    // printf("%s\n", a);
    char *pa = a;
    char *pb = b;
    while (*pa++ = *pb++)
        ;
    printf("%s\n", a);
}

void swap(float *a, float *b)
{
    float temp = *a;
    *a = *b;
    *b = temp;
}

void p17()
{
    float a = 1.0;
    float b = 4.0;
    printf("%f, %f\n", a, b);
    swap(&a, &b);
    printf("%f, %f\n", a, b);
}

void p18()
{
    int i_array[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    char c_array[10] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    int *ip;

    printf("%d, %d\n", i_array[5], *(i_array + 5));
    *ip = i_array;
    printf("%d", *ip);
}

int p19(char *s)
{
    int i = 0;
    if (s != NULL)
    {
        while (s[i] != '\0')
        {
            i++;
        }
    }
    return i;
}

void p20()
{
    char a[] = "Ariel";
    char *ptr = a;
    int i = p19(ptr);
    char *s = ptr;
    char *e = s + (i - 1);
    printf("%s, %s\n", a, ptr);
    while (s < e)
    {
        char t = *s;
        *s = *e;
        *e = t;
        s++;
        e--;
    }

    printf("%s, %s", a, ptr);
}

void p21()
{
    char arr[] = "MachineOrganization";
    int *ptr = (int *)(&arr[1]);
    printf("%c ", *(char *)ptr);
    ptr++;
    printf("%c ", *(char *)ptr);
}

void p22()
{
    char *argv[] = {"ewljfke", "def", "ghi"};
    int intval = atoi(argv[0]);

    printf("%d", intval);
}

void p23()
{
    int a[5];
    int *b[5];
    int i = 0;
    for (i = 0; i < 5; i++)
    {
        *(b + i) = a + i;
    }
    for (i = 0; i < 5; i++)
    {
        printf("%p ", *(b + i));
    }
    printf("\n");
    for (i = 0; i < 5; i++)
    {
        *(b[i]) = i * 2;
    }
    for (i = 0; i < 5; i++)
    {
        printf("%p ", (a + i));
        printf("%d ", *(a + i));
    }
}

void p24()
{
    int a[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    printf("%d, %d \n", a[5], *(a + 5));
    printf("%p, %p \n", &a[5], (a + 5));

    int array[20];
    array[100] = -1;

    int arr[6];
    int index = 3;
    arr[index - 4] = index;

    typedef struct n
    {
        int value;
        struct n *next;
    } NODE;

    NODE *ptr;
    ptr = NULL;
    ptr->value = 300;
}

void p25()
{
    int counts[240];
    printf("%p %p", counts, &counts[65]);
}

void main()
{
    // p1();
    // p2();
    // p3();
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
    // p16();
    // p17();
    // p18();
    // p20();
    // p21();
    // p22();
    // p23();
    // p24();
    p25();
}