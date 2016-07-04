var app = angular.module("Lista", ['datatables']);//[] = Conjunto de modulos a importar
app.service('sharedVar', function () {
    //Service não utilizado, mas deixar como exemplo caso for.
    var listaS ="";
    var codListaP;
    this.codListaP = codListaP;
    this.listaS = listaS;
        
    return {
        getString: function() {
            return listaS;
        },
        setString: function(value) {
            listaS = value;
        },
        setCodigo: function(codigo){
            codListaP = codigo;
        },
        getCodigo: function(){
            return codListaP;
        }
    }
});
    
app.value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
app.controller("ListaComprasController", function ($http, urlBase, $scope,sharedVar) {
       
    var self = this;
    
    self.produtosInLista = []; // Lista de Array para retornar os produtos na lista;
    self.produtos = [] //Lista de Array que vai retornar os produtos cadastrados
    self.quantidade = undefined; 
    self.produto = undefined;
    self.varCodLista = undefined;
    self.listaNome="Lista";
    self.obLista =[];
    self.totalizador=0;
    
    //Carregar Produtos para o SelectMenu
    self.carregarProdutos = function () {
        $http({
            method: 'GET',
            url: urlBase + 'produtos/listarProdLista/' +self.obLista.codLista
        }).then(function successCallback(response) {
            self.produtos = response.data; //JSON que retorna do Backend Java 
            self.quantidade=0;
            if (self.produtos[0] != undefined){ //Caso trazer setar 
                self.produto = self.produtos[0];
            }
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    };
     
    //Carregar Produtos da Lista
    self.carregarProdutosInLista = function(){
        //var codLista = sharedVar.getCodigo(); Não Utilizado - Deixar como exemplo
        self.varCodLista= self.obLista.codLista;
        self.listaNome = self.obLista.nomeLista;
        $http({
            method: 'GET',
            url: urlBase + 'lista/buscarProdutoLista/' +self.varCodLista
        //data: self.lista
        }).then(function successCallback(response) {
            self.produtosInLista = response.data;
            self.atualizarTotal();
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    };

    //Excluindo produto dentro da lista
    self.excluirProdLista = function (produto){
        var codProduto = produto.produto_CodProduto;
        var codLista = produto.lista_CodLista;
        $http({
            method: 'DELETE',
            url: urlBase + 'lista/deletarProLista/' +codProduto +'/'+codLista
        }).then(function successCallback(response) {
            self.produtosInLista = response.data;
            self.carregarProdutosInLista();
            self.carregarProdutos();
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
            
        
    }
    //Metodo para ativar e sempre carregar os produtos no Select
    self.activate = function (){
        self.obLista = JSON.parse(window.sessionStorage.getItem('lista'));
        self.carregarProdutos(); //Carregar o SelectMenu com os produtos para ser add na lista
        self.carregarProdutosInLista(); //e carregar os produtos na lista
    };
    self.activate(); //Ativar 

    self.adicionarProduto = function (){
        var quantidade = self.quantidade; 
        if (quantidade == undefined){
            quantidade = 0;
        }
        var situacao =  false;
        var codProduto = self.produto.codProduto;
        var valorProduto = self.produto.valor;
        var codLista = self.varCodLista;
        //alert(codProduto + ' ' + codLista + ' ' +quantidade + ' ' + situacao);
        var data = {
            'produto_CodProduto' : codProduto, 
            'lista_CodLista' : codLista, 
            'quantidade' : quantidade,
            'situacao' : situacao,
            'valorProduto' : valorProduto
        };
                
        var acao = 'inserirProdutoLista/';
        $http({
            method: 'POST',
            url: urlBase + 'lista/' +acao,
            data: data
        }).then(function successCallback(response) {
            self.carregarProdutosInLista();
            self.carregarProdutos();
            self.atualizarTotal();
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    };
    
    self.ocorreuErro = function () {
        //Falta Implementar.
        alert("Ocorreu um erro inesperado!");
    };

    self.atualizarTotal = function(){
        self.varCodLista= self.obLista.codLista;
        $http({
            method: 'GET',
            url: urlBase + 'lista/sumValorTotal/' +self.varCodLista
        //data: self.lista
        }).then(function successCallback(response) {
            self.totalizador = response.data
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    }

}); //Fim do Module Lista

//Parte que ira fazer a implmentação e requests com a Lista 
//Responsavel por adicionar nova lista, alterar nome, acessar a lista, e excluir
app.value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
app.controller("ListaCadastradas", function ($http, urlBase, $scope,sharedVar,$window ) {
    
    var self = this;
    self.listas = []; //Lista de Array que vai retornar as listas cadastrados
    self.lista = undefined;
    self.listaProdutos = [];
    self.divNovo=false;
    self.divAterar=false;
    
    self.ocorreuErro = function () {
        //Falta Implementar.
        alert("Ocorreu um erro inesperado!");
    };
        
    self.carregarListas = function () {
        $http({
            method: 'GET',
            url: urlBase + 'lista/carregarlista'
        }).then(function successCallback(response) {
            self.listas = response.data; 
            self.lista = undefined;
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    };
        
        
    self.novo = function(){
        self.lista = {}; 
        self.divNovo=true;
        self.divAlterar=false;
    };
        
    self.alterar = function(lista){
        self.lista = lista //Atribuindo a Lista selecionado
        self.divNovo=false;
        self.divAlterar=true;
    };
        
    //Salvar
    self.adicionaLista = function(){
        //Funcionará para atualizar ou inserir a Lista
        //Ira só salvar se existir dados válidos
        if ($scope.formNovaLista.$valid) {
            var metodo = 'POST'; //Inserir
            var acao = 'cadastrar';
            if (self.lista.codLista) {//Caso existir, alterar
                metodo = 'PUT'; //Alterar
                acao ='atualizar';
            }
            $http({
                method: metodo,
                url: urlBase + 'lista/' +acao,
                data: self.lista
            }).then(function successCallback(response) {
                self.carregarListas();
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        } else{
        //Tratamento
        }
    };
        
    self.excluir = function(lista){
        codLista = lista.codLista;
        $http({
            method: 'DELETE',
            url: urlBase + 'lista/' + codLista + '/'
        }).then(function successCallback(response) {
            self.carregarListas();
        }, function errorCallback(response) {
            self.ocorreuErro();
        });
    };
 

    self.abrirLista = function(lista){
        //Chamando o Service que troca valores entre controllers
        //Deixar como exemplo para passar valores entre controllers
        //sharedVar.setCodigo(lista.codLista);
        //sharedVar.setString(lista.nomeLista);
        //Funcionando passar objeto para outra pagina com sessionStorage
        window.sessionStorage.setItem('lista', JSON.stringify(lista));
        //fazer redirect
        $window.location.href = 'lista.html';
    };
    
    //Metodo para ativar e sempre carregar os produtos no Select
    self.activate = function (){
        self.carregarListas();
    };
    self.activate(); //Ativar 
}); //Fim do Module CadastroLista