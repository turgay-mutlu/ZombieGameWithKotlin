import model.Character
import model.Thing
import java.io.File

fun main(args: Array<String>){
    val file = File("src/main/resources/Input")

    val map = HashMap<String,Character>()
    val list = ArrayList<Character>()
    var hero: Character? = null
    val resource = Thing(0)

    file.forEachLine {
        val c = Utils.findCharacter(it)
        if(c!=null && !map.containsValue(c)){
            if(hero==null && c.name.equals("Hero")){
                hero = c
            }
            map.put(c.name,c)
        }
    }

    file.forEachLine {
        Utils.findResources(it,resource)
        Utils.setCharacterStats(it, map)
        Utils.addCharacterToGame(it, list, map)
    }

    list.sortBy {
        it.position
    }

    println("*****************************")
    Utils.journey(hero!!,resource,list)
}