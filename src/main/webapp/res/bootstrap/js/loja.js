angular.module("Produtos", [])//[] = Conjunto de modulos a importar
    .value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
    .controller("ProdutosController", function ($http, urlBase) {
        
        var self = this;
        self.produtos = [] //Lista de Array que vai retornar os produtos
        self.produto = undefined; //Deixando indefinido de inicio
       
        //Dar um novo na Página para inserir os dados via input.
        //O produto passa a existir
        self.novo = function(){
            self.produto = {}; //É igual a um objeto vazio, deixando de ser
        //Undefined, para assim exibir a div no produtos.html para poder inserir
        };
       
        //Salvar
        self.adicionaProduto = function(){
            var metodo = 'POST';
            if (self.produto.codProduto) {//Caso existir, alterar
                metodo = 'PUT';
            }

            $http({
                method: metodo,
                url: urlBase + 'produtos/',
                data: self.produto
            }).then(function successCallback(response) {
                self.atualizarTabela();
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        };
       
        self.alterar = function(produto){
            self.produto = produto
        };
        
        self.excluir = function(produto){
            self.produto = produto;

            $http({
                method: 'DELETE',
                url: urlBase + 'produtos/' + self.produto.codProduto + '/'
            }).then(function successCallback(response) {
                self.atualizarTabela();
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        };
 
        self.ocorreuErro = function () {
            alert("Ocorreu um erro inesperado!");
        };

        self.atualizarTabela = function () {
            $http({
                method: 'GET',
                url: urlBase + 'produtos/'
            }).then(function successCallback(response) {
                self.produtos = response.data; //JSON que retorna do Backend Java 
                self.produto = undefined;
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        };

        //Metodo para ativar e sempre atualizar a tabela
        self.activate = function (){
            self.atualizarTabela();
        };
        self.activate(); //Ativar sempre e atualizar
    });
        
            
