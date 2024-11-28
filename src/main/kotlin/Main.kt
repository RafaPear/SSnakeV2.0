import pt.isel.canvas.*


val CELLS = Cells(Vector2(20,16),32)

val body = listOf<SnakePart>(
    SnakePart(Vector2(10,7).times(CELLS.size),Direction.UP),
    SnakePart(Vector2(10,8).times(CELLS.size),Direction.UP)
)

var ateApple = false


fun main() {
    onStart {
        val screen = Canvas(CELLS.normalize.x,CELLS.normalize.y,BLACK)
        var game = Game(
            Snake(body, screen),
            Walls(emptyList(), CELLS.emptyCells(), screen),
            screen)

        screen.onTimeProgress(50){
            game = Game(snake,game.wall.newWall(),screen)
        }

        screen.onTimeProgress(250){
            var canPress = true
            screen.onKeyPressed { keyEvent ->
                game = when (keyEvent.code) {
                    UP_CODE -> Game(game.snake.setDirection(Direction.UP), game.wall, screen)
                    DOWN_CODE -> Game(game.snake.setDirection(Direction.DOWN), game.wall, screen)
                    LEFT_CODE -> Game(game.snake.setDirection(Direction.LEFT), game.wall, screen)
                    RIGHT_CODE -> Game(game.snake.setDirection(Direction.RIGHT), game.wall, screen)
                    else -> game
                }
                canPress = false
                if (keyEvent.char == 'a') ateApple = true
            }

            if(ateApple && !willCollide(game.snake,game.wall)) game = Game(game.snake.newPart(),game.wall,screen)
            game = Game(game.snake.move(game.wall), game.wall, screen)
            ateApple = false
            game.run()
        }

        screen.onTimeProgress(5000){
            // game = Game(game.snake,game.wall.newWall(game.snake),screen)

        }
    }

    onFinish {
    }

}