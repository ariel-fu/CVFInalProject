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
    // set the head to null
    list->head = NULL;
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
    /** TEST SIZE */
    printf("-- TEST SIZE -- \n");
    // 1. size = 0
    int size = Size(list);
    printf("size: %d = 0\n", size);
    // 2. size = 1
    NODE *temp = malloc(sizeof(NODE));
    temp->data = 1;
    temp->next = NULL;
    list.head = temp;

    size = Size(list);
    printf("size: %d = 1\n", size);
    // 3. size > 1
    NODE *temp2 = malloc(sizeof(NODE));
    temp2->data = 2;
    temp2->next = NULL;
    temp->next = temp2;

    size = Size(list);
    printf("size: %d = 2\n", size);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST PUSH_FRONT */
    printf("\n-- TEST PUSH_FRONT -- \n");
    // 1. push to empty list
    Push_Front(&list, 1);
    printf("list w empty: \n");
    Print_List(stdout, list);

    // 2. push to a list w 1 element
    Push_Front(&list, 2);
    printf("list w 1 element: \n");
    Print_List(stdout, list);

    // 3. push to a list w > 1 elements
    Push_Front(&list, 3);
    printf("list w > 1 elements: \n");
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST PUSH_BACK */
    printf("\n-- TEST PUSH_BACK -- \n");
    // 1. push to empty list
    Push_Back(&list, 1);
    printf("list w empty: \n");
    Print_List(stdout, list);

    // 2. push to a list w 1 element
    Push_Back(&list, 2);
    printf("list w 1 element: \n");
    Print_List(stdout, list);

    // 3. push to a list w > 1 elements
    Push_Back(&list, 3);
    printf("list w > 1 elements: \n");
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST POP_FRONT */
    printf("\n-- TEST POP_FRONT -- \n");
    int data = -1;
    int retVal;
    // 1. pop when the list is empty
    // list:
    retVal = Pop_Front(&list, &data);
    printf("return val: %d = 0\n", retVal);
    printf("data val: %d = -1\n", data);
    Print_List(stdout, list);

    // 2. pop when there is only 1 element
    temp = malloc(sizeof(NODE));
    temp->data = 1;
    temp->next = NULL;
    list.head = temp;
    // list: 1
    retVal = Pop_Front(&list, &data);
    printf("return val: %d = 1\n", retVal);
    printf("data val: %d = 1\n", data);
    Print_List(stdout, list);

    // 3. pop when there is > 1 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp2->next = NULL;
    list.head = temp;
    // list: 10->20
    retVal = Pop_Front(&list, &data);
    printf("return val: %d = 1\n", retVal);
    printf("data val: %d = 10\n", data);
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST POP_BACK */
    printf("\n-- TEST POP_BACK -- \n");
    data = -1;
    // 1. pop when the list is empty
    // list:
    retVal = Pop_Back(&list, &data);
    printf("return val: %d = 0\n", retVal);
    printf("data val: %d = -1\n", data);
    Print_List(stdout, list);

    // 2. pop when there is only 1 element
    temp = malloc(sizeof(NODE));
    temp->data = 1;
    temp->next = NULL;
    list.head = temp;
    // list: 1
    retVal = Pop_Back(&list, &data);
    printf("return val: %d = 1\n", retVal);
    printf("data val: %d = 1\n", data);
    Print_List(stdout, list);

    // 3. pop when there is > 1 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp2->next = NULL;
    list.head = temp;
    // list: 10->20
    retVal = Pop_Back(&list, &data);
    printf("return val: %d = 1\n", retVal);
    printf("data val: %d = 20\n", data);
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST COUNT_IF */
    printf("\n-- TEST COUNT_IF -- \n");
    int count = 0;
    // 1. test when the list is empty
    count = Count_If(list, 10);
    printf("count: %d = 0\n", count);
    // create a linked list w 8 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    NODE *temp3 = malloc(sizeof(NODE));
    temp3->data = 30;
    temp2->next = temp3;
    NODE *add20 = malloc(sizeof(NODE));
    add20->data = 20;
    temp3->next = add20;
    NODE *temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    add20->next = temp4;
    NODE *fty1 = malloc(sizeof(NODE));
    fty1->data = 40;
    temp4->next = fty1;
    NODE *fty2 = malloc(sizeof(NODE));
    fty2->data = 40;
    fty1->next = fty2;
    NODE *temp5 = malloc(sizeof(NODE));
    temp5->data = 50;
    fty2->next = temp5;
    temp5->next = NULL;
    list.head = temp;

    // list: 10->20->30->20->40->40->40->50
    // 2. test the element to count appears: 0
    count = Count_If(list, 60);
    printf("count: %d = 0\n", count);
    // 3. test the element to count appears: 1
    count = Count_If(list, 30);
    printf("count: %d = 1\n", count);
    // 4. test the element appears at the head
    count = Count_If(list, 10);
    printf("count: %d = 1\n", count);
    // 5. test the element appears at the tail
    count = Count_If(list, 50);
    printf("count: %d = 1\n", count);
    // 6. test the element to count appears: > 1
    count = Count_If(list, 20);
    printf("count: %d = 2\n", count);
    // 7. test the elements appear in sequence
    count = Count_If(list, 40);
    printf("count: %d = 3\n", count);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST DELETE */
    printf("\n-- TEST DELETE -- \n");
    retVal = -1;

    // 1. elements in the list = 0
    printf("delete 0\n");
    retVal = Delete(&list, 0);
    printf("return value: %d = 0\n", retVal);
    Print_List(stdout, list);

    // test only 1 element
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp->next = NULL;
    list.head = temp;
    printf("delete 1\n");
    retVal = Delete(&list, 10);
    printf("return value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // create a linked list w 8 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp3 = malloc(sizeof(NODE));
    temp3->data = 30;
    temp2->next = temp3;
    add20 = malloc(sizeof(NODE));
    add20->data = 20;
    temp3->next = add20;
    temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    add20->next = temp4;
    fty1 = malloc(sizeof(NODE));
    fty1->data = 40;
    temp4->next = fty1;
    fty2 = malloc(sizeof(NODE));
    fty2->data = 40;
    fty1->next = fty2;
    temp5 = malloc(sizeof(NODE));
    temp5->data = 50;
    fty2->next = temp5;
    temp5->next = NULL;
    list.head = temp;

    printf("starting list: \n");
    Print_List(stdout, list);
    printf("----");
    // 2. element does not appear
    retVal = Delete(&list, 80);
    printf("remove 80\nreturn value: %d = 0\n", retVal);
    Print_List(stdout, list);

    // 3. element appears once
    retVal = Delete(&list, 30);
    printf("remove 30 \nreturn value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // 4. element appears at the head
    retVal = Delete(&list, 10);
    printf("remove 10\nreturn value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // 5. element appears at the tail
    retVal = Delete(&list, 50);
    printf("remove 50\nreturn value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // 6. element appears multiple times
    retVal = Delete(&list, 20);
    printf("remove 20\nreturn value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // 7. element appears in a sequence
    retVal = Delete(&list, 40);
    printf("remove 40\nreturn value: %d = 1\n", retVal);
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST IS EMPTY */
    printf("\n-- TEST IS_EMPTY -- \n");
    // 1. list is empty
    retVal = Is_Empty(list);
    printf("is empty: %d = 1\n", retVal);

    // set up linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp2->next = NULL;
    list.head = temp;

    // 2. list is not empty
    retVal = Is_Empty(list);
    printf("is empty: %d = 0\n", retVal);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST IS CLEAR */
    printf("\n-- TEST CLEAR -- \n");
    // 1. list is empty
    printf("clearing an empty list\n");
    Clear(&list);
    Print_List(stdout, list);

    // set up linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp2->next = NULL;
    list.head = temp;
    Print_List(stdout, list);
    // 2. list is not empty
    printf("clearing a nonempty list\n");
    Clear(&list);
    Print_List(stdout, list);

    // Clear the list to prep for the next test
    list.head = NULL;

    /** TEST IS REMOVE DUPLICATES */
    printf("\n-- TEST REMOVE_DUPLICATES -- \n");
    // 1. remove duplicates in an empty list
    Remove_Duplicates(&list);

    // 2. list does not contain any duplicates
    // set up linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp2->next = NULL;
    list.head = temp;
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("does not contain duplicates: \n");
    Print_List(stdout, list);

    // 3. list contains 1 duplicate in the middle
    // set up the linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 10;
    temp->next = temp2;
    add20 = malloc(sizeof(NODE));
    add20->data = 20;
    temp2->next = add20;
    add20->next = NULL;
    list.head = temp;
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains duplicates in the middle: \n");
    Print_List(stdout, list);

    // 4. list contains 1 duplicate at the tail
    // set up the linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    add20 = malloc(sizeof(NODE));
    add20->data = 20;
    temp2->next = add20;
    add20->next = NULL;
    list.head = temp;
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains duplicates at the tail: \n");
    Print_List(stdout, list);

    // 5. list contains 1 duplicate w multiple duplicate values
    // set up the linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 20;
    temp->next = temp2;
    temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    temp2->next = temp4;
    fty1 = malloc(sizeof(NODE));
    fty1->data = 40;
    temp4->next = fty1;
    fty2 = malloc(sizeof(NODE));
    fty2->data = 40;
    fty1->next = fty2;
    temp5 = malloc(sizeof(NODE));
    temp5->data = 50;
    fty2->next = temp5;
    temp5->next = NULL;
    // 10 -> 20 -> 40 -> 40 -> 40 -> 50
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains 1 duplicates with multiple duplicate values: \n");
    Print_List(stdout, list);

    // 6. list contains multiple duplicates w 1 duplicate value
    // set up the linked list
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 10;
    temp->next = temp2;
    temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    temp2->next = temp4;
    fty1 = malloc(sizeof(NODE));
    fty1->data = 40;
    temp4->next = fty1;
    temp5 = malloc(sizeof(NODE));
    temp5->data = 50;
    fty1->next = temp5;
    temp5->next = NULL;
    // 10 -> 10 -> 40 -> 40 -> 50
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains multiple duplicates with 1 duplicate value: \n");
    Print_List(stdout, list);

    // 7. list contains multiple duplicates w multiple duplicate values
    // create a linked list w 8 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 10;
    temp->next = temp2;
    temp3 = malloc(sizeof(NODE));
    temp3->data = 10;
    temp2->next = temp3;
    add20 = malloc(sizeof(NODE));
    add20->data = 20;
    temp3->next = add20;
    temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    add20->next = temp4;
    fty1 = malloc(sizeof(NODE));
    fty1->data = 40;
    temp4->next = fty1;
    fty2 = malloc(sizeof(NODE));
    fty2->data = 40;
    fty1->next = fty2;
    temp5 = malloc(sizeof(NODE));
    temp5->data = 40;
    fty2->next = temp5;
    temp5->next = NULL;
    list.head = temp;
    // 10 -> 10 -> 10 -> 20 -> 40 -> 40 -> 40 -> 40
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains multiple duplicates with multiple duplicate values: \n");
    Print_List(stdout, list);

    // 8. list contains duplicates that are not in succession
    // create a linked list w 8 elements
    temp = malloc(sizeof(NODE));
    temp->data = 10;
    temp2 = malloc(sizeof(NODE));
    temp2->data = 40;
    temp->next = temp2;
    temp3 = malloc(sizeof(NODE));
    temp3->data = 20;
    temp2->next = temp3;
    add20 = malloc(sizeof(NODE));
    add20->data = 10;
    temp3->next = add20;
    temp4 = malloc(sizeof(NODE));
    temp4->data = 40;
    add20->next = temp4;
    fty1 = malloc(sizeof(NODE));
    fty1->data = 10;
    temp4->next = fty1;
    fty2 = malloc(sizeof(NODE));
    fty2->data = 50;
    fty1->next = fty2;
    temp5 = malloc(sizeof(NODE));
    temp5->data = 40;
    fty2->next = temp5;
    temp5->next = NULL;
    list.head = temp;
    // 10 -> 40 -> 20 -> 10 -> 40 -> 10 -> 50 -> 40
    printf("before: ");
    Print_List(stdout, list);

    Remove_Duplicates(&list);
    printf("contains multiple duplicates with multiple duplicate values: \n");
    Print_List(stdout, list);
    /** -------------- TEST CASES PROVIDED BY CANVAS --------------- */
    // printf("creating linked list\n");
    // LINKED_LIST list = Create_List();

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
    // // write a better test for Size
    // printf("Testing Size\n");
    // printf("size = %d\n", Size(list));
    // // write a better test for Push_Front
    // printf("Testing Push_Front\n");
    // Push_Front(&list, 0);
    // Print_List(stdout, list);

    // // write a better test for Push_Back
    // printf("Testing Push_Back\n");
    // Push_Back(&list, 3);
    // Print_List(stdout, list);
    // // write a better test for Pop_Front
    // printf("Testing Pop_Front\n");
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
    // // write a better test for Pop_Back
    // printf("Testing Pop_Back\n");
    // {
    //     int x;
    //     int not_empty = Pop_Back(&list, &x);
    //     if (not_empty)
    //     {
    //         printf("Element popped was %d\n", x);
    //         Print_List(stdout, list);
    //         printf("size = %d\n", Size(list));
    //     }
    //     else
    //         printf("List was empty\n");
    // }
    // // write a beter test for Count_If
    // Push_Front(&list, 5);
    // Push_Front(&list, 5);
    // Print_List(stdout, list);
    // printf("5 count = %d\n", Count_If(list, 5));

    // // write a test for Delete
    // printf("Testing Delete\n");
    // Print_List(stdout, list);
    // Delete(&list, 1);
    // Print_List(stdout, list);
    // // write a better test for Is_Empty
    // printf("Testing Is_Empty\n");
    // if (Is_Empty(list))
    //     printf("List is Empty\n");
    // else
    //     printf("List is not empty\n");

    // // write a better test for Clear
    // Clear(&list);
    // if (Is_Empty(list))
    //     printf("List is Empty\n");
    // else
    //     printf("List is not empty\n");
    // // write a better test for Remove_Duplicates
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
    // return 0;

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
