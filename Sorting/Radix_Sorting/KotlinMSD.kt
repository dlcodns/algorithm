import java.lang.Math.pow

fun Int.digits(): Int {
    var count = 0
    var num = this
    while (num != 0){
        count += 1
        num /= 10
    }
    return count
}

fun Int.digit(atPosition: Int): Int? {
    if(atPosition > digits()) return null
    var num = this
    val correctedPosition = (atPosition + 1).toDouble()
    while (num / (pow(10.0, correctedPosition).toInt()) != 0) {
        num /= 10
    }
    return num % 10
}

private fun radixSortMSD(list: MutableList<Int>, position: Int): MutableList<Int> {
    if(position > list.maxDigits()) return list

    val buckets = arrayListOf<MutableList<Int>>().apply {
        for(i in 0..9){
            this.add(arrayListOf())
        }
    }
    val priorityBucket = arrayListOf<Int>()
    list.forEach { number ->
        val digit = number.digit(position)
        if(digit == null){
            priorityBucket.add(number)
            return@forEach
        }
        buckets[digit].add(number)
    }
    priorityBucket.addAll(
        buckets.reduce { result, bucket ->
            if(bucket.isEmpty()) return@reduce result
            result.addAll(radixSortMSD(bucket, position+1))
            result
        })
    return priorityBucket
}

fun MutableList<Int>.lexicographicalSort() {
    val sorted = radixSortMSD(this, 0)
    this.clear()
    this.addAll(sorted)
}

private fun MutableList<Int>.maxDigits(): Int {
    return this.maxOrNull()?.digits() ?: 0
}

fun main(){
    val list = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    println("Original: $list")
    list.lexicographicalSort()
    println("Radix sorted: $list")
}
