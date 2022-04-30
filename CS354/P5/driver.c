#include <stdio.h>
#include <string.h>


void To_Upper(char *str);

void p1_whitespace_test(){
	char str[150];
	printf("test 1\n");
	strcpy(str, "   ajdshfosajdhfo");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 1\n");
	strcpy(str, "			   			   asfewfbjbuisd");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 2\n");
	strcpy(str, "		 	 asfewnbvoupwn");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 3\n");
	strcpy(str, "thIs");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 4\n");
	strcpy(str, "ajdshfosajdhfo   ");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 5\n");
	strcpy(str, "asfewfbjbuisd					    \n");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 6\n");
	strcpy(str, "asfewnbvoupwn	 		        \n");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 7\n");
	strcpy(str, "  				    		          this             is                         complicated		");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 8\n");
	strcpy(str, "    this            too                             		\n");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 9\n");
	strcpy(str, "this is clean");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 10\n");
	strcpy(str, "clean");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
}

void p1_test(){
	char str[50];
	printf("test 1\n");
	strcpy(str, "thIs");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 2\n");
	strcpy(str, "wORd2");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);

	printf("test 3\n");
	strcpy(str, "wORD3");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);	
	
	printf("test 4\n");
	strcpy(str, "MIKE");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 5\n");
	strcpy(str, "this is word");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 6\n");
	strcpy(str, "WORDS HERE TOO");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 7\n");
	strcpy(str, "jack EdUARdo zIMM");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 8\n");
	strcpy(str, "boOOgy WOOgy zIggYY ZagZAG");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 9\n");
	strcpy(str, "Mike Zimmerman");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 9\n");
	strcpy(str, "Boom Boom");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
}

void random(){
		/**
    char str[50];
		printf("test 6\n");
	//strcpy(str, NULL);
    printf("Original string:   %s\n",NULL);
    To_Upper(NULL);
    printf("\n");
    printf("Upper case string: %s\n",NULL);
	
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
	

	
	printf("test 7\n");
	strcpy(str, "");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 8\n");
	strcpy(str, "a");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 8\n");
	strcpy(str, "A");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 9\n");
	strcpy(str, "`a{");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	printf("test 10\n");
	strcpy(str, "`~(){}[]|;:<>/?");
    printf("Original string:   %s\n",str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n",str);
	
	**/
	}
	
void test_nonalpha(){
	char str[50];
	printf("test 1\n");
	strcpy(str, "!@#$%^&*()");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	
	printf("test 2\n");
	strcpy(str, "~``~");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);

	printf("test 3\n");
	strcpy(str, "-_=+");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);	
	
	printf("test 4\n");
	strcpy(str, "{}[]");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 5\n");
	strcpy(str, "||");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 6\n");
	strcpy(str, ":;""''");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 7\n");
	strcpy(str, ",<.>/?");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 8\n");
	strcpy(str, "\t\t\t");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 9\n");
	strcpy(str, "\n\n\n");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
	
	printf("test 9\n");
	strcpy(str, "\0\0");
    printf("Original string:   %s\n", str);
    To_Upper(str);
    printf("\n");
    printf("Upper case string: %s\n", str);
}


int main() {
	//p1_whitespace_test();
	//p1_test();
	test_nonalpha();
	
	
    return 0;
}



