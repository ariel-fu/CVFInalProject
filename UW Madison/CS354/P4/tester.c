#include "mem.h"
#include <stdio.h>

int headersize = 8;

/**
 * @brief sets up a heap
 */
void generate_heap(int size)
{
    Initialize_Memory_Allocator(size);
}

/**
 * @brief removes the heap
 */
void delete_heap()
{
    Free_Memory_Allocator();
}

void verify_size_alloc(int expected, BLOCK_HEADER *header)
{
    // get the header
    header = (BLOCK_HEADER *)((unsigned long)header - 8);
    int size_alloc = header->size_alloc;
    printf("Size: %d == %d\n", expected, size_alloc);
}
void verify_payload(int expected, BLOCK_HEADER *header)
{
    // get the header
    header = (BLOCK_HEADER *)((unsigned long)header - 8);
    int payload = header->payload;
    printf("Payload: %d == %d\n", expected, payload);
}
void verify_padding(int expected, BLOCK_HEADER *header)
{
    // get the header
    header = (BLOCK_HEADER *)((unsigned long)header - 8);
    int padding = (header->size_alloc & 0xFFFFFFFE) - 8 - header->payload;
    printf("Padding: %d == %d\n", expected, padding);
}

/**
 * test allocations that are too big
 * almost too big
 * small enough to require splitting
 * big enough to require not splitting
 * coalescing before
 *            after
 *            both.
 */

void too_big()
{
    generate_heap(160);
    BLOCK_HEADER *header = (BLOCK_HEADER *)Mem_Alloc(169);
    printf("*** too big ***\n");
    printf("is null: %d\n", header == NULL);
}

void almost_too_big()
{
    generate_heap(160);
    BLOCK_HEADER *header = Mem_Alloc(150);
    // int alloc = header->size_alloc;
    printf("*** almost too big ***\n");
    verify_size_alloc(161, header);
    verify_payload(150, header);
    verify_padding(160 - 150 - 8, header);
}

void split()
{
    generate_heap(160);
    BLOCK_HEADER *header = Mem_Alloc(80);
    printf("*** split *** \n");
    verify_size_alloc(96 + 1, header);
    verify_payload(80, header);
    verify_padding(96 - 80 - 8, header);
}

void no_coalesce()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** no coalesce ***\n");
    int free = Mem_Free(header);
    printf("freed: %d = 0\n", free);
    verify_size_alloc(16, header);
    verify_payload(8, header);
    verify_padding(0, header);
}

void coalesce_before()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** coalesce before ***\n");
    int free = Mem_Free(p1);
    printf("freed: %d = 0\n", free);
    free = Mem_Free(header);
    printf("freed: %d = 1\n", free);
    verify_size_alloc(32, p1);
    verify_payload(24, p1);
    verify_padding(0, p1);
}

void coalesce_after()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** coalesce after ***\n");
    int free = Mem_Free(p2);
    printf("freed: %d = 0\n", free);
    free = Mem_Free(header);
    printf("freed: %d = 0\n", free);
    verify_size_alloc(32, header);
    verify_payload(24, header);
    verify_padding(0, header);
}

void coalesce_both()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** coalesce both ***\n");
    int free = Mem_Free(p1);
    printf("freed: %d = 0\n", free);
    free = Mem_Free(p2);
    printf("freed: %d = 0\n", free);
    free = Mem_Free(header);
    printf("freed: %d = 0\n", free);
    verify_size_alloc(48, p1);
    verify_payload(40, p1);
    verify_padding(0, p1);
}

void free_invalid()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** free invalid ***\n");
    int x = 10;
    int *b = &x;
    int free = Mem_Free(b);
    printf("invalid free: -1 = %d\n", free);
}

void free_freed()
{
    generate_heap(160);
    BLOCK_HEADER *p = Mem_Alloc(8);
    BLOCK_HEADER *p1 = Mem_Alloc(8);
    BLOCK_HEADER *header = Mem_Alloc(1);
    BLOCK_HEADER *p2 = Mem_Alloc(8);
    BLOCK_HEADER *p3 = Mem_Alloc(8);

    printf("*** free freed ***\n");
    int free = Mem_Free(header);
    free = Mem_Free(header);
    printf("free freed: -1 = %d\n", free);
    verify_size_alloc(16, header);
    verify_payload(8, header);
    verify_padding(0, header);
}

void main()
{

    too_big();
    almost_too_big();
    split();
    no_coalesce();
    coalesce_before();
    coalesce_after();
    coalesce_both();
    free_invalid();
    free_freed();
}
// /* Test allocate */
// /**
//  * @brief test a normal allocate
//  *
//  */
// void alloc_easy()
// {
//     generate_heap(31);
//     int *p = Mem_Alloc(8);
//     print_block(p);
//     delete_heap();
// }

