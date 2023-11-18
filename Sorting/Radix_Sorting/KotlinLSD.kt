//LSD : 작은 자릿값부터 정렬 시작
fun MutableList<Int>.radixSortLSD() {
    val base = 10   //십진수
    var done = false
    var digits = 1  //1의 자리부터 시작
    //done이 true가 되면 stop
    while (!done) {
        done = true
        //0~9까지 버킷 생성
        val buckets = arrayListOf<MutableList<Int>>().apply {
            for(i in 0..9) {
                this.add(arrayListOf())
            }
        }
        this.forEach {
                number ->
            //remainingPart는 digits 자릿수 값까지만 포함한 남은 자릿수임
            val remainingPart = number / digits
            val digit = remainingPart % base    //현재 자릿수의 값 구하기
            buckets[digit].add(number)  //자릿수 값에 해당하는 버킷에 저장
            if(remainingPart > 0) {
                done = false
            }
        }
        digits *= base  //1, 10, 100, 1000
        this.clear()
        this.addAll(buckets.flatten())
    }
}

fun main(){
    val list = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    list.radixSortLSD()
    println("Radix sorted: $list")
}
