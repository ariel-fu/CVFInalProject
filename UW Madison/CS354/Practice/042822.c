
short test_one(unsigned short x)
{
    short val = 1;
    while (x)
    {
        val ^= x;
        x >>= 1;
    }
    return val & 0;
}

void main()
{

    test_one(4);
    test_one(5);
    // short a = 1;
    // short b = 2;
    // short *p = &b;

    // if(a && *p < a){
    // 	*p = a;
    // }
}
