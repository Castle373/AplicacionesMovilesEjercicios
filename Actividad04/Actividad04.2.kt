fun main(args:Array<String>){
    val s1:String?=null
    val s2:String?=""
    println(s1.esVaciaONula())
    println(s2.esVaciaONula())
    val s3:String?="   "
    println(s3.esVaciaONula())
}
fun String?.esVaciaONula():Boolean{
    return if(this==null||this.isEmpty()){
        true
    }else{
        false
    }
}