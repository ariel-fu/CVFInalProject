#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void p1()
{
    char *s = malloc(5);
    char *t = malloc(5);
    strcpy(s, "xyz");
    strcpy(t, "abc");
    int x = (*s = *t);
    printf("x: %c ", x);
    while (x)
    {
        printf("x: %c, t: %c ", x, *t);
        s++;
        t++;
        x = (*s = *t);
    }
}

void p2()
{
    int arr[] = {10, 20, 30, 40, 05, 60};
    int *ptr1 = arr;
    int *ptr2 = arr + 5;
    printf("num: %d\n", (ptr2 - ptr1));
    printf("num: %d\n", ((char *)ptr2 - (char *)ptr1));
}

void p3()
{
    char arr[] = "MachineOrganization";

    int *ptr = (int *)&arr[1];
    printf("value %c\n", *((char *)ptr));
    ptr++;
    printf("value %c\n", *((char *)ptr));
}

struct node
{
    struct node *next;
    int data;
};

void print(struct node *head)
{
    while (head->next != NULL)
    {
        printf("%d -> ", head->data);
        head = head->next;
    }
    printf("%d\n", head->data);
}

void ultimate(struct node **head_ref)
{
    if (*head_ref == NULL || (*head_ref)->next == NULL)
        return;
    struct node *secLast = NULL;
    struct node *last = *head_ref;
    while (last->next != NULL)
    {
        secLast = last;
        last = last->next;
    }
    secLast->next = NULL;
    last->next = *head_ref;
    *head_ref = last;
}
void p4()
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
    print(head_ref);

    ultimate(&head_ref);

    print(head_ref);
}

int dosomething(struct node *head, int element)
{
    struct node *temp = head;
    int pos = 0;
    if (head == NULL)
    {
        return -1;
    }
    else
    {
        while (temp != NULL)
        {
            ++pos;
            if (temp->data == element)
            {
                return pos;
            }
            temp = temp->next;
        }
        return -1;
    }
}

void p5()
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
    int n = dosomething(head_ref, 30);
    printf("n: %d", n);
}

void p6()
{
    int arr[] = {1, 2, 3};
    printf("%d, %d\n", arr[2], *(arr + 2));

    printf("%p, %p\n", &(arr[2]), arr + 2);
}

void p7()
{
    struct node
    {
        int data;
    };

    struct node *n1 = malloc(sizeof(struct node));
    n1->data = 10;
    printf("%d ", n1->data);
    (*n1).data = 30;
    printf("%d ", (*n1).data);
}

void p8()
{
    /*causes a seg fault for accessing out of bounds data*/
    // int array[20];
    // array[100] = -1;

    /*causes a seg fault for trying to write to read-only string*/
    // char *str = "foo";
    // *str = 'a';

    /*does not cause a seg fault because the chars are copied over into the heap, rather than in the text/read-only space of memory */
    // char *str = malloc(3);
    // strcpy(str, "abc");
    // *str = 'x';

    // int arr[6];
    // int index = 3;
    // arr[index - 4] = index;

    // int q[5] = {0, 2, 4, 6, 8};

    // int *p = &(q[4]);

    // int y = (char *)(p) - (char *)(q);

    // printf("%d", y);
}

void p9()
{
    int x[] = {10, 20, 30, 40, 50};
    printf("0x%p\n", &x);

    int *ptr = &x;

    int *ptr1 = ptr + 1;

    int *ptr2 = ptr + sizeof(int);

    printf("0x%p, 0x%p", ptr1, ptr2);
}

void p10()
{
    // typedef struct node
    // {
    //     int val;
    //     struct node *next;
    // } Node;

    // Node *ptr;
    // ptr = NULL;
    // ptr->intval = 300;
}

void doubleit(int y)
{
    y = y * 2;
}
void p11()
{
    int x;
    x = 20;
    doubleit(x);
    printf("%d", x);
}

void print_nodes(struct node *head)
{
    while (head != NULL)
    {
        printf("%d\n", head->data);
        head = head->next;
    }
}
void p12()
{
    struct node *head_ref = NULL;
    print_nodes(head_ref);
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
    head_ref = one;

    print_nodes(head_ref);
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
    // p11();
    p12();
}