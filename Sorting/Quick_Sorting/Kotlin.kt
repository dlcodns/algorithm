//두 값을 swap하는 함수
fun <T> ArrayList<T>.swapAt(first: Int, second: Int){
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}

//조건 하에 값이 바뀌는 부분
fun <T: Comparable<T>> ArrayList<T>.split(k: Int, m: Int): Int{
    val pivot = this[k] //this의 첫번째 값을 pivot으로 지정
    var le = k+1    //this[1]=left
    var rig = m     //this[9] = right

    while (le<=rig){
        //왼쪽 요소가 피봇보다 작거나 같으면 해당 요소의 인덱스값 1 증가 반복
        //피봇보다 큰 값을 찾는 작업
        while(le <= rig && this[le] <= pivot)
            le++
        //오른쪽 요소가 피봇보다 크거나 같으면 해당 요소의 인덱스값 1 감소 반복
        //피봇보다 작은 값을 찾는 작업
        while(le <= rig && this[rig] >= pivot)
            rig--
        //만약 left가 right보다 작으면
        if(le < rig)
            //this[le]가 this[rig]보다 클테니까
            swapAt(le, rig)
        else
            break
    }
    //left right 정렬이 끝난 후 k를 그 중간으로 이동시키고 rig를 새로운 pivot으로 설정
    swapAt(k,rig)
    return rig
}

//퀵을 수행하는 재귀함수
fun <T: Comparable<T>> ArrayList<T>.quickSort(k: Int, m: Int){
    if(this.size < 2) return
    //this 요소가 2개 이상일 때만
    if(k<m){
        val i = this.split(k, m)    //left right 분할
        this.quickSort(k, i-1)  //피봇 앞뒤로 솔팅 시작 (재귀)
        this.quickSort(i+1, m)
    }
}

fun main() {
    val list = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    list.quickSort(0,list.size - 1)
    println("Quick sorted: $list")
}
