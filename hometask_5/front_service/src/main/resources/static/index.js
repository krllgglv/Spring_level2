angular.module('app', []).controller('indexController', function ($scope, $http) {

    $scope.loadProducts = function () {
        $http({
            url: 'http://localhost:8189/front/api/v1/products',
            method: 'GET'
        }).then(function (response) {
            $scope.products = response.data;
        });
    }
    $scope.loadProducts();
});