// /**
//  * @brief allocate all the memory in the heap
//  *
//  */
// void alloc_all()
// {
//     generate_heap(31);
//     int *p = Mem_Alloc(32 - headersize);
//     print_block(p);
//     delete_heap();
// }

// /**
//  * @brief allocate exactly 16 bytes, including the header
//  *
//  */
// void alloc_no_padding()
// {
//     generate_heap(31);
//     int *p = Mem_Alloc(8);
//     print_block(p);
//     delete_heap();
// }

// /**
//  * @brief alloc smallest possible (1)
//  *
//  */
// void alloc_smallest()
// {
//     generate_heap(31);
//     int *p = Mem_Alloc(1);
//     print_block(p);
//     delete_heap();
// }

// /**
//  * @brief alloc about 8 - need padding of 15
//  *
//  */
// void alloc_above_eight()
// {
//     generate_heap(31);
//     int *p = Mem_Alloc(9);
//     print_block(p);
//     delete_heap();
// }

// /**
//  * @brief Allocate small enough to split
//  *
//  */
// void alloc_split()
// {
//     generate_heap(48);
//     Mem_Dump();
//     BLOCK_HEADER *p = Mem_Alloc(8);
//     print_block(p);
//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Allocate enough so that there is no need to split
//  *
//  */
// void alloc_no_split()
// {
//     generate_heap(48);
//     BLOCK_HEADER *p = Mem_Alloc(32);
//     print_block(p);
//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Allocate a sequence of 1s
//  *
//  */
// void alloc_seq1()
// {
//     generate_heap(160);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     int *p8 = Mem_Alloc(1);
//     int *p9 = Mem_Alloc(1);
//     int *p10 = Mem_Alloc(1);

//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);
//     print_block(p8);
//     print_block(p9);
//     print_block(p10);
//     delete_heap();
// }

// /**
//  * @brief Allocate an increasing sequence of heap values
//  *
//  */
// void alloc_increasing_seq()
// {
//     generate_heap(1600);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(2);
//     int *p3 = Mem_Alloc(3);
//     int *p4 = Mem_Alloc(4);
//     int *p5 = Mem_Alloc(5);
//     int *p6 = Mem_Alloc(6);
//     int *p7 = Mem_Alloc(7);
//     int *p8 = Mem_Alloc(8);
//     int *p9 = Mem_Alloc(9);
//     int *p10 = Mem_Alloc(10);
//     int *p11 = Mem_Alloc(11);
//     int *p12 = Mem_Alloc(12);
//     int *p13 = Mem_Alloc(13);
//     int *p14 = Mem_Alloc(14);
//     int *p15 = Mem_Alloc(15);
//     int *p16 = Mem_Alloc(16);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);
//     print_block(p8);
//     print_block(p9);
//     print_block(p10);
//     print_block(p11);
//     print_block(p12);
//     print_block(p13);
//     print_block(p14);
//     print_block(p15);
//     print_block(p16);
//     delete_heap();
// }

// /**
//  * @brief Allocate a decreasing sequence
//  *
//  */
// void alloc_decreasing_seq()
// {
//     generate_heap(1600);
//     int *p16 = Mem_Alloc(16);
//     int *p15 = Mem_Alloc(15);
//     int *p14 = Mem_Alloc(14);
//     int *p13 = Mem_Alloc(13);
//     int *p12 = Mem_Alloc(12);
//     int *p11 = Mem_Alloc(11);
//     int *p10 = Mem_Alloc(10);
//     int *p9 = Mem_Alloc(9);
//     int *p8 = Mem_Alloc(8);
//     int *p7 = Mem_Alloc(7);
//     int *p6 = Mem_Alloc(6);
//     int *p5 = Mem_Alloc(5);
//     int *p4 = Mem_Alloc(4);
//     int *p3 = Mem_Alloc(3);
//     int *p2 = Mem_Alloc(2);
//     int *p1 = Mem_Alloc(1);

//     print_block(p16);
//     print_block(p15);
//     print_block(p14);
//     print_block(p13);
//     print_block(p12);
//     print_block(p11);
//     print_block(p10);
//     print_block(p9);
//     print_block(p8);
//     print_block(p7);
//     print_block(p6);
//     print_block(p5);
//     print_block(p4);
//     print_block(p3);
//     print_block(p2);
//     print_block(p1);
//     delete_heap();
// }

// /**
//  * @brief allocate after the heap is full
//  *
//  */
// void allocate_after_full()
// {
//     generate_heap(32);
//     int *p = Mem_Alloc(24);
//     print_block(p);
//     int *p1 = Mem_Alloc(1);
//     print_block(p1);
//     delete_heap();
// }

