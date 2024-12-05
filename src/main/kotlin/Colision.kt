fun willCollide(snake: Snake, wall: Walls): Boolean {
    when (snake.body[0].direction) {
        Direction.UP ->
            if (Vector2(snake.body[0].pos.x,snake.body[0].pos.y-CELLS.size) in wall.walls ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y+CELLS.normalize.y-CELLS.size) in wall.walls ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y+CELLS.normalize.y-CELLS.size) in snake.totalPos(1) ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y-CELLS.size) in snake.totalPos(1)
                ) return true

        Direction.DOWN ->
            if (Vector2(snake.body[0].pos.x,snake.body[0].pos.y+CELLS.size) in wall.walls ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y-CELLS.normalize.y+CELLS.size) in wall.walls ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y-CELLS.normalize.y+CELLS.size) in snake.totalPos(1) ||
                Vector2(snake.body[0].pos.x,snake.body[0].pos.y+CELLS.size) in snake.totalPos(1)
                ) return true

        Direction.LEFT ->
            if (Vector2(snake.body[0].pos.x-CELLS.size,snake.body[0].pos.y) in wall.walls ||
                Vector2(snake.body[0].pos.x+CELLS.normalize.x-CELLS.size,snake.body[0].pos.y) in wall.walls ||
                Vector2(snake.body[0].pos.x+CELLS.normalize.x-CELLS.size,snake.body[0].pos.y) in snake.totalPos(1) ||
                Vector2(snake.body[0].pos.x-CELLS.size,snake.body[0].pos.y) in snake.totalPos(1)
                ) return true

        Direction.RIGHT ->
            if (Vector2(snake.body[0].pos.x+CELLS.size,snake.body[0].pos.y) in wall.walls ||
                Vector2(snake.body[0].pos.x-CELLS.normalize.x+CELLS.size,snake.body[0].pos.y) in wall.walls ||
                Vector2(snake.body[0].pos.x-CELLS.normalize.x+CELLS.size,snake.body[0].pos.y) in snake.totalPos(1) ||
                Vector2(snake.body[0].pos.x+CELLS.size,snake.body[0].pos.y) in snake.totalPos(1)
                ) return true
    }
    return false
}

fun canChangeDirection(direction: Direction, game: Game): Boolean{
    when(game.snake.body[0].direction){
        Direction.UP ->
            if (canChangeDirectionVertical(direction,game) && direction != Direction.DOWN) return true
        Direction.DOWN ->
            if (canChangeDirectionVertical(direction,game) && direction != Direction.UP) return true
        Direction.LEFT ->
            if (canChangeDirectionHorizontal(direction,game) && direction != Direction.RIGHT) return true
        Direction.RIGHT ->
            if (canChangeDirectionHorizontal(direction,game) && direction != Direction.LEFT) return true
    }
    return false
}

fun canChangeDirectionVertical(direction: Direction, game: Game): Boolean{
    return if (direction == Direction.LEFT) checkLeft(game) else checkRight(game)
}

fun canChangeDirectionHorizontal(direction: Direction, game: Game): Boolean{
    return if (direction == Direction.UP) checkUp(game) else checkDown(game)
}

fun checkUp(game: Game): Boolean {
    return!(
            (game.snake.body[0].pos - Vector2(0,CELLS.size) in game.wall.walls) ||
            (game.snake.body[0].pos - Vector2(0,CELLS.size) in game.snake.totalPos(1)) ||
            (game.snake.body[0].pos + Vector2(0,CELLS.normalize.y - CELLS.size) in game.wall.walls) ||
            (game.snake.body[0].pos + Vector2(0,CELLS.normalize.y - CELLS.size) in game.snake.totalPos(1))
            )
}

fun checkDown(game: Game): Boolean {
    return!(
            (game.snake.body[0].pos + Vector2(0,CELLS.size) in game.wall.walls) ||
            (game.snake.body[0].pos + Vector2(0,CELLS.size) in game.snake.totalPos(1)) ||
            (game.snake.body[0].pos - Vector2(0,CELLS.normalize.y - CELLS.size) in game.wall.walls) ||
            (game.snake.body[0].pos - Vector2(0,CELLS.normalize.y - CELLS.size) in game.snake.totalPos(1))
            )
}

fun checkLeft(game: Game): Boolean {
    return!(
            (game.snake.body[0].pos - Vector2(CELLS.size,0) in game.wall.walls) ||
            (game.snake.body[0].pos - Vector2(CELLS.size,0) in game.snake.totalPos(1)) ||
            (game.snake.body[0].pos + Vector2(CELLS.normalize.x - CELLS.size,0) in game.wall.walls) ||
            (game.snake.body[0].pos + Vector2(CELLS.normalize.x - CELLS.size,0) in game.snake.totalPos(1))
            )
}

fun checkRight(game: Game): Boolean {
    return!(
            (game.snake.body[0].pos + Vector2(CELLS.size,0) in game.wall.walls) ||
            (game.snake.body[0].pos + Vector2(CELLS.size,0) in game.snake.totalPos(1)) ||
            (game.snake.body[0].pos - Vector2(CELLS.normalize.x - CELLS.size,0) in game.wall.walls) ||
            (game.snake.body[0].pos - Vector2(CELLS.normalize.x - CELLS.size,0) in game.snake.totalPos(1))
            )
}

fun isStuck(game: Game): Boolean{
    val snakeHead = game.snake.body[0]
    if (willCollide(game.snake,game.wall)) {
        if (snakeHead.direction == Direction.UP || snakeHead.direction == Direction.DOWN) {
            return (!canChangeDirectionVertical(Direction.LEFT, game) &&
                    !canChangeDirectionVertical(Direction.RIGHT, game))
        }
        else if (snakeHead.direction == Direction.LEFT || snakeHead.direction == Direction.RIGHT) {
            return (!canChangeDirectionHorizontal(Direction.UP, game) &&
                    !canChangeDirectionHorizontal(Direction.DOWN, game))
        }
    }
    return false
}

fun checkApple(game: Game): Boolean {
    return when (game.snake.body[0].direction) {
        Direction.UP ->
            Vector2(game.snake.body[0].pos.x,game.snake.body[0].pos.y-CELLS.size) == game.apple.pos ||
            Vector2(game.snake.body[0].pos.x,game.snake.body[0].pos.y+CELLS.normalize.y-CELLS.size) == game.apple.pos

        Direction.DOWN ->
            Vector2(game.snake.body[0].pos.x,game.snake.body[0].pos.y+CELLS.size) == game.apple.pos ||
            Vector2(game.snake.body[0].pos.x,game.snake.body[0].pos.y-CELLS.normalize.y+CELLS.size) == game.apple.pos

        Direction.LEFT ->
            Vector2(game.snake.body[0].pos.x-CELLS.size,game.snake.body[0].pos.y) == game.apple.pos ||
                    Vector2(game.snake.body[0].pos.x+CELLS.normalize.x-CELLS.size,game.snake.body[0].pos.y) == game.apple.pos

        Direction.RIGHT ->
            Vector2(game.snake.body[0].pos.x+CELLS.size,game.snake.body[0].pos.y) == game.apple.pos ||
            Vector2(game.snake.body[0].pos.x-CELLS.normalize.x+CELLS.size,game.snake.body[0].pos.y) == game.apple.pos
    }
}
