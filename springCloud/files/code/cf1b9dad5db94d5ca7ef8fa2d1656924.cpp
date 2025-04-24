#include<iostream>
int main(){
  int a = 5;
  void* b = &a;
  *b = 6;
}