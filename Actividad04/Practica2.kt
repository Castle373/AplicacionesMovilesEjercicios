fun main (){
    val diego:String="bac"
    val diego1:String="aza"
    val diego2:String="abaca"
    val diego3:String="baaa"
    val diego4:String="aaab"
    println(diego.esFina())
    println(diego1.esFina())
    println(diego2.esFina())
    println(diego3.esFina())
    println(diego4.esFina())
    println("------------")
    println(diego.esFinaRegex())
    println(diego1.esFinaRegex())
    println(diego2.esFinaRegex())
    println(diego3.esFinaRegex())
    println(diego4.esFinaRegex())
}
fun String.esFinaRegex():Boolean {
    val condicion1 = !Regex("bu|ba|be").containsMatchIn(this)
    val condicion2 = Regex("[aeiouAEIOU]").findAll(this).count() >= 3
    val condicion3 = Regex("(\\w)\\1").containsMatchIn(this)

    val condicionesCumplidas = listOf(condicion1, condicion2, condicion3).count { it } >= 2

    return condicionesCumplidas
}

fun String.esFina():Boolean{
    var contadorFinos:Int =0
    if(this.contentEquals("ba")||this.contentEquals("bu")||this.contentEquals("be")){
        contadorFinos++
    }
    val cantidadVocales = this.count { it.equals('a', ignoreCase = true)|| it.equals('e', ignoreCase = true)|| it.equals('i', ignoreCase = true)|| it.equals('o', ignoreCase = true)|| it.equals('u', ignoreCase = true)}
    if (cantidadVocales==3){
        contadorFinos++
    }
    var co:Int=0
    var let:Char?=null;
    for (letra in this){
        if (letra==let){
            contadorFinos++;
            break;
        }
        let=letra;
    }

    if (contadorFinos>1){
        return true
    }
    return false
}