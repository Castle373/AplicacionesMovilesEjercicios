package com.example.myapplication




fun main(args: Array<String>) {
    println(verificaCadena("nombre"))
    println(verificaCadena("_nombre"))
    println(verificaCadena("_12"))
    println(verificaCadena(""))
    println(verificaCadena("012"))
    println(verificaCadena("no$"))
}

fun verificaCadena( variable: String): Boolean {
    if (variable.isEmpty()) {
        return false;
    }else if(variable.get(0).isLetter()||variable.get(0)=='_'){
        var validar:Boolean =false
        for (caracter:Char in variable){
            if(caracter.isLetter()||caracter=='_'||caracter.isDigit()){
                validar = true
            }else{
                validar = false
            }
        }
        return validar
    }else{
        return false;
    }

}