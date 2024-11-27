import pt.isel.canvas.BLACK
import pt.isel.canvas.Canvas

/**
 * A Vector2 is a two-dimensional class that contains an [x] value
 * and an [y] value. Is supports operations with constants or Vectors like
 * addition, subtraction, multiplication or division. For the operations
 * use the Kotlin default operators.
 */

data class Vector2(var x: Int, var y: Int) {
    operator fun plus(a: Int) = Vector2(x + a, y + a)
    operator fun plus(a: Vector2) = Vector2(x + a.x, y + a.y)

    operator fun minus(a: Int) = Vector2(x - a, y - a)
    operator fun minus(a: Vector2) = Vector2(x - a.x, y - a.y)

    operator fun times(a: Int) = Vector2(x * a, y * a)
    operator fun times(a: Vector2) = Vector2(x * a.x, y * a.y)

    operator fun div(a: Int) = Vector2(x / a, y / a)
    operator fun div(a: Vector2) = Vector2(x / a.x, y / a.y)
}

/**
 * Enumerate containing the 4 basic two-dimensional directions:
 * [UP], [DOWN], [LEFT] and [RIGHT].
 */

enum class Direction {UP, DOWN, LEFT, RIGHT}

data class Cells(val grid: Vector2, val size: Int) {
    val normalize= grid.div(2).times(size*2)
    val center = normalize.div(2)
    val total = grid.x * grid.y

    fun createWindow() = Canvas(normalize.x,normalize.y,BLACK)

    fun emptyCells(): List<Vector2>{
        var tempList = emptyList<Vector2>()
        for(x in 0 until grid.x)
            for(y in 0 until grid.y)
                tempList += Vector2(x, y).times(size)
        return tempList
    }
}

// TODO: Port the debug mode from the last version
