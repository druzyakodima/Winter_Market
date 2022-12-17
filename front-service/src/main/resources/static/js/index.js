
angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {

                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {

        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {

        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {

        if ($localStorage.winterMarketUser) {

            return true;
        }else {
            return false;
        }
    };



    $scope.authCheck = function () {
        $http.get('http://localhost:8189/market/auth_check/').then(function (response){
            alert(response.data.value)
        });
    };
    if ($localStorage.winterMarketUser) {

        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }
        if ($localStorage.winterMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
        }


    }

    $scope.fillTable = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.ListProducts = response.data;
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.fillTable();
            })
    }

    $scope.createNewProduct = function () {
        $http.post('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }


    $scope.findProductById = function (id) {
        $http.get('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.foundProduct = response.data;
            });
    }

    $scope.fillCart = function () {
        $http.get('http://localhost:8190/market-carts/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
            });
    }

    $scope.addProductCart = function (id) {
        $http.put('http://localhost:8190/market-carts/api/v1/cart/add/' + id)
            .then(function (response) {
                $scope.fillCart();
            });
    }

    $scope.deleteProductInCart = function (id) {
        $http.delete('http://localhost:8190/market-carts/api/v1/cart/delete/' + id)
            .then(function (response) {
                $scope.fillCart();
            })
    }


    $scope.clearCart = function () {
        $http.delete('http://localhost:8190/market-carts/api/v1/cart/clear')
            .then(function (response) {
                $scope.fillCart();
            })
    }

    $scope.createOrder = function () {
        $http.post('http://localhost:8189/market/api/v1/orders', $scope.OrderData)
            .then(function (response) {

                alert("Place on order")

                $localStorage.OrderData = {phone: response.data.phone, address: response.data.address };
                $scope.OrderData.address = response.data.address;
                $scope.OrderData.phone = response.data.phone;

                $scope.fillCart();
            });
    }

    $scope.fillCart();
    $scope.fillTable();
});
