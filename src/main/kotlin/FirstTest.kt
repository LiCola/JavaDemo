import com.licola.llogger.LLogger

/**
 *
 * @author LiCola
 * @date 2019-05-22
 */

fun main() {
    println("Hello Kotlin")
    LLogger.init()
    LLogger.d("运行")
    Thread(Runnable {
        LLogger.d("内部")
    }).start()

    var mainKotlin = MainKotlin("first", 20)
    var secondKotlin = MainKotlin("second")
    LLogger.d(mainKotlin.name, mainKotlin.age)
    LLogger.d(secondKotlin.name, secondKotlin.age)

    val b:String?=null

    var size=b?.length
    LLogger.d(size)

//    throw RuntimeException("抛出异常")
}

class MainKotlin constructor(var name: String, var age: Int) {

    constructor(name: String) : this(name, 20) {

    }
}