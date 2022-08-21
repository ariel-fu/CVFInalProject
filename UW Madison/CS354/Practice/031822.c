#include <stdio.h>
#include <stdlib.h>

// Assumes little endian
void printBits(size_t const size, void const *const ptr)
{
    unsigned char *b = (unsigned char *)ptr;
    unsigned char byte;
    int i, j;

    for (i = size - 1; i >= 0; i--)
    {
        for (j = 7; j >= 0; j--)
        {
            byte = (b[i] >> j) & 1;
            printf("%u", byte);
        }
    }
    puts("");
}

int f(int x, int *py, int **ppz)
{
    int y, z;
    **ppz += 1;
    z = **ppz;
    *py += 2;
    y = *py;
    x += 3;
    return x + y + z;
}

void shift()
{
    int i = 256;
    for (; i; i <<= 1)
    {
        printBits(sizeof(i), &i);
    }
    printf("value = %d\n", i);
}

int x;
void Q(int z)
{
    z += x;
    printf("%d ", z);
}
void P(int *y)
{
    int x = *y + 2;
    Q(x);
    *y = x - 1;
    printf("%d ", x);
}

void mystery(int *ptra, int *ptrb)
{
    int *temp;
    temp = ptrb;
    ptrb = ptra;
    ptra = temp;
}

int main()
{
    // int a = 2016, b = 0, c = 4, d = 42;
    // mystery(&a, &b);
    // if (a < c)
    //     mystery(&c, &a);
    // mystery(&a, &d);
    // printf("%dn", a);

    int array[5][5];
    printf("%p | %d", array, ((array == *array) && (*array == array[0])));
    return 0;

    // int arr[] = {10, 20, 30, 40, 50, 60};
    // int *ptr1 = arr;
    // int *ptr2 = arr + 5;
    // printf("Number of elements between two pointer are: %d.\n", (ptr2 - ptr1));

    // int *charptr1 = (int *)ptr2;
    // char *charptr2 = (char *)ptr1;

    // printf("ptr1: %p & val: %d | ptr2: %p & val: %d\n", ptr1, *ptr1, ptr2, *ptr2);

    // printf("char ptr1: %p & val: %c | char ptr2: %p & val: %c\n", charptr1, *charptr1, charptr2, *charptr2);

    // printf("Number of bytes between two pointers are: %d\n", (int *)ptr2 - (int *)ptr1);

    // int a = 50;
    // char *x;
    // int *ap = &a;
    // // convert to ascii representation
    // x = (char *)ap;

    // a = 512;
    // printBits(sizeof(a), &a);
    // x[0] = 1;
    // printf("add a 1: \n");
    // printBits(sizeof(x), x);
    // // printf("x val: %c | x[0]: %c\n", *x, x[0]);
    // x[1] = 'a';
    // printf("add a 2: %c\n", x[1]);
    // printBits(sizeof(x), x);

    // printf("%d\n", a);
    // printBits(sizeof(a), &a);

    // int c, *b, **a;
    // c = 4;
    // b = &c;
    // a = &b;
    // printf("%d ", f(c, b, a));

    // shift();

    // x = 5;
    // P(&x);
    // printf("%d ", x);

    // int i = 5;
    // void *vptr;
    // vptr = &i;
    // printf("value of vptr: %d\n", *(int *)vptr);
}