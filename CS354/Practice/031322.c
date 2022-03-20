#include <stdio.h>
#include <stdlib.h>

struct NODE
{
    int data;
    struct NODE *next;
};

void Print_List(struct NODE *head)
{
    while (head->next != NULL)
    {
        printf("%d -> ", head->data);
        head = head->next;
    }
    printf("%d\n", head->data);
    return;
}

int addBack(struct NODE **head, int val)
{
    struct NODE *curr = *head;
    struct NODE *temp = malloc(sizeof(struct NODE));
    temp->data = val;
    temp->next = NULL;

    if (curr == NULL)
    {
        *head = temp;
        return 0;
    }

    while (curr->next != NULL)
    {
        curr = curr->next;
    }

    curr->next = temp;

    return 0;
}

void conditional()
{
    if ('a')
    {
        printf("'a' returns true\n");
    }

    if ('0')
    {
        printf("'0' returns true\n");
    }

    if (!'a')
    {
        printf("!'a' returns true\n");
    }

    if (!'\0')
    {
        printf("!'\0' returns true\n");
    }

    if (1)
    {
        printf("1 returns true\n");
    }

    if (0)
    {
        printf("0 returns true\n");
    }

    if (!0)
    {
        printf("!0 returns true\n");
    }
}

void main()
{
    // struct NODE *head = NULL;
    // int retVal = addBack(&head, 1);
    // printf("return val: %d = 0\n", retVal);
    // Print_List(head);
    // retVal = addBack(&head, 2);
    // printf("return val: %d = 0\n", retVal);
    // Print_List(head);
    // retVal = addBack(&head, 3);
    // printf("return val: %d = 0\n", retVal);
    // Print_List(head);

    conditional();
}