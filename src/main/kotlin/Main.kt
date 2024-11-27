import pt.isel.canvas.BLACK
import pt.isel.canvas.Canvas
import pt.isel.canvas.onFinish
import pt.isel.canvas.onStart

val CELLS = Cells(Vector2(20,16),32)



fun main() {
    onStart {
        val screen = Canvas(CELLS.normalize.x,CELLS.normalize.y,BLACK)


    val body = listOf<SnakePart>(
        SnakePart(Vector2(10,7).times(CELLS.size),Direction.UP),
        SnakePart(Vector2(10,8).times(CELLS.size),Direction.UP),
        SnakePart(Vector2(10,9).times(CELLS.size),Direction.UP),
        SnakePart(Vector2(9,9).times(CELLS.size),Direction.RIGHT),
        SnakePart(Vector2(9,10).times(CELLS.size),Direction.UP),
        SnakePart(Vector2(10,10).times(CELLS.size),Direction.LEFT),
        SnakePart(Vector2(11,10).times(CELLS.size),Direction.LEFT),
        SnakePart(Vector2(11,9).times(CELLS.size),Direction.DOWN),
        SnakePart(Vector2(11,8).times(CELLS.size),Direction.DOWN)
    )

//    val body = listOf<SnakePart>(
//        SnakePart(Vector2(10,8).times(CELLS.size),0),
//        SnakePart(Vector2(10,9).times(CELLS.size),0),
//        SnakePart(Vector2(9,9).times(CELLS.size),3),
//        SnakePart(Vector2(9,10).times(CELLS.size),0),
//        SnakePart(Vector2(9,11).times(CELLS.size),0),
//        SnakePart(Vector2(10,11).times(CELLS.size),2)
//    )
        val snake = Snake(body, screen)
        val wall = Walls(emptyList(),CELLS.emptyCells(),snake,CELLS,screen)
        var game = Game(snake,wall,screen)

        screen.onTimeProgress(50){
            game = Game(snake,game.wall.newWall(),screen)
        }

        screen.onTimeProgress(50){
            game.draw()
        }
    }



    onFinish {
    }

}