fun <T: Comparable<T>> List<T>.mergeSort(): List<T> {
    if (this.size <2) return this
    //리스트의 반인덱스값
    val middle = this.size / 2

    val left = this.subList(0,middle).mergeSort()//왼쪽 재귀 설정
    val right = this.subList(middle, this.size).mergeSort() //오른쪽 재귀 설정

    //머지 수행한 값을 main에 리턴
    return merge(left, right)
}

private fun <T: Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
    var leftIndex = 0
    var rightIndex = 0

    val result = mutableListOf<T>()

    //하나의 사이즈라도 넘어가면 끝
    while (leftIndex < left.size &&rightIndex < right.size){
        val leftElement = left[leftIndex]
        val rightElement = right[rightIndex]
        if(leftElement < rightElement) {
            result.add(leftElement)
            leftIndex += 1
            //result에 더 작은 값 먼저 추가
        }
        else if(leftElement > rightElement) {
            result.add(rightElement)
            rightIndex += 1
            //result에 더 작은 값 먼저 추가
        }
        else{//같으면 둘 다 추가
            result.add(leftElement)
            leftIndex += 1
            result.add(rightElement)
            rightIndex += 1
        }
    }
    //왼쪽이 남으면
    if(leftIndex < left.size)
        //나머지 값 result에 추가
        result.addAll(left.subList(leftIndex, left.size))
    //반대로 진행
    if(rightIndex < right.size)
        result.addAll(right.subList(rightIndex, right.size))

    return result
}

fun main(){
    val list = listOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    val result = list.mergeSort()
    println("Merge sorted: $result")
}
