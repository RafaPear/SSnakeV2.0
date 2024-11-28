import pt.isel.canvas.*

/**
 * Creates the Screen class for a cleaner code. The [grid] parameter is a
 * position class that contains an amount of cells on the x-axis and
 * on the y-axis. The [size]
 * parameter is the pixel size
 */

data class Game(val snake: Snake, val wall: Walls, val screen: Canvas) {
    fun run() {
        screen.erase()
        snake.draw()
        wall.draw()
        debug()
    }

    fun debug(){
        for (i in 0..<snake.totalPos(0).size)
                screen.drawRect(snake.totalPos(0)[i].x,snake.totalPos(0)[i].y,CELLS.size,CELLS.size,WHITE,2)
    }
}




