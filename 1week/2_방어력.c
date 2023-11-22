#include <stdio.h>
#include <math.h>

int main(void) {
  int T,a,d,t;
  float s;
  scanf("%d",&T);
  float arr[T];

  for(int i=0; i<T;i++){
    scanf("%d%f%d%d", &a,&s,&d,&t);
    arr[i] = a*(100.0/(100+d))*floorf(s*t);
  }
  for(int i=0;i<T;i++){
    printf("%.1f\n",roundf(arr[i]*10)/10);
  }
  
  return 0;
}
