angular.module('market').controller('productsController', function ($scope, $http, $localStorage) {

    const contextPath = 'http://localhost:5555/core/api/v1';

    $scope.fillTable = function (page = 1) {
        $http({
            url: contextPath +'/products',
            method: 'GET',
            params: {
                p: page,
                titleFilter: $scope.filter ? $scope.filter.titleFilter : null,
                priceMinFilter: $scope.filter ? $scope.filter.priceMinFilter : null,
                priceMaxFilter: $scope.filter ? $scope.filter.priceMaxFilter : null
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);

        });
    };

    $scope.deleteProduct = function (id) {
        $http.get(contextPath + '/products/remove/' + id)
            .then(function (response) {
                $scope.fillTable();
            })
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }


    $scope.findProductById = function (id) {
        $http.get(contextPath + '/products/' + id)
            .then(function (response) {
                $scope.foundProduct = response.data;
            });
    }

    $scope.addProductCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.guestCartId + '/add/' + id)
            .then(function (response) {
            });
    }

    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }

    $scope.fillTable();
});