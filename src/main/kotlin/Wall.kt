import pt.isel.canvas.*

data class Walls(
    val walls: MutableList<Vector2>,
    val availablePlaces: MutableList<Vector2>,
    val screen: Canvas
){
    fun updateWall() {
        for (i in walls)
            availablePlaces -= i
    }


    fun newWall(snake: Snake, apple: Apple, debug: Boolean){
        updateWall()
        while(availablePlaces.size-snake.totalPos(0).size-1 != 0){
            val i = availablePlaces.indices.random()

            if (availablePlaces[i] !in snake.totalPos(0) && availablePlaces[i] != apple.pos){
                if (debug) screen.drawRect(availablePlaces[i],CELLS.square,2,GREEN)
                walls += availablePlaces[i]
                availablePlaces -= availablePlaces[i]
                break
            }
            if (debug) screen.drawRect(availablePlaces[i],CELLS.square,2,RED)
        }
    }

    fun draw(){
        for (pos in walls) {
            screen.drawImage("bricks|0,0,118,118", pos, CELLS.square)
        }
    }
}