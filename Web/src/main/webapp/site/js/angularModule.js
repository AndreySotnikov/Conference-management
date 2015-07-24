var routerApp = angular.module('app', ['ui.router','door3.css']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'views/home.html'
        })
        .state('home.login', {
            url: '/login',
            templateUrl: 'views/login.html',
            css: 'css/registration.css'
            //controller: function($scope, $http){
            //    
            //}
        })
        .state('home.register', {
            url: '/register',
            templateUrl: 'views/registration.html',
            css: 'css/registration.css',
            controller: function($scope, $http) {
                $scope.master= {};
                $scope.clickBtn = function (user) {
                    $scope.master= angular.copy(user);
                    $http({
                        url: '/Web-1.0-SNAPSHOT/rest/register',
                        method: "POST",
                        data: "login="+user.username+
                            "&password="+user.password+
                            "&role="+user.role+
                            "&phone="+user.phone+
                            "&email="+user.email+
                            "&name="+user.name,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    });
                }

            }
        })
        .state('translation', {
            url: '/translation',
            templateUrl: 'views/translation.html',
            controller: 'translationCtrl'
        });
});
routerApp.controller('translationCtrl', function($scope, $http) {

    $scope.period = 30;
    $scope.defaultperiod = 30;

    $('#showup').hide();

    $scope.reports = [
        "time",
        "text"
    ];
    $scope.reports = [];

    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.speechId = 2;
    $scope.lastId = 0;
    $scope.server = "http://localhost:8080/";
    // ������� ��������� �������
    $http.get($scope.server + $scope.warName + "/rest/speech/topic/" + $scope.speechId)
        .success(function (data) {
            $scope.title = data;
        });
    // ������� ��� ��������� ����������
    $http.get($scope.server + $scope.warName + "/rest/trans/fbs/" + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });

    $scope.autoUpdates = function() {
        if ($scope.autoUpdate){
            $scope.more();
            if ($scope.myForm.$valid){
                setTimeout($scope.autoUpdates, 1000*$scope.period);
            }else{
                setTimeout($scope.autoUpdates, 1000*$scope.defaultperiod);
            }

        }
    }

    // �������� ����������
    $scope.more = function() {
        $http({
            url: $scope.server + $scope.warName + "/rest/trans/updates",
            method: "GET",
            params: {speechId: $scope.speechId, lastId: $scope.lastId}
        }).success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });
    }

    $scope.changeCheckbox = function() {
        if ($scope.autoUpdate){
            $('#showup').show();
            $scope.autoUpdates();
        }else{
            $('#showup').hide();
        }
    }
});











