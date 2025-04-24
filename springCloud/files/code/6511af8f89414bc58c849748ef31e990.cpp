#include<iostream>
using namespace std;
int main(){
  int a = 0;
   char* p = (char*)&a + 1;
   *p = 1;
   cout<<a;
}