angular.module('market').controller('orderFormController', function ($scope, $http, $localStorage) {

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders', $scope.OrderData)
            .then(function (response) {

                alert("Place on order")
                $localStorage.OrderData = {phone: response.data.phone, address: response.data.address};
                $scope.fillCart()
            });
    }

    $scope.fillCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.guestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.fillCart();
});