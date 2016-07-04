angular.module("Produtos", ['datatables'])//[] = Conjunto de modulos a importar
    .value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
    .controller("ProdutosController", function ($http, urlBase, $scope) {
       
        //Configurando tabela com AngularJS e Datatable
        $(document).ready( function() {
            $('#lista-produtos').dataTable();
        } );

        
        
        var self = this;
        self.produtos = []; //Lista de Array que vai retornar os produtos
        self.produto = undefined; //Deixando indefinido de inicio
        self.divNovo=false;
        self.divAterar=false;
    
        //Dar um novo na Página para inserir os dados via input.
        //O produto passa a existir
        self.novo = function(){
            self.produto = {}; //É igual a um objeto vazio, deixando de ser
            //Undefined, para assim exibir a div no produtos.html para poder inserir
            self.divNovo=true;
            self.divAlterar=false;
        };
       
        //Salvar
        self.adicionaProduto = function(){
            //Funcionará para atualizar ou inserir produtos
            //alert($('#nomeProduto').value);
            //Ira só salvar se existir dados válidos
            if ($scope.formNovoProd.$valid) {
                var metodo = 'POST'; //Inserir
                var acao = 'cadastrar';
                if (self.produto.codProduto) {//Caso existir, alterar
                    metodo = 'PUT'; //Alterar
                    acao ='atualizar';
                }
                $http({
                    method: metodo,
                    url: urlBase + 'produtos/' +acao,
                    data: self.produto
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            } else{
                //Tratamento de aviso que deu erro
            }
        };
       
        //Ao clicar no botão Alterar em produto.html 
        //ira tornar visivel a div de alterar, e deixar o produto defined
        self.alterar = function(produto){
            self.produto = produto //Atribuindo o produto selecionado
            self.divNovo=false;
            self.divAlterar=true;
        };
        
        self.excluir = function(produto){
            //self.produto = produto;
            codProduto = produto.codProduto;
            //alert(codProduto);
            $http({
                method: 'DELETE',
                url: urlBase + 'produtos/' + codProduto + '/'
            }).then(function successCallback(response) {
                self.atualizarTabela();
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        };
 
        self.ocorreuErro = function () {
            //Falta Implementar.
            alert("Ocorreu um erro inesperado!");
        };

        self.atualizarTabela = function () {
            $http({
                method: 'GET',
                url: urlBase + 'produtos/listarTodos'
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


    }); //Fim do Arquivo

        
        
        
            
            
