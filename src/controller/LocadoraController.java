package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dal.ClientesDAO;
import model.pessoa.Cliente;
import model.produtos.Filme;
import model.produtos.Jogo;
import model.produtos.Serie;
import model.produtos.produtosUtil.Categoria;
import model.produtos.produtosUtil.Classe;
import model.produtos.produtosUtil.Classificacao;
import model.produtos.produtosUtil.Produto;
import model.transacoes.Atraso;
import model.transacoes.Compra;
import model.transacoes.Emprestimo;

public class LocadoraController {

    private List<Produto> produtos;
    private List<Cliente> clientes;
    private List<Emprestimo> emprestimos;
    private List<Atraso> atrasos;
    private List<Compra> compras;

    public LocadoraController(List<Produto> produtos, List<Cliente> clientes, List<Emprestimo> emprestimos,
            List<Atraso> atrasos, List<Compra> compras) {
        this.produtos = produtos;
        this.clientes = clientes;
        this.emprestimos = emprestimos;
        this.atrasos = atrasos;
        this.compras = compras;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void adicionarAtraso(Atraso atraso) {
        atrasos.add(atraso);
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }

    public void removerAtraso(Atraso atraso) {
        atrasos.remove(atraso);
    }

    public void removerCompra(Compra compra) {
        compras.remove(compra);
    }

    public int gerarNovoCodigo() {
        return produtos.stream()
                .mapToInt(p -> p.getCodigo())
                .max()
                .orElse(0) + 1;
    }

    public boolean cadastrarFilme(String nome, LocalDate dataCadastro, int quantidadeEstoque, String categoria,
            int classe, int classificacaoIndicativa, int duracaoMinutos) {

        Filme filme = Filme.criarFilme(gerarNovoCodigo(), nome, dataCadastro, quantidadeEstoque,
                Categoria.getCategoria(categoria), Classe.getClasse(classe),
                Classificacao.getClasse(classificacaoIndicativa), duracaoMinutos);

        adicionarProduto(filme);

        return true;
    }

    public boolean cadastrarSerie(String nome, LocalDate dataCadastro, int quantidadeEstoque, String categoria,
            int classe, int classificacaoIndicativa, int temporada) {

        Serie serie = Serie.criarSerie(gerarNovoCodigo(), nome, dataCadastro, quantidadeEstoque,
                Categoria.getCategoria(categoria), Classe.getClasse(classe),
                Classificacao.getClasse(classificacaoIndicativa), temporada);
        adicionarProduto(serie);
        return true;
    }

    public boolean cadastrarJogo(String nome, LocalDate dataCadastro, int quantidadeEstoque, String categoria,
            int classe, int classificacaoIndicativa, String plataforma) {

        Jogo jogo = Jogo.criarJogo(gerarNovoCodigo(), nome, dataCadastro, quantidadeEstoque,
                Categoria.getCategoria(categoria), Classe.getClasse(classe),
                Classificacao.getClasse(classificacaoIndicativa), plataforma);

        adicionarProduto(jogo);

        return true;

    }

    public List<String> listarTodosProdutos() {
        return produtos.stream()
                .map(p -> p.exibirInformacoesCruciais() + "\n")
                .toList();
    }

    public List<String> listarJogos() {
        return produtos.stream()
                .filter(p -> p instanceof Jogo)
                .map(p -> p.toString() + "\n")
                .toList();
    }

    public List<String> listarFilmes() {
        return produtos.stream()
                .filter(p -> p instanceof Filme)
                .map(p -> p.toString() + "\n")
                .toList();
    }

    public List<String> listarAtrasos() {
        return atrasos.stream()
                .map(atraso -> atraso.toString() + "\n")
                .toList();
    }

    public List<String> listarUsuarios() {
        return clientes.stream()
                .map(cliente -> cliente.toString() + "\n")
                .toList();
    }

    public List<String> listarSeries() {
        return produtos.stream()
                .filter(p -> p instanceof Serie)
                .map(p -> p.toString() + "\n")
                .toList();
    }

    public List<String> listarEmprestimo() {
        return emprestimos.stream()
                .map(p -> p.toString())
                .toList();
    }

    public boolean cpfValido(long cpf) {

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().getCpf() == cpf) {
                return false;
            }
        }

