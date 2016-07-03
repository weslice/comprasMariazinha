angular.module("Lista", ['datatables'])//[] = Conjunto de modulos a importar
    .value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
    .controller("ListaComprasController", function ($http, urlBase, $scope) {
       
    var self = this;

    self.produtos = [] //Lista de Array que vai retornar os produtos cadastrados
    
    self.carregarProdutos = function () {
            $http({
                method: 'GET',
                url: urlBase + 'produtos/listarTodos'
            }).then(function successCallback(response) {
                self.produtos = response.data; //JSON que retorna do Backend Java 
            }, function errorCallback(response) {
                self.ocorreuErro();
            });
        };

    //Metodo para ativar e sempre carregar os produtos no Select
        self.activate = function (){
            self.carregarProdutos();
        };
        self.activate(); //Ativar 


    $scope.itens = [
        {produto: 'Leite', quantidade: 2, comprado: false},
        {produto: 'Cerveja', quantidade: 12, comprado: false}
    ];

    $scope.adicionaItem = function () {
        $scope.itens.push({produto: $scope.item.produto,
                           quantidade: $scope.item.quantidade,
                           comprado: false});
        $scope.item.produto = $scope.item.quantidade = '';
    };
    
    self.ocorreuErro = function () {
            //Falta Implementar.
            alert("Ocorreu um erro inesperado!");
        };


}); //Fim do Module Lista

angular.module("CadastroLista", ['datatables'])//[] = Conjunto de modulos a importar
    .value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
    .controller("ListaCadastradas", function ($http, urlBase, $scope) {
    
    var self = this;

    self.listas = [] //Lista de Array que vai retornar as listas cadastrados
    self.lista = undefined;
    
    
      self.ocorreuErro = function () {
            //Falta Implementar.
            alert("Ocorreu um erro inesperado!");
        };
    self.carregarListas = function () {
//            $http({
//                method: 'GET',
//                url: urlBase + 'lista/carregarlista'
//            }).then(function successCallback(response) {
//                self.listas = response.data; //JSON que retorna do Backend Java 
//                self.lista = undefined;
//            }, function errorCallback(response) {
//                self.ocorreuErro();
//            });
        };
        
        
           self.novo = function(){
            self.lista = {}; //É igual a um objeto vazio, deixando de ser
        //Undefined, para assim exibir a div no produtos.html para poder inserir
        };
        
          self.alterar = function(lista){
            self.lista = lista //Atribuindo a Lista selecionado
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
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            } else{
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
 
      

        
     
        
    
    //Metodo para ativar e sempre carregar os produtos no Select
    self.activate = function (){
        self.carregarListas();
    };
    self.activate(); //Ativar 



}); //Fim do Module CadastroLista