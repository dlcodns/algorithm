fun <T> ArrayList<T>.swapAt(first: Int, second: Int){
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}

fun <T: Comparable<T>> ArrayList<T>.bubbleSort(showPasses: Boolean = false){
    if(this.size < 2) return    //숫자가 하나면 리턴

    for (end in (1 until this.size).reversed()){    //뒤에서 1까지 돌기
        var swapped = false
        for (current in 0 until end){    //0에서 end까지 돌기, 완료 시 end가 하나씩 줄어들거임
            if(this[current] > this[current+1]){    //앞 원소가 더 크면
                this.swapAt(current,current+1)      //swap
                swapped = true
            }
        }
        if(showPasses) println(this)    //한 turn의 pass를 할 때 마다 print
        if(!swapped) return             //바뀐 게 없는 건 이미 정렬돼있다는 뜻
    }
}

fun main(args: Array<String>) {
    val list = arrayListOf(9,4,10,3)
    println("Original: $list")
    list.bubbleSort(true)
    println("Bubble sorted: $list")
}
