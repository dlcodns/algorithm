#include <stdio.h>

int main(){
  int n, count=0;
  scanf("%d",&n);
  for(int i=1;i<=n;i++){
    for(int j=1;j<=n;j++){
      if(count % 2 == 0) printf("*");
      else printf(" ");
      count++;
    }
    if(n % 2 == 0) count++;
    printf("\n");
  }
  return 0;
}
