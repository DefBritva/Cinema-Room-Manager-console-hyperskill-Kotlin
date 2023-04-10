
fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    val cinema = CinemaHall(rows, seats)
    var isWork = true
    val menu = "1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit"
    while (isWork) {
        println(menu)
        val choise = readln().toInt()

        if (choise == 1) {
            cinema.showTheSeats()
        }
        else if (choise == 2) {
            cinema.buyTicket()
        }
        else if (choise == 3) {
            cinema.statistics()
        }
        else if (choise == 0) {
            isWork = false
        }
        else {
            println("Uncorrect choise")
        }
    }
}

class CinemaHall {
    val hall = arrayListOf<ArrayList<Char>>()
    var currentIncome = 0
    private var purchTickets = 0
    private val rows: Int
    private val seats: Int

    constructor(rows: Int, seats: Int) {
        this.rows = rows
        this.seats = seats
        repeat(rows) { hall.add(arrayListOf()) }
        for (raw in hall) {
            repeat(seats) { raw.add('S') }
        }
    }
    fun getTotalIncome(): Int {
        if (rows * seats <= 60) {
            return rows * seats * 10
        }
        else {
            return rows / 2 * seats * 10 + ((rows - rows / 2) * seats * 8)
        }
    }



    fun showTheSeats(hall: ArrayList<ArrayList<Char>> = this.hall) {
        println("Cinema:")
        print("  ")
        for (num in 1 .. hall[0].size) print("$num ")
        println()
        for (i in 1 .. hall.size) {
            println("$i ${hall[i-1].joinToString(" ")}")
        }
    }

    fun getTicketPrice(choiseRow: Int, rows: Int = this.rows, seats: Int = this.seats): Int {
        if (rows * seats <= 60) {
            return 10
        }
        if (choiseRow in 1..rows / 2 ) return 10
        return 8

    }
    fun buyTicket(hall: ArrayList<ArrayList<Char>> = this.hall) {
        var buying = true
        while (buying) {
            println("Enter a row number:")
            val rowN = readln().toInt()
            println("Enter a seat number in that row:")
            val seatN = readln().toInt()
            if (rowN in 1..rows && seatN in 1..seats) {
                if (this.hall[rowN - 1][seatN - 1] != 'B') {
                    this.hall[rowN - 1][seatN - 1] = 'B'
                    println("Ticket price: $${getTicketPrice(rowN)}")
                    currentIncome += getTicketPrice(rowN)
                    purchTickets += 1
                    buying = false
                } else println("That ticket has already been purchased!")

            } else println("Wrong input!")
        }
    }

    fun statistics() {
        println("Number of purchased tickets: $purchTickets")
        println("Percentage: ${getPercentage()}%")
        println("Current income: $$currentIncome")
        println("Total income: $${getTotalIncome()}")
    }

    fun getPercentage(): String {
        var counter = 0
        for (row in hall) {
            for (seat in row) {
                if (seat == 'B') counter++
            }
        }
        return String.format("%.2f", counter.toDouble() / (rows * seats).toDouble() * 100.0)
    }


}





