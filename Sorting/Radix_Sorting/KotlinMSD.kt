import java.lang.Math.pow

//자릿수를 파악하는 함수
fun Int.digits(): Int {
    var count = 0
    var num = this
    while (num != 0){//만약 num이 0이 아닐 때 자릿수 파악하기
        count += 1
        num /= 10
    }
    return count
}

//해당 위치의 숫자를 리턴
fun Int.digit(atPosition: Int): Int? {
    if(atPosition > digits()) return null  //자릿수보다 파라미터값이 크면 null 반환
    var num = this
    val correctedPosition = (atPosition + 1).toDouble()
    while (num / (pow(10.0, correctedPosition).toInt()) != 0) {
        num /= 10   //해당 위치값과 왼쪽 값들만 남김
    }
    return num % 10 //이제 위치값만 남음
}

//MSD : 큰 자릿수부터 정렬시작
private fun radixSortMSD(list: MutableList<Int>, position: Int): MutableList<Int> {
    if(position > list.maxDigits()) return list //포지션이 제일 큰 자릿수보다 크면 반환

    //0~9까지 버킷 생성
    val buckets = arrayListOf<MutableList<Int>>().apply {
        for(i in 0..9){
            this.add(arrayListOf())
        }
    }
    val priorityBucket = arrayListOf<Int>()
    list.forEach { number ->
        val digit = number.digit(position)
        //position(자릿수)보다 자릿수가 작으면 우선순위 버킷에 추가
        if(digit == null){
            priorityBucket.add(number)
            return@forEach
        }
        //position보다 자릿수가 같거나 크면 버킷에 추가
        buckets[digit].add(number)
    }
    //아래 조건에 따라 나머지 값을 버킷에 추가
    priorityBucket.addAll(
        buckets.reduce { result, bucket ->
            //버킷이 비어있으면 반환
            if(bucket.isEmpty()) return@reduce result
            //아니면 다음 자릿수를 정렬하고 result에 추가추가추가(재귀)
            result.addAll(radixSortMSD(bucket, position+1))
            result
        })
    return priorityBucket
}

//사전 찾기 동작(정렬)
fun MutableList<Int>.lexicographicalSort() {
    val sorted = radixSortMSD(this, 0)
    this.clear()
    this.addAll(sorted)
}

//가장 큰 자릿수를 반환
private fun MutableList<Int>.maxDigits(): Int {
    return this.maxOrNull()?.digits() ?: 0
}

fun main(){
    val list = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    list.lexicographicalSort()
    println("Radix sorted: $list")
}
