import pt.isel.canvas.*

data class Apple(
    val pos: Vector2? = null,
    val screen: Canvas
){
    fun newApple(snake: Snake, walls: Walls): Apple {
        var tempPos = pos
        while(walls.availablePlaces.size-snake.totalPos(0).size != 0){
            val i = walls.availablePlaces.indices.random()

            if (walls.availablePlaces[i] !in snake.totalPos(0) &&
                walls.availablePlaces[i] != snake.move(walls).body[0].pos){
                tempPos = walls.availablePlaces[i]
                break
            }
        }
        return Apple(tempPos, screen)
    }

    fun draw(){
        if (pos != null) {
            screen.drawImage("snake|0,192,64,64", pos, CELLS.square)
        }
    }
}
