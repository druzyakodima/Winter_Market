angular.module('market').controller('userController', function ($scope, $http, $localStorage) {

        $scope.createNewUser = function () {
            $http.post('http://localhost:5555/auth/create', $scope.user )
                .then(function (response) {


                    $localStorage.user = {
                        username: response.data.username,
                        password: response.data.password,
                        email: response.data.email,
                        roles: response.data.roles
                    };

                });
        }

        $scope.allRoles = function () {
            $http.get('http://localhost:5555/auth/roles').
                then(function (responses) {
                    $scope.role = responses.data;
            })
        }

        $scope.allRoles();
    });