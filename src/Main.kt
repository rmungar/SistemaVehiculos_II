fun main() {
    val coche = Automovil("¿?","Dodge", "Charger", 50.0F, 50.0F, 0, false)
    println(coche.calcularAutonomia())
    coche.realizaViaje(90)
    coche.realizarDerrape()
    coche.repostar(0.0F)
    println(coche.obtenerInformacion())
    Automovil.cambiarCondicionBritania(true)
    println(coche.obtenerInformacion())
    val moto = Motocicleta("Pinguino","Kawasaki", "Kaboom", 20.0F, 0.0F, 100, 210)
    moto.realizaViaje(100)
    moto.realizarCaballito()
    moto.repostar(100.0F)
    println(moto.obtenerInformacion())

//--------------------------------------------------------------------------------------------------------------------//
    val aurora = Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0, true) // Coche eléctrico con capacidad de 50 litros, inicia con el 10%
    val boreal = Automovil("Boreal", "BMW", "Boeal", 80f, 80f * 0.1f, 0, false) // SUV híbrido con capacidad de 80 litros, inicia con el 10%
    val cefiro = Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0, 500) // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
    val dinamo = Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0, true) // Camioneta eléctrica con capacidad de 70 litros, inicia con el 10%
    val eclipse = Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0, false) // Coupé deportivo con capacidad de 60 litros, inicia con el 10%
    val fenix = Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0, 250) // Motocicleta eléctrica con capacidad de 20 litros, inicia con el 10%




}