#include <stdio.h>

int main(){
  int n, score;
  scanf("%d", &n);
  char arr[n];
  for(int i=0; i<n; i++){
    scanf("%d",&score);
    if(score<=100 && score>=90)
      arr[i] = 'A';
    else if(score>=80)
      arr[i] = 'B';
    else if(score>=70)
      arr[i] = 'C';
    else if(score>=60)
      arr[i] = 'D';
    else
      arr[i] = 'F';
  }
  for(int i=0; i<n;i++){
    printf("%c\n", arr[i]);
  }
  return 0;
}
