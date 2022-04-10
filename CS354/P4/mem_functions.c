#include "mem.h"
extern BLOCK_HEADER *first_header;
const int HEADER_SIZE = 8;

/**
 * @brief Return 0 for free, 1 for allocated
 *
 * @param header
 * @return int
 */
int Is_Alloc(BLOCK_HEADER *header)
{
    return (header->size_alloc) & 1;
}

/**
 * @brief Set the current header flag to ALLOCATED
 *
 * @param header
 */
void Set_Allocated(BLOCK_HEADER *header)
{
    if (Is_Alloc(header))
    {
        return;
    }

    header->size_alloc += 1;
}

/**
 * @brief Round size up to the nearest multiple of 16
 *
 * @param num = header + payload
 * @return int rounded up size
 */
int Add_Padding(int num)
{
    while (num % 16)
    {
        num++;
    }
    return num;
}

/**
 * @brief Returns 1 if header is the last, 0 otherwise
 *
 * @param header - header in question
 * @return int
 */
int Is_Last(BLOCK_HEADER *header)
{
    return header->size_alloc == 1;
}

int Get_Block_Size(BLOCK_HEADER *header)
{
    return header->size_alloc & 0xFFFFFFFE;
}

/**
 * @brief Increments the curr header to the next header and returns a pointer to the next header
 *
 * @param header
 * @return BLOCK_HEADER*
 */
BLOCK_HEADER *Get_Next_Header(BLOCK_HEADER *header)
{
    int block_size = Get_Block_Size(header);
    header = (BLOCK_HEADER *)((unsigned long)header + block_size);
    return header;
}

/**
 * @brief set the size_alloc and payload based on the payload size, clear alloc bit in the process
 *
 * @param header
 * @param size - payload
 */
void Set_Payload(BLOCK_HEADER *header, int size)
{
    header->size_alloc = Add_Padding(HEADER_SIZE + size);
    header->payload = size;
}


/**
 * @brief Set the current header flag to FREE
 *
 * @param header
 */
void Set_Free(BLOCK_HEADER *header)
{
    if (!Is_Alloc(header))
    {
        return;
    }

    header->size_alloc -= 1;
    Set_Payload(header, (Get_Block_Size(header) - HEADER_SIZE));
}
/**
 * @brief
 *
 * @param header
 * @return void*
 */
void *Get_User_Pointer(BLOCK_HEADER *header)
{
    return (BLOCK_HEADER *)((unsigned long)header + HEADER_SIZE);
}

BLOCK_HEADER *return_start()
{
    return first_header;
}

// return a pointer to the payload
// if a large enough free block isn't available, return NULL
void *Mem_Alloc(int size)
{
    // if the size is 0, do nothing
    if (size == 0)
    {
        return NULL;
    }
    // find a free block that's big enough
    int min_size = HEADER_SIZE + size;
    BLOCK_HEADER *curr_header = first_header;

    while (!Is_Last(curr_header))
    {
        // check allocation status and space
        if (!Is_Alloc(curr_header))
        {
            if (Get_Block_Size(curr_header) >= min_size)
            {
                // if found, break
                break;
            }
        }
        // increment
        curr_header = Get_Next_Header(curr_header);
    }

    // return NULL if we didn't find a block
    if (Is_Last(curr_header))
    {
        return NULL;
    }
    // hold onto the amount of free space in the memory spot
    int free_space = Get_Block_Size(curr_header);

    // allocate the block
    Set_Payload(curr_header, size);
    // set the allocated flag to ALLOCATED
    Set_Allocated(curr_header);

    // get the total size
    int allocated_size = Get_Block_Size(curr_header);
    // split if necessary (if padding size is greater than or equal to 16 split the block)
    if (free_space - allocated_size > 0)
    {
        // increment past the newly allocated block
        BLOCK_HEADER *new_free = Get_Next_Header(curr_header);
        // set the size_alloc to the new free size and allocated flag to FREE/0
        Set_Payload(new_free, (free_space - allocated_size - HEADER_SIZE));
    }

    return Get_User_Pointer(curr_header);
}

// return 0 on success
// return -1 if the input ptr was invalid
int Mem_Free(void *ptr)
{

    // traverse the list and check all pointers to find the correct block
    BLOCK_HEADER *prev_header = NULL;
    BLOCK_HEADER *curr_header = first_header;

    while (!Is_Last(curr_header))
    {
        if (Get_User_Pointer(curr_header) == ptr)
        {
            break;
        }
        prev_header = curr_header;
        curr_header = Get_Next_Header(curr_header);
    }
    // if you reach the end of the list without finding it return -1
    if (Is_Last(curr_header))
    {
        return -1; // fail
    }
    // free the block
    Set_Free(curr_header);

    // coalesce adjacent free blocks
    BLOCK_HEADER *next_header = Get_Next_Header(curr_header);
    if (!Is_Last(next_header))
    {
        if (!Is_Alloc(next_header))
        {
            Set_Payload(curr_header, (Get_Block_Size(curr_header) + Get_Block_Size(next_header) - HEADER_SIZE));
        }
    }

    if (prev_header != NULL)
    {
        if (!Is_Alloc(prev_header))
        {
            Set_Payload(prev_header, (Get_Block_Size(curr_header) + Get_Block_Size(prev_header) - HEADER_SIZE));
        }
    }

    return 0;
}
