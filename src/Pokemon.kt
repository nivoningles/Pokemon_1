/*
Acadêmicos:
    Nivon Ingles
    Jorge Campiol
    Raissa Bonfim
 */

import kotlin.random.Random
import kotlin.collections.mapOf

fun calcularDano(
        ataque: Int,
        defesa: Double,
        especial: Boolean,
        tiposAtacanteEfetivos: List<String>,
        tiposDefensorEfetivos: List<String>,
        tipoDefensor: String,
        tipoAtacante: String
    ): Int {
        var baseDano = if (especial) ataque * 2 else ataque

        if (tiposAtacanteEfetivos.contains(tipoDefensor)) {
            baseDano = (baseDano * 2).toInt()
        }

        var defesaModificada = defesa
        if (tiposDefensorEfetivos.contains(tipoAtacante)) {
            defesaModificada *= 1.4
        }

        return (baseDano / defesaModificada).toInt()
    }

fun exibirTime(nomeTime: String, time: List<Map<String, Any>>) {
    println("Time do $nomeTime:")
    time.forEach { println("- ${it["NOME"]}") }
}

fun escolherPokemon(nomeTime: String, time: List<Map<String, Any>>): Int {
    println("$nomeTime, escolha um Pokémon para a batalha:")
    time.forEachIndexed { index, pokemon ->
        println("${index + 1}. ${pokemon["NOME"]}")
    }
    return readLine()?.toIntOrNull()?.minus(1) ?: 0
}

