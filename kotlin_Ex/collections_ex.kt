/**
Typically in an auction, the highest bidder determines the price of an item.
In this special auction, if there's no bidder for an item, the item is automatically sold to the auction house at the minimum price.

In the initial code provided in the following code snippet, you're given an auctionPrice() function that accepts a nullable Bid? type as an argument:
 */
enum class Daypart {MORNING, AFTERNOON, EVENING,}

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val duration: Int
)

val Event.durationOfEvent: String
	get() = if (this.duration < 60) {
        "short"
    } else {
        "long"
    }
    
fun main() {    
    val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, duration = 0)
	val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, duration = 15)
	val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, duration = 30)
	val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, duration = 60)
	val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, duration = 10)
	val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, duration = 45)
    
    val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)
    
    val shortevents = events.filter {it.duration < 60}
    println("Total short Events is: ${shortevents.size}")
    
    println("Morning: ${events.filter{it.daypart == Daypart.MORNING}.size}")
    println("Afternoon: ${events.filter{it.daypart == Daypart.AFTERNOON}.size}")
    println("Evening: ${events.filter{it.daypart == Daypart.EVENING}.size}")
    
    val groupEvents = events.groupBy {it.daypart}
    groupEvents.forEach { (daypart, events) ->
    	println("$daypart: ${events.size} events")
	}
    
    println("Last event of the day: ${events.last().title}")

    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}