#include <stdio.h>

// THE CACHE
struct LINE
{
    char valid;
    int tag;
    char data[32];
};

struct SET
{
    struct LINE line[1];
};

struct CACHE
{
    struct SET set[8];
} cache;

// GLOBALDATA
// increment these in your function
unsigned hit_count = 0;
unsigned miss_count = 0;
unsigned read_data_count = 0;

// SYSTEM BUS
// call Read_Data_From_Ram to update this
char system_bus[32];

// READ DATA FROM RAM
// this function copies 32 character from the text file to
// the system_bus array aligned with the system bus
// (we will not test this with input larger than the text file)
void Read_Data_From_Ram(unsigned address)
{
    address >>= 5;
    address <<= 5; // get align the data
    read_data_count++;
    FILE *file;
    file = fopen("alice_in_wonderland.txt", "r");
    fseek(file, address, SEEK_SET);
    for (int i = 0; i < 32; i++)
        system_bus[i] = fgetc(file);

    // fclose(file);
    return;
}

/* Helper functions:

    Get_Bit_Offset: gets the bit offset bits from an address
    Get_Set: gets the set bits from an address
    Get_Tag: gets the tag from an address
    Is_Valid: checks if the LINE's valid bit is set (=1)
    Tag_Matches: checks if the address' tag and the LINE's tag matches
 */

/**
 * @brief Extracts the rightmost 5 bits of the address
 *
 * @param address
 * @return int - bit offset bits
 */
int Get_Bit_Offset(unsigned address)
{
    // perform a bitwise AND with the address
    int offset = address & 31;
    return offset;
}

/**
 * @brief Extracts the bits that define the set
 *
 * @param address
 * @return int - bits that define the set
 */
int Get_Set(unsigned address)
{
    // right shift the adddress by 5 to remove the bit offset bits
    int set_bits = address >> 5;
    // perform a logical AND with the shifted address
    set_bits = set_bits & 7;
    return set_bits;
}

/**
 * @brief Extracts the bits that define the tag
 *
 * @param address
 * @return int - bits that define the tag
 */
int Get_Tag(unsigned address)
{
    // right shift the address by 8 to remove the bit offset and set bits.
    int tag_bits = address >> 8;
    return tag_bits;
}

// COMPLETE THE FOLLOWING FUNCTION
//
// this code should check to see if the data in the cache is valid and the tag matches
// if so it should return the char matching the address and increment the hit count
//
// if not it should read the data from the system bus copy it into the cache and
// increment the miss count and return the char matching the address
//
char Read_Data_From_Cache(unsigned address)
{
    // write your code here
    // 1. get the offset, set, and tag
    int address_offset = Get_Bit_Offset(address);
    int address_set = Get_Set(address);
    int address_tag = Get_Tag(address);

    // 2. go to the set in the cache & get the line
    struct SET set = cache.set[address_set];
    struct LINE line = set.line[0];

    // 3. if invalid or the tags do not match, load new data
    if (!line.valid || (address_tag != line.tag))
    {
        // increment the misses
        miss_count++;
        // read from the RAM
        Read_Data_From_Ram(address);
        // copy the system_bus to the line's char data array
        for (int i = 0; i < 32; i++)
        {
            cache.set[address_set].line[0].data[i] = system_bus[i];
        }
        // set the line to valid now that it has data in it
        cache.set[address_set].line[0].valid = 1;
        // set the tag
        cache.set[address_set].line[0].tag = address_tag;
        // get the char matching the address
        char cache_data = cache.set[address_set].line[0].data[address_offset];
        return cache_data;
    }

    // 4. extract data using offset
    char cache_data = line.data[address_offset];
    // 5. increment hit count
    hit_count++;
    // 6. return data
    // replace the hardcoded 'a' below
    return cache_data;
}

int main()
{
    // INITIALIZE CACHE
    for (int i = 0; i < 8; i++)
        cache.set[i].line[0].valid = 0;

    // READ SOME DATA
    char c;
    // char message[34];
    // int counter = 0;
    // for (int i = 10000; i < 60000; i++)
    // {
    //     c = Read_Data_From_Cache(i);
    //     printf("%c", c);
    // }

    for (int i = 0; i < 10; i++)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 50; i += 10)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 500; i += 32)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 64; i += 16)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 1000; i += 100)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 10000; i += 1000)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 100; i += 31)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 100; i += 29)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 100; i += 15)
    {
        c = Read_Data_From_Cache(i);

        printf("Reading character at index %d   : data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, c, hit_count, miss_count, read_data_count);
    }

    for (int i = 0; i < 30000; i++)
    {
        c = Read_Data_From_Cache(i);
        printf("%c", c);
    }

    // write the initial data
    // for (int set_count = 0; set_count < 8; set_count++)
    // {
    //     for (int i = 0; i < 32; i++)
    //     {
    //         c = Read_Data_From_Cache(counter * 32 + i);
    //         message[i] = c;
    //         // printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    //         //  printf("i = %d: hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, hit_count, miss_count, read_data_count);
    //     }
    //     message[33] = '\0';
    //     printf("message: %s\n", message);
    //     counter++;
    //     // verify set (set_count+1) - 8 are still invalid
    //     for (int i = (set_count + 1); i < 8; i++)
    //     {
    //         if (cache.set[i].line[0].valid != 0)
    //         {
    //             printf("error on set %d\n", i);
    //         }
    //     }
    // }
    // overwrite the data
    // for (int set_count = 0; set_count < 8; set_count++)
    // {
    //     for (int i = 0; i < 32; i++)
    //     {
    //         c = Read_Data_From_Cache(counter * 32 + i);
    //         message[i] = c;
    //         // printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    //         //  printf("i = %d: hit count = %-3u : miss count = %-3u : read data count = %-3u\n", i, hit_count, miss_count, read_data_count);
    //     }
    //     message[33] = '\0';
    //     printf("message: %s\n", message);
    //     counter++;
    //     // verify set (set_count) - 8 are still valid
    //     for (int i = (set_count + 1); i < 8; i++)
    //     {
    //         if (cache.set[i].line[0].valid != 1)
    //         {
    //             printf("error on set %d\n", i);
    //         }
    //     }
    // }

    /**
    c = Read_Data_From_Cache(0);
    printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    c = Read_Data_From_Cache(1);
    printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    c = Read_Data_From_Cache(2);
    printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    c = Read_Data_From_Cache(3);
    printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    c = Read_Data_From_Cache(4);
    printf("data = %c : hit count = %-3u : miss count = %-3u : read data count = %-3u\n", c, hit_count, miss_count, read_data_count);
    **/

    // WRITE A LOT MORE TESTS

    return 0;
}
