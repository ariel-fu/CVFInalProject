void Reverse(char *str)
{

    // start with two pointers - one to the front and one to the end
    char *forP = str;
    char *endP = str;
    while (*str)
    {
        str++;
        endP++;
    }
    // decrement the last pointer to point to the last non '\0' character
    endP--;
    // swap
    while (forP < endP)
    {
        char temp = *forP;
        *forP = *endP;
        *endP = temp;
        forP++;
        endP--;
    }
}