// /**
//  * @brief test if it is valid to alloc a block of size 0
//  *
//  */
// void allocate_zero()
// {
//     generate_heap(32);
//     int *p = Mem_Alloc(0);
//     print_block(p);
//     Mem_Dump();
//     delete_heap();
// }

// /* Test free */
// void free_valid()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     Mem_Dump();
//     int ret = Mem_Free(p2);
//     Mem_Dump();
//     print_block(p2);
//     printf("0 = %d\n", ret);
//     delete_heap();
// }

// /**
//  * @brief Free a null pointer
//  *
//  */
// void free_null()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     // int ret = Mem_Free(p2);
//     // ret = Mem_Free(p2);
//     // print_block(p2);

//     int ret = Mem_Free(NULL);
//     printf("-1 = %d\n", ret);
//     delete_heap();
// }

// /**
//  * @brief Free a pointer that is not a user pointer
//  *
//  */
// void free_invalid()
// {
//     int x = 10;
//     int *ptr = &x;
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);

//     int ret = Mem_Free(ptr);
//     printf("-1 = %d\n", ret);
//     delete_heap();
// }

// /**
//  * @brief free the head of the heap
//  *
//  */
// void free_first()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);

//     int ret = Mem_Free(p1);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     delete_heap();
// }

// /**
//  * @brief Free the last value
//  *
//  */
// void free_last()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);

//     int ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     delete_heap();
// }

// /**
//  * @brief Free the value not in the heap
//  *
//  */
// void free_not_in_heap()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int ret = Mem_Free(p4);
//     printf("-1 = %d\n", ret);
//     print_block(p4);
//     delete_heap();
// }

// /**
//  * @brief Free everything in the heap
//  *
//  */
// void free_everything()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     Mem_Dump();
//     int ret = Mem_Free(p1);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     ret = Mem_Free(p2);
//     printf("0 = %d\n", ret);
//     print_block(p2);
//     ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief free 1, then free 2. coalesce 1 and 2 together
//  * new size of 1 should be 1+2
//  *
//  */
// void free_coalesce_prev()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     Mem_Dump();
//     int ret = Mem_Free(p1);
//     print_block(p1);
//     printf("0 = %d\n", ret);

//     ret = Mem_Free(p2);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     printf("0 = %d\n", ret);
//     delete_heap();
// }

// void free_coalesce_next()
// {
//     generate_heap(48);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     Mem_Dump();
//     int ret = Mem_Free(p3);
//     print_block(p3);
//     printf("0 = %d\n", ret);

//     ret = Mem_Free(p2);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     printf("0 = %d\n", ret);
//     delete_heap();
// }

// /**
//  * @brief Coalesce three blocks in a row: prev, curr, next
//  * free at the top of the heap
//  *
//  */
// void free_coalesce_three_iar_top()
// {
//     generate_heap(160);
//     Mem_Dump();
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int ret = Mem_Free(p1);
//     printf("0 = %d\n", ret);
//     ret = Mem_Free(p2);
//     printf("0 = %d\n", ret);
//     ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     Mem_Dump();
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     delete_heap();
// }

// /**
//  * @brief free three in a row: prev, next, and curr
//  * free at the top of the heap
//  */
// void free_coalesce_three_prev_next_curr_top()
// {
//     generate_heap(96);
//     Mem_Dump();
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);

//     int ret = Mem_Free(p1);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     ret = Mem_Free(p2);
//     printf("0 = %d\n", ret);

//     // Mem_Dump();
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, prev, and curr
//  * free at the top of the heap
//  *
//  */
// void free_coalesce_three_next_prev_curr_top()
// {
//     generate_heap(96);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     Mem_Dump();
//     int ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);

//     ret = Mem_Free(p1);
//     printf("0 = %d\n", ret);
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);

//     ret = Mem_Free(p2);
//     printf("0 = %d\n", ret);

//     // Mem_Dump();
//     print_block(p1);
//     print_block(p2);
//     print_block(p3);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, prev, and curr
//  * free at the middle of the heap
//  *
//  */
// void free_coalesce_three_next_prev_curr_middle()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p4);
//     printf("0 = %d\n", ret);

//     // Mem_Dump();
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, prev, and curr
//  * free at the middle of the heap
//  *
//  */
// void free_coalesce_three_prev_curr_next_mid()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p4);
//     printf("0 = %d\n", ret);

//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: prev, next, curr
//  * free at the middle of the heap
//  *
//  */
// void free_coalesce_three_prev_next_curr_mid()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);

//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p4);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, curr, prev
//  * free at the middle of the heap
//  *
//  */
// void free_coalesce_three_next_curr_prev_mid()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p4);
//     printf("0 = %d\n", ret);

