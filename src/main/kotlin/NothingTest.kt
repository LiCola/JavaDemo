import com.licola.llogger.LLogger
import java.util.ArrayList

/**
 *
 * @author LiCola
 * @date 2019-05-30
 */

fun main(){

    LLogger.init()

    var name="name"
    val s=name?:throw IllegalArgumentException("throw")
    LLogger.d(s)

}