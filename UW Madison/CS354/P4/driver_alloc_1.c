#include "mem.h"
#include <stdio.h>

int main()
{

    Initialize_Memory_Allocator(1600);
    Mem_Dump();
    // ARIEL test
    BLOCK_HEADER *first = return_start();
    int ret = Is_Alloc(first);
    printf("%d = 0\n", ret);
    BLOCK_HEADER not_free = {1, 10};
    ret = Is_Alloc(&not_free);
    printf("%d = 1\n", ret);
    Set_Free(&not_free);
    ret = Is_Alloc(&not_free);
    printf("%d = 0\n", ret);

    // orig test

    char *p = Mem_Alloc(1);

    if (p == NULL)
    {
        printf("Allocation failed\n");
        exit(0);
    }
    p[0] = 'c';

    *p = Mem_Alloc(100);

    if (p == NULL)
    {
        printf("Allocation failed\n");
        exit(0);
    }
    p[0] = 'c';

    *p = Mem_Alloc(64);

    if (p == NULL)
    {
        printf("Allocation failed\n");
        exit(0);
    }
    p[0] = 'c';

    char *delete = Mem_Alloc(71);

    if (delete == NULL)
    {
        printf("Allocation failed\n");
        exit(0);
    }

    delete[0] = 'c';
    Mem_Dump();

    ret = Mem_Free(delete);
    Mem_Dump();

    // // out of bounds
    // char *out = Mem_Alloc(1305);

    // if (out == NULL)
    // {
    //     printf("Allocation failed\n");
    //     exit(0);
    // }
    // p[0] = 'c';
    // Mem_Dump();

    Free_Memory_Allocator();
    return 0;
}
