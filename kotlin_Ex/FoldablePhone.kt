/**
Typically, a phone screen turns on and off when the power button is pressed.
In contrast, if a foldable phone is folded, the main inner screen on a foldable phone doesn't turn on when the power button is pressed.

In the initial code provided in the following code snippet, write a FoldablePhone class that inherits from the Phone class.
It should contain the following:
    * A property that indicates whether the phone is folded.
    * A different switchOn() function behavior than the Phone class so that it only turns the screen on when the phone isn't folded.
    * Methods to change the folding state.
 */

 open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }
    
    open fun switchOff() {
        isScreenLightOn = false
    }
    
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(var isFolded: Boolean = true): Phone() {
    fun checkFolded() {
    	val phoneFolded = if (isFolded) "folded" else "not folded"
        println("The phone is $phoneFolded.")
    }
    
    fun changeFoldState() {
        isFolded = !isFolded
        checkFolded()
    }
    
    override fun switchOn() {
        if (!isFolded) {
            super.switchOn()
            print("The phone now is turn on")
        } else {
            println("Can not turn on the device because device is folded now")
        }
    }
    
    override fun switchOff() {
        super.switchOff()
        println("The phone now is turned off")

    }
}

fun main() {
    var myPhone = FoldablePhone()
    myPhone.checkFolded()
    myPhone.switchOn()
    println("Change the state of fold now")
    myPhone.changeFoldState()
    myPhone.switchOn()
   
}