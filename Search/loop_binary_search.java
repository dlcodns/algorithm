public class Main {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 10, 40};
        int target = 10;
        int result = binarySearch(array, target);

        System.out.println("--재귀적 호출을 통한 이진 탐색--\n");
        if (result == -1) {
            System.out.println("배열에 "+target+"이 없습니다.");
        } else {
            System.out.println(target+"은 index " + result+"에 있습니다.");
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;  //배열 마지막 인덱스

        while (left <= right) {  //검색 다 할 때까지
            int mid = left + (right - left) / 2;  //중간 인덱스 값

            if (array[mid] == target) {  //만약 중앙이 타겟이면 바로 반환
                return mid;
            }
            if (array[mid] < target) {  //만약 아니면 수 1씩 바꾸면서 계속 돌기
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
