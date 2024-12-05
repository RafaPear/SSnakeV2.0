import pt.isel.canvas.*


fun initGame(screen: Canvas,level: Int, paused: Boolean): Game {
    var wall: Walls
    if (level == 0){
        val level1: MutableList<Vector2> = mutableListOf(
            Vector2(0,0), Vector2(CELLS.size,0), Vector2(CELLS.size*2,0),
            Vector2(0,CELLS.size), Vector2(0,CELLS.size*2), Vector2(0,CELLS.size*3),
            Vector2(CELLS.normalize.x-CELLS.size,0), Vector2(CELLS.normalize.x-CELLS.size*2,0),
            Vector2(CELLS.normalize.x-CELLS.size*3,0), Vector2(CELLS.normalize.x-CELLS.size,CELLS.size),
            Vector2(CELLS.normalize.x-CELLS.size,CELLS.size*2),  Vector2(CELLS.normalize.x-CELLS.size,CELLS.size*3),
            Vector2(0,CELLS.normalize.y-CELLS.size), Vector2(0,CELLS.normalize.y-CELLS.size*2),
            Vector2(0,CELLS.normalize.y-CELLS.size*3), Vector2(0,CELLS.normalize.y-CELLS.size*4),
            Vector2(CELLS.size,CELLS.normalize.y-CELLS.size), Vector2(CELLS.size*2,CELLS.normalize.y-CELLS.size),
            CELLS.normalize-CELLS.square, Vector2(CELLS.normalize.x-CELLS.size*2, CELLS.normalize.y - CELLS.size),
            Vector2(CELLS.normalize.x-CELLS.size*3, CELLS.normalize.y - CELLS.size),
            Vector2(CELLS.normalize.x-CELLS.size, CELLS.normalize.y - CELLS.size*2),
            Vector2(CELLS.normalize.x-CELLS.size, CELLS.normalize.y - CELLS.size*3),
            Vector2(CELLS.normalize.x-CELLS.size, CELLS.normalize.y - CELLS.size*4))
        wall = Walls(level1, CELLS.emptyCells().toMutableList(), screen)
    }
    else wall = Walls(mutableListOf(), CELLS.emptyCells().toMutableList(), screen)

    wall.updateWall()

    return Game(
        Snake(
            (listOf<SnakePart>(SnakePart(CELLS.center,Direction.UP),
            SnakePart(Vector2(CELLS.center.x,CELLS.center.y+CELLS.size),Direction.UP))), screen),
        wall, screen, 3, false, paused, Apple(null,screen), 0
    )
}


