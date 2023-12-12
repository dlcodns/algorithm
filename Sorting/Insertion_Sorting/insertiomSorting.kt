fun <T> ArrayList<T>.swapAt(first: Int, second: Int){
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}

fun <T: Comparable<T>> ArrayList<T>.insertionSort(showPasses: Boolean = false){
    if(this.size < 2) return  //숫자가 하나면 리턴

    for (current in 1 until this.size){                //1~size만큼 돌기
        for (shifting in (1 .. current).reversed()){   //current~1만큼 돌기
            if(this[shifting] < this[shifting-1]){     //오른쪽이 더 작으면 swap
                this.swapAt(shifting, shifting-1)
            } else{                                    //정렬되어 있으면 냅두기
                break
            }
        }
        if(showPasses) println(this)
    }
}

fun main() {
    val list = arrayListOf(9,4,10,3)
    println("Original: $list")
    list.insertionSort(true)
    println("Insertion sorted: $list")
}
