/**
Imagine that you need to create a music-player app.

Create a class that can represent the structure of a song. The Song class must include these code elements:

Properties for the title, artist, year published, and play count
A property that indicates whether the song is popular. If the play count is less than 1,000, consider it unpopular.
A method that prints a song description in this format:
"[Title], performed by [artist], was released in [year published]."
 */
class Song (titleSong: String, artistSong: String, yearSong: String, playCount: Int) {
    val title = titleSong
    val artist = artistSong
    val year = yearSong
    val count = playCount
    
    val isPopular : Boolean = (count>1000)
    
    fun songPrint() {
        println("Song $title, performed by $artist, was released in $year.")
    }
}
fun main() {
    val Song1 = Song("abc", "xyz", "2024", 30)
    Song1.songPrint()
    
    println("Song ${Song1.title} with ${Song1.count} is ${Song1.isPopular} popular")
}