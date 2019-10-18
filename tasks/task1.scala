object Task1{
    def main(args: Array[String]) {
        var array = Array.empty[Int]
        for (i <- 1 to 50) {
            array :+= i
        }
        array.foreach(print)
    }
}
