#include<iostream>
using namespace std;
int main(){
  int a = 0;
  short* b = (char*)(&a) + 2;//指针进行移动
  cout<<b;
  *b = 1;
  cout<<b;
}