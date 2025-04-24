#include<stdio.h>
int StringCompare(const char* s1, const char* s2) {
    while (*s1 && *s1 == *s2) {
        s1++;
        s2++;
    }
    return (*s1 > *s2) ? 1 : (*s1 < *s2) ? -1 : 0;
}
int main(){
    int s1=StringCompare("abc","abc");
    printf("%s %s %d\n","abc","abc",s1);
    int s2=StringCompare("abc","cba");
    printf("%d\n",s2);
    int s3=StringCompare("def","abc");
    printf("%d\n",s3);
}