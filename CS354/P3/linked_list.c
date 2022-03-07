// do not include other libraries
#include <stdio.h>
#include <stdlib.h>

// ***************************************
// *** struct definitions ****************
// *** do not change these ***************
// ***************************************
typedef struct NODE
{
    int data;
    struct NODE *next;
} NODE; // nodes of the linked list
typedef struct LINKED_LIST
{
    struct NODE *head;
} LINKED_LIST; // struct to act as the head of the list

// ***************************************
// *** provided functions  ****************
// ***************************************

// this function returns a LINKED_LIST struct to
// act as the head of the linked list.
// do not change this function
LINKED_LIST Create_List(void)
{
    LINKED_LIST list = {NULL};
    return list;
}

// call this function to verify malloc worked when you create new nodes
void Verify_Malloc(NODE *node)
{
    if (node == NULL)
    {
        printf("Malloc Failed\n");
        exit(1);
    }
    return;
}

// do not change this function
// this function prints out all of the nodes in the linked list
void Print_List(FILE *out, LINKED_LIST list)
{
    if (list.head == NULL)
    {
        printf("\n");
        return;
    } // empty list
    NODE *current = list.head;
    while (current->next != NULL)
    {
        fprintf(out, "%d -> ", current->data);
        current = current->next;
    }
    fprintf(out, "%d\n", current->data);
    return;
}

// ******************************************************
// *** Complete the following functions  ****************
// ******************************************************

// this function returns the number
// of elements in the linked list
int Size(LINKED_LIST list)
{
    if (list.head == NULL)
    {
        return 0;
    } // empty list
    NODE *current = list.head;
    // start count at 1 to acc. for the head
    int count = 1;
    while (current->next != NULL)
    {
        count++;
        current = current->next;
    }
    return count;
}
// this function adds an element to
// the front of the list
void Push_Front(LINKED_LIST *list, int data)
{
    // create a new node with data
    NODE *temp = malloc(sizeof(NODE));
    Verify_Malloc(temp);
    temp->data = data;
    // set next to curr head
    temp->next = list->head;
    // set head to new node
    list->head = temp;
    return;
}

// this function adds an element
// to the end of the linked list
void Push_Back(LINKED_LIST *list, int data)
{
    // if the list is empty, add the node as the head
    if (list->head == NULL)
    {
        Push_Front(list, data);
        return;
    }

    // create a new node with data
    NODE *temp = malloc(sizeof(NODE));
    Verify_Malloc(temp);
    temp->data = data;
    temp->next = NULL;
    // move to the end of the list
    NODE *current = list->head;
    while (current->next != NULL)
    {
        current = current->next;
    }
    // set the last to the curr node
    current->next = temp;
    return;
}

// if the list is not empty
// the value of the first element of the list is returned by reference in the parameter data
// the first element of the list is deleted
// returns 0 if the list is empty otherwise returns 1
// remember to free the deleted node
int Pop_Front(LINKED_LIST *list, int *data)
{
    // list is empty: return 0
    if (list->head == NULL)
    {
        return 0;
    }
    // get a pointer to curr head
    NODE *oldHead = list->head;
    // move the pointer to the head
    list->head = list->head->next;
    *data = oldHead->data;

    free(oldHead);

    return 1;
}

// if the list is not empty
// the value of the last element of the list is returned by reference in the parameter data
// the last element of the list is deleted
// returns 0 if the list is empty otherwise returns 1
// remember to free the deleted node
int Pop_Back(LINKED_LIST *list, int *data)
{

    int listSize = Size(*list);
    if (listSize <= 1)
    {
        // retain same behavior for remove with single or no
        return Pop_Front(list, data);
    }

    NODE *prev = list->head;
    NODE *current = prev->next;
    while (current->next != NULL)
    {
        prev = prev->next;
        current = prev->next;
    }

    *data = current->data;

    free(current);
    // remove the last node
    prev->next = NULL;

    return 1;

    // increment current until curr.next points to the last node
    // NODE *current = list->head;
    // for (int i = 0; i < listSize - 2; i++)
    // {
    //     current = current->next;
    // }

    // NODE *last = current->next;
    // *data = last->data;
    // free(last);
    // // remove the last node
    // current->next = NULL;
}

// this function returns the number
// of times that the value of the parameter
// data appears in the list
int Count_If(LINKED_LIST list, int data)
{
    NODE *current = list.head;
    int count = 0;
    // iterate through the whole LL
    while (current != NULL)
    {
        if (data == current->data)
        {
            count++;
        }
        current = current->next;
    }
    return count;
}

// delete the first node containing the data value
// return 1 if something was deleted otherwise 0
// remember to free the deleted node
int Delete(LINKED_LIST *list, int data)
{
    NODE *previous = list->head;
    // nothing in the LL
    if (previous == NULL)
    {
        return 0;
    }
    // head has the data value
    if (previous->data == data)
    {
        return Pop_Front(list, &data);
    }

    NODE *current = previous->next;
    while (current != NULL)
    {
        // found first node containing data value
        if (current->data == data)
        {
            previous->next = current->next;
            free(current);
            // deleted first occurrence, return success
            return 1;
        }
        // continue searching
        previous = previous->next;
        current = previous->next;
    }
    return 0;
}

