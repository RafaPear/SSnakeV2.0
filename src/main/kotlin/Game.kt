import pt.isel.canvas.*

/**
 * Creates the Screen class for a cleaner code. The [grid] parameter is a
 * position class that contains an amount of cells on the x-axis and
 * on the y-axis. The [size]
 * parameter is the pixel size
 */

data class Cells(val grid: Vector2, val size: Int) {
    fun normalize() = grid.div(2).times(size).times(2)
    fun center() = normalize().div(2)
    fun createWindow() = Canvas(normalize().x,normalize().y,BLACK)
}

data class Game(val snake: Snake, val screen: Canvas) {
    fun draw() {

        onStart {

            snake.draw()
        }

        onFinish {

        }
    }
}




