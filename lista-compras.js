angular.module("Lista", ['datatables'])//[] = Conjunto de modulos a importar
    .value('urlBase', 'http://localhost:8080/comprasMariazinha/resources/')
    .controller("ListaComprasController", function ($http, urlBase, $scope) {
       
 var self = this;


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


}); //Fim do Arquivo

//function ListaComprasController($scope) {
//}