package model

class Character(var name: String, var hp: Int, var attack: Int, var characterType: String, override var position: Int): Thing(position){
    constructor(name: String, characterType: String) : this(name,0,0,characterType,0) {

    }
}