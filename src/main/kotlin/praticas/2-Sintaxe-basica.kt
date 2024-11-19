package praticas

class Livro(val titulo: String, val preco: Float) {
    override fun toString(): String {
        return "Livro: Titulo = $titulo, Preco = $preco"
    }
}

fun menu() {
    println("1 - Cadastrar livro")
    println("2 - Excluir livro")
    println("3 - Buscar livro")
    println("4 - Editar livro")
    println("5 - Listar livros")
    println("6 - Listar livros que começam com letra escolhida")
    println("7 - Listar livros com preço abaixo do informado")
    println("8 - Sair")
}

fun inputTitulo(): String {
    print("Digite o titulo do livro: ")
    return readlnOrNull() ?:""
}

fun inputPreco(): Float {
    print("Digite o preco do livro: ")
    val preco = readlnOrNull()!!.toFloat()

    return preco
}

fun cadastrarLivro(repositorio: MutableList<Livro>) {
    val titulo = inputTitulo()
    val preco = inputPreco()

    repositorio.add(Livro(titulo, preco))
    println("\nCadastrado com sucesso!\n")
}

fun excluirLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    repositorio.remove(livro)
    println("Livro removido com sucesso!")
}

fun buscarNome(repositorio: MutableList<Livro>): Livro? {
    val titulo = inputTitulo()
    return repositorio.find { it.titulo == titulo }
}

fun editarLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    if (livro != null) {
        println("Livro encontrado: $livro")
        val novoPreco = inputPreco()
        val livroEditado = Livro(livro.titulo, novoPreco)
        repositorio[repositorio.indexOf(livro)] = livroEditado
        println("Preço do livro atualizado com sucesso!")
    } else {
        println("Livro não encontrado.")
    }
}

fun listar(repositorio: MutableList<Livro>) {
    if (repositorio.isEmpty()) {
        println("Nenhum livro cadastrado.")
    } else {
        println("Lista de livros:")
        repositorio.forEach { println(it) }
    }
}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
    print("Informe a letra: ")
    var letra = readlnOrNull() ?:""

    while(letra.length > 1) {
        print("Informe apenas uma letra: ")
        letra = readlnOrNull() ?:""
    }

    if(letra != "") {
        val livros = repositorio.filter { it.titulo.startsWith(letra) }
        livros.forEach {println(it)}
    } else {
        println("É necessário informar pelo menos um caracter para esta função executar!")
    }
}

fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
    val preco = inputPreco()
    val livros = repositorio.filter { it.preco < preco }
    livros.forEach { println(it) }
}

fun main() {
    val repositorioLivros = mutableListOf<Livro>()
    repositorioLivros.add(Livro("Livro dos Livros", 999999.99f))
    repositorioLivros.add(Livro("Turma da Monica", 4.99f))
    repositorioLivros.add(Livro("Kotlin for Dummies", 29.99f))
    repositorioLivros.add(Livro("A Arte da Guerra", 59.99f))

    var opcao = 0
    while (opcao != 8) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 8

        when (opcao) {
            1 -> cadastrarLivro(repositorioLivros)
            2 -> excluirLivro(repositorioLivros)
            3 -> {
                val livro = buscarNome(repositorioLivros)
                if (livro != null) {
                    println("Livro encontrado: $livro")
                } else {
                    println("Livro não encontrado.")
                }
            }
            4 -> editarLivro(repositorioLivros)
            5 -> listar(repositorioLivros)
            6 -> listarComLetraInicial(repositorioLivros)
            7 -> listarComPrecoAbaixo(repositorioLivros)
            8 -> println("Até a próxima :)")
            else -> println("Opção inválida. Tente novamente.")
        }
        println()
    }
}