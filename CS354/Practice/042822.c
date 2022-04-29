
void main(){
	short a = 1;
	short b = 2;
	short *p = &b;

	if(a && *p < a){
		*p = a;
	}
}
