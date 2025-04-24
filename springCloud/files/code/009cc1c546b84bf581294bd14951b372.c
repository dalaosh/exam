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
    printf("%d",s1);
    int s1=StringCompare("abc","cba");
    printf("%d",s1);
    int s1=StringCompare("def","abc");
    printf("%d",s1);
}