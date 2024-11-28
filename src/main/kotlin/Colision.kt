import kotlin.collections.plus

// TODO: Create the main collision function that can check if anything is colliding with anything

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