//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     ret = Mem_Free(p3);
//     printf("0 = %d\n", ret);
//     print_block(p3);
//     print_block(p4);
//     print_block(p5);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: prev, curr, next
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_prev_curr_next_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: prev, next, curr
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_prev_next_curr_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: curr, prev, next
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_curr_prev_next_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: curr, next, prev
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_curr_next_prev_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, prev, curr
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_next_prev_curr_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /**
//  * @brief Coalesce three in a row: next, curr, prev
//  * free at the end of the heap
//  *
//  */
// void free_coalesce_three_next_curr_prev_end()
// {
//     generate_heap(1600);

//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
//     int *p4 = Mem_Alloc(1);
//     int *p5 = Mem_Alloc(1);
//     int *p6 = Mem_Alloc(1);
//     int *p7 = Mem_Alloc(1);
//     Mem_Dump();

//     int ret = Mem_Free(p7);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p6);
//     printf("0 = %d\n", ret);

//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     ret = Mem_Free(p5);
//     printf("0 = %d\n", ret);
//     print_block(p5);
//     print_block(p6);
//     print_block(p7);

//     Mem_Dump();
//     delete_heap();
// }

// /* Testing a mixture of alloc and frees */
// /**
//  * @brief Malloc 3, free 3, malloc 3
//  *
//  */
// void t1(){
//     generate_heap(160);
//     int *p1 = Mem_Alloc(1);
//     int *p2 = Mem_Alloc(1);
//     int *p3 = Mem_Alloc(1);
// }

// void main()
// {
//     printf("easy alloc: \n");
//     alloc_easy();

//     printf("all alloc: \n");
//     alloc_all();

//     printf("no padding: \n");
//     alloc_no_padding();

//     printf("7 bytes of padding: \n");
//     alloc_smallest();

//     printf("15 bytes of padding: \n");
//     alloc_above_eight();

//     printf("split block: \n");
//     alloc_split();

//     printf("do not split block: \n");
//     alloc_no_split();

//     printf("allocate a sequence of 1s:\n");
//     alloc_seq1();

//     printf("allocate an increasing sequence: \n");
//     alloc_increasing_seq();

//     printf("allocate a decreasing sequence: \n");
//     alloc_decreasing_seq();

//     printf("adding after full: \n");
//     allocate_after_full();

//     printf("allocate 0: \n");
//     allocate_zero();

//     printf("----------------------------------------------------------------------------\n");
//     printf("free easy: \n");
//     free_valid();

//     printf("free null: \n");
//     free_null();

//     printf("free invalid: \n");
//     free_invalid();

//     printf("free head of heap: \n");
//     free_first();

//     printf("free ass of heap: \n");
//     free_last();

//     printf("free ptr never added to heap: \n");
//     free_not_in_heap();

//     printf("free everything: \n");
//     free_everything();

//     printf("coalesce prev and curr: \n");
//     free_coalesce_prev();

//     printf("coalesce curr and next: \n");
//     free_coalesce_next();

//     printf("free: coalesce three in a row (prev, curr, next): \n");
//     free_coalesce_three_iar_top();

//     printf("free: coalesce three in a row (prev, next, curr): \n");
//     free_coalesce_three_prev_next_curr_top();

//     printf("free: coalesce three in a row (next, prev, curr): \n");
//     free_coalesce_three_next_prev_curr_top();

//     printf("free: coalesce thee iar (next, prev, curr) middle OH: \n");
//     free_coalesce_three_next_prev_curr_middle();

//     printf("free: coalesce thee iar (prev, curr, next) middle OH: \n");
//     free_coalesce_three_prev_curr_next_mid();

//     printf("free: coalesce three iar (prev, next, curr) middle OH: \n");
//     free_coalesce_three_prev_next_curr_mid();

//     printf("free: coalesce three iar (next, curr, prev) middle OH: \n");
//     free_coalesce_three_next_curr_prev_mid();

//     printf("free: coalesce three iar (prev, curr, next) end OH: \n");
//     free_coalesce_three_prev_curr_next_end();

//     printf("free: coalesce three iar (prev, next, curr) end OH: \n");
//     free_coalesce_three_prev_next_curr_end();

//     printf("free: coalesce three iar (curr, prev, next) end OH: \n");
//     free_coalesce_three_curr_prev_next_end();

//     printf("free: coalesce three iar (curr, next, curr) end OH: \n");
//     free_coalesce_three_curr_next_prev_end();

//     printf("free: coalesce three iar (curr, next, curr) end OH: \n");
//     free_coalesce_three_curr_next_prev_end();

//     printf("free: coalesce three iar (next, prev, curr) end OH: \n");
//     free_coalesce_three_next_prev_curr_end();

//     printf("free: coalesce three iar (next, curr, prev) end OH: \n");
//     free_coalesce_three_next_curr_prev_end();
// }
