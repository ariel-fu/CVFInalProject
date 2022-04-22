#include <stdio.h>
#include <string.h>


void To_Upper(char *str);
  
int main() {
    char str[50];
	printf("test 1\n");
    strcpy(str, "cs 354 is Awesome!");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 2\n");
	strcpy(str, "ARE WE SURE THIS IS WORKING?!");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 3\n");
	strcpy(str, "are you sure ariel? really sure?");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 4\n");
	strcpy(str, "1234567890 1234567890 1234567890 1234567890");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 5\n");
	strcpy(str, "!@#$%^&*() !@#$%^&*() !@#$%^&*() !@#$%^&*()");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
    return 0;
}



