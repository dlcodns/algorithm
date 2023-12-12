fun <T> ArrayList<T>.swapAt(first: Int, second: Int){
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}

fun <T: Comparable<T>> ArrayList<T>.selectionSort(showPasses: Boolean = false){
    if(this.size < 2) return    //숫자가 하나면 리턴

    for (current in 0 until (this.size-1)){    //0~size-1까지 돌기
        var lowest = current                   //최솟값 비교 및 저장 위해 선언
        for (other in (current+1) until this.size){    //current+1~size까지 돌기
            if(this[lowest] > this[other]){            //더 작으면 대입
                lowest = other
            }
        }
        if(lowest != current){            //그대로면 그냥 skip, 다르면 current와 swap
            this.swapAt(lowest,current)
        }
        if(showPasses) println(this)
    }
}

fun main() {
    val list = arrayListOf(9,4,10,3)
    println("Original: $list")
    list.selectionSort(true)
    println("Selection sorted: $list")
}