fun batalha(
        nomeTime1: String,
        time1: MutableList<Map<String, Any>>,
        nomeTime2: String,
        time2: MutableList<Map<String, Any>>
    ) 
    {
        var indice1 = escolherPokemon(nomeTime1, time1)
        var indice2 = escolherPokemon(nomeTime2, time2)
        var trocaUsada1 = false
        var trocaUsada2 = false
        var contAtaque1 = 0
        var contAtaque2 = 0

        while (true) {
            exibirTime(nomeTime1, time1)
            exibirTime(nomeTime2, time2)

            val poke1 = time1[indice1]
            val poke2 = time2[indice2]

            val atributos1 = poke1["ATRIBUTOS"] as MutableMap<String, Any>
            val atributos2 = poke2["ATRIBUTOS"] as MutableMap<String, Any>

            val vel1 = atributos1["VELOCIDADE"] as Int
            val vel2 = atributos2["VELOCIDADE"] as Int

            val (jogador1, jogador2) = if (vel1 >= vel2) 1 to 2 else 2 to 1

            for (jogador in listOf(jogador1, jogador2)) {
                val atacante = if (jogador == 1) poke1 else poke2
                val defensor = if (jogador == 1) poke2 else poke1
                val nomeTimeAtacante = if (jogador == 1) nomeTime1 else nomeTime2
                val nomeTimeDefensor = if (jogador == 1) nomeTime2 else nomeTime1

                val atkAttr = atacante["ATRIBUTOS"] as MutableMap<String, Any>
                val defAttr = defensor["ATRIBUTOS"] as MutableMap<String, Any>

                val contAtaque = if (jogador == 1) contAtaque1 else contAtaque2

                val tipoAtacante = (atacante["TIPO_EFETIVO"] as List<String>).firstOrNull() ?: ""
                val tipoDefensor = (defensor["TIPO_EFETIVO"] as List<String>).firstOrNull() ?: ""

                val dano: Int
                println("$nomeTimeAtacante (${atacante["NOME"]}) - HP: ${atkAttr["HP"]} | $nomeTimeDefensor: ${defensor["NOME"]} - HP: ${defAttr["HP"]}")
                println("1. Ataque normal")
                println("2. ${if (contAtaque >= 2) "Ataque especial" else "Ataque especial (indisponível)"}")
                println("3. Trocar Pokémon")

                val escolha = readLine()?.toIntOrNull() ?: 1

                when (escolha) {
                    1 -> {
                        val tiposAtacanteEfetivos = atacante["TIPO_EFETIVO"] as List<String>
                        val tiposDefensorEfetivos = defensor["TIPO_EFETIVO"] as List<String>
                        dano = calcularDano(
                            atkAttr["ATAQUE"] as Int,
                            defAttr["DEFESA"] as Double,
                            especial = false,
                            tiposAtacanteEfetivos = tiposAtacanteEfetivos,
                            tiposDefensorEfetivos = tiposDefensorEfetivos,
                            tipoDefensor = tipoDefensor,
                            tipoAtacante = tipoAtacante
                        )
                        defAttr["HP"] = (defAttr["HP"] as Int - dano).coerceAtLeast(0)
                        if (jogador == 1) contAtaque1++ else contAtaque2++
                        println("${atacante["NOME"]} deu $dano de dano!")

                        if (tiposAtacanteEfetivos.contains(tipoDefensor)) {
                            println("É super efetivo!")
                        }

                        if (tiposDefensorEfetivos.contains(tipoAtacante)) {
                            println("A defesa foi reforçada por tipo!")
                        }
                    }

                    2 -> {
                        if (contAtaque >= 2) {
                            println("${atacante["NOME"]} usou um ataque especial!")
                            val tiposAtacanteEfetivos = atacante["TIPO_EFETIVO"] as List<String>
                            val tiposDefensorEfetivos = defensor["TIPO_EFETIVO"] as List<String>
                            dano = calcularDano(
                                atkAttr["ATAQUE"] as Int,
                                defAttr["DEFESA"] as Double,
                                especial = true,
                                tiposAtacanteEfetivos = tiposAtacanteEfetivos,
                                tiposDefensorEfetivos = tiposDefensorEfetivos,
                                tipoDefensor = tipoDefensor,
                                tipoAtacante = tipoAtacante
                            )
                            defAttr["HP"] = (defAttr["HP"] as Int - dano).coerceAtLeast(0)
                            if (jogador == 1) contAtaque1 = 0 else contAtaque2 = 0
                            println("${atacante["NOME"]} causou $dano de dano com o ataque especial!")

                            if (tiposAtacanteEfetivos.contains(tipoDefensor)) {
                                println("É super efetivo!")
                            }

                            if (tiposDefensorEfetivos.contains(tipoAtacante)) {
                                println("A defesa foi reforçada por tipo!")
                            }
                        } else {
                            println("Ataque especial ainda não disponível!")
                            continue
                        }
                    }

                    3 -> {
                        if ((jogador == 1 && !trocaUsada1) || (jogador == 2 && !trocaUsada2)) {
                            val novoIndice = escolherPokemon(if (jogador == 1) nomeTime1 else nomeTime2, if (jogador == 1) time1 else time2)
                            if (jogador == 1) {
                                indice1 = novoIndice
                                trocaUsada1 = true
                            } else {
                                indice2 = novoIndice
                                trocaUsada2 = true
                            }
                            println("Pokémon trocado!")
                            continue
                        } else {
                            println("Você já usou sua troca!")
                            continue
                        }
                    }
                }

                if (defAttr["HP"] as Int <= 0) {
                    println("${defensor["NOME"]} foi derrotado!")
                    if (jogador == 1) {
                        time2.removeAt(indice2)
                        if (time2.isEmpty()) {
                            println("$nomeTime1 venceu a batalha!")
                            return
                        }
                        indice2 = 0
                    } else {
                        time1.removeAt(indice1)
                        if (time1.isEmpty()) {
                            println("$nomeTime2 venceu a batalha!")
                            return
                        }
                        indice1 = 0
                    }
                    break
                }
            }
        }
    }