        for (Cliente c : clientes) {
            if (c.getCpf() == cpf) {
                return true;
            }
        }
        return false;
    }

    public boolean comprarProdutos(List<Integer> codigos, Long cpf) {
        Cliente cliente = acharCliente(cpf);
        if (cliente == null) {
            return false;
        }

        List<Produto> carrinho = new ArrayList<>();
        for (int codigo : codigos) {
            Produto produto = procurarProdutoPorCodigo(codigo);
            if (produto != null) {
                produto.diminuirEstoque();
                carrinho.add(produto);
            }
        }

        Compra compra = Compra.criarCompra(carrinho, cliente, calcularValorCompre(carrinho));
        cliente.adicionarCompra(compra);
        compras.add(compra);
        return true;
    }

    public double calcularValorEmprestimo(List<Produto> carrinho) {
        double valorTotal = 0;
        for (Produto produto : carrinho) {
            valorTotal += produto.getClasse().getPrecoAluguel();
        }
        return valorTotal;
    }

    public double calcularValorCompre(List<Produto> carrinho) {
        double valorTotal = 0;
        for (Produto produto : carrinho) {
            valorTotal += produto.getClasse().getPrecoCompra();
        }
        return valorTotal;
    }

    public boolean emprestarProdutos(List<Produto> carrinhoCliente, Long cpf, LocalDate dataEmprestimo) {

        Cliente cliente = acharCliente(cpf);

        for (Produto produto : carrinhoCliente) {
            produto.diminuirEstoque();
        }

        Emprestimo emprestimo = Emprestimo.criarEmprestimo(carrinhoCliente, cliente, dataEmprestimo,
                dataEmprestimo.plusDays(7), calcularValorEmprestimo(carrinhoCliente));

        cliente.adicionarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);

        return true;

    }

    public LocalDate dataStringParaLocaLDate(String dataUser) {
        if (dataUser.contains("-")) {
            dataUser = dataUser.replaceAll("-", "/");
        }
        DateTimeFormatter frmtReceber = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataConvertida = LocalDate.parse(dataUser, frmtReceber);
        return dataConvertida;
    }

    public int contarDias(LocalDate dataMaior, LocalDate dataMenor) {
        Period periodo = Period.between(dataMenor, dataMaior);

        int dias = periodo.getDays();
        int meses = periodo.getMonths();
        int anos = periodo.getYears();

        return dias + (meses * 30) + (anos * 365);
    }

    public double calcularMulta(double valorCompra, int diasAtraso) {
        double porcentagemPorDia = 0.05;
        double multa = valorCompra * porcentagemPorDia * diasAtraso;
        return multa;
    }

    public boolean devolverProdutos(Long cpf, String dataDevolvida, List<Integer> codigosParaDevolver) {
        LocalDate dataDevolvidaCerta = dataStringParaLocaLDate(dataDevolvida.trim());

        Emprestimo emprestimoEncontrado = null;

        
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().getCpf() == cpf) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado == null) {
            return false; 
        }

        List<Produto> produtosParaRemover = new ArrayList<>();
        List<Produto> produtosEntreguesComAtraso = new ArrayList<>();

        // Verifica e remove os produtos do empréstimo
        for (Produto produto : new ArrayList<>(emprestimoEncontrado.getProdutosEmprestados())) {
            if (codigosParaDevolver.contains(produto.getCodigo())) {
                // Atualiza estoque
                produto.aumentarEstoque();

                // Verifica atraso
                long diasPrevisto = contarDias(emprestimoEncontrado.getDataEmprestimo(),
                        emprestimoEncontrado.getDataDevolucao());
                long diasEfetivo = contarDias(emprestimoEncontrado.getDataEmprestimo(), dataDevolvidaCerta);

                if (diasEfetivo > diasPrevisto) {
                    produtosEntreguesComAtraso.add(produto);
                }

                // Remove produto do empréstimo
                emprestimoEncontrado.removerProdutosEmprestimo(produto);
            }
        }

        if (!produtosEntreguesComAtraso.isEmpty()) {
            int diasAtraso = contarDias(emprestimoEncontrado.getDataDevolucao(), dataDevolvidaCerta);
            double multa = calcularMulta(emprestimoEncontrado.getPrecoEmprestimo(), diasAtraso);

            Atraso atraso = Atraso.criarAtraso(
                    emprestimoEncontrado.getCliente(),
                    produtosEntreguesComAtraso,
                    diasAtraso,
                    multa);
            adicionarAtraso(atraso);
        }

        if (emprestimoEncontrado.getProdutosEmprestados().isEmpty()) {
            emprestimos.remove(emprestimoEncontrado);
        }

        return true;

    }
    public Emprestimo localizarEmprestimo(Long cpf) {
        for (Emprestimo e : emprestimos) {
            if (e.getCliente().getCpf() == cpf) {
                return e;
            }
        }
        return null;
    }
    public void alterarDataDevolucao(Long cpf , String dataNova ){
        
        LocalDate dataNovaLocalDate = dataStringParaLocaLDate(dataNova);
        Emprestimo emprestimo = localizarEmprestimo(cpf);
        emprestimo.setDataDevolucao(dataNovaLocalDate);

    }

    public boolean cadastrarCliente(String nome, String email, String endereco, long cpf, long numero,
            String dataNascimento) {

        Cliente cliente = Cliente.criarCliente(nome, email, endereco, cpf, numero,
                dataStringParaLocaLDate(dataNascimento));
        adicionarCliente(cliente);
        return true;

    }

    public Cliente acharCliente(Long cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf() == cpf)
                .findFirst()
                .orElse(null);
    }

    public boolean clientePossuiEmprestimo(Long cpf) {
        Cliente cliente = acharCliente(cpf);
        if (cliente.getProdutosAlugados().isEmpty()) {
            return false;
        }
        return true;

    }

    public Produto procurarProdutoPorCodigo(int codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public boolean verificarCodigo(int codigo, List<Produto> carrinho) {

        for (Produto produtoCarrinho : carrinho) {
            if (produtoCarrinho.getCodigo() == codigo) {
                return false;
            }
        }

        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    public void salvar() throws IOException{
        ClientesDAO.salvar(clientes);
    }

    public static List<Cliente> carregar() throws IOException, ClassNotFoundException{
        return ClientesDAO.carregar();
    }
    
}
