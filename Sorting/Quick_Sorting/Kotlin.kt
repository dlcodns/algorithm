fun <T> ArrayList<T>.swapAt(first: Int, second: Int){
    val aux = this[first]
    this[first] = this[second]
    this[second] = aux
}

fun <T: Comparable<T>> ArrayList<T>.split(k: Int, m: Int): Int{
    val pivot = this[k]
    var le = k+1
    var rig = m

    while (le<=rig){
        while(le <= rig && this[le] <= pivot)
            le++
        while(le <= rig && this[rig] >= pivot)
            rig--
        if(le < rig)
            swapAt(le, rig)
        else
            break
    }
    swapAt(k,rig)
    return rig
}

fun <T: Comparable<T>> ArrayList<T>.quickSort(k: Int, m: Int){
    if(this.size < 2) return

    if(k<m){
        val i = this.split(k, m)
        this.quickSort(k, i-1)
        this.quickSort(i+1, m)
    }
}

fun main() {
    val list = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    list.quickSort(0,list.size - 1)
    println("Quick sorted: $list")
}