fun main() {
    val efetivo = mapOf(
        "ÁGUA" to listOf("FOGO", "PEDRA", "TERRA"),
        "GRAMA" to listOf("ÁGUA", "TERRA", "PEDRA"),
        "FOGO" to listOf("METAL", "GRAMA", "INSETO", "GELO"),
        "ELÉTRICO" to listOf("ÁGUA", "VOADOR"),
        "PEDRA" to listOf("VOADOR", "GELO", "FOGO", "INSETO"),
        "TERRA" to listOf("ELÉTRICO", "PEDRA", "VENENO", "METAL", "FOGO"),
        "INSETO" to listOf("GRAMA", "PSÍQUICO", "SOMBRIO"),
        "VOADOR" to listOf("GRAMA", "INSETO", "LUTADOR"),
        "VENENO" to listOf("FADA", "PLANTA"),
        "FADA" to listOf("DRAGÃO", "LUTADOR", "SOMBRIO"),
        "NORMAL" to listOf(""),
        "LUTADOR" to listOf("NORMAL", "PEDRA", "METAL"),
        "PSÍQUICO" to listOf("VENENO", "LUTADOR"),
        "GELO" to listOf("TERRA", "GRAMA", "DRAGÃO", "VOADOR"),
        "METAL" to listOf("FADA", "PEDRA", "GELO"),
        "FANTASMA" to listOf("PSÍQUICO", "FANTASMA"),
        "DRAGÃO" to listOf("DRAGÃO")
    )

    fun cria_atributo(
        velocidade: Int,
        defesa: Double,
        ataque: Int,
        hp: Int,
        hpmax: Int
    ): Map<String, Any> {
        return mapOf(
            "VELOCIDADE" to velocidade,
            "DEFESA" to defesa,
            "ATAQUE" to ataque,
            "HP" to hp,
            "HPMAX" to hpmax
        )
    }

    fun gerarAtributosAleatorios(): Map<String, Any> {
        val velocidade = (1..100).random()
        val defesa = Random.nextDouble(1.0, 5.0)
        val ataque = (10..100).random()
        val hp = (100..175).random()
        val hpmax = hp
        return cria_atributo(velocidade, defesa, ataque, hp, hpmax)
    }

    val bulbasaur = mapOf(
        "NOME" to "Bulbasaur",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val ivysaur = mapOf(
        "NOME" to "Ivysaur",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val venosaur = mapOf(
        "NOME" to "Venusaur",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val charmander = mapOf(
        "NOME" to "Charmander",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val charmeleon = mapOf(
        "NOME" to "Charmeleon",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val charizard = mapOf(
        "NOME" to "Charizard",
        "TIPO_EFETIVO" to listOf("FOGO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val squirtle = mapOf(
        "NOME" to "Squirtle",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val wartortle = mapOf(
        "NOME" to "Wartortle",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val blastoise = mapOf(
        "NOME" to "Blastoise",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val caterpie = mapOf(
        "NOME" to "Caterpie",
        "TIPO_EFETIVO" to efetivo["INSETO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val metapod = mapOf(
        "NOME" to "Metapod",
        "TIPO_EFETIVO" to efetivo["INSETO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val butterfree = mapOf(
        "NOME" to "Butterfree",
        "TIPO_EFETIVO" to listOf("INSETO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val weedle = mapOf(
        "NOME" to "Weedle",
        "TIPO_EFETIVO" to listOf("INSETO", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kakuna = mapOf(
        "NOME" to "Kakuna",
        "TIPO_EFETIVO" to listOf("INSETO", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val beedrill = mapOf(
        "NOME" to "Beedrill",
        "TIPO_EFETIVO" to listOf("INSETO", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val pidgey = mapOf(
        "NOME" to "Pidgey",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val pidgeotto = mapOf(
        "NOME" to "Pidgeotto",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val pidgeot = mapOf(
        "NOME" to "Pidgeot",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val rattata = mapOf(
        "NOME" to "Rattata",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val raticate = mapOf(
        "NOME" to "Raticate",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val spearow = mapOf(
        "NOME" to "Spearow",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val fearow = mapOf(
        "NOME" to "Fearow",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val ekans = mapOf(
        "NOME" to "Ekans",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val arbok = mapOf(
        "NOME" to "Arbok",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val pikachu = mapOf(
        "NOME" to "Pikachu",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val raichu = mapOf(
        "NOME" to "Raichu",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val sandshrew = mapOf(
        "NOME" to "Sandshrew",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val sandslash = mapOf(
        "NOME" to "Sandslash",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidoran_f = mapOf(
        "NOME" to "Nidoran♀",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidorina = mapOf(
        "NOME" to "Nidorina",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidoqueen = mapOf(
        "NOME" to "Nidoqueen",
        "TIPO_EFETIVO" to listOf("VENENO", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidoran_m = mapOf(
        "NOME" to "Nidoran♂",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidorino = mapOf(
        "NOME" to "Nidorino",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val nidoking = mapOf(
        "NOME" to "Nidoking",
        "TIPO_EFETIVO" to listOf("VENENO", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val clefairy = mapOf(
        "NOME" to "Clefairy",
        "TIPO_EFETIVO" to efetivo["FADA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val clefable = mapOf(
        "NOME" to "Clefable",
        "TIPO_EFETIVO" to efetivo["FADA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val vulpix = mapOf(
        "NOME" to "Vulpix",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val ninetales = mapOf(
        "NOME" to "Ninetales",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val jigglypuff = mapOf(
        "NOME" to "Jigglypuff",
        "TIPO_EFETIVO" to efetivo["FADA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val wigglytuff = mapOf(
        "NOME" to "Wigglytuff",
        "TIPO_EFETIVO" to efetivo["FADA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val zubat = mapOf(
        "NOME" to "Zubat",
        "TIPO_EFETIVO" to listOf("VENENO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val golbat = mapOf(
        "NOME" to "Golbat",
        "TIPO_EFETIVO" to listOf("VENENO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val oddish = mapOf(
        "NOME" to "Oddish",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val gloom = mapOf(
        "NOME" to "Gloom",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val vileplume = mapOf(
        "NOME" to "Vileplume",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val paras = mapOf(
        "NOME" to "Paras",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val parasect = mapOf(
        "NOME" to "Parasect",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val venonat = mapOf(
        "NOME" to "Venonat",
        "TIPO_EFETIVO" to listOf("INSETO", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val venomoth = mapOf(
        "NOME" to "Venomoth",
        "TIPO_EFETIVO" to listOf("INSETO", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val diglett = mapOf(
        "NOME" to "Diglett",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dugtrio = mapOf(
        "NOME" to "Dugtrio",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val meowth = mapOf(
        "NOME" to "Meowth",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val persian = mapOf(
        "NOME" to "Persian",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val psyduck = mapOf(
        "NOME" to "Psyduck",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val golduck = mapOf(
        "NOME" to "Golduck",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val mankey = mapOf(
        "NOME" to "Mankey",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val primeape = mapOf(
        "NOME" to "Primeape",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val poliwag = mapOf(
        "NOME" to "Poliwag",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val poliwhirl = mapOf(
        "NOME" to "Poliwhirl",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val poliwrath = mapOf(
        "NOME" to "Poliwrath",
        "TIPO_EFETIVO" to listOf("ÁGUA", "LUTADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val abra = mapOf(
        "NOME" to "Abra",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kadabra = mapOf(
        "NOME" to "Kadabra",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val alakazam = mapOf(
        "NOME" to "Alakazam",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val machop = mapOf(
        "NOME" to "Machop",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val machoke = mapOf(
        "NOME" to "Machoke",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val machamp = mapOf(
        "NOME" to "Machamp",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val bellsprout = mapOf(
        "NOME" to "Bellsprout",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val weepinbell = mapOf(
        "NOME" to "Weepinbell",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val victreebel = mapOf(
        "NOME" to "Victreebel",
        "TIPO_EFETIVO" to listOf("GRAMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val tentacool = mapOf(
        "NOME" to "Tentacool",
        "TIPO_EFETIVO" to listOf("ÁGUA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val tentacruel = mapOf(
        "NOME" to "Tentacruel",
        "TIPO_EFETIVO" to listOf("ÁGUA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val geodude = mapOf(
        "NOME" to "Geodude",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val graveler = mapOf(
        "NOME" to "Graveler",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val golem = mapOf(
        "NOME" to "Golem",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val ponyta = mapOf(
        "NOME" to "Ponyta",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val rapidash = mapOf(
        "NOME" to "Rapidash",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val slowpoke = mapOf(
        "NOME" to "Slowpoke",
        "TIPO_EFETIVO" to listOf("ÁGUA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val slowbro = mapOf(
        "NOME" to "Slowbro",
        "TIPO_EFETIVO" to listOf("ÁGUA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val magnemite = mapOf(
        "NOME" to "Magnemite",
        "TIPO_EFETIVO" to listOf("ELÉTRICO", "METAL").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val magneton = mapOf(
        "NOME" to "Magneton",
        "TIPO_EFETIVO" to listOf("ELÉTRICO", "METAL").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val farfetchd = mapOf(
        "NOME" to "Farfetch'd",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val doduo = mapOf(
        "NOME" to "Doduo",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dodrio = mapOf(
        "NOME" to "Dodrio",
        "TIPO_EFETIVO" to listOf("NORMAL", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val seel = mapOf(
        "NOME" to "Seel",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dewgong = mapOf(
        "NOME" to "Dewgong",
        "TIPO_EFETIVO" to listOf("ÁGUA", "GELO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val grimer = mapOf(
        "NOME" to "Grimer",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val muk = mapOf(
        "NOME" to "Muk",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val shellder = mapOf(
        "NOME" to "Shellder",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val cloyster = mapOf(
        "NOME" to "Cloyster",
        "TIPO_EFETIVO" to listOf("ÁGUA", "GELO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val gastly = mapOf(
        "NOME" to "Gastly",
        "TIPO_EFETIVO" to listOf("FANTASMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val haunter = mapOf(
        "NOME" to "Haunter",
        "TIPO_EFETIVO" to listOf("FANTASMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val gengar = mapOf(
        "NOME" to "Gengar",
        "TIPO_EFETIVO" to listOf("FANTASMA", "VENENO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val onix = mapOf(
        "NOME" to "Onix",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val drowzee = mapOf(
        "NOME" to "Drowzee",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val hypno = mapOf(
        "NOME" to "Hypno",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val krabby = mapOf(
        "NOME" to "Krabby",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kingler = mapOf(
        "NOME" to "Kingler",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val voltorb = mapOf(
        "NOME" to "Voltorb",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val electrode = mapOf(
        "NOME" to "Electrode",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val exeggcute = mapOf(
        "NOME" to "Exeggcute",
        "TIPO_EFETIVO" to listOf("GRAMA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val exeggutor = mapOf(
        "NOME" to "Exeggutor",
        "TIPO_EFETIVO" to listOf("GRAMA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val cubone = mapOf(
        "NOME" to "Cubone",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val marowak = mapOf(
        "NOME" to "Marowak",
        "TIPO_EFETIVO" to efetivo["TERRA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val hitmonlee = mapOf(
        "NOME" to "Hitmonlee",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val hitmonchan = mapOf(
        "NOME" to "Hitmonchan",
        "TIPO_EFETIVO" to efetivo["LUTADOR"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val lickitung = mapOf(
        "NOME" to "Lickitung",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val koffing = mapOf(
        "NOME" to "Koffing",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val weezing = mapOf(
        "NOME" to "Weezing",
        "TIPO_EFETIVO" to efetivo["VENENO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val rhyhorn = mapOf(
        "NOME" to "Rhyhorn",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val rhydon = mapOf(
        "NOME" to "Rhydon",
        "TIPO_EFETIVO" to listOf("PEDRA", "TERRA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val chansey = mapOf(
        "NOME" to "Chansey",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val tangela = mapOf(
        "NOME" to "Tangela",
        "TIPO_EFETIVO" to efetivo["GRAMA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kangaskhan = mapOf(
        "NOME" to "Kangaskhan",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val horsea = mapOf(
        "NOME" to "Horsea",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val seadra = mapOf(
        "NOME" to "Seadra",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val goldeen = mapOf(
        "NOME" to "Goldeen",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val seaking = mapOf(
        "NOME" to "Seaking",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val staryu = mapOf(
        "NOME" to "Staryu",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val starmie = mapOf(
        "NOME" to "Starmie",
        "TIPO_EFETIVO" to listOf("ÁGUA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val mr_mime = mapOf(
        "NOME" to "Mr. Mime",
        "TIPO_EFETIVO" to listOf("FADA", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val scyther = mapOf(
        "NOME" to "Scyther",
        "TIPO_EFETIVO" to listOf("INSETO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val jynx = mapOf(
        "NOME" to "Jynx",
        "TIPO_EFETIVO" to listOf("GELO", "PSÍQUICO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val electabuzz = mapOf(
        "NOME" to "Electabuzz",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val magmar = mapOf(
        "NOME" to "Magmar",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val pinsir = mapOf(
        "NOME" to "Pinsir",
        "TIPO_EFETIVO" to efetivo["INSETO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val tauros = mapOf(
        "NOME" to "Tauros",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val magikarp = mapOf(
        "NOME" to "Magikarp",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val gyarados = mapOf(
        "NOME" to "Gyarados",
        "TIPO_EFETIVO" to listOf("ÁGUA", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val lapras = mapOf(
        "NOME" to "Lapras",
        "TIPO_EFETIVO" to listOf("ÁGUA", "GELO").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val ditto = mapOf(
        "NOME" to "Ditto",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val eevee = mapOf(
        "NOME" to "Eevee",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val vaporeon = mapOf(
        "NOME" to "Vaporeon",
        "TIPO_EFETIVO" to efetivo["ÁGUA"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val jolteon = mapOf(
        "NOME" to "Jolteon",
        "TIPO_EFETIVO" to efetivo["ELÉTRICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val flareon = mapOf(
        "NOME" to "Flareon",
        "TIPO_EFETIVO" to efetivo["FOGO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val porygon = mapOf(
        "NOME" to "Porygon",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val omanyte = mapOf(
        "NOME" to "Omanyte",
        "TIPO_EFETIVO" to listOf("PEDRA", "ÁGUA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val omastar = mapOf(
        "NOME" to "Omastar",
        "TIPO_EFETIVO" to listOf("PEDRA", "ÁGUA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kabuto = mapOf(
        "NOME" to "Kabuto",
        "TIPO_EFETIVO" to listOf("PEDRA", "ÁGUA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val kabutops = mapOf(
        "NOME" to "Kabutops",
        "TIPO_EFETIVO" to listOf("PEDRA", "ÁGUA").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val aerodactyl = mapOf(
        "NOME" to "Aerodactyl",
        "TIPO_EFETIVO" to listOf("PEDRA", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val snorlax = mapOf(
        "NOME" to "Snorlax",
        "TIPO_EFETIVO" to efetivo["NORMAL"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val articuno = mapOf(
        "NOME" to "Articuno",
        "TIPO_EFETIVO" to listOf("GELO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val zapdos = mapOf(
        "NOME" to "Zapdos",
        "TIPO_EFETIVO" to listOf("ELÉTRICO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val moltres = mapOf(
        "NOME" to "Moltres",
        "TIPO_EFETIVO" to listOf("FOGO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dratini = mapOf(
        "NOME" to "Dratini",
        "TIPO_EFETIVO" to efetivo["DRAGÃO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dragonair = mapOf(
        "NOME" to "Dragonair",
        "TIPO_EFETIVO" to efetivo["DRAGÃO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val dragonite = mapOf(
        "NOME" to "Dragonite",
        "TIPO_EFETIVO" to listOf("DRAGÃO", "VOADOR").flatMap { efetivo[it]!! },
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val mewtwo = mapOf(
        "NOME" to "Mewtwo",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )
    val mew = mapOf(
        "NOME" to "Mew",
        "TIPO_EFETIVO" to efetivo["PSÍQUICO"]!!,
        "ATRIBUTOS" to gerarAtributosAleatorios()
    )

    val todosPokemons = mutableListOf(
        bulbasaur, ivysaur, venosaur, charmander, charmeleon, charizard,
        squirtle, wartortle, blastoise, caterpie, metapod, butterfree,
        weedle, kakuna, beedrill, pidgey, pidgeotto, pidgeot, rattata,
        raticate, spearow, fearow, ekans, arbok, pikachu, raichu, sandshrew,
        sandslash, nidoran_f, nidorina, nidoqueen, nidoran_m, nidorino,
        nidoking, clefairy, clefable, vulpix, ninetales, jigglypuff,
        wigglytuff, zubat, golbat, oddish, gloom, vileplume, paras, parasect,
        venonat, venomoth, diglett, dugtrio, meowth, persian, psyduck, golduck,
        mankey, primeape, poliwag, poliwhirl, poliwrath, abra, kadabra,
        alakazam, machop, machoke, machamp, bellsprout, weepinbell, victreebel,
        tentacool, tentacruel, geodude, graveler, golem, ponyta, rapidash,
        slowpoke, slowbro, magnemite, magneton, farfetchd, doduo, dodrio, seel,
        dewgong, grimer, muk, shellder, cloyster, gastly, haunter, gengar, onix,
        drowzee, hypno, krabby, kingler, voltorb, electrode, exeggcute,
        exeggutor, cubone, marowak, hitmonlee, hitmonchan, lickitung, koffing,
        weezing, rhyhorn, rhydon, chansey, tangela, kangaskhan, horsea, seadra,
        goldeen, seaking, staryu, starmie, mr_mime, scyther, jynx, electabuzz,
        magmar, pinsir, tauros, magikarp, gyarados, lapras, ditto, eevee,
        vaporeon, jolteon, flareon, porygon, omanyte, omastar, kabuto, kabutops,
        aerodactyl, snorlax, articuno, zapdos, moltres, dratini, dragonair,
        dragonite, mewtwo, mew
    )

    fun escolherTimeJogador(nomeJogador: String, listaParaEscolha: MutableList<Map<String, Any>>): MutableList<Map<String, Any>> {
        val time = mutableListOf<Map<String, Any>>()
        val opcoes = listaParaEscolha.toMutableList()
        var i = 1
        while (i <= 3) {
            println("$nomeJogador, escolha o Pokémon número $i:")
            for ((index, pokemon) in opcoes.withIndex()) {
                println("${index + 1}. ${pokemon["NOME"]}")
            }

            val escolha = readLine()?.toIntOrNull()

            if (escolha != null && escolha in 1..opcoes.size) {
                val escolhido = opcoes[escolha - 1]
                time.add(escolhido)
                opcoes.removeAt(escolha - 1)
                i++
            } else {
                println("Escolha inválida! Tente novamente.")
            }
        }
        return time
    }

    println("Jogador 1, escolha o nome do seu time Pokémon: ")
    val nomeTime1 = readln()
    println("Jogador 2, escolha o nome do seu time Pokémon: ")
    val nomeTime2 = readln()

    val timeDoJogador1 = escolherTimeJogador(nomeTime1, todosPokemons.toMutableList())
    val disponiveisParaJogador2 = todosPokemons.filter { it !in timeDoJogador1 }.toMutableList()
    val timeDoJogador2 = escolherTimeJogador(nomeTime2, disponiveisParaJogador2)

    exibirTime(nomeTime1, timeDoJogador1)
    exibirTime(nomeTime2, timeDoJogador2)

    println("Hora da batalha!")
    batalha(nomeTime1, timeDoJogador1, nomeTime2, timeDoJogador2)
}