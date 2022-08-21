#include <stdio.h>

void main()
{

    int count = 0;
    for (char i = 1; count < 10; i *= 2)
    {
        printf("char i %d ", i);
        count++;
    }

    int i = -1;
    do
    {
        printf("\n%d", i++);
    } while (i++);

    printf("\n%d", !'3');

    int q[5] = {0, 2, 4, 6, 8};
    int *p = &(q[4]);

    printf("\nq-p: %d, q address: %p, p address: %p", q - p, &q, &p);

    
}