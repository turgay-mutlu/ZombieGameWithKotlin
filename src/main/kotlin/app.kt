import model.Character
import model.Thing
import java.io.File

fun main(args: Array<String>){
    val file = File("src/main/resources/Input")
    val utils = Utils()

    val map = HashMap<String,Character>()
    val list = ArrayList<Character>()
    var hero: Character? = null
    val resource = Thing(0)

    file.forEachLine {
        val c = utils.findCharacter(it)
        if(c!=null && !map.containsValue(c)){
            if(hero==null && c.name.equals("Hero")){
                hero = c
            }
            map.put(c.name,c)
        }
    }

    file.forEachLine {
        utils.findResources(it,resource)
        utils.setCharacterStats(it, map)
        utils.addCharacterToGame(it, list, map)
    }

    list.sortBy {
        it.position
    }

    println("*****************************")
    utils.journey(hero!!,resource,list)
}