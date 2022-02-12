angular.module('market-front').controller('welcomeController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/stat';

    $scope.loadProductsInCarts = function () {
        $http({
            url: contextPath + '/api/v1/stat/popular/products/in_carts',
            method: 'GET'
        }).then(function (response) {
            $scope.productsInCarts = response.data;
        });
    };
    $scope.loadProductsInOrders = function () {
        $http({
            url: contextPath + '/api/v1/stat/popular/products/in_orders',
            method: 'GET'
        }).then(function (response) {
            $scope.productsInOrders = response.data;
        });
    };

    $scope.loadProductsInCarts();
    $scope.loadProductsInOrders();
});