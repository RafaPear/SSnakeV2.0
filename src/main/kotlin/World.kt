import pt.isel.canvas.*

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
fun Direction.inverted() = when (this) {
    Direction.UP -> Direction.DOWN
    Direction.DOWN -> Direction.UP
    Direction.LEFT -> Direction.RIGHT
    Direction.RIGHT -> Direction.LEFT
}

/**
 * Creates the Screen class for a cleaner code. The [grid] parameter is a
 * position class that contains an amount of cells on the x-axis and
 * on the y-axis. The [size]
 * parameter is the pixel size
 */

data class Cells(val grid: Vector2, val size: Int) {
    val normalize= grid.div(2).times(size*2)
    val center = normalize.div(2)
    val square = Vector2(size,size)
    val total = grid.x * grid.y

    fun createWindow() = Canvas(normalize.x,normalize.y + size*2,BLACK)

    fun emptyCells(): List<Vector2>{
        var tempList = emptyList<Vector2>()
        for(x in 0 until grid.x-1)
            for(y in 0 until grid.y-1)
                tempList += Vector2(x, y).times(size)
        return tempList
    }
}

data class Button(
    val pos: Vector2,
    val size: Vector2,
    val screen: Canvas,
    val name: String,
    val textColor: Int,
    val thickness: Int? = null,
    val color: Int? = null
)
{
    fun draw(){
        val newThickness: Int = thickness ?: 0
        val textSize: Vector2 = Vector2(((name.length*2+size.x)/name.length).toInt(),(name.length+size.y/name.length+CELLS.size/3).toInt())
        val newColor: Int = color ?: WHITE
        screen.drawRect(pos,size, newThickness,newColor)
        screen.drawText(pos+textSize,name,textColor, 20)
    }

    fun isClicked(mouseEvent: MouseEvent): Boolean{
        return (mouseEvent.x >= pos.x &&
            mouseEvent.y >= pos.y &&
            mouseEvent.x <= pos.x + size.x &&
            mouseEvent.y <= pos.y + size.y)
    }
}



