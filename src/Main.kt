import java.util.*

fun main() {
   //crio uma pilha
    val pilha = LinkedList<String>()

    //adicionando produtos na prateleira
    pilha.push("Sardinha")
    pilha.push("Milho")
    pilha.push("Molho  de Tomate")

    //Acessar primeiro elemento da pilha
    println(pilha.peek())

    //ver tamanho da pilha
    println(pilha.size)

    //Remover um elemento da prateleira(pilha)
    val esgotado = pilha.pop()
    println("esgotados: $esgotado")
    println("Novo produto no topo da pilha: ${pilha.peek()}")

    if(pilha.isEmpty()){
        println("Acabou os produtos!")
    }else{
        println("Ainda existem produtos!")
    }

    //Verifica tudo o que tem na prateleira
    println(pilha)

//------------------------------------------------------------------------------------------------
    //Crio uma fila
    val fila = LinkedList<String>()

    //Adicionar usuarios a fila
    fila.addLast("Joãozinho")
    fila.addLast("Mariazinha")
    fila.addLast("Pedrinho")


    //Vejo o primeiro usuario da fila
    println(fila.peek())

    //ver todos os usuarios da fila
    println(fila)

    do {
        //Faz duas coisas ao mesmo tempo, imprime o usuario e remove ele
        println("O user ${fila.removeFirst()} entrou no servidor")
        Thread.sleep(5000)//Faz o processador parar por 5 segundos
    }while(fila.isNotEmpty())//Enquanto tiver gente na fila, repita
//pode ser usado (!fila.isEmpty()) que vai funcionar igual ja que ! é uma negação


    //-------------------------------------------------------------------------------------------
    //pokemon

    val bubasalro = mapOf(
        "GRAMA" to  listOf("AGUA, TERRA, PEDRA"),// a minha chave é uma string
        "VENENO" to  listOf("FADA, PLANTA"),//meu valor é uma lista de string
        //chave  TO valor
    )
    val ivysaur = mapOf(
        "GRAMA" to  listOf("AGUA", "TERRA", "PEDRA"),
    )
    val venusaur = mapOf(
        "GRAMA" to  listOf("AGUA, TERRA, PEDRA"),
        "VENENO" to  listOf("FADA, PLANTA"),
    )
    val Charmander = mapOf(
        "FOGO" to  listOf("FOGO", "TERRA", "PEDRA"),
    )
    val charmeleon = mapOf(
        "FOGO" to  listOf("FOGO", "TERRA", "PEDRA"),
    )
    val charizard = mapOf(
        "FOGO" to  listOf("FOGO", "TERRA", "PEDRA"),
    )
    val squirtle = mapOf(
    "AGUA" to  listOf("ELETRICO", "GRAMA"),
    )
    val wartortle = mapOf(
        "AGUA" to  listOf("ELETRICO", "GRAMA"),
    )
    val blastoise = mapOf(
        "AGUA" to  listOf("ELETRICO", "GRAMA"),
    )
    val caterpie = mapOf(
        "INSETO" to  listOf("FOGO", "VODOR", "PEDRA"),
    )
    val metapod = mapOf(
        "INSETO" to  listOf("FOGO", "VODOR", "PEDRA"),
    )
    val butterfree = mapOf(
        "INSETO" to  listOf("FOGO","VODOR", "PEDRA"),
    )
    val weedle = mapOf(
        "INSETO" to  listOf("FOGO", "VODOR", "PEDRA"),
        "VENENO" to  listOf("TERRA, PSIQUICO"),

    )
    val beedrill = mapOf(
        "INSETO" to  listOf("FOGO", "VODOR", "PEDRA"),
        "VENENO" to listOf("TERRA", "PSIQUICO")
    )
    val pidgey = mapOf(
        "NORMAL" to  listOf("LUTA"),
        "VOADOR" to  listOf("ELETRICO", "ROCHA", "GEL"),
    )
    val pidgeotto = mapOf(
        "NORMAL" to  listOf("LUTA"),
        "VOADOR" to  listOf("ELETRICO", "ROCHA"),
    )
    val pidgeot = mapOf(
        "NORMAL" to  listOf("LUTA"),
        "VOADOR" to  listOf("ELETRICO", "ROCHA", "GEL"),
        )
    val rattata = mapOf(
        "NORMAL" to  listOf("LUTA"),
    )
    val raticate = mapOf(
        "NORMAL" to  listOf("LUTA"),
    )
    val spearow = mapOf(
        "NORMAL" to  listOf("LUTA"),
        "VOADOR" to  listOf("ELETRICO","ROCHA", "GEL"),
    )
    val fearow = mapOf(
        "NORMAL" to  listOf("LUTA"),
    "VOADOR" to  listOf("ELETRICO", "ROCHA", "GEL"),
    )
    val ekans = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val arbok = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val pikachu = mapOf(
        "ELETRICO" to  listOf("AGUA", "VOADOR"),
    )
    val raichu = mapOf(
        "ELETRICO" to  listOf("AGUA", "VOADOR"),
    )
    val sandshrew = mapOf(
        "TERRA" to  listOf("AGUA", "GEL", "GRAMA"),
    )
    val sandslash = mapOf(
        "TERRA" to  listOf("AGUA", "GEL", "GRAMA"),
    )
    val nidoran = mapOf(
    "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val nidorina = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val nidoqueen =     mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val  nidorano = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val nidorino = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val nidoking = mapOf(
        "VENENO" to  listOf("TERRA", "PSIQUICO"),
    )
    val clefairy = mapOf(
        "FADA" to  listOf("VENENO", "INSETO"),
    )
    val clefable = mapOf(
    "FADA" to  listOf("VENENO", "INSETO"),
    )
    val vulpix = mapOf(
        "FOGO" to  listOf("AGUA", "PEDRA", "TERRA"),
    )
    val ninetales = mapOf(
        "FOGO" to  listOf("AGUA", "PEDRA", "TERRA"),
    )
    val machop = mapOf(
        "LUTA" to  listOf("NORMAL", "GEL", "PEDRA", "ACO"),
    )
    val machoke = mapOf(
        "LUTA" to  listOf("NORMAL", "GEL", "PEDRA", "ACO"),
    )
    val machamp = mapOf(
        "LUTA" to  listOf("NORMAL", "GEL", "PEDRA", "ACO"),
    )
    val bellsprout = mapOf(
        "GRAMA" to listOf("AGUA", "TERA", "PEDRA"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val weepinbell = mapOf(
        "GRAMA" to listOf("AGUA", "TERA", "PEDRA"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val victreebel = mapOf(
        "GRAMA" to listOf("AGUA", "TERA", "PEDRA"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val tentacool = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val tentacruel = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val geodude = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "GEL", "LUTA", "ACO"),
        "TERA" to listOf("GEL"),
    )
    val graveler = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "GEL", "LUTA", "ACO"),
        "TERA" to listOf("GEL"),
    )
    val golem = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "GEL", "LUTA", "ACO"),
        "TERA" to listOf("GEL"),
    )
    val ponyta = mapOf(
        "FOGO" to listOf("AGUA", "PEDRA", "TERA"),
    )
    val rapidash = mapOf(
        "FOGO" to listOf("AGUA", "PEDRA", "TERA"),
    )
    val magnemite = mapOf(
        "ELETRICO" to listOf("AGUA", "GEL", "GRAMA"),
        "ACO" to listOf("FANTASMA"),
    )
    val magneton = mapOf(
        "ELETRICO" to listOf("AGUA", "GEL", "GRAMA"),
        "ACO" to listOf("FANTASMA"),
    )
    val farfetchd = mapOf(
        "VOADOR" to listOf("ELETRICO", "GEL", "PEDRA"),
        "NORMAL" to listOf("LUTA"),
    )
    val Doduo = mapOf(
        "NORMAL" to listOf("LUTA"),
        "VOADOR" to listOf("ELETRICO", "ROCHA", "GEL"),
    )
    val dodrio = mapOf(
        "NORMAL" to listOf("LUTA"),
        "VOADOR" to listOf("ELETRICO", "ROCHA", "GEL"),
    )
    val seel = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "GEL" to listOf("FOGO", "LUTA", "ROCHA"),
    )
    val dewgong = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "GEL" to listOf("FOGO", "LUTA", "ROCHA"),
    )
    val grimer = mapOf(
        "VENENO" to listOf("TERRA", "PSIQUICO"),
    )
    val muk = mapOf(
        "VENENO" to listOf("TERRA", "PSIQUICO"),
    )
    val shellder = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "GEL" to listOf("FOGO", "LUTA", "ROCHA"),
    )
    val cloyster = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "GEL" to listOf("FOGO", "LUTA", "ROCHA"),
    )
    val gastly = mapOf(
        "FANTASMA" to listOf("NORMAL", "PSIQUICO"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val haunter = mapOf(
        "FANTASMA" to listOf("NORMAL", "PSIQUICO"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val gengar = mapOf(
        "FANTASMA" to listOf("NORMAL", "PSIQUICO"),
        "VENENO" to listOf("PSIQUICO"),
    )
    val onix = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "GEL", "LUTA", "ACO"),
        "TERA" to listOf("GEL"),
    )
    val drowzee = mapOf(
        "PSIQUICO" to listOf("PSIQUICO"),
    )
    val hypno = mapOf(
        "PSIQUICO" to listOf("PSIQUICO"),
    )
    val krabby = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val kingler = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val exeggcute = mapOf(
        "GRAMA" to listOf("AGUA", "TERA", "PEDRA"),
        "PSIQUICO" to listOf("INSETO"),
    )

    val cubone = mapOf(
        "TERA" to listOf("AGUA", "GEL", "GRAMA"),
    )
    val marowak = mapOf(
        "TERA" to listOf("AGUA", "GEL", "GRAMA"),
    )
    val horsea = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val seadra = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val goldeen = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val seaking = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val staryu = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val starmie = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val mrMime = mapOf(
        "PSIQUICO" to listOf("PSIQUICO"),
    )
    val scyther = mapOf(
        "INSETO" to listOf("FOGO", "VODOR", "PEDRA"),
    )
    val jynx = mapOf(
        "GEL" to listOf("FOGO", "ACO", "PEDRA"),
        "PSIQUICO" to listOf("INSETO"),
    )
    val electabuzz = mapOf(
        "ELETRICO" to listOf("AGUA", "VOADOR"),
    )
    val magmar = mapOf(
        "FOGO" to listOf("AGUA", "PEDRA", "TERA"),
    )
    val pinsir = mapOf(
        "INSETO" to listOf("FOGO", "VODOR", "PEDRA"),
    )
    val tauros = mapOf(
        "NORMAL" to listOf("LUTA"),
    )
    val magikarp = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val gyarados = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val lapras = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "GEL" to listOf("FOGO", "LUTA", "ROCHA"),
    )
    val ditto = mapOf(
        "NORMAL" to listOf("LUTA"),
    )
    val eevee = mapOf(
        "NORMAL" to listOf("LUTA"),
    )
    val vaporeon = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
    )
    val jolteon = mapOf(
        "ELETRICO" to listOf("AGUA", "VOADOR"),
    )
    val flareon = mapOf(
        "FOGO" to listOf("AGUA", "PEDRA", "TERA"),
    )
    val espeon = mapOf(
        "PSIQUICO" to listOf("PSIQUICO"),
    )
    val umbreon = mapOf(
        "SOMBRIO" to listOf("FADA"),
    )
    val leafeon = mapOf(
        "GRAMA" to listOf("AGUA", "TERA", "PEDRA"),
    )
    val glaceon = mapOf(
        "GEL" to listOf("FOGO", "ACO", "PEDRA"),
    )
    val lckitung = mapOf(
        "NORMAL" to listOf("LUTA"),
    )
    val lickitung = mapOf(
        "NORMAL" to listOf("LUTA"),
    )

    val koffing = mapOf(
        "VENENO" to listOf("PSIQUICO"),
    )

    val weezing = mapOf(
        "VENENO" to listOf("PSIQUICO"),
    )

    val rhyhorn = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "LUTA"),
        "TERRA" to listOf("AGUA", "GELO", "GRAMA"),
    )

    val rhydon = mapOf(
        "ROCHA" to listOf("AGUA", "GRAMA", "LUTA"),
        "TERRA" to listOf("AGUA", "GELO", "GRAMA"),
    )

    val chansey = mapOf(
        "NORMAL" to listOf("LUTA"),
    )

    val tangela = mapOf(
        "GRAMA" to listOf("FOGO", "GELO", "VENENO", "VOADOR", "INSETO"),
    )

    val kangaskhan = mapOf(
        "NORMAL" to listOf("LUTA"),
    )

    val horner = mapOf(
        "ÁGUA" to listOf("ELETRICO", "GRAMA"),
    )

    val golbat = mapOf(
        "VENENO" to listOf("PSIQUICO"),
        "VOADOR" to listOf("ELETRICO", "GELO", "PEDRA"),
    )

    val exeggutor = mapOf(
        "GRAMA" to listOf("FOGO", "GELO", "VENENO", "VOADOR", "INSETO"),
        "PSIQUICO" to listOf("FANTASMA", "INSETO", "SOMBRIO"),
    )

    val sylveon = mapOf(
        "FADA" to listOf("VENENO", "INSETO"),
    )
    val kakuna = mapOf(
        "INSETO" to listOf("FOGO", "PEDRA", "VOADOR")
    )
    val nidoran_f = mapOf(
        "VENENO" to listOf("TERRA", "PSIQUICO")
    )
    val nidoran_m = mapOf(
        "VENENO" to listOf("TERRA", "PSIQUICO")
    )
    val jigglypuff = mapOf(
        "FADA" to listOf("VENENO", "INSETO")
    )
    val wigglytuff = mapOf(
        "FADA" to listOf("VENENO", "INSETO")
    )
    val zubat = mapOf(
        "VENENO" to listOf("PSIQUICO"),
        "VOADOR" to listOf("ELETRICO", "ROCHA")
    )
    val oddish = mapOf(
        "GRAMA" to listOf("FOGO", "GELO", "VENENO")
    )
    val gloom = mapOf(
        "GRAMA" to listOf("FOGO", "GELO", "VENENO")
    )
    val vileplume = mapOf(
        "GRAMA" to listOf("FOGO", "GELO", "VENENO")
    )
    val paras = mapOf(
        "INSETO" to listOf("FOGO", "PEDRA"),
        "GRAMA" to listOf("VOADOR", "VENENO")
    )
    val parasect = mapOf(
        "INSETO" to listOf("FOGO", "PEDRA"),
        "GRAMA" to listOf("VOADOR", "VENENO")
    )
    val venonat = mapOf(
        "INSETO" to listOf("FOGO", "PEDRA", "VOADOR"),
        "VENENO" to listOf("PSIQUICO")
    )
    val venomoth = mapOf(
        "INSETO" to listOf("FOGO", "PEDRA", "VOADOR"),
        "VENENO" to listOf("PSIQUICO")
    )
    val diglett = mapOf(
        "TERRA" to listOf("AGUA", "GELO", "GRAMA")
    )
    val dugtrio = mapOf(
        "TERRA" to listOf("AGUA", "GELO", "GRAMA")
    )
    val meowth = mapOf(
        "NORMAL" to listOf("LUTA")
    )
    val persian = mapOf(
        "NORMAL" to listOf("LUTA")
    )
    val psyduck = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA")
    )
    val golduck = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA")
    )
    val mankey = mapOf(
        "LUTA" to listOf("PSIQUICO", "VOADOR")
    )
    val primeape = mapOf(
        "LUTA" to listOf("PSIQUICO", "VOADOR")
    )
    val growlithe = mapOf(
        "FOGO" to listOf("AGUA", "TERRA", "PEDRA")
    )
    val arcanine = mapOf(
        "FOGO" to listOf("AGUA", "TERRA", "PEDRA")
    )
    val poliwag = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA")
    )
    val poliwhirl = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA")
    )
    val poliwrath = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "LUTA" to listOf("PSIQUICO", "VOADOR")
    )
    val abra = mapOf(
        "PSIQUICO" to listOf("FANTASMA", "INSETO")
    )
    val kadabra = mapOf(
        "PSIQUICO" to listOf("FANTASMA", "INSETO")
    )
    val alakazam = mapOf(
        "PSIQUICO" to listOf("FANTASMA", "INSETO")
    )
    val slowpoke = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "PSIQUICO" to listOf("FANTASMA")
    )
    val slowbro = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "PSIQUICO" to listOf("FANTASMA")
    )
    val voltorb = mapOf(
        "ELETRICO" to listOf("TERRA")
    )
    val electrode = mapOf(
        "ELETRICO" to listOf("TERRA")
    )
    val hitmonlee = mapOf(
        "LUTA" to listOf("PSIQUICO", "VOADOR")
    )
    val hitmonchan = mapOf(
        "LUTA" to listOf("PSIQUICO", "VOADOR")
    )
    val porygon = mapOf(
        "NORMAL" to listOf("LUTA")
    )
    val omanyte = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "ROCHA" to listOf("GRAMA", "LUTA")
    )
    val omastar = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "ROCHA" to listOf("GRAMA", "LUTA")
    )
    val kabuto = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "ROCHA" to listOf("GRAMA", "LUTA")
    )
    val kabutops = mapOf(
        "AGUA" to listOf("ELETRICO", "GRAMA"),
        "ROCHA" to listOf("GRAMA", "LUTA")
    )
    val aerodactyl = mapOf(
        "ROCHA" to listOf("GELO", "AGUA", "ELETRO"),
        "VOADOR" to listOf("ELETRICO", "GELO")
    )
    val snorlax = mapOf(
        "NORMAL" to listOf("LUTA")
    )
    val articuno = mapOf(
        "GELO" to listOf("FOGO", "PEDRA"),
        "VOADOR" to listOf("ELETRICO", "GELO")
    )
    val zapdos = mapOf(
        "ELETRICO" to listOf("TERRA"),
        "VOADOR" to listOf("ROCHA", "GELO")
    )
    val moltres = mapOf(
        "FOGO" to listOf("AGUA", "PEDRA"),
        "VOADOR" to listOf("ELETRICO", "ROCHA")
    )
    val dratini = mapOf(
        "DRAGAO" to listOf("GELO", "FADA")
    )
    val dragonair = mapOf(
        "DRAGAO" to listOf("GELO", "FADA")
    )
    val dragonite = mapOf(
        "DRAGAO" to listOf("GELO", "FADA"),
        "VOADOR" to listOf("ELETRICO", "ROCHA")
    )
    val mewtwo = mapOf(
        "PSIQUICO" to listOf("FANTASMA", "INSETO")
    )
    val mew = mapOf(
        "PSIQUICO" to listOf("FANTASMA", "INSETO")
    )


}