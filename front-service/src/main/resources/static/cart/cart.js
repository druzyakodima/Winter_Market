angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:5555/cart/api/v1/cart/';

    $scope.fillCart = function () {
        $http.get(contextPath + $localStorage.guestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + $localStorage.guestCartId + '/clear')
            .then(function (response) {
                $scope.fillCart();
            })
    }

    $scope.addProductCart = function (id) {
        $http.get(contextPath +  $localStorage.guestCartId + '/add/' + id)
            .then(function (response) {
                $scope.fillCart()
            });
    }

    $scope.deleteProductInCart = function (id) {
        $http.get(contextPath + $localStorage.guestCartId + '/delete/' + id)
            .then(function (response) {
                $scope.fillCart();
            })
    }

    $scope.fillCart();
});