import pt.isel.canvas.*

val CELLS = Cells(Vector2(20,16),32)
const val scoreAmount: Int = 1
const val growAmount: Int = 5


fun main() {
    onStart {
        val screen = CELLS.createWindow()


        screen.drawRect(Vector2(0, 0), CELLS.normalize * 2)
        screen.drawText(CELLS.center - Vector2(CELLS.size * 3, -CELLS.size), "Loading...", BLACK, 50)
        loadSounds("LevelUp", "Music1", "button1", "button2")

        var game: Game = initGame(screen, 0, true).addApple()

        screen.onMouseDown { mouseEvent ->
            if (game.snake.totalPos(0).size <= 3 && game.paused == true) {
                if (game.level1Button.isClicked(mouseEvent)) {
                    game = initGame(screen, 1, false).addApple()
                } else if (game.level2Button.isClicked(mouseEvent)) {
                    game = initGame(screen, 0, false).addApple()
                }
            }
            
            var newDebug = if (game.debugButton.isClicked(mouseEvent) && game.snake.totalPos(0).size > 3) {
                if (game.debug) playSound("button1") else playSound("button2")
                !game.debug
            } else game.debug

            var newPaused = if (game.pauseButton.isClicked(mouseEvent) && game.snake.totalPos(0).size > 3) {
                if (game.paused) playSound("button1") else playSound("button2")
                !game.paused
            } else game.paused

            if (game.restartButton.isClicked(mouseEvent) && isStuck(game)) {
                playSound("button1")
                newPaused = true
                game = initGame(screen, 0, true).addApple()
            }
            game = game.copy(debug = newDebug, paused = newPaused)
        }

        screen.onKeyPressed { keyEvent ->
            game = when (keyEvent.code) {
                UP_CODE -> if (canChangeDirection(Direction.UP, game) && !game.paused)
                    game.copy(snake = game.snake.setDirection(Direction.UP))
                else game

                DOWN_CODE -> if (canChangeDirection(Direction.DOWN, game) && !game.paused)
                    game.copy(snake = game.snake.setDirection(Direction.DOWN))
                else game

                LEFT_CODE -> if (canChangeDirection(Direction.LEFT, game) && !game.paused)
                    game.copy(snake = game.snake.setDirection(Direction.LEFT))
                else game

                RIGHT_CODE -> if (canChangeDirection(Direction.RIGHT, game) && !game.paused)
                    game.copy(snake = game.snake.setDirection(Direction.RIGHT))
                else game

                else -> game
            }
        }

        //600000

        playSound("Music1")
        screen.onTimeProgress(101000) { playSound("Music1") }



        screen.onTimeProgress(5000) {
            if (!game.paused) {
                game.wall.newWall(game.snake, game.apple, game.debug)
                playSound("button1")
            }
        }

        screen.onTimeProgress(250) {
            if (checkApple(game)) {
                playSound("LevelUp")
                game = game.copy(grow = game.grow + growAmount, score = game.score + scoreAmount).addApple()
                if (game.apple.pos in game.snake.totalPos(0)) game = game.addApple()
            }
            game = game.run()


        }
    }


    onFinish {
    }

}