import java.util.*
import kotlin.collections.ArrayList

//heap을 위한 여러 변수와 함수 설정
interface MyCollection<Element> {
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun insert(element: Element)
    fun remove(): Element?
    fun remove(index: Int): Element?
}

interface HeapInterface<Element> : MyCollection<Element> {
    fun peek(): Element?
}

abstract class AbstractHeap<Element>() : HeapInterface<Element> {
    var elements: ArrayList<Element> = ArrayList<Element>()
    override val count: Int
        get() = elements.size

    override fun peek(): Element? = elements.first()
    //왼쪽 자식 인덱스
    private fun leftChildIndex(index: Int) = (2*index)+1
    //오른쪽 자식 인덱스
    private fun rightChildIndex(index: Int) = (2*index)+2
    //부모 노드 인덱스
    private fun parentIndex(index: Int) = (index - 1)/2

    abstract fun compare(a: Element, b: Element): Int

    override fun insert(element: Element) {
        //insert는 element를 삽입하고 SiftUp 시키는 함수로 override
        elements.add(element)
        SiftUp(count - 1)
    }

    //위로 올리는 함수
    private fun SiftUp(index: Int){
        var child = index
        var parent = parentIndex(child)
        //자식 요소가 부모 노드 요소보다 값이 적을 때만
        while (child > 0 && compare(elements[child], elements[parent]) > 0){
            //자식과 부모 위치 swap
            Collections.swap(elements, child, parent)
            child = parent
            //부모 위치 새로 지정
            parent = parentIndex(child)
        }
    }

    //아래로 내리는 함수
    private fun SiftDown(index: Int) {
        var parent = index
        while(true){
            val left = leftChildIndex(parent)
            val right = rightChildIndex(parent)
            var candidate = parent
            //왼쪽 자식 노드가 부모 노드값보다 클 때 left로 이동
            if(left < count && compare(elements[left], elements[candidate]) > 0){
                candidate = left
            }
            //오른쪽 자식 노드가 부모 노드값보다 클 때 right로 이동
            if(right < count && compare(elements[right], elements[candidate]) > 0){
                candidate = right
            }
            //같을 때 반환(끝)
            if(candidate == parent)
                return
            //부모와 후보의 위치 교환
            Collections.swap(elements, parent, candidate)
            parent = candidate
        }
    }

    //제일 큰 노드를 제거
    override fun remove(): Element? {
        if (isEmpty) return null
        //제일 큰 노드와 마지막 노드의 요소를 swap, 마지막 노드에 있을 큰 값 제거
        Collections.swap(elements, 0 , count-1)
        val item = elements.removeAt(count-1)
        //root에 있을 요소를 siftdown
        SiftDown(0)
        return item
    }

    //특정 노드를 제거
    override fun remove(index: Int): Element? {
        if (index >= count) return null
        return if (index == count - 1) {
            elements. removeAt(count - 1 )
        }
        else{
            //특정 노드와 마지막 노드 swap, 마지막 노드에 있을 특정 값 제거
            Collections.swap(elements, index, count - 1)
            val item = elements.removeAt(count -1)
            //index에 대해 siftup, siftdown 수행하면 정렬 끝
            SiftDown(index)
            SiftUp(index)
            item
        }
    }

    //임의의 값을 가진 아이템이 heap의 몇 번째 index에 존재하는지 찾는 함수
    private fun index(element: Element, i: Int): Int? {
        if(i>=count)    //index를 넘으면 null
            return null
        if(compare(element, elements[i]) > 0)   //임의의 값이 더 크면 null
            return null
        if(element == elements[i])  //같으면 i번째에 있는 것
            return i

        //
        val leftChildIndex = index(element,leftChildIndex(i))
        if(leftChildIndex != null) return leftChildIndex
        val rightChildIndex = index(element, rightChildIndex(i))
        if(rightChildIndex != null) return rightChildIndex
        return null
    }

    //임의의 array를 heap 객체로 만들어주는 함수
    fun heapify(values: ArrayList<Element>) {
        elements = values

        if(!elements.isEmpty()){
            (count / 2 downTo 0).forEach {
                SiftDown(it)
            }
        }
    }
}

//root가 제일 큰 힙 구현
class MaxHeap<Element: Comparable<Element>>() : AbstractHeap<Element>() {
    override fun compare(a: Element, b: Element): kotlin.Int{
        return a.compareTo(b)
    }
}
//root가 제일 작은 힙 구현
//class MinHeap<Element: Comparable<Element>>() : AbstractHeap<Element>() {
//    override fun compare(a: Element, b: Element): kotlin.Int {
//        return b. compareTo(a)
//    }
//}

fun main(){
    var array = arrayListOf(6,3,8,4,2,1,9,5,0,7)
    val h = MaxHeap<Int>()
    //val h = MinHeap<Int>()
    h.heapify(array)
    while(!h.isEmpty){
        println(h.remove())
    }
}
