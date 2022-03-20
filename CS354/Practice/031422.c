#include <stdio.h>
#include <stdlib.h>

typedef struct point
{
    int x;
    int y;
} POINT;
typedef struct rect
{
    POINT pt1;
    POINT pt2;
} RECT;
typedef struct nlist
{
    struct nlist *next;
    char *name;
    char *defn;
} nlist;

static struct nlist *hashtab[101];

POINT create_point(int x, int y)
{
    POINT p;
    p.x = x;
    p.y = y;
    return p;
}

void sum(int *a, int *b, int *sum)
{
    printf("adding %d and %d ", *a, *b);
    *sum = *a + *b;
}

void print_elements(char *array)
{
    // printf("array: ");
    // while (*array)
    // {
    //     printf("%c ", *array);
    //     array++;
    // }

    for (int i = 0; *(array + i); i++)
    {
        char curr = *(array + i);
        printf("%c ", *(array + i));
    }
    printf("\n");
}

void copy(char *arr1, char *arr2)
{
    printf("copying: ");
    print_elements(arr1);
    printf("to: ");
    print_elements(arr2);
    while (*arr1)
    {
        *arr2 = *arr1;
        arr1++;
        arr2++;
    }
    *arr2 = '\0';
    return;
}

void swap(char *arr1, char *arr2)
{
    printf("arr1: ");
    print_elements(arr1);
    printf("arr2: ");
    print_elements(arr2);
    while (*arr1 && *arr2)
    {
        char temp = *arr1;
        *arr1 = *arr2;
        *arr2 = temp;
        arr1++;
        arr2++;
    }
    if (*arr1)
    {
        // add the rest of arr1 to arr2
        while (*arr1)
        {
            *arr2 = *arr1;
            arr1++;
        }
        *arr2 = '\0';
    }
    else
    {
        // add the rest of arr2 to arr1
        while (*arr2)
        {
            *arr1 = *arr2;
            arr2++;
        }
        *arr1 = '\0';
    }
}
/**
 * @brief return 0 if the element is not in the array, 1 if the element is in the array
 *
 * @param array
 * @param element
 * @return int
 */
int search(char *array, char element)
{
    while (*array)
    {
        if (element == *array)
        {
            return 1;
        }
        *array++;
    }
    return 0;
}

void test_pointers(RECT r, RECT *rp)
{
    printf("r: %d \t rp: %d\n", r.pt1.x, rp->pt1.x);
    printf("r: %d \t rp: %d\n", (r.pt1).x, (rp->pt1).x);
    printf("rp: %d \t rp * notation: %d\n", rp->pt1.x, (*rp).pt1.x);
}

void ptrStrCat(char *s, char *t)
{
    while (*s)
    {
        s++;
    }

    while (*t)
    {
        *s = *t;
        s++;
        t++;
    }
    *s = '\0';
}

void main()
{
    // int a = 10;
    // int b = 20;
    // int sumval = 200;
    // sum(&a, &b, &sumval);
    // printf("to get %d\n", sumval);

    // char array[] = "abcdef";
    // print_elements(array);

    // char cutoff[8] = {'a', 'b', 'c', 'd', 'e', '\0'};
    // print_elements(cutoff);

    // copy(array, cutoff);
    // printf("successfully copied: ");
    // print_elements(array);
    // printf("to: ");
    // print_elements(array);

    // char arr1[] = "abc";
    // char arr2[] = "xyz";
    // swap(arr1, arr2);
    // printf("arr1: ");
    // print_elements(arr1);
    // printf("arr2: ");
    // print_elements(arr2);

    // int ret = search(arr2, 'a');
    // printf("found %d = 1\n", ret);
    // ret = search(arr2, 'b');
    // printf("found %d = 1\n", ret);
    // ret = search(arr2, 'c');
    // printf("found %d = 1\n", ret);
    // ret = search(arr2, 'd');
    // printf("found %d = 0\n", ret);

    // RECT r1;
    // r1.pt1 = create_point(5, 5);
    // r1.pt2 = create_point(10, 10);

    // RECT r2;
    // r2.pt1 = create_point(1, 1);
    // r2.pt2 = create_point(5, 5);

    // test_pointers(r1, &r2);

    char s[] = {'1', '2', '3', '\0'};
    char t[] = "abc";
    ptrStrCat(s, t);
    printf("%s\n", s);
}