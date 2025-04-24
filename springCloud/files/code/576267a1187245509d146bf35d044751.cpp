#include<iostream>
int main(){
  int a = 0;
  short* b = (char*)(&a) + 2;//指针进行移动
  print("%d",b)
  *b = 1;
  print("%d",b)
}