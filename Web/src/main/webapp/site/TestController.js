var myModule = angular.module('app2', []);
myModule.config(function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

myModule.controller('MyCtrl', function($scope, $http) {
    $scope.greetings = 'Hello world!';

    //$http.get('http://127.0.0.1:8080/Web-1.0-SNAPSHOT/rest/all').
    $http.get('/Web-1.0-SNAPSHOT/rest/all').
        success(function(data) {
            $scope.entities = data;
        });
});
myModule.controller('Controller', function($scope, $http) {
    $scope.master= {};
    $scope.update = function(user) {
        $scope.master= angular.copy(user);
        $http({
            //url: 'http://localhost:8080/Web-1.0-SNAPSHOT/rest/test',
            url: '/Web-1.0-SNAPSHOT/rest/test',
            method: "POST",
            data: "name="+user.name,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
    };
    $scope.reset = function() {
        $scope.user = angular.copy($scope.master);
    };
    $scope.reset();
});

