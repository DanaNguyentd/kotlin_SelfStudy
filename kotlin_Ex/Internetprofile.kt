/**
Oftentimes, you're required to complete profiles for online websites that contain mandatory and non-mandatory fields.
For example, you can add your personal information and link to other people who referred you to sign up for the profile.

In the initial code provided in the following code snippet, write a program which prints out a person's profile details.
 */

 fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, null, amanda)
    
    println("test")
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val nameofPerson: String, val ageofPerson: Int, val hobbyofPerson: String?, val referrerofPerson: Person?) {
    val name = nameofPerson
    val age = ageofPerson
    val hobby = hobbyofPerson
    val re: Person? = referrerofPerson
    
    fun hobbyPrint(): String {
       if (hobby == null) {
           return "Likes nothing."
       } else {
           return ("Likes to " + hobby +".")
       }
    }
    
    fun showProfile() {
       println("Name: $name")
       println("Age: $age")
       if (re != null) {
           println(hobbyPrint() + " Has a referrer named ${re.name}, who " + re.hobbyPrint())
           println("")
  
       } else {
           println(hobbyPrint() + " Doesn't have a referrer.")
           println("")
       }
    }
}