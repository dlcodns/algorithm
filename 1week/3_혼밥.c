//[n-2]+[n-1]+1이 [n]의 답이다. 이때 1은 전에 안나왔던 0..0에 01을 더한 것

#include <stdio.h>

int main(){
  int n, a=1, b=2, c;
  scanf("%d", &n);

  if(n==1) printf("%d", a);
  else if(n==2) printf("%d", b);

  else {
    for(int i = 3; i<=n; i++){
      c = a + b + 1;
      a = b;
      b = c;
    }
    printf("%d", b);
  }
  
}
