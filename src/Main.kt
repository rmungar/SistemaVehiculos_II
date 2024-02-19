fun main() {
    val coche = Automovil("Dodge", "Charger", 50.0F, 50.0F, 0, false)
    println(coche.calcularAutonomia())
    coche.realizaViaje(90)
    coche.realizarDerrape()
    coche.repostar(0.0F)
    println(coche.obtenerInformacion())
    Automovil.cambiarCondicionBritania(true)
    println(coche.obtenerInformacion())
    val moto = Motocicleta("Kawasaki", "Kaboom", 20.0F, 0.0F, 100, 210)
    moto.realizaViaje(100)
    moto.realizaCaballito()
    moto.repostar(100.0F)
    println(moto.obtenerInformacion())
}