// return 1 if the list is empty otherwise returns 0
int Is_Empty(LINKED_LIST list)
{
    int listSize = Size(list);
    return listSize == 0;
}

// delete all elements of the list
// remember to free the nodes
void Clear(LINKED_LIST *list)
{
    int temp;
    // while there are still elements, pop the first element
    while (Pop_Front(list, &temp))
    {
    }

    return;
}

// if an element appears in the list
// retain the first occurance but
// remove all other nodes with the same
// data value
void Remove_Duplicates(LINKED_LIST *list)
{
    // check for empty list
    NODE *current = list->head;
    if (current == NULL)
    {
        return;
    }

    while (current != NULL)
    {
        // truncate the list to be everything after the curr node
        LINKED_LIST truncList = Create_List();
        truncList.head = current->next;
        // delete all other occurrences of the curr data value
        while (Delete(&truncList, current->data))
        {
        }
        // "reappend" the list
        current->next = truncList.head;
        current = current->next;
    }
    return;
}

// modify main to completely test your functions
int main()
{
    // create a linked list
    printf("creating linked list\n");
    LINKED_LIST list = Create_List();

    // add some data (hardcoded for testing)
    printf("hardcoding some data\n");
    NODE *first = malloc(sizeof(NODE));
    Verify_Malloc(first);
    NODE *second = malloc(sizeof(NODE));
    Verify_Malloc(second);
    first->data = 1;
    second->data = 2;
    list.head = first;
    first->next = second;
    second->next = NULL;
    // print the list
    printf("Testing Print_List\n");
    Print_List(stdout, list);
    // write a better test for Size
    printf("Testing Size\n");
    printf("size = %d\n", Size(list));
    // write a better test for Push_Front
    printf("Testing Push_Front\n");
    Push_Front(&list, 0);
    Print_List(stdout, list);

    // write a better test for Push_Back
    printf("Testing Push_Back\n");
    Push_Back(&list, 3);
    Print_List(stdout, list);
    // write a better test for Pop_Front
    printf("Testing Pop_Front\n");
    {
        int x;
        int not_empty = Pop_Front(&list, &x);
        if (not_empty)
        {
            printf("Element popped was %d\n", x);
            Print_List(stdout, list);
            printf("size = %d\n", Size(list));
        }
        else
            printf("List was empty\n");
    }
    // write a better test for Pop_Back
    printf("Testing Pop_Back\n");
    {
        int x;
        int not_empty = Pop_Back(&list, &x);
        if (not_empty)
        {
            printf("Element popped was %d\n", x);
            Print_List(stdout, list);
            printf("size = %d\n", Size(list));
        }
        else
            printf("List was empty\n");
    }
    // write a beter test for Count_If
    Push_Front(&list, 5);
    Push_Front(&list, 5);
    Print_List(stdout, list);
    printf("5 count = %d\n", Count_If(list, 5));

    // write a test for Delete
    printf("Testing Delete\n");
    Print_List(stdout, list);
    Delete(&list, 1);
    Print_List(stdout, list);
    // write a better test for Is_Empty
    printf("Testing Is_Empty\n");
    if (Is_Empty(list))
        printf("List is Empty\n");
    else
        printf("List is not empty\n");

    // write a better test for Clear
    Clear(&list);
    if (Is_Empty(list))
        printf("List is Empty\n");
    else
        printf("List is not empty\n");
    // write a better test for Remove_Duplicates
    Push_Back(&list, 1);
    Push_Back(&list, 1);
    Push_Back(&list, 1);
    Push_Back(&list, 2);
    Push_Back(&list, 2);
    Push_Back(&list, 3);
    Push_Back(&list, 3);
    Push_Back(&list, 3);
    Remove_Duplicates(&list);
    Print_List(stdout, list);

    Clear(&list);
    return 0;

    // // create a linked list
    // printf("creating linked list\n");
    // LINKED_LIST list = Create_List();

    // // // ARIEL TEST
    // printf("Testing Push_Back - emtpy list\n");
    // Push_Back(&list, 3);
    // Print_List(stdout, list);
    // Clear(&list);
    // // write a better test for Push_Front
    // // printf("Testing Push_Front - new node data: 10\n");
    // // Push_Front(&list, 10);
    // // Print_List(stdout, list);
    // // printf("__________TEST PUSH BACK_________\n");

    // // add some data (hardcoded for testing)
    // printf("hardcoding some data\n");
    // NODE *first = malloc(sizeof(NODE));
    // Verify_Malloc(first);
    // NODE *second = malloc(sizeof(NODE));
    // Verify_Malloc(second);
    // first->data = 1;
    // second->data = 2;
    // list.head = first;
    // first->next = second;
    // second->next = NULL;

    // // print the list
    // printf("Testing Print_List\n");
    // Print_List(stdout, list);
    // printf("__________TEST SIZE____________\n");

    // // write a better test for Size
    // printf("Testing Size - should be 2\n");
    // printf("size = %d\n", Size(list));
    // printf("__________TEST PUSH FRONT___________\n");

    // // write a better test for Push_Front
    // printf("Testing Push_Front - new node data: 10\n");
    // Push_Front(&list, 10);
    // Print_List(stdout, list);
    // printf("__________TEST PUSH BACK_________\n");

    // // write a better test for Push_Back
    // printf("Testing Push_Back - new node data: 3\n");
    // Push_Back(&list, 3);
    // Print_List(stdout, list);
    // printf("__________TEST POP FRONT__________\n");

    // // write a better test for Pop_Front
    // printf("Testing Pop_Front - pop 10\n");
    // {
    //     int x;
    //     int not_empty = Pop_Front(&list, &x);
    //     if (not_empty)
    //     {
    //         printf("Element popped was %d\n", x);
    //         Print_List(stdout, list);
    //         printf("size = %d\n", Size(list));
    //     }
    //     else
    //         printf("List was empty\n");
    // }
    // printf("___________TEST POP BACK__________\n");

    // // write a better test for Pop_Back
    // printf("Testing Pop_Back - pop 3\n");
    // {
    //     int x;
    //     int not_empty;
    //     do
    //     {
    //         not_empty = Pop_Back(&list, &x);
    //         if (not_empty)
    //         {
    //             printf("Element popped was %d\n", x);
    //             Print_List(stdout, list);
    //             printf("size = %d\n", Size(list));
    //         }
    //         else
    //         {
    //             printf("List was empty\n");
    //         }
    //     } while (not_empty);
    // }
    // printf("__________TEST COUNT IF__________\n");

    // // write a beter test for Count_If
    // printf("empty list 5 count = %d\n", Count_If(list, 5));
    // Push_Front(&list, 5);
    // Print_List(stdout, list);
    // printf("at beginning 5 count = %d\n", Count_If(list, 5));

    // Push_Front(&list, 5);
    // Push_Front(&list, 5);
    // Print_List(stdout, list);
    // printf("(3) 5 count = %d\n", Count_If(list, 5));
    // printf("(0) 3 count = %d\n", Count_If(list, 3));
    // printf("__________TEST DELETE__________\n");

    // // write a test for Delete
    // printf("Testing Delete\n");
    // Print_List(stdout, list);
    // Delete(&list, 5);

    // Print_List(stdout, list);
    // printf("Testing Delete - 2 is not in list\n");
    // Delete(&list, 2);

    // Print_List(stdout, list);
    // Delete(&list, 5);

    // Print_List(stdout, list);
    // Delete(&list, 5);

    // printf("Testing Delete - nothing in list\n");
    // Print_List(stdout, list);
    // Delete(&list, 5);

    // printf("Nothing in list? \n");
    // Print_List(stdout, list);
    // printf("_________TEST IS EMPTY__________\n");

    // // write a better test for Is_Empty
    // printf("Testing Is_Empty - should be empty\n");
    // if (Is_Empty(list))
    // {
    //     printf("List is Empty\n");
    // }
    // else
    // {
    //     printf("List is not empty\n");
    // }

    // printf("Testing Is_Empty - should not be empty\n");
    // Push_Front(&list, 1);
    // Push_Front(&list, 2);
    // Push_Front(&list, 3);
    // Print_List(stdout, list);
    // if (Is_Empty(list))
    // {
    //     printf("List is Empty\n");
    // }
    // else
    // {
    //     printf("List is not empty\n");
    // }

    // printf("________TEST CLEAR__________\n");

    // // write a better test for Clear
    // printf("Testing Clear\n");
    // Clear(&list);
    // if (Is_Empty(list))
    //     printf("List is Empty\n");
    // else
    //     printf("List is not empty\n");
    // printf("_________TEST REMOVE DUPLICATES_________\n");

    // // write a better test for Remove_Duplicates
    // printf("Test remove duplicates; empty\n");
    // Remove_Duplicates(&list);
    // Print_List(stdout, list);
    // // test rud duplicates
    // printf("Test remove duplicates; 1->1->1->2->2->3->3->3\n");
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 2);
    // Push_Back(&list, 2);
    // Push_Back(&list, 3);
    // Push_Back(&list, 3);
    // Push_Back(&list, 3);
    // Remove_Duplicates(&list);
    // Print_List(stdout, list);
    // Clear(&list);

    // // test all same values
    // printf("Test remove duplicates: all duplicates: 1->1->...->1\n");
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Push_Back(&list, 1);
    // Remove_Duplicates(&list);
    // Print_List(stdout, list);
    // Clear(&list);

    // // test no duplicates
    // Push_Back(&list, 1);
    // Push_Back(&list, 2);
    // Push_Back(&list, 3);
    // Push_Back(&list, 4);
    // Push_Back(&list, 5);
    // printf("Test remove duplicates: no duplicates\n");
    // Remove_Duplicates(&list);
    // Print_List(stdout, list);

    // Clear(&list);

    // return 0;
}
