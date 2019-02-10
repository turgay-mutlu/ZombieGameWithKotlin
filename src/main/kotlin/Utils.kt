import model.Character
import model.Thing
import java.io.File

class Utils {
    fun findCharacter(line:String): Character?{
        if(line.contains("is Enemy",true)){
            val s = line.split(" ")
            return Character(s[0],"Enemy")
        }
        if(line.contains("Hero has",true)){
            return Character("Hero","Hero")
        }
        return null
    }

    fun setCharacterStats(line: String, map: HashMap<String, Character>){
        val s = line.split(" ")
        if(line.contains("hp",true)){
            if(map.containsKey(s[0])){
                try {
                    val c = map[s[0]]
                    c!!.hp= Integer.parseInt(s[2])
                    map[s[0]] = c
                } catch (e:Exception){
                    println("HP cannot set for " + map[s[0]]!!.name)
                }
            }
        }
        if(line.contains("attack",true)){
            if(map.containsKey(s[0])){
                try {
                    val c = map[s[0]]
                    c!!.attack= Integer.parseInt(s[3])
                    map[s[0]] = c
                } catch (e:Exception){
                    println("attack cannot set for " + map[s[0]]!!.name)
                }
            }
        }
    }

    fun addCharacterToGame(line: String, list: ArrayList<Character>, map: HashMap<String, Character>){
        val s= line.split(" ")
        if(line.contains("position",true)){
            if(map.containsKey(s[3])){
                try {
                    val c = Character(map[s[3]]!!.name,map[s[3]]!!.hp,map[s[3]]!!.attack,map[s[3]]!!.characterType,0)
                    c.position = Integer.parseInt(s[6])
                    list.add(c)
                } catch (e: Exception){
                    println("attack cannot set for " + map[s[3]]!!.name)
                }
            }
        }
    }

    fun findResources(line: String, resource:Thing){
        if(line.contains("Resources", true)){
            val s= line.split(" ")
            try {
                resource.position = Integer.parseInt(s[2])
            } catch (e:Exception){
                println("Resource cannot set")
            }
        }
    }

    fun journey(hero:Character, resource: Thing, list:ArrayList<Character>){
        if(hero.hp==0){
            println("hero is dead")
            return
        }
        if(resource.position==0){
            println("There is no resource")
            return
        }

        println("Hero started journey with ${hero.hp} HP!")
        for (character in list) {
            if(fight(hero,character)){
                return
            }
        }
        println("Hero Survived!")
    }

    fun fight(hero: Character, enemy:Character): Boolean{
        val heroHitCount= hitCount(hero.attack.toDouble(),enemy.hp.toDouble())
        val enemyHitCount = hitCount(enemy.attack.toDouble(),hero.hp.toDouble())

        if(heroHitCount<enemyHitCount){
            hero.hp -= heroHitCount*enemy.attack
            hero.position = enemy.position
            println("Hero defeated ${enemy.name} with ${hero.hp} HP remaining")
            return false
        } else{
            enemy.hp -= enemyHitCount*hero.attack
            println("${enemy.name} defeated Hero with ${enemy.hp} HP remaining")
            println("Hero is Dead!! Last seen at position ${hero.position}!!")
            return true
        }

    }

    fun hitCount(power:Double, hp:Double): Int{
        return Math.round((hp/power)).toInt()
    }
}