data class Game(
    val snake: Snake,
    val wall: Walls,
    val screen: Canvas,
    val grow: Int,
    val debug: Boolean,
    val paused: Boolean,
    val apple: Apple,
    val score: Int
) {

    var lose = false
    var win = false

    val debugButton = Button(
        Vector2(CELLS.normalize.x-CELLS.size*4+CELLS.size/2,CELLS.normalize.y+CELLS.size/2),
        Vector2(CELLS.size*3, CELLS.size), screen, "Debug", BLACK)

    val pauseButton = Button(Vector2(debugButton.pos.x-debugButton.size.x-CELLS.size/2, debugButton.pos.y),
        Vector2(CELLS.size*3, CELLS.size), screen, "Pause", BLACK)

    val restartButton = Button(CELLS.center - Vector2(CELLS.size*2,CELLS.size/2),
        Vector2(CELLS.size*4, CELLS.size), screen, "  Restart", BLACK)

    val level1Button: Button = Button(
        CELLS.center - Vector2(CELLS.size * 6, 0),
        Vector2(CELLS.size * 5, CELLS.size),
        screen,
        "Level 1",
        BLACK
    )
    val level2Button: Button = Button(
        CELLS.center + Vector2(CELLS.size * 2, 0),
        Vector2(CELLS.size * 5, CELLS.size),
        screen,
        "Level 2",
        BLACK
    )

    fun addApple(): Game = Game(snake, wall, screen, grow, debug, paused, apple.newApple(snake, wall),score)

    fun run(): Game{
        if (!paused){
            var newGame = this.copy(snake = snake.move(wall))
            draw(newGame)
            newGame = newGame.grow()
            if (debug) newGame.drawDebug()
            newGame.drawUI()
            return newGame
        }
        else {
            draw(this)
            if (debug) drawDebug()
            drawUI()
            screen.drawRect(Vector2(0,0)+CELLS.size/2,CELLS.normalize-CELLS.size,2,RED)
            screen.drawText(Vector2(CELLS.size, CELLS.size+CELLS.size/2),"PAUSED",RED,("PAUSED").length*5)
            drawLevelSelect()
            return this
        }

    }

    fun draw(newGame: Game){
        newGame.screen.erase()
        newGame.apple.draw()
        newGame.snake.draw()
        newGame.wall.draw()
    }

    fun drawLevelSelect(){
        if (snake.totalPos(0).size <= 3) {
            screen.drawRect(Vector2(0, 0), CELLS.normalize * 2, 0, GREY)
            screen.drawText(
                CELLS.center - Vector2(CELLS.size * 8, CELLS.size * 4),
                "SSnake: Select Level",
                WHITE,
                50
            )
            level1Button.draw()
            level2Button.draw()
        }
    }

    fun drawUI(){
        screen.drawRect(Vector2(0,CELLS.normalize.y),Vector2(CELLS.normalize.x, CELLS.size*2),0,GREY)
        debugButton.draw()
        pauseButton.draw()
        if (isStuck(this) && snake.totalPos(0).size < 60) {
            screen.drawText(CELLS.center - Vector2(CELLS.size * 4, CELLS.size), "YOU LOSE", WHITE, 50)
            restartButton.draw()
            lose = true
        }
        else if (isStuck(this)) {
            screen.drawText(CELLS.center - Vector2((CELLS.size * 3.5).toInt(), CELLS.size), "YOU WIN", WHITE, 50)
            restartButton.draw()
            win = true
        }

        screen.drawText(Vector2(CELLS.size*6,CELLS.normalize.y) + Vector2(0,CELLS.size + CELLS.size/4), "Score: $score",WHITE,28)

        if (snake.totalPos(0).size < 60)
            screen.drawText(Vector2(CELLS.size/2,CELLS.normalize.y) + Vector2(0,CELLS.size + CELLS.size/4), "Size: ${snake.totalPos(0).size}",WHITE,28)
        else
            screen.drawText(Vector2(CELLS.size/2,CELLS.normalize.y) + Vector2(0,CELLS.size + CELLS.size/4), "Size: ${snake.totalPos(0).size}",GREEN,28)
    }

    fun grow(): Game{
        return if(!willCollide(snake, wall) && grow > 0){
            this.copy(snake = snake.newPart(), grow = grow-1)
        }
        else this
    }

    fun drawDebug(){
        for (x in 0..<CELLS.grid.x)
            for (y in 0..<CELLS.grid.y)
                screen.drawRect(Vector2(x,y)*CELLS.size,CELLS.square,1)

        for (i in 0..<snake.totalPos(0).size)
                screen.drawRect(snake.body[i].pos,CELLS.square,2,YELLOW)
        if (snake.body[0].direction == Direction.LEFT || snake.body[0].direction == Direction.RIGHT) {
            if (!canChangeDirectionHorizontal(Direction.UP, this))
                screen.drawLine(snake.body[0].pos, snake.body[0].pos + Vector2(CELLS.size, 0), 4, RED)
            if (!canChangeDirectionHorizontal(Direction.DOWN, this))
                screen.drawLine(snake.body[0].pos + Vector2(0, CELLS.size), snake.body[0].pos + Vector2(CELLS.size, CELLS.size), 4, RED)
        }
        else if (snake.body[0].direction == Direction.UP || snake.body[0].direction == Direction.DOWN) {
            if (!canChangeDirectionVertical(Direction.LEFT, this))
                screen.drawLine(snake.body[0].pos, snake.body[0].pos + Vector2(0, CELLS.size), 4, RED)
            if (!canChangeDirectionVertical(Direction.RIGHT, this))
                screen.drawLine(snake.body[0].pos + Vector2(CELLS.size, 0), snake.body[0].pos + Vector2(CELLS.size, CELLS.size), 4, RED)
        }
        if (willCollide(snake,wall))
            when (snake.body[0].direction) {
                Direction.UP ->
                    screen.drawLine(snake.body[0].pos, snake.body[0].pos + Vector2(CELLS.size, 0), 4, RED)
                Direction.DOWN ->
                    screen.drawLine(snake.body[0].pos + Vector2(0, CELLS.size), snake.body[0].pos + Vector2(CELLS.size, CELLS.size), 4, RED)
                Direction.LEFT ->
                    screen.drawLine(snake.body[0].pos, snake.body[0].pos + Vector2(0, CELLS.size), 4, RED)
                Direction.RIGHT ->
                    screen.drawLine(snake.body[0].pos + Vector2(CELLS.size, 0), snake.body[0].pos + Vector2(CELLS.size, CELLS.size), 4, RED)
            }
    }
}