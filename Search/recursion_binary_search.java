public class Main {

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 10, 40};
        int target = 10;
        int result = binarySearch(array, 0, array.length - 1, target);

        System.out.println("--재귀적 호출을 통한 이진 탐색--\n");
        if (result == -1) {
            System.out.println("배열에 "+target+"이 없습니다.");
        } else {
            System.out.println(target+"은 index " + result+"에 있습니다.");
        }
    }

    public static int binarySearch(int[] array, int left, int right, int target) {
        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                return binarySearch(array, left, mid - 1, target);
            } else {
                return binarySearch(array, mid + 1, right, target);
            }
        }

        return -1;
    }


}
