open class Product(val name: String, val price: Int) {
    fun info(): String = "Product: $name, Price: $price"
    fun calculatePrice(quantity: Int): Int = price * quantity
}

interface DiscountProduct {
    fun calculateDiscountPrice(): Double
}

fun Product.getInfo() {
    println(info())
}

class DairyProducts(name: String, price: Int, val discountPercentage: Int = 15) :
    Product(name, price), DiscountProduct {
    override fun calculateDiscountPrice(): Double = (price * (1 - discountPercentage / 100.0))
}

class MeatProducts(name: String, price: Int, val discountPercentage: Int = 30) :
    Product(name, price), DiscountProduct {
    override fun calculateDiscountPrice(): Double = (price * (1 - discountPercentage / 100.0))
}

data class Control(
    val storeName: String,
    val dairyProducts: List<DairyProducts> = emptyList(),
    val meatProducts: List<MeatProducts> = emptyList()
) {
    fun addDairyProduct(product: DairyProducts): Control {
        val updatedDairyProducts = dairyProducts + product
        return this.copy(dairyProducts = updatedDairyProducts)
    }

    fun addMeatProduct(product: MeatProducts): Control {
        val updatedMeatProducts = meatProducts + product
        return this.copy(meatProducts = updatedMeatProducts)
    }

    fun getReport() {
        println("$storeName")
        println("Total Dairy Products: ${dairyProducts.size}")
        println("Total Meat Products: ${meatProducts.size}\n")
    }
}

fun main() {
    var magnit = Control("Magnit")
    var monetka = magnit.copy(storeName = "Monetka")

    val milk = DairyProducts(name = "Milk", price = 76)
    milk.getInfo()
    println("Discount price: ${milk.calculateDiscountPrice()}\n")
    magnit = magnit.addDairyProduct(milk)
    magnit = magnit.addDairyProduct(milk)
    milk.let { println("Cost of 2 bottles of milk in the Magnit store: ${it.calculatePrice(2)}\n") }
    monetka = monetka.addDairyProduct(milk)

    val beef = MeatProducts(name = "Beef", price = 278)
    beef.getInfo()
    println("Discount price: ${beef.calculateDiscountPrice()}\n")
    monetka = monetka.addMeatProduct(beef)
    monetka = monetka.addMeatProduct(beef)
    magnit = magnit.addMeatProduct(beef)

    magnit.getReport()
    monetka.getReport()
}